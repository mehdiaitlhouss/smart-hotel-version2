package com.miola.smarthotel.controller.mainwindowcontrollers;

import com.miola.smarthotel.helpers.ScenePath;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * Code created by Andrius on 2020-09-29
 */
public class SceneController {

    private static double x;
    private static double y;

    private static Parent main;

    public static void getInitialScene(Stage stage) throws IOException {
        main = FXMLLoader.load((SceneController.class.getResource(ScenePath.LOGIN.getPath())));
        Scene scene = new Scene(main);
        controlDrag(stage);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("SMART HOTEL");
        stage.setScene(scene);
        stage.show();
    }

    public static void getAdminMainScene(ActionEvent event) throws IOException {
        changeScreen(event, ScenePath.HOME_ADMIN.getPath());
    }

    public static void getRecepMainScene(ActionEvent event) throws IOException {
        changeScreen(event, ScenePath.HOME_RECEPTIONNISTE.getPath());
    }

    public static void getReservationsScene(ActionEvent event) throws IOException {
        changeScreen(event, ScenePath.RESERVATIONS.getPath());
    }

    public static void getEmployesScene(ActionEvent event) throws IOException {
        changeScreen(event, ScenePath.EMPLOYES.getPath());
    }

    public static void getChambresScene(ActionEvent event) throws IOException {
        changeScreen(event, ScenePath.CHAMBRES.getPath());
    }

    public static void getClientsScene(ActionEvent event) throws IOException {
        changeScreen(event, ScenePath.CLIENTS.getPath());
    }

    public static void getSearchScene(ActionEvent event) throws IOException {
        changeScreen(event, ScenePath.SEARCH.getPath());
    }

    private static void changeScreen(ActionEvent event, String path) throws IOException {
        main = FXMLLoader.load(SceneController.class.getResource(path));
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

    public static void close(ActionEvent actionEvent) {
        Node  source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
