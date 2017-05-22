/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.time.LocalDate;
import java.util.Date;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Xuantong
 */



public class Book {
    private int id;
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleIntegerProperty publisherId = new SimpleIntegerProperty();
    private SimpleStringProperty publisher = new SimpleStringProperty();
    private SimpleStringProperty author = new SimpleStringProperty();
    private SimpleDoubleProperty price = new SimpleDoubleProperty();
    private SimpleStringProperty introduction = new SimpleStringProperty();
    //private int inventory;
    private SimpleStringProperty type = new SimpleStringProperty();
    private SimpleIntegerProperty quantity = new SimpleIntegerProperty();
    private LocalDate date;
    
    public Book(){
        this.name = new SimpleStringProperty();
        this.publisherId = new SimpleIntegerProperty();
        this.author = new SimpleStringProperty();
        this.price = new SimpleDoubleProperty();
        this.type = new SimpleStringProperty();
        this.introduction = new SimpleStringProperty();
        this.publisher = new SimpleStringProperty();
        this.quantity = new SimpleIntegerProperty();
        this.date = null;
    }
    
    public Book(String name, String author, double price,String publisher, String type,int quantity,String intro) {
        this.name = new SimpleStringProperty(name);
        this.author = new SimpleStringProperty(author);
        this.price = new SimpleDoubleProperty(price);
        this.type = new SimpleStringProperty(type);
        this.publisher = new SimpleStringProperty(publisher);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.introduction = new SimpleStringProperty(intro);
    }
    
    public Book(String name, String author, double price,String publisher, String type, String intro, int quantity, LocalDate date) {
        this.name = new SimpleStringProperty(name);
        this.author = new SimpleStringProperty(author);
        this.price = new SimpleDoubleProperty(price);
        this.type = new SimpleStringProperty(type);
        this.introduction = new SimpleStringProperty(intro);
        this.publisher = new SimpleStringProperty(publisher);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.date = date;
    }
    
    public Book(String name, String author, double price, int publisherId, String type, String intro,String publisher) {
        this.name = new SimpleStringProperty(name);
        this.publisherId = new SimpleIntegerProperty(publisherId);
        this.author = new SimpleStringProperty(author);
        this.price = new SimpleDoubleProperty(price);
        this.type = new SimpleStringProperty(type);
        this.introduction = new SimpleStringProperty(intro);
        this.publisher = new SimpleStringProperty(publisher);
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

    public int getQuantity(){
        return quantity.get();
    }
    
    public void setQuantity(int quantity){
        this.quantity.set(quantity);
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
    public int getPublisherId() {
        return publisherId.get();
    }

    /**
     * @param publiser the publisher to set
     */
    public void setPublisherId(int publisherId) {
        this.publisherId.set(publisherId);
    }
    
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
    public String getIntroduction() {
        return introduction.get();
    }

    /**
     * @param intro the intro to set
     */
    public void setIntro(String intro) {
        this.introduction.set(intro);
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
    
    public LocalDate getDate() {
        return date;
    }

     public void setDate(LocalDate date) {
        this.date = date;
    }
    
    //property value
    public SimpleStringProperty nameProperty(){
        return name;
    }
    public SimpleIntegerProperty publisherIdProperty(){
        return publisherId;
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
        return introduction;
    }
    public SimpleStringProperty typeProperty(){
        return type;
    }
    
    public SimpleIntegerProperty quantityProperty(){
        return quantity;
    }
}
