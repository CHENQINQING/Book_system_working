/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hasnaz
 */
public class DatabaseConnection {
    
    Connection c;
    Statement st;
    ResultSet rs;
    String userName;
    String password;
    // String URL = "jdbc:mysql://127.0.0.1:3306/YOUR_DATABASE_NAME?user=YOUR_USER_NAME&password=YOUR_PASSWORD";
    String url = "jdbc:mysql://127.0.0.1:3306/world?user=root&password=root";

    //constructor will manage stablishing a connection to the database
    public DatabaseConnection()  {
        
        try{
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        c=(Connection) DriverManager.getConnection(url);
        st=(Statement) c.createStatement();
        }catch(Exception ex)
        {
            System.out.println("Problem with stablishing a connection to database");
        }
         
    }
        
    //A method thet search for a city name in the database
    public void executeSql(String sql) {
        try
        {
            st.execute(sql);
        } catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    

    public void printUserInfo(ResultSet result) {
        try
        {
            while(result.next()) {
                System.out.println("userNname:" + result.getString(1) 
                        + ", password:" + result.getString(2));
            }
        } catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public ResultSet query(String sql) {

        try
        {
            rs = st.executeQuery(sql);
        } catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return rs;
    }    
    
    
    
}
