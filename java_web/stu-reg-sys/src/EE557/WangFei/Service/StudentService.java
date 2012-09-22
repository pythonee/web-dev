package EE557.WangFei.Service;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;        
import java.util.regex.Pattern;   
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import EE557.WangFei.DAO.ProgrammeDAO;
import EE557.WangFei.DAO.StudentDAO;
import EE557.WangFei.Domain.Programme;
import EE557.WangFei.Domain.Student;

public class StudentService extends HttpServlet{
	
	private final static Logger log = Logger.getLogger(StudentService.class);
	private StudentDAO studentDAO;
	private ProgrammeDAO programmeDAO;
	private final static int MIN_LENGTH = 3;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		if (req.getParameter("action").equals("login")) {
			
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			
			List<Student> allStudent = studentDAO.findAll();
			
			Map<String, String> map = new HashMap<String, String>();
			
			for (Student student : allStudent) {
				
				map.put(student.getUsername(), student.getPassword());
				
			}
			
			Iterator it = map.entrySet().iterator();
			
			
			if (map.containsKey(username)) {
				if (map.get(username).equals(password)) {
					Student student = studentDAO.findByUsername(username);
					req.getSession().setAttribute("uid", student.getStudentID());
					
					RequestDispatcher rd = req.getRequestDispatcher("ucenter.jsp");
					rd.forward(req, resp);
					return;
				}
				else {
					req.setAttribute("loginError", true);
					req.setAttribute("message", "Password is not correct...");
					RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
					rd.forward(req, resp);
					return;
				}
			}else {
				req.setAttribute("loginError", true);
				req.setAttribute("message", "User doesn't exist...");
				RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
				rd.forward(req, resp);
				return;
			}		
		}
		
		else if (req.getParameter("action").equals("signup")) {
			
			List<Student> allStudent = studentDAO.findAll();
			
			List<String> usernameList  = new ArrayList<String>();
			
			for (Student student : allStudent) {
				usernameList.add(student.getUsername());
			}
			
			if (usernameList.contains(req.getParameter("username"))) {
				req.setAttribute("signupError", true);
				req.setAttribute("message", "the username is already taken,please try another...");
				RequestDispatcher rd = req.getRequestDispatcher("signup.jsp");
				rd.forward(req, resp);
				return;
			}else {
				
				Boolean validate = false;
				
				if (checkValidString(req.getParameter("firstName"), MIN_LENGTH) && checkValidString(req.getParameter("lastName"), MIN_LENGTH)) {
					validate = true;
				}else {
					validate = false;
					req.setAttribute("signupError", true);
					req.setAttribute("message", "the min length of first name or lastName is 3...");
					RequestDispatcher rd = req.getRequestDispatcher("signup.jsp");
					rd.forward(req, resp);
					return;
				}
				
				if (checkMail(req.getParameter("email"))) {
					validate = true;
				}else {
					validate = false;
					req.setAttribute("signupError", true);
					req.setAttribute("message", "the email address is not valid...");
					RequestDispatcher rd = req.getRequestDispatcher("signup.jsp");
					rd.forward(req, resp);
					return;
				}
				
				if (checkDate(req.getParameter("birthday"))) {
					validate = true;
				}else {
					validate = false;
					req.setAttribute("signupError", true);
					req.setAttribute("message", "the birthday is not valid...");
					RequestDispatcher rd = req.getRequestDispatcher("signup.jsp");
					rd.forward(req, resp);
					return;
				}
				
				if (validate) {
					Student student = new Student();
					
					student.setFirstName(req.getParameter("firstName"));
					student.setLastName(req.getParameter("lastName"));
					student.setCountry(req.getParameter("country"));
					student.setIsPaid('n');
					student.setEmail(req.getParameter("email"));
					
					List<Programme> allProgramme = programmeDAO.findAll();
					Programme programme = allProgramme.get(0);
					
					student.setProgrammeID(programme.getProgrammeID());
					student.setPassword(req.getParameter("password"));
					student.setSex(req.getParameter("sex").charAt(0));
					student.setUsername(req.getParameter("username"));
					
					String birthday = req.getParameter("birthday");
					
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					
					try
					{
						Date date = new java.sql.Date((dateFormat.parse(birthday)).getTime());
						
						student.setBirthday(date);
					} catch (Exception e)
					{
						e.printStackTrace();
					}
					
					
					studentDAO.insert(student);
					
					Student stu = studentDAO.findByUsername(req.getParameter("username"));
					req.getSession().setAttribute("uid", stu.getStudentID());
					RequestDispatcher rd = req.getRequestDispatcher("ucenter.jsp");
					rd.forward(req, resp);
					return;
				}			
			}
		}
		
		else if (req.getParameter("action").equals("logout")) {
			req.getSession().removeAttribute("uid");
			log.debug(req.getSession().getAttribute("uid"));
			RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
			rd.forward(req, resp);
			log.debug("logout");
			return;
		}			
		else if (req.getParameter("action").equals("profile")) {
			int uid = (Integer)req.getSession().getAttribute("uid");
			Student student = studentDAO.findByID(uid);
			if (Character.toLowerCase(student.getSex()) == 'm') {
				req.setAttribute("sex", "Man");
			}else {
				req.setAttribute("sex", "Woman");
			}
			if (Character.toLowerCase(student.getIsPaid()) == 'y') {
				req.setAttribute("isPaid", true);
			}else {
				req.setAttribute("isPaid", false);
			}
			
			req.setAttribute("student", student);
		}
		
		else if(req.getParameter("action").equals("getAllStudent")){
			List<Student> students = studentDAO.findAll();
			int size = students.size();
			req.setAttribute("allStudentSize", size);
			req.setAttribute("students", students);
		}
	}


	@Override
	public void init() throws ServletException {
		studentDAO = new StudentDAO();
		programmeDAO = new ProgrammeDAO();
	}
	
	public boolean checkMail(String email){
		return email.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
	}
	
	public boolean checkDate(String date){
		String eL= "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";        
        Pattern p = Pattern.compile(eL);         
        Matcher m = p.matcher(date);         
        
        return m.matches();
	}
	// check username and password and other string type parameter
	public boolean checkValidString(String str,int length){
		return str.length() >= length;
	}
}
