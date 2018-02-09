/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondeproyectos.ui.controller;

import java.io.Serializable;
import java.math.BigInteger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Miguel Axier Lafuente Peñas
 */
@XmlRootElement(name="cliente")
public class ClienteBean implements Serializable {
    private SimpleStringProperty nif;
    private SimpleStringProperty nombre;
    private SimpleStringProperty direccion;
    private SimpleObjectProperty telefono;
    private SimpleStringProperty email;
    private SimpleStringProperty web;
    private SimpleObjectProperty<PersonaDeContactoBean> contacto;
    
    //TODO 
    
    public ClienteBean(){
        this.nif=new SimpleStringProperty();
        this.nombre=new SimpleStringProperty();
        this.direccion=new SimpleStringProperty();
        this.telefono=new SimpleObjectProperty();
        this.email=new SimpleStringProperty();
        this.web=new SimpleStringProperty();
        this.contacto = new SimpleObjectProperty();
    }
    
    public ClienteBean(String nif, String nombre, String direccion, Integer telefono
                        ,String email, String web, PersonaDeContactoBean contacto){
        
        this.nif=new SimpleStringProperty(nif);
        this.nombre=new SimpleStringProperty(nombre);
        this.direccion=new SimpleStringProperty(direccion);
        this.telefono=new SimpleObjectProperty(telefono);
        this.email=new SimpleStringProperty(email);
        this.web=new SimpleStringProperty(web);
        this.contacto=new SimpleObjectProperty(contacto);
        
        
        
    }
    public PersonaDeContactoBean getContacto(){
        return this.contacto.get();
    }
    
    public void setContacto(PersonaDeContactoBean contacto){
        this.contacto.set(contacto);
    }
    
    public String getNif() {
        return this.nif.get();
    }

    public void setNif(String nif) {
        this.nif.set(nif);
    }

    public String getNombre() {
        return this.nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getDireccion() {
        return this.direccion.get();
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    public BigInteger getTelefono() {
        return (BigInteger) this.telefono.get();
    }

    public void setTelefono(BigInteger telefono) {
        this.telefono.set(telefono);
    }

    public String getEmail() {
        return this.email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getWeb() {
        return this.web.get();
    }

    public void setWeb(String web) {
        this.web .set(web);
    }   
}
