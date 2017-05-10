/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project.Controller;

import database.DatabaseConnection;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author chenq
 */
public class Sales_management_interfaceController implements Initializable {

    /**
     * Initializes the controller class.
     */

    @FXML 
    private TextField data_text;
    @FXML
    private TextField quantity_text;
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
    
    
    String date,totalp;
    String sql;
    ResultSet rs;
    Double price;
    int quantity;
    String totalq;
    String text1=null;
    private ObservableList<String> booklist = FXCollections.observableArrayList();
    DatabaseConnection connection = new DatabaseConnection();
    
    
    @FXML
    private void handleSearchButtonAction(ActionEvent event) throws SQLException{
        //yyy-mm-dd
        if(data_text.getText().matches("^\\d{4}-0[1-9]|1[1-2]-0[1-9]|[1-2]")){
            date = data_text.getText();
            textA.setText("");
        }
        else{
            System.out.println("Please follow the format: yyyy-mm-dd");
            JOptionPane.showMessageDialog(null, "Time ：follow the time format", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
       
        sql = "SELECT import.RECORD_DATE,import_has_book.IN_SUM,book.BOOK_NAME,book.BOOK_PRICE,book.REPERTORY_SIZE "
                + "FROM import,import_has_book,book "
                +"WHERE import.idImport = import_has_book.import_idImport and import_has_book.book_idBook = book.idBook"
                +"and import.RECORD_DATE ="+"'" + date + "'";  
        rs = connection.query(sql);
        while(rs.next()){
            text1=text1+"book: "+rs.getString("")+"  price: "+rs.getDouble("BOOK_PRICE")+"  date: "+rs.getString("RECORD_DATE")+"  quantity:"+rs.getInt("IN_SUM")+"\r\n";
            price= price+rs.getDouble("BOOK_PRICE")*rs.getInt("IN_SUM");
            quantity= quantity+rs.getInt("IN_SUM");
        }
        totalp = price.toString();
        textA.appendText(text1);
        totalprice.setText(totalp);
        DateL.setText(date);
        totalq=String.valueOf(quantity);
        QuantityL.setText(totalq);
        
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
        // TODO
        
    }    
    
}
