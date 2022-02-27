package com.miola.smarthotel.helpers;

/**
 * Code created by Andrius on 2020-09-28
 */
public enum ScenePath
{

    ADD_PET("/view/addPet.fxml"),
    ADD_VET("/view/addVet.fxml"),
    ADD_VISIT("/view/addReservation.fxml"),
    HOME_ADMIN("/view/dashboardAdmin.fxml"),
    HOME_RECEPTIONNISTE("/view/dashbordReceptionniste.fxml"),
    LOGIN("/view/login.fxml"),
    CHAMBRES("/view/chambresDash.fxml"),
    SEARCH("/view/search.fxml"),
    EMPLOYES("/view/EmployesDash.fxml"),
    RESERVATIONS("/view/reservationDash.fxml"),
    CLIENTS("/view/clientsDash.fxml"),
    ADD_CLIENT("/view/addClient.fxml"),
    ADD_EMPLOYE("/view/addEmploye.fxml"),
    ADD_CHAMBRE("/view/addChambre.fxml"),
    ADD_RESERVATION("/view/addReservation.fxml"),
    ETAGES_TEMPERATURES("/view/etagesTemperature.fxml"),
    TEMPERATURES("/view/Temperature.fxml"),
    ECLAIRAGES("/view/Eclairage.fxml"),
    ETAGES_ECLAIRAGES("/view/etagesEclairage.fxml");

    private final String path;

    private ScenePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
