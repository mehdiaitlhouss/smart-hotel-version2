package com.miola.smarthotel.helpers;

/**
 * Code created by Andrius on 2020-10-06
 */
public final class UpdateStatus {

    private UpdateStatus() {

    }

    private static boolean isPetAdded;
    private static boolean isVetAdded;
    private static boolean isEmployeAdded;
    private static boolean IsChambreAdded;

    public static boolean isVetAdded() {
        return isVetAdded;
    }



    public static boolean isPetAdded() {
        return isPetAdded;
    }

    public static void setIsPetAdded(boolean isPet) {
        UpdateStatus.isPetAdded = isPet;
    }

    private static boolean isClientAdded;


    public static boolean isClientAdded() {
        return isClientAdded;
    }

    public static void setIsClientAdded(boolean isClientAdded) {
        UpdateStatus.isClientAdded = isClientAdded;
    }

    public static void setIsEmployeAdded(boolean isEmployeAdded) {
        UpdateStatus.isEmployeAdded = isEmployeAdded;
    }

    public static boolean isEmployeAdded() {
        return isEmployeAdded;
    }

    public static void setIsChambreAdded(boolean IsChambreAdded) {
        UpdateStatus.IsChambreAdded = IsChambreAdded;
    }

    public static boolean IsChambreAdded() {
        return IsChambreAdded;
    }



}
