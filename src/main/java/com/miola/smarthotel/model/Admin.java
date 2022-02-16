package com.miola.smarthotel.model;

public class Admin extends Employe
{
    public Admin(String prenom, String nom, String cin, String email, String telephone, String adresse, String userName, String password)
    {
        super(prenom, nom, cin, email, telephone, adresse, userName, password);
    }

    public Admin(long idEmploye, String prenom, String nom, String cin, String email, String telephone, String adresse, String userName, String password)
    {
        super(idEmploye, prenom, nom, cin, email, telephone, adresse , userName, password);
    }
}
