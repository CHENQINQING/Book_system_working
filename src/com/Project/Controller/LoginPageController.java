/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project.Controller;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author chenq
 */
public class LoginPageController implements Initializable {
    @FXML private Tab searchTab, loginTab;
    @FXML private TabPane tabPane;
    @FXML private Button searchBt, loginBt;
    @FXML private ComboBox combo,loginCombo;
    @FXML private TextField username, password;
    
    //ObservableList used for holding comboBox value.
    private ObservableList<String> list = FXCollections.observableArrayList("Name","Author","Publisher");
    private ObservableList<String> loginList = FXCollections.observableArrayList("Manager","Member");
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setComboBoxValue();
    }
    
    private void setComboBoxValue(){
        //set comboBox value.
        combo.setValue("Name");
        combo.setItems(list);
        //set combo value to log in type.
        loginCombo.setItems(loginList);
    }
    
    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        String uname = username.getText();
        String pwd = password.getText();
        
        //Check if username and password belongs to admin or members
        if(uname.equals("a") && pwd.equals("a")) {
            visitManager(event);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Customer scene has not been created. Please enter 'a','a' to log in as manager.");
            alert.show();
            //visitCustomer(event);
        }
        
    }
    
    @FXML
    public void searchBook(ActionEvent event) throws IOException{
        /*Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(""));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();*/
    }
    
    //go to manager page.
    private void visitManager(ActionEvent event) throws IOException{
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/Project/FXML/Menu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Menu");
        stage.show();
    }
    
    private void visitCustomer(ActionEvent event) throws IOException{
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(""));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    
}
