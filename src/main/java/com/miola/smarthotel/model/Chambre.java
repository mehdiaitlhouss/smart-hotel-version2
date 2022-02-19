package com.miola.smarthotel.model;

import com.miola.smarthotel.helpers.EtatChambre;
import com.miola.smarthotel.helpers.TypeChambre;

public class Chambre
{
    private int id;
    private TypeChambre type;
    private int nombreLit;
    private int nombrePersonne;
    private double prixParjour;
    private int etage;
    private EtatChambre etat;

    public Chambre(int id, TypeChambre type, int etage, EtatChambre etat)
    {
        this.id = id;
        this.etage = etage;
        this.type = type;
        this.etat = etat;
        this.nombreLit = type.getNombreLit();
        this.nombrePersonne = type.getNombrePersonne();
        this.prixParjour = type.getPrixParJour();
    }

    public Chambre() {
    }

    public int getNombreLit() {
        return nombreLit;
    }

    public void setNombreLit(int nombreLit) {
        this.nombreLit = nombreLit;
    }

    public int getNombrePersonne() {
        return nombrePersonne;
    }

    public void setNombrePersonne(int nombrePersonne) {
        this.nombrePersonne = nombrePersonne;
    }

    public double getPrixParjour() {
        return prixParjour;
    }

    public void setPrixParjour(double prixParjour) {
        this.prixParjour = prixParjour;
    }

    public long getId() {
        return id;
    }

    public TypeChambre getType() {
        return type;
    }

    public int getEtage() {
        return etage;
    }

    public EtatChambre getEtat() {
        return etat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(TypeChambre type) {
        this.type = type;
    }

    public void setEtage(int etage) {
        this.etage = etage;
    }

    public void setEtat(EtatChambre etat) {
        this.etat = etat;
    }
}
