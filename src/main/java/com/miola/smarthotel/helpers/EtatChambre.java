package com.miola.smarthotel.helpers;

public enum EtatChambre
{
    RESERVER("réserver"),
    NON_RESERVEE("non réserver");

    private String etat;

    private EtatChambre(String etat)
    {
        this.etat = etat;
    }

    public String getEtat() {
        return etat;
    }
}
