package com.miola.smarthotel.dao;

import com.miola.smarthotel.helpers.EtatReservation;
import com.miola.smarthotel.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ReservationDao implements Dao<Reservation>
{

    @Override
    public boolean update(Reservation reservation)
    {
        PreparedStatement ps = null;
        boolean tmp = true;

        try
        {
            ps = BDSingleton.getConn().prepareStatement("UPDATE reservation SET date = ?, heure = ?, duree = ?, nombrePersonne = ?, idEmploye = ?, idClient = ?, idEtatReservation = ? WHERE id = ?");
            ps.setDate(1, reservation.getDateReservation());
            ps.setTime(2, reservation.getHeureReservation());
            ps.setInt(3, reservation.getDureeSejour());
            ps.setInt(4, reservation.getNombrePersonne());
            ps.setInt(5, reservation.getEmploye().getIdEmploye());
            ps.setInt(6, reservation.getClient().getIdClient());
            ps.setInt(7, reservation.getEtat().getId());
            ps.setInt(8, reservation.getId());

            if(ps.executeUpdate() != 1)
            {
                tmp = false;
            }

            ps.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return tmp;
    }

    @Override
    public boolean delete(int id)
    {
        PreparedStatement ps = null;
        String sqlQuery = "DELETE FROM reservation WHERE id = ?";

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
    public boolean add(Reservation reservation) {
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
            rs = statement.executeQuery("SELECT COUNT(*) FROM Reservation");

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
    public Reservation get(int id)
    {
        Reservation reservation = null;
        Client client = null;
        Employe employe = null;
        ClientDao clientDao = new ClientDao();
        EmployeDao employeDao = new EmployeDao();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sqlQuery = "SELECT r.id AS id, date, heure, duree, nombrePersonne, idEmploye, idClient, etat " +
                "FROM reservation AS r " +
                "INNER JOIN etatreservation AS et ON et.id = r.idEtatReservation WHERE r.id = ?";

        try
        {
            pstmt= BDSingleton.getConn().prepareStatement(sqlQuery);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next())
            {
                employe = employeDao.get(rs.getInt("idEmploye"));
                client = clientDao.get(rs.getInt("idClient"));

                reservation = new Reservation(rs.getDate("date"),
                        rs.getTime("heure"),
                        rs.getInt("duree"),
                        rs.getInt("nombrePersonne"),
                        employe,
                        client,
                        EtatReservation.valueOf(rs.getString("etat")));
                reservation.setId(rs.getInt("id"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return reservation;
    }

    @Override
    public ArrayList<Reservation> getAll()
    {
        ArrayList<Reservation> reservations = new ArrayList<>();
        Reservation reservation = null;
        Client client = null;
        Employe employe = null;
        ClientDao clientDao = new ClientDao();
        EmployeDao employeDao = new EmployeDao();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sqlQuery = "SELECT r.id AS id, date, heure, duree, nombrePersonne, idEmploye, idClient, etat " +
                            "FROM reservation AS r " +
                            "INNER JOIN etatreservation AS et ON et.id = r.idEtatReservation";

        try {
            pstmt= BDSingleton.getConn().prepareStatement(sqlQuery);
            rs = pstmt.executeQuery();

            while (rs.next())
            {
                employe = employeDao.get(rs.getInt("idEmploye"));
                client = clientDao.get(rs.getInt("idClient"));

                reservation = new Reservation(rs.getDate("date"),
                                              rs.getTime("heure"),
                                              rs.getInt("duree"),
                                              rs.getInt("nombrePersonne"),
                                              employe,
                                              client,
                                              EtatReservation.valueOf(rs.getString("etat")));
                reservation.setId(rs.getInt("id"));
                reservations.add(reservation);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return reservations;
    }
}
