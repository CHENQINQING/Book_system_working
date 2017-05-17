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
public class Book {
    private int id;
    private String name;
    private String publisher;
    private String author;
    private double price;
    private String intro;
    //private int inventory;
    private String type;
    
    public Book(){
        
    }
    
    public Book(String name, String author, double price, String publisher, String type) {
        this.name = name;
        this.publisher = publisher;
        this.author = author;
        this.price = price;
        this.type = type;
    }
    
    public Book(String name, String author, double price, String publisher, String type, String intro) {
        //this.id = id;
        this.name = name;
        this.publisher = publisher;
        this.author = author;
        this.price = price;
        this.type = type;
        this.intro = intro;
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
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * @param publiser the publisher to set
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the intro
     */
    public String getIntro() {
        return intro;
    }

    /**
     * @param intro the intro to set
     */
    public void setIntro(String intro) {
        this.intro = intro;
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
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    public int getInventory() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
