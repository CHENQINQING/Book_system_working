/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project.Controller;

import classes.Help;
import classes.LoginStorage;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author liushuai
 */
public class ManagerMenuController implements Initializable {

    @FXML private Label loginAs;
    @FXML private Button searchBt,addBt,feedbackBt,salesBt,reperotyBt,calculatorBt,typeBt,publisherBt,accountBt,logoutBt;
    private Help help = new Help();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginAs.setText("Login As: " + LoginStorage.getInstance().getUsername());
    }

    @FXML    
    public void search(ActionEvent event) throws IOException{
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/Project/FXML/.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Search page");
        stage.show();
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
    public void feedback(ActionEvent event) throws IOException{
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
    public void account(ActionEvent event) throws IOException{
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/Project/FXML/.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Account page");
        stage.show();
    }
    
    
    //Resize button when mouse move entered button.
    @FXML
    public void mouseEnteredSearch(MouseEvent e){
        help.resizeButton(searchBt);
    }
    
    @FXML
    public void mouseExitedSearch(MouseEvent e){
        help.reverseButtonSize(searchBt);
    }
    
    @FXML
    public void mouseEnteredAdd(MouseEvent e){
        help.resizeButton(addBt);
    }
    
    @FXML
    public void mouseExitedAdd(MouseEvent e){
        help.reverseButtonSize(addBt);
    }
    
    @FXML
    public void mouseEnteredFeedback(MouseEvent e){
        help.resizeButton(feedbackBt);
    }
    
    @FXML
    public void mouseExitedFeedback(MouseEvent e){
        help.reverseButtonSize(feedbackBt);
    }
    
    @FXML
    public void mouseEnteredSales(MouseEvent e){
        help.resizeButton(salesBt);
    }
    
    @FXML
    public void mouseExitedSales(MouseEvent e){
        help.reverseButtonSize(salesBt);
    }
    
    //Resize button when mouse move entered button.
    @FXML
    public void mouseEnteredQuantity(MouseEvent e){
        help.resizeButton(reperotyBt);
    }
    
    @FXML
    public void mouseExitedQuantity(MouseEvent e){
        help.reverseButtonSize(reperotyBt);
    }
    
    @FXML
    public void mouseEnteredLogout(MouseEvent e){
        help.resizeButton(logoutBt);
    }
    
    @FXML
    public void mouseExitedLogout(MouseEvent e){
        help.reverseButtonSize(logoutBt);
    }
    
    @FXML
    public void mouseEnteredCalculator(MouseEvent e){
        help.resizeButton(calculatorBt);
    }
    
    @FXML
    public void mouseExitedCalculator(MouseEvent e){
        help.reverseButtonSize(calculatorBt);
    }
    
    @FXML
    public void mouseEnteredType(MouseEvent e){
        help.resizeButton(typeBt);
    }
    
    @FXML
    public void mouseExitedType(MouseEvent e){
        help.reverseButtonSize(typeBt);
    }
    
    @FXML
    public void mouseEnteredPublisher(MouseEvent e){
        help.resizeButton(publisherBt);
    }
    
    @FXML
    public void mouseExitedPublisher(MouseEvent e){
        help.reverseButtonSize(publisherBt);
    }
    
    @FXML
    public void mouseEnteredAccount(MouseEvent e){
        help.resizeButton(accountBt);
    }
    
    @FXML
    public void mouseExitedAccount(MouseEvent e){
        help.reverseButtonSize(accountBt);
    }
}
