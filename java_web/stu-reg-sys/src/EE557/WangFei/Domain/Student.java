package EE557.WangFei.Domain;

import java.sql.Date;
import java.sql.Timestamp;

public class Student {
	private int studentID;
	private int programmeID;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private char sex;
	private String email;
	private char isPaid;
	private String country;
	private Date birthday;
	private Timestamp registeTime;
	
	public Student(){
		
	}
	
	public Student(int studentID, String firstName,String lastName,
				 String password, char sex, String email,int programmeID, 
				 char isPaid,String country,Date birthday,Timestamp registeTime,
				 String username){
		this.studentID = studentID;
		this.programmeID = programmeID;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.sex = sex;
		this.email = email;
		this.isPaid = isPaid;
		this.country = country;
		this.birthday = birthday;
		this.registeTime = registeTime;
	}
	/**
	 * @return the studentID
	 */
	public int getStudentID() {
		return studentID;
	}
	/**
	 * @param studentID the studentID to set
	 */
	public void setStudentID(int studentID) {
		this.studentID = studentID;
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
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the sex
	 */
	public char getSex() {
		return sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(char sex) {
		this.sex = sex;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the isPaid
	 */
	public char getIsPaid() {
		return isPaid;
	}
	/**
	 * @param isPaid the isPaid to set
	 */
	public void setIsPaid(char isPaid) {
		this.isPaid = isPaid;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}
	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	/**
	 * @return the registeTime
	 */
	public Timestamp getRegisteTime() {
		return registeTime;
	}
	/**
	 * @param registeTime the registeTime to set
	 */
	public void setRegisteTime(Timestamp registeTime) {
		this.registeTime = registeTime;
	}	
}
