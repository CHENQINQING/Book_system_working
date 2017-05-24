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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.control.ButtonBar;
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

    @FXML private TextField name,emailTf;
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
        if(!name.getText().isEmpty()){
            if(db.verifyCustomer(name.getText())){
                System.out.println("This customer is already in database.");
                
                ButtonType yes = new ButtonType("YES", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);
                Alert alert = new Alert(AlertType.CONFIRMATION, "Your name has been used. Have you ever created this name before?", yes,no);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Name Repeat");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == yes){
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
            else if(!emailTf.getText().isEmpty()){
                if(!checkEmail(emailTf.getText())){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Email ERROR");
                    alert.setContentText("Email address is incorrect.");
                    alert.showAndWait();
                }
                else{
                    UserStorage.getInstance().setName(name.getText());
                
                    user.setName(name.getText());
                    user.setLevel(3);
                    user.setUsername(" ");
                    user.setPassword(" ");
                    user.setEmail(emailTf.getText());
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
                UserStorage.getInstance().setName(name.getText());
                user.setName(name.getText());
                user.setLevel(3);
                user.setUsername(" ");
                user.setPassword(" ");
                user.setEmail(" ");
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
    
    private static boolean checkEmail(String emaile){
        String RULE_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        //正则表达式的模式
        Pattern p = Pattern.compile(RULE_EMAIL);
        //正则表达式的匹配器
        Matcher m = p.matcher(emaile);
        //进行正则匹配
        return m.matches();
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
    
    @FXML
    public void mouseEnteredEmail(MouseEvent e){
        help.resizeButton(emailTf);
    }
    
    @FXML
    public void mouseExitedEmail(MouseEvent e){
        help.reverseButtonSize(emailTf);
    }
}
