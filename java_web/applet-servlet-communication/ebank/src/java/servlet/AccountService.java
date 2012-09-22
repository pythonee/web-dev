/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlet;

import dao.AccountDAO;
import dao.LogDAO;
import dao.UserDAO;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pojo.Account;
import pojo.Log;
import pojo.User;

/**
 *
 * @author pythonee
 */
public class AccountService extends HttpServlet {

    AccountDAO accountDAO = null;
    UserDAO userDAO = null;
    LogDAO logDAO = null;
    final static Logger log = Logger.getLogger("");

    @Override
    public void init() throws ServletException {
        accountDAO = new AccountDAO();
        userDAO = new UserDAO();
        logDAO = new LogDAO();
    }



    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("application/x-java-serialized-object");
        try {
            InputStream is = req.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            HashMap<String, String> param = (HashMap<String, String>) ois.readObject();
            ois.close();
            log.info("read the parameter from client");

            log.info(param.get("action"));


            OutputStream os = resp.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);

            if (param.get("action").equals("getUserAccout")) {
                String username = (String) req.getSession().getAttribute("username");
                User user = userDAO.getByUserName(username);
                int userID = user.getUserID();

                ArrayList<String> result = accountDAO.getUserAccountTitleList(userID);

                oos.writeObject(result);
                oos.flush();
                oos.close();

                
                log.info("get all user account");
            }


            
            else if(param.get("action").equals("fetchAccountDetail")){
                
                String username = (String) req.getSession().getAttribute("username");
                User user = userDAO.getByUserName(username);

                int userID = user.getUserID();

                String accountTitle = param.get("accountTitle");

                Account account = accountDAO.getByIDandTitle(userID, accountTitle);
                

                HashMap<String,String> result = new HashMap<String, String>();

                result.put("accountTitle", account.getTitle());
                result.put("accountType", account.getType() + "");
                result.put("accountBalance", account.getBalance() + "");
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String startDay = df.format(account.getTimestamp());
                result.put("startDate",  startDay);
                result.put("cardNumber", account.getCardNumber()+"");

                oos.writeObject(result);
                oos.flush();
                oos.close();
                
                log.info("fetch account info");
            }



            else if(param.get("action").equals("addAccount")){
                String username = (String) req.getSession().getAttribute("username");
                User user = userDAO.getByUserName(username);

                int cardNumber = Integer.parseInt(param.get("cardNumber"));

                ArrayList<String> accountTitleList = accountDAO.getUserAccountTitleList(user.getUserID());

                ArrayList<Integer> cardNumberList = accountDAO.getUserCardNumberList(user.getUserID());

                if (accountTitleList.contains(param.get("accountTitle"))) {
                    oos.writeObject(1);
                    oos.flush();
                    oos.close();
                }
                else if (cardNumberList.contains(cardNumber)){
                    oos.writeObject(2);
                    oos.flush();
                    oos.close();
                }
                else {
                    Account account = new Account();
                    account.setBalance(0);
                    account.setCardNumber(cardNumber);
                    account.setPin(MD5_Encrypt.encrypt(cardNumber+param.get("pinCode")));
                    account.setTitle(param.get("accountTitle"));
                    account.setType(Integer.parseInt(param.get("cardType")));

                    account.setUserID(user.getUserID());

                    accountDAO.addAccount(account);

                    oos.writeObject(0);
                    oos.flush();
                    oos.close();
                }
            }


            else if(param.get("action").equals("depositORwithdraw")){
                log.info("deposit or withdraw");
                int operator = Integer.parseInt(param.get("operator"));
                String accountTitle = param.get("accountTitle");
                double howmuch = Double.parseDouble(param.get("howmuch"));

                String username = (String)req.getSession().getAttribute("username");
                User user = userDAO.getByUserName(username);
                int userID = user.getUserID();

                Account account = accountDAO.getByIDandTitle(userID, accountTitle);
                
                accountDAO.updateBalance(userID, accountTitle, howmuch, operator);

                Log history = new Log();

                history.setUsername(username);
                history.setAccountTitle(accountTitle);
                history.setOperator(operator);
                history.setCount(howmuch);

                logDAO.insertLog(userID,account.getAccountID(),operator,howmuch);

                oos.writeObject(0);
                oos.flush();
                oos.close();
                
            }



            else if(param.get("action").equals("deleteAccount")){

                String accountTitle = param.get("accountTitle");
                String username = (String) req.getSession().getAttribute("username");
                User user = userDAO.getByUserName(username);
                int userID = user.getUserID();

                accountDAO.deleteAccount(userID, accountTitle);

                oos.writeObject(0);
                oos.flush();
                oos.close();

                
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
