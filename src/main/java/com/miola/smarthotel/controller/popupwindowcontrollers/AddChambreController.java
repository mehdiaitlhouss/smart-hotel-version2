package com.miola.smarthotel.controller.popupwindowcontrollers;

import com.miola.smarthotel.controller.mainwindowcontrollers.SceneController;
import com.miola.smarthotel.dao.ChambreDao;
import com.miola.smarthotel.helpers.EtatChambre;
import com.miola.smarthotel.helpers.TypeChambre;
import com.miola.smarthotel.helpers.UpdateStatus;
import com.miola.smarthotel.model.Chambre;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import java.io.IOException;

public class AddChambreController
{
    @FXML
    private ComboBox<String> selectedEtage;

    @FXML
    private ComboBox<String> selectedType;

    @FXML
    private TextField nombreLit;

    @FXML
    private TextField nombrePersonne;

    @FXML
    private TextField prixParJour;

    @FXML
    private Label alertText;

    public void initialize()
    {
        ObservableList<String> list1 = FXCollections.observableArrayList();
        list1.addAll(new String[] {"1", "2", "3", "5", "6", "7", "8"});
        selectedEtage.setItems(list1);

        ObservableList<String> list2 = FXCollections.observableArrayList();
        list2.addAll(TypeChambre.getTableString());
        selectedType.setItems(list2);
    }

    private Chambre createChambreFromInput()
    {
        Chambre chambre = new Chambre();
        chambre.setType(TypeChambre.valueOf(selectedType.getValue()));
        chambre.setEtat(EtatChambre.NON_RESERVER);
        chambre.setEtage(Integer.parseInt(selectedEtage.getValue()));
        return chambre;
    }

    private void delayWindowClose(ActionEvent event)
    {
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(event2 -> closeWindow(event));
        delay.play();
    }

    @FXML
    private void closeWindow(ActionEvent event)
    {
        SceneController.close(event);
    }

    @FXML
    public void saveNewChambre(ActionEvent event) throws IOException
    {
        Chambre chambre = createChambreFromInput();
        boolean isSaved = new ChambreDao().add(chambre);
        if (isSaved)
        {
            UpdateStatus.setIsChambreAdded(true);
            alertText.setText("Chambre is added!");
            delayWindowClose(event);
        }
    }

    @FXML
    public void setOtherFields(ActionEvent event)
    {
        nombreLit.setText(Integer.toString(TypeChambre.valueOf(selectedType.getValue()).getNombreLit()));
        nombrePersonne.setText(Integer.toString(TypeChambre.valueOf(selectedType.getValue()).getNombrePersonne()));
        prixParJour.setText(Double.toString(TypeChambre.valueOf(selectedType.getValue()).getPrixParJour()));
    }
}
