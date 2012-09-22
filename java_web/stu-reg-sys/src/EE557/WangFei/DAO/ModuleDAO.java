package EE557.WangFei.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import EE557.WangFei.Domain.Module;
import EE557.WangFei.Domain.Programme;

public class ModuleDAO extends BaseDAO{
	
	public final static String MODULE_TABLE = "wangfei_module" ;
	public final static String MODULE_ID_COLUMN = "module_id" ;
	public final static String MODULE_TITLE_COLUMN = "module_title";
	public final static String MODULE_DESC_COLUMN = "module_desc";
	public final static String MODULE_TEACHER_COLUMN = "module_teacher";
	public final static String MODULE_START_COLUMN = "module_start";
	public final static String MODULE_LAST_COLUMN = "module_last";
	
	private final static Logger log = Logger.getLogger(ModuleDAO.class);
	
	public List<Module> findAll(){
		
		List<Module> modules = new ArrayList<Module>();
		
		Connection conn = ConnectManager.getConnection();
		
		String sql = "Select * from " + MODULE_TABLE;
		
		try {
			
			Statement stam = conn.createStatement();
			
			ResultSet rs = stam.executeQuery(sql);
			
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
			
			stam.close();
			rs.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		
		}finally{

			ConnectManager.closeConnection(conn);			
		}
		
		if (modules.size() == 0) {
			
			log.debug("finish find all module...");
			
			return Collections.EMPTY_LIST;
		}
		
		log.debug("finish find all module...");
		
		return modules;
	}
	
	public Module findByID(int id){
		
		Module module = null;
		
		Connection conn = ConnectManager.getConnection();
		
		String sql = "Select * from " + MODULE_TABLE + " where " + MODULE_ID_COLUMN + "=?";
		
		try {
			
			PreparedStatement stam = conn.prepareStatement(sql);
			stam.setInt(1, id);
			
			ResultSet rs = stam.executeQuery();
			
			if (rs.next()) {
				
				module = new Module(rs.getInt(1),
						   rs.getString(2),
						   rs.getString(3),
						   rs.getInt(4),
						   rs.getDate(5),
						   rs.getInt(6)
						   );
				
			}
			
			rs.close();
			stam.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}finally{
			
			ConnectManager.closeConnection(conn);
			
		}
		
		log.debug("finish find module by id...");

		return module;
	}
	
	public Module findByTitle(String title){
		Module module = null;
		
		Connection conn = ConnectManager.getConnection();
		
		String sql = "select * from " + MODULE_TABLE + " where " + MODULE_TITLE_COLUMN + "=?";
		try {
			PreparedStatement stam = conn.prepareStatement(sql);
			
			stam.setString(1, title);
			
			ResultSet rs = stam.executeQuery();
			if (rs.next()) {
				module = new Module(rs.getInt(1),
						            rs.getString(2),
						            rs.getString(3),
						            rs.getInt(4),
						            rs.getDate(5),
						            rs.getInt(6)
						   			);
			}
			
			rs.close();
			stam.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			ConnectManager.closeConnection(conn);
		}
		
		log.debug(module.getModuleID());
		return module;
	}
	
	
	public List<Programme> findAllProgramme(int moduleID){
		
		List<Programme> modules = new ArrayList<Programme>();
		
		Connection conn = ConnectManager.getConnection();
		
		String sql = "select * from " + ProgrammeDAO.PROGRAMME_TABLE + " programme " + 
					 "join " + ProgrammeModuleAssociateDAO.ProgrammeModuleAssociate_TABLE + " wmp " +
					 "on " + "programme." + ProgrammeDAO.PROGRAMME_ID_COLUMN + "=" + "wmp." + ProgrammeModuleAssociateDAO.ProgrammeModuleAssociate_PROGRAMME_ID_COLUMN +
					 " join " + ModuleDAO.MODULE_TABLE + " module " + 
					 " on " + "module." + ModuleDAO.MODULE_ID_COLUMN + "=" + "wmp." + ProgrammeModuleAssociateDAO.ProgrammeModuleAssociate_MODULE_ID_COLUMN +
					 " where " + "module." + ModuleDAO.MODULE_ID_COLUMN + "=?";
		
		try {
			
			PreparedStatement stam = conn.prepareStatement(sql);
			
			stam.setInt(1, moduleID);
			
			ResultSet rs = stam.executeQuery();
			
			while (rs.next()) {
				
				Programme programme = new Programme(rs.getInt(1),
													rs.getString(2),
													rs.getString(3),
													rs.getInt(4)
													);
				modules.add(programme);
			}
			
			rs.close();
			stam.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			ConnectManager.closeConnection(conn);
		}
		
		if (modules.size() == 0) {
			log.debug("finish find all programme of a module...");
			return Collections.EMPTY_LIST;
		}
		
		log.debug("finish find all programme of a module...");
		return modules;
	}
	
	public void insert(Module module){
		
		Connection conn = ConnectManager.getConnection();
		
		String sql = "INSERT INTO "+ MODULE_TABLE + 
					 " (" + MODULE_TITLE_COLUMN + "," + MODULE_DESC_COLUMN +"," + MODULE_TEACHER_COLUMN + "," + 
					 MODULE_START_COLUMN + "," +  MODULE_LAST_COLUMN + ") " +
					 "VALUES (?, ?, ?, ?, ?)";
		
		try {
			
			PreparedStatement stam = conn.prepareStatement(sql);
			stam.setString(1, module.getModuleTitle());
			stam.setString(2, module.getModuleDesc());
			stam.setInt(3, module.getModuleTeacher());
			stam.setDate(4, module.getModuleStart());
			stam.setInt(5, module.getModuleLast());
			
			stam.executeUpdate();
			
			stam.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		
		} finally {
			
			ConnectManager.closeConnection(conn);
			
		}
		
		log.debug("finish insert a module...");
		
	}
	
	public void delete(int id){
		super.delete(id, MODULE_TABLE, MODULE_ID_COLUMN);	
		
		log.debug("finish delete module by id...");
	
	}
	
	public void update(int id , String columnName,String value){
		super.update(MODULE_TABLE,MODULE_ID_COLUMN,id, columnName, value);
		
		log.debug("finish update module...");
		
	}

	public void update(int id , String columnName,Date value){
		super.update(MODULE_TABLE,MODULE_ID_COLUMN,id, columnName, value);
		
		log.debug("finish update module...");
		
	}

	public void update(int id , String columnName,int value){
		super.update(MODULE_TABLE,MODULE_ID_COLUMN,id, columnName, value);
		
		log.debug("finish update module...");
		
	}
	
	public int count(){
		return super.count(MODULE_TABLE);
	}
	
}
