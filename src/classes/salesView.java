/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author chenq
 */
public class salesView {
    private final SimpleStringProperty bookName = new SimpleStringProperty();   
    private final SimpleDoubleProperty totalp = new SimpleDoubleProperty();
    private final SimpleDoubleProperty price = new SimpleDoubleProperty();  
   
       private String uuid;  
         
         public salesView(String filename, double price) {  
           setFileName(filename);  
           setprice(price);  
       }       
         
       public salesView(String filename, double tp, double price) {  
           settp(tp);  
           setFileName(filename);   
           setprice(price);  
       }  
    /** 
     * @return the fileName 
     */  
    public String getFileName() {  
        return bookName.get();  
    }  
  
    /** 
     * @param fileName the fileName to set 
     */  
    public void setFileName(String fileName) {  
        this.bookName.set(fileName);  
    }  
      
    public SimpleStringProperty fileNameProperty(){  
        return bookName;  
    }  
  
    /** 
     * @return the status 
     */  

    public double tp() {  
        return totalp.get();  
    }  
  
    /** 
     * @param progress the progress to set 
     */  
    public void settp(double tp) {  
        this.totalp.set(tp);  
    }  
      
    public SimpleDoubleProperty tpProperty(){  
        return totalp;  
    }
    
        public double getprice() {  
        return price.get();  
    }  
  
    /** 
     * @param progress the progress to set 
     */  
    public void setprice(double price) {  
        this.price.set(price);  
    }  
      
    public SimpleDoubleProperty priceProperty(){  
        return price;  
    } 
      
 
        
    public String getUUID() {  
        return uuid;  
    }  
  
    public void setUUID(String uuid) {  
        this.uuid = uuid;  
    }    
}
