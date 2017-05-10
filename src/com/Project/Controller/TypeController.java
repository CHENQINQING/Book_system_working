/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project.Controller;


import book_database.Database_Connection_LS;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Liu
 */
public class TypeController implements Initializable {

//     @FXML
//    private Label labelout;
    @FXML
    private Button ButtonClear;
    @FXML
    private TextField typename,search;
    @FXML
    private TextArea typeintroduction;
    @FXML
    private TableColumn zxc,cxz;
//    @FXML
//    private Label label1;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {

    }
    @FXML
    private void ButtonClear(ActionEvent event){
        search.setText("");
        typename.setText("");
        typeintroduction.setText("");
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        Database_Connection_LS test = new Database_Connection_LS();
//        System.out.println(test.showtype());

 
    }    
    
}
