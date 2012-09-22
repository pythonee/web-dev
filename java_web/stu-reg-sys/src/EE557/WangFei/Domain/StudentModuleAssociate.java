package EE557.WangFei.Domain;

public class StudentModuleAssociate {
	private int module_id;
	private int stu_id;
	
	
	public StudentModuleAssociate(){
		
	}
	
	public StudentModuleAssociate(int module_id, int stu_id) {
		super();
		this.module_id = module_id;
		this.stu_id = stu_id;
	}
	/**
	 * @return the module_id
	 */
	public int getModule_id() {
		return module_id;
	}
	/**
	 * @param module_id the module_id to set
	 */
	public void setModule_id(int module_id) {
		this.module_id = module_id;
	}
	/**
	 * @return the stu_id
	 */
	public int getStu_id() {
		return stu_id;
	}
	/**
	 * @param stu_id the stu_id to set
	 */
	public void setStu_id(int stu_id) {
		this.stu_id = stu_id;
	}
	
	
}
