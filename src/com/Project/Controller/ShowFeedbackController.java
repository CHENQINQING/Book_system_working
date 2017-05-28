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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author liushuai
 */
public class ShowFeedbackController implements Initializable {
    private JCDB db =new JCDB();
    private ObservableList<String> titleList = FXCollections.observableArrayList();
    private Feedback feedback = new Feedback();
    
    @FXML
    private TextArea bodyText;
    @FXML
    private TextField dateText,emailTf;
    @FXML
    private ListView listView;
    @FXML
    private TextField userText;
    @FXML
    private Label status;
    @FXML
    ComboBox multiple;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          getListView();
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
    public void readFeedback(MouseEvent event){
        getFeedbackData();
    }
    
    @FXML
    public void readMutipleFeedback(ActionEvent event){
        try {
            db.readMultipleFeedback(multiple, userText, dateText, bodyText, status,emailTf);
        } catch (Exception e) {
            System.out.println("readMultiple error: "+e);
        }
    }
    
    @FXML    
    public void ButtonDelete(ActionEvent event) throws IOException{
        Deletefeedback();
    }
    
    private void getListView(){
        db.fillTitle(titleList);
        listView.setItems(titleList);
    }
    
    private void getFeedbackData(){
        try {
            String title = listView.getSelectionModel().getSelectedItem().toString();
            db.ManagerRitriveFeedback(title,bodyText,dateText,userText,status,feedback,multiple,emailTf);
        } catch (Exception e) {
            System.out.println("listView no value in here");
        }
    }

    private void Deletefeedback(){
        String title = listView.getSelectionModel().getSelectedItem().toString();
        if(title.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText("ERROR");
            alert.setContentText("Please select a title.");
            alert.showAndWait();
        }
        else{
            ButtonType delete = new ButtonType("DELETE", ButtonBar.ButtonData.OK_DONE);
            ButtonType back = new ButtonType("BACK", ButtonBar.ButtonData.CANCEL_CLOSE);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you want to delete this feedback?",delete,back);
            alert.setHeaderText("Confirm Deletion");
            
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() && result.get() == delete){
                db.ManagerDeleteFeedback(feedback.getId()); //remove feedback from database
                
                bodyText.clear();
                dateText.clear();
                userText.clear();
                System.out.println("Remove success");
                listView.getItems().clear();
                getListView();
            }
            else{
                alert.close();
            }
        }
    }
}
