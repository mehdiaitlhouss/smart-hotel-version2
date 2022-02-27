package com.miola.smarthotel.dao;

import com.miola.smarthotel.model.BDSingleton;
import com.miola.smarthotel.model.Eclairage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EclairageDao {
    public ArrayList<Eclairage> getAll(int nrEtage)
    {
        ArrayList<Eclairage> eclairages = new ArrayList<>();
        Eclairage eclairage = null;
        PreparedStatement s = null;
        ResultSet rs = null;
        String sqlQuery = "SELECT * FROM eclairage where etage=?";

        try
        {
            s = BDSingleton.getConn().prepareStatement(sqlQuery);
            s.setInt(1,nrEtage);
            rs = s.executeQuery();

            while (rs.next()) {
                eclairage = new Eclairage(rs.getInt("position"),rs.getInt("etage"),
                        rs.getInt("isOpen"));
                eclairage.setIdEcl(rs.getInt("idEclairage"));
                String imageSrc =rs.getInt("isOpen")==0 ?"/images/LampeOff.png":"/images/LampeOn.png";

                eclairage.setImgSrc(imageSrc);
                eclairages.add(eclairage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eclairages;
    }

    public boolean updateEclairage(int id,int etat) throws SQLException {
        String sql = "UPDATE eclairage SET isOpen=? WHERE idEclairage=?";

        PreparedStatement statement = null;

        statement = BDSingleton.getConn().prepareStatement(sql);
        statement.setInt(1, etat);
        statement.setInt(2, id);

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            return true;
        }
        else return false;


    }

}
