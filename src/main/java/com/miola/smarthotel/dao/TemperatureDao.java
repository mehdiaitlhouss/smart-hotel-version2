package com.miola.smarthotel.dao;

import com.miola.smarthotel.model.BDSingleton;
import com.miola.smarthotel.model.Temperature;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TemperatureDao {
    public ArrayList<Temperature> getAll(int nrEtage)
    {
        ArrayList<Temperature> temperatures = new ArrayList<>();
        Temperature temperature = null;
        PreparedStatement s = null;
        ResultSet rs = null;
        String sqlQuery = "SELECT * FROM temperature where etage=?";

        try
        {
            s = BDSingleton.getConn().prepareStatement(sqlQuery);
            s.setInt(1,nrEtage);
            rs = s.executeQuery();


            while (rs.next()) {
                temperature = new Temperature(rs.getInt("position"),rs.getInt("degree"),
                        rs.getInt("etage"));
                temperature.setIdTemp(rs.getInt("idTemp"));
                temperatures.add(temperature);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temperatures;
    }

    public boolean updateTemperature(int id,int degree) throws SQLException {
        String sql = "UPDATE temperature SET degree=? WHERE idTemp=?";

        PreparedStatement statement = null;

        statement = BDSingleton.getConn().prepareStatement(sql);
        statement.setInt(1, degree);
        statement.setInt(2, id);

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            return true;
        }
        else return false;


    }
}
