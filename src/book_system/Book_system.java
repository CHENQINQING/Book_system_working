/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book_system;

import database.JCDB;
import java.sql.SQLException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author chenq
 */
public class Book_system extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
//        JCDB db = new JCDB();
//        db.createDefaultEmployee();
        
        Parent root = FXMLLoader.load(getClass().getResource("/com/Project/FXML/LoginPage.fxml"));        
        Scene scene = new Scene(root);        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
