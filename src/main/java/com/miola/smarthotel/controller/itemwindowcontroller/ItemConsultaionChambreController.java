package com.miola.smarthotel.controller.itemwindowcontroller;


import com.miola.smarthotel.model.Reservation;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ItemConsultaionChambreController
{
    @FXML
    private Label idReservation;

    @FXML
    private Label dateReservation;

    @FXML
    private Label heureReservation;

    @FXML
    private Label dureeReservation;

    @FXML
    private Label employeName;

    @FXML
    private Label clientName;

    @FXML
    private Label etatResevation;

    @FXML
    private Label nombrePersonneReservation;

    public void setData(Reservation reservation)
    {
        idReservation.setText(String.valueOf(reservation.getId()));
        dateReservation.setText(reservation.getDateReservationString());
        heureReservation.setText(reservation.getHeureReservationString());
        dureeReservation.setText(reservation.getDureeSejourString());
        employeName.setText(reservation.getEmploye().getNom());
        clientName.setText(reservation.getClient().getNom());
        etatResevation.setText(reservation.getEtatString());
        nombrePersonneReservation.setText(reservation.getNombrePersonneString());
    }
}
