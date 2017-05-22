/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 *
 * @author Xuantong
 */
public class Feedback {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty title = new SimpleStringProperty();
    private StringProperty body = new SimpleStringProperty();
    private StringProperty status = new SimpleStringProperty();
    private LocalDate date;
    
    public Feedback(){
        this.id = new SimpleIntegerProperty();
        this.title = new SimpleStringProperty();
        this.body = new SimpleStringProperty();
        this.status = new SimpleStringProperty();
        this.date = null;
    }
    
    public Feedback(String title,String body){
        this.title.set(title);       
        this.body.set(body);
    }
    
    public void setStatus(String status){
        this.statusProperty().set(status);
    }
    public String getStatus(){
        return status.get();
    }
    public StringProperty statusProperty(){
        return status;
    }
    
    public StringProperty titleProperty() {
        return title;
    }

    public String getTitle() {
        return titleProperty().get();
    }

    public void setTitle(String title) {
        this.titleProperty().set(title);
    }
    public StringProperty bodyProperty() {
        return body;
    }

    public String getBody() {
        return bodyProperty().get();
    }

    public void setBody(String body) {
        this.bodyProperty().set(body);
    }

    public LocalDate getDatetime() {
        return date;
    }

     public void setDatetime(LocalDate date) {
        this.date = date;
    }
    
    public void setId(int id){
        this.idProperty().set(id);
    }
    public int getId(){
        return id.get();
    }
    public IntegerProperty idProperty(){
        return id;
    }
}
