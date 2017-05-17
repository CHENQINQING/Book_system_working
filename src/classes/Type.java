/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author liushuai
 */
public class Type {
    private int id;
    private SimpleStringProperty type = new SimpleStringProperty();
    private SimpleStringProperty introduction = new SimpleStringProperty();
    
    
    public Type(String typeName, String introduction){
        setTypeName(typeName);
        setIntroduction(introduction);
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
     * @return the type
     */
    public String getTypeName() {
        return type.get();
    }

    /**
     * @param typeName the type to set
     */
    public void setTypeName(String typeName) {
        this.type.set(typeName);
    }

    /**
     * @return the introduction
     */
    public String getIntroduction() {
        return introduction.get();
    }

    /**
     * @param introduction the introduction to set
     */
    public void setIntroduction(String introduction) {
        this.introduction.set(introduction);
    }
    
    public SimpleStringProperty nameProperty(){
        return type;
    }
    public SimpleStringProperty introductionProperty(){
        return introduction;
    }
}
