package com.miola.smarthotel.controller.popupwindowcontrollers;

import com.jfoenix.controls.JFXToggleButton;
import com.miola.smarthotel.controller.mainwindowcontrollers.EtagesEclairageController;
import com.miola.smarthotel.controller.itemwindowcontroller.ItemEclairageController;
import com.miola.smarthotel.controller.mainwindowcontrollers.SceneController;
import com.miola.smarthotel.dao.EclairageDao;
import com.miola.smarthotel.dao.MyListenerEclairage;
import com.miola.smarthotel.model.Eclairage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EclairageController implements Initializable
{
    public Button exitBtn;
    @FXML
    private VBox chosenFruitCard;

    @FXML
    private Label etatLampe;

    @FXML
    private Label position;

    @FXML
    private GridPane grid;

    @FXML
    private ImageView imgLampe;

    @FXML
    private JFXToggleButton ToggleEtat;

    @FXML
    private ComboBox<String> nbrPersonne;

    private List<Eclairage> eclairages = new ArrayList<>();

    private MyListenerEclairage myListenerEclairage;

    private Eclairage eclairage;

    EclairageDao eclairageDao = new EclairageDao();

    private void setChoseenEclairage(Eclairage eclairage)
    {
        this.eclairage= new Eclairage(eclairage);
        position.setText("Position "+Integer.toString(eclairage.getPosition()));

        if (eclairage.getIsOpen()==0)
        {
            ToggleEtat.setSelected(false);
            etatLampe.setText("OFF");
        }
        else
        {
            ToggleEtat.setSelected(true);
            etatLampe.setText("ON");
        }

        Image image = new Image(this.getClass().getResourceAsStream(eclairage.getImgSrc()));
        imgLampe.setImage(image);

        chosenFruitCard.setStyle("-fx-background-color: #ff8000;-fx-background-radius: 30;");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        eclairages.addAll(eclairageDao.getAll(EtagesEclairageController.nrEtage));
        exitBtn.setOnAction(SceneController::close);

        if (eclairages.size() > 0)
        {
            setChoseenEclairage(eclairages.get(0));
            myListenerEclairage = new MyListenerEclairage() {
                @Override
                public void onClickListener(Eclairage eclairage) {
                    setChoseenEclairage(eclairage);
                }
            };
        }
        int column = 0;
        int row = 1;
        try
        {
            for (int i = 0; i < eclairages.size(); i++)
            {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/itemEclairage.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemEclairageController itemController = fxmlLoader.getController();
                itemController.setData(eclairages.get(i),myListenerEclairage);

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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateEclairage(ActionEvent event) throws SQLException, IOException
    {
        int etat = ToggleEtat.isSelected() ? 1 : 0;

        if (eclairageDao.updateEclairage(eclairage.getIdEcl(),etat))
        {
            SceneController.getEclairageScene(event, "/view/Eclairage.fxml");
        }
        else
        {
            System.out.println("not work");
        }
    }

    public void onClick(ActionEvent actionEvent)
    {
        Image imageOn = new Image(getClass().getResourceAsStream("/images/LampeOn.png"));
        Image imageOff = new Image(getClass().getResourceAsStream("/images/LampeOff.png"));

        if(ToggleEtat.isSelected())
        {
            imgLampe.setImage(imageOn);
        }
        else {
            imgLampe.setImage(imageOff);
        }
    }
}
