/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book_database;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    // String URL = "jdbc:mysql://127.0.0.1:3306/YOUR_DATABASE_NAME?user=YOUR_USER_NAME&password=YOUR_PASSWORD";
    String url = "jdbc:mysql://127.0.0.1:3306/mydb?user=root&password=root";

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
    public void searchBook(String bookName) 
    {
        try {
            rs=st.executeQuery("SELECT * FROM book");
            
            
            while(rs.next())
            {
                System.out.println(rs.getString("BOOK_NAME")+"is in database");
            }
        } catch (SQLException ex) {
            System.out.println("something went wrong when executing query");
        }
    }
    
    
    
}
