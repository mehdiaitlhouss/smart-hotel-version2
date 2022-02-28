package com.miola.smarthotel.controller.itemwindowcontroller;

import com.miola.smarthotel.model.Chambre;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ItemConsultaionReservationController
{
    @FXML
    private Label etat;

    @FXML
    private Label etage;

    @FXML
    private ImageView imageChambre;

    public void setData(Chambre chambre)
    {
        etage.setText("Etage : " + chambre.getEtageString());
        etat.setText("Etat : " + chambre.getEtatChambreString());
        Image image = new Image(getClass().getResourceAsStream(chambre.getType().getImagePath()));
        imageChambre.setImage(image);
    }
}
