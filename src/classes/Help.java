/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

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
    public static boolean isInteger(String s){
        try {
            Integer.parseInt(s);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    //
    public static void resizeButton(Button bt){
        ScaleTransition st = new ScaleTransition(Duration.ONE, bt);
        st.setToX(1.03);
        st.setToY(1.03);
        st.setAutoReverse(false);
        st.play();
    }
    
    public static void reverseButtonSize(Button bt){
        ScaleTransition st = new ScaleTransition(Duration.ONE, bt);
        st.setToX(1);
        st.setToY(1);
        st.setAutoReverse(false);
        st.play();
    }
    public static void resizeButton(TextField tf){
        ScaleTransition st = new ScaleTransition(Duration.ONE, tf);
        st.setToX(1.03);
        st.setToY(1.03);
        st.setAutoReverse(false);
        st.play();
    }
    
    public static void reverseButtonSize(TextField tf){
        ScaleTransition st = new ScaleTransition(Duration.ONE, tf);
        st.setToX(1);
        st.setToY(1);
        st.setAutoReverse(false);
        st.play();
    }
    public static void resizeButton(ComboBox cb){
        ScaleTransition st = new ScaleTransition(Duration.ONE, cb);
        st.setToX(1.03);
        st.setToY(1.03);
        st.setAutoReverse(false);
        st.play();
    }
    
    public static void reverseButtonSize(ComboBox cb){
        ScaleTransition st = new ScaleTransition(Duration.ONE, cb);
        st.setToX(1);
        st.setToY(1);
        st.setAutoReverse(false);
        st.play();
    }
}
