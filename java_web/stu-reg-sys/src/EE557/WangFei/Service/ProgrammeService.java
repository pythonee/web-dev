package EE557.WangFei.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sun.xml.internal.bind.v2.model.core.MaybeElement;

import EE557.WangFei.DAO.ModuleDAO;
import EE557.WangFei.DAO.ProgrammeDAO;
import EE557.WangFei.DAO.StudentDAO;
import EE557.WangFei.DAO.StudentModuleAssociateDAO;
import EE557.WangFei.DAO.TeacherDAO;
import EE557.WangFei.Domain.Module;
import EE557.WangFei.Domain.Programme;
import EE557.WangFei.Domain.Student;
import EE557.WangFei.Domain.Teacher;
import EE557.WangFei.Domain.StudentModuleAssociate;

public class ProgrammeService extends HttpServlet {
	
	private final static Logger log = Logger.getLogger(ProgrammeService.class);

	private ProgrammeDAO programmeDAO;
	private TeacherDAO teacherDAO;
	private StudentDAO studentDAO;
	private ModuleDAO moduleDAO;
	private StudentModuleAssociateDAO studentModuleAssociateDAO;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		if (req.getParameter("action").equals("details")) {
			
			int id = Integer.valueOf(req.getParameter("id"));
			
			log.debug(id);
			
			Map<Module,Teacher> moduleOfProgramme = new HashMap<Module, Teacher>(); 
			
			
			Programme programme = programmeDAO.findByID(id);
			
			List<Module> moduleList = programmeDAO.findAllModule(id);
			
			for (Module module : moduleList) {
				int teacherID = module.getModuleTeacher();
				Teacher teacher = teacherDAO.findByID(teacherID);
				moduleOfProgramme.put(module, teacher);
				log.debug(teacher.getFirstName());
			}
			
			req.setAttribute("programme", programme);
			req.setAttribute("moduleOfProgramme", moduleOfProgramme);
			
			RequestDispatcher rd = req.getRequestDispatcher("programmeDetails.jsp");
			rd.forward(req, resp);
						
		} 
		
		else if (req.getParameter("action").equals("getAllProgramme")) {
			List<Programme> programmeList = programmeDAO.findAll();
			if (programmeList.size() < 1)
			{
				req.setAttribute("InitError", true);
				
			}else {
				// remove the default programme
				Iterator<Programme> it = programmeList.iterator();
				
				while (it.hasNext()) {
					if (it.next().getProgrammeTitle().equals("default")) {
						it.remove();
					}
				}
				
				int count = programmeList.size();
				req.setAttribute("programmeList", programmeList);
				req.setAttribute("allProgrmmeNum", count);
			}

		}
		
		else if(req.getParameter("action").equals("add")){
			
			List<Programme> programmeList = programmeDAO.findAll();
			List<String> programmeTitleList = new ArrayList<String>();
			for (Programme programme : programmeList) {
				programmeTitleList.add(programme.getProgrammeTitle());
			}
			
			String programmeTitle = req.getParameter("programme_title");
			String programmeDesc = req.getParameter("programme_desc");
			int programmeCost = Integer.valueOf(req.getParameter("programme_cost"));
			
			if (programmeTitleList.contains(programmeTitle)) {
				req.setAttribute("messasge", " there have been a same programme... ");
				RequestDispatcher rd = req.getRequestDispatcher("admin.jsp");
				rd.forward(req, resp);
				return;
			}
			else {
				Programme programme = new Programme();
				programme.setCost(programmeCost);
				programme.setProgrammeDESC(programmeDesc);
				programme.setProgrammeTitle(programmeTitle);
				
				programmeDAO.insert(programme);
				
				req.setAttribute("messasge", " add programme success... ");
				RequestDispatcher rd = req.getRequestDispatcher("admin.jsp");
				rd.forward(req, resp);
				return;
			}
		}
		
		
		else if (req.getParameter("action").equals("pay_programme")){

			if(req.getSession().getAttribute("uid") == null){
				
				req.setAttribute("authorityError", true);
				req.setAttribute("message", "You must login first...");
				RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
				rd.forward(req, resp);
				return;
				
			}else {
				int uid = (Integer)req.getSession().getAttribute("uid");
				if (req.getParameter("select") == null) {
					
					req.setAttribute("pay_programme_error", true);
					req.setAttribute("message", "you have not choose a programme..");
					RequestDispatcher rd = req.getRequestDispatcher("ucenter.jsp");
					rd.forward(req, resp);
					return;
					
				}else {
					int pid = Integer.valueOf(req.getParameter("select"));
					studentDAO.update(uid, StudentDAO.STUDENT_PROGRAMME_ID_COLUMN, pid);
					studentDAO.update(uid, StudentDAO.STUDENT_ISPAID_COLUMN, "y");
					studentModuleAssociateDAO.delete(uid);
					
					RequestDispatcher rd = req.getRequestDispatcher("ucenter.jsp");
					rd.forward(req, resp);
					return;
				}
			}
		}
		
		else if(req.getParameter("action").equals("getMyProgramme")) {
			int uid = (Integer)req.getSession().getAttribute("uid");
			Student student = studentDAO.findByID(uid);
			Programme programme = null;
			if (Character.toLowerCase(student.getIsPaid()) == 'y') {
				programme = programmeDAO.findByID(student.getProgrammeID());
				if (programme != null && programme.getProgrammeID() != 0) {
					req.setAttribute("programme", programme);
					req.setAttribute("isPaid", true);
					// get all available modules
					List<Module> allAvailableModules = programmeDAO.findAllModule(programme.getProgrammeID());
					
					// get have select modules
					List<StudentModuleAssociate> smas = studentModuleAssociateDAO.findByID(uid);
					List<Module> myModules =  new ArrayList<Module>();
					
					for (StudentModuleAssociate sma : smas) {
						Module module = moduleDAO.findByID(sma.getModule_id());
						myModules.add(module);
					}
					
					Map<Module,Boolean> allAvailableModulesMap = new HashMap<Module, Boolean>();
					
					for (Module module : allAvailableModules) {
						allAvailableModulesMap.put(module, true);
					}
					
					Iterator it = allAvailableModulesMap.entrySet().iterator();
					for (Module module : myModules) {
						while (it.hasNext()) {
							Map.Entry<Module, Boolean> entry = (Map.Entry<Module, Boolean>)it.next();
							if (entry.getKey().getModuleTitle().equals(module.getModuleTitle())) {
								entry.setValue(false);
							}
						}
					}
					
 					
					req.setAttribute("allAvailableModulesMap", allAvailableModulesMap);
					req.setAttribute("allAvailableModulesSize", allAvailableModulesMap.size());
				}
			}
		}
	}

	@Override
	public void init() throws ServletException {

		programmeDAO = new ProgrammeDAO();
		teacherDAO  = new TeacherDAO();
		studentDAO = new StudentDAO();
		studentModuleAssociateDAO = new StudentModuleAssociateDAO();
		moduleDAO = new ModuleDAO();
	}
	
	

	
	
}
