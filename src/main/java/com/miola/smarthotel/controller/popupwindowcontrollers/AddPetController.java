package com.miola.smarthotel.controller.popupwindowcontrollers;

import com.miola.smarthotel.controller.mainwindowcontrollers.SceneController;
import com.miola.smarthotel.dao.PetDao;
import com.miola.smarthotel.helpers.UpdateStatus;
import com.miola.smarthotel.model.Pet;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.io.IOException;

/**
 * Code created by Andrius on 2020-10-01
 */
public class AddPetController {

    @FXML
    private TextField petRace;

    @FXML
    private DatePicker petBirthDate;

    @FXML
    private TextArea ownerInfo;

    @FXML
    private CheckBox isVaccinated;

    @FXML
    private Label alertText;

//    @FXML
//    private void createNewPet(ActionEvent event) throws IOException {
//        if (validateInputs()) {
//            Pet pet = createPetFromInput();
//            boolean isSaved = new PetDao().createPet(pet);
//            if (isSaved) {
//                UpdateStatus.setIsPetAdded(true);
//                alertText.setText("Pet is added!");
//                delayWindowClose(event);
//            }
//        }
//    }

    private Pet createPetFromInput() {
        Pet pet = new Pet();
        pet.setRace(petRace.getText());
        pet.setBirthDate(petBirthDate.getValue());
        pet.setOwnerName(ownerInfo.getText());
        pet.setIsVaccinated(isVaccinated.isSelected());
        return pet;
    }

    private boolean validateInputs() {
        if (petRace.getText().equals("")) {
            alertText.setText("*You must add pet race!");
            return false;
        }

        if (petBirthDate.getValue() == null) {
            alertText.setText("*You must select birth date!");
            return false;
        }

        if (ownerInfo.getText().equals("")) {
            alertText.setText("*You must add owner information!");
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
        SceneController.close(event);
    }
}