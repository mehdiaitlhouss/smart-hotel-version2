package com.miola.smarthotel.helpers;

public enum TypeChambre
{
    SINGLE_ROOM(1, "une chambre pour une personne, avec un lit simple", 1, 1, 1000, "/images/SINGLEROOM.png"),
    TWIN_FOR_SOLO_USE(2, "une chambre avec deux lits, conçue pour une personne", 2, 1, 2000, "/images/TWINFORSOLOUSE.png"),
    TWIN_ROOM(3, "c'est une chambre concue pour 2 personne, elle se caracterise par la presence de deux lits simples", 2, 2, 2000, "/images/TWINROOM.png"),
    DOUBLE_ROOM(4, "c'est une chambre double, mais avec un lit double", 2, 2, 4000, "/images/DOUBLEROOM.png"),
    TRIPLE_ROOM(5, "une chambre pour trois personnes, generalement avec trois lits simples", 3, 3, 5000, "/images/TRIPLEROOM.png"),
    QUAD_ROOM(6, "chambre pour quatre personnes", 4, 4, 6000, "/images/QUADROOM.png");

    private int id;
    private String description;
    private int nombreLit;
    private int nombrePersonne;
    private double prixParJour;
    private String imagePath;

    TypeChambre(int id, String description, int nombreLit, int nombrePersonne, double prixParJour, String imagePath)
    {
        this.id = id;
        this.description = description;
        this.nombreLit = nombreLit;
        this.nombrePersonne = nombrePersonne;
        this.prixParJour = prixParJour;
        this.imagePath = imagePath;
    }

    public static String[] getTableString()
    {
        String[] str = new String[TypeChambre.values().length];

        for (int i = 0; i < TypeChambre.values().length; i++)
        {
            str[i] = TypeChambre.values()[i].toString();
        }
        return str;
    }

    public int getId() {
        return id;
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

    public String getImagePath()
    {
        return imagePath;
    }
}
