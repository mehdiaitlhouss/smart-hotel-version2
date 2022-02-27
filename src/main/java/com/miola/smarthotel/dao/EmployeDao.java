package com.miola.smarthotel.dao;

import com.miola.smarthotel.helpers.TypeEmploye;
import com.miola.smarthotel.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class EmployeDao implements Dao<Employe>
{
    public Employe getConnectedEmploye(String userName, String password)
    {
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
                    employe.setIdEmploye(rs.getInt("idEmploye"));
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
                    employe.setIdEmploye(rs.getInt("idEmploye"));
                }
            }
            else
            {
                System.err.println("user not found");
            }
            pstmt.close();
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
            pstmt.setInt(1, id);
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
            pstmt.close();
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
                employe.setIdEmploye(rs.getInt("idEmploye"));
                    employes.add(employe);
            }
            pstmt.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return employes;
    }

    @Override
    public boolean update(Employe employe)
    {
        PreparedStatement ps;
        ResultSet rs = null;
        boolean tmp = false;

        try
        {
            ps= BDSingleton.getConn().prepareStatement("UPDATE employe SET userName = ?, password = ? WHERE id = ?");
            ps.setString(1, employe.getUserName());
            ps.setString(2, employe.getPassword());
            ps.setInt(3, employe.getIdEmploye());

            if(ps.executeUpdate() == 1)
            {
                ps= BDSingleton.getConn().prepareStatement("UPDATE user SET prenom = ?,nom = ?,cin = ?,email = ?,telephone = ?,adresse = ? WHERE id = ?");
                ps.setString(1, employe.getPrenom());
                ps.setString(2, employe.getNom());
                ps.setString(3, employe.getCin());
                ps.setString(4, employe.getEmail());
                ps.setString(5, employe.getTelephone());
                ps.setString(6, employe.getAdresse());
                ps.setInt(7, employe.getIdUser());

                if(ps.executeUpdate() == 1)
                {
                    tmp = true;
                }
            }
            ps.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return tmp;
    }

    @Override
    public boolean delete(int id)
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean tmp = true;

        try
        {
            ps = BDSingleton.getConn().prepareStatement("DELETE FROM employe WHERE idUser = ?");
            ps.setInt(1, id);

            if(ps.executeUpdate() != 1)
            {
                tmp = false;
            }

            ps = BDSingleton.getConn().prepareStatement("DELETE FROM user WHERE id = ?");
            ps.setInt(1, id);

            if(ps.executeUpdate() != 1)
            {
                tmp = false;
            }

            ps.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return tmp;
    }

    @Override
    public boolean add(Employe employe)
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id;
        boolean tmp = true;

        try
        {
            ps = BDSingleton.getConn().prepareStatement("INSERT INTO user(nom, prenom, email, cin, telephone, adresse) VALUES(?, ?, ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,employe.getNom());
            ps.setString(2,employe.getPrenom());
            ps.setString(3,employe.getEmail());
            ps.setString(4,employe.getCin());
            ps.setString(5,employe.getTelephone());
            ps.setString(6,employe.getAdresse());

            if(ps.executeUpdate() != 1)
            {
                System.out.println("user non inserer");
                tmp = false;
            }

            rs = ps.getGeneratedKeys();

            if(rs.next())
            {
                id = rs.getInt(1);

                ps = BDSingleton.getConn().prepareStatement("INSERT INTO employe(idUser, userName, password) VALUES(?,?,?)");

                ps.setInt(1, id);
                ps.setString(2,employe.getUserName());
                ps.setString(3, employe.getPassword());

                if(ps.executeUpdate() != 1)
                {
                    System.out.println("employe non inserer");
                    tmp = false;
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return tmp;
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