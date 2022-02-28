package com.miola.smarthotel.controller.mainwindowcontrollers;

import com.miola.smarthotel.dao.*;
import com.miola.smarthotel.helpers.CurrentEmploye;
import com.miola.smarthotel.helpers.CurrentTime;
import com.miola.smarthotel.helpers.SceneName;
import com.miola.smarthotel.model.Client;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Code created by Andrius on 2020-09-27
 */

public class MainDashController
{
    @FXML
    private PieChart pieChartClimat;

    @FXML
    private PieChart pieChartChambres;

    @FXML
    private PieChart pieChartLampes;

    @FXML
    private Label clientNumber;

    @FXML
    private Label climatNumber;

    @FXML
    private Label lampeNumber;

    @FXML
    private Label reservationEnAttenteNumber;

    @FXML
    private Label reservationValideNumber;

    @FXML
    private Label reservationPayeeNumber;

    @FXML
    private Label reservationReserveeeNumber;

    @FXML
    private Button buttonTemp;

    @FXML private Button buttonEcl;

    @FXML
    private Label title;

    @FXML
    private Label date;

    @FXML
    private Label reservationNumber;

    @FXML
    private Label chambresNumber;

    @FXML
    private Label employeNumber;

    @FXML
    private Label userInfo;

    @FXML
    VBox secondSubVBox;

    @FXML
    VBox secondSubMenuList;

    @FXML
    Button secondMenu;

    @FXML
    private Circle shpActive;

    Random r = new Random();
    Map<VBox,VBox> map = new HashMap<VBox,VBox>();
    ClientDao clientDao = new ClientDao();
    ChambreDao chambreDao = new ChambreDao();

    private void loadPieChartClimat(PieChart pieChart)
    {
        ObservableList<PieChart.Data> data =
                FXCollections.observableArrayList(
                        new PieChart.Data("Active", r.nextInt(50)),
                        new PieChart.Data("Desactive", r.nextInt(50))
                );
        pieChart.setData(data);
    }

    private void loadPieChartChambre(PieChart pieChart)
    {
        ObservableList<PieChart.Data> data =
                FXCollections.observableArrayList(
                        new PieChart.Data("Reserver", chambreDao.countReservedChambre()),
                        new PieChart.Data("Reserver", chambreDao.countNonReservedChambre())
                );
        pieChart.setData(data);
    }

    private void loadPieChartLampes(PieChart pieChart)
    {
        ObservableList<PieChart.Data> data =
                FXCollections.observableArrayList(
                        new PieChart.Data("Active", r.nextInt(50)),
                        new PieChart.Data("Desactive", r.nextInt(50))
                );
        pieChart.setData(data);
    }

    @FXML
    private void initialize() {
        setTexts();
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
        loadPieChartChambre(pieChartChambres);
        loadPieChartClimat(pieChartClimat);
        loadPieChartLampes(pieChartLampes);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(10), event -> {
            loadPieChartChambre(pieChartChambres);
            loadPieChartClimat(pieChartClimat);
            loadPieChartLampes(pieChartLampes);
            reservationEnAttenteNumber.setText(String.valueOf(r.nextInt(11)));
            reservationPayeeNumber.setText(String.valueOf(r.nextInt(10)));
            reservationReserveeeNumber.setText(String.valueOf(r.nextInt(15)));
            reservationValideNumber.setText(String.valueOf(r.nextInt(20)));

        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void setTexts() {
        title.setText(SceneName.DASHBOARD.getName());
        date.setText(LocalDate.now().toString());
        reservationNumber.setText(getNumberOfReservations());
        chambresNumber.setText(getNumberOfChambres());
        employeNumber.setText(getNumberOfEmployes());
        clientNumber.setText(String.valueOf(new ClientDao().count()));
        climatNumber.setText("100");
        lampeNumber.setText("150");
        setUserInfo();
    }

    private String getNumberOfReservations() {
        return String.valueOf(new ReservationDao().count());
    }

    private String getNumberOfChambres() {
        return String.valueOf(new ChambreDao().count());
    }

    private String getNumberOfEmployes() {
        return String.valueOf(new EmployeDao().count());
    }

    private void setUserInfo()
    {
        userInfo.setText(String.format("User : %s %s", CurrentEmploye.getCurrentEmploye().getNom(), CurrentEmploye.getCurrentEmploye().getPrenom()));
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
    void showEmployesScreens(ActionEvent event) throws IOException {
        SceneController.getEmployesScene(event);
    }

    @FXML
    void exitProgram(ActionEvent event) {
        SceneController.close(event);
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
