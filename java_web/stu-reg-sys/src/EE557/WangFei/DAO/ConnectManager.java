package EE557.WangFei.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.log4j.Logger;

public class ConnectManager {
	
	private final static Logger log = Logger.getLogger(ConnectManager.class);
	
	// The method to get a connect to DataBase
	public static Connection getConnection(){
		
		Connection conn = null;
		
		//String username = "wangfei";   // DataBase Normal Username : wangfei
		//String password = "onedefour"; // DataBase Normal Password : onedefour
		//String connUrl = "jdbc:oracle:thin:@localhost:1521:StuRegSys"; // Schema : StuRegSys
		
		String username="ee_user";
		String password="ee_pass";
		String connUrl ="jdbc:oracle:thin:@136.206.35.131:1521:SSD";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(connUrl, username, password);			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		log.debug("Get Connect success...");
		
		return conn;
	}
	
	public static void closeConnection(Connection connection){
		
		try {
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		log.debug("Close connect success...");
		
	}
}
