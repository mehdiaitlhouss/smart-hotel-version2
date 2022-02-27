package com.miola.smarthotel.controller.popupwindowcontrollers;

import com.miola.smarthotel.controller.mainwindowcontrollers.SceneController;
import com.miola.smarthotel.dao.ClientDao;
import com.miola.smarthotel.dao.EmployeDao;
import com.miola.smarthotel.helpers.UpdateStatus;
import com.miola.smarthotel.model.Client;
import com.miola.smarthotel.model.Employe;
import com.miola.smarthotel.model.Receptionniste;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AddEmployeController
{
    @FXML
    private TextField prenom;

    @FXML
    private TextField nom;

    @FXML
    private TextField cin;

    @FXML
    private TextField telephone;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private TextField email;

    @FXML
    private TextArea adresse;

    @FXML
    private Label alertText;

    @FXML
    private Button cancelBtn;

    private void initialize()
    {

    }

    @FXML
    public void saveNewEmpoye(ActionEvent event)
    {
        if(validateInputs())
        {
            Employe employe = createEmployeFromInput();
            boolean isSaved = new EmployeDao().add(employe);
            if (isSaved)
            {
                UpdateStatus.setIsEmployeAdded(true);
                alertText.setText("Employe is added!");
                delayWindowClose(event);
            }
        }
    }

    @FXML
    private void closeWindow(ActionEvent event)
    {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    private Employe createEmployeFromInput()
    {
        Employe employe = new Receptionniste(prenom.getText(),
                nom.getText(),
                cin.getText(),
                email.getText(),
                telephone.getText(),
                adresse.getText(),
                username.getText(),
                password.getText()
        );
        return employe;
    }

    private boolean validateInputs()
    {
        if (prenom.getText() == null) {
            alertText.setText("prenom is required");
            return false;
        }
        if (nom.getText() == null) {
            alertText.setText("nom is required");
            return false;
        }
        if (telephone.getText() == null) {
            alertText.setText("telephone is required");
            return false;
        }
        if (adresse.getText() == null) {
            alertText.setText("adresse is required");
            return false;
        }
        if (password.getText() == null) {
            alertText.setText("password is required");
            return false;
        }
        if (email.getText() == null) {
            alertText.setText("email is required");
            return false;
        }
        if (cin.getText() == null) {
            alertText.setText("cin is required");
            return false;
        }

        return true;
    }

    private void delayWindowClose(ActionEvent event)
    {
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(event2 -> closeWindow(event));
        delay.play();
    }
}
