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
 * @author liushuai
 */
public class Publisher {
    
    private final IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private final StringProperty publisher = new SimpleStringProperty(this, "publisher");
    private final StringProperty address = new SimpleStringProperty(this, "address");
    private final IntegerProperty telephone = new SimpleIntegerProperty(this, "telephone");
    private final StringProperty introduction = new SimpleStringProperty(this, "introduction");
  
    public Publisher(String publisher,String address,int telephone,String introduction){
//        this.publisherName=publisher;
//        this.address=address;
//        this.telephone=telephone;
//        this.introduction=introduction;
        this.publisher.set(publisher);
        this.address.set(address);
        this.telephone.set(telephone);
        this.introduction.set(introduction);
    }
    
    public final IntegerProperty idProperty() {
        return this.id;
    }

    public final int getId() {
        return this.idProperty().get();
    }

    public final void setId(final int id) {
        this.idProperty().set(id);
    }

    public final StringProperty publisherProperty() {
        return this.publisher;
    }

    public final java.lang.String getPublisher() {
        return this.publisherProperty().get();
    }

    public final void setPublisher(final java.lang.String publisher) {
        this.publisherProperty().set(publisher);
    }

    public final StringProperty addressProperty() {
        return this.address;
    }

    public final java.lang.String getAddress() {
        return this.addressProperty().get();
    }

    public final void setAddress(final java.lang.String address) {
        this.addressProperty().set(address);
    }
    public final IntegerProperty telephoneProperty() {
        return this.telephone;
    }

    public final int getTelephone() {
        return this.telephoneProperty().get();
    }

    public final void setTelehone(final int telephone) {
        this.telephoneProperty().set(telephone);
    }

    public final StringProperty introductionProperty() {
        return this.introduction;
    }

    public final java.lang.String getIntroduction() {
        return this.introductionProperty().get();
    }

    public final void setintroduction(final java.lang.String introduction) {
        this.introductionProperty().set(introduction);
    }

//    /**
//     * @return the id
//     */
//    public int getId() {
//        return id;
//    }
//
//    /**
//     * @param id the id to set
//     */
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    /**
//     * @return the publisherName
//     */
//    public String getPublisher() {
//        return publisherName;
//    }
//
//    /**
//     * @param publisher the publisherName to set
//     */
//    public void setPublisher(String publisher) {
//        this.publisherName = publisher;
//    }
//
//    /**
//     * @return the address
//     */
//    public String getAddress() {
//        return address;
//    }
//
//    /**
//     * @param address the address to set
//     */
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    /**
//     * @return the telephone
//     */
//    public String getTelephone() {
//        return telephone;
//    }
//
//    /**
//     * @param telephone the telephone to set
//     */
//    public void setTelephone(String telephone) {
//        this.telephone = telephone;
//    }
//
//    /**
//     * @return the introduction
//     */
//    public String getIntroduction() {
//        return introduction;
//    }
//
//    /**
//     * @param introduction the introduction to set
//     */
//    public void setIntroduction(String introduction) {
//        this.introduction = introduction;
//    }
}

