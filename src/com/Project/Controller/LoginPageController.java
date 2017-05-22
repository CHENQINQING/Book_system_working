/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project.Controller;

import classes.BookStorage;
import classes.Help;
import classes.LoginStorage;
import classes.User;
import database.JCDB;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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
    @FXML private Button searchBt, loginBt,main,comic,learning,leisure,bio,feedback;
    @FXML private ComboBox combo,loginCombo;
    @FXML private TextField username,searchField;
    @FXML private PasswordField password;
    @FXML private Label title;
    
    private JCDB db = new JCDB();
    private User user = new User();
    private Help help = new Help();
    
    //ObservableList used for holding comboBox value.
    //private ObservableList<String> list = FXCollections.observableArrayList("Name","Author","Publisher");
    private ObservableList<String> loginList = FXCollections.observableArrayList("Employee","Manager");
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setComboBoxValue();
    }
    
    private void setComboBoxValue(){
        //set comboBox value.
        //combo.setValue("Name");
        //combo.setItems(list);
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
            boolean validUsername = db.verifyAccount(uname, loginType);
            if(!validUsername){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("ERROR");
                alert.setContentText("Your Account or Password Is Incorrect");
                alert.showAndWait();
            }else{
                id = db.getId(uname, pword, loginType);
                if(id == 0){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("ERROR");
                    alert.setContentText("Your Account or Password Is Incorrect");
                    alert.showAndWait();
                }else{
                    if(loginType.equals("Employee")){
                        LoginStorage.getInstance().setUsername(uname);
                        LoginStorage.getInstance().setId(id);
                        LoginStorage.getInstance().setAccountType(2);
                        visitEmployee(event);
                    }
                    else if(loginType.equals("Manager")){
                        LoginStorage.getInstance().setUsername(uname);
                        LoginStorage.getInstance().setId(id);
                        LoginStorage.getInstance().setAccountType(1);
                        visitManager(event);
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
        if(searchField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("Search Field cannot be empty");
            alert.showAndWait();
        }else{
            BookStorage.getInstance().setName(searchField.getText());
            
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/com/Project/FXML/BookSearchedDetial.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    
    @FXML
    public void handleComicSearch(ActionEvent event) throws IOException{
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/Project/FXML/ComicSearch.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void handleBioSearch(ActionEvent event) throws IOException{
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/Project/FXML/BiographySearch.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void handleLeisureSearch(ActionEvent event) throws IOException{
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/Project/FXML/LeisureSearch.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void handleLearningSearch(ActionEvent event) throws IOException{
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/Project/FXML/LearningSearch.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
    
    private void visitManager(ActionEvent event) throws IOException{
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/Project/FXML/ManagerMenu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void handleFeedback(ActionEvent event) throws IOException{
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/Project/FXML/CustomerFeedbackInfo.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    //Resize button when mouse move entered button.
    //user search page
    @FXML
    public void mouseEnteredSearch(MouseEvent e){
        help.resizeButton(searchField);
    }
    
    @FXML
    public void mouseExitedSearch(MouseEvent e){
        help.reverseButtonSize(searchField);
    }
    
    @FXML
    public void mouseEnteredCombo(MouseEvent e){
        help.resizeButton(combo);
    }
    
    @FXML
    public void mouseExitedCombo(MouseEvent e){
        help.reverseButtonSize(combo);
    }
    
    @FXML
    public void mouseEnteredSearchBt(MouseEvent e){
        help.resizeButton(searchBt);
    }
    
    @FXML
    public void mouseExitedSearchBt(MouseEvent e){
        help.reverseButtonSize(searchBt);
    }
    
    //Resize button when mouse move entered button.
    //loging page
    @FXML
    public void mouseEnteredUsername(MouseEvent e){
        help.resizeButton(username);
    }
    
    @FXML
    public void mouseExitedUsername(MouseEvent e){
        help.reverseButtonSize(username);
    }
    
    @FXML
    public void mouseEnteredPassword(MouseEvent e){
        help.resizeButton(password);
    }
    
    @FXML
    public void mouseExitedPassword(MouseEvent e){
        help.reverseButtonSize(password);
    }
    
    @FXML
    public void mouseEnteredLoginCombo(MouseEvent e){
        help.resizeButton(loginCombo);
    }
    
    @FXML
    public void mouseExitedLoginCombo(MouseEvent e){
        help.reverseButtonSize(loginCombo);
    }
    
    @FXML
    public void mouseEnteredLoginBt(MouseEvent e){
        help.resizeButton(loginBt);
    }
    
    @FXML
    public void mouseExitedLoginBt(MouseEvent e){
        help.reverseButtonSize(loginBt);
    }
    
    @FXML
    public void mouseEnteredType(MouseEvent e){
        help.resizeButton(main);
    }
    
    @FXML
    public void mouseExitedType(MouseEvent e){
        help.reverseButtonSize(main);
    }
    
    @FXML
    public void mouseEnteredLearning(MouseEvent e){
        help.resizeButton(learning);
    }
    
    @FXML
    public void mouseExitedLearning(MouseEvent e){
        help.reverseButtonSize(learning);
    }
    
    @FXML
    public void mouseEnteredLeisure(MouseEvent e){
        help.resizeButton(leisure);
    }
    
    @FXML
    public void mouseExitedLeisure(MouseEvent e){
        help.reverseButtonSize(leisure);
    }
    
    @FXML
    public void mouseEnteredBio(MouseEvent e){
        help.resizeButton(bio);
    }
    
    @FXML
    public void mouseExitedBio(MouseEvent e){
        help.reverseButtonSize(bio);
    }
    
    @FXML
    public void mouseEnteredComic(MouseEvent e){
        help.resizeButton(comic);
    }
    
    @FXML
    public void mouseExitedComic(MouseEvent e){
        help.reverseButtonSize(comic);
    }
    
    @FXML
    public void mouseEnteredFeedback(MouseEvent e){
        help.resizeButton(feedback);
    }
    
    @FXML
    public void mouseExitedFeedback(MouseEvent e){
        help.reverseButtonSize(feedback);
    }
    
    
    
    @FXML
    public void handleMoreButton(MouseEvent e){
        categoryMenuBar(learning, 500);
        categoryMenuBar(leisure, 400);
        categoryMenuBar(bio, 300);
        categoryMenuBar(comic, 200);
    }
    
   @FXML
   public void handleMoveBack(MouseEvent e){
       categoryMenuBarReverse(learning, 0);
        categoryMenuBarReverse(leisure, 0);
        categoryMenuBarReverse(bio, 0);
        categoryMenuBarReverse(comic, 0);
   }
    
    public void categoryMenuBar(Button bt,int x){
        //TranslateTransition ---> get a move.
        TranslateTransition translate = new TranslateTransition();
        translate.setDuration(Duration.seconds(2));
        translate.setNode(bt);
        translate.setToX(x);
        translate.play();
    }
    
    public void categoryMenuBarReverse(Button bt,int x){
        //TranslateTransition ---> get a move.
        TranslateTransition translate = new TranslateTransition();
        translate.setDuration(Duration.seconds(2));
        translate.setNode(bt);
        translate.setToX(x);
        translate.play();
    }
}
