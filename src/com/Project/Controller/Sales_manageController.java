/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project.Controller;

import classes.Book;
import classes.salesObject;
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
public class Sales_manageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField data_text;
    @FXML
    private TextField amountL;
    @FXML
    private TextField delete;
    @FXML
    private TextArea textA;
    @FXML
    private Label totalprice;
    @FXML
    private Label totalp2;
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
    private TableView<salesPro> tableView;
    @FXML
    private TableColumn<salesPro,String> bookNameT;  
    @FXML
    private TableColumn<salesPro,String> DateT;
    @FXML
    private TableColumn<salesPro,Integer> amountT;
    @FXML
    private TableColumn<salesPro,Double> priceT;
            
    String date, totalp;
    String time;
    String sql;
    ResultSet rs;
    Double price = 0.0;
    Double pt = 0.0;
    int quantity = 0;
    int reperint;
    String rep;
    String totalq = "";
    String text1 = "";
    private ObservableList<String> booklist = FXCollections.observableArrayList();
    //private ObservableList<String> tablelist = FXCollections.observableArrayList();
    DatabaseConnection connection = new DatabaseConnection();
    salesObject sales = new salesObject(20);
    private ObservableList<salesPro> salespro = FXCollections.observableArrayList();

//ObservableList<TableColumn> observableList = table.getColumns(); 
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
            sql = "SELECT sales.RECORD_DATE,book_has_sales.TRADE_SUM,book.BOOK_NAME,book.price,book.REPERTORY_SIZE "
                    + "FROM sales,book_has_sales,book "
                    + "WHERE sales.idSales = book_has_sales.sales_idSales and book_has_sales.book_idBook = book.book_id"
                    + " and sales.RECORD_DATE like" + "'" + date + "%'";
            rs = connection.query(sql);
            if (rs.next()) {
                rs = connection.query(sql);
                while (rs.next()) {
                    text1 = text1 + "book: " + rs.getString("BOOK_NAME") + "  price: " + rs.getDouble("price") + "   date: " + rs.getString("RECORD_DATE") + "   quantity: " + rs.getInt("TRADE_SUM") + "\r\n";
                    price = price + rs.getDouble("price") * rs.getInt("TRADE_SUM");
                    quantity = quantity + rs.getInt("TRADE_SUM");
                }
                totalp = price.toString();
                textA.appendText(text1);
                totalprice.setText(totalp);
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
    private void handleAddButton(ActionEvent event) throws SQLException {
        getTime();
        int amount = 0;
        if (amountL.getText().trim().length() < 1) {
            JOptionPane.showMessageDialog(null, "Empty", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            if (book.getValue() == "choose") {
                JOptionPane.showMessageDialog(null, "Please choose a book", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                amount = Integer.parseInt(amountL.getText());
                rs = connection.query("select price from book where book_name = '"+(String) book.getValue()+"'");
                Double p = 0.0;
                while(rs.next()){
                    p = (Double) rs.getDouble("price")*amount;
                }
                salespro.add(new salesPro((String) book.getValue(),time,amount,p));
                tableView.setItems(salespro);
                
            }
            
            
            pt = pt+ salespro.get(salespro.size()-1).getPrice();
            
            totalp2.setText(String.valueOf(pt));
        }

    }

    @FXML
    private void handleDeleteButton(ActionEvent event) {
        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        double temp = salespro.get(selectedIndex).getPrice();
        if(tableView.getSelectionModel().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please choose a Line", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        else{
            tableView.getItems().remove(selectedIndex);
            pt = pt-temp;
            //System.out.println(salespro.get(selectedIndex).getPrice());
            totalp2.setText(String.valueOf(pt));
        }
    }
    
    @FXML
    private void handleConfirmButton(ActionEvent event) throws SQLException{
        //int i =0;
        int bookid = 0,salesid = 0;
        System.out.println("yesy\r\n");
        //System.out.println(importPro.get(1));
        for(int i=salespro.size()-1;salespro.size()>0;i--){
            rs = connection.query("select book_id from book where book_name = '"+salespro.get(i).getbookName()+"'");
            while(rs.next()){ bookid = rs.getInt("book_id");}
            String sqla = "insert into sales (RECORD_DATE) values('"+salespro.get(i).getDate()+"');";
            connection.executeSql(sqla);
            rs = connection.query("select idSales from sales where RECORD_DATE = '"+salespro.get(i).getDate()+"'");
            System.out.println(salespro.get(i).getDate());
            while(rs.next()){ salesid = rs.getInt("idSales");}            
            //System.out.println(sql);
            String sqlb = "insert into book_has_sales (TRADE_SUM,sales_idSales,book_idBook ) values( "+salespro.get(i).getamount()+","+salesid+","+bookid+")";
            connection.executeSql(sqlb);
            checkR(salespro.get(i).getbookName());
            int am= Integer.valueOf(rep)-salespro.get(i).getamount();
            String sqlc = "update book set REPERTORY_SIZE = "+am+" where book_id = "+bookid;
            System.out.println("book:"+bookid+"imp:"+salesid);
            connection.executeSql(sqlc);    
            
            salespro.remove(i);
        }
        pt=0.0;
        totalp2.setText("0");
        //importPro.removeAll();
    }
    
    @FXML
    private void handleCancelButton(ActionEvent event){
        for(int i=salespro.size()-1;salespro.size()>0;i--){
            salespro.remove(i);
            pt=0.0;
            totalp2.setText("0");
        }
    }
    
    private void checkR(String bookName) throws SQLException{
        sql = "select* from book where BOOK_NAME = " + "'" + bookName + "'";
        rs = connection.query(sql);
        while (rs.next()) {
            reperint = rs.getInt("REPERTORY_SIZE");
        }
        rep = String.valueOf(reperint);
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bookNameT.setCellValueFactory(new PropertyValueFactory<salesPro,String> ("bookName"));
        DateT.setCellValueFactory(new PropertyValueFactory<salesPro,String> ("Date"));
        amountT.setCellValueFactory(new PropertyValueFactory<salesPro,Integer> ("amount"));
        priceT.setCellValueFactory(new PropertyValueFactory<salesPro,Double> ("price"));
        
        //tableView.getSelectionModel().selectedItemProperty().addListener(listener);
        try {
            // TODO
            getbooklist();
        } catch (SQLException ex) {
            Logger.getLogger(Sales_manageController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
