import java.net.InetAddress;

/*
 * 收集客户端信息: 主机名+ip地址
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
