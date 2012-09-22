import java.awt.*;
import java.io.File;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.tree.DefaultTreeModel;

public class ChatClientGui extends JFrame {

    public final Config tip = new Config();

    public JTextArea serverIpAddressArea = new JTextArea(); // ��������ַ�����Ĭ��Ϊ127.0.0.1
    public JTextArea msgArea = new JTextArea(); // ��Ϣ����򣬲���Ϊ��
    public JTextArea msgViewArea = new JTextArea(); // ������Ϣ��ʾ����������ʾ���������û����͵���Ϣ
    public JLabel onLineNumberTip = new JLabel(tip.onLineNumberTip);
    public JLabel onlineNumber = new JLabel(); // ��ʾ��������
    public JTree onLineTree = new JTree();
    public DefaultTreeModel onLineTreeModel = (DefaultTreeModel)onLineTree.getModel();
    public JLabel iptipsLabel = new JLabel(tip.ServerIpAddrTip); // ��ܰ��ʾ��:������IP��ַ
    public JButton connectButton = new JButton(tip.connectButtonTextTip); // ���ӷ�������ť
    public JButton unConnecButton = new JButton(tip.unConnectButtonTextTip); // �Ͽ����Ӱ�ť
    public JButton sentButton = new JButton(tip.sentButtonTextTip); // ���Ͱ�ť
    public JButton cancelButton = new JButton(tip.CancelButtonTextTip); // ȡ����ť

    public JMenuBar menuBar = new JMenuBar(); // �˵���
    public JMenu MainMenu = new JMenu(tip.menuTitleTip);
    public JMenuItem exitMenuItem = new JMenuItem(tip.exitMenuItemTitleTip); // �˳�ϵͳ�˵���

    public JToolBar toolBar = new JToolBar(tip.toolBarTitleTextTip); // ������

    public JPanel topPanel = new JPanel(); // �����ͻ���
    public JPanel leftPanel = new JPanel(); // ��߿ͻ���
    public JPanel rightPanel = new JPanel(); // �ұ߿ͻ���
    public JPanel bottomPanel = new JPanel(); // �ײ��ͻ���
    // �������
    public JPanel holdMessageViewPanel = new JPanel();
    public JPanel holdMessageInputPanel = new JPanel();
    public JPanel holdOnlineMemberPanel = new JPanel();
    public JPanel holdValidatePanel = new JPanel();
    public JPanel onLineNumberPanel = new JPanel();

    public JFileChooser history = new JFileChooser();              // ѡ�������¼�����·���ĶԻ���

    // �˷�����������ͻ��˵��û�ͼ�ν���
    public void ClientBuild() {
        msgViewArea.setEditable(false); // ������Ϣ��Ϊ����д
        unConnecButton.setEnabled(false); // ��ʼ״̬�����������Ӵ��ڣ����Բ����ԶϿ�����
        serverIpAddressArea.setText(tip.defaultServerIpAddr);
        msgViewArea.setLineWrap(true);
        msgArea.setLineWrap(true);
        onLineTreeModel.setRoot(null);

        JScrollPane msgViewScrollPane = new JScrollPane(msgViewArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollPane msgInputScrollPane = new JScrollPane(msgArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollPane onLineMemberScrollPane = new JScrollPane(onLineTree,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        MainMenu.add(exitMenuItem);
        menuBar.add(MainMenu);

        toolBar.setFloatable(true);
        toolBar.add(connectButton);
        toolBar.add(unConnecButton);
        toolBar.addSeparator();

        topPanel.setLayout(new GridLayout(2, 1));
        leftPanel.setLayout(new GridLayout(2, 1));
        rightPanel.setLayout(new GridLayout(1, 1));
        bottomPanel.setLayout(new FlowLayout());

        holdValidatePanel.setLayout(new GridLayout(1, 1));
        holdValidatePanel.add(iptipsLabel);
        holdValidatePanel.add(serverIpAddressArea);
        topPanel.add(toolBar);
        topPanel.add(holdValidatePanel);

        holdMessageViewPanel.setLayout(new BoxLayout(holdMessageViewPanel,BoxLayout.Y_AXIS));
        holdMessageInputPanel.setLayout(new BoxLayout(holdMessageInputPanel,BoxLayout.Y_AXIS));
        holdMessageViewPanel.setBorder(new TitledBorder(tip.msgViewBorderTitleTip));
        holdMessageInputPanel.setBorder(new TitledBorder(tip.msgInputBorderTitleTip));
        holdMessageViewPanel.add(msgViewScrollPane);
        holdMessageInputPanel.add(msgInputScrollPane);
        leftPanel.add(holdMessageViewPanel);
        leftPanel.add(holdMessageInputPanel);

        onLineNumberPanel.setLayout(new FlowLayout());
        onLineNumberPanel.add(onLineNumberTip);
        onLineNumberPanel.add(onlineNumber);
        holdOnlineMemberPanel.setLayout(new BoxLayout(holdOnlineMemberPanel,BoxLayout.Y_AXIS));
        holdOnlineMemberPanel.setBorder(new TitledBorder(tip.whoIsOnLineBorderTitleTip));
        holdOnlineMemberPanel.add(onLineNumberPanel);
        holdOnlineMemberPanel.add(onLineMemberScrollPane);
        rightPanel.add(holdOnlineMemberPanel);

        bottomPanel.add(sentButton);
        bottomPanel.add(cancelButton);

        JSplitPane contentPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                leftPanel, rightPanel);
        contentPane.setOneTouchExpandable(true);
        contentPane.setDividerLocation(tip.DividerLocation);

        this.getContentPane().setLayout(new BorderLayout(1, 10));
        this.getContentPane().add(BorderLayout.NORTH, topPanel);
        this.getContentPane().add(BorderLayout.CENTER, contentPane);
        this.getContentPane().add(BorderLayout.SOUTH, bottomPanel);

        history.setDialogTitle(tip.historySaveTip);
        // ����������ʷ�����ļ��Ĺ�����
        history.setFileFilter(new FileFilter(){
            public boolean accept(File f){
                if (f.getName().endsWith(tip.fileFilter) || f.isDirectory()) {
                    return true;
                }
                return false;
            }
            public String getDescription(){
                return tip.fileFilterDescription;
            }
        });

        // ���ò˵���
        this.setJMenuBar(menuBar);
        // ���ô��ڵ���С�ߴ�
        this.setMinimumSize(new Dimension(tip.width, tip.height));
        // �ô��ھ���
        this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - this
                    .getWidth()) / 2, (Toolkit.getDefaultToolkit()
                    .getScreenSize().height - this.getHeight()) / 2);
        this.setVisible(true);
    }


    // ���캯��
    public ChatClientGui() {
        super("Welcome to this chating room..");
        this.ClientBuild();
    }
}
