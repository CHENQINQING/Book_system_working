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
    @FXML private ComboBox combo;
    @FXML private TextField username, password;
    
    //ObservableList used for holding comboBox value.
    private ObservableList<String> list = FXCollections.observableArrayList("Name","Author","Publisher");
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //set comboBox value.
        combo.setValue("Name");
        combo.setItems(list);
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
            System.out.println("None");
            //visitCustomer(event);
        }
        
    }
    
    //go to manager page.
    private void visitManager(ActionEvent event) throws IOException{
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/Project/FXML/BookManagingPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("");
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
