package com.miola.smarthotel.model;

public class Receptionniste extends Employe
{
    public Receptionniste(String prenom, String nom, String cin, String email, String telephone, String adresse, String userName, String password)
    {
        super(prenom, nom, cin, email, telephone, adresse, userName, password);
    }

    public Receptionniste(int idEmploye, String prenom, String nom, String cin, String email, String telephone, String adresse, String userName, String password)
    {
        super(idEmploye, prenom, nom, cin, email, telephone, adresse, userName, password);
    }
}
