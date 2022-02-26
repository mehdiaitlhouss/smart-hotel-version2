package com.miola.smarthotel.controller.mainwindowcontrollers;

import com.miola.smarthotel.controller.popupwindowcontrollers.NewWindowController;
import com.miola.smarthotel.dao.ClientDao;
import com.miola.smarthotel.dao.EmployeDao;
import com.miola.smarthotel.dao.VetDao;
import com.miola.smarthotel.helpers.*;
import com.miola.smarthotel.model.Client;
import com.miola.smarthotel.model.Employe;
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
    private TableView<Employe> employesTable;

    @FXML
    private TableColumn<Employe, Long> idEmploye;

    @FXML
    private TableColumn<Employe, String> employeName;

    @FXML
    private TableColumn<Employe, String> employeCin;

    @FXML
    private TableColumn<Employe, String> employeEmail;

    @FXML
    private TableColumn<Employe, String> employeAdresse;

    EmployeDao employeDao = new EmployeDao();
    ObservableList<Employe> employesObList = FXCollections.observableArrayList();

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
        employesObList.clear();
        employesObList.addAll(employeDao.getAll());
    }

    private void fillTable() {
        idEmploye.setCellValueFactory(new PropertyValueFactory<>("idEmploye"));
        employeName.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        employeCin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        employeEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        employeAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        employeName.setCellFactory(TextFieldTableCell.forTableColumn());
        employeCin.setCellFactory(TextFieldTableCell.forTableColumn());
        employeEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        employeAdresse.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    private void addTableSettings() {
        employesTable.setEditable(true);
        employesTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        employesTable.setItems(getSortedList());
    }

    private SortedList<Employe> getSortedList() {
        SortedList<Employe> sortedList = new SortedList<>(getFilteredList());
        sortedList.comparatorProperty().bind(employesTable.comparatorProperty());
        return sortedList;
    }

    private FilteredList<Employe> getFilteredList() {
        FilteredList<Employe> filteredList = new FilteredList<>(employesObList, b -> true);
        searchBar.textProperty().addListener((observable, oldValue, newValue) ->
                filteredList.setPredicate(Employe -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (Employe.getNom().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (Employe.getNom().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (Employe.getNom().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (Employe.getNom().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else return Employe.getNom().toString().contains(lowerCaseFilter);
                }));
        return filteredList;
    }

    @FXML
    private void changeNameCell(TableColumn.CellEditEvent<Client, String> editEvent) {
        Employe selectedVet = employesTable.getSelectionModel().getSelectedItem();
        selectedVet.setNom(editEvent.getNewValue().toString());
        // clientDao.updateVet(selectedVet);
    }

    @FXML
    private void changeLastNameCell(TableColumn.CellEditEvent<Client, String> editEvent) {
        Employe selectedVet = employesTable.getSelectionModel().getSelectedItem();
        selectedVet.setNom(editEvent.getNewValue().toString());
        // clientDao.updateVet(selectedVet);
    }

    @FXML
    private void changeSpecCell(TableColumn.CellEditEvent<Client, String> editEvent) {
        Employe selectedVet = employesTable.getSelectionModel().getSelectedItem();
        selectedVet.setNom(editEvent.getNewValue().toString());
        // clientDao.updateVet(selectedVet);
    }

    @FXML
    private void changeAddressCell(TableColumn.CellEditEvent<Client, String> editEvent) {
        Employe selectedVet = employesTable.getSelectionModel().getSelectedItem();
        selectedVet.setNom(editEvent.getNewValue().toString());
        //  clientDao.updateVet(selectedVet);
    }

    @FXML
    private void newWindow(ActionEvent event) throws IOException {
        NewWindowController.getNewEmploye();
        if(UpdateStatus.isEmployeAdded())
        {
            refreshScreen(event);
            UpdateStatus.setIsEmployeAdded(false);
        }
    }

    @FXML
    void deleteVets(ActionEvent event) throws IOException {
        ObservableList<Employe> selectedRows = employesTable.getSelectionModel().getSelectedItems();
        for (Employe emp : selectedRows) {
            //    clientDao.deleteVet(vet);
        }
        refreshScreen(event);
    }

    private void setUserInfo() {
        userInfo.setText(String.format("User: %s", CurrentEmploye.getCurrentEmploye().getUserName()));
    }

    private void setDbInfo() {
        stats.setText(String.format("Total vets in database: %s", employeDao.count()));
    }

    @FXML
    void showDashboard(ActionEvent event) throws IOException {
        SceneController.getAdminMainScene(event);
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
    void refreshScreen(ActionEvent event) throws IOException {
        SceneController.getEmployesScene(event);
    }

    public void showIotScreens(ActionEvent event)
    {

    }
}
