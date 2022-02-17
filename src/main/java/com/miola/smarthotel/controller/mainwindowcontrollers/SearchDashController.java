package com.miola.smarthotel.controller.mainwindowcontrollers;

import com.miola.smarthotel.helpers.SceneName;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Code created by Andrius on 2020-09-28
 */
public class SearchDashController {

    protected static String searchValue = "";

    @FXML
    private Label title;

    @FXML
    private Label date;

    @FXML
    private TextField searchField;

    @FXML
    private Label searchQueryInfo;

    @FXML
    private Label searchKeyword;

    @FXML
    private void initialize() {
        title.setText(SceneName.SEARCH.getName());
        date.setText(LocalDate.now().toString());
        searchQueryInfo.setText("Search results for:");
        searchKeyword.setText(searchValue);
    }

    @FXML
    private synchronized void getSearchResults(ActionEvent event)  throws IOException {
        String searchTerm = searchField.getText();
        if(!searchTerm.equals("")) {
            searchValue = searchTerm;
            SceneController.getSearchScene(event);
        }
    }

    @FXML
    void showDashboard(ActionEvent event) throws IOException {
        SceneController.getAdminMainScene(event);
    }

    @FXML
    void showVisitScreen(ActionEvent event) throws IOException {
        SceneController.getReservationsScene(event);
    }

    @FXML
    void showPetScreen(ActionEvent event) throws IOException {
        SceneController.getChambresScene(event);
    }

    @FXML
    void showVetScreen(ActionEvent event) throws IOException {
        SceneController.getEmployesScene(event);
    }

    @FXML
    void exitProgram(ActionEvent event) {
        SceneController.close(event);
    }
}
