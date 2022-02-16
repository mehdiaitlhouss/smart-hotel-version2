package com.miola.smarthotel.helpers;

public enum TypeChambre
{
    SINGLE_ROOM("une chambre pour une personne, avec un lit simple", 1, 1, 1000),
    TWIN_FOR_SOLO_USE("une chambre avec deux lits, conçue pour une personne", 2, 1, 2000),
    TWIN_ROOM("c'est une chambre concue pour 2 personne, elle se caracterise par la presence de deux lits simples", 2, 2, 2000),
    DOUBLE_ROOM("c'est une chambre double, mais avec un lit double", 2, 2, 4000),
    TRIPLE_ROOM("une chambre pour trois personnes, generalement avec trois lits simples", 3, 3, 5000),
    QUAD_ROOM("chambre pour quatre personnes", 4, 4, 6000);

    private String description;
    private int nombreLit;
    private int nombrePersonne;
    private double prixParJour;

    TypeChambre(String description, int nombreLit, int nombrePersonne, double prixParJour)
    {
        this.description = description;
        this.nombreLit = nombreLit;
        this.nombrePersonne = nombrePersonne;
        this.prixParJour = prixParJour;
    }

    public String getDescription() {
        return description;
    }

    public int getNombreLit() {
        return nombreLit;
    }

    public int getNombrePersonne() {
        return nombrePersonne;
    }

    public double getPrixParJour() {
        return prixParJour;
    }
}
