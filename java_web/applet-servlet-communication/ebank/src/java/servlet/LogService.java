/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlet;

import dao.LogDAO;
import dao.UserDAO;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pojo.Log;
import pojo.User;

/**
 *
 * @author pythonee
 */
public class LogService extends HttpServlet {
    
    LogDAO logDAO = null;
    UserDAO userDAO = null;
    final static Logger log = Logger.getLogger("");

    @Override
    public void init() throws ServletException {
        logDAO = new LogDAO();
        userDAO = new UserDAO();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/x-java-serialized-object");
        
        try {
            InputStream is = req.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            HashMap<String, String> param = (HashMap<String, String>) ois.readObject();
            ois.close();

            OutputStream os = resp.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);

            if (param.get("action").equals("fetchUserHistory")) {
                
                String username = (String) req.getSession().getAttribute("username");
                User user = userDAO.getByUserName(username);
                int userID = user.getUserID();
                ArrayList<Log> userHistory = logDAO.getUserLog(userID);
                log.log(Level.INFO, "{0}", userHistory.size());
                oos.writeObject(userHistory);
                oos.flush();
                oos.close();

                log.info("fetch user history");

            }

            else if (param.get("action").equals("fetchAllHistory")) {
                
                ArrayList<Log> userHistory = logDAO.getALl();

                log.log(Level.INFO, "{0}", userHistory.size());
                
                oos.writeObject(userHistory);
                oos.flush();
                oos.close();

                log.info("fetch all history");
            }


        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
