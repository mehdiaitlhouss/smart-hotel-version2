package com.miola.smarthotel.dao;

import com.miola.smarthotel.model.BDSingleton;
import com.miola.smarthotel.model.Chambre;
import com.miola.smarthotel.model.Reservation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ChambreReservationDao
{

    public ArrayList<Chambre> getChambreByReservation(int id)
    {
        ArrayList<Chambre> chambres = new ArrayList<>();
        Chambre chambre = null;
        ChambreDao chambreDao = new ChambreDao();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlQuery = "SELECT c.id AS id FROM reservationchambre AS rc INNER JOIN chambre AS c ON c.id = rc.idChambre WHERE rc.idReservation = ?";
        try
        {
            ps = BDSingleton.getConn().prepareStatement(sqlQuery);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next())
            {
                chambre = chambreDao.get(rs.getInt("id"));
                chambres.add(chambre);
            }
            ps.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return chambres;
    }

    public ArrayList<Reservation> getReservationByChambre(int id)
    {
        ArrayList<Reservation> reservations = new ArrayList<>();
        Reservation reservation = null;
        ReservationDao reservationDao = new ReservationDao();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlQuery = "SELECT r.id AS id FROM reservationchambre AS rc INNER JOIN reservation AS r ON r.id = rc.idReservation WHERE rc.idChambre = ?";
        try
        {
            ps = BDSingleton.getConn().prepareStatement(sqlQuery);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next())
            {
                reservation = reservationDao.get(rs.getInt("id"));
                reservations.add(reservation);
            }
            ps.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return reservations;
    }
}
