import java.net.InetAddress;

/*
 * �ռ��ͻ�����Ϣ: ������+ip��ַ
 */
class UserInfo {
	public String name = null;
	public String ipAddr = null;
	public InetAddress inet = null;

	public UserInfo() {
		try {
			inet = InetAddress.getLocalHost();
			this.name = inet.getHostName();
			this.ipAddr = inet.getHostAddress();
		} catch (Exception e) {
		}
	}
}
