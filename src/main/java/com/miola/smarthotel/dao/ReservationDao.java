package com.miola.smarthotel.dao;

import com.miola.smarthotel.model.BDSingleton;
import com.miola.smarthotel.model.Reservation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ReservationDao implements Dao<Reservation>
{

    @Override
    public boolean update(Reservation reservation) {
        return false;
    }

    @Override
    public boolean delete(int reservation) {
        return false;
    }

    @Override
    public boolean add(Reservation reservation) {
        return false;
    }

    @Override
    public boolean creat(Reservation reservation) {
        return false;
    }

    @Override
    public int count()
    {
        Statement statement = null;
        ResultSet rs = null;
        int count = 0;
        try
        {
            statement = BDSingleton.getConn().createStatement();
            rs = statement.executeQuery("SELECT COUNT(*) FROM Reservartion");
            count = rs.getInt(1);
            rs.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public Reservation get(int id)
    {
        return null;
    }

    @Override
    public ArrayList<Reservation> getAll()
    {
        ArrayList<Reservation> reservations = new ArrayList<>();
        Reservation reservation = null;

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sqlQuery = "SELECT  r.id," +
                            "r.date," +
                            "r.heure," +
                            "r.duree," +
                            "r.nombrePersonne," +
                            "r.idReceptionniste," +
                            "r.idClient," +
                            "et.etat" +
                            "FROM reservation AS r" +
                            "INNER JOIN etatreservation AS et ON et.id = r.idEtatReservation";

        try {
            pstmt= BDSingleton.getConn().prepareStatement(sqlQuery);
            rs = pstmt.executeQuery();

            while (rs.next())
            {

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return map;
    }
}
