package com.rssninja.utils;

import java.sql.*;
import java.util.Properties;

public class Database {

    public static Database INSTANCE = new Database();
    private static String userName = "dev";
    private static String password = "secreto";
    private static String dbms = "mysql";
    private static String serverName = "localhost";
    private static String portNumber = "3306";

    private static final String insertWordSQL = "INSERT INTO word VALUES (?)";
    private Database(){        
    }

    public static synchronized Connection getConnection(){
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", userName);
        connectionProps.put("password", password);
        try{
            conn = DriverManager.
                getConnection("jdbc:" + dbms + "://" + serverName +
                              ":" + portNumber + "/", connectionProps);
        }catch(SQLException e){
            System.out.println("Error while connecting to database");
        }
        System.out.println("Connected to database");        
        return conn;
    }

    public static void insertWord(String word){
        PreparedStatement st = null;
        Connection c = null;
        try{
            c= getConnection();
            st = c.prepareStatement(insertWordSQL);
            st.setString(1, word);
            st.executeUpdate();

        }catch(SQLException e){
            //
        }finally{
            try{
            st.close();
            }catch(SQLException e){
              //
            }finally{
                try{
                c.close();
                }catch(SQLException e){
                    //
                }
            }
        }
    }

}
