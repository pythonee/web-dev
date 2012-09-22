package EE557.WangFei.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import EE557.WangFei.Domain.Programme;

import junit.framework.TestCase;

public class TestProgrammeDAO extends TestCase {
	
	ProgrammeDAO programmeDAO;

	@Override
	protected void setUp() throws Exception {
		programmeDAO = new ProgrammeDAO();
	}

	public void testInsert(){
		
		Programme programme = new Programme();
		
		programme.setProgrammeTitle("whu");
		programme.setProgrammeDESC("DCU&WHU");
		programme.setCost(10);
		
		programmeDAO.insert(programme);
		
	}
	
	public void testFindAll(){
		assertEquals(programmeDAO.count(), programmeDAO.findAll().size());
	}
	
	public void testFindByID(){
		assertNotNull(programmeDAO.findByID(getTestID()));
	}
	
	public void testFindAllModule(){
		assertNotNull(programmeDAO.findAllModule(0));
	}
	
	public void testUpdate(){
		
		programmeDAO.update(getTestID(), ProgrammeDAO.PROGRAMME_COST_COLUMN, 1000);
		
	}
	
	public void testDelete(){
		
		if (programmeDAO.count() > 0) {
			
			programmeDAO.delete(getTestID());
			
		}
		
	}
	
	public static int getTestID(){
		
		Connection conn = ConnectManager.getConnection();
		
		String sql = "select " + ProgrammeDAO.PROGRAMME_ID_COLUMN + " from " + ProgrammeDAO.PROGRAMME_TABLE;
		
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
