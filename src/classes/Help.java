/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

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
}
