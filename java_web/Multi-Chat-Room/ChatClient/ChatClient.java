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
    private ChatThread thread;                  // �������ͨ���̣߳����й����г���ֻ֤��һ��ʵ��
    private Helper helper = new Helper();
    private UserInfo userInfo = new UserInfo(); // �ͻ�����Ϣ�࣬������������IP��ַ
    private boolean connState = false; // ����״̬�������ӣ�δ����
    private File file;

    // ��ʼ��
    public void init() {
        gui.onlineNumber.setText(0+"");
        // ����¼�����
        gui.exitMenuItem.addActionListener(btListener);
        gui.connectButton.addActionListener(btListener);
        gui.unConnecButton.addActionListener(btListener);
        gui.sentButton.addActionListener(btListener);
        gui.cancelButton.addActionListener(btListener);
        gui.addKeyListener(keyListener);
        gui.addWindowListener(windListener);

        // ѡ�������¼�ļ��ľ���
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
            connState = false; // ����״̬Ϊ�Ͽ����߳̽���ɱ��
        } catch (Exception e) {
        } finally {
            System.exit(0);
        }
    }
    // ��ť�¼�����
    ActionListener btListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            // �������
            if (e.getActionCommand().equals(gui.tip.connectButtonTextTip)) {
                try {
                    thread = new ChatThread();
                    thread.Connect(); // �������
                    thread.start(); // ���������߳�
                    /*
                     * ���͵�����Ϣ��������
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
            // �Ͽ�����
            if (e.getActionCommand().equals(gui.tip.unConnectButtonTextTip)) {
                if (connState) {
                    try {
                        // ����������͹ر�������Ϣ
                        gui.connectButton.setEnabled(true);
                        gui.unConnecButton.setEnabled(false);
                        Message logoutNotify = new Message(userInfo.name,
                                userInfo.ipAddr,
                                helper.getCurrTime(),
                                "",
                                gui.tip.LogOutFlag);
                        thread.writer.writeObject(logoutNotify);
                        thread.writer.flush();
                        connState = false; // ����״̬Ϊ�Ͽ����߳̽���ɱ��
                    } catch (Exception ie) {
                    }
                }
            }
            /*
             * �������������Ϣ
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
             * ȡ������
             */
            if (e.getActionCommand().equals(gui.tip.CancelButtonTextTip)) {
                gui.msgArea.setText("");
            }
            if(e.getActionCommand().equals(gui.tip.exitMenuItemTitleTip)){
                exit();
            }
        }
    };

    // �����¼�����
    KeyAdapter keyListener = new KeyAdapter() {
        public void keyPressed(KeyEvent k) {
            if (k.getKeyCode() == KeyEvent.VK_ESCAPE) {
                System.exit(0);
            }
        }
    };
    // �����¼�����
    WindowAdapter windListener = new WindowAdapter() {
        public void windowClosing(WindowEvent w) {
            exit();
        }
    };

    // �������ͨ���߳��࣬��һ���ڲ���
    class ChatThread extends Thread {
        ObjectOutputStream writer;
        ObjectInputStream reader;
        private final int portNumber = 777; // �˳���ʹ�õĶ˿ں�
        private Socket socket;
        private Message msg;
        private Message historyMsg;

        public void Connect() {
            try {
                socket = new Socket(gui.serverIpAddressArea.getText(), portNumber);
                // �������ӣ�Load�����¼
                ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
                historyMsg =(Message)is.readObject();
                // ��ʾ���������¼
                gui.msgViewArea.append(historyMsg.history);
                connState = true;  //��Ϣ����ѭ����ʼ
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
                        gui.msgViewArea.append(msg.name +" @ "+ msg.Time + " " +msg.flag+"\n"); 	// ��ʾ������Ϣ
                        gui.onlineNumber.setText(msg.onLineList.size()+"");             			// ������������
                        helper.listFormate(gui.onLineTree,msg.onLineList);                          // ���������б�
                    } else if (msg.flag.equals(gui.tip.msgFlag)) {
                        gui.msgViewArea.append(helper.msgFormate(msg) + msg.content + "\n");       	// ��ʾ��ͨ��Ϣ
                        if (file != null) {
                            helper.saveToFile(helper.msgFormate(msg)+msg.content + "\n", file);	  	// ���������¼
                        }
                    } else if (msg.flag.equals(gui.tip.LogOutFlag)) {
                        gui.msgViewArea.append(msg.name+" @ " + msg.Time + " "+ msg.flag +"\n");   	// ��ʾ�ǳ���Ϣ
                        gui.onlineNumber.setText(msg.onLineList.size()+"");   						// ������������
                        helper.listFormate(gui.onLineTree,msg.onLineList);							// ���������б�
                    }
                }
            } catch (Exception e) {
            } finally {
                // �ر�����
                try {
                    if(thread.writer != null) thread.writer.close();
                    if(thread.reader != null) thread.writer.close();
                    if(thread.socket != null) thread.socket.close();
                    gui.connectButton.setEnabled(true);		// ���Ӱ�ť����
                    gui.unConnecButton.setEnabled(false);	// �Ͽ����Ӱ�ť������
                    gui.onlineNumber.setText(0+"");			// ����������
                    gui.msgViewArea.setText("");			// �����Ϣ
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
        client.init();         // �ͻ��˳�ʼ��������
    }
}
