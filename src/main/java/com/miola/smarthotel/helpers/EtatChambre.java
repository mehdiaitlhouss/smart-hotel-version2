package com.miola.smarthotel.helpers;

public enum EtatChambre
{
    RESERVER(1, "réserver", "la chambre est déjà réserver "),
    NON_RESERVER(2, "non réserver", "la chambre est non réserver");

    private int id;
    private String etat;
    private String description;

    private EtatChambre(int id, String etat, String description)
    {
        this.id = id;
        this.etat = etat;
        this.description = description;

    }

    public int getId() {
        return id;
    }

    public String getEtat() {
        return etat;
    }

    public String getDescription() {
        return description;
    }
}
