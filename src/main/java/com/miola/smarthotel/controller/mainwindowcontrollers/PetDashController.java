package com.miola.smarthotel.controller.mainwindowcontrollers;

import com.miola.smarthotel.controller.popupwindowcontrollers.NewWindowController;
import com.miola.smarthotel.dao.PetDao;
import com.miola.smarthotel.helpers.CurrentTime;
import com.miola.smarthotel.helpers.CurrentUser;
import com.miola.smarthotel.helpers.SceneName;
import com.miola.smarthotel.helpers.UpdateStatus;
import com.miola.smarthotel.model.Pet;
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
public class PetDashController {

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
    private TableView<Pet> petTable;

    @FXML
    private TableColumn<Pet, Long> idColumn;

    @FXML
    private TableColumn<Pet, LocalDate> dateColumn;

    @FXML
    private TableColumn<Pet, String> typeColumn;

    @FXML
    private TableColumn<Pet, Boolean> vaccineColumn;

    @FXML
    private TableColumn<Pet, String> ownerColumn;

    PetDao petDao = new PetDao();
    ObservableList<Pet> petsObList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        setTexts();
        setObList();
        fillTable();
        addTableSettings();
        exitBtn.setOnAction(SceneController::close);
    }

    private void setTexts() {
        title.setText(SceneName.PETS.getName());
        date.setText(LocalDate.now().toString());
        updateTime.setText("Last update: " + CurrentTime.getTime());
        setDbInfo();
        setUserInfo();
    }

    private void setObList() {
        petsObList.clear();
        petsObList.addAll(petDao.getPets());
    }

    private void fillTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("race"));
        vaccineColumn.setCellValueFactory(new PropertyValueFactory<>("isVaccinated"));
        ownerColumn.setCellValueFactory(new PropertyValueFactory<>("ownerName"));
        ownerColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        typeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        vaccineColumn.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item ? "Yes" : "No");
            }
        });
    }

    private void addTableSettings() {
        petTable.setEditable(true);
        petTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        petTable.setItems(getSortedList());
    }

    private SortedList<Pet> getSortedList() {
        SortedList<Pet> sortedList = new SortedList<>(getFilteredList());
        sortedList.comparatorProperty().bind(petTable.comparatorProperty());
        return sortedList;
    }

    private FilteredList<Pet> getFilteredList() {
        FilteredList<Pet> filteredList = new FilteredList<>(petsObList, b -> true);
        searchBar.textProperty().addListener((observable, oldValue, newValue) ->
                filteredList.setPredicate(pet -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (pet.getRace().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (pet.isVaccinatedStringValue().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (pet.getBirthDate().toString().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (pet.getOwnerName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else return pet.getId().toString().contains(lowerCaseFilter);
                }));
        return filteredList;
    }

    @FXML
    private void changeOwnerCell(TableColumn.CellEditEvent<Pet, String> editEvent) {
        Pet selectedPet = petTable.getSelectionModel().getSelectedItem();
        selectedPet.setOwnerName(editEvent.getNewValue());
        petDao.updatePet(selectedPet);
    }

    @FXML
    private void changeTypeCell(TableColumn.CellEditEvent<Pet, String> editEvent) {
        Pet selectedPet = petTable.getSelectionModel().getSelectedItem();
        selectedPet.setRace(editEvent.getNewValue());
        petDao.updatePet(selectedPet);
    }

    @FXML
    private void deletePets(ActionEvent event) throws Exception {
        ObservableList<Pet> selectedRows = petTable.getSelectionModel().getSelectedItems();
        for (Pet pet : selectedRows) {
            petDao.deletePet(pet);
        }
        refreshScreen(event);
    }

    @FXML
    private void newWindow(ActionEvent event) throws IOException {
        NewWindowController.getNewPetWindow();
        if(UpdateStatus.isPetAdded()) {
            refreshScreen(event);
            UpdateStatus.setIsPetAdded(false);
        }
    }

    private void setUserInfo() {
        userInfo.setText(String.format("User: %s", CurrentUser.getCurrentUser().getUserName()));
    }

    private void setDbInfo() {
        stats.setText(String.format("Total pets in database: %s", petDao.getNumberOfPets()));
    }

    @FXML
    private void refreshScreen(ActionEvent event) throws IOException {
        SceneController.getPetsScene(event);
    }

    @FXML
    private void showVisitScreen(ActionEvent event) throws IOException {
        SceneController.getVisitScene(event);
    }

    @FXML
    private void showVetScreen(ActionEvent event) throws IOException {
        SceneController.getVetsScene(event);
    }

    @FXML
    private void showDashboard(ActionEvent event) throws IOException {
        SceneController.getMainScene(event);
    }
}