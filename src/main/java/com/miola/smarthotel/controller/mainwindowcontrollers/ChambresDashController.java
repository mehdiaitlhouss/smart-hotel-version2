package com.miola.smarthotel.controller.mainwindowcontrollers;

import com.miola.smarthotel.dao.ChambreDao;
import com.miola.smarthotel.helpers.*;
import com.miola.smarthotel.model.Chambre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Screen;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Code created by Andrius on 2020-09-27
 */
public class ChambresDashController {

    @FXML
    private Label title; // titre de la fenetre reservation

    @FXML
    private Label date; // la date courant

    @FXML
    private Label stats; // la totale du reservation dans la base de donnees

    @FXML
    private Label updateTime; // date du dernier mise a jour des reservation

    @FXML
    private Button exitBtn; // boutton de sortir de la fenetre

    @FXML
    private Label userInfo; // champe indique le nom de l'utilisateur courant

    @FXML
    private TextField searchBar;

    @FXML
    private TableView<Chambre> chambreTable;

    @FXML
    private TableColumn<Chambre, Long> idColumn;

    @FXML
    private TableColumn<Chambre, Integer> etageColumn;

    @FXML
    private TableColumn<Chambre, String> typeColumn;

    @FXML
    private TableColumn<Chambre, Integer> nombreLitColumn;

    @FXML
    private TableColumn<Chambre, Integer> nombrePersonneColumn;

    @FXML
    private TableColumn<Chambre, Double> prixParJourColumn;

    @FXML
    private TableColumn<Chambre, EtatChambre> etatChambreColumn;

    ChambreDao chambreDao = new ChambreDao();
    ObservableList<Chambre> chambresObList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        setTexts();
        setObList();
        fillTable();
        addTableSettings();
        exitBtn.setOnAction(SceneController::close);
    }

    private void setTexts() {
        title.setText(SceneName.CHAMBRES.getName());
        date.setText(LocalDate.now().toString());
        updateTime.setText("Last update: " + CurrentTime.getTime());
        setDbInfo();
        setUserInfo();
    }

    private void setObList() {
        chambresObList.clear();
        chambresObList.addAll(chambreDao.getAll());
    }

    private void fillTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        etageColumn.setCellValueFactory(new PropertyValueFactory<>("etage"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        nombreLitColumn.setCellValueFactory(new PropertyValueFactory<>("nombreLit"));
        nombrePersonneColumn.setCellValueFactory(new PropertyValueFactory<>("nombrePersonne"));
        prixParJourColumn.setCellValueFactory(new PropertyValueFactory<>("prixParjour"));
        etatChambreColumn.setCellValueFactory(new PropertyValueFactory<>("etat"));


        /*ownerColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        typeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        vaccineColumn.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item ? "Yes" : "No");
            }
        });*/
    }

    private void addTableSettings() {
        chambreTable.setEditable(true);
        chambreTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        chambreTable.setItems(getSortedList());
    }

    private SortedList<Chambre> getSortedList() {
        SortedList<Chambre> sortedList = new SortedList<>(getFilteredList());
        sortedList.comparatorProperty().bind(chambreTable.comparatorProperty());
        return sortedList;
    }

    private FilteredList<Chambre> getFilteredList() {
        FilteredList<Chambre> filteredList = new FilteredList<>(chambresObList, b -> true);
        searchBar.textProperty().addListener((observable, oldValue, newValue) ->
                filteredList.setPredicate(chambre -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (chambre.getType().toString().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (chambre.getType().toString().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (chambre.getType().toString().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (chambre.getType().toString().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else return chambre.getType().toString().contains(lowerCaseFilter);
                }));
        return filteredList;
    }

    @FXML
    private void changeOwnerCell(TableColumn.CellEditEvent<Chambre, String> editEvent) {
//        Chambre selectedChambre = chambreTable.getSelectionModel().getSelectedItem();
//        selectedChambre.setOwnerName(editEvent.getNewValue());
//        chambreDao.updateChambre(selectedChambre);
    }

    @FXML
    private void changeTypeCell(TableColumn.CellEditEvent<Chambre, String> editEvent) {
//        Chambre selectedChambre = chambreTable.getSelectionModel().getSelectedItem();
//        selectedChambre.setRace(editEvent.getNewValue());
//        chambreDao.updateChambre(selectedChambre);
    }

    @FXML
    private void deleteChambres(ActionEvent event) throws Exception {
//        ObservableList<Chambre> selectedRows = chambreTable.getSelectionModel().getSelectedItems();
//        for (Chambre Chambre : selectedRows) {
//            chambreDao.deleteChambre(Chambre);
//        }
//        refreshScreen(event);
    }

    @FXML
    private void newWindow(ActionEvent event) throws IOException {
//        NewWindowController.getNewChambreWindow();
//        if(UpdateStatus.isChambreAdded()) {
//            refreshScreen(event);
//            UpdateStatus.setIsChambreAdded(false);
//        }
    }

    private void setUserInfo() {
        userInfo.setText(String.format("User: %s", CurrentEmploye.getCurrentEmploye().getUserName()));
    }

    private void setDbInfo() {
        stats.setText(String.format("Total Chambres in database: %s", chambreDao.count()));
    }

    @FXML
    private void refreshScreen(ActionEvent event) throws IOException {
        SceneController.getChambresScene(event);
    }

    @FXML
    private void showReservationsScreen(ActionEvent event) throws IOException {
        SceneController.getReservationsScene(event);
    }

    @FXML
    private void showScreen(ActionEvent event) throws IOException {
        SceneController.getEmployesScene(event);
    }

    @FXML
    private void showDashboard(ActionEvent event) throws IOException {
        SceneController.getAdminMainScene(event);
    }
    @FXML
    void showClientsScreen(ActionEvent event) throws IOException {
        SceneController.getClientsScene(event);
    }
    @FXML
    void showEmployesScreen(ActionEvent event) throws IOException {
        SceneController.getEmployesScene(event);
    }
}