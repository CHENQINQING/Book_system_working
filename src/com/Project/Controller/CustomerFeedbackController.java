/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project.Controller;

import classes.Book;
import classes.Help;
import classes.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author liushuai
 */
public class CustomerFeedbackController implements Initializable {

    @FXML
    private TextArea body;
    @FXML
    private TextField title,name;
    @FXML
    private Button sad,normal,good,save,clear,home;
    
    private User user = new User();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    @FXML
    private void ButtonClear(ActionEvent event) {
        title.setText("");
        body.setText("");
    }
    @FXML
    private void ButtonMeun(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/Project/FXML/LoginPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Menu");
        stage.show();
    }
    
    @FXML
    private void handleSaveAction(ActionEvent event) throws SQLException{
        SaveToDatabase();
    }
    
    private void SaveToDatabase() throws SQLException, NumberFormatException {
        
    }
    
    
    
    //Resize button when mouse move entered button.
    //user search page
    @FXML
    public void mouseEnteredTitle(MouseEvent e){
        Help.resizeButton(title);
    }
    
    @FXML
    public void mouseExitedTitle(MouseEvent e){
        Help.reverseButtonSize(title);
    }
    
    @FXML
    public void mouseEnteredSad(MouseEvent e){
        Help.resizeButton(sad);
    }
    
    @FXML
    public void mouseExitedSad(MouseEvent e){
        Help.reverseButtonSize(sad);
    }
    
    @FXML
    public void mouseEnteredNormal(MouseEvent e){
        Help.resizeButton(normal);
    }
    
    @FXML
    public void mouseExitedNormal(MouseEvent e){
        Help.reverseButtonSize(normal);
    }

    @FXML
    public void mouseEnteredGood(MouseEvent e){
        Help.resizeButton(good);
    }
  
    @FXML
    public void mouseExitedGood(MouseEvent e){
        Help.reverseButtonSize(good);
    }
    
    @FXML
    public void mouseEnteredSave(MouseEvent e){
        Help.resizeButton(save);
    }
    
    @FXML
    public void mouseExitedSave(MouseEvent e){
        Help.reverseButtonSize(save);
    }
    
    @FXML
    public void mouseEnteredClear(MouseEvent e){
        Help.resizeButton(clear);
    }
    
    @FXML
    public void mouseExitedClear(MouseEvent e){
        Help.reverseButtonSize(clear);
    }
    
    @FXML
    public void mouseEnteredHome(MouseEvent e){
        Help.resizeButton(home);
    }
    
    @FXML
    public void mouseExitedHome(MouseEvent e){
        Help.reverseButtonSize(home);
    }
    
    /*@FXML
    public void mouseEnteredName(MouseEvent e){
        Help.resizeButton(name);
    }
    
    @FXML
    public void mouseExitedName(MouseEvent e){
        Help.reverseButtonSize(name);
    }*/
}
