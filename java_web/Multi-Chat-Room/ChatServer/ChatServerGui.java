import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;

public class ChatServerGui extends JFrame {

	public final Config tip = new Config();
	
	public JFileChooser history = new JFileChooser();
	public JLabel onLineNumberTipLabel = new JLabel(tip.onLineNumberTip);
	public JLabel onLineNumber = new JLabel(); 			// ��ʾ��������
	public JTextArea serverInfoArea = new JTextArea(); // ��ʾ����������״̬
	public JPanel onLineNumberPanel = new JPanel();
	
	public void ChatServerGUI() {
		JScrollPane serverinfoScrollPane = new JScrollPane(serverInfoArea,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		serverInfoArea.setLineWrap(true);
		
		onLineNumberPanel.setLayout(new GridLayout(1,2));
		onLineNumberPanel.add(onLineNumberTipLabel);
		onLineNumberPanel.add(onLineNumber);
		
		
		this.getContentPane().add(BorderLayout.NORTH, onLineNumberPanel);
		this.getContentPane().add(BorderLayout.CENTER, serverinfoScrollPane);

		this.addWindowListener(windListener);
		
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
		
		this.setMinimumSize(new Dimension(tip.width, tip.height));
		// �ô��ھ���
		this.setLocation(
				(Toolkit.getDefaultToolkit().getScreenSize().width - this
						.getWidth()) / 2, (Toolkit.getDefaultToolkit()
						.getScreenSize().height - this.getHeight()) / 2);
		this.setVisible(true);
	}

	// ������Ϣ����
	WindowAdapter windListener = new WindowAdapter() {
		public void windowClosing(WindowEvent w) {
			System.exit(0);
		}
	};

	public ChatServerGui() {
		super("The Chat Server");
		ChatServerGUI();
	}
}
