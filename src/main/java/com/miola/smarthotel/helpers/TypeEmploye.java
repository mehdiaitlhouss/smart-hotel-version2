package com.miola.smarthotel.helpers;

public enum TypeEmploye
{
    ADMIN("admin"),
    RECEP("recep");

    private String type;

    TypeEmploye(String type)
    {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
