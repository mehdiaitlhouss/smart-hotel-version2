package com.miola.smarthotel.controller.mainwindowcontrollers;

import com.miola.smarthotel.dao.ConsultDao;
import com.miola.smarthotel.dao.PetDao;
import com.miola.smarthotel.dao.VetDao;
import com.miola.smarthotel.helpers.CurrentTime;
import com.miola.smarthotel.helpers.CurrentUser;
import com.miola.smarthotel.helpers.SceneName;
import com.miola.smarthotel.model.Consult;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Code created by Andrius on 2020-09-27
 */
public class MainDashController {

    @FXML
    private Label title;

    @FXML
    private Label date;

    @FXML
    private Label vetInfoBlockName;

    @FXML
    private Label vetNumber;

    @FXML
    private Label petInfoBlockName;

    @FXML
    private Label petNumber;

    @FXML
    private Label visitInfoBlockName;

    @FXML
    private Label visitNumber;

    @FXML
    private Label userInfo;

    @FXML
    private TableView<Consult> visitTable;

    @FXML
    private TableColumn<Consult, Long> visitId;

    @FXML
    private TableColumn<Consult, Long> visitDate;

    @FXML
    private TableColumn<Consult, String> petId;

    @FXML
    private TableColumn<Consult, String> vetId;

    @FXML
    private TableColumn<Consult, String> descriptionId;

    @FXML
    private Label visitInfoText;

    ConsultDao consultDao = new ConsultDao();

    @FXML
    private void initialize() {
        setTexts();
        fillTableWithData();
    }

    private void setTexts() {
        title.setText(SceneName.DASHBOARD.getName());
        date.setText(LocalDate.now().toString());
        vetNumber.setText(getNumberOfVets());
        petNumber.setText(getNumberOfPets());
        visitNumber.setText(getVisitNumber());
        visitInfoBlockName.setText("Upcoming visits");
        vetInfoBlockName.setText("Vets in DB");
        petInfoBlockName.setText("Pets in DB");
        visitInfoText.setText(getCalendarInformation());
        setUserInfo();
    }

    private void fillTableWithData() {
        visitId.setCellValueFactory(new PropertyValueFactory<>("id"));
        visitDate.setCellValueFactory(new PropertyValueFactory<>("visitDate"));
        petId.setCellValueFactory(new PropertyValueFactory<>("pet"));
        vetId.setCellValueFactory(new PropertyValueFactory<>("veterinarian"));
        descriptionId.setCellValueFactory(new PropertyValueFactory<>("description"));
        visitTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        visitTable.setItems(getVisitObservableList());
    }

    private ObservableList<Consult> getVisitObservableList() {
        ObservableList<Consult> consults = FXCollections.observableArrayList();
        consults.addAll(consultDao.getConsultInterval());
        return consults;
    }

    private String getNumberOfVets() {
        return String.valueOf(new VetDao().getVetsNumber());
    }

    private String getNumberOfPets() {
        return String.valueOf(new PetDao().getNumberOfPets());
    }

    private String getVisitNumber() {
        return String.valueOf(consultDao.getConsultInterval().size());
    }

    private void setUserInfo() {
        userInfo.setText(String.format("User: %s", CurrentUser.getCurrentUser().getUserName()));
    }

    private String getCalendarInformation() {
        return String.format("Upcoming visits in next 14 days. Last update: %s", CurrentTime.getTime());
    }

    @FXML
    void showVisitScreen(ActionEvent event) throws IOException {
        SceneController.getVisitScene(event);
    }

    @FXML
    void showVetScreen(ActionEvent event) throws IOException {
        SceneController.getVetsScene(event);
    }

    @FXML
    void showPetScreen(ActionEvent event) throws IOException {
        SceneController.getPetsScene(event);
    }

    @FXML
    void refreshWindow(ActionEvent event) throws IOException {
        SceneController.getMainScene(event);
    }

    @FXML
    void exitProgram(ActionEvent event) {
        SceneController.close(event);
    }
}
