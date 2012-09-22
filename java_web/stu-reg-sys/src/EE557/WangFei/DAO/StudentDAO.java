package EE557.WangFei.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

import java.sql.Date;

import org.apache.log4j.Logger;

import EE557.WangFei.Domain.Student;

	public class StudentDAO extends BaseDAO{

	public final static String STUDENT_TABLE = "wangfei_student" ;
	public final static String STUDENT_ID_COLUMN = "stu_id" ;
	public final static String STUDENT_USERNAME_COLUMN = "stu_username";
	public final static String STUDENT_PASSWORD_COLUMN = "stu_password";
	public final static String STUDENT_FIRSTNAME_COLUMN = "stu_first_name";
	public final static String STUDENT_LASTNAME_COLUMN = "stu_last_name";
	public final static String STUDENT_EMAIL_COLUMN = "stu_email";
	public final static String STUDENT_SEX_COLUMN = "stu_sex" ;
	public final static String STUDENT_COUNTRY_COLUMN = "stu_country";
	public final static String STUDENT_BIRTHDAY_COLUMN = "stu_birthday";
	public final static String STUDENT_REGIST_TIME_COLUMN = "stu_registe_time";
	public final static String STUDENT_ISPAID_COLUMN = "stu_ispaid";
	public final static String STUDENT_PROGRAMME_ID_COLUMN = "stu_programme_id";
	
	private final static Logger log = Logger.getLogger(StudentDAO.class);
	
	
	// find all student
	public List<Student> findAll() {
		
		List<Student> students = new ArrayList<Student>();
		
		Connection conn = ConnectManager.getConnection();

		// query string
		String sql = "Select * from " + STUDENT_TABLE;
		
		try {
			Statement stam = conn.createStatement();
			
			ResultSet rs = stam.executeQuery(sql);
						
			while (rs.next()) {
				Student student = new Student(
									rs.getInt(1),
									rs.getString(2),
									rs.getString(3),
									rs.getString(4),
									rs.getString(5).charAt(0),
									rs.getString(6),
									rs.getInt(7),
									rs.getString(8).charAt(0),
									rs.getString(9),
									rs.getDate(10),
									rs.getTimestamp(11,super.getLocalTimeZone()),
									rs.getString(12)
									);

				students.add(student);
				
			}
			
			rs.close();
			stam.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		
		}finally{
			
			ConnectManager.closeConnection(conn);
			
		}
		
		if (students.size() == 0) {
			
			log.debug("finish find all student...");
			return Collections.EMPTY_LIST;
			
		}
		
		log.debug("finish find all student...");
		
		return students;
	}
	
	// find a student by primary key(id)
	public Student findByID(int id){
		
		Connection conn = ConnectManager.getConnection();
		
		// query string
		String sql = "Select * from " + STUDENT_TABLE + " where " + STUDENT_ID_COLUMN + "=?";
		
		Student student = null;
		
		try {
			PreparedStatement stam = conn.prepareStatement(sql);
			stam.setInt(1, id);
			ResultSet rs = stam.executeQuery();
			
			if(rs.next()){
				student = new Student(rs.getInt(1),
									  rs.getString(2),
									  rs.getString(3),
									  rs.getString(4),
									  rs.getString(5).charAt(0),
									  rs.getString(6),
									  rs.getInt(7),
									  rs.getString(8).charAt(0),											 
									  rs.getString(9),
									  rs.getDate(10),
									  rs.getTimestamp(11,super.getLocalTimeZone()),
									  rs.getString(12)
									 );
			}
			
			rs.close();
			stam.close();		
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}finally{
			
			ConnectManager.closeConnection(conn);
			
		}
		
		log.debug("finish find student by id...");
		
		return student;
	}
	
	
	public Student findByUsername(String username){
		Connection conn = ConnectManager.getConnection();
		
		// query string
		String sql = "Select * from " + STUDENT_TABLE + " where " + STUDENT_USERNAME_COLUMN + "=?";
		
		Student student = null;
		
		try {
			PreparedStatement stam = conn.prepareStatement(sql);
			stam.setString(1, username);
			ResultSet rs = stam.executeQuery();
			
			if(rs.next()){
				student = new Student(rs.getInt(1),
									  rs.getString(2),
									  rs.getString(3),
									  rs.getString(4),
									  rs.getString(5).charAt(0),
									  rs.getString(6),
									  rs.getInt(7),
									  rs.getString(8).charAt(0),											 
									  rs.getString(9),
									  rs.getDate(10),
									  rs.getTimestamp(11,super.getLocalTimeZone()),
									  rs.getString(12)
									 );
			}
			
			rs.close();
			stam.close();		
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}finally{
			
			ConnectManager.closeConnection(conn);
			
		}
		
		log.debug("finish find student by username...");
		
		return student;
		
	}
	// add a student
	public void insert(Student student){
		
		Connection conn = ConnectManager.getConnection();
		
		String sql = "INSERT INTO "+ STUDENT_TABLE + 
					 " (" + STUDENT_FIRSTNAME_COLUMN + "," + STUDENT_LASTNAME_COLUMN +"," + STUDENT_PASSWORD_COLUMN + "," + STUDENT_SEX_COLUMN + "," + 
					   STUDENT_EMAIL_COLUMN + "," + STUDENT_PROGRAMME_ID_COLUMN + "," +  STUDENT_ISPAID_COLUMN + "," + STUDENT_COUNTRY_COLUMN + "," +
					   STUDENT_BIRTHDAY_COLUMN +"," + STUDENT_USERNAME_COLUMN + ") " +
					 "VALUES (?, ?, ?, ?, ?, ? , ? , ? , ? , ? )";
				
		try {
			PreparedStatement stam = conn.prepareStatement(sql);
			
			// ready the parameter
			stam.setString(1, student.getFirstName());
			stam.setString(2, student.getLastName());
			stam.setString(3, student.getPassword());
			stam.setString(4, String.valueOf(student.getSex()));
			stam.setString(5, student.getEmail());
			stam.setInt(6, student.getProgrammeID());
			stam.setString(7, String.valueOf(student.getIsPaid()));
			stam.setString(8, student.getCountry());
			stam.setDate(9, student.getBirthday());
			stam.setString(10, student.getUsername());
			
			// execute the query
			stam.executeUpdate();
			
			stam.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}finally{
			
			ConnectManager.closeConnection(conn);
			
		}
		
		log.debug("finish insert a student...");
		
	}
	
	// delete a student
	public void delete(int id){
		super.delete(id, STUDENT_TABLE, STUDENT_ID_COLUMN);
		
		log.debug("finish delete student by id...");
	}
	
	// update user ( include password, email, ispaid, sex )
	public void update(int id , String columnName,String value){
		super.update(STUDENT_TABLE,STUDENT_ID_COLUMN,id,columnName, value);
		
		log.debug("finish update student...");
	}
	
	// update user ( only birthday )
	public void update(int id , String columnName,Date value){
		super.update(STUDENT_TABLE,STUDENT_ID_COLUMN,id,columnName,value);
		
		log.debug("finish update student...");
	}
	
	// update user ( only  )
	public void update(int id , String columnName,int value){
		super.update(STUDENT_TABLE,STUDENT_ID_COLUMN,id,columnName,value);
		
		log.debug("finish update student...");
	}
	
	public int count(){
		return super.count(STUDENT_TABLE);
	}
}
