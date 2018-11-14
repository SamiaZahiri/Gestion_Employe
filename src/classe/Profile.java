/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classe;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author PC
 */
@Entity
public class Profile {
     @Id
    @GeneratedValue
    private int id;
    private String code;
    private String libelle;

    public Profile() {
    }

    public Profile(String code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }

//    public Profile(int id, String code, String libelle) {
//        this.id = id;
//        this.code = code;
//        this.libelle = libelle;
//    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    
    
    
}
