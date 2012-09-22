import java.util.*;

class Message implements java.io.Serializable {

    public String name; // �ͻ���������
    public String ipAddr; // �ͻ���Ip��ַ
    public String content; // ��Ϣ����
    public String Time; // ��Ϣ����ʱ��
    public String flag; // ��Ϣ��־�����롢�ǳ�����ͨ��Ϣ
    public String history; // �����¼
    public HashMap<String, String> onLineList = new HashMap<String, String>(); // �����б�

    // ��ͨ��Ϣ��ѡ����
    public Message(String name, String ipAddr, String Time, String content,	String flag) {
        this.name = name;
        this.ipAddr = ipAddr;
        this.Time = Time;
        this.content = content;
        this.flag = flag;
    }

    // �����¼��ѡ����
    public Message(String history) {
        this.history = history;
    }
}
