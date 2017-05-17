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
 * @author Xuantong
 */
public class Book {
    private int id;
    private SimpleStringProperty name;
    private SimpleStringProperty publisher;
    private SimpleStringProperty author;
    private SimpleDoubleProperty price;
    private SimpleStringProperty intro;
    //private int inventory;
    private SimpleStringProperty type;
    
    public Book(){
        
    }
    
    public Book(String name, String author, double price, String publisher, String type) {
        this.name = new SimpleStringProperty(name);
        this.publisher = new SimpleStringProperty(publisher);
        this.author = new SimpleStringProperty(author);
        this.price = new SimpleDoubleProperty(price);
        this.type = new SimpleStringProperty(type);
    }
    
    public Book(String name, String author, double price, String publisher, String type, String intro) {
        this.name = new SimpleStringProperty(name);
        this.publisher = new SimpleStringProperty(publisher);
        this.author = new SimpleStringProperty(author);
        this.price = new SimpleDoubleProperty(price);
        this.type = new SimpleStringProperty(type);
        this.intro = new SimpleStringProperty(intro);
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name.get();
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * @return the publisher
     */
    public String getPublisher() {
        return publisher.get();
    }

    /**
     * @param publiser the publisher to set
     */
    public void setPublisher(String publisher) {
        this.publisher.set(publisher);
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author.get();
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author.set(author);
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price.get();
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price.set(price);
    }

    /**
     * @return the intro
     */
    public String getIntro() {
        return intro.get();
    }

    /**
     * @param intro the intro to set
     */
    public void setIntro(String intro) {
        this.intro.set(intro);
    }

    /**
     * @return the stock
     */
    /*public int getInventory() {
        return inventory;
    }*/

    /**
     * @param stock the stock to set
     */
    /*public void setInventory(int inventory) {
        this.inventory = inventory;
    }*/

    /**
     * @return the type
     */
    public String getType() {
        return type.get();
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type.set(type);
    }
    
    //property value
    public SimpleStringProperty nameProperty(){
        return name;
    }
    public SimpleStringProperty publisherProperty(){
        return publisher;
    }
    public SimpleStringProperty authorProperty(){
        return author;
    }
    public SimpleDoubleProperty priceProperty(){
        return price;
    }
    public SimpleStringProperty introProperty(){
        return intro;
    }
    public SimpleStringProperty typeProperty(){
        return type;
    }
}
