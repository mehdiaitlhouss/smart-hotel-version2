package com.miola.smarthotel.model;

public abstract class Employe extends User
{
    private int idEmploye; // l'id primaire de la table employe
    private String userName;
    private String password;

    public Employe(String userName, String password)
    {
        this.userName = userName;
        this.password = password;
    }

    public Employe(String prenom, String nom, String cin, String email, String telephone, String adresse, String userName, String password)
    {
        super(prenom, nom, cin, email, telephone, adresse);
        this.userName = userName;
        this.password = password;
    }

    public Employe(int idEmploye, String prenom, String nom, String cin, String email, String telephone, String adresse, String userName, String password)
    {
        super(prenom, nom, cin, email, telephone, adresse);
        this.idEmploye = idEmploye;
        this.userName = userName;
        this.password = password;
    }

    public int getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Employe{" +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
