package com.miola.smarthotel.controller.mainwindowcontrollers;

import com.miola.smarthotel.controller.popupwindowcontrollers.NewWindowController;
import com.miola.smarthotel.dao.ReservationDao;
import com.miola.smarthotel.helpers.*;
import com.miola.smarthotel.model.Reservation;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

/**
 * Code created by Andrius on 2020-09-27
 */
public class ReservationDashController {

    @FXML
    private Label title;

    @FXML
    private Label date;

    @FXML
    private Label stats;

    @FXML
    private Label updateTime;

    @FXML
    private Button exitBtn;

    @FXML
    private Label userInfo;

    @FXML
    private TextField searchBar;

    @FXML
    private DatePicker dateFrom;

    @FXML
    private DatePicker dateTo;

    @FXML
    private Button clearBtn;

    @FXML
    private TableView<Reservation> reservationTable;

    @FXML
    private TableColumn<Reservation, Integer> reservationId;

    @FXML
    private TableColumn<Reservation, Date> reservationDate;

    @FXML
    private TableColumn<Reservation, Time> reservationHeure;

    @FXML
    private TableColumn<Reservation, Integer> reservationDuree;

    @FXML
    private TableColumn<Reservation, Integer> reservationNombrePersonne;

    @FXML
    private TableColumn<Reservation, EtatReservation> reservationEtat;


    ReservationDao reservationDao = new ReservationDao();
    ObservableList<Reservation> reservationObList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        setTexts();
        setObList();
        fillTable();
        addTableSettings();
        clearSearchResults();
        exitBtn.setOnAction(SceneController::close);
    }

    private void setTexts() {
        date.setText(LocalDate.now().toString());
        title.setText(SceneName.RESERVATIONS.getName());
        updateTime.setText("Last update: " + CurrentTime.getTime());
        setDbInfo();
        setUserInfo();

        for (Reservation r: reservationDao.getAll())
        {
            System.out.println(r);
        }
    }

    private void setObList() {
        reservationObList.clear();
        reservationObList.addAll(reservationDao.getAll());
    }

    private void fillTable()
    {
        reservationId.setCellValueFactory(new PropertyValueFactory<>("id"));
        reservationDate.setCellValueFactory(new PropertyValueFactory<>("dateReservation"));
        reservationHeure.setCellValueFactory(new PropertyValueFactory<>("heureReservation"));
        reservationDuree.setCellValueFactory(new PropertyValueFactory<>("dureeSejour"));
        reservationNombrePersonne.setCellValueFactory(new PropertyValueFactory<>("nombrePersonne"));
        reservationEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        //descriptionId.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    private void addTableSettings() {
        reservationTable.setEditable(true);
        reservationTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        reservationTable.setItems(getSortedList());
    }

    private SortedList<Reservation> getSortedList() {
        SortedList<Reservation> sortedList = new SortedList<>(getFilteredListByDates());
        sortedList.comparatorProperty().bind(reservationTable.comparatorProperty());
        return sortedList;
    }

    @FXML
    private FilteredList<Reservation> getFilteredListByDates() {
        FilteredList<Reservation> filteredItems = new FilteredList<>(getFilteredListByString());

        filteredItems.predicateProperty().bind(Bindings.createObjectBinding(() -> {
                    LocalDate minDate = dateFrom.getValue();
                    LocalDate maxDate = dateTo.getValue();

                    final LocalDate finalMin = minDate == null ? LocalDate.MIN : minDate;
                    final LocalDate finalMax = maxDate == null ? LocalDate.MAX : maxDate;

                    return ti -> !finalMin.isAfter(LocalDate.MIN) && !finalMax.isBefore(LocalDate.MAX);
                },
                dateFrom.valueProperty(),
                dateTo.valueProperty()));
        return filteredItems;
    }

    private FilteredList<Reservation> getFilteredListByString() {
        FilteredList<Reservation> filteredList = new FilteredList<>(reservationObList, b -> true);
        searchBar.textProperty().addListener((observable, oldValue, newValue) ->
                filteredList.setPredicate(reservation -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (reservation.getDateReservation().toString().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (reservation.getDateReservation().toString().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (reservation.getDateReservation().toString().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else return reservation.getDateReservation().toString().contains(lowerCaseFilter);
                }));
        return filteredList;
    }

    private void clearSearchResults() {
        clearBtn.setOnAction(event -> {
            dateFrom.setValue(null);
            dateTo.setValue(null);
            searchBar.setText("");
            reservationTable.setItems(getSortedList());
        });
    }

    @FXML
    private void changeDescriptionCell(TableColumn.CellEditEvent editEvent) {
        // il faut ajouter onEditCommit="#changeDescriptionCell" dans l'attrinue de la table
        Reservation reservation = reservationTable.getSelectionModel().getSelectedItem();
       // reservation.set(editEvent.getNewValue().toString());
        reservationDao.update(reservation);
    }

    @FXML
    void deleteVisit(ActionEvent event) throws IOException {
//        ObservableList<Reservation> selectedRows = reservationTable.getSelectionModel().getSelectedItems();
//        for (Reservation reservation : selectedRows) {
//            reservationDao.delete(reservation.getId());
//        }
//        refreshWindow(event);
    }

    @FXML
    private void newWindow(ActionEvent event) throws IOException {
        NewWindowController.getNewVisitWindow();
        if (UpdateStatus.isEmployeAdded()) {
            refreshWindow(event);
            UpdateStatus.setIsVisitAdded(false);
        }
    }

    private void setUserInfo() {
        userInfo.setText(String.format("User: %s", CurrentEmploye.getCurrentEmploye().getNom()));
    }

    private void setDbInfo() {
        stats.setText(String.format("Total reservations in database: %s", reservationDao.count()));
    }

    @FXML
    void refreshWindow(ActionEvent event) throws IOException {
        SceneController.getReservationsScene(event);
    }

    @FXML
    void showEmployesScreen(ActionEvent event) throws IOException {
        SceneController.getEmployesScene(event);
    }

    @FXML
    void showDashboard(ActionEvent event) throws IOException {
        SceneController.getAdminMainScene(event);
    }

    @FXML
    public void showChambresScreen(ActionEvent event) throws IOException {
        SceneController.getChambresScene(event);
    }

    @FXML
    public void showClientsScreen(ActionEvent actionEvent) throws IOException {
        SceneController.getClientsScene(actionEvent);
    }

    public void deleteReservation(ActionEvent event) {
    }


}