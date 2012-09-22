package EE557.WangFei.DAO;


import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import EE557.WangFei.Domain.Student;

import junit.framework.TestCase;

public class TestStudentDAO extends TestCase{
	
	private StudentDAO studentDAO;

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		studentDAO = new StudentDAO();
	}
	
	public void testInsert(){
		
		Student student = new Student();
		
		Date date = new Date(2009,12,01);
		
		student.setFirstName("Wang");
		student.setLastName("Fei");
		student.setUsername("pythonee");
		student.setPassword("onedefour");
		student.setEmail("pythonee@gmail.com");
		student.setCountry("china");
		student.setIsPaid('Y');
		student.setProgrammeID(0);
		student.setSex('M');
		student.setBirthday(date);
		
		studentDAO.insert(student);
		
		assertTrue(true);
		
	}
	
	public void testFindAll(){
		
		List<Student> students = studentDAO.findAll();
		
		assertEquals(TestStudentDAO.countAll(), students.size());
	}
	
	public void testFindByID(){
		
		Student student = studentDAO.findByID(getTestID());
		
		assertNotNull(student);
		
	}
	
	public void testUpdate(){
		
		studentDAO.update(getTestID(), StudentDAO.STUDENT_FIRSTNAME_COLUMN, "Fei");
		studentDAO.update(getTestID(), StudentDAO.STUDENT_LASTNAME_COLUMN, "Wang");
		
	}
	
	public void testDelete(){
		studentDAO.delete(getTestID());
	}
	
	
	public static int countAll(){
		
		Connection conn = ConnectManager.getConnection();
		
		String sql = "select COUNT(*) from " + StudentDAO.STUDENT_TABLE;
		
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
	
	public static int getTestID(){
		
		Connection conn = ConnectManager.getConnection();
		
		String sql = "select " + StudentDAO.STUDENT_ID_COLUMN + " from " + StudentDAO.STUDENT_TABLE;
		
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
