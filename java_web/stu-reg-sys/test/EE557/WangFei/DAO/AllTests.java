package EE557.WangFei.DAO;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for EE557.WangFei.DAO");
		//$JUnit-BEGIN$
		suite.addTestSuite(TestModuleDAO.class);
		suite.addTestSuite(TestProgrammeDAO.class);
		suite.addTestSuite(TestStudentDAO.class);
		suite.addTestSuite(TestTeacherDAO.class);
		//$JUnit-END$
		return suite;
	}

}
