package com.miola.smarthotel.helpers;

/**
 * Code created by Andrius on 2020-09-28
 */
public enum ScenePath {

    ADD_PET("/view/addPet.fxml"),
    ADD_VET("/view/addVet.fxml"),
    ADD_VISIT("/view/addVisit.fxml"),
    HOME("/view/dashboard.fxml"),
    LOGIN("/view/login.fxml"),
    PETS("/view/petDash.fxml"),
    SEARCH("/view/search.fxml"),
    VETS("/view/vetsDash.fxml"),
    VISITS("/view/visitDash.fxml");

    private final String path;

    private ScenePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
