package workshop.noodles.service;

import org.springframework.beans.factory.annotation.Autowired;

import workshop.noodles.dao.TestDao;
import workshop.noodles.model.entities.TestModel;

public class TestService {
	
	@Autowired
	private TestDao testDao;

	public TestModel getTest() {
		// TODO Auto-generated method stub
		return testDao.getTest();
	}

}
