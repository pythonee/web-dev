import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class Helper {

    // 写聊天记录
    public void saveHistory(String str, File file) {
        try {
            FileOutputStream fos = new FileOutputStream(file.getAbsolutePath(),	true);
            byte[] words = str.getBytes();
            fos.write(words);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
        } catch (IOException ie) {
        }
    }

    // 读聊天记录
    public String readHistory(File file) throws IOException {
        StringBuffer history = new StringBuffer();
        String str;
        try {
            BufferedReader fis = new BufferedReader(new FileReader(file.getAbsolutePath()));
            while ((str = fis.readLine()) != null) {
                history.append(str);
                history.append("\n");
            }
        } catch (FileNotFoundException e) {
        }

        return history.toString();
    }

    // 聊天消息格式化,主要是为了对齐
    public String msgFormate(Message msg) {
        String msgStr = new String();
        String spaces = new String();

        for (int i = 0; i < 16 - msg.name.length(); i++) {
            spaces += " ";
        }
        msgStr += msg.name + spaces + " @ " + msg.Time + " said: " + "\n";

        return msgStr;
    }
}
