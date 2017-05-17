/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project.Controller;

import classes.Help;
import classes.Publisher;
import classes.Type;
import database.JCDB;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.StringProperty;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
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
public class TypeController implements Initializable {

    @FXML
    private TextField search;
    @FXML
    private TableView tt;
    @FXML
    private TableColumn<Type, String> TYPE;
    @FXML
    private TableColumn<Type, String> INTRODUCTION;
    @FXML
    private TextField TypeName;
    @FXML
    private TextArea TypeIntroduction;

    private ObservableList<Type>typeData;
    private ObservableList<Type>searchData;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        gettypeData();
    }    

    @FXML
    private void ButtonClear(ActionEvent event) {
        TypeName.setText("");
        TypeIntroduction.setText("");
        search.setText("");
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
    
    @FXML
    private void handleSaveAction(ActionEvent event) throws SQLException {
        SaveToDatabase();
        showNewType();
    }
    
    private void SaveToDatabase() throws SQLException, NumberFormatException {
        
        if(TypeName.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("INVALID TYPE NAME.");
            alert.show();
        }
        else if(TypeIntroduction.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("INVALID INTRODUCTION.");
            alert.show();
        }
        else{
            Type T = new Type(
                    TypeName.getText(),
                    TypeIntroduction.getText());
            JCDB.managerSaveType(
                    T.getTypeName(),
                    T.getIntroduction());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("SAVED");
            alert.setContentText("The Data Has Been Saved");
            alert.show();
        }
    }
    
    private void showNewType(){
        
        if(TYPE.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("INVALID TYPE NAME.");
            alert.show();
        }
        else if(INTRODUCTION.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("INVALID INTRODUCTION.");
            alert.show();
        }
        else{
            Type T = new Type(TypeName.getText(),TypeIntroduction.getText());
        
            tt.getItems().add(T);
        
            //Clear text field.
            TypeName.clear();
            TypeIntroduction.clear();
        }
    }
    
    private void gettypeData() {
       typeData=FXCollections.observableArrayList();
       ResultSet rs = JCDB.ManageRitriveType();
       Type t = null;
        try {
            while(rs.next()){
                System.out.println(rs.getString("Type_name"));
                System.out.println(rs.getString("Type_introduction"));
                t = new Type(rs.getString("Type_name"), rs.getString("Type_introduction"));
                typeData.add(t);
            }
        } catch (SQLException ex) {
            System.out.println("Error "+ ex);
        }
        System.out.println("111");
        
        System.out.println(t.getTypeName());
        System.out.println("222");
        
        TYPE.setCellValueFactory(new PropertyValueFactory<Type,String>("type"));
        INTRODUCTION.setCellValueFactory(new PropertyValueFactory<Type,String>("introduction"));
        tt.setItems(typeData);
        
    }
    
    @FXML
    private void handleDeleteAction(ActionEvent event){
        ObservableList<Type> typeSelect, allType;
        allType = tt.getItems();
        typeSelect = tt.getSelectionModel().getSelectedItems();
        
        if(typeSelect.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText("ERROR");
            alert.setContentText("Please select a publisher.");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Confirm Deletion");
            alert.setContentText("Are you sure you want to delete this type?");
            
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK){
                JCDB.ManagerDeleteType(typeSelect.get(0).getTypeName()); //remove publisher to database
                typeSelect.forEach(allType::remove); //remove publisher to table view.
            }
            else{
                alert.close();
            }
        }
    }
}
