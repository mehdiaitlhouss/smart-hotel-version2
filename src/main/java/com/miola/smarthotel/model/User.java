package com.miola.smarthotel.model;

/*
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

*/
/**
* Code created by Andrius on 2020-10-08
*//*

@Entity
@Getter @Setter
public class User
{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String userName;

private String password;
}
*/
public abstract class User
{
    private int idUser; // l'id primaire de la table user
    private String prenom;
    private String nom;
    private String cin;
    private String email;
    private String telephone;
    private String adresse;
    private String fullName;

    public User()
    {

    }

    public User(int idUser, String prenom, String nom, String cin, String email, String telephone, String adresse)
    {
        this.idUser = idUser;
        this.prenom = prenom;
        this.nom = nom;
        this.cin = cin;
        this.email = email;
        this.telephone = telephone;
        this.adresse = adresse;
        this.fullName = nom + " " + prenom;
    }

    public User(String prenom, String nom, String cin, String email, String telephone, String adresse)
    {
        this.prenom = prenom;
        this.nom = nom;
        this.cin = cin;
        this.email = email;
        this.telephone = telephone;
        this.adresse = adresse;
        this.fullName = nom + " " + prenom;
    }


    public String getFullName()
    {
        return fullName;
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getNom() {
        return nom;
    }

    public String getCin() {
        return cin;
    }

    public String getEmail() {
        return email;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", cin='" + cin + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", adresse='" + adresse + '\'' +
                '}';
    }
}