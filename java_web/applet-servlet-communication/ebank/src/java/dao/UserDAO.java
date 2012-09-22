/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.User;

/**
 *
 * @author pythonee
 */
public class UserDAO {
    final static Logger log = Logger.getLogger("UserDAO");
    
    public void addUser(User user){
        Connection con = ConnectManager.getConnection();

        if (ConnectManager.checkConnect(con)) {

            try {
                String sql = "Insert into user (username,passwd,email,firstname,lastname,birthday,sex) values (?,?,?,?,?,?,?)";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, user.getUsername());
                stmt.setString(2, user.getPassword());
                stmt.setString(3, user.getEmail());
                stmt.setString(4, user.getFirstName());
                stmt.setString(5, user.getLastName());
                stmt.setDate(6, new java.sql.Date(user.getBirthday().getTime()));
                stmt.setInt(7, user.getSex());
                stmt.executeUpdate();

                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }


    }

    public ArrayList<String> getALL(){
        ArrayList<String> allUserName = new ArrayList<String>();
        Connection con = ConnectManager.getConnection();

        if (ConnectManager.checkConnect(con)) {
            try {
                Statement stmt = con.createStatement();

                String sql = "Select username from user";

                ResultSet result = stmt.executeQuery(sql);

                while (result.next()) {
                    allUserName.add(result.getString("username"));
                }

                stmt.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return  allUserName;
     }

    public User getByUserName(String username){
        User user = null;
        Connection con = ConnectManager.getConnection();

        if (ConnectManager.checkConnect(con)) {
            try {
                String sql = "Select * from user where username=?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, username);

                ResultSet result = stmt.executeQuery();
                if (result.next()) {
                    user = new User();
                    user.setUserID(result.getInt(1));
                    user.setUsername(result.getString(2));
                    user.setPassword(result.getString(3));
                    user.setEmail(result.getString(4));
                    user.setFirstName(result.getString(5));
                    user.setLastName(result.getString(6));
                    user.setBirthday(new Date(result.getDate(7).getTime()));
                    user.setSex(result.getInt(8));
                }

                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return user;
    }

    public void updatePassword(String newPassword,int userID){
        Connection con = ConnectManager.getConnection();
        
        if (ConnectManager.checkConnect(con)){
            try {
                String sql = "update user set passwd=? where userID=?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, newPassword);
                stmt.setInt(2, userID);

                stmt.executeUpdate();
                stmt.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

    public void updateUser(User user){
        Connection con = ConnectManager.getConnection();

        if (ConnectManager.checkConnect(con)) {
            try {
                String sql = "update user set firstname=?,lastname=?,birthday=?,sex=? where userID=?";
                PreparedStatement stmt = con.prepareStatement(sql);

                stmt.setString(1, user.getFirstName());
                stmt.setString(2, user.getLastName());
                stmt.setDate(3, new java.sql.Date(user.getBirthday().getTime()));
                stmt.setInt(4, user.getSex());
                stmt.setInt(5, user.getUserID());

                stmt.executeUpdate();
                stmt.close();

            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
