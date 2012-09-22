package EE557.WangFei.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import EE557.WangFei.Domain.ProgrammeModuleAssociate;
import EE557.WangFei.Domain.StudentModuleAssociate;

public class StudentModuleAssociateDAO extends BaseDAO {
	public final static String STUDENTMODULEASSOCIATE_TABLE = "wangfei_student_module" ;
	public final static String STUDENTMODULEASSOCIATE_MODULE_ID_COLUMN = "module_id";
	public final static String STUDENTMODULEASSOCIATE_STU_ID_COLUMN = "stu_id";

	
	private final static Logger log = Logger.getLogger(StudentModuleAssociateDAO.class);
	
	public List<StudentModuleAssociate> findAll(){
		
		List<StudentModuleAssociate> studentModuleList = new ArrayList<StudentModuleAssociate>();
		
		Connection conn = ConnectManager.getConnection();
		
		String sql = "select * from " + STUDENTMODULEASSOCIATE_TABLE;
		
		try {
			
			Statement stam = conn.createStatement();
			
			ResultSet rs  = stam.executeQuery(sql);
			
			while (rs.next()) {
				
				StudentModuleAssociate manytomany = new StudentModuleAssociate(rs.getInt(1),
																			   rs.getInt(2)
																			   );
				studentModuleList.add(manytomany);
			}
			
			rs.close();
			stam.close();
			
		} catch (Exception e) {
			 
			e.printStackTrace();
			
		} finally {
			
			ConnectManager.closeConnection(conn);
			
		}
		
		if (studentModuleList.size() == 0) {
			
			log.debug("finish find all student&module...");
			return Collections.EMPTY_LIST;
		}
		
		log.debug("finish find all student&module...");
		return studentModuleList;
	}

	public List<StudentModuleAssociate> findByID(int id){
		
		List<StudentModuleAssociate> studentModuleAssociates = new ArrayList<StudentModuleAssociate>();
		
		Connection conn = ConnectManager.getConnection();
		
		String sql = "select * from " + STUDENTMODULEASSOCIATE_TABLE + " where " + STUDENTMODULEASSOCIATE_STU_ID_COLUMN + "=?";
		
		try {
			
			PreparedStatement stam = conn.prepareStatement(sql);
			
			stam.setInt(1, id);
			
			ResultSet rs  = stam.executeQuery();
			
			while (rs.next()) {
				StudentModuleAssociate studentModuleAssociate = new StudentModuleAssociate(rs.getInt(1),rs.getInt(2));
				studentModuleAssociates.add(studentModuleAssociate);
			}
			
			rs.close();
			stam.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			ConnectManager.closeConnection(conn);
			
		}
		
		log.debug("finish find student&module by id...");
		return studentModuleAssociates;
		
	}
	
	
	public void insert(StudentModuleAssociate sma){
		
		Connection conn = ConnectManager.getConnection();
		
		String sql = "INSERT INTO "+ STUDENTMODULEASSOCIATE_TABLE + 
					 " (" + STUDENTMODULEASSOCIATE_MODULE_ID_COLUMN + "," + STUDENTMODULEASSOCIATE_STU_ID_COLUMN  + ")" +
					 " VALUES (?, ?)";
		
		try {
			
			PreparedStatement stam  = conn.prepareStatement(sql);
			stam.setInt(1, sma.getModule_id());
			stam.setInt(2, sma.getStu_id());
			
			stam.executeUpdate();
			
			stam.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			ConnectManager.closeConnection(conn);
			
		}
		
		log.debug("finish insert a student&module...");
		
	}

	public void delete(int id) {
		super.delete(id, STUDENTMODULEASSOCIATE_TABLE, STUDENTMODULEASSOCIATE_STU_ID_COLUMN);
	}

	public void update(int fid ,int sid, String columnName,int value){
		super.update(STUDENTMODULEASSOCIATE_TABLE, STUDENTMODULEASSOCIATE_MODULE_ID_COLUMN, STUDENTMODULEASSOCIATE_STU_ID_COLUMN,
				     fid,sid,columnName, value);
		
		log.debug("finish update student&module...");
	}
}
