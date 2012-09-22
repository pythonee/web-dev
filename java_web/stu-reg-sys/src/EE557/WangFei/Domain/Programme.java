package EE557.WangFei.Domain;

public class Programme {

	private int programmeID;
	private String programmeTitle;
	private String programmeDESC;
	private int cost;
	
	public Programme(){
		
	}
	
	public Programme(int programmeID, String programmeTitle,
			String programmeDESC, int cost) {
		super();
		this.programmeID = programmeID;
		this.programmeTitle = programmeTitle;
		this.programmeDESC = programmeDESC;
		this.cost = cost;
	}

	/**
	 * @return the programmeID
	 */
	public int getProgrammeID() {
		return programmeID;
	}

	/**
	 * @param programmeID the programmeID to set
	 */
	public void setProgrammeID(int programmeID) {
		this.programmeID = programmeID;
	}

	/**
	 * @return the programmeTitle
	 */
	public String getProgrammeTitle() {
		return programmeTitle;
	}

	/**
	 * @param programmeTitle the programmeTitle to set
	 */
	public void setProgrammeTitle(String programmeTitle) {
		this.programmeTitle = programmeTitle;
	}

	/**
	 * @return the programmeDESC
	 */
	public String getProgrammeDESC() {
		return programmeDESC;
	}

	/**
	 * @param programmeDESC the programmeDESC to set
	 */
	public void setProgrammeDESC(String programmeDESC) {
		this.programmeDESC = programmeDESC;
	}

	/**
	 * @return the cost
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}	
}
