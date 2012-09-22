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
import pojo.Account;

/**
 *
 * @author pythonee
 */
public class AccountDAO {

    public Account getByIDandTitle(int userID,String accountTitle){
        Account account = new Account();

        Connection con = ConnectManager.getConnection();

        if (ConnectManager.checkConnect(con)) {
            try {
                String sql = "SELECT * FROM account where userID=? and title=?";
                PreparedStatement stmt = con.prepareStatement(sql);

                stmt.setInt(1, userID);
                stmt.setString(2, accountTitle);

                ResultSet result = stmt.executeQuery();

                if (result.next()) {
                    account.setAccountID(result.getInt(1));
                    account.setUserID(result.getInt(2));
                    account.setBalance(result.getDouble(3));
                    account.setType(result.getInt(4));
                    account.setTimestamp(result.getTimestamp(5));
                    account.setTitle(result.getString(6));
                    account.setCardNumber(result.getInt(7));
                    account.setPin(result.getString(8));
                }

                stmt.close();

            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }    

        return account;
    }

    public Account getByID(int userID,int accountID){
        Account account = new Account();

        Connection con = ConnectManager.getConnection();

        if (ConnectManager.checkConnect(con)) {
            try {
                String sql = "SELECT * FROM account where userID=? and title=?";
                PreparedStatement stmt = con.prepareStatement(sql);

                stmt.setInt(1, userID);
                stmt.setInt(2, accountID);

                ResultSet result = stmt.executeQuery();

                if (result.next()) {
                    account.setAccountID(result.getInt(1));
                    account.setUserID(result.getInt(2));
                    account.setBalance(result.getDouble(3));
                    account.setType(result.getInt(4));
                    account.setTimestamp(result.getTimestamp(5));
                    account.setTitle(result.getString(6));
                    account.setCardNumber(result.getInt(7));
                    account.setPin(result.getString(8));
                }

                stmt.close();

            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return account;
    }

    public ArrayList<String> getUserAccountTitleList(int userID){
        ArrayList<String> accountTitleList = new ArrayList<String>();

        Connection con = ConnectManager.getConnection();

        if (ConnectManager.checkConnect(con)) {
            try {
                String sql = "SELECT title FROM account where userID=?";
                PreparedStatement stmt = con.prepareStatement(sql);

                stmt.setInt(1, userID);

                ResultSet result = stmt.executeQuery();

                while (result.next()) {
                    accountTitleList.add(result.getString(1));
                }

                stmt.close();

            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return accountTitleList;
    }

    public ArrayList<Integer> getUserCardNumberList(int userID){
        ArrayList<Integer> accountTitleList = new ArrayList<Integer>();

        Connection con = ConnectManager.getConnection();

        if (ConnectManager.checkConnect(con)) {
            try {
                String sql = "SELECT cardnumber FROM account where userID=?";
                PreparedStatement stmt = con.prepareStatement(sql);

                stmt.setInt(1, userID);

                ResultSet result = stmt.executeQuery();

                while (result.next()) {
                    accountTitleList.add(result.getInt(1));
                }

                stmt.close();

            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return accountTitleList;
    }


    public void addAccount(Account account) {
        Connection con = ConnectManager.getConnection();
        try {
            String sql = "Insert into account (userID,balance,type,title,cardnumber,pin) values (?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, account.getUserID());
            stmt.setDouble(2, account.getBalance());
            stmt.setInt(3, account.getType());
            stmt.setString(4, account.getTitle());
            stmt.setInt(5, account.getCardNumber());
            stmt.setString(6, account.getPin());

            stmt.executeUpdate();
            stmt.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }


    public void updateBalance(int userID,String accountTitle,double howmuch,int dORw){

        Connection con = ConnectManager.getConnection();

        if (ConnectManager.checkConnect(con)) {

            String sql = "";

            if (dORw == 0) {
                sql = "update account set balance=balance+? where userID=? and title=?";
            }
            else{
                sql = "update account set balance=balance-? where userID=? and title=?";
            }
            try {
                PreparedStatement stmt = con.prepareStatement(sql);

                stmt.setDouble(1, howmuch);
                stmt.setInt(2, userID);
                stmt.setString(3, accountTitle);

                stmt.executeUpdate();

                stmt.close();

            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void deleteAccount(int userID,String accountTitle){
        Connection con = ConnectManager.getConnection();

        if (ConnectManager.checkConnect(con)) {
            try {
                String sql = "delete from account where userID=? and title=?";
                PreparedStatement stmt = con.prepareStatement(sql);

                stmt.setInt(1, userID);
                stmt.setString(2, accountTitle);

                stmt.executeUpdate();

                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
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
