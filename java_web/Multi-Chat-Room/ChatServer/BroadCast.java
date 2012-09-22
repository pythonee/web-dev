import java.io.ObjectOutputStream;
import java.util.Vector;

	// ��Ϣ�㲥��
	class BroadCast extends Vector<ObjectOutputStream> {

		private static final long serialVersionUID = 1L;
		
		// ����ÿһ�� socket��writer
		public boolean add(ObjectOutputStream writer) {
			return super.add(writer);
		}
		// �Ƴ���Ӧ��writer
		void remove(ObjectOutputStream writer) {
			super.remove(writer);
		}

		// ͬ����������ֹ�ͻ���ͬʱ�������������Ϣ�����¿ͻ����յ�����Ϣ��ͬ��
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
