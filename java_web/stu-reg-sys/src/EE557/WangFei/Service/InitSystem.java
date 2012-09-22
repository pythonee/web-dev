package EE557.WangFei.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.net.aso.l;
import oracle.net.aso.s;

import EE557.WangFei.DAO.ModuleDAO;
import EE557.WangFei.DAO.ProgrammeDAO;
import EE557.WangFei.DAO.ProgrammeModuleAssociateDAO;
import EE557.WangFei.DAO.StudentDAO;
import EE557.WangFei.DAO.TeacherDAO;
import EE557.WangFei.Domain.Module;
import EE557.WangFei.Domain.Programme;
import EE557.WangFei.Domain.ProgrammeModuleAssociate;
import EE557.WangFei.Domain.Student;
import EE557.WangFei.Domain.Teacher;


public class InitSystem extends HttpServlet
{

	ProgrammeDAO programmeDAO;
	TeacherDAO teacherDAO;
	ModuleDAO moduleDAO;
	ProgrammeModuleAssociateDAO programmeModuleAssociateDAO;
	StudentDAO studentDAO;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		Programme programmeOne = new Programme(0,"default","default",0);
		Programme programmeTwo = new Programme(0,"WHU and DCU","WHU and DCU",80000);
		Programme programmeThree = new Programme(0,"PKU and DCU","WHU and DCU",80000);
		
		programmeDAO.insert(programmeOne);
		programmeDAO.insert(programmeTwo);
		programmeDAO.insert(programmeThree);
		
		Teacher teacherOne = new Teacher(0,"Hao","Gui");
		Teacher teacherTwo = new Teacher(0,"Li","Li");
		teacherDAO.insert(teacherOne);
		teacherDAO.insert(teacherTwo);
		
		List<Teacher> teachers = teacherDAO.findAll();
		List<Programme> programmes = programmeDAO.findAll();
		
		Module moduleOne = new Module();
		moduleOne.setModuleDesc("This is description");
		moduleOne.setModuleLast(18);
		java.util.Date date = new java.util.Date();
		moduleOne.setModuleStart(new java.sql.Date(date.getTime()));
		moduleOne.setModuleTeacher(teachers.get(0).getId());
		moduleOne.setModuleTitle("EE557");
		
		Module moduleTwo = new Module();
		moduleTwo.setModuleDesc("This is decription");
		moduleTwo.setModuleLast(18);
		moduleTwo.setModuleStart(new java.sql.Date(date.getTime()));
		moduleTwo.setModuleTeacher(teachers.get(1).getId());
		moduleTwo.setModuleTitle("EE548");
		
		
		moduleDAO.insert(moduleOne);
		moduleDAO.insert(moduleTwo);
		
		List<Module> modules = moduleDAO.findAll();
		
		ProgrammeModuleAssociate programmeModuleAssociateOne = new ProgrammeModuleAssociate();
		programmeModuleAssociateOne.setModule_id(modules.get(0).getModuleID());
		programmeModuleAssociateOne.setProgramme_id(programmes.get(0).getProgrammeID());
		
		ProgrammeModuleAssociate programmeModuleAssociateTwo = new ProgrammeModuleAssociate();
		programmeModuleAssociateOne.setModule_id(modules.get(1).getModuleID());
		programmeModuleAssociateOne.setProgramme_id(programmes.get(1).getProgrammeID());
		
		programmeModuleAssociateDAO.insert(programmeModuleAssociateOne);
		programmeModuleAssociateDAO.insert(programmeModuleAssociateTwo);
		
		Student student = new Student();
		student.setBirthday(new java.sql.Date(date.getTime()));
		student.setCountry("China");
		student.setEmail("pythonee@gmail.com");
		student.setFirstName("Wang");
		student.setLastName("Fei");
		student.setIsPaid('n');
		student.setPassword("onedefour");
		student.setProgrammeID(programmes.get(1).getProgrammeID());
		student.setRegisteTime(new Timestamp(date.getTime()));
		student.setSex('m');
		student.setUsername("pythonee");
		studentDAO.insert(student);
		
		if (req.getSession().getAttribute("uid") != null) {
			req.getSession().removeAttribute("uid");
		}
		
		req.setAttribute("InitSuccess", true);
		RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
		rd.forward(req, resp);
		return;
	}

	public void init() throws ServletException
	{
		programmeDAO = new ProgrammeDAO();
		teacherDAO = new TeacherDAO();
		programmeModuleAssociateDAO = new ProgrammeModuleAssociateDAO();
		studentDAO = new StudentDAO();
		moduleDAO = new ModuleDAO();
	}

}
