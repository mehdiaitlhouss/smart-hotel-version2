package com.miola.smarthotel.controller.mainwindowcontrollers;

import com.miola.smarthotel.controller.popupwindowcontrollers.NewWindowController;
import com.miola.smarthotel.dao.ClientDao;
import com.miola.smarthotel.helpers.CurrentEmploye;
import com.miola.smarthotel.helpers.CurrentTime;
import com.miola.smarthotel.helpers.SceneName;
import com.miola.smarthotel.helpers.UpdateStatus;
import com.miola.smarthotel.model.Chambre;
import com.miola.smarthotel.model.Client;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientDashController
{
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

    @FXML
    private TableColumn<Client, String> clientTelephone;

    @FXML
    private TableColumn<Client, String> clientPays;

    @FXML
    private TableColumn<Client, String> clientsVille;

    ClientDao clientDao = new ClientDao();
    ObservableList<Client> clientsObList = FXCollections.observableArrayList();

    Map<VBox,VBox> map = new HashMap<VBox,VBox>();
    @FXML
    VBox secondSubVBox;
    @FXML
    VBox secondSubMenuList;
    @FXML
    Button secondMenu;


    @FXML
    private void initialize()
    {
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
        clientName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        clientCin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        clientEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        clientAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        clientTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        clientPays.setCellValueFactory(new PropertyValueFactory<>("pays"));
        clientsVille.setCellValueFactory(new PropertyValueFactory<>("ville"));

        clientName.setCellFactory(TextFieldTableCell.forTableColumn());
        clientCin.setCellFactory(TextFieldTableCell.forTableColumn());
        clientEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        clientAdresse.setCellFactory(TextFieldTableCell.forTableColumn());
        clientTelephone.setCellFactory(TextFieldTableCell.forTableColumn());
        clientPays.setCellFactory(TextFieldTableCell.forTableColumn());
        clientsVille.setCellFactory(TextFieldTableCell.forTableColumn());


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

    private FilteredList<Client> getFilteredList()
    {
        FilteredList<Client> filteredList = new FilteredList<>(clientsObList, b -> true);
        searchBar.textProperty().addListener((observable, oldValue, newValue) ->
                filteredList.setPredicate(client -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (client.getNom().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (client.getPrenom().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (client.getCin().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (client.getEmail().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else return Integer.toString(client.getIdClient()).contains(lowerCaseFilter);
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
        for (Client client : selectedRows)
        {
            clientDao.delete(client.getIdUser());
        }
        refreshScreen(event);
    }

    private void setUserInfo() {
        userInfo.setText(String.format("User : %s", CurrentEmploye.getCurrentEmploye().getUserName()));
    }

    private void setDbInfo() {
        stats.setText(String.format("Total client in database: %s", clientDao.count()));
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
    void showEmployesScreens(ActionEvent event) throws IOException
    {
        SceneController.getEmployesScene(event);
    }

    @FXML
    public void showIotScreens(ActionEvent event)
    {

    }

    @FXML
    public void changeFullName(TableColumn.CellEditEvent<Client, String> clientStringCellEditEvent)
    {
        Client selectedClient = clientTable.getSelectionModel().getSelectedItem();
        String[] fullName = null;
        String ss = "";

        fullName = clientStringCellEditEvent.getNewValue().toString().split(" ");

        if(chkNamVldFnc(clientStringCellEditEvent.getNewValue().toString()))
        {
            for (int i = 1; i < fullName.length; i++)
            {
                ss += fullName[i] + " ";
            }

            selectedClient.setNom(fullName[0]);
            selectedClient.setPrenom(ss);

            if(clientDao.update(selectedClient))
            {
                alertText.setText("Modification enregistrer");
            }
            else
            {
                alertText.setText("Modification Non enregistrer");
            }

        }
        else
        {
            alertText.setText("Valeur Non Valide");
        }
    }

    public static boolean chkNamVldFnc(String namVar)
    {
        String namRegExpVar = "^[A-Z][a-z]{2,}(?: [A-Z][a-z]*)*$";

        Pattern pVar = Pattern.compile(namRegExpVar);
        Matcher mVar = pVar.matcher(namVar);
        return mVar.matches();
    }

    @FXML
    public void changeCin(TableColumn.CellEditEvent<Client, String> clientStringCellEditEvent)
    {

    }

    @FXML
    public void changeEmail(TableColumn.CellEditEvent<Client, String> clientStringCellEditEvent)
    {

    }

    @FXML
    public void changeAdresse(TableColumn.CellEditEvent<Client, String> clientStringCellEditEvent)
    {

    }

    @FXML
    public void changeTelephone(TableColumn.CellEditEvent<Client, String> clientStringCellEditEvent)
    {

    }

    @FXML
    public void changeVille(TableColumn.CellEditEvent<Client, String> clientStringCellEditEvent)
    {

    }

    @FXML
    public void changePays(TableColumn.CellEditEvent<Client, String> clientStringCellEditEvent)
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
}
