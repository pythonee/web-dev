package com.mashup.base;

import org.apache.log4j.Logger;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

public class BaseTransactionalDataSourceTests extends
		AbstractTransactionalDataSourceSpringContextTests {

	protected Logger log = Logger.getLogger(this.getClass().getName());

	@Override
	protected String[] getConfigLocations() {
		// TODO Auto-generated method stub
		String[] config;

		config = new String[] {
				"file:WebRoot/WEB-INF/applicationContext-dao.xml",
				"file:WebRoot/WEB-INF/applicationContext-hibernate.xml",
				"file:WebRoot/WEB-INF/applicationContext-mail.xml",
				"file:WebRoot/WEB-INF/applicationContext-mail-template.xml",
				"file:WebRoot/WEB-INF/applicationContext-service.xml" };

		return config;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.test.AbstractTransactionalSpringContextTests#isDefaultRollback()
	 */
	@Override
	protected boolean isDefaultRollback() {
		// TODO Auto-generated method stub
		return true;
	}
}
