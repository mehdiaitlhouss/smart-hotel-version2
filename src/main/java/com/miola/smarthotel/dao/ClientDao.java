package com.miola.smarthotel.dao;

import com.miola.smarthotel.model.BDSingleton;
import com.miola.smarthotel.model.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ClientDao implements Dao<Client>
{
    public boolean creat(Client c)
    {
        return false;
    }

    public boolean update(Client client)
    {
        return false;
    }

    public boolean delete(int id) // id de user
    {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try
        {
            ps = BDSingleton.getConn().prepareStatement("DELETE FROM client WHERE idUser = ?");
            ps.setInt(1, id);

            if(ps.executeUpdate() != 1)
            {
                return false;
            }

            ps = BDSingleton.getConn().prepareStatement("DELETE FROM user WHERE id = ?");
            ps.setInt(1, id);

            if(ps.executeUpdate() != 1)
            {
                return false;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean add(Client client) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id;

        try
        {
            ps = BDSingleton.getConn().prepareStatement("INSERT INTO user(nom, prenom, email, cin, telephone, adresse) VALUES(?, ?, ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,client.getNom());
            ps.setString(2,client.getPrenom());
            ps.setString(3,client.getEmail());
            ps.setString(4,client.getCin());
            ps.setString(5,client.getTelephone());
            ps.setString(6,client.getAdresse());

            if(ps.executeUpdate() != 1)
            {
                System.out.println("user non inserer");
                return false;
            }

            rs = ps.getGeneratedKeys();

            if(rs.next())
            {
                id = rs.getInt(1);

                ps = BDSingleton.getConn().prepareStatement("INSERT INTO client(idUser, ville, pays) VALUES(?,?,?)");

                ps.setInt(1, id);
                ps.setString(2,client.getVille());
                ps.setString(3, client.getPays());

               if(ps.executeUpdate() != 1)
               {
                   System.out.println("client non inserer");
                   return false;
               }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return true;
    }

    public int count()
    {
        Statement stm = null;
        ResultSet rs = null;
        int count = 0;

        try
        {
            stm = BDSingleton.getConn().createStatement();
            rs = stm.executeQuery("SELECT COUNT(*) FROM client");

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

    public Client get(int id) // l'id de la table client
    {
        Client client = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sqlQuery = "SELECT c.idUser AS idUser, nom, prenom, telephone, email, cin, adresse, ville, pays " +
                            "FROM client AS c INNER JOIN user AS u ON c.idUser = u.id WHERE c.id = ?";

        try {
            pstmt= BDSingleton.getConn().prepareStatement(sqlQuery);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next())
            {
                client = new Client(rs.getString("prenom"),
                        rs.getString("nom"),
                        rs.getString("cin"),
                        rs.getString("email"),
                        rs.getString("telephone"),
                        rs.getString("adresse"),
                        rs.getString("pays"),
                        rs.getString("ville"));
                client.setIdUser(rs.getInt("idUser"));
                client.setIdClient(id);
            }
            rs.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return client;
    }

    public ArrayList<Client> getAll()
    {
        ArrayList<Client> clients = new ArrayList<>();
        Client client;
        PreparedStatement pstmt;
        ResultSet rs = null;
        String sqlQuery = "SELECT c.id AS idClient, c.idUser AS idUser, nom, prenom, telephone, email, cin, adresse, ville, pays  " +
                "FROM client AS c INNER JOIN user AS u ON c.idUser = u.id ";

        try {
            pstmt= BDSingleton.getConn().prepareStatement(sqlQuery);
            rs = pstmt.executeQuery();

            while (rs.next())
            {
                client = new Client(rs.getString("prenom"),
                        rs.getString("nom"),
                        rs.getString("cin"),
                        rs.getString("email"),
                        rs.getString("telephone"),
                        rs.getString("adresse"),
                        rs.getString("pays"),
                        rs.getString("ville"));
                client.setIdUser(rs.getInt("idUser")); // c'est l'id de la table user
                client.setIdClient(rs.getInt("idClient"));
                clients.add(client);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return clients;
    }
}
