package com.miola.smarthotel.controller.mainwindowcontrollers;

import com.miola.smarthotel.dao.MyListenerEclairage;
import com.miola.smarthotel.model.Eclairage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ItemEclairageController {
    @FXML
    private Label position;

    @FXML
    private Label etatLampe;

    @FXML
    private ImageView img;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListenerEclairage.onClickListener(eclairage);
    }

    private Eclairage eclairage;
    private MyListenerEclairage myListenerEclairage;

    public void setData(Eclairage eclairage, MyListenerEclairage myListenerEclairage) {
        this.eclairage = eclairage;
        this.myListenerEclairage = myListenerEclairage;
        position.setText("Position "+Integer.toString(eclairage.getPosition()));
        String etat =eclairage.getIsOpen()==0 ?"OFF":"ON";
        etatLampe.setText(etat);
        Image image = new Image(getClass().getResourceAsStream(eclairage.getImgSrc()));
        img.setImage(image);

    }
}
