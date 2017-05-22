/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project.Controller;

import classes.Feedback;
import classes.Help;
import classes.LoginStorage;
import database.JCDB;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
    
    private ObservableList<Feedback>feedbackData;

    private Feedback feedback = new Feedback();

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
    private void ButtonSave(ActionEvent event) throws SQLException,IOException{
        SaveToDatabase(event);
    }
    
    private void SaveToDatabase(ActionEvent event) throws SQLException, NumberFormatException, IOException{        
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
        else{
            feedback.setTitle(titleText.getText());
            feedback.setBody(bodyText.getText());
            feedback.setDatetime(currentDate());
            
            jcdb.employeeSaveFeedback(feedback);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("FEEDBACK SUCCEED");
            alert.setContentText("Thanks For Your Suggestion, We Have Received Your Feedback.");
            alert.showAndWait();

            titleText.setText("");
            bodyText.setText("");
            ButtonMeun(event);
        }
    }    
    private LocalDate currentDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDate localDate = LocalDate.now();
        return localDate;
    }
}
