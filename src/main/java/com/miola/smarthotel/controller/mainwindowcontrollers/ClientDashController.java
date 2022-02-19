package com.miola.smarthotel.controller.mainwindowcontrollers;

import com.miola.smarthotel.controller.popupwindowcontrollers.NewWindowController;
import com.miola.smarthotel.dao.ClientDao;
import com.miola.smarthotel.helpers.CurrentEmploye;
import com.miola.smarthotel.helpers.CurrentTime;
import com.miola.smarthotel.helpers.SceneName;
import com.miola.smarthotel.helpers.UpdateStatus;
import com.miola.smarthotel.model.Client;
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

public class ClientDashController {
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
    private TableView<Client> clientTable;

    @FXML
    private TableColumn<Client, Long> idClient;

    @FXML
    private TableColumn<Client, String> clientName;

    @FXML
    private TableColumn<Client, String> clientCin;

    @FXML
    private TableColumn<Client, String> clientEmail;

    @FXML
    private TableColumn<Client, String> clientAdresse;

    ClientDao clientDao = new ClientDao();
    ObservableList<Client> clientsObList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        setTexts();
        setObList();
        fillTable();
        addTableSettings();
        exitBtn.setOnAction(SceneController::close);
    }

    private void setTexts() {
        title.setText(SceneName.CLIENTS.getName());
        date.setText(LocalDate.now().toString());
        updateTime.setText("Last update: " + CurrentTime.getTime());
        setDbInfo();
        setUserInfo();
    }

    private void setObList() {
        clientsObList.clear();
        clientsObList.addAll(clientDao.getAll());
    }

    private void fillTable() {
        idClient.setCellValueFactory(new PropertyValueFactory<>("idClient"));
        clientName.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        clientCin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        clientEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        clientAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        clientName.setCellFactory(TextFieldTableCell.forTableColumn());
        clientCin.setCellFactory(TextFieldTableCell.forTableColumn());
        clientEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        clientAdresse.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    private void addTableSettings() {
        clientTable.setEditable(true);
        clientTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        clientTable.setItems(getSortedList());
    }

    private SortedList<Client> getSortedList() {
        SortedList<Client> sortedList = new SortedList<>(getFilteredList());
        sortedList.comparatorProperty().bind(clientTable.comparatorProperty());
        return sortedList;
    }

    private FilteredList<Client> getFilteredList() {
        FilteredList<Client> filteredList = new FilteredList<>(clientsObList, b -> true);
        searchBar.textProperty().addListener((observable, oldValue, newValue) ->
                filteredList.setPredicate(Client -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (Client.getNom().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (Client.getNom().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (Client.getNom().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (Client.getNom().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else return Client.getNom().toString().contains(lowerCaseFilter);
                }));
        return filteredList;
    }

    @FXML
    private void changeNameCell(TableColumn.CellEditEvent<Client, String> editEvent) {
        Client selectedVet = clientTable.getSelectionModel().getSelectedItem();
        selectedVet.setNom(editEvent.getNewValue().toString());
       // clientDao.updateVet(selectedVet);
    }

    @FXML
    private void changeLastNameCell(TableColumn.CellEditEvent<Client, String> editEvent) {
        Client selectedVet = clientTable.getSelectionModel().getSelectedItem();
        selectedVet.setNom(editEvent.getNewValue().toString());
       // clientDao.updateVet(selectedVet);
    }

    @FXML
    private void changeSpecCell(TableColumn.CellEditEvent<Client, String> editEvent) {
        Client selectedVet = clientTable.getSelectionModel().getSelectedItem();
        selectedVet.setNom(editEvent.getNewValue().toString());
       // clientDao.updateVet(selectedVet);
    }

    @FXML
    private void changeAddressCell(TableColumn.CellEditEvent<Client, String> editEvent) {
        Client selectedVet = clientTable.getSelectionModel().getSelectedItem();
        selectedVet.setNom(editEvent.getNewValue().toString());
      //  clientDao.updateVet(selectedVet);
    }

    @FXML
    private void newWindow(ActionEvent event) throws IOException {
        NewWindowController.getNewClientWindow();
        if(UpdateStatus.isClientAdded())
        {
            refreshScreen(event);
            UpdateStatus.setIsClientAdded(false);
        }
    }

    @FXML
    void deleteClient(ActionEvent event) throws IOException {
        ObservableList<Client> selectedRows = clientTable.getSelectionModel().getSelectedItems();
        for (Client client : selectedRows) {
        //    clientDao.deleteVet(vet);
        }
        refreshScreen(event);
    }

    private void setUserInfo() {
        userInfo.setText(String.format("User: %s", CurrentEmploye.getCurrentEmploye().getUserName()));
    }

    private void setDbInfo() {
        stats.setText(String.format("Total vets in database: %s", clientDao.count()));
    }

    @FXML
    void showDashboard(ActionEvent event) throws IOException {
        SceneController.getAdminMainScene(event);
    }

    @FXML
    void refreshScreen(ActionEvent event) throws IOException {
        SceneController.getClientsScene(event);
    }

    @FXML
     void showChambresScreen(ActionEvent event) throws IOException {
        SceneController.getChambresScene(event);
    }
    @FXML
    void showReservationsScreen(ActionEvent event) throws IOException {
        SceneController.getReservationsScene(event);
    }

    @FXML
    void showEmployesScreens(ActionEvent event) throws IOException {
        SceneController.getEmployesScene(event);
    }
}
