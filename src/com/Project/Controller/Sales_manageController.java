/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project.Controller;

import database.DatabaseConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author chenq
 */
public class Sales_manageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML 
    private TextField data_text;
    @FXML
    private TextField amountL;
    @FXML
    private TextArea textA;
    @FXML
    private Label totalprice;
    @FXML
    private Label DateL;
    @FXML
    private Label QuantityL;
    @FXML
    private ComboBox book;
    @FXML 
    private Label repertory;
    @FXML
    private Label bookName;
    
    
    String date,totalp;
    String sql;
    ResultSet rs;
    Double price=0.0;
    int quantity=0;
    int reperint;
    String rep;
    String totalq="";
    String text1="";
    private ObservableList<String> booklist = FXCollections.observableArrayList();
    DatabaseConnection connection = new DatabaseConnection();
    
    
    @FXML
    private void handleSearchButtonAction(ActionEvent event) throws SQLException{
        //yyy-mm-dd
        textA.setText("");
        text1="";
        price=0.0;
        quantity=0;
        if(data_text.getText().matches("([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))|([1-2][0-9]{3})")){
            date = data_text.getText();
            System.out.println(date);
            sql = "SELECT sales.RECORD_DATE,book_has_sales.TRADE_SUM,book.BOOK_NAME,book.BOOK_PRICE,book.REPERTORY_SIZE "
                + "FROM sales,book_has_sales,book "
                +"WHERE sales.idSales = book_has_sales.sales_idSales and book_has_sales.book_idBook = book.idBook"
                +" and sales.RECORD_DATE like" + "'" + date + "%'";  
        rs = connection.query(sql);
        if(rs.next()){
            rs = connection.query(sql);
        while(rs.next()){
            text1=text1+"book: "+rs.getString("BOOK_NAME")+"  price: "+rs.getDouble("BOOK_PRICE")+"  date: "+rs.getString("RECORD_DATE")+"  quantity:"+rs.getInt("TRADE_SUM")+"\r\n";
            price= price+rs.getDouble("BOOK_PRICE")*rs.getInt("TRADE_SUM");
            quantity= quantity+rs.getInt("TRADE_SUM");
        }
        totalp = price.toString();
        textA.appendText(text1);
        totalprice.setText(totalp);
        DateL.setText(date);
        totalq=String.valueOf(quantity);
        QuantityL.setText(totalq);
        }
        else{
            textA.setText("No data.");
        }
        }
        else{
            System.out.println("Please follow the format: yyyy-mm-dd");
            JOptionPane.showMessageDialog(null, "Time ：follow the time format", "ERROR", JOptionPane.ERROR_MESSAGE);
            
        }    
 
    }
    
    @FXML
    private void handleBackButtonAction(ActionEvent event) throws IOException{
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/Project/FXML/Menu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Menu");
        stage.show();
    }
    
    @FXML
    private void handleSureButtonAction(ActionEvent event) throws SQLException{
        System.out.println(book.getValue());
        sql = "select* from book where BOOK_NAME = "+"'"+book.getValue()+"'";
        rs = connection.query(sql);
        while(rs.next()){
        reperint=rs.getInt("REPERTORY_SIZE");
        }
        rep=String.valueOf(reperint);
        repertory.setText(rep);
        bookName.setText((String) book.getValue());
    }

    
    
    public void getTime(){
        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String time=format.format(date);
    }
    public void getbooklist() throws SQLException{
        sql = "select BOOK_NAME from book";
        rs = connection.query(sql);
        while(rs.next()){
            booklist.add(rs.getString("BOOK_NAME"));
        }
        book.setValue("choose");
        book.setItems(booklist);
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            getbooklist();
        } catch (SQLException ex) {
            Logger.getLogger(Sales_manageController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    
    
}

