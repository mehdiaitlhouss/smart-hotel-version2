package com.miola.smarthotel.model;

import com.miola.smarthotel.helpers.EtatChambre;
import com.miola.smarthotel.helpers.TypeChambre;

public class Chambre
{
    private int id;
    private TypeChambre type;
    private String typeString;
    private int nombreLit;
    private String nombreLitString;
    private int nombrePersonne;
    private String nombrePersonneString;
    private double prixParjour;
    private String prixParjourString;
    private int etage;
    private String etageString;
    private EtatChambre etat;
    private String etatChambreString;
    private String imagePath;

    public Chambre(int id, TypeChambre type, int etage, EtatChambre etat)
    {
        this.id = id;
        this.etage = etage;
        this.type = type;
        this.etat = etat;
        this.nombreLit = type.getNombreLit();
        this.nombrePersonne = type.getNombrePersonne();
        this.prixParjour = type.getPrixParJour();
        this.typeString = type.toString();
        this.nombreLitString = String.valueOf(type.getNombreLit());
        this.nombrePersonneString = String.valueOf(type.getNombrePersonne());
        this.prixParjourString = String.valueOf(type.getPrixParJour());
        this.etageString = String.valueOf(etage);
        this.etatChambreString = etat.toString();
        this.imagePath = type.getImagePath();
    }

    public Chambre() {
    }

    public String getTypeString()
    {
        return typeString;
    }

    public void setTypeString(String typeString)
    {
        this.typeString = typeString;
    }

    public String getNombreLitString()
    {
        return nombreLitString;
    }

    public void setNombreLitString(String nombreLitString)
    {
        this.nombreLitString = nombreLitString;
    }

    public String getNombrePersonneString()
    {
        return nombrePersonneString;
    }

    public void setNombrePersonneString(String nombrePersonneString)
    {
        this.nombrePersonneString = nombrePersonneString;
    }

    public String getPrixParjourString()
    {
        return prixParjourString;
    }

    public void setPrixParjourString(String prixParjourString)
    {
        this.prixParjourString = prixParjourString;
    }

    public String getEtageString()
    {
        return etageString;
    }

    public void setEtageString(String etageString)
    {
        this.etageString = etageString;
    }

    public String getEtatChambreString()
    {
        return etatChambreString;
    }

    public void setEtatChambreString(String etatChambreString)
    {
        this.etatChambreString = etatChambreString;
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

    public int getId() {
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
