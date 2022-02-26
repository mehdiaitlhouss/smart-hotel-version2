package com.miola.smarthotel.model;

import com.miola.smarthotel.helpers.EtatReservation;

import java.sql.Time;
import java.sql.Date;

public class Reservation
{
    private int id;
    private Date dateReservation;
    private String dateReservationString;
    private Time heureReservation;
    private String heureReservationString;
    private int dureeSejour;
    private String dureeSejourString;
    private int nombrePersonne;
    private String nombrePersonneString;
    private Employe employe;
    private Client client;
    private EtatReservation etat;
    private String etatString;

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
        this.etatString = etat.getEtat();
        this.dateReservationString = dateReservation.toString();
        this.heureReservationString = heureReservation.toString();
        this.dureeSejourString = String.valueOf(dureeSejour);
        this.nombrePersonneString = String.valueOf(nombrePersonne);
    }

    public String getDureeSejourString()
    {
        return dureeSejourString;
    }

    public void setDureeSejourString(String dureeSejourString)
    {
        this.dureeSejourString = dureeSejourString;
    }

    public String getNombrePersonneString()
    {
        return nombrePersonneString;
    }

    public void setNombrePersonneString(String nombrePersonneString)
    {
        this.nombrePersonneString = nombrePersonneString;
    }

    public String getDateReservationString()
    {
        return dateReservationString;
    }

    public void setDateReservationString(String dateReservationString)
    {
        this.dateReservationString = dateReservationString;
    }

    public String getHeureReservationString()
    {
        return heureReservationString;
    }

    public void setHeureReservationString(String heureReservationString)
    {
        this.heureReservationString = heureReservationString;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public EtatReservation getEtat() {
        return etat;
    }

    public void setEtat(EtatReservation etat) {
        this.etat = etat;
    }

    public String getEtatString() {
        return etatString;
    }

    public void setEtatString(String etatString) {
        this.etatString = etatString;
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

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", dateReservation=" + dateReservation +
                ", heureReservation=" + heureReservation +
                ", dureeSejour=" + dureeSejour +
                ", nombrePersonne=" + nombrePersonne +
                ", employe=" + employe +
                ", client=" + client +
                ", etat=" + etat +
                '}';
    }
}
