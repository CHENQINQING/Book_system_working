/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project.Controller;

import classes.Feedback;
import classes.Help;
import database.JCDB;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author liushuai
 */
public class FeedbackController implements Initializable {

    JCDB jcdb=new JCDB();
    @FXML
    private TextArea bodyText;
    @FXML
    private TextField titleText;
    @FXML
    private TextField emailText;
    
    private ObservableList<Feedback>feedbackData;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void ButtonClear(ActionEvent event) {
        titleText.setText("");
        bodyText.setText("");
        emailText.setText("");
    }
    @FXML
    private void ButtonMeun(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/Project/FXML/Menu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Menu");
        stage.show();
    }
    @FXML
    private void handleSaveAction(ActionEvent event) throws SQLException{
        SaveToDatabase();
        titleText.setText("");
        bodyText.setText("");
        emailText.setText("");
    }
    
    private void SaveToDatabase() throws SQLException, NumberFormatException {        
        if(titleText.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("INVALID TITLE.");
            alert.show();
        }
        else if(bodyText.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("INVALID BODY.");
            alert.show();
        }
        else if(emailText.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("INVALID E-MAIL.");
            alert.show();
        }
        else{
//            Feedback feed = new Feedback(
//                    titleText.getText(), 
//                    bodyText.getText(), 
//                    emailText.getText());
            
//            jcdb.managerSaveFeedback(feed);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("SAVED");
            alert.setContentText("The Data Has Been Saved");
            alert.show();
        }
    }
    
}
