package com.miola.smarthotel.controller.mainwindowcontrollers;

import com.miola.smarthotel.dao.MyListener;
import com.miola.smarthotel.model.Temperature;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ItemTemperatureController {
    @FXML
    private Label position;

    @FXML
    private Label degreeLable;

    @FXML
    private ImageView img;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(temperature);
    }

    private Temperature temperature;
    private MyListener myListener;

    public void setData(Temperature temperature, MyListener myListener) {
        this.temperature = temperature;
        this.myListener = myListener;
        position.setText("Position "+Integer.toString(temperature.getPosition()));
        degreeLable.setText(Integer.toString(temperature.getDegree())+"°C");

    }
}
