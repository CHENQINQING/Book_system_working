/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Xuantong
 */
public class Person {
    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty username = new SimpleStringProperty();
    private SimpleStringProperty password = new SimpleStringProperty();
    private SimpleIntegerProperty level = new SimpleIntegerProperty();

    private static Person personStoage;
    /**
     * @return the id
     */
    private Person(){
        
    }
    
    public static Person getInstance(){
        if(personStoage == null){
            personStoage = new Person();
        }
        return personStoage;
    }
    
    public int getId() {
        return id.get();
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id.set(id);
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
     * @return the username
     */
    public String getUsername() {
        return username.get();
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username.set(username);
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password.get();
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password.get();
    }
    
    public void setLevel(int level){
        this.level.set(level);
    }
    
    public int getLevel(){
        return level.get();
    }
    
    //
    public SimpleIntegerProperty idProperty(){
        return id;
    }
    public SimpleStringProperty nameProperty(){
        return name;
    }
    public SimpleStringProperty usernameProperty(){
        return username;
    }
    public SimpleStringProperty passwordProperty(){
        return password;
    }
    public SimpleIntegerProperty levelProperty(){
        return level;
    }
}
