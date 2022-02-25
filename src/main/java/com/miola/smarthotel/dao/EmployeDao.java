package com.miola.smarthotel.dao;

import com.miola.smarthotel.helpers.TypeEmploye;
import com.miola.smarthotel.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Code created by Andrius on 2020-10-08
 */
public class EmployeDao implements Dao<Employe>
{
    public Employe getConnectedEmploye(String userName, String password)
    {
        // methode lancer lors de la connexion (page login) possede comme entree l'userName et le password du employe
        // et retourne soit l'admin ou le receptionniste depande en fait la diffrence d'apres le type de l'employe de la table
        // employe dans la base de donne

        Employe employe = null;

        PreparedStatement pstmt;
        ResultSet rs = null;
        String sqlQuery = "SELECT e.id AS idEmploye, e.idUser AS idUser, prenom, nom, cin, email, telephone, adresse, type, userName, password" +
                          " FROM employe AS e INNER JOIN user AS u ON e.idUser = u.id WHERE userName = ? AND password = ?";

        try {
            pstmt= BDSingleton.getConn().prepareStatement(sqlQuery);
            pstmt.setString(1,userName);
            pstmt.setString(2,password);
            rs = pstmt.executeQuery();

            if (rs.next())
            {
                if (TypeEmploye.ADMIN.getType().equals(rs.getString("type")))
                {
                    employe = new Admin(rs.getString("prenom"),
                            rs.getString("nom"),
                            rs.getString("cin"),
                            rs.getString("email"),
                            rs.getString("telephone"),
                            rs.getString("adresse"),
                            rs.getString("userName"),
                            rs.getString("password"));
                    employe.setIdUser(rs.getInt("idUser"));
                    employe.setIdEmploye(rs.getLong("idEmploye"));
                }
                else
                {
                    employe = new Receptionniste(rs.getString("prenom"),
                            rs.getString("nom"),
                            rs.getString("cin"),
                            rs.getString("email"),
                            rs.getString("telephone"),
                            rs.getString("adresse"),
                            rs.getString("userName"),
                            rs.getString("password"));
                    employe.setIdUser(rs.getInt("idUser"));
                    employe.setIdEmploye(rs.getLong("idEmploye"));
                }
            }
            else
            {
                System.err.println("user not found");
            }
            rs.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return employe;
    }



    public Employe get(int id)
    {
        Employe employe = null;
        PreparedStatement pstmt;
        ResultSet rs = null;
        String sqlQuery = "SELECT e.idUser AS idUser, prenom, nom, cin, email, telephone, adresse, userName, password" +
                         " FROM employe AS e INNER JOIN user AS u ON e.idUser = u.id WHERE e.id = ?";

        try
        {
            pstmt= BDSingleton.getConn().prepareStatement(sqlQuery);
            pstmt.setLong(1,id);
            rs = pstmt.executeQuery();

            if (rs.next())
            {
                    employe = new Receptionniste(rs.getString("prenom"),
                            rs.getString("nom"),
                            rs.getString("cin"),
                            rs.getString("email"),
                            rs.getString("telephone"),
                            rs.getString("adresse"),
                            rs.getString("userName"),
                            rs.getString("password"));
                    employe.setIdUser(rs.getInt("idUser"));
                    employe.setIdEmploye(id);
            }
            rs.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return employe;
    }

    public ArrayList<Employe> getAll()
    {
        ArrayList<Employe> employes = new ArrayList<>();
        Employe employe;
        PreparedStatement pstmt;
        ResultSet rs = null;
        String sqlQuery = "SELECT e.id AS idEmploye, e.idUser AS idUser, nom, prenom, telephone, email, cin, adresse, userName, password  " +
                          "FROM employe AS e INNER JOIN user AS u ON e.idUser = u.id " +
                          "WHERE type = \"recep\"";

        try {
            pstmt= BDSingleton.getConn().prepareStatement(sqlQuery);
            rs = pstmt.executeQuery();

            while (rs.next())
            {
                    employe = new Receptionniste(rs.getString("prenom"),
                            rs.getString("nom"),
                            rs.getString("cin"),
                            rs.getString("email"),
                            rs.getString("telephone"),
                            rs.getString("adresse"),
                            rs.getString("userName"),
                            rs.getString("password"));
                    employe.setIdUser(rs.getInt("idUser")); // c'est l'id de la table user
                employe.setIdEmploye(rs.getLong("idEmploye"));
                    employes.add(employe);
            }
            rs.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return employes;
    }

    @Override
    public boolean update(Employe employe) {
        return false;
    }

    @Override
    public boolean delete(int employe) {
        return false;
    }

    @Override
    public boolean add(Employe employe) {
        return false;
    }

    @Override
    public boolean creat(Employe employe) {
        return false;
    }

    public int count()
    {
        Statement stm = null;
        ResultSet rs = null;
        int count = 0;

        try
        {
            stm = BDSingleton.getConn().createStatement();
            rs = stm.executeQuery("SELECT COUNT(*) FROM employe WHERE type = \"recep\"");

            if(rs.next())
            {
                count = rs.getInt(1);
            }

            rs.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return count;
    }
}