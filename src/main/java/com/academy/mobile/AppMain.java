package com.academy.mobile;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class AppMain {
    public static void main(String[] args) {
        System.out.println("*** Demo of mobile ***");
        Properties properties=new Properties();
        try {
            String propertyFile = System.getProperty("configFile", "etc/jdbc.properties");
            properties.load(new FileReader(new File(propertyFile)));
            String host = (String)properties.get("host");
            String port = (String)properties.get("port");
            String db = (String)properties.get("db");
            String user = (String)properties.get("user");
            String password = (String)properties.get("password");

            System.out.println(host);
            System.out.println(port);
            System.out.println(db);
            System.out.println(user);
            System.out.println(password);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mobile?user=root&password=root&useSSL=false");

            Statement statement = conn.createStatement();
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

            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
