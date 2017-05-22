/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import classes.Book;
import classes.BookStorage;
import classes.Feedback;
import classes.LoginStorage;
import classes.Help;
import classes.Publisher;
import classes.User;
import classes.UserStorage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import javafx.collections.ObservableList;

/**
 *
 * @author Xuantong
 */
public class JCDB {
    private final static String dbName = "book_system";
    private final static String user = "root" ;
    private final static String password = "root";
    private final static String connectionURL = "jdbc:mysql://localhost/" + dbName  + "?user=" + user + "&password=" + password + "&useSSL=false";
    

    private Help help = new Help();
    
    //A method thet search for a city name in the database
    /*public static void searchBook(String bookName){
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement prepStmt = null;
        try {
            String SQL = "SELECT * FROM book";
            conn = establishConnection();
            prepStmt = conn.prepareStatement(SQL);
            rs=prepStmt.executeQuery();
            
            
            while(rs.next())
            {
                System.out.println(rs.getString("BOOK_NAME")+"is in database");
            }
        } catch (SQLException ex) {
            System.out.println("something went wrong when executing query");
        }
    }*/
    
    /**
    *
    * Create a default login account()
    */
    public void createDefaultEmployee(){
        try(Connection conn = establishConnection();){
            //String statement = "INSERT INTO book (book_name, publisher_Pub_id, author, price, introduction, type) VALUES (?,?,?,?,?,?,?)";
            String statement = "INSERT INTO user SET name=?, username=?, password=?, level=?, email=?";
            PreparedStatement prepStmt = (PreparedStatement) conn.prepareStatement(statement);
            
            prepStmt.setString(1, "John");
            
            prepStmt.setString(2, "John");
            
            prepStmt.setString(3, "admin1");
            
            prepStmt.setInt(4, 2);
            
            prepStmt.setString(5, "john@gmail.com");
            
            prepStmt.executeUpdate();
            
            System.out.println("default login account has been created and saved");
        }
        catch(Exception e){
            System.out.println("Default login account: "+e);
        }
    }
    
