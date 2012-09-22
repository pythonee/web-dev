/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.Log;

/**
 *
 * @author pythonee
 */
public class LogDAO {

    public ArrayList<Log> getALl() {
        ArrayList<Log> all_Log = new ArrayList<Log>();
        Connection con = ConnectManager.getConnection();

        if (ConnectManager.checkConnect(con)) {
            try {
                String sql = "select user.username,account.title,log.operator,log.count,log.timestamp "
                           + "from user,account,log "
                           + "where user.userID=log.userID and account.accountID=log.accountID "
                           + "order by log.timestamp desc";
                PreparedStatement stmt = con.prepareStatement(sql);

                ResultSet result = stmt.executeQuery();

                while (result.next()) {
                    Log log = new Log();
                    log.setUsername(result.getString(1));
                    log.setAccountTitle(result.getString(2));
                    log.setOperator(result.getInt(3));
                    log.setCount(result.getDouble(4));
                    log.setTimestamp(result.getTimestamp(5));

                    all_Log.add(log);
                }

            } catch (SQLException ex) {
                Logger.getLogger(LogDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return all_Log;
    }

    public ArrayList<Log> getUserLog(int userID) {
        ArrayList<Log> userLog = new ArrayList<Log>();
        Connection con = ConnectManager.getConnection();

        if (ConnectManager.checkConnect(con)) {
            try {
                String sql = "select user.username,account.title,log.operator,log.count,log.timestamp "
                           + "from user,account,log "
                           + "where log.userID=? and user.userID=? and account.accountID=log.accountID "
                           + "order by log.timestamp desc";
                PreparedStatement stmt = con.prepareStatement(sql);

                stmt.setInt(1, userID);
                stmt.setInt(2, userID);

                ResultSet result = stmt.executeQuery();

                while (result.next()) {
                    Log log = new Log();
                    log.setUsername(result.getString(1));
                    log.setAccountTitle(result.getString(2));
                    log.setOperator(result.getInt(3));
                    log.setCount(result.getDouble(4));
                    log.setTimestamp(result.getTimestamp(5));

                    userLog.add(log);
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(LogDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        return userLog;
    }

    public void insertLog(int userID,int accountID,int operator,double howmuch) {
        Connection con = ConnectManager.getConnection();

        if (ConnectManager.checkConnect(con)) {
            try {
                String sql = "insert into log (userID,accountID,operator,count) values (?,?,?,?)";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, userID);
                stmt.setInt(2, accountID);
                stmt.setInt(3, operator);
                stmt.setDouble(4, howmuch);

                stmt.executeUpdate();
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(LogDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
