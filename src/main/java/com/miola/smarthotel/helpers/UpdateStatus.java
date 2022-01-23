package com.miola.smarthotel.helpers;

/**
 * Code created by Andrius on 2020-10-06
 */
public final class UpdateStatus {

    private UpdateStatus() {

    }

    private static boolean isPetAdded;
    private static boolean isVetAdded;
    private static boolean isVisitAdded;

    public static boolean isVetAdded() {
        return isVetAdded;
    }

    public static void setIsVetAdded(boolean isVetAdded) {
        UpdateStatus.isVetAdded = isVetAdded;
    }

    public static boolean isVisitAdded() {
        return isVisitAdded;
    }

    public static void setIsVisitAdded(boolean isVisitAdded) {
        UpdateStatus.isVisitAdded = isVisitAdded;
    }

    public static boolean isPetAdded() {
        return isPetAdded;
    }

    public static void setIsPetAdded(boolean isPet) {
        UpdateStatus.isPetAdded = isPet;
    }
}
