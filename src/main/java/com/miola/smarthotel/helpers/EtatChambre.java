package com.miola.smarthotel.helpers;

public enum EtatChambre
{
    RESERVER("réserver", "la chambre est déjà réserver "),
    NON_RESERVER("non réserver", "la chambre est non réserver");

    private String etat;
    private String description;

    private EtatChambre(String etat, String description)
    {
        this.etat = etat;
        this.description = description;

    }

    public String getEtat() {
        return etat;
    }

    public String getDescription() {
        return description;
    }
}
