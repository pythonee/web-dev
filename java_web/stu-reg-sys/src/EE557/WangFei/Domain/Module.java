package EE557.WangFei.Domain;

import java.sql.Date;

public class Module {

	private int moduleID;
	private String moduleTitle;
	private String moduleDesc;
	private int moduleTeacher;
	private Date moduleStart;
	private int moduleLast;
	
	public Module(){
		
	}
	
	public Module(int moduleID, String moduleTitle, String moduleDesc,
			int moduleTeacher, Date moduleStart, int moduleLast) {
		super();
		this.moduleID = moduleID;
		this.moduleTitle = moduleTitle;
		this.moduleDesc = moduleDesc;
		this.moduleTeacher = moduleTeacher;
		this.moduleStart = moduleStart;
		this.moduleLast = moduleLast;
	}

	/**
	 * @return the moduleID
	 */
	public int getModuleID() {
		return moduleID;
	}

	/**
	 * @param moduleID the moduleID to set
	 */
	public void setModuleID(int moduleID) {
		this.moduleID = moduleID;
	}

	/**
	 * @return the moduleTitle
	 */
	public String getModuleTitle() {
		return moduleTitle;
	}

	/**
	 * @param moduleTitle the moduleTitle to set
	 */
	public void setModuleTitle(String moduleTitle) {
		this.moduleTitle = moduleTitle;
	}

	/**
	 * @return the moduleDesc
	 */
	public String getModuleDesc() {
		return moduleDesc;
	}

	/**
	 * @param moduleDesc the moduleDesc to set
	 */
	public void setModuleDesc(String moduleDesc) {
		this.moduleDesc = moduleDesc;
	}

	/**
	 * @return the moduleTeacher
	 */
	public int getModuleTeacher() {
		return moduleTeacher;
	}

	/**
	 * @param moduleTeacher the moduleTeacher to set
	 */
	public void setModuleTeacher(int moduleTeacher) {
		this.moduleTeacher = moduleTeacher;
	}

	/**
	 * @return the moduleStart
	 */
	public Date getModuleStart() {
		return moduleStart;
	}

	/**
	 * @param moduleStart the moduleStart to set
	 */
	public void setModuleStart(Date moduleStart) {
		this.moduleStart = moduleStart;
	}

	/**
	 * @return the moduleLast
	 */
	public int getModuleLast() {
		return moduleLast;
	}

	/**
	 * @param moduleLast the moduleLast to set
	 */
	public void setModuleLast(int moduleLast) {
		this.moduleLast = moduleLast;
	}
}
