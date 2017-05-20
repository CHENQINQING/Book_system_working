/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project.Controller;

import classes.Help;
import classes.User;
import classes.UserStorage;
import database.JCDB;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
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
    @FXML private RadioButton nameless,sighName;
    
    private JCDB db = new JCDB();
    private User user = new User();
    private Help help = new Help();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //name.setEditable(false);
    }    
    
    @FXML
    private void handleOkAction(ActionEvent event) throws IOException, SQLException{
        if(!name.getText().isEmpty() /*nameless.isSelected() || sighName.isSelected()*/){
            /*if(nameless.isSelected()){
                    UserStorage.getInstance().setName("an");
                
                    user.setName("an");
                    user.setLevel(3);
                    user.setUsername(" ");
                    user.setPassword(" ");
                    db.createCustomerID(user);
                    System.out.println("customer id success");
                
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("/com/Project/FXML/CustomerFeedback.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
            }
            else{
                if(name.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("ERROR");
                    alert.setContentText("Please enter your name");
                    alert.showAndWait();
                }
                else{
                    if(db.verifyCustomer(name.getText())){
                        System.out.println("This customer is already in database.");
                
                        UserStorage.getInstance().setName(name.getText());
                
                        Node node = (Node) event.getSource();
                        Stage stage = (Stage) node.getScene().getWindow();
                        Parent root = FXMLLoader.load(getClass().getResource("/com/Project/FXML/CustomerFeedback.fxml"));
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    }
                    else{
                        UserStorage.getInstance().setName(name.getText());
                
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
            }*/
            
            if(db.verifyCustomer(name.getText())){
                System.out.println("This customer is already in database.");
                
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Name Repeat");
                alert.setContentText("Your name has been used. Have you ever used this name?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    UserStorage.getInstance().setName(name.getText());
                
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("/com/Project/FXML/CustomerFeedback.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setHeaderText("Important Information");
                    alert2.setContentText("Please change your name");
                    alert2.showAndWait();
                }
                
                
            }
            else{
                UserStorage.getInstance().setName(name.getText());
                
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
            alert.setContentText("Please enter your name");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleNamelessAction(ActionEvent event){
//        name.clear();
//        name.setEditable(false);
    }
    
    @FXML
    private void handleSighNameAction(ActionEvent event){
//        name.setEditable(true);
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
        help.resizeButton(name);
    }
    
    @FXML
    public void mouseExitedName(MouseEvent e){
        help.reverseButtonSize(name);
    }
    
    @FXML
    public void mouseEnteredOk(MouseEvent e){
        help.resizeButton(ok);
    }
    
    @FXML
    public void mouseExitedOk(MouseEvent e){
        help.reverseButtonSize(ok);
    }
    
    @FXML
    public void mouseEnteredCanel(MouseEvent e){
        help.resizeButton(cancel);
    }
    
    @FXML
    public void mouseExitedCanel(MouseEvent e){
        help.reverseButtonSize(cancel);
    }
    
}
