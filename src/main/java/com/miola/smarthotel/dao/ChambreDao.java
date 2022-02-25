package com.miola.smarthotel.dao;

import com.miola.smarthotel.helpers.EtatChambre;
import com.miola.smarthotel.helpers.EtatReservation;
import com.miola.smarthotel.helpers.TypeChambre;
import com.miola.smarthotel.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ChambreDao implements Dao<Chambre>
{
    @Override
    public boolean update(Chambre chambre) {
        return false;
    }

    @Override
    public boolean delete(int id)
    {
        PreparedStatement ps = null;
        int rs;
        String sqlQuery = "DELETE FROM Chambre WHERE id = ?";
        try
        {
            ps = BDSingleton.getConn().prepareStatement(sqlQuery);
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
    public boolean add(Chambre chambre)
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlQuery = "INSERT INTO chambre(etage, idTypeChambre, idEtatChambre) VALUES (?, ?, ?)";

        try
        {
            ps = BDSingleton.getConn().prepareStatement(sqlQuery);
            ps.setInt(1, chambre.getEtage());
            ps.setInt(2, chambre.getType().getId());
            ps.setInt(3, chambre.getEtat().getId());

            if(ps.executeUpdate() != 1)
            {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean creat (Chambre chambre)
    {
        return false;
    }

    @Override
    public int count ()
    {
        Statement stm = null;
        ResultSet rs = null;
        int count = 0;

        try {
            stm = BDSingleton.getConn().createStatement();
            rs = stm.executeQuery("SELECT COUNT(*) FROM client");

            if (rs.next()) {
                count = rs.getInt(1);
            }

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public Chambre get ( int id){
        return null;
    }

    @Override
    public ArrayList<Chambre> getAll()
    {
        ArrayList<Chambre> chambres = new ArrayList<>();
        Chambre chambre = null;
        Statement s = null;
        ResultSet rs = null;
        String sqlQuery = "SELECT c.id AS id, etage, type, etat FROM chambre AS c " +
                            "INNER JOIN etatchambre AS e ON e.id = c.idEtatChambre " +
                            "INNER JOIN typechambre AS t ON t.id = c.idTypeChambre ";

        try
        {
            s = BDSingleton.getConn().createStatement();
            rs = s.executeQuery(sqlQuery);

            while (rs.next()) {
                chambre = new Chambre(rs.getInt("id"),
                        TypeChambre.valueOf(rs.getString("type")),
                        rs.getInt("etage"),
                        EtatChambre.valueOf(rs.getString("etat")));
                chambres.add(chambre);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chambres;
    }
}
