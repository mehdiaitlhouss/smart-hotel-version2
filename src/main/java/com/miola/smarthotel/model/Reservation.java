package com.miola.smarthotel.model;

import com.miola.smarthotel.helpers.EtatReservation;

import java.sql.Time;
import java.util.Date;

public class Reservation
{
    private int id;
    private Date dateReservation;
    private Time heureReservation;
    private int dureeSejour;
    private int nombrePersonne;
    private Employe employe;
    private Client client;
    private EtatReservation etat;

    public Reservation(int id, Date dateReservation, Time heureReservation, int dureeSejour, int nombrePersonne, Employe employe, Client client, EtatReservation etat) {
        this.id = id;
        this.dateReservation = dateReservation;
        this.heureReservation = heureReservation;
        this.dureeSejour = dureeSejour;
        this.nombrePersonne = nombrePersonne;
        this.employe = employe;
        this.client = client;
        this.etat = etat;
    }

    public Reservation(Date dateReservation, Time heureReservation, int dureeSejour, int nombrePersonne, Employe employe, Client client, EtatReservation etat) {
        this.dateReservation = dateReservation;
        this.heureReservation = heureReservation;
        this.dureeSejour = dureeSejour;
        this.nombrePersonne = nombrePersonne;
        this.employe = employe;
        this.client = client;
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public Time getHeureReservation() {
        return heureReservation;
    }

    public int getDureeSejour() {
        return dureeSejour;
    }

    public int getNombrePersonne() {
        return nombrePersonne;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public void setHeureReservation(Time heureReservation) {
        this.heureReservation = heureReservation;
    }

    public void setDureeSejour(int dureeSejour) {
        this.dureeSejour = dureeSejour;
    }

    public void setNombrePersonne(int nombrePersonne) {
        this.nombrePersonne = nombrePersonne;
    }
}
