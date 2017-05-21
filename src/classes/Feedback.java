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
public class Feedback {
    private final IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private final StringProperty title = new SimpleStringProperty(this, "title");
    private final StringProperty body = new SimpleStringProperty(this, "body");
    private final StringProperty email = new SimpleStringProperty(this, "email");
    private final StringProperty datetime = new SimpleStringProperty(this, "datatime");
    
    public Feedback(String title,String body,String email){
        this.title.set(title);   
        this.body.set(body); 
        this.email.set(email); 
    }
    public final StringProperty titleProperty() {
        return this.title;
    }

    public final java.lang.String getTitle() {
        return this.titleProperty().get();
    }

    public final void setTitle(final java.lang.String title) {
        this.titleProperty().set(title);
    }
    public final StringProperty bodyProperty() {
        return this.body;
    }

    public final java.lang.String getBody() {
        return this.bodyProperty().get();
    }

    public final void setBody(final java.lang.String body) {
        this.bodyProperty().set(body);
    }
    public final StringProperty datetimeProperty() {
        return this.datetime;
    }

    public final java.lang.String getDatetime() {
        return this.datetimeProperty().get();
    }

    public final void setDatetime(final java.lang.String datetime) {
        this.datetimeProperty().set(datetime);
    }
    public final StringProperty emailProperty() {
        return this.email;
    }

    public final java.lang.String getEmail() {
        return this.emailProperty().get();
    }

    public final void setEmail(final java.lang.String email) {
        this.datetimeProperty().set(email);
    }
}
