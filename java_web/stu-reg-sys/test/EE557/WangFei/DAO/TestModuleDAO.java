package EE557.WangFei.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

import EE557.WangFei.Domain.Module;
import junit.framework.TestCase;

public class TestModuleDAO extends TestCase{

	private ModuleDAO moduleDAO;
	
	@Override
	protected void setUp() throws Exception {
		moduleDAO = new ModuleDAO();
	}

	public void testInsert(){
		
		Module module = new Module();
		module.setModuleDesc("Module desc");
		module.setModuleLast(12);
		module.setModuleStart(new Date(12,1,1));
		module.setModuleTeacher(TestTeacherDAO.getTestID());
		module.setModuleTitle("EE548");
		
		moduleDAO.insert(module);
		
		assertTrue(true);
		
	}
	
	public void testFindAll(){
		
		assertEquals(moduleDAO.count(), moduleDAO.findAll().size());
		
	}
	
	public void testFindByID(){
		assertNotNull(moduleDAO.findByID(getTestID()));
	}
	
	public void testFindAllProgramme(){
		assertNotNull(moduleDAO.findAllProgramme(34));
	}
	
	public void testUpdate(){
		
		moduleDAO.update(getTestID(), ModuleDAO.MODULE_TITLE_COLUMN, "EE548");
		
	}
	
	public void testDelete(){
		
		moduleDAO.delete(getTestID());
	}
	
	
	public static int getTestID(){
		
		Connection conn = ConnectManager.getConnection();
		
		String sql = "select " + ModuleDAO.MODULE_ID_COLUMN + " from " + ModuleDAO.MODULE_TABLE;
		
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
