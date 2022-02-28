package com.miola.smarthotel.controller.popupwindowcontrollers;

import com.miola.smarthotel.controller.mainwindowcontrollers.SceneController;
import com.miola.smarthotel.dao.VetDao;
import com.miola.smarthotel.helpers.UpdateStatus;
import com.miola.smarthotel.model.Veterinarian;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.io.IOException;

public class AddReservationController {

    @FXML
    private TextField personneNumber;

    @FXML
    private Spinner<Integer> singleRoomNumber;

    @FXML
    private Spinner<Integer> twinForSoloUseNumber;

    @FXML
    private Spinner<Integer> twinRoomNumber;

    @FXML
    private Spinner<Integer> DoubleRoomNumber;

    @FXML
    private Spinner<Integer> TripleRoomNulmber;

    @FXML
    private Spinner<Integer> QuadRoomNumber;

    @FXML
    private DatePicker reservationDate;

    @FXML
    private Button cancelBtn;

    @FXML
    private Label alertText;

    @FXML
    private void initialize()
    {
        SpinnerValueFactory<Integer> valueFactory1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5, 0);
        SpinnerValueFactory<Integer> valueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5, 0);
        SpinnerValueFactory<Integer> valueFactory3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5, 0);
        SpinnerValueFactory<Integer> valueFactory4 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5, 0);
        SpinnerValueFactory<Integer> valueFactory5 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5, 0);
        SpinnerValueFactory<Integer> valueFactory6 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5, 0);

        singleRoomNumber.setValueFactory(valueFactory1);
        twinForSoloUseNumber.setValueFactory(valueFactory2);
        twinRoomNumber.setValueFactory(valueFactory3);
        DoubleRoomNumber.setValueFactory(valueFactory4);
        TripleRoomNulmber.setValueFactory(valueFactory5);
        QuadRoomNumber.setValueFactory(valueFactory6);

    }

    @FXML
    private void saveNewVetToDb(ActionEvent event) throws IOException
    {
        if (validateInputs())
        {
            Veterinarian vet = createVetFromInput();
            boolean isSaved = new VetDao().createVet(vet);
            if (isSaved) {
                UpdateStatus.setIsEmployeAdded(true);
                alertText.setText("Vet is added!");
                delayWindowClose(event);
            }
        }
    }

    private boolean validateInputs() {
        if (personneNumber.getText().equals("")) {
            alertText.setText("*You must add first name!");
            return false;
        }
        return true;
    }

    private Veterinarian createVetFromInput() {
//        Veterinarian vet = new Veterinarian();
//        vet.setFirstName(vetFirstName.getText());
//        vet.setLastName(vetLastName.getText());
//        vet.setSpeciality(vetSpeciality.getText());
//        vet.setAddress(vetAddress.getText());
//        return vet;
        return null;
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

    public void saveNewReservationToDb(ActionEvent event) {
    }
}
