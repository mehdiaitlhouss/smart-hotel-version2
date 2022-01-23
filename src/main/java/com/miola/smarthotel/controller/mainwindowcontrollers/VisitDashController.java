package com.miola.smarthotel.controller.mainwindowcontrollers;

import com.miola.smarthotel.controller.popupwindowcontrollers.NewWindowController;
import com.miola.smarthotel.dao.ConsultDao;
import com.miola.smarthotel.helpers.CurrentTime;
import com.miola.smarthotel.helpers.CurrentUser;
import com.miola.smarthotel.helpers.SceneName;
import com.miola.smarthotel.helpers.UpdateStatus;
import com.miola.smarthotel.model.Consult;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Code created by Andrius on 2020-09-27
 */
public class VisitDashController {

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
    private TableView<Consult> visitTable;

    @FXML
    private TableColumn<Consult, Long> visitId;

    @FXML
    private TableColumn<Consult, LocalDate> visitDate;

    @FXML
    private TableColumn<Consult, String> petId;

    @FXML
    private TableColumn<Consult, String> vetId;

    @FXML
    private TableColumn<Consult, String> descriptionId;

    ConsultDao consultDao = new ConsultDao();
    ObservableList<Consult> visitObList = FXCollections.observableArrayList();

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
        title.setText(SceneName.VISITS.getName());
        updateTime.setText("Last update: " + CurrentTime.getTime());
        setDbInfo();
        setUserInfo();
    }

    private void setObList() {
        visitObList.clear();
        visitObList.addAll(consultDao.getConsults());
    }

    private void fillTable() {
        visitId.setCellValueFactory(new PropertyValueFactory<>("id"));
        visitDate.setCellValueFactory(new PropertyValueFactory<>("visitDate"));
        petId.setCellValueFactory(new PropertyValueFactory<>("pet"));
        vetId.setCellValueFactory(new PropertyValueFactory<>("veterinarian"));
        descriptionId.setCellValueFactory(new PropertyValueFactory<>("description"));
        descriptionId.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    private void addTableSettings() {
        visitTable.setEditable(true);
        visitTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        visitTable.setItems(getSortedList());
    }

    private SortedList<Consult> getSortedList() {
        SortedList<Consult> sortedList = new SortedList<>(getFilteredListByDates());
        sortedList.comparatorProperty().bind(visitTable.comparatorProperty());
        return sortedList;
    }

    @FXML
    private FilteredList<Consult> getFilteredListByDates() {
        FilteredList<Consult> filteredItems = new FilteredList<>(getFilteredListByString());

        filteredItems.predicateProperty().bind(Bindings.createObjectBinding(() -> {
                    LocalDate minDate = dateFrom.getValue();
                    LocalDate maxDate = dateTo.getValue();

                    final LocalDate finalMin = minDate == null ? LocalDate.MIN : minDate;
                    final LocalDate finalMax = maxDate == null ? LocalDate.MAX : maxDate;

                    return ti -> !finalMin.isAfter(ti.getVisitDate()) && !finalMax.isBefore(ti.getVisitDate());
                },
                dateFrom.valueProperty(),
                dateTo.valueProperty()));
        return filteredItems;
    }

    private FilteredList<Consult> getFilteredListByString() {
        FilteredList<Consult> filteredList = new FilteredList<>(visitObList, b -> true);
        searchBar.textProperty().addListener((observable, oldValue, newValue) ->
                filteredList.setPredicate(consult -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (consult.getDescription().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (consult.getPet().toString().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (consult.getVeterinarian().toString().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else return consult.getId().toString().contains(lowerCaseFilter);
                }));
        return filteredList;
    }

    private void clearSearchResults() {
        clearBtn.setOnAction(event -> {
            dateFrom.setValue(null);
            dateTo.setValue(null);
            searchBar.setText("");
            visitTable.setItems(getSortedList());
        });
    }

    @FXML
    private void changeDescriptionCell(TableColumn.CellEditEvent editEvent) {
        Consult consult = visitTable.getSelectionModel().getSelectedItem();
        consult.setDescription(editEvent.getNewValue().toString());
        consultDao.updateConsult(consult);
    }

    @FXML
    void deleteVisit(ActionEvent event) throws IOException {
        ObservableList<Consult> selectedRows = visitTable.getSelectionModel().getSelectedItems();
        for (Consult consult : selectedRows) {
            consultDao.deleteConsult(consult);
        }
        refreshWindow(event);
    }

    @FXML
    private void newWindow(ActionEvent event) throws IOException {
        NewWindowController.getNewVisitWindow();
        if (UpdateStatus.isVisitAdded()) {
            refreshWindow(event);
            UpdateStatus.setIsVisitAdded(false);
        }
    }

    private void setUserInfo() {
        userInfo.setText(String.format("User: %s", CurrentUser.getCurrentUser().getUserName()));
    }

    private void setDbInfo() {
        stats.setText(String.format("Total visits in database: %s", consultDao.getNumberOfVisits()));
    }

    @FXML
    void refreshWindow(ActionEvent event) throws IOException {
        SceneController.getVisitScene(event);
    }

    @FXML
    void showPetsScreen(ActionEvent event) throws IOException {
        SceneController.getPetsScene(event);
    }

    @FXML
    void showVetsScreen(ActionEvent event) throws IOException {
        SceneController.getVetsScene(event);
    }

    @FXML
    void showDashboard(ActionEvent event) throws IOException {
        SceneController.getMainScene(event);
    }
}