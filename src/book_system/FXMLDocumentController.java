/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book_system;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author chenq
 */
public class FXMLDocumentController implements Initializable {
    

    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        Stage stage2 = new Stage();
        Parent root2 = FXMLLoader.load(getClass().getResource("sales_management_interface.fxml"));        
        //Scene scene = new Scene(root);        
        //stage2.setScene(scene);
        //stage2.show();  
    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
