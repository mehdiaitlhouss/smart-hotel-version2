package com.miola.smarthotel.controller.popupwindowcontrollers;

import com.miola.smarthotel.dao.ConsultDao;
import com.miola.smarthotel.dao.PetDao;
import com.miola.smarthotel.dao.VetDao;
import com.miola.smarthotel.helpers.UpdateStatus;
import com.miola.smarthotel.model.Consult;
import com.miola.smarthotel.model.Pet;
import com.miola.smarthotel.model.Veterinarian;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AddVisitController {

    @FXML
    private DatePicker visitDate;

    @FXML
    private TextArea description;

    @FXML
    private Label alertText;

    @FXML
    private Button cancelBtn;

    @FXML
    private ComboBox<Pet> selectPet;

    @FXML
    private ComboBox<Veterinarian> selectVet;

    @FXML
//    private void initialize() {
//        selectPet.setItems(getPetObservableList());
//        selectVet.setItems(getVetObservableList());
//    }

/*    private ObservableList<Pet> getPetObservableList() {
        ObservableList<Pet> list = FXCollections.observableArrayList();
        list.addAll(new PetDao().getPets());
        return list;
    }*/

    private ObservableList<Veterinarian> getVetObservableList() {
        ObservableList<Veterinarian> list = FXCollections.observableArrayList();
        list.addAll(new VetDao().getVets());
        return list;
    }

/*    @FXML
    private void saveNewVisitToDb(ActionEvent event) {
        if(validateInputs()) {
            Consult consult = createConsultFromInput();
            boolean isSaved = new ConsultDao().createConsult(consult);
            if (isSaved) {
                UpdateStatus.setIsVisitAdded(true);
                alertText.setText("Visit is added!");
                delayWindowClose(event);
            }
        }
    }*/

    private Consult createConsultFromInput() {
        Consult visit = new Consult();
        visit.setVisitDate(visitDate.getValue());
        visit.setPet(selectPet.getValue());
        visit.setVeterinarian(selectVet.getValue());
        visit.setDescription(description.getText());
        return visit;
    }

    private boolean validateInputs() {

        if (visitDate.getValue() == null) {
            alertText.setText("*Date must be selected from calendar!");
            return false;
        }

        if(selectPet.getValue() == null) {
            alertText.setText("*You must select pet!");
            return false;
        }

        if(selectVet.getValue() == null) {
            alertText.setText("*You must select vet!");
            return false;
        }

        if (description.getText().equals("")) {
            alertText.setText("*You must add visit description!");
            return false;
        }
        return true;
    }

    private void delayWindowClose(ActionEvent event) {
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(event2 -> closeWindow(event));
        delay.play();
    }

    @FXML
    private void closeWindow(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
}