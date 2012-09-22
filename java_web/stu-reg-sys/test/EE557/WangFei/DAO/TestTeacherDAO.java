package EE557.WangFei.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import EE557.WangFei.Domain.Teacher;
import junit.framework.TestCase;

public class TestTeacherDAO extends TestCase {

	TeacherDAO teacherDAO;
	
	
	@Override
	protected void setUp() throws Exception {
		teacherDAO = new TeacherDAO();
	}

	
	public void testInsert(){
		
		Teacher teacher = new Teacher(1,"monly","david");
		teacherDAO.insert(teacher);
		
	}
	
	public void testFindAll(){
		
		assertEquals(teacherDAO.count(), teacherDAO.findAll().size());
		
	}
	
	public void testUpdate(){
		
		teacherDAO.update(getTestID(), TeacherDAO.TEACHER_FIRSTNAME_COLUMN, "fan");
		
	}
	
	public void testDelete(){
		
		teacherDAO.delete(getTestID());
		
	}
	
	public static int getTestID(){
		
		Connection conn = ConnectManager.getConnection();
		
		String sql = "select " + TeacherDAO.TEACHER_ID_COLUMN + " from " + TeacherDAO.TEACHER_TABLE;
		
		int id = 0;
		
		try {
			Statement stam = conn.createStatement();
			ResultSet rs = stam.executeQuery(sql);
			
			while (rs.next()) {
				id = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ConnectManager.closeConnection(conn);
		}
		return id;
	}
	
}
