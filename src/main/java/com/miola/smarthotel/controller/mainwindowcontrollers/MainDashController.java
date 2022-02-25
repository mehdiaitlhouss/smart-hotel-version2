package com.miola.smarthotel.controller.mainwindowcontrollers;

import com.miola.smarthotel.dao.*;
import com.miola.smarthotel.helpers.CurrentEmploye;
import com.miola.smarthotel.helpers.CurrentTime;
import com.miola.smarthotel.helpers.SceneName;
import com.miola.smarthotel.model.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    private Label reservationInfoBlockName;

    @FXML
    private Label reservationNumber;

    @FXML
    private Label chambresInfoBlockName;

    @FXML
    private Label chambresNumber;

    @FXML
    private Label employeInfoBlockName;

    @FXML
    private Label employeNumber;

    @FXML
    private Label userInfo;

    @FXML
    private TableView<Client> clientTable;

    @FXML
    private TableColumn<Client, Integer> idClient;

    @FXML
    private TableColumn<Client, String> clientName;

    @FXML
    private TableColumn<Client, String> clientCin;

    @FXML
    private TableColumn<Client, String> clientEmail;

    @FXML
    private TableColumn<Client, String> clientAdresse;

    @FXML
    private Label visitInfoText;

    ClientDao clientDao = new ClientDao();

    @FXML
    private void initialize() {
        setTexts();
        fillTableWithData();
    }

    private void setTexts() {
        title.setText(SceneName.DASHBOARD.getName());
        date.setText(LocalDate.now().toString());
        reservationNumber.setText(getNumberOfReservations());
        chambresNumber.setText(getNumberOfChambres());
        employeNumber.setText(getNumberOfEmployes());
        reservationInfoBlockName.setText("Upcoming Reservation");
        chambresInfoBlockName.setText("Chambres in DB");
        employeInfoBlockName.setText("Employes in DB");
        visitInfoText.setText(getCalendarInformation());
        setUserInfo();
    }

    private void fillTableWithData()
    {
        idClient.setCellValueFactory(new PropertyValueFactory<>("idClient"));
        clientName.setCellValueFactory(new PropertyValueFactory<>("nom"));
        clientCin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        clientEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        clientAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        clientTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        clientTable.setItems(getVisitObservableList());
    }

    private ObservableList<Client> getVisitObservableList() {
        ObservableList<Client> clients = FXCollections.observableArrayList();
        clients.addAll(clientDao.getAll());
        return clients;
    }

    private String getNumberOfReservations() {
        return String.valueOf(new ReservationDao().count());
    }

    private String getNumberOfChambres() {
        return String.valueOf(new ChambreDao().count());
    }

    private String getNumberOfEmployes() {
        return String.valueOf(new EmployeDao().count());
    }

    private void setUserInfo() {
        userInfo.setText(String.format("User : %s", CurrentEmploye.getCurrentEmploye().getUserName()));
    }

    private String getCalendarInformation() {
        return String.format("Upcoming visits in next 14 days. Last update: %s", CurrentTime.getTime());
    }

    @FXML
    void showReservationsScreen(ActionEvent event) throws IOException {
        SceneController.getReservationsScene(event);
    }

    @FXML
    void showChambresScreen(ActionEvent event) throws IOException {
        SceneController.getChambresScene(event);
    }

    @FXML
    void showClientsScreen(ActionEvent event) throws IOException {
        SceneController.getClientsScene(event);
    }
    @FXML
    void showEmployesScreens(ActionEvent event) throws IOException {
        SceneController.getEmployesScene(event);
    }

    @FXML
    void refreshWindow(ActionEvent event) throws IOException {
        SceneController.getAdminMainScene(event);
    }

    @FXML
    void exitProgram(ActionEvent event) {
        SceneController.close(event);
    }

}
