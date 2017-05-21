/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author chenq
 */
public class importPro {
    private final StringProperty bookName;
    private final StringProperty Date;
    private final IntegerProperty amount;

    /**
     * Default constructor.
     */


    /**
     * Constructor with some initial data.
     * 
     * @param bookName
     * @param lastName
     */
    public importPro(String bookName, String Date, int amount) {
        this.bookName = new SimpleStringProperty(bookName);

        // Some initial dummy data, just for convenient testing.
        this.Date = new SimpleStringProperty(Date);
        this.amount = new SimpleIntegerProperty(amount);

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
}
