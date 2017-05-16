/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author liushuai
 */
public class Publisher {
    private int id;
    private String publisherName;
    private String address;
    private String phone;
    private String introduction;
    public Publisher(String publisher,String address,String telephone,String introduction){
        this.publisherName=publisher;
        this.address=address;
        this.phone=telephone;
        this.introduction=introduction;
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
     * @return the publisherName
     */
    public String getPublisher() {
        return publisherName;
    }

    /**
     * @param publisher the publisherName to set
     */
    public void setPublisher(String publisher) {
        this.publisherName = publisher;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the phone
     */
    public String getTelephone() {
        return phone;
    }

    /**
     * @param telephone the phone to set
     */
    public void setTelephone(String telephone) {
        this.phone = telephone;
    }

    /**
     * @return the introduction
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * @param introduction the introduction to set
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}

