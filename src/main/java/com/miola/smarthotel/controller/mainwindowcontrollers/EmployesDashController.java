package com.miola.smarthotel.controller.mainwindowcontrollers;

import com.miola.smarthotel.controller.popupwindowcontrollers.NewWindowController;
import com.miola.smarthotel.dao.EmployeDao;
import com.miola.smarthotel.helpers.*;
import com.miola.smarthotel.model.Employe;
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
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Code created by Andrius on 2020-09-27
 */
public class EmployesDashController {

    @FXML
    private Label alertText;

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
    private TableColumn<Employe, String> employePrenom;

    @FXML
    private TableColumn<Employe, String> employeNom;

    @FXML
    private TableColumn<Employe, String> employeCin;

    @FXML
    private TableColumn<Employe, String> employeEmail;

    @FXML
    private TableColumn<Employe, String> employeAdresse;

    EmployeDao employeDao = new EmployeDao();
    ObservableList<Employe> employesObList = FXCollections.observableArrayList();

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
        employeNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        employePrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        employeCin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        employeEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        employeAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        employeNom.setCellFactory(TextFieldTableCell.forTableColumn());
        employePrenom.setCellFactory(TextFieldTableCell.forTableColumn());
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
    private void newWindow(ActionEvent event) throws IOException {
        NewWindowController.getNewEmploye();
        if(UpdateStatus.isEmployeAdded())
        {
            refreshScreen(event);
            UpdateStatus.setIsEmployeAdded(false);
        }
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

    @FXML
    public void showIotScreens(ActionEvent event)
    {

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

    @FXML
    public void changePrenom(TableColumn.CellEditEvent cellEditEvent)
    {
        Employe selectedEmploye = employesTable.getSelectionModel().getSelectedItem();
        selectedEmploye.setPrenom(cellEditEvent.getNewValue().toString());
        if(employeDao.update(selectedEmploye))
        {
            alertText.setText("Modification Enregistrer");
        }
        else
        {
            alertText.setText("Modification Non Enregistrer");
        }
    }

    @FXML
    public void changeCin(TableColumn.CellEditEvent<Employe, String> cellEditEvent)
    {
        Employe selectedEmploye = employesTable.getSelectionModel().getSelectedItem();
        selectedEmploye.setCin(cellEditEvent.getNewValue().toString());
        if(employeDao.update(selectedEmploye))
        {
            alertText.setText("Modification Enregistrer");
        }
        else
        {
            alertText.setText("Modification Non Enregistrer");
        }
    }

    @FXML
    public void changeName(TableColumn.CellEditEvent cellEditEvent)
    {
        Employe selectedEmploye = employesTable.getSelectionModel().getSelectedItem();
        selectedEmploye.setNom(cellEditEvent.getNewValue().toString());
        if(employeDao.update(selectedEmploye))
        {
            alertText.setText("Modification Enregistrer");
        }
        else
        {
            alertText.setText("Modification Non Enregistrer");
        }
    }

    @FXML
    public void changeEmail(TableColumn.CellEditEvent<Employe, String> cellEditEvent)
    {
        Employe selectedEmploye = employesTable.getSelectionModel().getSelectedItem();
        selectedEmploye.setEmail(cellEditEvent.getNewValue().toString());
        if(employeDao.update(selectedEmploye))
        {
            alertText.setText("Modification Enregistrer");
        }
        else
        {
            alertText.setText("Modification Non Enregistrer");
        }
    }

    @FXML
    public void changeAdresse(TableColumn.CellEditEvent<Employe, String> cellEditEvent)
    {
        Employe selectedEmploye = employesTable.getSelectionModel().getSelectedItem();
        selectedEmploye.setAdresse(cellEditEvent.getNewValue().toString());
        if(employeDao.update(selectedEmploye))
        {
            alertText.setText("Modification Enregistrer");
        }
        else
        {
            alertText.setText("Modification Non Enregistrer");
        }
    }

    @FXML
    public void consultEmploye(ActionEvent event)
    {

    }

    @FXML
    public void deleteEmploye(ActionEvent event) throws IOException
    {
        ObservableList<Employe> selectedRows = employesTable.getSelectionModel().getSelectedItems();
        for (Employe employe : selectedRows)
        {
            employeDao.delete(employe.getIdEmploye());
        }
        refreshScreen(event);
    }
}
