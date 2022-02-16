package com.miola.smarthotel.helpers;

/**
 * Code created by Andrius on 2020-09-28
 */
public enum ScenePath {

    ADD_PET("/view/addPet.fxml"),
    ADD_VET("/view/addVet.fxml"),
    ADD_VISIT("/view/addVisit.fxml"),
    HOME_ADMIN("/view/dashboardAdmin.fxml"),
    HOME_RECEPTIONNISTE("/view/dashbordReceptionniste.fxml"),
    LOGIN("/view/login.fxml"),
    CHAMBRES("/view/chambresDash.fxml"),
    SEARCH("/view/search.fxml"),
    EMPLOYES("/view/EmployesDash.fxml"),
    CLIENTS("/view/ReservationDash.fxml");

    private final String path;

    private ScenePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
