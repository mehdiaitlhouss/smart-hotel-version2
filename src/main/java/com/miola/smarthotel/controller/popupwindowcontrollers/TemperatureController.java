package com.miola.smarthotel.controller.popupwindowcontrollers;

import com.miola.smarthotel.controller.mainwindowcontrollers.EtagesTemperatureController;
import com.miola.smarthotel.controller.mainwindowcontrollers.ItemTemperatureController;
import com.miola.smarthotel.dao.MyListener;
import com.miola.smarthotel.dao.TemperatureDao;
import com.miola.smarthotel.model.Temperature;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TemperatureController implements Initializable {
    @FXML
    private VBox chosenFruitCard;

    @FXML
    private Spinner<Integer> DegreeSpinner;


    @FXML
    private Label degree;

    @FXML
    private Label position;


    @FXML
    private GridPane grid;

    @FXML
    private ComboBox<String> nbrPersonne;
    private static Parent main;
    private static double x;
    private static double y;

    private List<Temperature> Temperatures = new ArrayList<>();
    private MyListener myListener;
    private Temperature temp;


    private void setChosenTemperature(Temperature temperature) {
        temp= new Temperature(temperature);
        position.setText("Position "+Integer.toString(temperature.getPosition()));
        SpinnerValueFactory<Integer> valueFactory1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, temperature.getDegree());
        DegreeSpinner.setValueFactory(valueFactory1);
        degree.setText(Integer.toString(temperature.getDegree())+"°C");
        chosenFruitCard.setStyle("-fx-background-color: #ff8000;-fx-background-radius: 30;");
    }
    TemperatureDao temperatureDao = new TemperatureDao();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(EtagesTemperatureController.nrEtage);
        Temperatures.addAll(temperatureDao.getAll(EtagesTemperatureController.nrEtage));
        if (Temperatures.size() > 0) {
            setChosenTemperature(Temperatures.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Temperature temperature) {
                    setChosenTemperature(temperature);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < Temperatures.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/ItemTemperature.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemTemperatureController itemController = fxmlLoader.getController();
                itemController.setData(Temperatures.get(i),myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateTemperature(ActionEvent ActionEvent) throws SQLException, IOException {

        if (temperatureDao.updateTemperature(temp.getIdTemp(),DegreeSpinner.getValue())){
            changeScreen(ActionEvent);

        }else {
            System.out.println("not work");
        }

    }

    private static void changeScreen(ActionEvent event) throws IOException {
        main = FXMLLoader.load(TemperatureController.class.getResource("/view/Temperature.fxml"));
        Scene visitScene = new Scene(main);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        controlDrag(window);
        window.setScene(visitScene);
        window.show();
    }
    public static void controlDrag(Stage stage) {
        main.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                x = stage.getX() - event.getScreenX();
                y = stage.getY() - event.getScreenY();
            }
        });
        main.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() + x);
                stage.setY(event.getScreenY() + y);
            }
        });
    }
}
