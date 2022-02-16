package com.miola.smarthotel.dao;

import java.util.ArrayList;

public interface Dao<T>
{
    public boolean update(T t);
    public boolean delete(int id);
    public boolean add(T t);
    public boolean creat(T t);
    public int count();
    public T get(int id);
    public ArrayList<T> getAll();
}
