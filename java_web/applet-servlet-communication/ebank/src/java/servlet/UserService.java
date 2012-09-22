/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlet;

import dao.UserDAO;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pojo.Mail;

import pojo.User;

/**
 *
 * @author pythonee
 */
public class UserService extends HttpServlet {

    UserDAO userDAO = null;
    final static Logger log = Logger.getLogger("UserServlet");

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            resp.setContentType("application/x-java-serialized-object");

            InputStream is = req.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            HashMap<String, String> param = (HashMap<String, String>) ois.readObject();
            ois.close();


            log.info("read the parameter from client");
            log.info(param.get("action"));

            OutputStream os = resp.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);

            if (param.get("action").equals("signup")) {
                User user = new User();
                user.setUsername(param.get("username"));
                user.setPassword(MD5_Encrypt.encrypt(param.get("password")+param.get("username")));
                user.setSex(Integer.parseInt(param.get("sex"))-1);
                user.setEmail(param.get("email"));
                user.setFirstName(param.get("firstName"));
                user.setLastName(param.get("lastName"));

                int year = Integer.parseInt(param.get("year"))+1904;
                int month = Integer.parseInt(param.get("month"));
                int day = Integer.parseInt(param.get("day"));

                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date birthday = df.parse(year+"-"+month+"-"+day);
                user.setBirthday(birthday);
                
                if (userDAO.getALL().contains(param.get("username"))) {
                    oos.writeObject(1);
                    oos.flush();
                    oos.close();
                }
                else{
                    userDAO.addUser(user);
                    req.getSession().setAttribute("username", param.get("username"));
                    oos.writeObject(0);
                    oos.flush();
                    oos.close();
                    log.info("complete add user");
                }
            }
            


            else if(param.get("action").equals("signin")){

                User user = userDAO.getByUserName(param.get("username"));

                if (user == null) {
                    oos.writeObject(1);
                    oos.flush();
                    oos.close();
                    log.info("user not existed");
                } else if (MD5_Encrypt.encrypt(param.get("password")+param.get("username")).equals(user.getPassword())) {
                    req.getSession().setAttribute("username", param.get("username"));
                    oos.writeObject(0);
                    oos.flush();
                    oos.close();
                    log.info("login success");
                } else {
                    oos.writeObject(2);
                    oos.flush();
                    oos.close();
                    log.log(Level.INFO, "password is not correct{0}", param.get("password").toString());
                }
            }



            else if(param.get("action").equals("checkAvailable")){
                String username = (String)param.get("username");

                if (userDAO.getALL().contains(username)) {
                    oos.writeObject(1);
                    oos.flush();
                    oos.close();
                    log.info("username is not available");
                }
                else{
                    oos.writeObject(0);
                    oos.flush();
                    oos.close();
                    log.info("username is available");
                }

                log.info("check availability");
            }



            else if(param.get("action").equals("resetPassword")){

                Mail mail = new Mail();

                mail.setSubject("Recover Password");

                String username = param.get("username");
                User user = userDAO.getByUserName(username);

                if (user == null) {
                    oos.writeObject(1);
                    oos.flush();
                    oos.close();
                } else {
                    mail.setMail_to(user.getEmail());


                    char[] newPassword = new char[(int) (Math.random() * 7 + 8)];

                    for (int i = 0; i < newPassword.length; i++) {
                        newPassword[i] = (char) (Math.random() * 26 + 'a');
                    }

                    userDAO.updatePassword(MD5_Encrypt.encrypt(String.valueOf(newPassword) + username), user.getUserID());

                    mail.setMsg(String.valueOf(newPassword));
                    
                    try {
                        MailService.send(mail);
                        oos.writeObject(0);
                        oos.flush();
                        oos.close();
                    } catch (AddressException ex) {
                        oos.writeObject(2);
                        oos.flush();
                        oos.close();

                        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (MessagingException ex) {
                        oos.writeObject(2);
                        oos.flush();
                        oos.close();
                        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                log.info("recover password");
            }


            else if(param.get("action").equals("changePassword")){

                String username = (String) req.getSession().getAttribute("username");
                String currentPassword = param.get("curPassword");
                String newPassword = param.get("newPassword");

                User user = userDAO.getByUserName(username);

                if (!MD5_Encrypt.encrypt(currentPassword+username).equals(user.getPassword())) {

                    oos.writeObject(1);
                    oos.flush();
                    oos.close();

                }

                else{

                    newPassword = MD5_Encrypt.encrypt(newPassword+username);
                    userDAO.updatePassword(newPassword , user.getUserID());
                    oos.writeObject(0);
                    oos.flush();
                    oos.close();
                    
                }
            }

            

            else if (param.get("action").equals("fetchUserDetail")) {

                if (req.getSession().getAttribute("username") == null) {
                    oos.writeObject(null);
                    oos.flush();
                    oos.close();
                } else {
                    String username =(String)req.getSession().getAttribute("username");
                    User user = userDAO.getByUserName(username);

                    HashMap<String, String> result = new HashMap<String, String>();
                    result.put("username", user.getUsername());
                    result.put("firstName", user.getFirstName());
                    result.put("lastName", user.getLastName());
                    result.put("email", user.getEmail());
                    result.put("sex", user.getSex()+"");

                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    String birthday = df.format(user.getBirthday());
                    result.put("birthday", birthday);

                    
                    oos.writeObject(result);
                    oos.flush();
                    oos.close();
                }
                log.info("fetch user detail");
            }


            else if (param.get("action").equals("updateUser")) {
                String username = (String) req.getSession().getAttribute("username");
                User user = userDAO.getByUserName(username);
                user.setFirstName(param.get("firstName"));

                user.setLastName(param.get("lastName"));
                user.setEmail(param.get("email"));
                user.setSex(Integer.parseInt(param.get("sex"))-1);
                int year = Integer.parseInt(param.get("year")) + 1904;
                int month = Integer.parseInt(param.get("month"));
                int day = Integer.parseInt(param.get("day"));

                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date birthday = df.parse(year + "-" + month + "-" + day);
                user.setBirthday(birthday);

                userDAO.updateUser(user);

                oos.writeObject(0);
                oos.flush();
                oos.close();

                
            }



            else if(param.get("action").equals("signout")){
                if (req.getSession().getAttribute("username") == null) {
                    oos.writeObject(1);
                    oos.flush();
                    oos.close();

                    log.info("already logout");
                }
                else{
                    req.getSession().removeAttribute("username");
                    oos.writeObject(0);
                    oos.flush();
                    oos.close();

                    log.info("logout success");
                }
                
                log.info("user sign out");
            }
        } catch (ParseException ex) {
            log.severe(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log.severe(ex.getMessage());
        } catch (IOException ioe) {
            log.severe(ioe.getMessage());
        }
    }
}
