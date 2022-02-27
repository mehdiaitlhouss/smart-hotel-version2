package com.miola.smarthotel.model;

public class Temperature {
    private int idTemp;
    private int position;
    private int degree;
    private int etage;

    public Temperature(int position, int degree, int etage) {
        this.position = position;
        this.degree = degree;
        this.etage = etage;
    }

    public Temperature(Temperature t){
        this.position=t.getPosition();
        this.degree=t.getDegree();
        this.etage=t.getEtage();
        this.idTemp=t.getIdTemp();
    }
    public int getIdTemp() {
        return idTemp;
    }

    public void setIdTemp(int idTemp) {
        this.idTemp = idTemp;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public int getEtage() {
        return etage;
    }

    public void setEtage(int etage) {
        this.etage = etage;
    }
}
