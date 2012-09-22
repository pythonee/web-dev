package EE557.WangFei.Service;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import EE557.WangFei.DAO.ModuleDAO;
import EE557.WangFei.DAO.ProgrammeDAO;
import EE557.WangFei.DAO.ProgrammeModuleAssociateDAO;
import EE557.WangFei.DAO.StudentModuleAssociateDAO;
import EE557.WangFei.DAO.TeacherDAO;
import EE557.WangFei.Domain.Module;
import EE557.WangFei.Domain.Programme;
import EE557.WangFei.Domain.ProgrammeModuleAssociate;
import EE557.WangFei.Domain.StudentModuleAssociate;
import EE557.WangFei.Domain.Teacher;


public class ModuleService extends HttpServlet {

	private final static Logger log = Logger.getLogger(ModuleService.class);
	private ModuleDAO moduleDAO;
	private TeacherDAO teacherDAO;
	private ProgrammeDAO programmeDAO;
	private ProgrammeModuleAssociateDAO programmeModuleAssociateDAO;
	private StudentModuleAssociateDAO studentModuleAssociateDAO;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		if (req.getParameter("action").equals("details")) {
			
			int mid = Integer.valueOf(req.getParameter("id"));
			Module module = moduleDAO.findByID(mid);
			
			int tid = module.getModuleTeacher();
			Teacher teacher = teacherDAO.findByID(tid);
			
			List<Programme> programmeList = moduleDAO.findAllProgramme(mid);
						
			req.setAttribute("module", module);
			req.setAttribute("teacher", teacher);
			req.setAttribute("programmeList", programmeList);
			
			RequestDispatcher rd = req.getRequestDispatcher("moduleDetails.jsp");
			
			rd.forward(req, resp);
			
		}
		
		else if (req.getParameter("action").equals("getAllModule")) {
			List<Module> moduleList = moduleDAO.findAll();
			int size = moduleList.size();
			req.setAttribute("moduleList", moduleList);
			req.setAttribute("allModuleNum", size);
		}	
		
		else if (req.getParameter("action").equals("add")) {
			List<Module> moduleList = moduleDAO.findAll();
			List<String> moduleTitleList = new ArrayList<String>();
			
			for(Module module : moduleList){
				moduleTitleList.add(module.getModuleTitle());
			}
			
			String moduleTitle = req.getParameter("module_title");
			String moduleDesc = req.getParameter("module_desc");
			int moduleTeacherID = Integer.valueOf(req.getParameter("module_teacher"));
			int moduleLast = Integer.valueOf(req.getParameter("module_last"));
			String dateStr  = req.getParameter("module_start");
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date start = null;
			
			try
			{
				Date date = new java.sql.Date((dateFormat.parse(dateStr)).getTime());
				start = date;
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			
			int programme_id = Integer.valueOf(req.getParameter("module_programme"));
			
			if (moduleTitleList.contains(moduleTitle)) {
				req.setAttribute("messasge", " there have been a same module... ");
				RequestDispatcher rd = req.getRequestDispatcher("admin.jsp");
				rd.forward(req, resp);
				return;
			}
			else {
				
				Module module = new Module();
				module.setModuleDesc(moduleDesc);
				module.setModuleTitle(moduleTitle);
				module.setModuleTeacher(moduleTeacherID);
				module.setModuleLast(moduleLast);
				module.setModuleStart(start);
				
				moduleDAO.insert(module);
				
				Module manytomany = moduleDAO.findByTitle(moduleTitle);
				ProgrammeModuleAssociate pma = new ProgrammeModuleAssociate();
				pma.setModule_id(manytomany.getModuleID());
				pma.setProgramme_id(programme_id);
				programmeModuleAssociateDAO.insert(pma);
				req.setAttribute("messasge", " add module success... ");
				RequestDispatcher rd = req.getRequestDispatcher("admin.jsp");
				rd.forward(req, resp);
				return;
			}
		}
		
		else if(req.getParameter("action").equals("getMyAllModule")){
			int uid = (Integer)req.getSession().getAttribute("uid");
			List<StudentModuleAssociate> smas = studentModuleAssociateDAO.findByID(uid);
			List<Module> modules =  new ArrayList<Module>();
			
			for (StudentModuleAssociate sma : smas) {
				Module module = moduleDAO.findByID(sma.getModule_id());
				modules.add(module);
			}
			
			int size = modules.size();
			req.setAttribute("myModuleSize", size);
			req.setAttribute("myModules", modules);
		}
		
		else if(req.getParameter("action").equals("select_module")){
			
			String[] selected_modules = req.getParameterValues("select_module");
			if (selected_modules != null && selected_modules.length > 0) {
				
				int uid = (Integer)req.getSession().getAttribute("uid");
				for (String str : selected_modules) {
					int mid = Integer.valueOf(str);
					StudentModuleAssociate sma = new StudentModuleAssociate(mid,uid);
					studentModuleAssociateDAO.insert(sma);
				}
				
				List<StudentModuleAssociate> smas = studentModuleAssociateDAO.findByID(uid);
				List<Module> modules =  new ArrayList<Module>();
				
				for (StudentModuleAssociate sma : smas) {
					Module module = moduleDAO.findByID(sma.getModule_id());
					modules.add(module);
				}
				
				RequestDispatcher rd = req.getRequestDispatcher("ucenter.jsp");
				rd.forward(req, resp);
				return;
				
			}else {
				req.setAttribute("select_module_error", true);
				req.setAttribute("message", "you must select one module at least");
				RequestDispatcher rd = req.getRequestDispatcher("ucenter.jsp");
				rd.forward(req, resp);
				return;
			}
			
		}
	}


	@Override
	public void init() throws ServletException {
		moduleDAO = new ModuleDAO();
		teacherDAO = new TeacherDAO();
		programmeDAO = new ProgrammeDAO();
		programmeModuleAssociateDAO = new ProgrammeModuleAssociateDAO();
		studentModuleAssociateDAO = new StudentModuleAssociateDAO();
	}
}
