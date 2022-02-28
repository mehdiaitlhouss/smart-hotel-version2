package com.miola.smarthotel.controller.popupwindowcontrollers;

import com.miola.smarthotel.controller.itemwindowcontroller.ItemConsultaionReservationController;
import com.miola.smarthotel.controller.mainwindowcontrollers.ReservationDashController;
import com.miola.smarthotel.controller.mainwindowcontrollers.SceneController;
import com.miola.smarthotel.dao.*;
import com.miola.smarthotel.helpers.ScenePath;
import com.miola.smarthotel.model.Chambre;
import com.miola.smarthotel.model.Client;
import com.miola.smarthotel.model.Employe;
import com.miola.smarthotel.model.Reservation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.util.ArrayList;

public class ConsultRservationDashController
{
    @FXML
    private Button exitBtn;

    @FXML
    private Label idReservation;

    @FXML
    private Label dateReservation;

    @FXML
    private Label heureReservation;

    @FXML
    private Label dureeReservation;

    @FXML
    private Label nombrePersonneReservation;

    @FXML
    private Label etatReservation;

    @FXML
    private Label idClient;

    @FXML
    private Label nomClient;

    @FXML
    private Label prenomClient;

    @FXML
    private Label cinClient;

    @FXML
    private Label emailClient;

    @FXML
    private Label telephoneClient;

    @FXML
    private Label adresseClient;

    @FXML
    private Label paysClient;

    @FXML
    private Label villeClient;

    @FXML
    private Label idEmploye;

    @FXML
    private Label nomEmploye;

    @FXML
    private Label prenomEmploye;

    @FXML
    private Label cinEmploye;

    @FXML
    private Label emailEmploye;

    @FXML
    private Label telephoneEmploye;

    @FXML
    private Label adresseEmploye;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    private Reservation reservation = ReservationDashController.selectedRowTmp;


    public void initialize() throws IOException
    {
        initialItems();
        initialiseChamps();
        exitBtn.setOnAction(SceneController::close);
    }

    public void initialiseChamps()
    {
        Client client = reservation.getClient();
        Employe employe = reservation.getEmploye();

        idReservation.setText(String.valueOf(reservation.getId()));
        etatReservation.setText(reservation.getEtatString());
        dureeReservation.setText(reservation.getDureeSejourString());
        nombrePersonneReservation.setText(reservation.getNombrePersonneString());
        dateReservation.setText(reservation.getDateReservationString());
        heureReservation.setText(reservation.getHeureReservationString());

        idClient.setText(String.valueOf(client.getIdClient()));
        nomClient.setText(client.getNom());
        prenomClient.setText(client.getPrenom());
        cinClient.setText(client.getCin());
        emailClient.setText(client.getEmail());
        adresseClient.setText(client.getAdresse());
        paysClient.setText(client.getPays());
        villeClient.setText(client.getVille());
        telephoneClient.setText(client.getTelephone());

        idEmploye.setText(String.valueOf(employe.getIdEmploye()));
        prenomEmploye.setText(employe.getPrenom());
        nomEmploye.setText(employe.getNom());
        cinEmploye.setText(employe.getCin());
        adresseEmploye.setText(employe.getAdresse());
        emailEmploye.setText(employe.getEmail());
        telephoneEmploye.setText(employe.getTelephone());
    }

    private void initialItems() throws IOException
    {
        ArrayList<Chambre> chambres = new ChambreReservationDao().getChambreByReservation(reservation.getId());

        int column = 0;
        int row = 1;

        for (int i = 0; i < chambres.size(); i++)
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(ScenePath.ITEM_CONSULTATION_RESRVATION.getPath()));
            AnchorPane anchorPane = fxmlLoader.load();
            ItemConsultaionReservationController itemController = fxmlLoader.getController();
            itemController.setData(chambres.get(i));

            if (column == 3)
            {
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
    }
}
