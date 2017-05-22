/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.time.LocalDate;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author chenq
 */
public class salesPro {

    private final StringProperty bookName;
    private final StringProperty Date;
    private final IntegerProperty amount;
    private final DoubleProperty price;

    /**
     * Default constructor.
     */



    public salesPro(String bookName, String Date, int amount,double price) {
        this.bookName = new SimpleStringProperty(bookName);
        // Some initial dummy data, just for convenient testing.
        this.Date = new SimpleStringProperty(Date);
        this.amount = new SimpleIntegerProperty(amount);
        this.price = new SimpleDoubleProperty(price);

    }


    public String getbookName() {
        return bookName.get();
    }

    public void setbookName(String bookName) {
        this.bookName.set(bookName);
    }

    public StringProperty bookNameProperty() {
        return bookName;
    }


    public String getDate() {
        return Date.get();
    }

    public void setDate(String Date) {
        this.Date.set(Date);
    }

    public StringProperty DateProperty() {
        return Date;
    }

    public int getamount() {
        return amount.get();
    }

    public void setamount(int amount) {
        this.amount.set(amount);
    }

    public IntegerProperty amountProperty() {
        return amount;
    }
    
    public double getPrice(){
        return price.get();
    }
    
    public void setPrice(double price){
        this.price.set(price);
    }
    
    public DoubleProperty priceProperty(){
        return price;
    }


}

