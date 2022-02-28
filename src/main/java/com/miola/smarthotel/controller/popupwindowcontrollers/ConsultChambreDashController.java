package com.miola.smarthotel.controller.popupwindowcontrollers;

import com.miola.smarthotel.controller.itemwindowcontroller.ItemConsultaionChambreController;
import com.miola.smarthotel.controller.itemwindowcontroller.ItemConsultaionReservationController;
import com.miola.smarthotel.controller.mainwindowcontrollers.ChambresDashController;
import com.miola.smarthotel.controller.mainwindowcontrollers.ReservationDashController;
import com.miola.smarthotel.controller.mainwindowcontrollers.SceneController;
import com.miola.smarthotel.dao.ChambreReservationDao;
import com.miola.smarthotel.dao.ReservationDao;
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

public class ConsultChambreDashController
{
    @FXML
    private Label idChambre;

    @FXML
    private Label etageChambre;

    @FXML
    private Label typeChambre;

    @FXML
    private Label descriptionChambre;

    @FXML
    private Label nombreLitChambre;

    @FXML
    private Label nombrePersonneChambre;

    @FXML
    private Label prixParJourChambre;

    @FXML
    private Label etatChambre;

    @FXML
    private Button exitBtn;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    private Chambre chambre = ChambresDashController.chambreSelectionnerTmp;

    public void initialize() throws IOException
    {
        initialItems();
        initialiseChamps();
        exitBtn.setOnAction(SceneController::close);
    }

    public void initialiseChamps()
    {
        idChambre.setText("ID : " + String.valueOf(chambre.getId()));
        etageChambre.setText("ETAGE : " + chambre.getEtageString());
        typeChambre.setText("TYPE : " + chambre.getTypeString());
        descriptionChambre.setText("DESCRIPTION : " + chambre.getType().getDescription());
        nombreLitChambre.setText("NOMBRE DE LIT : " + chambre.getNombreLitString());
        nombrePersonneChambre.setText("NOMBRE DE PERSONNE : " + chambre.getNombrePersonneString());
        prixParJourChambre.setText("PRIX PAR JOUR : " + chambre.getPrixParjourString());
        etatChambre.setText("ETAT : " + chambre.getEtatChambreString());
    }

    private void initialItems() throws IOException
    {
        ArrayList<Reservation> reservations = new ChambreReservationDao().getReservationByChambre(chambre.getId());

        int column = 0;
        int row = 1;

        for (int i = 0; i < reservations.size(); i++)
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(ScenePath.ITEM_CONSULTATION_CHAMBRE.getPath()));
            AnchorPane anchorPane = fxmlLoader.load();
            ItemConsultaionChambreController itemController = fxmlLoader.getController();
            itemController.setData(reservations.get(i));

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
