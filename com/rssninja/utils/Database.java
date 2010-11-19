package com.rssninja.utils;

import java.sql.*;
import java.util.Properties;
import com.rssninja.models.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Database {

    public static Database INSTANCE = new Database();
    private  String userName = "root";
    private String password = "";
    private String dbms = "mysql";
    private String serverName = "localhost";
    private String portNumber = "3306";

    private final String insertWordSQL = "INSERT INTO word (value) VALUES (?)";
    private final String selectWordSQL = "SELECT * FROM word WHERE value = ?";
    private final String insertSemanticSQL = "INSERT INTO Semantic (word1,word2,relation_factor) VALUES (?,?,?)";
    private final String insertKeywordSQL = "INSERT INTO Keyword (value) values(?)";
    private final String insertLinkSQL = "INSERT INTO link (value,fecha) values(?,?)";
    private final String insertKnowledgeSQL = "INSERT INTO knowledge (link,keyword,servicio,relevancia) values(?,?,?,?)";
    private final String selectLinkSQL = "SELECT * FROM link WHERE value = ?";
    private final String selectKeywordSQL = "SELECT * FROM keyword WHERE value = ?";
    private final String selectLinkByIdSQL = "SELECT * FROM link WHERE id = ?";
    private final String selectKeywodByIdSQL = "SELECT * FROM link WHERE id = ?";
    private final String getKnowledgeByServiceSQL = "SELECT * FROM knowledge WHERE servicio = ?";
    private final String getKnowledgeByKeywordSQL = "SELECT * FROM knowledge WHERE keyword = ?";
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
            String url = "jdbc:mysql://Localhost/RSSNinja";
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

public Keyword insertKeyword(String value){
    PreparedStatement iK = null;
    Connection c = null;
     int autoID = -1;
     try {
            c = getConnection();
            iK = c.prepareStatement(insertKeywordSQL, Statement.RETURN_GENERATED_KEYS);
            iK.setString(1, value);
            iK.executeUpdate();
            ResultSet keys = iK.getGeneratedKeys();
            if(keys.next()){
                autoID = keys.getInt(1);
            }else{
                System.out.println("Can't insert the keyword");
            }
            keys.close();
            keys = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
             iK.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new Keyword(autoID, value);
    }
public Link insertLink(String value,String fecha){
    PreparedStatement iL = null;
    Connection c = null;
     int autoID = -1;
     try {
            c = getConnection();
            iL = c.prepareStatement(insertLinkSQL, Statement.RETURN_GENERATED_KEYS);
            iL.setString(1, value);
            iL.setString(2, fecha);
            iL.executeUpdate();
            ResultSet keys = iL.getGeneratedKeys();
            if(keys.next()){
                autoID = keys.getInt(1);
            }else{
                System.out.println("Can't insert the Link");
            }
            keys.close();
            keys = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
             iL.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new Link(autoID, value,fecha);
    }
public Knowledge insertKnowledge(Link link, Keyword keyword,String service, int relevance){
    PreparedStatement iK = null;
    Connection c = null;
     int autoID = -1;
     try {
            c = getConnection();
            iK = c.prepareStatement(insertKnowledgeSQL, Statement.RETURN_GENERATED_KEYS);
            iK.setInt(1, link.getId());
            iK.setInt(2, keyword.getId());
            iK.setString(3,service);
            iK.setInt(4,relevance);
            iK.executeUpdate();
            ResultSet keys = iK.getGeneratedKeys();
            if(keys.next()){
                autoID = keys.getInt(1);
            }else{
                System.out.println("Can't insert the knowledge");
            }
            keys.close();
            keys = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
             iK.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new Knowledge(autoID,link,keyword,service,relevance);
    }
public Link getLink(String value){
        Link l = null;
        PreparedStatement gL = null;
        Connection c = null;
        try{
            c= getConnection();
            gL = c.prepareStatement(selectLinkSQL);
            gL.setString(1, value);
            ResultSet rs = gL.executeQuery();
            if(rs.next()){
                l = new Link(rs.getInt(1), rs.getString(2),rs.getString(3));
            }
            rs.close();
            rs = null;
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
            gL.close();
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
        return l;
    }
public Keyword getKeyword(String value){
        Keyword k = null;
        PreparedStatement gK = null;
        Connection c = null;
        try{
            c= getConnection();
            gK = c.prepareStatement(selectKeywordSQL);
            gK.setString(1, value);
            ResultSet rs = gK.executeQuery();
            if(rs.next()){
                k = new Keyword(rs.getInt(1), rs.getString(2));
            }
            rs.close();
            rs = null;
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
            gK.close();
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
        return k;
    }
public Keyword getKeywordById(int id){
        Keyword k = null;
        PreparedStatement gK = null;
        Connection c = null;
        try{
            c= getConnection();
            gK = c.prepareStatement(selectKeywodByIdSQL);
            gK.setInt(1, id);
            ResultSet rs = gK.executeQuery();
            if(rs.next()){
                k = new Keyword(rs.getInt(1), rs.getString(2));
            }
            rs.close();
            rs = null;
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
            gK.close();
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
        return k;
    }
public Link getLinkById(int id){
        Link l = null;
        PreparedStatement gK = null;
        Connection c = null;
        try{
            c= getConnection();
            gK = c.prepareStatement(selectLinkByIdSQL);
            gK.setInt(1, id);
            ResultSet rs = gK.executeQuery();
            if(rs.next()){
                l = new Link(rs.getInt(1), rs.getString(2),rs.getString(3));
            }
            rs.close();
            rs = null;
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
            gK.close();
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
        return l;
    }
public Collection<Knowledge> getKnowledgeByService(String service){
        PreparedStatement gK = null;
        Connection c = null;
        ResultSet result = null;
        List<Knowledge> resultList = new ArrayList<Knowledge>();

        try {
            c = getConnection();
            gK = c.prepareStatement(getKnowledgeByServiceSQL);
            gK.setString(1, service);
            result = gK.executeQuery();
            while(result.next()){
                resultList.add(new Knowledge(result.getInt(1), 
                        getLinkById(result.getInt(2)),
                        getKeywordById(result.getInt(3)),
                        result.getString(4),
                        result.getInt(5)));
            }
            result.close();
            result = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultList;
}
public Collection<Knowledge> getKnowledgeByKeyword(String keyword){
        PreparedStatement gK = null;
        Connection c = null;
        ResultSet result = null;
        List<Knowledge> resultList = new ArrayList<Knowledge>();
        Keyword k = null;
        try {
            k= getKeyword(keyword);
            c = getConnection();
            gK = c.prepareStatement(getKnowledgeByKeywordSQL);
            gK.setInt(1, k.getId());
            result = gK.executeQuery();
            while(result.next()){
                resultList.add(new Knowledge(result.getInt(1),
                        getLinkById(result.getInt(2)),
                        getKeywordById(result.getInt(3)),
                        result.getString(4),
                        result.getInt(5)));
            }
            result.close();
            result = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultList;
}
}
