/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project.Controller;

import classes.Book;
import classes.Help;
import database.JCDB;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    private TableView tv;
    @FXML
    private TableColumn<Book, String> NAME;
    @FXML
    private TableColumn<Book, String> AUTHOR;
    @FXML
    private TableColumn<Book, String> PUBLISHER;
    @FXML
    private TableColumn<Book, Double> PRICE;
    @FXML
    private TableColumn<Book, String> TYPE;
    @FXML
    private TableColumn<Book,Integer> quantityId;
    @FXML
    private TableColumn<Book,String> introduction;
    
    @FXML
    private TextField bookNameTf2, authorBt, priceBt,quantityBt;
    @FXML
    private ComboBox publisherCombo;
    @FXML
    private ComboBox typeCombo;
    @FXML
    private Button searchBt,deleteBt,saveBt,clearBt,logoutBt,homeBt;
    
    private JCDB db = new JCDB();
    
    //ObservableList used for holding comboBox value.
    private ObservableList<String> publisherList = FXCollections.observableArrayList();
    private ObservableList<String> typeList = FXCollections.observableArrayList();
    
    //ObservableList used for holding Book values.
    private ObservableList<Book> bookData;
    private ObservableList<Book> searchData;
    //ObservableList used for showNewBook method.
    private ObservableList<Book> show;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getComboBoxValue();
        getBookData();
    }
    
    private void getComboBoxValue(){
        db.fillPublisherCombo(publisherList);
        //set comboBox value.
        publisherCombo.setValue("PUBLISHER");
        publisherCombo.setItems(publisherList);
        
        db.fillTypeCombo(typeList);
        typeCombo.setValue("TYPE");
        typeCombo.setItems(typeList);
    }

    private void getBookData(){
        try {
            bookData = FXCollections.observableArrayList();
            ResultSet rs = db.ritriveAllBook();
            
            while(rs.next()){
                System.out.println(rs.getString("book_name"));
                System.out.println(rs.getString("Pub_name"));
                System.out.println(rs.getString("author"));
                System.out.println(rs.getDouble("price"));
                System.out.println(rs.getString("type"));
                System.out.println(rs.getInt("REPERTORY_SIZE"));
                
                bookData.add(new Book(
                        rs.getString("book_name"), 
                        rs.getString("author"), 
                        rs.getDouble("price"), 
                        rs.getString("Pub_name"), 
                        rs.getString("type"), 
                        rs.getInt("REPERTORY_SIZE"),
                        rs.getString("introduction")));
            }
        } catch (Exception e) {
            System.out.println("Error "+ e);
        }
        
        NAME.setCellValueFactory(new PropertyValueFactory<Book,String>("name"));
        AUTHOR.setCellValueFactory(new PropertyValueFactory<Book,String>("author"));
        PUBLISHER.setCellValueFactory(new PropertyValueFactory<Book,String>("publisher"));
        PRICE.setCellValueFactory(new PropertyValueFactory<Book,Double>("price"));
        TYPE.setCellValueFactory(new PropertyValueFactory<Book,String>("type"));
        quantityId.setCellValueFactory(new PropertyValueFactory<Book,Integer>("quantity"));
        introduction.setCellValueFactory(new PropertyValueFactory<Book,String>("introduction"));
        
        tv.setItems(bookData);
    }
    
    @FXML
    private void handleBackAction(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/Project/FXML/Menu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
     @FXML
    private void handleLogoutAction(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/Project/FXML/LoginPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void handleSaveAction(ActionEvent event) throws SQLException {
        SaveToDatabase();
    }

    private void SaveToDatabase() throws SQLException, NumberFormatException {
        boolean isPrice = Help.isInteger(priceBt.getText());
        boolean isQuantity = Help.isInteger(quantityBt.getText());
        
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
            Book book = new Book(
                    bookNameTf2.getText(),
                    authorBt.getText(),
                    price,
                    publisherCombo.getSelectionModel().getSelectedItem().toString(),
                    typeCombo.getSelectionModel().getSelectedItem().toString(), 
                    introArea.getText(),
                    quantity);
            
            db.managerAddNewBook(book);
            refresh();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("SAVED");
            alert.setContentText("The Data Has Been Saved");
            alert.show();
        }
    }
    
    private void refresh(){
        bookData.clear();
        getBookData();
        /*Book book = new Book(
                bookNameTf2.getText(), 
                authorBt.getText(), 
                Integer.parseInt(priceBt.getText()), 
                publisherCombo.getSelectionModel().getSelectedItem().toString(), 
                typeCombo.getSelectionModel().getSelectedItem().toString(), 
                introArea.getText());
        
        tv.getItems().add(book);
        
        //Clear text field.
        bookNameTf2.clear();
        authorBt.clear();
        priceBt.clear();
        introArea.clear();
        publisherCombo.setValue("publisher");
        typeCombo.setValue("type");*/
    }
    
    @FXML
    private void handleClearAction(ActionEvent event) {
        bookNameTf2.setText("");
        authorBt.setText("");
        priceBt.setText("");
        introArea.setText("");
        publisherCombo.setValue("publisher");
        typeCombo.setValue("type");
    }
    
    @FXML
    private void handleSearchAction(ActionEvent event){
        if(bookNameTf1.getText().isEmpty()){
            getBookData();
        }
        else{
            getSearched();
        }
    }
    
    private void getSearched(){
        try {
            NAME.getColumns().clear();
            AUTHOR.getColumns().clear();
            PUBLISHER.getColumns().clear();
            PRICE.getColumns().clear();
            TYPE.getColumns().clear();
            quantityId.getColumns().clear();
            
            searchData = FXCollections.observableArrayList();
            ResultSet rs = db.searchingAllBook(bookNameTf1.getText());
            while(rs.next()){
                searchData.add(new Book(
                        rs.getString("book_name"), 
                        rs.getString("author"), 
                        rs.getDouble("price"), 
                        rs.getString("Pub_name"), 
                        rs.getString("type"), 
                        rs.getInt("REPERTORY_SIZE"),
                        rs.getString("introduction")));
            }
        } catch (Exception e) {
            System.out.println("ERROR"+e);
        }
        
        NAME.setCellValueFactory(new PropertyValueFactory<Book,String>("name"));
        AUTHOR.setCellValueFactory(new PropertyValueFactory<Book,String>("author"));
        PUBLISHER.setCellValueFactory(new PropertyValueFactory<Book,String>("publisher"));
        PRICE.setCellValueFactory(new PropertyValueFactory<Book,Double>("price"));
        TYPE.setCellValueFactory(new PropertyValueFactory<Book,String>("type"));
        quantityId.setCellValueFactory(new PropertyValueFactory<Book,Integer>("quantity"));
        introduction.setCellValueFactory(new PropertyValueFactory<Book,String>("introduction"));
        
        tv.setItems(searchData);
    }
    
    @FXML
    private void handleDeleteAction(ActionEvent event){
        ObservableList<Book> bookSelect, allBooks;
        allBooks = tv.getItems();
        bookSelect = tv.getSelectionModel().getSelectedItems();
        
        if(bookSelect.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText("ERROR");
            alert.setContentText("Please select a book.");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Confirm Deletion");
            alert.setContentText("Are you sure you want to delete this book?");
            
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK){
                db.ManagerDeleteBook(bookSelect.get(0).getName()); //remove book to database
                bookSelect.forEach(allBooks::remove); //remove book to table view.
            }
            else{
                alert.close();
            }
        }
    }
    
    //Resize button when mouse move entered button.
    @FXML
    public void mouseEnteredSearch(MouseEvent e){
        Help.resizeButton(searchBt);
    }
    
    @FXML
    public void mouseExitedSearch(MouseEvent e){
        Help.reverseButtonSize(searchBt);
    }
    
    @FXML
    public void mouseEnteredDelete(MouseEvent e){
        Help.resizeButton(deleteBt);
    }
    
    @FXML
    public void mouseExitedDelete(MouseEvent e){
        Help.reverseButtonSize(deleteBt);
    }
    
     @FXML
    public void mouseEnteredSave(MouseEvent e){
        Help.resizeButton(saveBt);
    }
    
    @FXML
    public void mouseExitedSave(MouseEvent e){
        Help.reverseButtonSize(saveBt);
    }
     @FXML
    public void mouseEnteredClear(MouseEvent e){
        Help.resizeButton(clearBt);
    }
    
    @FXML
    public void mouseExitedClear(MouseEvent e){
        Help.reverseButtonSize(clearBt);
    }
     @FXML
    public void mouseEnteredLogout(MouseEvent e){
        Help.resizeButton(logoutBt);
    }
    
    @FXML
    public void mouseExitedLogout(MouseEvent e){
        Help.reverseButtonSize(logoutBt);
    }
     @FXML
    public void mouseEnteredHome(MouseEvent e){
        Help.resizeButton(homeBt);
    }
    
    @FXML
    public void mouseExitedHome(MouseEvent e){
        Help.reverseButtonSize(homeBt);
    }
    
    
}
