/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project.Controller;

import classes.Book;
import classes.Feedback;
import classes.Help;
import classes.User;
import classes.UserStorage;
import database.JCDB;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.RadioButton;
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
    private Help help = new Help();
    private Feedback feedback = new Feedback();
    private JCDB db = new JCDB();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("user storage: " + UserStorage.getInstance().nameProperty());
    }    
    @FXML
    private void ButtonClear(ActionEvent event) {
        title.setText("");
        body.setText("");
    }
    @FXML
    private void ButtonMeun(ActionEvent event) throws IOException {
        BackToHome(event);
    }

    private void BackToHome(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/Project/FXML/LoginPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Menu");
        stage.show();
    }
    
    @FXML
    private void handleSaveAction(ActionEvent event) throws SQLException, NumberFormatException, IOException{
        SaveToDatabase(event);
    }
    
    private void SaveToDatabase(ActionEvent event) throws SQLException, NumberFormatException, IOException {
        if(!title.getText().isEmpty() && !body.getText().isEmpty()){
            if(title.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("ERROR");
                alert.setContentText("Please enter a title");
                alert.showAndWait();
            }
            else if(body.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("ERROR");
                alert.setContentText("Please enter contents");
                alert.showAndWait();
            }
            else{
                feedback.setTitle(title.getText());
                feedback.setBody(body.getText());
                feedback.setDatetime(currentDate());
                
                db.customerFeedback(feedback);
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("FEEDBACK RECEIVED");
                alert.setContentText("Thanks For Your Suggestion, We Have Received Your Feedback");
                alert.showAndWait();
                
                title.setText("");
                body.setText("");
                BackToHome(event);
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("Please enter your feedback");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleSadAction(ActionEvent event){
        feedback.setStatus("Not happy with our service");
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("FEEDBACK RECEIVED");
        alert.setContentText("Thanks For Your Suggestion, We Will Improve Our Service");
        alert.showAndWait();
    }
    
    @FXML
    private void handleNormalAction(ActionEvent event){
        feedback.setStatus("It is okay with our service");
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("FEEDBACK RECEIVED");
        alert.setContentText("Thanks For Your Suggestion, We Will Improve Our Service");
        alert.showAndWait();
    }
    
    @FXML
    private void handleGoodAction(ActionEvent event){
        feedback.setStatus("Very satisfied with our service");
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("FEEDBACK RECEIVED");
        alert.setContentText("Thanks For Your Suggestion, Enjoy Your Time");
        alert.showAndWait();
    }
    
    private LocalDate currentDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDate localDate = LocalDate.now();
        return localDate;
    }
    
    
    //Resize button when mouse move entered button.
    //user search page
    @FXML
    public void mouseEnteredTitle(MouseEvent e){
        help.resizeButton(title);
    }
    
    @FXML
    public void mouseExitedTitle(MouseEvent e){
        help.reverseButtonSize(title);
    }
    
    @FXML
    public void mouseEnteredSad(MouseEvent e){
        help.resizeButton(sad);
    }
    
    @FXML
    public void mouseExitedSad(MouseEvent e){
        help.reverseButtonSize(sad);
    }
    
    @FXML
    public void mouseEnteredNormal(MouseEvent e){
        help.resizeButton(normal);
    }
    
    @FXML
    public void mouseExitedNormal(MouseEvent e){
        help.reverseButtonSize(normal);
    }

    @FXML
    public void mouseEnteredGood(MouseEvent e){
        help.resizeButton(good);
    }
  
    @FXML
    public void mouseExitedGood(MouseEvent e){
        help.reverseButtonSize(good);
    }
    
    @FXML
    public void mouseEnteredSave(MouseEvent e){
        help.resizeButton(save);
    }
    
    @FXML
    public void mouseExitedSave(MouseEvent e){
        help.reverseButtonSize(save);
    }
    
    @FXML
    public void mouseEnteredClear(MouseEvent e){
        help.resizeButton(clear);
    }
    
    @FXML
    public void mouseExitedClear(MouseEvent e){
        help.reverseButtonSize(clear);
    }
    
    @FXML
    public void mouseEnteredHome(MouseEvent e){
        help.resizeButton(home);
    }
    
    @FXML
    public void mouseExitedHome(MouseEvent e){
        help.reverseButtonSize(home);
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
