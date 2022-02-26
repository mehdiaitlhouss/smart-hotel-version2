package com.miola.smarthotel.controller.mainwindowcontrollers;

import com.miola.smarthotel.controller.popupwindowcontrollers.NewWindowController;
import com.miola.smarthotel.dao.ReservationDao;
import com.miola.smarthotel.helpers.*;
import com.miola.smarthotel.model.Reservation;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.beans.binding.Bindings;
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
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Code created by Andrius on 2020-09-27
 */
public class ReservationDashController
{
    @FXML
    private Label promptMessage;

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
    private TableColumn<Reservation, String> reservationId;

    @FXML
    private TableColumn<Reservation, String> reservationDate;

    @FXML
    private TableColumn<Reservation, String> reservationHeure;

    @FXML
    private TableColumn<Reservation, String> reservationDuree;

    @FXML
    private TableColumn<Reservation, String> reservationNombrePersonne;

    @FXML
    private TableColumn<Reservation, String> reservationEtat;

    Map<VBox,VBox> map = new HashMap<VBox,VBox>();
    @FXML
    VBox secondSubVBox;
    @FXML
    VBox secondSubMenuList;
    @FXML
    Button secondMenu;


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
        addMenusToMap();
        secondMenu.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                toolsSlider(secondSubVBox,secondSubMenuList);
                removeOtherMenus(secondSubVBox);
            }
        });
    }

    private void setTexts() {
        date.setText(LocalDate.now().toString());
        title.setText(SceneName.RESERVATIONS.getName());
        updateTime.setText("Last update: " + CurrentTime.getTime());
        setDbInfo();
        setUserInfo();
    }

    private void setObList() {
        reservationObList.clear();
        reservationObList.addAll(reservationDao.getAll());
    }

    private void fillTable()
    {
        reservationId.setCellValueFactory(new PropertyValueFactory<>("id"));
        reservationDate.setCellValueFactory(new PropertyValueFactory<>("dateReservationString"));
        reservationHeure.setCellValueFactory(new PropertyValueFactory<>("heureReservationString"));
        reservationDuree.setCellValueFactory(new PropertyValueFactory<>("dureeSejourString"));
        reservationNombrePersonne.setCellValueFactory(new PropertyValueFactory<>("nombrePersonneString"));
        reservationEtat.setCellValueFactory(new PropertyValueFactory<>("etatString"));
        reservationDate.setCellFactory(TextFieldTableCell.forTableColumn());
        reservationHeure.setCellFactory(TextFieldTableCell.forTableColumn());
        reservationDuree.setCellFactory(TextFieldTableCell.forTableColumn());
        reservationNombrePersonne.setCellFactory(TextFieldTableCell.forTableColumn());
        reservationEtat.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    private void addTableSettings() {
        reservationTable.setEditable(true);
        reservationTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        reservationTable.setItems(getSortedList());
    }

    private SortedList<Reservation> getSortedList()
    {
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

    private void clearSearchResults()
    {
        clearBtn.setOnAction(event -> {
            dateFrom.setValue(null);
            dateTo.setValue(null);
            searchBar.setText("");
            reservationTable.setItems(getSortedList());
        });
    }

    @FXML
    private void changeEtatReservationCell(TableColumn.CellEditEvent editEvent)
    {
        Reservation reservation = reservationTable.getSelectionModel().getSelectedItem();

        try
        {
            reservation.setEtat(EtatReservation.valueOf(editEvent.getNewValue().toString()));
        }
        catch (IllegalArgumentException e)
        {
            promptMessage.setText("Etat non valide");
            return;
        }

        if(reservationDao.update(reservation))
        {
            promptMessage.setText("Modification enregister");
        }
    }

    @FXML
    private void newWindow(ActionEvent event) throws IOException {
        NewWindowController.getNewReservationWindow();
        if (UpdateStatus.isEmployeAdded()) {
            refreshWindow(event);
            UpdateStatus.setIsEmployeAdded(false);
        }
    }


    private void setUserInfo()
    {
        userInfo.setText(String.format("User : %s", CurrentEmploye.getCurrentEmploye().getUserName()));
    }

    private void setDbInfo() {
        stats.setText(String.format("Total reservations in database : %s", reservationDao.count()));
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

    @FXML
    public void deleteReservation(ActionEvent event) throws IOException
    {
        ObservableList<Reservation> selectedRows = reservationTable.getSelectionModel().getSelectedItems();

        for (Reservation reservation : selectedRows)
        {
            reservationDao.delete(reservation.getId());
        }
        refreshWindow(event);
    }


    public void showIotScreens(ActionEvent event)
    {
    }

    public void afficheReservationAction(ActionEvent event)
    {
    }

    @FXML
    public void changeDateReservationCell(TableColumn.CellEditEvent<Reservation, String> reservationDate)
    {
        Reservation reservation = reservationTable.getSelectionModel().getSelectedItem();
        Date date = null;

        try
        {
            date = Date.valueOf(reservationDate.getNewValue().toString());
            reservation.setDateReservation(date);
        }
        catch (IllegalArgumentException e)
        {
            promptMessage.setText("Date non valide");
            return;
        }

        if(reservationDao.update(reservation))
        {
            promptMessage.setText("Modification enregister");
        }
    }

    @FXML
    public void changeHeureReservationCell(TableColumn.CellEditEvent<Reservation, String> reservationTime)
    {
        Reservation reservation = reservationTable.getSelectionModel().getSelectedItem();
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");

        try
        {
            Time timeValue = new Time(formatter.parse(reservationTime.getNewValue().toString()).getTime());
            reservation.setHeureReservation(timeValue);
        }
        catch (ParseException e)
        {
            promptMessage.setText("Time non valide");
            return;
        }

        if(reservationDao.update(reservation))
        {
            promptMessage.setText("Modification enregister");
        }
    }

    @FXML
    public void changeDureeReservationCell(TableColumn.CellEditEvent<Reservation, String> reservationDuree)
    {
        Reservation reservation = reservationTable.getSelectionModel().getSelectedItem();

        try
        {
            reservation.setDureeSejour(Integer.parseInt(reservationDuree.getNewValue().toString()));
        }
        catch (NumberFormatException e)
        {
            promptMessage.setText("Valeur non valide");
            return;
        }

        if(reservationDao.update(reservation))
        {
            promptMessage.setText("Modification enregister");
        }
    }

    @FXML
    public void changeNombrePersonneReservationCell(TableColumn.CellEditEvent<Reservation, String> reservationNumberPerson)
    {
        Reservation reservation = reservationTable.getSelectionModel().getSelectedItem();

        try
        {
            reservation.setNombrePersonne(Integer.parseInt(reservationNumberPerson.getNewValue().toString()));
        }
        catch (NumberFormatException e)
        {
            promptMessage.setText("valeur non valide");
            return;
        }

        if(reservationDao.update(reservation))
        {
            promptMessage.setText("Modification enregister");
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