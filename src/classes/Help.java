/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.Duration;

/**
 *
 * @author Xuantong
 */
public class Help {
    //check if is a integer
    public boolean isInteger(String s){
        try {
            Integer.parseInt(s);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    //
    public void resizeButton(Button bt){
        ScaleTransition st = new ScaleTransition(Duration.ONE, bt);
        st.setToX(1.03);
        st.setToY(1.03);
        st.setAutoReverse(false);
        st.play();
    }
    
    public void reverseButtonSize(Button bt){
        ScaleTransition st = new ScaleTransition(Duration.ONE, bt);
        st.setToX(1);
        st.setToY(1);
        st.setAutoReverse(false);
        st.play();
    }
    public void resizeButton(TextField tf){
        ScaleTransition st = new ScaleTransition(Duration.ONE, tf);
        st.setToX(1.03);
        st.setToY(1.03);
        st.setAutoReverse(false);
        st.play();
    }
    
    public void reverseButtonSize(TextField tf){
        ScaleTransition st = new ScaleTransition(Duration.ONE, tf);
        st.setToX(1);
        st.setToY(1);
        st.setAutoReverse(false);
        st.play();
    }
    public void resizeButton(ComboBox cb){
        ScaleTransition st = new ScaleTransition(Duration.ONE, cb);
        st.setToX(1.03);
        st.setToY(1.03);
        st.setAutoReverse(false);
        st.play();
    }
    
    public void reverseButtonSize(ComboBox cb){
        ScaleTransition st = new ScaleTransition(Duration.ONE, cb);
        st.setToX(1);
        st.setToY(1);
        st.setAutoReverse(false);
        st.play();
    }
    
    public java.sql.Date toSqlDate(LocalDate date){
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//        date = LocalDate.now();
        return Date.valueOf(date);
    }
}
