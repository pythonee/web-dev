package EE557.WangFei.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

import EE557.WangFei.Domain.Student;

public class BaseDAO {
	
	public Calendar getLocalTimeZone(){
		Calendar cal = Calendar.getInstance();
		//set the time zone
		TimeZone timeZone = TimeZone.getTimeZone("Asia/Chongqing");
		
		cal.setTimeZone(timeZone);
		
		return cal;
	}

	public void delete(int id,String tableName,String primaryColumn){
		
		Connection conn = ConnectManager.getConnection();
		
		String sql = "DELETE FROM " + tableName + " WHERE " + primaryColumn + "=?";
		
		try {
			
			PreparedStatement stam = conn.prepareStatement(sql);
			stam.setInt(1, id);
			
			stam.executeUpdate();
			
			stam.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}finally{
			
			ConnectManager.closeConnection(conn);
			
		}
		
	}

	public void update(String tableName,String primaryColumn,int id ,String columnName,String value){
		Connection conn = ConnectManager.getConnection();
		
		String sql = "update " + tableName + " set " + columnName + "=?" + " where " + primaryColumn + "=?";
		
		try {
			
			PreparedStatement stam = conn.prepareStatement(sql);
			
			stam.setString(1, value);
			stam.setInt(2, id);

			stam.executeUpdate();
			
			stam.close();
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}finally{
			
			ConnectManager.closeConnection(conn);
			
		}
	}

	public void update(String tableName,String primaryColumn,int id ,String columnName,Date value){
		Connection conn = ConnectManager.getConnection();
		
		String sql = "update " + tableName + " set " + columnName + "=?" + " where " + primaryColumn + "=?";
		
		try {
			
			PreparedStatement stam = conn.prepareStatement(sql);
			
			stam.setDate(1, value);
			stam.setInt(2, id);

			stam.executeUpdate();
			
			stam.close();
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}finally{
			
			ConnectManager.closeConnection(conn);
			
		}
	}

	public void update(String tableName ,String primaryColumn,int id , String columnName,int value){
		Connection conn = ConnectManager.getConnection();
		
		String sql = "update " + tableName + " set " + columnName + "=?" + " where " + primaryColumn + "=?";
		
		try {
			
			PreparedStatement stam = conn.prepareStatement(sql);
			
			stam.setInt(1, value);
			stam.setInt(2, id);

			stam.executeUpdate();
			
			stam.close();
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}finally{
			
			ConnectManager.closeConnection(conn);
			
		}
	}
	
	public void update(String tableName ,String firstPrimaryColumn,String secondPrimaryColumn,int fid ,int sid, String columnName,int value){
		Connection conn = ConnectManager.getConnection();
		
		String sql = "update " + tableName + " set " + columnName + "=?" + " where " + 
					 firstPrimaryColumn + "=?" + " and " + secondPrimaryColumn + "=?";
		
		try {
			
			PreparedStatement stam = conn.prepareStatement(sql);
			
			stam.setInt(1, value);
			stam.setInt(2, fid);
			stam.setInt(3, sid);

			stam.executeUpdate();
			
			stam.close();
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}finally{
			
			ConnectManager.closeConnection(conn);
			
		}
	}
	
	// count the rows of a special table
	public int count(String tableName){
		
		Connection conn = ConnectManager.getConnection();
		
		String sql = "select COUNT(*) from " + tableName;
		
		int count = 0;
		
		try {
			Statement stam = conn.createStatement();
			ResultSet rs = stam.executeQuery(sql);
			
			if (rs.next()) {
				count = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ConnectManager.closeConnection(conn);
		}
		return count;
	}
}
