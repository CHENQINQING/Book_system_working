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
public class Type {
    private final IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private final StringProperty type = new SimpleStringProperty(this, "type");
    private final StringProperty introduction = new SimpleStringProperty(this, "introduction");
    
    public Type(String type, String introduction){
        this.type.set(type);
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
    public final StringProperty typeProperty() {
        return this.type;
    }

    public final java.lang.String getType() {
        return this.typeProperty().get();
    }

    public final void setType(final java.lang.String type) {
        this.typeProperty().set(type);
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
}
