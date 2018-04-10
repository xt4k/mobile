package com.academy.mobile;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class AppMain {
    public static void main(String[] args) throws Exception {
        System.out.println("*** Demo of mobile ***");
        Properties prop = new Properties();
        prop.load(new FileReader(new File(System.getProperty("cfg", "src/main/resources/cfg.properties"))));
        Server server = new Server(Integer.parseInt(prop.getProperty("jetty.port")));
        String rootPath = AppMain.class.getClassLoader().getResource(".").toString();
        WebAppContext webapp = new WebAppContext(rootPath + "../../src/main/webapp", "");
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
}
