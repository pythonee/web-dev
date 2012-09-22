/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package user;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Logger;

/**
 *
 * @author pythonee
 */
public class ConnectManager {

public static URLConnection getConnection(URL codeBase,String file)
        throws MalformedURLException, IOException{
        Logger log = Logger.getLogger("ConnectManager");
        URLConnection con = null;
        
        URL url = new URL(codeBase, file);
        con = url.openConnection();
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setUseCaches(false);
        con.setDefaultUseCaches(false);
        con.setRequestProperty("Content-Type", "application/x-java-serialized-object");
        log.info("connecting to servlet");
        return con;
    }
}
