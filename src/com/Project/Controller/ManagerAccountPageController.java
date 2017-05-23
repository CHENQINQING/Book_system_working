/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project.Controller;

import classes.BookStorage;
import classes.User;
import classes.importPro;
import database.DatabaseConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Xuantong
 */
public class ManagerAccountPageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField searchTf;
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn<User,String> name;
    @FXML
    private TableColumn<User,Integer> level;
    @FXML
    private TableColumn<User,String> email;
    @FXML
    private TextField nameT;
    @FXML
    private TextField userNameT;
    @FXML
    private TextField passT;
    @FXML
    private TextField emailT;
    @FXML
    private TextField levelT;
    @FXML
    private Tab tabView, tabCreate;
    
    private ResultSet rs;
    private ObservableList<User> user = FXCollections.observableArrayList();
    private ObservableList<User> data;
    private DatabaseConnection connection = new DatabaseConnection();
    
    @FXML
    public void SearchBook(ActionEvent event) throws IOException, SQLException{
        String Sname = searchTf.getText();
        if(searchTf.getText().isEmpty()){
            getData();
        }else{
            //BookStorage.getInstance().setName(searchTf.getText());
            String sql = "select * from user where name like '"+Sname+"%'";
            rs = connection.query(sql);
            while(rs.next()){
                user.add(new User(rs.getString("name"),rs.getInt("level"),rs.getString("email")));
            }                        
            tableView.setItems(user);
        }
    }

    
     @FXML
    private void handleBackButtonAction(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/Project/FXML/ManagerMenu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Menu");
        stage.show();
    }
    
    @FXML
    private void confirm(ActionEvent event) throws SQLException{
        if(nameT.getText().isEmpty()||userNameT.getText().isEmpty()||passT.getText().isEmpty()||emailT.getText().isEmpty()||levelT.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("Search Field cannot be empty");
            alert.showAndWait();
        }
        else if(!checkEmail(emailT.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Email ERROR");
            alert.setContentText("Please type correct email address.");
            alert.showAndWait();
        }
        else if(!checkLevel(levelT.getText())){
            System.out.println(levelT.getText().trim());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Level ERROR");
            alert.setContentText(" 1 = employee \r\n 2 = manager");
            alert.showAndWait();
        }
        else{
            //BookStorage.getInstance().setName(searchTf.getText());            
            String sql = "insert into user (name,username,password,level,email) values ('"+nameT.getText()+"','"+userNameT.getText()+"','"+passT.getText()+"',"+levelT.getText()+",'"+emailT.getText()+"')";
            System.out.println(sql);
            connection.executeSql(sql);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Success");
            alert.setContentText("Account Created");
            alert.show();
            
            nameT.setText("");
            userNameT.setText("");
            passT.setText("");
            emailT.setText("");
            levelT.setText("");
            
            refresh();
        }
    }
    
    @FXML
    private void cancel(ActionEvent event){
        nameT.setText("");
        userNameT.setText("");
        passT.setText("");
        emailT.setText("");
        levelT.setText("");
    }
    
    public void getData() throws SQLException{
        String sql = "select name,level,email from user ";
        data = FXCollections.observableArrayList();
        rs = connection.query(sql);
            while(rs.next()){
                data.add(new User(rs.getString("name"),rs.getInt("level"),rs.getString("email")));
            }                        
        tableView.setItems(data);
        name.setCellValueFactory(new PropertyValueFactory<User,String> ("name"));
        email.setCellValueFactory(new PropertyValueFactory<User,String> ("email"));
        level.setCellValueFactory(new PropertyValueFactory<User,Integer> ("level"));    
    }
    
    public void refresh() throws SQLException{
        data.clear();
        getData();
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
    
    private static boolean checkLevel(String level){
        String RULE_EMAIL = "[1-2]";
        //正则表达式的模式
        Pattern p = Pattern.compile(RULE_EMAIL);
        //正则表达式的匹配器
        Matcher m = p.matcher(level);
        //进行正则匹配
        return m.matches();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        name.setCellValueFactory(new PropertyValueFactory<User,String> ("name"));
        email.setCellValueFactory(new PropertyValueFactory<User,String> ("email"));
        level.setCellValueFactory(new PropertyValueFactory<User,Integer> ("level"));
        try {
            getData();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerAccountPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
