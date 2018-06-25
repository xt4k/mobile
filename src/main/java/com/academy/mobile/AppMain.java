package com.academy.mobile;

import com.academy.mobile.dao.ConnectionManager;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class AppMain {
    private static final String DEF_CFG_PATH="src/main/resources/cfg.properties";
    private static final String DEF_WEBAPP_PATH="/src/main/webapp";

    public static void main(String[] args) throws Exception {
        System.out.println("*** Demo of mobile ***");

        Properties prop = new Properties();

        String cfgPath = System.getProperty("cfg", DEF_CFG_PATH);
        String rootPath = System.getProperty("user.dir");
        String webApp = System.getProperty("webapp", DEF_WEBAPP_PATH);

        prop.load(new FileReader(new File(cfgPath)));
        int port = Integer.parseInt(prop.getProperty("jetty.port"));

        Server server = new Server(port);

        WebAppContext webapp = new WebAppContext(new File(rootPath + webApp).getCanonicalPath(), "");
        /****** JSP SUPPORT ADD TO JETTY ****/
        Configuration.ClassList classlist = org.eclipse.jetty.webapp.Configuration.ClassList
                .setServerDefault(server);
        classlist.addBefore(
                "org.eclipse.jetty.webapp.JettyWebXmlConfiguration",
                "org.eclipse.jetty.annotations.AnnotationConfiguration");
        webapp.setAttribute(
                "org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",
                ".*/[^/]*servlet-api-[^/]*\\.jar$|.*/javax.servlet.jsp.jstl-.*\\.jar$|.*/[^/]*taglibs.*\\.jar$");
        /*********************************/
        server.setHandler(webapp);

        server.start();
        server.join();

        try(ConnectionManager cm = new ConnectionManager(prop)) {
            Statement statement = cm.getConn().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM abonent");

            while (rs.next()) {
                int id = rs.getInt("abonent_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String gender = rs.getString("gender");
                int age = rs.getInt("age");
                System.out.println(String.format("%d, %s, %s, %s, %d",
                        id, firstName, lastName, gender, age));
            }

            rs.close();

//            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void valueOF(String str) {
    }

    private static void valueOF(char str) {
    }
}
