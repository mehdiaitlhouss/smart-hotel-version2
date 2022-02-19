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
    public boolean delete(int chambre) {
        return false;
    }

    @Override
    public boolean add(Chambre chambre) {
        return false;
    }

    @Override
    public boolean creat(Chambre chambre) {
        return false;
    }

    @Override
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

    @Override
    public Chambre get(int id) {
        return null;
    }

    @Override
    public ArrayList<Chambre> getAll()
    {
        ArrayList<Chambre> chambres = new ArrayList<>();
        Chambre chambre = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sqlQuery = "SELECT c.id AS id, etage, type, etat FROM chambre AS c " +
                            "INNER JOIN etatchambre AS e ON e.id = c.idEtatChambre " +
                            "INNER JOIN typechambre AS t ON t.id = c.idTypeChambre ";

        try {
            pstmt= BDSingleton.getConn().prepareStatement(sqlQuery);
            rs = pstmt.executeQuery();

            while (rs.next())
            {
                chambre = new Chambre(rs.getInt("id"),
                                      TypeChambre.valueOf(rs.getString("type")),
                                      rs.getInt("etage"),
                                      EtatChambre.valueOf(rs.getString("etat")));
                 chambres.add(chambre);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return chambres;
    }
}
