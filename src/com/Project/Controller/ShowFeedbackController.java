/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project.Controller;

import classes.Feedback;
import classes.Type;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author liushuai
 */
public class ShowFeedbackController implements Initializable {
    JCDB jcdb =new JCDB();
    @FXML
    private TextArea bodyText;
    @FXML
    private TextField dateText;
    
    @FXML
    private ComboBox titleCombo;

    private ObservableList<String> titleList = FXCollections.observableArrayList();
    
    private ObservableList<Feedback> feedbackData;
    @FXML
    private TextField useridText;
    
    private Feedback feedback = new Feedback();
    @FXML
    private Label status;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //getFeedbackData();
        getComboBoxValue();
    }    
    @FXML
    private void ButtonMeun(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/Project/FXML/ManagerMenu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Menu");
        stage.show();
    }
    @FXML    
    public void ButtonRead(ActionEvent event) throws IOException{
        getFeedbackData(titleCombo.getSelectionModel().getSelectedItem().toString()); 
    }
    @FXML    
    public void ButtonDelete(ActionEvent event) throws IOException{
        Deletefeedback(titleCombo.getSelectionModel().getSelectedItem().toString()); 
        titleCombo.setValue("choise title");
        bodyText.setText("");
        dateText.setText("");
        useridText.setText("");
        status.setText("");
    }
    
    private void getComboBoxValue(){
        jcdb.fillTitleCombo(titleList);
        //set comboBox value.
        titleCombo.setValue("choise title");
        titleCombo.setItems(titleList);
    }
    
    private void getFeedbackData(String title) {
       feedbackData=FXCollections.observableArrayList();
       if(title=="choise title"){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText("ERROR");
            alert.setContentText("Please select a title.");
            alert.showAndWait();
        }
        else{
           
           ResultSet rs = jcdb.ManagerRitriveFeedback( title );
           try {
               while(rs.next()){
                   System.out.println(rs.getInt("feedback_id"));
                   System.out.println(rs.getString("body"));
                   System.out.println(rs.getString("status"));
                   
                   feedback.setId(rs.getInt("feedback_id"));
                   bodyText.setText(rs.getString("body"));
                   System.out.println(rs.getDate("date").toString());
                   dateText.setText(rs.getDate("date").toString());
                   useridText.setText(rs.getString("user_userId"));
                   
                   if(rs.getString("status") == null){
                       status.setText(" ");
                   }
                   else{
                       status.setText("The user feels: "+rs.getString("status"));
                   }
                   
               }
           } catch (SQLException ex) {
               System.out.println("Error "+ ex);
           }        
        }
    }

    private void Deletefeedback(String title){
        feedbackData=FXCollections.observableArrayList();
        if(title=="choise title"){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText("ERROR");
            alert.setContentText("Please select a title.");
            alert.showAndWait();
        }
         else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Confirm Deletion");
            alert.setContentText("Are you sure you want to delete this feedback?");
            
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK){
                jcdb.ManagerDeleteFeedback(feedback.getId()); //remove publisher to database
            }
            else{
                alert.close();
            }
        }
    }
}
