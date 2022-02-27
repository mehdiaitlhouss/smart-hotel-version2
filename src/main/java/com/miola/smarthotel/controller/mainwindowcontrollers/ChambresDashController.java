package com.miola.smarthotel.controller.mainwindowcontrollers;

import com.miola.smarthotel.controller.popupwindowcontrollers.NewWindowController;
import com.miola.smarthotel.dao.ChambreDao;
import com.miola.smarthotel.helpers.*;
import com.miola.smarthotel.model.Chambre;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Code created by Andrius on 2020-09-27
 */
public class ChambresDashController
{
    @FXML private Button buttonTemp;

    @FXML private Button buttonEcl;

    @FXML
    private Label alertText;

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
    private TableColumn<Chambre, String> etageColumn;

    @FXML
    private TableColumn<Chambre, String> typeColumn;

    @FXML
    private TableColumn<Chambre, String> nombreLitColumn;

    @FXML
    private TableColumn<Chambre, String> nombrePersonneColumn;

    @FXML
    private TableColumn<Chambre, String> prixParJourColumn;

    @FXML
    private TableColumn<Chambre, String> etatChambreColumn;

    ChambreDao chambreDao = new ChambreDao();
    ObservableList<Chambre> chambresObList = FXCollections.observableArrayList();

    Map<VBox,VBox> map = new HashMap<VBox,VBox>();
    @FXML
    VBox secondSubVBox;
    @FXML
    VBox secondSubMenuList;
    @FXML
    Button secondMenu;


    @FXML
    private void initialize() {
        setTexts();
        setObList();
        fillTable();
        addTableSettings();
        exitBtn.setOnAction(SceneController::close);
        addMenusToMap();
        secondMenu.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                toolsSlider(secondSubVBox,secondSubMenuList);
                removeOtherMenus(secondSubVBox);
            }
        });
        buttonTemp.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    SceneController.getEtagesTemperaturesScene(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        buttonEcl.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    SceneController.getEtagesEclairagesScene(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
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
        etageColumn.setCellValueFactory(new PropertyValueFactory<>("etageString"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("typeString"));
        nombreLitColumn.setCellValueFactory(new PropertyValueFactory<>("nombreLitString"));
        nombrePersonneColumn.setCellValueFactory(new PropertyValueFactory<>("nombrePersonneString"));
        prixParJourColumn.setCellValueFactory(new PropertyValueFactory<>("prixParjourString"));
        etatChambreColumn.setCellValueFactory(new PropertyValueFactory<>("etatChambreString"));
        etageColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        typeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        etatChambreColumn.setCellFactory(TextFieldTableCell.forTableColumn());
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
        ObservableList<Chambre> selectedRows = chambreTable.getSelectionModel().getSelectedItems();
        for (Chambre chambre : selectedRows)
        {
            chambreDao.delete(chambre.getId());
        }
        refreshScreen(event);
    }

    @FXML
    private void newWindow(ActionEvent event) throws IOException {
      NewWindowController.getNewChambreWindow();
       if(UpdateStatus.IsChambreAdded()) {
            refreshScreen(event);
            UpdateStatus.setIsChambreAdded(false);
        }
    }

    private void setUserInfo() {
        userInfo.setText(String.format("User : %s", CurrentEmploye.getCurrentEmploye().getUserName()));
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

    public void showIotScreens(ActionEvent event)
    {

    }

    @FXML
    public void changeEtage(TableColumn.CellEditEvent<Chambre, String> chambreStringCellEditEvent)
    {
        Chambre selectedChambre = chambreTable.getSelectionModel().getSelectedItem();

        try
        {
            selectedChambre.setEtage(Integer.parseInt(chambreStringCellEditEvent.getNewValue().toString()));
        }
        catch(NumberFormatException e)
        {
            alertText.setText("Valeur Non Valide");
            return;
        }

        if(chambreDao.update(selectedChambre))
        {
            alertText.setText("Modification enregistrer");
        }
    }

    @FXML
    public void changeType(TableColumn.CellEditEvent<Chambre, String> chambreStringCellEditEvent)
    {
        Chambre selectedChambre = chambreTable.getSelectionModel().getSelectedItem();

        try
        {
            selectedChambre.setType(TypeChambre.valueOf(chambreStringCellEditEvent.getNewValue().toString()));

        }
        catch(IllegalArgumentException e)
        {
            alertText.setText("Type Non Valide");
            return;
        }

        if(chambreDao.update(selectedChambre))
        {
            alertText.setText("Modification enregistrer");
        }
    }

    @FXML
    public void changeEtate(TableColumn.CellEditEvent<Chambre, String> chambreStringCellEditEvent)
    {
        Chambre selectedChambre = chambreTable.getSelectionModel().getSelectedItem();

        try
        {
            selectedChambre.setEtat(EtatChambre.valueOf(chambreStringCellEditEvent.getNewValue().toString()));

        }
        catch(IllegalArgumentException e)
        {
            alertText.setText("Etat Non Valide");
            return;
        }

        if(chambreDao.update(selectedChambre))
        {
            alertText.setText("Modification enregistrer");
        }
    }
    public void addMenusToMap() {
        addMenusToMapImpl();
    }

    private void addMenusToMapImpl() {

        map.put(secondSubVBox, secondSubMenuList);

        for (Map.Entry<VBox,VBox> entry : map.entrySet()) {
            entry.getKey().getChildren().remove(entry.getValue());
        }
    }

    public void toolsSlider(VBox menu,VBox subMenu){
        toolsSliderImpl(menu,subMenu);
    }

    private void toolsSliderImpl(VBox menu,VBox subMenu) {
        if(menu.getChildren().contains(subMenu)){
            final FadeTransition transition = new FadeTransition(Duration.millis(500), menu);
            transition.setFromValue(0.5);
            transition.setToValue(1);
            transition.setInterpolator(Interpolator.EASE_IN);
            menu.getChildren().remove(subMenu);
            transition.play();
        }else{
            final FadeTransition transition = new FadeTransition(Duration.millis(500), menu);
            transition.setFromValue(0.5);
            transition.setToValue(1);
            transition.setInterpolator(Interpolator.EASE_IN);
            menu.getChildren().add(subMenu);
            transition.play();
        }
    }

    public void removeOtherMenus(VBox menu){
        removeOtherMenusImpl(menu);
    }
    private void removeOtherMenusImpl(VBox menu) {
        for (Map.Entry<VBox,VBox> entry : map.entrySet()) {
            if(!entry.getKey().equals(menu))
                entry.getKey().getChildren().remove(entry.getValue());
        }
    }
}