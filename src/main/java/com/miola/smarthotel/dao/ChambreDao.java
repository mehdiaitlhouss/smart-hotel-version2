package com.miola.smarthotel.dao;

import com.miola.smarthotel.model.Chambre;

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
    public int count() {
        return 0;
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
