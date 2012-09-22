package EE557.WangFei.Service;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import EE557.WangFei.Domain.Module;
import EE557.WangFei.Domain.Teacher;

import org.apache.log4j.Logger;

import EE557.WangFei.DAO.TeacherDAO;

public class TeacherService extends HttpServlet {

	private final static Logger log = Logger.getLogger(TeacherService.class);
	private TeacherDAO teacherDAO;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		if (req.getParameter("action").equals("details")) {
			
			int id = Integer.valueOf(req.getParameter("id"));
			
			Teacher teacher = teacherDAO.findByID(id);
			
			req.setAttribute("teacher", teacher);
			
			List<Module> modules = teacherDAO.findModulesTheTeacherTeach(id);
			
			req.setAttribute("moduleList", modules);
			
			RequestDispatcher rd = req.getRequestDispatcher("teacherDetails.jsp");
			rd.forward(req, resp);
			return;
		}
		
		else if (req.getParameter("action").equals("getAllTeacher")){
			List<Teacher> teacherList = teacherDAO.findAll();
			int size = teacherList.size();
			req.setAttribute("teacherList", teacherList);
			req.setAttribute("allTeacherNum", size);
		}
		
		else if(req.getParameter("action").equals("add")){
			
			List<Teacher> teachers = teacherDAO.findAll();
			
			Map<String, String> map = new HashMap<String, String>();
			
			for (Teacher teacher : teachers) {
				map.put(teacher.getFirstName(), teacher.getLastName());
			}
			
			String firstName = req.getParameter("teacher_firstName");
			String lastName = req.getParameter("teacher_lastName");
			
			if (map.containsKey(firstName) && map.get(firstName).equals(lastName)) {
				req.setAttribute("messasge", " there have been a same teacher... ");
				RequestDispatcher rd = req.getRequestDispatcher("admin.jsp");
				rd.forward(req, resp);
				return;
			}
			else {
				Teacher teacher = new Teacher();
				teacher.setFirstName(firstName);
				teacher.setLastName(lastName);
				teacherDAO.insert(teacher);
				req.setAttribute("messasge", " add teacher success... ");
				RequestDispatcher rd = req.getRequestDispatcher("admin.jsp");
				rd.forward(req, resp);
				return;
			}
		}
		
	}


	@Override
	public void init() throws ServletException {
	
		teacherDAO = new TeacherDAO();
	}

}
