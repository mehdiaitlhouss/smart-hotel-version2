package com.miola.smarthotel.model;

public class Client extends User
{
    private long idClient; // l'id primaire de la table client
    private String pays;
    private String ville;

    public Client(String prenom, String nom, String cin, String email, String telephone, String adresse, String pays, String ville)
    {
        super(prenom, nom, cin, email, telephone, adresse);
        this.pays = pays;
        this.ville = ville;
    }

    public Client(long idUser, String prenom, String nom, String cin, String email, String telephone, String adresse, long idClient, String pays, String ville) {
        super(idUser, prenom, nom, cin, email, telephone, adresse);
        this.idClient = idClient;
        this.pays = pays;
        this.ville = ville;
    }

    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(long idClient) {
        this.idClient = idClient;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return "Client{" +super.toString()+
                ", pays='" + pays + '\'' +
                ", ville='" + ville + '\'' +
                '}';
    }
}
