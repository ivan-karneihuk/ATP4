package main.java.Vitebsk.ATP4.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Contact 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name, full_txt, mobail;

    public Long getId_contact() {
        return id;
    }

    public void setId_contact(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_txt() {
        return full_txt;
    }

    public void setFull_txt(String full_txt) {
        this.full_txt = full_txt;
    }

    public String getMobail() {
        return mobail;
    }

    public void setMobail(String mobail) {
        this.mobail = mobail;
    }

    public Contact() {
    }
    
    public Contact(String name, String mobail, String full_txt) {
        this.name = name;
        this.mobail = mobail;
        this.full_txt = full_txt;
    }
}