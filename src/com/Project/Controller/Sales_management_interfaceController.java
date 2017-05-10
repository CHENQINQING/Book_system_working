/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project.Controller;

import database.DatabaseConnection;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author chenq
 */
public class Sales_management_interfaceController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button search;
    @FXML
    private Button Add;
    @FXML
    private Button Delete;
    @FXML
    private Button confirm;
    @FXML
    private Button cancel;
    @FXML 
    private TextField data_text;
    @FXML
    private TextField quantity_text;
    
    
    private String date;
    ResultSet rs;
    
    @FXML
    private void handleSearchButtonAction(ActionEvent event){
        //yyy-mm-dd
        if(data_text.getText().matches("^\\d{4}-0[1-9]|1[1-2]-0[1-9]|[1-2]")){
            date = data_text.getText();
        }
        else{
            System.out.println("Please follow the format: yyyy-mm-dd");
        }
        DatabaseConnection connection = new DatabaseConnection();
        String sql = "SELECT RECORD_DATE FROM city WHERE RECORD_DATE='" + date + "'";  
        ResultSet rs = connection.query(sql);
    }
    
    public void getTime(){
        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(date);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
