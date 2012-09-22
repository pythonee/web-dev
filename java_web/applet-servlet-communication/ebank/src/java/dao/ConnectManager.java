/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pythonee
 */
public class ConnectManager {
    final static Logger log = Logger.getLogger("ConnectManager");

    public static Connection getConnection() {
        InputStream is = ConnectManager.class.getResourceAsStream("../../../JDBC.properties");
        Properties property = new Properties();
    Connection con = null;
        try {
            property.load(is);
            String DRIVER = property.getProperty("DRIVER");
            String url = property.getProperty("url");
            String username = property.getProperty("username");
            String password = property.getProperty("password");

             Class.forName(DRIVER);

            con = DriverManager.getConnection(url, username, password);

            Logger.getLogger("ConnectManager").log(Level.INFO,"connecting to database");

        } catch (SQLException ex) {
            log.severe(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log.severe(ex.getMessage());
        } catch (IOException e) {
            log.severe(e.getMessage());
        }
        return con;
    }

    public static boolean checkConnect(Connection con) {
        if (con == null) {
            log.info("Cannot connect to database");
            log.info("Please check the username,password and connect url");
            return false;
        }
        return true;
    }
}
