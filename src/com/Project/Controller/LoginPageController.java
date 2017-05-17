/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project.Controller;

import classes.Help;
import classes.LoginStorage;
import database.JCDB;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.ScaleTransition;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author chenq
 */
public class LoginPageController implements Initializable {
    @FXML private Tab searchTab, loginTab;
    @FXML private TabPane tabPane;
    @FXML private Button searchBt, loginBt;
    @FXML private ComboBox combo,loginCombo;
    @FXML private TextField username, password,searchField;
    
    //ObservableList used for holding comboBox value.
    private ObservableList<String> list = FXCollections.observableArrayList("Name","Author","Publisher");
    private ObservableList<String> loginList = FXCollections.observableArrayList("employee","member");
    
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
    private void handleLogin(ActionEvent event) throws IOException, SQLException {
        String uname = username.getText();
        String pword = password.getText();
        
        if(!uname.isEmpty()&&!pword.isEmpty()&&loginCombo.getSelectionModel().getSelectedItem()!=null){
            String loginType = loginCombo.getSelectionModel().getSelectedItem().toString();
            int id;
            boolean validUsername = JCDB.verifyAccount(uname, loginType);
            if(!validUsername){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("ERROR");
                alert.setContentText("Account does not exists");
                alert.showAndWait();
            }else{
                id = JCDB.getId(uname, pword, loginType);
                if(id == 0){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("ERROR");
                    alert.setContentText("Wrong Password");
                    alert.showAndWait();
                }else{
                    if(loginType.equals("employee")){
                        LoginStorage.getInstance().setUsername(uname);
                        LoginStorage.getInstance().setId(id);
                        LoginStorage.getInstance().setAccountType("employee");
                        visitEmployee(event);
                    }
                    else if(loginType.equals("member")){
                        LoginStorage.getInstance().setUsername(uname);
                        LoginStorage.getInstance().setId(id);
                        LoginStorage.getInstance().setAccountType("member");
                        visitCustomer(event);
                    }
                }
            }
            
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("Username, password or login type cannot be empty");
            alert.showAndWait();
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
    private void visitEmployee(ActionEvent event) throws IOException{
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
    
    
    //Resize button when mouse move entered button.
    //user search page
    @FXML
    public void mouseEnteredSearch(MouseEvent e){
        Help.resizeButton(searchField);
    }
    
    @FXML
    public void mouseExitedSearch(MouseEvent e){
        Help.reverseButtonSize(searchField);
    }
    
    @FXML
    public void mouseEnteredCombo(MouseEvent e){
        Help.resizeButton(combo);
    }
    
    @FXML
    public void mouseExitedCombo(MouseEvent e){
        Help.reverseButtonSize(combo);
    }
    
    @FXML
    public void mouseEnteredSearchBt(MouseEvent e){
        Help.resizeButton(searchBt);
    }
    
    @FXML
    public void mouseExitedSearchBt(MouseEvent e){
        Help.reverseButtonSize(searchBt);
    }
    
    //Resize button when mouse move entered button.
    //loging page
    @FXML
    public void mouseEnteredUsername(MouseEvent e){
        Help.resizeButton(username);
    }
    
    @FXML
    public void mouseExitedUsername(MouseEvent e){
        Help.reverseButtonSize(username);
    }
    
    @FXML
    public void mouseEnteredPassword(MouseEvent e){
        Help.resizeButton(password);
    }
    
    @FXML
    public void mouseExitedPassword(MouseEvent e){
        Help.reverseButtonSize(password);
    }
    
    @FXML
    public void mouseEnteredLoginCombo(MouseEvent e){
        Help.resizeButton(loginCombo);
    }
    
    @FXML
    public void mouseExitedLoginCombo(MouseEvent e){
        Help.reverseButtonSize(loginCombo);
    }
    
    @FXML
    public void mouseEnteredLoginBt(MouseEvent e){
        Help.resizeButton(loginBt);
    }
    
    @FXML
    public void mouseExitedLoginBt(MouseEvent e){
        Help.reverseButtonSize(loginBt);
    }
    
}
