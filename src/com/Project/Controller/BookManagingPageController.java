/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project.Controller;

import classes.Book;
import classes.Help;
import database.JDBC;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Xuantong
 */
public class BookManagingPageController implements Initializable {

    @FXML
    private TextField bookNameTf1;
    @FXML
    private TextArea introArea;
    @FXML
    private TableView<?> tv;
    @FXML
    private TableColumn<?, ?> nameT;
    @FXML
    private TableColumn<?, ?> authorT;
    @FXML
    private TableColumn<?, ?> publisherT;
    @FXML
    private TableColumn<?, ?> priceT;
    @FXML
    private TableColumn<?, ?> typeT;
    @FXML
    private TableColumn<?, ?> stockT;
    @FXML
    private TextField bookNameTf2, authorBt, priceBt;
    @FXML
    private ComboBox publisherCombo;
    @FXML
    private ComboBox typeCombo;
    
    //ObservableList used for holding comboBox value.
    private ObservableList<String> publisherList = FXCollections.observableArrayList("one","two","three");
    private ObservableList<String> typeList = FXCollections.observableArrayList("aaa","bbb","ccc");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //set comboBox value.
        publisherCombo.setValue("one");
        publisherCombo.setItems(publisherList);
        
        typeCombo.setValue("aaa");
        typeCombo.setItems(typeList);
    }    

    @FXML
    private void handleBackAction(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(""));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void handleSaveAction(ActionEvent event) throws SQLException {
        
        boolean isPrice = Help.isInteger(priceBt.getText());
        
        if(!isPrice){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("PRICE IS INVALID.");
            alert.show();
        }
        else if(bookNameTf2.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("INVALID BOOK NAME.");
            alert.show();
        }
        else if(authorBt.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("INVALID AUTHOR.");
            alert.show();
        }
        else if(introArea.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("INVALID INTRO.");
            alert.show();
        }
        else{
            double price = Double.valueOf(priceBt.getText());
            Book b = new Book(
                    01,
                    1, 
                    bookNameTf2.getText(),
                    authorBt.getText(), 
                    price,  
                    publisherCombo.getSelectionModel().getSelectedItem().toString(), 
                    typeCombo.getSelectionModel().getSelectedItem().toString(), 
                    introArea.getText());
            
            JDBC.managerAddNewBook(
                    b.getId(),
                    b.getInventory(), 
                    b.getName(), 
                    b.getAuthor(),
                    b.getPrice(), 
                    b.getPublisher(), 
                    b.getType(),
                    b.getIntro());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("SAVED.");
            alert.show();
        }
        
        
    }
    
    @FXML
    private void handleClearAction(ActionEvent event) {
        bookNameTf2.setText("");
        authorBt.setText("");
        priceBt.setText("");
        introArea.setText("");
        
    }
    
    @FXML
    private void handleSearchAction(ActionEvent event){
        
    }
    
}
