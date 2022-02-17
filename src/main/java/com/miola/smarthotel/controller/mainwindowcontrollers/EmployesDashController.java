package com.miola.smarthotel.controller.mainwindowcontrollers;

import com.miola.smarthotel.controller.popupwindowcontrollers.NewWindowController;
import com.miola.smarthotel.dao.VetDao;
import com.miola.smarthotel.helpers.*;
import com.miola.smarthotel.model.Veterinarian;
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
public class EmployesDashController {

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
    private TableView<Veterinarian> vetsTable;

    @FXML
    private TableColumn<Veterinarian, Long> idColumn;

    @FXML
    private TableColumn<Veterinarian, String> nameColumn;

    @FXML
    private TableColumn<Veterinarian, String> lastNameColumn;

    @FXML
    private TableColumn<Veterinarian, String> specialityColumn;

    @FXML
    private TableColumn<Veterinarian, String> addressColumn;

    VetDao vetDao = new VetDao();
    ObservableList<Veterinarian> vetsObList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        setTexts();
        setObList();
        fillTable();
        addTableSettings();
        exitBtn.setOnAction(SceneController::close);
    }

    private void setTexts() {
        title.setText(SceneName.EMPLOYES.getName());
        date.setText(LocalDate.now().toString());
        updateTime.setText("Last update: " + CurrentTime.getTime());
        setDbInfo();
        setUserInfo();
    }

    private void setObList() {
        vetsObList.clear();
        vetsObList.addAll(vetDao.getVets());
    }

    private void fillTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        specialityColumn.setCellValueFactory(new PropertyValueFactory<>("speciality"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        specialityColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        addressColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    private void addTableSettings() {
        vetsTable.setEditable(true);
        vetsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        vetsTable.setItems(getSortedList());
    }

    private SortedList<Veterinarian> getSortedList() {
        SortedList<Veterinarian> sortedList = new SortedList<>(getFilteredList());
        sortedList.comparatorProperty().bind(vetsTable.comparatorProperty());
        return sortedList;
    }

    private FilteredList<Veterinarian> getFilteredList() {
        FilteredList<Veterinarian> filteredList = new FilteredList<>(vetsObList, b -> true);
        searchBar.textProperty().addListener((observable, oldValue, newValue) ->
                filteredList.setPredicate(veterinarian -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (veterinarian.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (veterinarian.getLastName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (veterinarian.getSpeciality().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (veterinarian.getAddress().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else return veterinarian.getId().toString().contains(lowerCaseFilter);
                }));
        return filteredList;
    }

    @FXML
    private void changeNameCell(TableColumn.CellEditEvent<Veterinarian, String> editEvent) {
        Veterinarian selectedVet = vetsTable.getSelectionModel().getSelectedItem();
        selectedVet.setFirstName(editEvent.getNewValue().toString());
        vetDao.updateVet(selectedVet);
    }

    @FXML
    private void changeLastNameCell(TableColumn.CellEditEvent<Veterinarian, String> editEvent) {
        Veterinarian selectedVet = vetsTable.getSelectionModel().getSelectedItem();
        selectedVet.setLastName(editEvent.getNewValue().toString());
        vetDao.updateVet(selectedVet);
    }

    @FXML
    private void changeSpecCell(TableColumn.CellEditEvent<Veterinarian, String> editEvent) {
        Veterinarian selectedVet = vetsTable.getSelectionModel().getSelectedItem();
        selectedVet.setSpeciality(editEvent.getNewValue().toString());
        vetDao.updateVet(selectedVet);
    }

    @FXML
    private void changeAddressCell(TableColumn.CellEditEvent<Veterinarian, String> editEvent) {
        Veterinarian selectedVet = vetsTable.getSelectionModel().getSelectedItem();
        selectedVet.setAddress(editEvent.getNewValue().toString());
        vetDao.updateVet(selectedVet);
    }

    @FXML
    private void newWindow(ActionEvent event) throws IOException {
        NewWindowController.getNewVetWindow();
        if(UpdateStatus.isVetAdded()) {
            refreshScreen(event);
            UpdateStatus.setIsVetAdded(false);
        }
    }

    @FXML
    void deleteVets(ActionEvent event) throws IOException {
        ObservableList<Veterinarian> selectedRows = vetsTable.getSelectionModel().getSelectedItems();
        for (Veterinarian vet : selectedRows) {
            vetDao.deleteVet(vet);
        }
        refreshScreen(event);
    }

    private void setUserInfo() {
        userInfo.setText(String.format("User: %s", CurrentEmploye.getCurrentEmploye().getUserName()));
    }

    private void setDbInfo() {
        stats.setText(String.format("Total vets in database: %s", vetDao.getVetsNumber()));
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
    void refreshScreen(ActionEvent event) throws IOException {
        SceneController.getEmployesScene(event);
    }
}
