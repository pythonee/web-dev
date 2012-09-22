import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JFileChooser;

public class ChatClient {

    private ChatClientGui gui = new ChatClientGui();
    private ChatThread thread;                  // 与服务器通信线程，运行过程中程序保证只有一个实例
    private Helper helper = new Helper();
    private UserInfo userInfo = new UserInfo(); // 客户端信息类，包括主机名和IP地址
    private boolean connState = false; // 连接状态：已连接，未连接
    private File file;

    // 初始化
    public void init() {
        gui.onlineNumber.setText(0+"");
        // 添加事件监听
        gui.exitMenuItem.addActionListener(btListener);
        gui.connectButton.addActionListener(btListener);
        gui.unConnecButton.addActionListener(btListener);
        gui.sentButton.addActionListener(btListener);
        gui.cancelButton.addActionListener(btListener);
        gui.addKeyListener(keyListener);
        gui.addWindowListener(windListener);

        // 选择聊天记录文件的径径
        int rVal = gui.history.showSaveDialog(gui);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            file = gui.history.getSelectedFile();
        } else {
            gui.msgViewArea.append(gui.tip.notFilePath + "\n");
        }
    }

    public void exit(){
        try {
            Message logoutNotify = new Message(userInfo.name,
                    userInfo.ipAddr,
                    helper.getCurrTime(),
                    "",
                    gui.tip.LogOutFlag);
            thread.writer.writeObject(logoutNotify);
            thread.writer.flush();
            connState = false; // 连接状态为断开，线程将被杀死
        } catch (Exception e) {
        } finally {
            System.exit(0);
        }
    }
    // 按钮事件监听
    ActionListener btListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            // 获得连接
            if (e.getActionCommand().equals(gui.tip.connectButtonTextTip)) {
                try {
                    thread = new ChatThread();
                    thread.Connect(); // 获得连接
                    thread.start(); // 启动聊天线程
                    /*
                     * 发送登入消息到服务器
                     */
                    Message loginNotify = new Message(userInfo.name,
                            userInfo.ipAddr,
                            helper.getCurrTime(),
                            "",
                            gui.tip.LogInFlag);
                    thread.writer.writeObject(loginNotify);
                    thread.writer.flush();
                } catch (Exception ie) {
                }
            }
            // 断开连接
            if (e.getActionCommand().equals(gui.tip.unConnectButtonTextTip)) {
                if (connState) {
                    try {
                        // 向服务器发送关闭连接消息
                        gui.connectButton.setEnabled(true);
                        gui.unConnecButton.setEnabled(false);
                        Message logoutNotify = new Message(userInfo.name,
                                userInfo.ipAddr,
                                helper.getCurrTime(),
                                "",
                                gui.tip.LogOutFlag);
                        thread.writer.writeObject(logoutNotify);
                        thread.writer.flush();
                        connState = false; // 连接状态为断开，线程将被杀死
                    } catch (Exception ie) {
                    }
                }
            }
            /*
             * 向服务器发送消息
             */
            if (e.getActionCommand().equals(gui.tip.sentButtonTextTip)) {
                try {
                    if (connState) {
                        Message normalMsg = new Message(userInfo.name,
                                userInfo.ipAddr,
                                helper.getCurrTime(),
                                gui.msgArea.getText(),
                                gui.tip.msgFlag);
                        thread.writer.writeObject(normalMsg);
                        gui.msgArea.setText("");
                    } else {
                        gui.msgViewArea.append(gui.tip.notConnectErr+"\n");
                    }
                } catch (Exception ie) {
                }
            }
            /*
             * 取消发送
             */
            if (e.getActionCommand().equals(gui.tip.CancelButtonTextTip)) {
                gui.msgArea.setText("");
            }
            if(e.getActionCommand().equals(gui.tip.exitMenuItemTitleTip)){
                exit();
            }
        }
    };

    // 键盘事件监听
    KeyAdapter keyListener = new KeyAdapter() {
        public void keyPressed(KeyEvent k) {
            if (k.getKeyCode() == KeyEvent.VK_ESCAPE) {
                System.exit(0);
            }
        }
    };
    // 窗口事件监听
    WindowAdapter windListener = new WindowAdapter() {
        public void windowClosing(WindowEvent w) {
            exit();
        }
    };

    // 与服务器通信线程类，是一个内部类
    class ChatThread extends Thread {
        ObjectOutputStream writer;
        ObjectInputStream reader;
        private final int portNumber = 777; // 此程序使用的端口号
        private Socket socket;
        private Message msg;
        private Message historyMsg;

        public void Connect() {
            try {
                socket = new Socket(gui.serverIpAddressArea.getText(), portNumber);
                // 初次连接，Load聊天记录
                ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
                historyMsg =(Message)is.readObject();
                // 显示离线聊天记录
                gui.msgViewArea.append(historyMsg.history);
                connState = true;  //消息处理循环开始
                gui.connectButton.setEnabled(false);
                gui.unConnecButton.setEnabled(true);
                writer = new ObjectOutputStream(socket.getOutputStream());
                reader = new ObjectInputStream(socket.getInputStream());
            } catch (Exception e) {
                gui.msgViewArea.append(gui.tip.ConnectErr + "\n");
            }
        }

        public void run() {
            try {
                while (connState) {
                    msg = (Message) reader.readObject();
                    if (msg.flag.equals(gui.tip.LogInFlag)) {
                        gui.msgViewArea.append(msg.name +" @ "+ msg.Time + " " +msg.flag+"\n"); 	// 显示登入消息
                        gui.onlineNumber.setText(msg.onLineList.size()+"");             			// 更新在线人数
                        helper.listFormate(gui.onLineTree,msg.onLineList);                          // 更新在线列表
                    } else if (msg.flag.equals(gui.tip.msgFlag)) {
                        gui.msgViewArea.append(helper.msgFormate(msg) + msg.content + "\n");       	// 显示普通消息
                        if (file != null) {
                            helper.saveToFile(helper.msgFormate(msg)+msg.content + "\n", file);	  	// 保存聊天记录
                        }
                    } else if (msg.flag.equals(gui.tip.LogOutFlag)) {
                        gui.msgViewArea.append(msg.name+" @ " + msg.Time + " "+ msg.flag +"\n");   	// 显示登出消息
                        gui.onlineNumber.setText(msg.onLineList.size()+"");   						// 更新在线人数
                        helper.listFormate(gui.onLineTree,msg.onLineList);							// 更新在线列表
                    }
                }
            } catch (Exception e) {
            } finally {
                // 关闭连接
                try {
                    if(thread.writer != null) thread.writer.close();
                    if(thread.reader != null) thread.writer.close();
                    if(thread.socket != null) thread.socket.close();
                    gui.connectButton.setEnabled(true);		// 连接按钮可用
                    gui.unConnecButton.setEnabled(false);	// 断开连接按钮不可用
                    gui.onlineNumber.setText(0+"");			// 在线人数零
                    gui.msgViewArea.setText("");			// 清除消息
                    gui.msgArea.setText("");
                    gui.onLineTreeModel.setRoot(null);
                    gui.msgViewArea.append(gui.tip.connectClosed + "\n");
                } catch (Exception ex) {
                }
            }
        }
    }

    public static void main(String args[]){
        ChatClient client = new ChatClient();
        client.init();         // 客户端初始化、启动
    }
}
