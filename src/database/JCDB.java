/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import classes.Book;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Xuantong
 */
public class JCDB {
    private final static String dbName = "book_system";
    private final static String user = "root" ;
    private final static String password = "root";
    private final static String connectionURL = "jdbc:mysql://localhost/" + dbName  + "?user=" + user + "&password=" + password + "&useSSL=false";
    
    
    //A method thet search for a city name in the database
    /*public static void searchBook(String bookName){
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement prepStmt = null;
        try {
            String statement = "SELECT * FROM book";
            conn = establishConnection();
            prepStmt = conn.prepareStatement(statement);
            rs=prepStmt.executeQuery();
            
            
            while(rs.next())
            {
                System.out.println(rs.getString("BOOK_NAME")+"is in database");
            }
        } catch (SQLException ex) {
            System.out.println("something went wrong when executing query");
        }
    }*/
    
    public static void managerAddNewBook(Book book) throws SQLException {
        try(Connection conn = establishConnection();){
            String statement = "INSERT INTO book (book_name, publisher_publisherName, author, price, introduction, type) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement prepStmt = (PreparedStatement) conn.prepareStatement(statement);
            
            // remove ++ from here, do it in last
            //prepStmt.setInt(1, id);
            prepStmt.setString(1, book.getName());
            prepStmt.setString(2, book.getPublisher());
            prepStmt.setString(3, book.getAuthor());
            prepStmt.setDouble(4, book.getPrice());
            prepStmt.setString(5, book.getIntro());
            prepStmt.setString(6, book.getType());
            prepStmt.executeUpdate();
            
            System.out.println("the data has been moved into database.");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static ResultSet ManagerSearchBook(String name){
        Connection conn = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        try {
            String statement = "SELECT * FROM Book WHERE book_name LIKE\"%" + name + "%\"";
            conn = establishConnection();
            prepStmt = conn.prepareStatement(statement);
            rs = prepStmt.executeQuery();
            System.out.println("Success");
            
        } catch (Exception e) {
            System.out.println("Cannot ritrive any book.");
        }
        return rs;
    }
    
    public static ResultSet ManagerRitriveBook(){
        Connection conn = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        try {
            String statement = "SELECT book_name, publisher, author, price, type FROM Book";
            conn = establishConnection();
            prepStmt = conn.prepareStatement(statement);
            rs = prepStmt.executeQuery();
            System.out.println("Success");
            
        } catch (Exception e) {
            System.out.println("Cannot ritrive any book.");
        }
        return rs;
    }
    
    public static void ManagerDeleteBook(String bookName){
        Connection conn = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        try {
            String statement = "DELETE FROM Book WHERE book_name = ?";
            conn = establishConnection();
            prepStmt = conn.prepareStatement(statement);
            prepStmt.setString(1, bookName);
            prepStmt.execute();
            System.out.println("Success removed");
            
        } catch (Exception e) {
            System.out.println("ERROR. Not delete.");
        }
    }
    
    public static Connection establishConnection() {
        Connection conn;

        //Get connection to database
        try {
            conn = DriverManager.getConnection(connectionURL);
            if(conn!=null) {
                System.out.println("connected to database successfully");
                return conn;
            }
        }
        catch (Exception ex) {
            System.out.println("Not connected to database");
        }
        return null;
    }
    
    public static boolean verifyAccount(String username, String accountType) throws SQLException{
        Connection conn = establishConnection();
        String query = "";
        if(accountType.equals("employee")) {
            query = "SELECT count(*) FROM employee WHERE username = ?";
        }
        else if(accountType.equals("member")) {
            query = "SELECT count(*) FROM member WHERE username = ?";
        }
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, username);
        ResultSet rs = statement.executeQuery();
        int count = 0;
        while(rs.next()) {
            count = rs.getInt(1);
        }
        if(count==0) {
            return false;
        }
        else {
            return true;
        }
    }
    
    public static int getId(String username, String pwd, String accountType) {
        Connection conn = establishConnection();
        String query = "";
        if(accountType.equals("employee")) {
            query = "SELECT employee_id FROM employee WHERE username = ? AND password = ?";
        }
        else if(accountType.equals("member")) {
            query = "SELECT customer_id FROM member WHERE username = ? AND password = ?";
        }
        int id = 0;
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, pwd);
            ResultSet rs = statement.executeQuery();
            System.out.println(statement);
            System.out.println(rs);
            while(rs.next()) {
                id = rs.getInt(1);
            }
        }
        catch(SQLException e) {
            
        }
        return id;
    }
}
    

