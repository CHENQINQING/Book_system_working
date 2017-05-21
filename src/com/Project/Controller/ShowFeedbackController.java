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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    
    private void getComboBoxValue(){
        jcdb.fillPublisherCombo(titleList);
        //set comboBox value.
        titleCombo.setItems(titleList);
    }
    
//    private void getFeedbackData() {
//       feedbackData=FXCollections.observableArrayList();
//       ResultSet rs = jcdb.ManageRitriveFeedback();
//       Feedback feed = null;
//        try {
//            while(rs.next()){
//                System.out.println(rs.getString("title"));
//                System.out.println(rs.getString("body"));
//                System.out.println(rs.getString("datetime"));
//                feed = new Feedback(rs.getString("title"));
//               // feedbackData.add(feed);
//            }
//        } catch (SQLException ex) {
//            System.out.println("Error "+ ex);
//        }        
//        table.setItems(feedbackData);
//    }
    
}
