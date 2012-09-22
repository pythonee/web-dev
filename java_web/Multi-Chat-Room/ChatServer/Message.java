import java.util.*;

class Message implements java.io.Serializable {

    public String name; // 客户端主机名
    public String ipAddr; // 客户端Ip地址
    public String content; // 消息正文
    public String Time; // 消息发送时间
    public String flag; // 消息标志：登入、登出、普通消息
    public String history; // 聊天记录
    public HashMap<String, String> onLineList = new HashMap<String, String>(); // 在线列表

    // 普通消息构选函数
    public Message(String name, String ipAddr, String Time, String content,	String flag) {
        this.name = name;
        this.ipAddr = ipAddr;
        this.Time = Time;
        this.content = content;
        this.flag = flag;
    }

    // 聊天记录构选函数
    public Message(String history) {
        this.history = history;
    }
}
