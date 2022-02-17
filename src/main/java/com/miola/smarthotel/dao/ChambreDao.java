package com.miola.smarthotel.dao;

import com.miola.smarthotel.model.BDSingleton;
import com.miola.smarthotel.model.Chambre;

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
    public ArrayList<Chambre> getAll() {
        return null;
    }
}
