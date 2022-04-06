package services;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
//    public Connection getConnection(){
//        String databaseName = "projectoop";
//        String databaseUser = "root";
//        String databasePassword = "zaiphola1";
//        String url = "jdbc:mysql://localhost/" + databaseName;
//
//        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
//        } catch (Exception e){
//            e.printStackTrace();
//            e.getCause();
//        }
//        return databaseLink;
//    }
    public Connection getConnection() {
        String databaseName = "projectoop";
        String databaseUser = "root";
        String databasePassword = "zaiphola1";
        String url = "jdbc:mysql://localhost/" + databaseName;

        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, databaseUser, databasePassword);
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }

        return conn;
    }
}
