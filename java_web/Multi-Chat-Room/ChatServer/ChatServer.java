import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;

public class ChatServer {
    private ChatServerGui gui = new ChatServerGui();
    private BroadCast broadCast = new BroadCast(); // 广播对象
    private Helper helper = new Helper();
    private ServerSocket serverSocket;  // 服务器Socket
    private final int portNumber = 777; // 此程序使用的端口号
    private File file;

    WindowAdapter winListener = new WindowAdapter() {
        public void windowClosing(WindowEvent w) {
            System.exit(0);
        }
    };

    public void startServer() {
        // 选择聊天记录文件的径径
        int rVal = gui.history.showSaveDialog(gui);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            file = gui.history.getSelectedFile();
        } else {
            gui.serverInfoArea.append(gui.tip.notFilePath + "\n");
        }
        try {
            serverSocket = new ServerSocket(portNumber);
            gui.serverInfoArea.append(gui.tip.ServerStartSuccess+"\n");
            while (true) {
                Socket socket = serverSocket.accept();
                if (file != null && file.exists()) {
                    ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
                    Message history = new Message(helper.readHistory(file));
                    os.writeObject(history);
                }
                ChatThread thread = new ChatThread(socket);   // 聊天线程在这里创建
                broadCast.add(thread.writer);
                thread.start();
            }
        } catch (Exception e) {
            gui.serverInfoArea.append(gui.tip.ServerStartErr+"\n");
        }
    }

    // 与客户机通信类
    // 作为一个内部类
    class ChatThread extends Thread {
        Socket socket;
        boolean connState; // 连接状态
        private ObjectInputStream reader;
        private ObjectOutputStream writer;
        private Message msg;

        public ChatThread(Socket socket) {
            this.socket = socket;
            this.connState = true;
            try {
                this.writer = new ObjectOutputStream(socket.getOutputStream());
                this.reader = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
            }
        }

        public void run() {
            try {
                // 消息处理循环
                while (connState) {
                    msg = (Message) reader.readObject();
                    if (msg.flag.equals(gui.tip.LogInFlag)) {					// 登入消息
                        gui.serverInfoArea.append(msg.name + " @ " + msg.Time + " " + msg.flag + "\n");
                        Message welcomeMsg = new Message(msg.name,msg.ipAddr,msg.Time,msg.content,gui.tip.LogInFlag);
                        if (!msg.onLineList.containsKey(msg.ipAddr)) {
                            msg.onLineList.put(msg.ipAddr, msg.name);
                            welcomeMsg.onLineList = msg.onLineList;
                        }
                        gui.onLineNumber.setText(welcomeMsg.onLineList.size()+"");
                        broadCast.sendMsgToAll(welcomeMsg);
                    } else if (msg.flag.equals(gui.tip.LogOutFlag)) {			// 登出消息
                        gui.serverInfoArea.append(msg.name + " @ " + msg.Time + " " + msg.flag);
                        Message exitMsg = new Message(msg.name,msg.ipAddr,msg.Time,msg.content,gui.tip.LogOutFlag);
                        if (msg.onLineList.containsKey(msg.ipAddr)) {
                            msg.onLineList.remove(msg.ipAddr);
                            exitMsg.onLineList = msg.onLineList;
                        }
                        exitMsg.onLineList.remove(msg.ipAddr);
                        gui.onLineNumber.setText(exitMsg.onLineList.size()+"");
                        this.connState = false;
                        broadCast.sendMsgToAll(exitMsg);
                    }else if(msg.flag.equals(gui.tip.msgFlag)){                 // 普通消息
                        gui.serverInfoArea.append(msg.name + " said: "+msg.content +"\n");
                        Message normalMsg = new Message(msg.name,msg.ipAddr,msg.Time,msg.content,gui.tip.msgFlag);
                        broadCast.sendMsgToAll(normalMsg);
                        if (file != null && file.exists()) {
                            helper.saveHistory(helper.msgFormate(msg)+ msg.content +"\n", file); // 保存消息记录
                        }
                    }
                }
            } catch (Exception e) {
            } finally {
                try {
                    // 关闭连接
                    if (writer != null)		writer.close();
                    if (reader != null)		reader.close();
                    if (socket != null)		socket.close();
                    broadCast.remove(writer);
                    gui.serverInfoArea.append(gui.tip.connectClosed + "\n");
                } catch (Exception e) {
                }
            }
        }
    }

    public static void main(String args[])
    {
        ChatServer server = new ChatServer();
        server.startServer();          // 启动服务器
    }
}
