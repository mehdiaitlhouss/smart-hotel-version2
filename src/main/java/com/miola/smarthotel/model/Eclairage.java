package com.miola.smarthotel.model;

public class Eclairage {
    private int idEcl;
    private int position;
    private int etage;
    private int isOpen;
    private String ImgSrc;

    public Eclairage(int position, int etage, int isOpen) {
        this.position = position;
        this.etage = etage;
        this.isOpen = isOpen;
    }
    public Eclairage(Eclairage e){
        this.position=e.getPosition();
        this.isOpen=e.getIsOpen();
        this.etage=e.getEtage();
        this.idEcl=e.getIdEcl();
        this.ImgSrc=e.getImgSrc();
    }

    public String getImgSrc() {
        return ImgSrc;
    }

    public void setImgSrc(String imgSrc) {
        ImgSrc = imgSrc;
    }

    public int getIdEcl() {
        return idEcl;
    }

    public void setIdEcl(int idEcl) {
        this.idEcl = idEcl;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getEtage() {
        return etage;
    }

    public void setEtage(int etage) {
        this.etage = etage;
    }

    public int getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(int isOpen) {
        this.isOpen = isOpen;
    }
}
