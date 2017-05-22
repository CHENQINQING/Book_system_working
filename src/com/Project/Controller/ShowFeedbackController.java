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
import javafx.scene.control.ComboBox;
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
        System.out.println(titleCombo.getSelectionModel().getSelectedItem().toString());
        getFeedbackData(titleCombo.getSelectionModel().getSelectedItem().toString()); 
    }
    
    private void getComboBoxValue(){
        jcdb.fillTitleCombo(titleList);
        //set comboBox value.
        titleCombo.setValue("choise title");
        titleCombo.setItems(titleList);
    }
    
    private void getFeedbackData(String title) {
       feedbackData=FXCollections.observableArrayList();
       ResultSet rs = jcdb.ManageRitriveFeedback( title );
//       Feedback feed = null;
        try {
            while(rs.next()){
                System.out.println(rs.getString("body"));
                bodyText.setText(rs.getString("body"));
                System.out.println(rs.getString("datetime"));
                dateText.setText(rs.getString("datetime"));
            }
        } catch (SQLException ex) {
            System.out.println("Error "+ ex);
        }        

    }
    
}
