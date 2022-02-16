package com.miola.smarthotel.model;

import com.miola.smarthotel.helpers.EtatChambre;
import com.miola.smarthotel.helpers.TypeChambre;

public class Chambre
{
    private long id;
    private TypeChambre type;
    private int etage;
    private EtatChambre etat;

    public Chambre(long id, TypeChambre type, int etage, EtatChambre etat)
    {
        this.id = id;
        this.etage = etage;
        this.type = type;
        this.etat = etat;
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

    public void setId(long id) {
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
