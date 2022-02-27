package com.miola.smarthotel.controller.popupwindowcontrollers;

import com.miola.smarthotel.helpers.ScenePath;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * Code created by Andrius on 2020-10-02
 */

public class NewWindowController
{
    static double x;
    static double y;

    public static void getNewPetWindow() throws IOException
    {
        getPopUpWindow(ScenePath.ADD_PET.getPath());
    }

    public static Stage getNewVetWindow() throws IOException {
        Stage stage = new Stage();
        Pane main = FXMLLoader.load(NewWindowController.class.getResource(ScenePath.ADD_VET.getPath()));
        controlDrag(main, stage);
        stage.setScene(new Scene(main));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Pet Clinic CRM");
        stage.getScene();
        stage.showAndWait();
        return stage;
    }

    public static void getNewVisitWindow() throws IOException
    {
        getPopUpWindow(ScenePath.ADD_VISIT.getPath());
    }

    public static void getPopUpWindow(String path) throws IOException
    {
        Stage stage = new Stage();
        Pane main = FXMLLoader.load(NewWindowController.class.getResource(path));
        controlDrag(main, stage);
        stage.setScene(new Scene(main));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Pet Clinic CRM");
        stage.getScene();
        stage.showAndWait();
    }

    public static void controlDrag(Pane main, Stage stage) {
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

    public static void getNewClientWindow() throws IOException
    {
        getPopUpWindow(ScenePath.ADD_CLIENT.getPath());
    }

    public static void getNewEmploye() throws IOException
    {
        getPopUpWindow(ScenePath.ADD_EMPLOYE.getPath());
    }

    public static void getNewChambreWindow() throws IOException
    {
        getPopUpWindow(ScenePath.ADD_CHAMBRE.getPath());
    }

    public static void getNewReservationWindow() throws IOException
    {
        getPopUpWindow(ScenePath.ADD_RESERVATION.getPath());
    }
    public static void getTemperaturesWindow() throws IOException
    {
        getPopUpWindow(ScenePath.TEMPERATURES.getPath());
    }
    public static void getEclairagesWindow() throws IOException
    {
        getPopUpWindow(ScenePath.ECLAIRAGES.getPath());
    }
}
