import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/*
 * ������
 */
class Helper
{
    // ��ȡϵͳ��ǰʱ��
    public String getCurrTime() {
        String curr;
        String am_pm;
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR);
        int minute = now.get(Calendar.MINUTE);
        int second = now.get(Calendar.SECOND);

        if (now.get(Calendar.AM_PM) == 0) {
            am_pm = "AM";
        } else {
            am_pm = "PM";
        }
        curr = hour + ":" + minute + ":" + second + " " + am_pm;

        return curr;
    }

    // ������Ϣ��ʽ��,��Ҫ��Ϊ�˶���
    public String msgFormate(Message msg){
        String msgStr = new String();
        String spaces = new String();

        for (int i = 0; i < 16-msg.name.length(); i++) {
            spaces += " ";
        }
        msgStr += msg.name + spaces + " @ " +msg.Time + " said: " + "\n";

        return msgStr;
    }

    // �����б��ʽ������Ҫ��Ϊ�˰�HashMap�͵�OnLineListд��Jtree��ȥ
    public void listFormate(JTree onLineTree,HashMap<String, String> onLineList) {

        String listStr = new String();
        DefaultTreeModel model = (DefaultTreeModel)onLineTree.getModel();
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Who's on line");
        model.setRoot(root);

        Iterator<Entry<String, String>> iter = onLineList.entrySet().iterator();

        while (iter.hasNext()) {
            Entry<String, String> entry = iter.next();
            listStr += entry.toString() + "\n";
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(entry.toString());
            root.add(node);
        }
        onLineTree.expandPath(new TreePath(root.getPath()));
    }

    // �������¼д���ļ���
    public void saveToFile(String str, File file) throws IOException {
        try {
            FileOutputStream fos = new FileOutputStream(file.getAbsolutePath(),true);
            byte[] words = str.getBytes();
            fos.write(words);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
        } catch(IOException ie){
        }
    }
}
