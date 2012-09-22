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

public class ProgrammeModuleAssociateDAO extends BaseDAO{

	public final static String ProgrammeModuleAssociate_TABLE = "wangfei_module_programme" ;
	public final static String ProgrammeModuleAssociate_MODULE_ID_COLUMN = "module_id";
	public final static String ProgrammeModuleAssociate_PROGRAMME_ID_COLUMN = "programme_id";

	
	private final static Logger log = Logger.getLogger(ProgrammeModuleAssociateDAO.class);
	
	public List<ProgrammeModuleAssociate> findAll(){
		
		List<ProgrammeModuleAssociate> programmeModuleAssociateList = new ArrayList<ProgrammeModuleAssociate>();
		
		Connection conn = ConnectManager.getConnection();
		
		String sql = "select * from " + ProgrammeModuleAssociate_TABLE;
		
		try {
			
			Statement stam = conn.createStatement();
			
			ResultSet rs  = stam.executeQuery(sql);
			
			while (rs.next()) {
				
				ProgrammeModuleAssociate manytomany = new ProgrammeModuleAssociate(	rs.getInt(1),
																					rs.getInt(2)
																				   );
				programmeModuleAssociateList.add(manytomany);
			}
			
			rs.close();
			stam.close();
			
		} catch (Exception e) {
			 
			e.printStackTrace();
			
		} finally {
			
			ConnectManager.closeConnection(conn);
			
		}
		
		if (programmeModuleAssociateList.size() == 0) {
			
			log.debug("finish find all programme&module...");
			return Collections.EMPTY_LIST;
		}
		
		log.debug("finish find all programme&module...");
		return programmeModuleAssociateList;
	}

	public ProgrammeModuleAssociate findByID(int id){
		
		ProgrammeModuleAssociate programmeModuleAssociate = null;
		
		Connection conn = ConnectManager.getConnection();
		
		String sql = "select * from " + ProgrammeModuleAssociate_TABLE + " where " + "=?";
		
		try {
			
			PreparedStatement stam = conn.prepareStatement(sql);
			
			stam.setInt(1, id);
			
			ResultSet rs  = stam.executeQuery();
			
			if (rs.next()) {
				programmeModuleAssociate = new ProgrammeModuleAssociate(rs.getInt(1),rs.getInt(2));
			}
			
			rs.close();
			stam.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			ConnectManager.closeConnection(conn);
			
		}
		
		log.debug("finish find programme&module by id...");
		return programmeModuleAssociate;
		
	}
	
	public void insert(ProgrammeModuleAssociate pma){
		
		Connection conn = ConnectManager.getConnection();
		
		String sql = "INSERT INTO "+ ProgrammeModuleAssociate_TABLE + 
					 " (" + ProgrammeModuleAssociate_MODULE_ID_COLUMN + "," + ProgrammeModuleAssociate_PROGRAMME_ID_COLUMN  + ")" +
					 " VALUES (?, ?)";
		
		try {
			
			PreparedStatement stam  = conn.prepareStatement(sql);
			stam.setInt(1, pma.getModule_id());
			stam.setInt(2, pma.getProgramme_id());
			
			stam.executeUpdate();
			
			stam.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			ConnectManager.closeConnection(conn);
			
		}
		
		log.debug("finish insert a programme&module...");
		
	}
	
	public void update(int fid ,int sid, String columnName,int value){
		super.update(ProgrammeModuleAssociate_TABLE, ProgrammeModuleAssociate_MODULE_ID_COLUMN, ProgrammeModuleAssociate_PROGRAMME_ID_COLUMN,
				     fid,sid,columnName, value);
		
		log.debug("finish update programme&module...");
	}
}
