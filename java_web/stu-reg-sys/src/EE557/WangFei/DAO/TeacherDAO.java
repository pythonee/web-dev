package EE557.WangFei.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import EE557.WangFei.Domain.Module;
import EE557.WangFei.Domain.Student;
import EE557.WangFei.Domain.Teacher;

public class TeacherDAO extends BaseDAO {

	public final static String TEACHER_TABLE = "wangfei_teacher" ;
	public final static String TEACHER_ID_COLUMN = "teacher_id" ;
	public final static String TEACHER_FIRSTNAME_COLUMN = "teacher_first_name";
	public final static String TEACHER_LASTNAME_COLUMN = "teacher_last_name";
	
	private final static Logger log = Logger.getLogger(TeacherDAO.class);
	
	public List<Teacher> findAll(){
		
		Connection conn = ConnectManager.getConnection();
		List<Teacher> teachers = new ArrayList<Teacher>();
		
		String sql = "Select * from " + TEACHER_TABLE;
		
		try {
			Statement stam = conn.createStatement();
			
			ResultSet rs = stam.executeQuery(sql);
						
			while (rs.next()) {
				Teacher teacher = new Teacher(rs.getInt(1),rs.getString(2),rs.getString(3));

				teachers.add(teacher);
				
			}
			
			rs.close();
			stam.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		
		}finally{
			
			ConnectManager.closeConnection(conn);
			
		}
		
		if (teachers.size() == 0) {
			
			log.debug("finish find all teacher...");
			return Collections.EMPTY_LIST;
			
		}
		
		log.debug("finish find all teacher...");
		
		return teachers;
		
	}
	
	public Teacher findByID(int id){
		Teacher teacher = null;
		
		Connection conn = ConnectManager.getConnection();
		
		String sql = "Select * from " + TEACHER_TABLE + " where " + TEACHER_ID_COLUMN + "=?";
		
		try {
			
			PreparedStatement stam = conn.prepareStatement(sql);
			stam.setInt(1, id);
			
			ResultSet rs = stam.executeQuery();
			
			if (rs.next()) {
				
				teacher = new Teacher(rs.getInt(1),rs.getString(2),rs.getString(3));
				
			}
			
			rs.close();
			stam.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}finally{
			
			ConnectManager.closeConnection(conn);
			
		}
		
		log.debug("finish find teacher by id...");

		return teacher;
	}
	
	public List<Module> findModulesTheTeacherTeach(int id){
		
		List<Module> modules = new ArrayList<Module>();
		
		Connection conn = ConnectManager.getConnection();
		
		String sql  = "Select * from " + ModuleDAO.MODULE_TABLE + " module "+
					  "join " + TEACHER_TABLE + " teacher " +
					  "on " + "teacher." + TEACHER_ID_COLUMN + "=" + "module." + ModuleDAO.MODULE_TEACHER_COLUMN +
					  " where " + "teacher." + TEACHER_ID_COLUMN + "=?";
		
		log.debug(sql);
		
		try {
			PreparedStatement stam  = conn.prepareStatement(sql);
			stam.setInt(1, id);
			ResultSet rs = stam.executeQuery();
			
			while (rs.next()) {
				Module module = new Module(rs.getInt(1),
						   				   rs.getString(2),
						   				   rs.getString(3),
						   				   rs.getInt(4),
						   				   rs.getDate(5),
						   				   rs.getInt(6)
										  );
				modules.add(module);
			}
			
			rs.close();
			stam.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(modules.size() == 0) {
			log.debug("finish find the modules which the teacher teach");
			return Collections.EMPTY_LIST;
		}
		
		log.debug("finish find the modules which the teacher teach");
		return modules;
		
	}
	
	public void insert(Teacher teacher){
		
		Connection conn = ConnectManager.getConnection();
		
		String sql = "INSERT INTO "+ TEACHER_TABLE + 
					 " (" + TEACHER_FIRSTNAME_COLUMN + "," + TEACHER_LASTNAME_COLUMN + ") " +
					 "VALUES (?, ?)";
		
		try {
			
			PreparedStatement stam = conn.prepareStatement(sql);
			stam.setString(1, teacher.getFirstName());
			stam.setString(2, teacher.getLastName());
			
			stam.executeUpdate();
			
			stam.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		
		} finally {
			
			ConnectManager.closeConnection(conn);
			
		}
		
		log.debug("finish insert a teacher...");
	}
	
	public void delete(int id){
		super.delete(id, TEACHER_TABLE, TEACHER_ID_COLUMN);
		
		log.debug("finish delete teacher...");
	}

	public void update(int id , String columnName,String value){
		super.update(TEACHER_TABLE, TEACHER_ID_COLUMN, id, columnName, value);
		
		log.debug("finish update teacher...");
	}

	public int count(){
		return super.count(TEACHER_TABLE);
	}
}
