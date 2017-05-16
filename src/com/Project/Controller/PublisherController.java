/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project.Controller;

import classes.Help;
import classes.Publisher;
import database.JCDB;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class PublisherController implements Initializable {

    @FXML
    private Button ButtonClear;
    @FXML
    private TextField contact;
    @FXML
    private TextField telephone;
    @FXML
    private Button ButtonMeun;
    @FXML
    private TextField search;
    @FXML
    private TextField publishername;
    @FXML
    private TextArea publisherintroduction;
    @FXML
    private TableView pb;
    @FXML
    private TableColumn<Publisher, String> Publisher;
    @FXML
    private TableColumn<Publisher, String> Address;
    @FXML
    private TableColumn<Publisher, String> Telephone;
    @FXML
    private TableColumn<Publisher, String> Introduction;

    private ObservableList<Publisher>publisherData;
    private ObservableList<Publisher>searchData;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getPublisherData();
    }    

     @FXML
    private void handleSaveAction(ActionEvent event) throws SQLException{
         SaveToDatabase();
         showNewPublisher();
    }
    
    @FXML
    private void ButtonClear(ActionEvent event) {
        publishername.setText("");
        publisherintroduction.setText("");
        contact.setText("");
        telephone.setText("");
        search.setText("");
    }
    @FXML
    private void ButtonMeun(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/Project/FXML/Menu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Menu");
        stage.show();
    }
    
    private void SaveToDatabase() throws SQLException, NumberFormatException {
        boolean isTel = Help.isInteger(telephone.getText());
        
        if(!isTel){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("TELEPHONE NUMBER IS INVALID");
            alert.show();
        }
        else if(publishername.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("INVALID PUBLISHER NAME.");
            alert.show();
        }
        else if(contact.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("INVALID ADDRESS.");
            alert.show();
        }
        else if(publisherintroduction.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("INVALID INTRODUCTION.");
            alert.show();
        }
        else{
            String tele = telephone.getText();
            int phoneNum = Integer.parseInt(tele);
            Publisher p = new Publisher(publishername.getText(), contact.getText(), tele, publisherintroduction.getText());
            
            JCDB.managerSavePublisher(p.getPublisher(), p.getAddress(), phoneNum, p.getIntroduction());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("SAVED");
            alert.setContentText("The Data Has Been Saved");
            alert.show();
        }
    }

    private void getPublisherData() {
       publisherData=FXCollections.observableArrayList();
       ResultSet rs = JCDB.ManageRitrivePublisher();
        try {
            while(rs.next()){
//                System.out.println(rs.getString("publisherName"));
//                System.out.println(rs.getString("address"));
//                System.out.println(rs.getInt("phone"));
//                System.out.println(rs.getString("introduction"));

                String newTele = Integer.toString(rs.getInt("Pub_tel"));
                
                System.out.println(rs.getString("Pub_name"));
                System.out.println(rs.getString("Pub_link_man"));
                //System.out.println(rs.getInt("Pub_tel"));
                System.out.println(newTele);
                System.out.println(rs.getString("Pub_introduction"));  
                publisherData.add(new Publisher(
                        rs.getString("Pub_name"), 
                        rs.getString("Pub_link_man"), 
                        newTele, 
                        rs.getString("Pub_introduction")));
//                publisherData.add(new Publisher(
//                        rs.getString("publisherName"), 
//                        rs.getString("address"), 
//                        rs.getInt("phone"), 
//                        rs.getString("introduction")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PublisherController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Publisher.setCellValueFactory(new PropertyValueFactory<Publisher,String>("publisher"));
        Address.setCellValueFactory(new PropertyValueFactory<Publisher,String>("address"));
        Telephone.setCellValueFactory(new PropertyValueFactory<Publisher,String>("telephone number"));
        Introduction.setCellValueFactory(new PropertyValueFactory<Publisher,String>("introduction"));
        pb.setItems(publisherData);
    }
    
    private void showNewPublisher(){
        boolean isTel = Help.isInteger(telephone.getText());
        if(!isTel){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("TELEPHONE NUMBER IS INVALID");
            alert.show();
        }
        else if(publishername.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("INVALID PUBLISHER NAME.");
            alert.show();
        }
        else if(contact.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("INVALID ADDRESS.");
            alert.show();
        }
        else if(publisherintroduction.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("INVALID INTRODUCTION.");
            alert.show();
        }
        else{
            String tele = telephone.getText();
            Publisher p = new Publisher(publishername.getText(), contact.getText(), tele, publisherintroduction.getText());
        
            pb.getItems().add(p);
        
            //Clear text field.
            publishername.clear();
            contact.clear();
            telephone.clear();
            publisherintroduction.clear();
        }
    }
    
    @FXML
    private void handleSearchAction(ActionEvent event){
        if(search.getText().isEmpty()){
            getPublisherData();
        }
        else{
            getSearched();
        }
    }
    
    private void getSearched(){
        try {
            Publisher.getColumns().clear();
            Address.getColumns().clear();
            Telephone.getColumns().clear();
            Introduction.getColumns().clear();
            
            searchData = FXCollections.observableArrayList();
            ResultSet rs = JCDB.ManagerSearchBook(search.getText());
            
            String newTele = Integer.toString(rs.getInt("Pub_tel"));
            
            while(rs.next()){
                searchData.add((new Publisher(rs.getString("Pub_name"), 
                        rs.getString("Pub_link_man"), 
                        newTele, 
                        rs.getString("Pub_introduction"))));
            }
        } catch (Exception e) {
            System.out.println("ERROR"+e);
        }
        
        Publisher.setCellValueFactory(new PropertyValueFactory<Publisher,String>("publisher"));
        Address.setCellValueFactory(new PropertyValueFactory<Publisher,String>("address"));
        Telephone.setCellValueFactory(new PropertyValueFactory<Publisher,String>("telephone number"));
        Introduction.setCellValueFactory(new PropertyValueFactory<Publisher,String>("introduction"));
        
        pb.setItems(searchData);
    }
}
