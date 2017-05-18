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
import java.sql.ResultSet;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Xuantong
 */
public class BookSearchedDetialController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField bookNameTf1;
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
    private Button searchBt,resetBt,saveBt,homeBt;
    
    private JCDB db = new JCDB();
    
    //ObservableList used for holding Book values.
    private ObservableList<Book> bookData;
    private ObservableList<Book> searchData;
    //ObservableList used for showNewBook method.
    private ObservableList<Book> show;

    private Book intro;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(BookStorage.getInstance().getName());
        //bookNameTf1.textProperty().bind(BookStorage.getInstance().nameProperty());
        
        getBookData();
    }
    
    private void refresh(){
        bookData.clear();
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
    
    private void getBookData(){
        
        try {
            bookData = FXCollections.observableArrayList();
            ResultSet rs = db.searchingAllBook(BookStorage.getInstance().getName());
            
            while(rs.next()){
                System.out.println(rs.getString("book_name"));
                System.out.println(rs.getString("Pub_name"));
                System.out.println(rs.getString("author"));
                System.out.println(rs.getDouble("price"));
                System.out.println(rs.getString("type"));
                
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
    private void handleLogoutAction(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/Project/FXML/LoginPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void handleResetAction(ActionEvent event){
        bookData.clear();
        refresh();
    }
    
    @FXML
    private void handleSearchAction(ActionEvent event){
        if(bookNameTf1.getText().isEmpty()){
            refresh();
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
    public void mouseEnteredHome(MouseEvent e){
        Help.resizeButton(homeBt);
    }
    
    @FXML
    public void mouseExitedHome(MouseEvent e){
        Help.reverseButtonSize(homeBt);
    }
    
    @FXML
    public void mouseEnteredReset(MouseEvent e){
        Help.resizeButton(resetBt);
    }
    
    @FXML
    public void mouseExitedReset(MouseEvent e){
        Help.reverseButtonSize(resetBt);
    }
    
    @FXML
    public void mouseEnteredField(MouseEvent e){
        Help.resizeButton(bookNameTf1);
    }
    
    @FXML
    public void mouseExitedField(MouseEvent e){
        Help.reverseButtonSize(bookNameTf1);
    }
}
