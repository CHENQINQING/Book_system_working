/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project.Controller;

import classes.BookStorage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author liushuai
 */
public class MenuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML    
    public void logout(ActionEvent event) throws IOException{
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/Project/FXML/LoginPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login page");
        stage.show();
    }
    @FXML    
    public void visitBookType(ActionEvent event) throws IOException{
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/Project/FXML/BookType.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Book type");
        stage.show();
    }
    
    @FXML    
    public void visitBookPublisher(ActionEvent event) throws IOException{
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/Project/FXML/BookPublisher.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Book Publisher");
        stage.show();
    }
    @FXML    
    public void Feedback(ActionEvent event) throws IOException{
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/Project/FXML/Feedback.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Feedback");
        stage.show();
    }
    @FXML    
    public void Bookmanaging(ActionEvent event) throws IOException{
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/Project/FXML/BookManagingPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Book Management");
        stage.show();
    }
    @FXML    
    public void sale(ActionEvent event) throws IOException{
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/Project/FXML/sales_manage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Book sale management");
        stage.show();
    }
    @FXML    
    public void resperoty(ActionEvent event) throws IOException{
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/Project/FXML/resperoty_management_interface.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Book resperoty management");
        stage.show();
    }
    
    @FXML
    public void logoutAction(ActionEvent event) throws IOException{
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/Project/FXML/LoginPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
    @FXML    
    public void calculator(ActionEvent event) throws IOException{
        //Node node = (Node) event.getSource();
        //Stage stage = (Stage) node.getScene().getWindow();
        Stage SecStage = new Stage();
        Parent SecRoot = FXMLLoader.load(getClass().getResource("/calculator/calculator.fxml"));
        Scene SecScene = new Scene(SecRoot);
        SecStage.setScene(SecScene);
        SecStage.setTitle("calculator");
        SecStage.show();
    }
    @FXML
    public void SearchBook(ActionEvent event) throws IOException{
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/Project/FXML/SearchBook.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}
