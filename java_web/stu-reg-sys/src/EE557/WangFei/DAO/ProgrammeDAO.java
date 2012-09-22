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
import EE557.WangFei.Domain.Programme;
import EE557.WangFei.Domain.Student;

public class ProgrammeDAO extends BaseDAO{
	
	public final static String PROGRAMME_TABLE = "wangfei_programme" ;
	public final static String PROGRAMME_ID_COLUMN = "programme_id";
	public final static String PROGRAMME_TITLE_COLUMN = "programme_title";
	public final static String PROGRAMME_DESC_COLUMN = "programme_desc";
	public final static String PROGRAMME_COST_COLUMN = "programme_cost";
	
	private final static Logger log = Logger.getLogger(ProgrammeDAO.class);

	public List<Programme> findAll(){
		
		List<Programme> programmes = new ArrayList<Programme>();
		
		Connection conn  = ConnectManager.getConnection();
		
		String sql = "Select * from " + PROGRAMME_TABLE; 
		
		try {
			
			Statement stam = conn.createStatement();
			ResultSet rs = stam.executeQuery(sql);
			
			while (rs.next()) {
				Programme programme = new Programme(rs.getInt(1),
													rs.getString(2),
													rs.getString(3),
													rs.getInt(4)
													);
				
				programmes.add(programme);
			}
			
			rs.close();
			stam.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (programmes.size() == 0) {
			log.debug("finish find all programme...");
			return Collections.EMPTY_LIST;
		}
		
		log.debug("finish find all programme...");
		
		return programmes;
	}
	
	public Programme findByID(int id){
		
		Connection conn  = ConnectManager.getConnection();
		
		String sql = "Select * from " + PROGRAMME_TABLE + " where " + PROGRAMME_ID_COLUMN + "=?"; 
		
		Programme programme = null;
		
		try {
			
			PreparedStatement stam = conn.prepareStatement(sql);
			
			stam.setInt(1, id);
			
			ResultSet rs = stam.executeQuery();
			
			if (rs.next()) {
				
				programme = new Programme(rs.getInt(1),
										  rs.getString(2),
										  rs.getString(3),
										  rs.getInt(4)
										);
				
			}
			
			rs.close();
			stam.close();
			
		} catch (Exception e) {
		
			e.printStackTrace();
		
		} finally{
			
			ConnectManager.closeConnection(conn);
			
		}
		
		log.debug("finish find programme by id...");
		
		return programme;
	}
	
	public List<Module> findAllModule(int programmeID){
		
		List<Module> modules = new ArrayList<Module>();
		
		Connection conn = ConnectManager.getConnection();
		
		String sql = "select * from " + ModuleDAO.MODULE_TABLE + " module " + 
					 "join " + ProgrammeModuleAssociateDAO.ProgrammeModuleAssociate_TABLE + " wmp " +
					 "on " + "module." + ModuleDAO.MODULE_ID_COLUMN + "=" + "wmp." + ProgrammeModuleAssociateDAO.ProgrammeModuleAssociate_MODULE_ID_COLUMN +
					 " join " + ProgrammeDAO.PROGRAMME_TABLE + " programme " + 
					 " on " + "programme." + ProgrammeDAO.PROGRAMME_ID_COLUMN + "=" + "wmp." + ProgrammeModuleAssociateDAO.ProgrammeModuleAssociate_PROGRAMME_ID_COLUMN +
					 " where " + "programme." + ProgrammeDAO.PROGRAMME_ID_COLUMN + "=?";
		
		try {
			
			PreparedStatement stam = conn.prepareStatement(sql);
			
			stam.setInt(1, programmeID);
			
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
		} finally{
			ConnectManager.closeConnection(conn);
		}
		
		if (modules.size() == 0) {
			log.debug("finish find all modules of a programme...");
			return Collections.EMPTY_LIST;
		}
		
		log.debug("finish find all modules of a programme...");
		return modules;
	}
	
	public void insert(Programme programme){
		
		Connection conn = ConnectManager.getConnection();		
		
		String sql = "INSERT INTO "+ PROGRAMME_TABLE + 
		 " (" + PROGRAMME_TITLE_COLUMN + "," + PROGRAMME_DESC_COLUMN +"," + PROGRAMME_COST_COLUMN + ")" +
		 " VALUES (?, ?, ?)";
		
		try {
			
			PreparedStatement stam  = conn.prepareStatement(sql);
			stam.setString(1, programme.getProgrammeTitle());
			stam.setString(2, programme.getProgrammeDESC());
			stam.setInt(3, programme.getCost());
			
			stam.executeUpdate();
			
			stam.close();
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		
		} finally{
			
			ConnectManager.closeConnection(conn);
			
		}
		
		log.debug("finish insert a programme...");
		
	}
	
	public void delete(int id){
		super.delete(id, PROGRAMME_TABLE, PROGRAMME_ID_COLUMN);
		
		log.debug("finish delete programme by id...");
	}
	
	public void update(int id ,String columnName,String value){
		super.update(PROGRAMME_TABLE, PROGRAMME_ID_COLUMN, id, columnName, value);
		
		log.debug("finish update programme...");
	}
	
	public void update(int id , String columnName,int value){
		super.update(PROGRAMME_TABLE, PROGRAMME_ID_COLUMN, id, columnName, value);
		
		log.debug("finish update programme...");
	}
	
	public int count(){
		return super.count(PROGRAMME_TABLE);
	}
	

}
