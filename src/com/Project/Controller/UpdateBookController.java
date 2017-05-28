/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project.Controller;

import classes.Book;
import classes.BookStorage;
import classes.Help;
import database.JCDB;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Xuantong
 */
public class UpdateBookController implements Initializable {
    @FXML
    private TextArea introArea;
    
    @FXML
    private TextField bookNameTf2, authorBt, priceBt,quantityBt;
    @FXML
    private ComboBox publisherCombo,typeCombo,doubleName;
    @FXML
    private Button saveBt,clearBt;
    @FXML
    private ListView listView;
    
    private JCDB db = new JCDB();
    private Help help  = new Help();
    
    //ObservableList used for holding comboBox value.
    private ObservableList<String> publisherList = FXCollections.observableArrayList();
    private ObservableList<String> typeList = FXCollections.observableArrayList();
    private ObservableList<String> nameList = FXCollections.observableArrayList();
    
    //ObservableList used for holding Book values.
    private ObservableList<Book> bookData;
    private ObservableList<Book> searchData;
    //ObservableList used for showNewBook method.
    private ObservableList<Book> show;
    
    private Book book = new Book();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getListView();
        getComboBoxValue();
    }
    
    private void getListView(){
        db.fillBookName(nameList);
        listView.setItems(nameList);
    }
    
    private void getComboBoxValue(){
        db.fillPublisherCombo(publisherList);
        publisherCombo.setItems(publisherList);
        
        db.fillTypeCombo(typeList);
        typeCombo.setItems(typeList);
    }
    
    @FXML
    private void readMultiple(ActionEvent event){
        try {
            db.readMultipleBook(book,doubleName, bookNameTf2, authorBt, priceBt, quantityBt, introArea, publisherCombo, typeCombo);
            System.out.println("book id in update"+book.getId());
        } catch (Exception e) {
            System.out.println("readMultiple error: "+e);
        }
    }
    
    @FXML    
    private void readBook(MouseEvent event){
        getBookData();
    }
    
    private void getBookData(){
        try {
            String bookName = listView.getSelectionModel().getSelectedItem().toString();
            db.retrieveBookForUpdate(book,bookName,bookNameTf2, authorBt, priceBt, quantityBt, introArea, publisherCombo, typeCombo, doubleName);
        } catch (Exception e) {
            System.out.println("listView no value in here");
        }
    }
    
    private void refresh(){
        bookData.clear();
        getBookData();
    }
    
    @FXML
    private void handleUpdateAction(ActionEvent event) throws SQLException, NumberFormatException, IOException{
        updateBook(event);
    }
    
    private void updateBook(ActionEvent event) throws SQLException, NumberFormatException, IOException {
        boolean isPrice = help.isInteger(priceBt.getText());
        boolean isQuantity = help.isInteger(quantityBt.getText());
        
        if(!isPrice){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("PRICE IS INVALID");
            alert.show();
        }
        else if(!isQuantity){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("QUANTITY IS INVALID");
            alert.show();
        }
        else if(bookNameTf2.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("INVALID BOOK NAME.");
            alert.show();
        }
        else if(authorBt.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("INVALID AUTHOR.");
            alert.show();
        }
        else if(introArea.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("INVALID INTRODUCTION.");
            alert.show();
        }
        else{
            double price = Double.valueOf(priceBt.getText());
            int quantity = Integer.valueOf(quantityBt.getText());
            
            book.setName(bookNameTf2.getText());
            book.setAuthor(authorBt.getText());
            book.setPrice(price);
            book.setPublisher(publisherCombo.getSelectionModel().getSelectedItem().toString());
            book.setType(typeCombo.getSelectionModel().getSelectedItem().toString());
            book.setIntro(introArea.getText());
            book.setQuantity(quantity);
            book.setDate(currentDate());
            
            ButtonType yes = new ButtonType("YES", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);
            Alert alert = new Alert(AlertType.CONFIRMATION, "Do You Want To Save Your Update?", yes,no);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Update Request");
            
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == yes){
                db.updateBook(book);
                System.out.println("update method:"+book.getId());
                
                refresh();
                
                bookNameTf2.clear();
                authorBt.clear();
                priceBt.clear();
                introArea.clear();
                quantityBt.clear();
                publisherCombo.setValue("publisher");
                typeCombo.setValue("type");
            } else {
                alert.close();
            }
        }
    }
    
    private LocalDate currentDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDate localDate = LocalDate.now();
        return localDate;
    }
    
    @FXML
    private void handleClearAction(ActionEvent event){
        
    }
    
    @FXML
    private void handleBackAction(ActionEvent event) throws IOException{
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/Project/FXML/BookManagingPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
