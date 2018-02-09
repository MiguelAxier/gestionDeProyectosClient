/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondeproyectos.ui.controller;

import java.io.Serializable;
import java.math.BigInteger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author H
 */
@XmlRootElement(name="personaContacto")
public class PersonaDeContactoBean implements Serializable{
    private SimpleIntegerProperty id;
    private SimpleStringProperty nombre;
    private SimpleStringProperty email;
    private SimpleObjectProperty telefono;
    
    public PersonaDeContactoBean(){
        //this.id = new SimpleIntegerProperty();
        this.nombre = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.telefono = new SimpleObjectProperty();
    }
    
    public PersonaDeContactoBean(String nombre, String email, BigInteger telefono){
        this.nombre = new SimpleStringProperty(nombre);
        this.email = new SimpleStringProperty(email);
        this.telefono = new SimpleObjectProperty(telefono);
    }
    public Integer getId() {
        return this.id.get();
    }

    public void setId(Integer id) {
        this.id.set(id);
    }

    public String getNombre() {
        return this.nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getEmail() {
        return this.email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public BigInteger getTelefono() {
        return (BigInteger) this.telefono.get();
    }

    public void setTelefono(BigInteger telefono) {
        this.telefono.set(telefono);
    }
    
    
}
