import java.awt.*;
import java.io.File;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.tree.DefaultTreeModel;

public class ChatClientGui extends JFrame {

    public final Config tip = new Config();

    public JTextArea serverIpAddressArea = new JTextArea(); // 服务器地址输入框，默认为127.0.0.1
    public JTextArea msgArea = new JTextArea(); // 消息输入框，不能为空
    public JTextArea msgViewArea = new JTextArea(); // 聊天消息显示区域，这里显示所有在线用户发送的消息
    public JLabel onLineNumberTip = new JLabel(tip.onLineNumberTip);
    public JLabel onlineNumber = new JLabel(); // 显示在线人数
    public JTree onLineTree = new JTree();
    public DefaultTreeModel onLineTreeModel = (DefaultTreeModel)onLineTree.getModel();
    public JLabel iptipsLabel = new JLabel(tip.ServerIpAddrTip); // 温馨提示语:服务器IP地址
    public JButton connectButton = new JButton(tip.connectButtonTextTip); // 连接服务器按钮
    public JButton unConnecButton = new JButton(tip.unConnectButtonTextTip); // 断开连接按钮
    public JButton sentButton = new JButton(tip.sentButtonTextTip); // 发送按钮
    public JButton cancelButton = new JButton(tip.CancelButtonTextTip); // 取消按钮

    public JMenuBar menuBar = new JMenuBar(); // 菜单栏
    public JMenu MainMenu = new JMenu(tip.menuTitleTip);
    public JMenuItem exitMenuItem = new JMenuItem(tip.exitMenuItemTitleTip); // 退出系统菜单项

    public JToolBar toolBar = new JToolBar(tip.toolBarTitleTextTip); // 工具栏

    public JPanel topPanel = new JPanel(); // 顶部客户区
    public JPanel leftPanel = new JPanel(); // 左边客户区
    public JPanel rightPanel = new JPanel(); // 右边客户区
    public JPanel bottomPanel = new JPanel(); // 底部客户区
    // 辅助面板
    public JPanel holdMessageViewPanel = new JPanel();
    public JPanel holdMessageInputPanel = new JPanel();
    public JPanel holdOnlineMemberPanel = new JPanel();
    public JPanel holdValidatePanel = new JPanel();
    public JPanel onLineNumberPanel = new JPanel();

    public JFileChooser history = new JFileChooser();              // 选择聊天记录保存的路径的对话框

    // 此方法用来构造客户端的用户图形界面
    public void ClientBuild() {
        msgViewArea.setEditable(false); // 设置消息框为不可写
        unConnecButton.setEnabled(false); // 初始状态不何能有连接存在，所以不可以断开连接
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
        // 设置聊天历史保存文件的过滤器
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

        // 设置菜单条
        this.setJMenuBar(menuBar);
        // 设置窗口的最小尺寸
        this.setMinimumSize(new Dimension(tip.width, tip.height));
        // 让窗口居中
        this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - this
                    .getWidth()) / 2, (Toolkit.getDefaultToolkit()
                    .getScreenSize().height - this.getHeight()) / 2);
        this.setVisible(true);
    }


    // 构造函数
    public ChatClientGui() {
        super("Welcome to this chating room..");
        this.ClientBuild();
    }
}
