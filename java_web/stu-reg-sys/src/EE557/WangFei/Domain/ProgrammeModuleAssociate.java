package EE557.WangFei.Domain;

public class ProgrammeModuleAssociate {

	private int module_id;
	private int programme_id;
	
	public ProgrammeModuleAssociate(){
		
	}
	
	public ProgrammeModuleAssociate(int module_id, int programme_id) {
		super();
		this.module_id = module_id;
		this.programme_id = programme_id;
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
	 * @return the programme_id
	 */
	public int getProgramme_id() {
		return programme_id;
	}
	/**
	 * @param programme_id the programme_id to set
	 */
	public void setProgramme_id(int programme_id) {
		this.programme_id = programme_id;
	}	
}
