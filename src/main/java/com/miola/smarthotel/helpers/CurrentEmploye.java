package com.miola.smarthotel.helpers;

import com.miola.smarthotel.model.Employe;

public class CurrentEmploye
{
    private static Employe empl;

    private CurrentEmploye()
    {

    }

    public static Employe getCurrentEmploye()
    {
        return CurrentEmploye.empl;
    }

    public static void setCurrentEmploye(Employe empl) {
        CurrentEmploye.empl = empl;
    }


}
