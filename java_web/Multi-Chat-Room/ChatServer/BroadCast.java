import java.io.ObjectOutputStream;
import java.util.Vector;

	// 消息广播类
	class BroadCast extends Vector<ObjectOutputStream> {

		private static final long serialVersionUID = 1L;
		
		// 加入每一个 socket的writer
		public boolean add(ObjectOutputStream writer) {
			return super.add(writer);
		}
		// 移除对应的writer
		void remove(ObjectOutputStream writer) {
			super.remove(writer);
		}

		// 同步方法，防止客户端同时向服务器发送消息，导致客户端收到的消息不同步
		synchronized void sendMsgToAll(Message msg) {
			ObjectOutputStream writer = null;
			for (int i = 0; i < size(); i++) {
				writer = (ObjectOutputStream) elementAt(i);
				try {
					//writer = new ObjectOutputStream(socket.getOutputStream());
					writer.writeObject(msg);
					writer.flush();
				} catch (Exception e) {
					if (writer != null) {
						System.out.println(writer.toString());
					}
				}
			}
		}
	}
