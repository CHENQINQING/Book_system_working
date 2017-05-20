/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project.Controller;

import classes.Help;
import classes.User;
import database.JCDB;
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Xuantong
 */
public class CustomerFeedbackInfoController implements Initializable {

    @FXML private TextField name;
    @FXML private Button ok,cancel;
    
    private JCDB db = new JCDB();
    private User user = new User();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void handleOkAction(ActionEvent event) throws IOException, SQLException{
        if(!name.getText().isEmpty()){
            if(db.verifyCustomer(name.getText())){
                System.out.println("This customer is already in database.");
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("/com/Project/FXML/CustomerFeedback.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else{
                user.setName(name.getText());
                user.setLevel(3);
                user.setUsername(" ");
                user.setPassword(" ");
                db.createCustomerID(user);
                
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("/com/Project/FXML/CustomerFeedback.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("Please enter your name again");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleCancelAction(ActionEvent event) throws IOException{
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/Project/FXML/LoginPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    //Resize button when mouse move entered button.
    //user search page
    @FXML
    public void mouseEnteredName(MouseEvent e){
        Help.resizeButton(name);
    }
    
    @FXML
    public void mouseExitedName(MouseEvent e){
        Help.reverseButtonSize(name);
    }
    
    @FXML
    public void mouseEnteredOk(MouseEvent e){
        Help.resizeButton(ok);
    }
    
    @FXML
    public void mouseExitedOk(MouseEvent e){
        Help.reverseButtonSize(ok);
    }
    
    @FXML
    public void mouseEnteredCanel(MouseEvent e){
        Help.resizeButton(cancel);
    }
    
    @FXML
    public void mouseExitedCanel(MouseEvent e){
        Help.reverseButtonSize(cancel);
    }
    
}
