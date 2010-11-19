package com.rssninja.utils;

import java.sql.*;
import java.util.Properties;
import com.rssninja.models.*;

public class Database {

    public static Database INSTANCE = new Database();
    private  String userName = "dev";
    private String password = "secreto";
    private String dbms = "mysql";
    private String serverName = "localhost";
    private String portNumber = "3306";

    private final String insertWordSQL = "INSERT INTO word (value) VALUES (?)";
    private final String selectWordSQL = "SELECT * FROM word WHERE value = ?";
    private final String insertSemanticSQL = "INSERT INTO Semantic (word1,word2,relation_factor) VALUES (?,?,?)";

    private Database(){        
    }

    private synchronized Connection getConnection(){
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", userName);
        connectionProps.put("password", password);
        try{
            //String url = "jdbc:" + dbms + "://" + serverName + "/RSSNinja";
            //System.out.println(connStr);
            //conn = DriverManager.getConnection(connStr, connectionProps);
            String url = "jdbc:mysql://localhost/RSSNinja";
            //Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url,connectionProps);
            
        }catch(SQLException e){
            System.out.println("Error while connecting to database");
        //}catch(ClassNotFoundException e){
         //   System.out.println("Class not found");
        }catch(Exception e){
            System.out.println("Another Exception!!");
        }
        if(conn != null)
            System.out.println("Connected to database");
        
        return conn;
    }

    /*
     * Inserts a word object in DB and returns the auto generated ID for that word
     */
    public Word insertWord(String word){
        PreparedStatement st = null;
        Connection c = null;
        int autoID = -1;
        try{
            c= getConnection();
            st = c.prepareStatement(insertWordSQL,Statement.RETURN_GENERATED_KEYS);
            st.setString(1, word);
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                autoID = rs.getInt(1);
            }else {
                    throw new SQLException("Row not inserted");
            }            
            System.out.println("AutoID: "+autoID);
            rs.close();
            rs = null;
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
            st.close();
            }catch(SQLException e){
              e.printStackTrace();
            }finally{
                try{
                c.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
        if(autoID == -1)
            return null;
        
        return new Word(autoID,word);
    }
    
    public Word getWord(String value){
        Word w = null;
        PreparedStatement st = null;
        Connection c = null;        
        try{
            c= getConnection();
            st = c.prepareStatement(selectWordSQL);
            st.setString(1, value);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                w = new Word(rs.getInt(1), rs.getString(2));
            }
            rs.close();
            rs = null;
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
            st.close();
            }catch(SQLException e){
              e.printStackTrace();
            }finally{
                try{
                c.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return w;
    }

    public Semantic insertSemantic(Word word1, Word word2, float relation_factor){
        int autoID = -1;

        PreparedStatement st = null;
        Connection c = null;

        try{
            c= getConnection();
            st = c.prepareStatement(insertSemanticSQL, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, word1.getId());
            st.setInt(2, word2.getId());
            st.setFloat(3, relation_factor);
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                autoID = rs.getInt(1);
            }else {
                System.out.println("not inserted");
            }
            rs.close();
            rs = null;
        }catch(SQLException e){            
            e.printStackTrace();
        }finally{
            try{
            st.close();
            }catch(SQLException e){                
              e.printStackTrace();
            }finally{
                try{
                c.close();
                }catch(SQLException e){                    
                    e.printStackTrace();
                }
            }
        }
        
        if(autoID==-1)
            return null;
        return new Semantic(autoID,word1, word2, relation_factor);
    }


    
}
