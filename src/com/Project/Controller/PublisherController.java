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
import java.util.Optional;
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
    private JCDB jcdb =new JCDB();
    private Help help = new Help();
    
    @FXML
    private Button ButtonClear;
    @FXML
    private TextField address;
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
    private TableColumn<Publisher, Integer> Telephone;
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
    private void ButtonClear(ActionEvent event) {
        publishername.setText("");
        publisherintroduction.setText("");
        address.setText("");
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
     @FXML
    private void handleSaveAction(ActionEvent event) throws SQLException{
        SaveToDatabase();
         //showNewPublisher();
        publisherData.clear();
        getPublisherData();
        publishername.setText("");
        publisherintroduction.setText("");
        address.setText("");
        telephone.setText("");
        search.setText("");
    }
    
    private void SaveToDatabase() throws SQLException, NumberFormatException {
        boolean isTel = help.isInteger(telephone.getText());
        
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
        else if(address.getText().isEmpty()){
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
            int tele =Integer.valueOf( telephone.getText());;
            Publisher p = new Publisher(
                    publishername.getText(), 
                    address.getText(), 
                    tele, 
                    publisherintroduction.getText());
            
            jcdb.managerSavePublisher(p);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("SAVED");
            alert.setContentText("The Data Has Been Saved");
            alert.show();
        }
    }
    
    private void showNewPublisher(){
        boolean isTel = help.isInteger(telephone.getText());
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
        else if(address.getText().isEmpty()){
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
            Publisher p = new Publisher(publishername.getText(), address.getText(), telephone.getAnchor(), publisherintroduction.getText());
        
            pb.getItems().add(p);
        
            //Clear text field.
            publishername.clear();
            address.clear();
            telephone.clear();
            publisherintroduction.clear();
        }
    }

    private void getPublisherData() {
       publisherData=FXCollections.observableArrayList();
       ResultSet rs = jcdb.ManageRitrivePublisher();
        try {
            while(rs.next()){
//                String newTele = Integer.toString(rs.getInt("Pub_tel"));
                System.out.println(rs.getString("Pub_name"));
                System.out.println(rs.getString("Pub_address"));
                System.out.println(rs.getInt("Pub_tel"));
                //System.out.println(newTele);
                System.out.println(rs.getString("Pub_introduction")); 
                
                publisherData.add(new Publisher(
                        rs.getString("Pub_name"), 
                        rs.getString("Pub_address"), 
                        rs.getInt("Pub_tel"),
                        rs.getString("Pub_introduction")));
            }
        } catch (SQLException ex) {
           System.out.println("Error "+ ex);
        }
        Publisher.setCellValueFactory(new PropertyValueFactory<Publisher,String>("publisher"));
        Address.setCellValueFactory(new PropertyValueFactory<Publisher,String>("address"));
        Telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        Introduction.setCellValueFactory(new PropertyValueFactory<Publisher,String>("introduction"));
        pb.setItems(publisherData);
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
            ResultSet rs = jcdb.ManagerSearchPublisher(search.getText());
                      
            while(rs.next()){
                searchData.add((new Publisher(rs.getString("Pub_name"), 
                        rs.getString("Pub_address"), 
                        rs.getInt("Pub_tel"), 
                        rs.getString("Pub_introduction"))));
            }
        } catch (Exception e) {
            System.out.println("ERROR"+e);
        }
        
        Publisher.setCellValueFactory(new PropertyValueFactory<Publisher,String>("publisher"));
        Address.setCellValueFactory(new PropertyValueFactory<Publisher,String>("address"));
        Telephone.setCellValueFactory(new PropertyValueFactory<Publisher,Integer>("telephone"));
        Introduction.setCellValueFactory(new PropertyValueFactory<Publisher,String>("introduction"));
        
        pb.setItems(searchData);
    }
//    @FXML
//    private void handleDeleteAction(ActionEvent event){
//        ObservableList<Publisher> publisherSelect, allPublishers;
//        allPublishers = pb.getItems();
//        publisherSelect = pb.getSelectionModel().getSelectedItems();
//        
//        if(publisherSelect.isEmpty()){
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error Message");
//            alert.setHeaderText("ERROR");
//            alert.setContentText("Please select a publisher.");
//            alert.showAndWait();
//        }
//        else{
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setHeaderText("Confirm Deletion");
//            alert.setContentText("Are you sure you want to delete this publisher?");
//            
//            Optional<ButtonType> result = alert.showAndWait();
//            if(result.get() == ButtonType.OK){
//                JCDB.ManagerDeletePublisher(publisherSelect.get(0).getPublisher()); //remove publisher to database
//                publisherSelect.forEach(allPublishers::remove); //remove publisher to table view.
//            }
//            else{
//                alert.close();
//            }
//        }
//    }
}
