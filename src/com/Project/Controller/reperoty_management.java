/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project.Controller;

import classes.importPro;
import classes.respertoryObject;
import classes.salesPro;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author chenq
 */
public class reperoty_management implements Initializable {

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
    private Label DateL;
    @FXML
    private Label QuantityL;
    @FXML
    private ComboBox book;
    @FXML
    private Label repertory;
    @FXML
    private Label bookName;
    @FXML
    private TextArea text2;
    @FXML
    private TextField delete;
    @FXML
    private TableView<importPro> tableView;
    @FXML
    private TableColumn<importPro,String> bookNameT;  
    @FXML
    private TableColumn<importPro,String> DateT;
    @FXML
    private TableColumn<importPro,Integer> amountT;

    String date, totalp;
    String sql;
    String time;
    ResultSet rs;
    Double price = 0.0;
    int quantity = 0;
    int reperint;
    String rep;
    String totalq = "";
    String text1 = "";
    private ObservableList<String> booklist = FXCollections.observableArrayList();
    DatabaseConnection connection = new DatabaseConnection();
    respertoryObject res = new respertoryObject(20);
    private ObservableList<importPro> importPro = FXCollections.observableArrayList();

    @FXML
    private void handleSearchButtonAction(ActionEvent event) throws SQLException {
        //yyy-mm-dd
        textA.setText("");
        text1 = "";
        price = 0.0;
        quantity = 0;
        if (data_text.getText().matches("([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))|([1-2][0-9]{3})")) {
            date = data_text.getText();
            System.out.println(date);
            sql = "SELECT import.RECORD_DATE,import_has_book.INT_SUM,book.BOOK_NAME,book.price,book.REPERTORY_SIZE "
                    + "FROM import,import_has_book,book "
                    + "WHERE import.idImport = import_has_book.import_idImport and import_has_book.book_idBook = book.book_id"
                    + " and import.RECORD_DATE like" + "'" + date + "%'";
            rs = connection.query(sql);
            if (rs.next()) {
                rs = connection.query(sql);
                while (rs.next()) {
                    text1 = text1 + "book: " + rs.getString("book_name") + "  ***price: " + rs.getDouble("price") + "  ***date: " + rs.getString("RECORD_DATE") + "  ***quantity: " + rs.getInt("INT_SUM") + "\r\n";
                    price = price + rs.getDouble("price") * rs.getInt("INT_SUM");
                    quantity = quantity + rs.getInt("INT_SUM");
                }
                totalp = price.toString();
                textA.appendText(text1);
                DateL.setText(date);
                totalq = String.valueOf(quantity);
                QuantityL.setText(totalq);
            } else {
                textA.setText("No data.");
            }
        } else {
            System.out.println("Please follow the format: yyyy-mm-dd");
            JOptionPane.showMessageDialog(null, "Time ：follow the time format", "ERROR", JOptionPane.ERROR_MESSAGE);

        }
    }

    @FXML
    private void handleBackButtonAction(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/Project/FXML/Menu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Menu");
        stage.show();
    }

    @FXML
    private void handleSureButtonAction(ActionEvent event) throws SQLException {
        System.out.println(book.getValue());
        sql = "select* from book where BOOK_NAME = " + "'" + book.getValue() + "'";
        rs = connection.query(sql);
        while (rs.next()) {
            reperint = rs.getInt("REPERTORY_SIZE");
        }
        rep = String.valueOf(reperint);
        repertory.setText(rep);
        bookName.setText((String) book.getValue());
    }

    @FXML
    private void handleAddButton(ActionEvent event) {
        getTime();
        int amount = 0;
        if (amountL.getText().trim().length() < 1) {
            JOptionPane.showMessageDialog(null, "Empty", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            if (book.getValue() == "choose") {
                JOptionPane.showMessageDialog(null, "Please choose a book", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                amount = Integer.parseInt(amountL.getText());
                //String text=("book name: "+book.getValue()+"  ***date: "+time+"  ***amount: "+amount+"\r\n");
                //text2.appendText(text);
                
                //sales.insert((String) book.getValue(), time, amount);
                //text2.appendText(sales.display());
                importPro.add(new importPro((String) book.getValue(),time,amount));
                tableView.setItems(importPro);
            }
        }

    }

    @FXML
    private void handleDeleteButton(ActionEvent event) {
        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        if(tableView.getSelectionModel().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please choose a Line", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        else{
            tableView.getItems().remove(selectedIndex);
        }
    }
    
    @FXML
    private void handleConfirmButton(ActionEvent event) throws SQLException{
        //int i =0;
        int bookid = 0,importid = 0;
        System.out.println("yesy\r\n");
        //System.out.println(importPro.get(1));
        for(int i=importPro.size()-1;importPro.size()>0;i--){
            rs = connection.query("select book_id from book where book_name = '"+importPro.get(i).getbookName()+"'");
            while(rs.next()){ bookid = rs.getInt("book_id");}
            String sqla = "insert into import (RECORD_DATE) values('"+importPro.get(i).getDate()+"');";
            connection.executeSql(sqla);
            rs = connection.query("select idImport from import where RECORD_DATE = '"+importPro.get(i).getDate()+"'");
            System.out.println(importPro.get(i).getDate());
            while(rs.next()){ importid = rs.getInt("idImport");}            
            //System.out.println(sql);
            String sqlb = "insert into import_has_book (INT_SUM,import_idImport,book_idBook ) values( "+importPro.get(i).getamount()+","+importid+","+bookid+")";
            connection.executeSql(sqlb);
            checkR(importPro.get(i).getbookName());
            int am= Integer.valueOf(rep)+importPro.get(i).getamount();
            String sqlc = "update book set REPERTORY_SIZE = "+am+" where book_id = "+bookid;
            System.out.println("book:"+bookid+"imp:"+importid);
            connection.executeSql(sqlc);    
            
            importPro.remove(i);
        }
        //importPro.removeAll();
    }
    
    @FXML
    private void handleCancelButton(ActionEvent event){
        for(int i=importPro.size()-1;importPro.size()>0;i--){
            importPro.remove(i);
            //totalp2.setText("0");
        }
    }

    public void getTime() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        time = format.format(date);
    }

    public void getbooklist() throws SQLException {
        sql = "select BOOK_NAME from book";
        rs = connection.query(sql);
        while (rs.next()) {
            booklist.add(rs.getString("BOOK_NAME"));
        }
        book.setValue("choose");
        book.setItems(booklist);
    }
    private void checkR(String bookName) throws SQLException{
        sql = "select* from book where BOOK_NAME = " + "'" + bookName + "'";
        rs = connection.query(sql);
        while (rs.next()) {
            reperint = rs.getInt("REPERTORY_SIZE");
        }
        rep = String.valueOf(reperint);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bookNameT.setCellValueFactory(new PropertyValueFactory<importPro,String> ("bookName"));
        DateT.setCellValueFactory(new PropertyValueFactory<importPro,String> ("Date"));
        amountT.setCellValueFactory(new PropertyValueFactory<importPro,Integer> ("amount"));
        try {
            // TODO
            getbooklist();
        } catch (SQLException ex) {
            Logger.getLogger(Sales_manageController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