    public void managerAddNewBook(Book book) throws SQLException {
        try(Connection conn = establishConnection();){
            //String statement = "INSERT INTO book (book_name, publisher_Pub_id, author, price, introduction, type) VALUES (?,?,?,?,?,?,?)";
            String statement = "INSERT INTO book "
                    + "SET book_name=?, author=?, price=?, introduction=?, REPERTORY_SIZE=?, date=?, "
                    + "publisher_Pub_id=(SELECT Pub_id FROM publisher WHERE Pub_name = ?)";
            PreparedStatement prepStmt = (PreparedStatement) conn.prepareStatement(statement);
            
            prepStmt.setString(1, book.getName());
            prepStmt.setString(2, book.getAuthor());
            prepStmt.setDouble(3, book.getPrice());
            prepStmt.setString(4, book.getIntroduction());
            //prepStmt.setString(5, book.getType());
            prepStmt.setInt(5, book.getQuantity());
            prepStmt.setDate(6, help.toSqlDate(book.getDate()));
            prepStmt.setString(7, book.getPublisher());
            
            prepStmt.executeUpdate();
            
            addBook_has_type(book.getName(), book.getType());
            
            System.out.println("the data has been moved into database.");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void addBook_has_type(String bookName, String typeName){
        try(Connection conn = establishConnection();){
            //String statement = "INSERT INTO book (book_name, publisher_Pub_id, author, price, introduction, type) VALUES (?,?,?,?,?,?,?)";
            String statement = "INSERT INTO book_has_type "
                    + "SET book_book_id = (SELECT book_id FROM book WHERE book_name = ?), "
                    + "type_type_id = (SELECT type_id FROM type WHERE type_name = ?)";
            PreparedStatement prepStmt = (PreparedStatement) conn.prepareStatement(statement);
            prepStmt.setString(1, bookName);
            prepStmt.setString(2, typeName);
            
            prepStmt.executeUpdate();
            
            System.out.println("Book_has_type has been added");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public ResultSet searchingAllBook(String name){
        Connection conn = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        try {
            //String statement = "SELECT book_name, Pub_name, author, price, type, REPERTORY_SIZE, introduction FROM Book,publisher WHERE publisher_Pub_id = Pub_id AND book_name LIKE\"%" + name + "%\"";
            String statement = "SELECT book_name, Pub_name, author, price, type_name, REPERTORY_SIZE, introduction FROM publisher,type  "
                    + "INNER JOIN book_has_type ON book_has_type.type_type_id = type.Type_id "
                    + "INNER JOIN book ON book.book_id = book_has_type.book_book_id WHERE publisher_Pub_id = Pub_id AND book_name LIKE\"%" + name + "%\"";
            conn = establishConnection();
            prepStmt = conn.prepareStatement(statement);
            rs = prepStmt.executeQuery();
            System.out.println("Success");
            
        } catch (Exception e) {
            System.out.println("Cannot ritrive any book.");
        }
        return rs;
    }
    
    public ResultSet retrieveAllBook(){
        Connection conn = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        try {
            //String statement = "SELECT book_name, Pub_name, author, price, type_name, REPERTORY_SIZE, introduction FROM Book,publisher WHERE publisher_Pub_id = Pub_id";
            String statement = "SELECT book_name, Pub_name, author, price, type_name, REPERTORY_SIZE, introduction FROM publisher,type  "
                    + "INNER JOIN book_has_type ON book_has_type.type_type_id = type.Type_id "
                    + "INNER JOIN book ON book.book_id = book_has_type.book_book_id WHERE publisher_Pub_id = Pub_id";
            conn = establishConnection();
            prepStmt = conn.prepareStatement(statement);
            rs = prepStmt.executeQuery();
            System.out.println("Success");
            return rs;
        } catch (Exception e) {
            System.out.println("Cannot ritrive any book.");
            return null;
        }
    }
    
    public void ManagerDeleteBook(String bookName){
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
    
    public ResultSet customerSearchingBook(String name,String typeName){
        Connection conn = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        try {
            //String statement = "SELECT book_name, publisher_Pub_id, author, price, type, REPERTORY_SIZE FROM Book WHERE book_name = ?";
            //String statement = "SELECT book_name, Pub_name, author, price, type, REPERTORY_SIZE, introduction FROM Book,publisher WHERE publisher_Pub_id = Pub_id AND type = ? AND book_name LIKE\"%" + name + "%\"";
            
            String statement = "SELECT book_name, Pub_name, author, price, type_name, REPERTORY_SIZE, introduction FROM publisher,type  "
                    + "INNER JOIN book_has_type ON book_has_type.type_type_id = type.Type_id "
                    + "INNER JOIN book ON book.book_id = book_has_type.book_book_id "
                    + "WHERE publisher_Pub_id = Pub_id AND type_name = ? AND book_name LIKE\"%" + name + "%\"";
            
            conn = establishConnection();
            prepStmt = conn.prepareStatement(statement);
            prepStmt.setString(1, typeName);
            System.out.println("JCDB: "+BookStorage.getInstance().getName()); //using singleton to bind name of book.
            //prepStmt.setString(1, BookStorage.getInstance().getName());
            rs = prepStmt.executeQuery();
            System.out.println("Success");
            return rs;
            
        } catch (Exception e) {
            System.out.println("Customer cannot ritrive any book.");
            return null;
        }
    }
    
    public ResultSet customerRitriveBookByType(String typeName){
        Connection conn = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        try {
            //String statement = "SELECT book_name, Pub_name, author, price, type, REPERTORY_SIZE, introduction FROM Book,publisher WHERE publisher_Pub_id = Pub_id AND type = ?";
            String statement = "SELECT book_name, Pub_name, author, price, type_name, REPERTORY_SIZE, introduction FROM publisher,type  "
                    + "INNER JOIN book_has_type ON book_has_type.type_type_id = type.Type_id "
                    + "INNER JOIN book ON book.book_id = book_has_type.book_book_id "
                    + "WHERE publisher_Pub_id = Pub_id AND type_name = ?";
            conn = establishConnection();
            prepStmt = conn.prepareStatement(statement);
            prepStmt.setString(1, typeName);
            rs = prepStmt.executeQuery();
            System.out.println("Success");
            return rs;
        } catch (Exception e) {
            System.out.println("Cannot ritrive any book.");
            return null;
        }
    }
    
    public ResultSet typeSearching(String name){
        Connection conn = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        try {
            //String statement = "SELECT book_name, publisher_Pub_id, author, price, type, REPERTORY_SIZE FROM Book WHERE book_name = ?";
            String statement = "SELECT book_name, Pub_name, author, price, type, REPERTORY_SIZE, introduction FROM Book,publisher WHERE publisher_Pub_id = Pub_id AND type = Comic AND book_name LIKE\"%" + name + "%\"";
            conn = establishConnection();
            prepStmt = conn.prepareStatement(statement);
            System.out.println("JCDB: "+BookStorage.getInstance().getName()); //using singleton to bind name of book.
            //prepStmt.setString(1, BookStorage.getInstance().getName());
            rs = prepStmt.executeQuery();
            System.out.println("Success");
            return rs;
            
        } catch (Exception e) {
            System.out.println("Customer cannot ritrive any book.");
            return null;
        }
    }
    
    public void fillPublisherCombo(ObservableList option){
         Connection conn = null;
         try {
             String query = "SELECT Pub_name FROM publisher ORDER BY Pub_name DESC";
             conn = establishConnection();
             PreparedStatement prepStmt = conn.prepareStatement(query);
             ResultSet rs = prepStmt.executeQuery();
             while(rs.next()){
                 System.out.println("Pub_name: "+rs.getString("Pub_name"));
                 option.add(rs.getString("Pub_name"));
             }
         } catch (Exception e) {
             System.out.println("fill combox error: "+ e);
         }
     }
    
    public void fillTypeCombo(ObservableList option){
         Connection conn = null;
         try {
             String query = "SELECT type_name FROM type ORDER BY type_name DESC";
             conn = establishConnection();
             PreparedStatement prepStmt = conn.prepareStatement(query);
             ResultSet rs = prepStmt.executeQuery();
             while(rs.next()){
                 System.out.println("type_name: "+rs.getString("type_name"));
                 option.add(rs.getString("type_name"));
             }
         } catch (Exception e) {
             System.out.println("fill combox error: "+ e);
         }
     }
    
    public void customerFeedback(Feedback feedback){
        try(Connection conn = establishConnection();){
            //String statement = "INSERT INTO book (book_name, publisher_Pub_id, author, price, introduction, type) VALUES (?,?,?,?,?,?,?)";
            String statement = "INSERT INTO feedback SET feedback_id=?, title=?, body=?, date=?, status=?, user_userId=(SELECT userId FROM user WHERE name=?)";
            PreparedStatement prepStmt = (PreparedStatement) conn.prepareStatement(statement);
            
            prepStmt.setInt(1, feedback.getId());
            prepStmt.setString(2, feedback.getTitle());
            prepStmt.setString(3, feedback.getBody());
            prepStmt.setDate(4, help.toSqlDate(feedback.getDatetime()));
            prepStmt.setString(5, feedback.getStatus());
            prepStmt.setString(6, UserStorage.getInstance().getName());
            
            prepStmt.executeUpdate();
            
            System.out.println("the data has been moved into database.");
        }
        catch(Exception e){
            System.out.println("Customer Feedback: "+e);
        }
    }
    
    public void createCustomerID(User user){
        try(Connection conn = establishConnection();){
            //String statement = "INSERT INTO book (book_name, publisher_Pub_id, author, price, introduction, type) VALUES (?,?,?,?,?,?,?)";
            String statement = "INSERT INTO user SET name=?, username=?, password=?, level=?,email=?";
            PreparedStatement prepStmt = (PreparedStatement) conn.prepareStatement(statement);
            
            prepStmt.setString(1, user.getName());
            prepStmt.setString(2, user.getUsername());
            prepStmt.setString(3, user.getPassword());
            prepStmt.setInt(4, user.getLevel());
            prepStmt.setString(5, user.getEmail());
            
            prepStmt.executeUpdate();
            
            System.out.println("the data has been moved into database.");
        }
        catch(Exception e){
            System.out.println("Create Customer ID: "+e);
        }
    }
    
    /*public ResultSet ritriveCustomerName(String name){
        Connection conn = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        try {
            String statement = "SELECT name FROM user WHERE name = ?";
            conn = establishConnection();
            prepStmt = conn.prepareStatement(statement);
            prepStmt.setString(1, name);
            rs = prepStmt.executeQuery();
            System.out.println("Success");
            return rs;
        } catch (Exception e) {
            System.out.println("Cannot ritrive any book.");
            return null;
        }
    }*/
    
    public boolean verifyCustomer(String name) throws SQLException{
        Connection conn = establishConnection();
        String query = "SELECT count(*) FROM user WHERE name = ? AND level = 3";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, name);
        ResultSet rs = statement.executeQuery();
        int count = 0;
        while(rs.next()) {
            System.out.println("count: "+rs.getInt(1));
            count = rs.getInt(1);
        }
        if(count==0) {
            return false;
        }
        else {
            return true;
        }
    }
    
    public Connection establishConnection() {
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
    
    public void managerSavePublisher(Publisher p) throws SQLException {
        try(Connection conn = establishConnection();){
            String SQL = "INSERT INTO publisher (Pub_name,Pub_tel,Pub_address,Pub_introduction) VALUES (?,?,?,?)";
//            String SQL = "INSERT INTO publisher SET Pub_name=?, Pub_address=?, Pub_tel=?, Pub_introduction=?";
            PreparedStatement prepStmt = (PreparedStatement) conn.prepareStatement(SQL);
            
            prepStmt.setString(1, p.getPublisher());
            prepStmt.setInt(2, p.getTelephone());
            prepStmt.setString(3, p.getAddress());
            prepStmt.setString(4, p.getIntroduction());
            
            prepStmt.executeUpdate();
            
            System.out.println("the data has been moved into database.");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public ResultSet ManageRitrivePublisher(){
        Connection conn=null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        try {
            String SQL = "SELECT Pub_name,Pub_tel,Pub_address,Pub_introduction FROM publisher";
            conn = establishConnection();
            prepStmt = conn.prepareStatement(SQL);
            rs = prepStmt.executeQuery();
            System.out.println("Success");
            
        } catch (Exception e) {
            System.out.println("Cannot ritrive any publisher information.");
        }
        return rs;
    }
    
//    public static void ManagerDeletePublisher(String publisherName){
//        Connection conn = null;
//        PreparedStatement prepStmt = null;
//        ResultSet rs = null;
//        try {
//            String SQL = "DELETE FROM Publisher WHERE Pub_name = ?";
//            conn = establishConnection();
//            prepStmt = conn.prepareStatement(SQL);
//            prepStmt.setString(1, publisherName);
//            prepStmt.execute();
//            System.out.println("Success removed");
//            
//        } catch (Exception e) {
//            System.out.println("ERROR. Not delete.");
//        }
//    }
    
    public ResultSet ManagerSearchPublisher(String name){
        Connection conn = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        try {
           // String SQL = "SELECT Pub_name,Pub_address,Pub_tel,Pub_introduction FROM Publisher WHERE Pub_name=?";
            String SQL = "SELECT Pub_name, Pub_address, Pub_tel, Pub_introduction FROM publisher WHERE Pub_name LIKE\"%" + name + "%\"";
            conn = establishConnection();
            prepStmt = conn.prepareStatement(SQL);
            rs = prepStmt.executeQuery();
            System.out.println("Success");
            
        } catch (Exception e) {
            System.out.println("Cannot ritrive any publisher.");
        }
        return rs;
    }
    
    public void managerSaveType(String type,String introduction) throws SQLException {
        try(Connection conn = establishConnection();){
            //String SQL = "INSERT INTO type (Type_name,Type_introduction) VALUES (?,?)";
            String SQL = "INSERT INTO type (Type_name,type_introduction) VALUES (?,?)";
            PreparedStatement prepStmt = (PreparedStatement) conn.prepareStatement(SQL);
            
            prepStmt.setString(1, type);
            prepStmt.setString(2, introduction);
            prepStmt.executeUpdate();
            
            System.out.println("the data has been moved into database.");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public ResultSet ManageRitriveType(){
        Connection conn=null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        try {
           // String SQL = "SELECT publisherName, address, phone, introduction FROM Publisher";
            String SQL = "SELECT Type_name,Type_introduction FROM type";
            conn = establishConnection();
            prepStmt = conn.prepareStatement(SQL);
            rs = prepStmt.executeQuery();
            System.out.println("Success");
            
        } catch (Exception e) {
            System.out.println("Cannot ritrive any publisher information.");
        }
        return rs;
    }
    
    public void ManagerDeleteType(String typeName){
        Connection conn = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        try {
            String SQL = "DELETE FROM type WHERE Type_name = ?";
            conn = establishConnection();
            prepStmt = conn.prepareStatement(SQL);
            prepStmt.setString(1, typeName);
            prepStmt.execute();
            System.out.println("Success removed");
            
        } catch (Exception e) {
            System.out.println("ERROR. Not delete.");
        }
    }
    
    public ResultSet ManagerSearchType(String name) {
        Connection conn = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        try {
           // String SQL = "SELECT Pub_name,Pub_address,Pub_tel,Pub_introduction FROM Publisher WHERE Pub_name=?";
            String SQL = "SELECT type_name, type_introduction FROM type WHERE type_name LIKE\"%" + name + "%\"";
            conn = establishConnection();
            prepStmt = conn.prepareStatement(SQL);
            rs = prepStmt.executeQuery();
            System.out.println("Success");
            
        } catch (Exception e) {
            System.out.println("Cannot ritrive any publisher.");
        }
        return rs;
    }
    
    public boolean verifyAccount(String uname, String accountType) throws SQLException{
        Connection conn = establishConnection();
        String query = "";
        if(accountType.equals("Employee")) {
            query = "SELECT count(*) FROM user WHERE username = ? AND level = 2";
        }
        else if(accountType.equals("Manager")) {
            query = "SELECT count(*) FROM user WHERE username = ? AND level = 1";
        }
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, uname);
        //statement.setInt(2, LoginStorage.getInstance().getAccountType());
        ResultSet rs = statement.executeQuery();
        int count = 0;
        while(rs.next()) {
            System.out.println("count: "+rs.getInt(1));
            count = rs.getInt(1);
        }
        if(count==0) {
            return false;
        }
        else {
            return true;
        }
    }
    
    public int getId(String username, String pwd, String accountType) {
        Connection conn = establishConnection();
        String query = "";
        if(accountType.equals("Employee")) {
            query = "SELECT userId FROM user WHERE username = ? AND password = ? AND level = 2";
        }
        else if(accountType.equals("Manager")) {
            query = "SELECT userId FROM user WHERE username = ? AND password = ? AND level = 1";
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
            System.out.println("password wrong."+e);
        }
        return id;
    }
    
    public ResultSet ManageRitriveFeedback(String title){
        Connection conn = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        try {
            String SQL = "SELECT body,datetime FROM feedback WHERE title = ?";
            conn = establishConnection();
            prepStmt = conn.prepareStatement(SQL);
            prepStmt.setString(1, title);
            rs =prepStmt.executeQuery();
            return rs;
        } catch (Exception e) {
            System.out.println("Cannot ritrive any feedback information.");
            return null;
        }
    }
    public void fillTitleCombo(ObservableList option){
         Connection conn = null;
         try {
             String SQL = "SELECT title FROM feedback ORDER BY title DESC";
             conn = establishConnection();
             PreparedStatement prepStmt = conn.prepareStatement(SQL);
             ResultSet rs = prepStmt.executeQuery();
             while(rs.next()){
                 System.out.println("title: "+rs.getString("title"));
                 option.add(rs.getString("title"));
             }
         } catch (Exception e) {
             System.out.println("fill combox error: "+ e);
         }
     }
    public void managerSaveFeedback(Feedback feed) throws SQLException {
        try(Connection conn = establishConnection();){
            String SQL = "INSERT INTO feedback (title,body,email) VALUES (?,?,?)";
            PreparedStatement prepStmt = (PreparedStatement) conn.prepareStatement(SQL);
            
//            prepStmt.setString(1, feed.getTitle());
//            prepStmt.setString(2, feed.getBody());
//            prepStmt.setString(3, feed.getEmail());
            prepStmt.executeUpdate();
            
            System.out.println("the data has been moved into database.");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
    

