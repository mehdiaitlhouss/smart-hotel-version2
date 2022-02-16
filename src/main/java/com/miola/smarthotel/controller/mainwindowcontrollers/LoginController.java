package com.miola.smarthotel.controller.mainwindowcontrollers;

import com.miola.smarthotel.dao.EmployeDao;
import com.miola.smarthotel.helpers.CurrentEmploye;
import com.miola.smarthotel.model.Admin;
import com.miola.smarthotel.model.Employe;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import java.io.IOException;

/**
 * Code created by Andrius on 2020-10-08
 */
public class LoginController {

    @FXML
    private TextField userName; // login de l'utilisateur

    @FXML
    private PasswordField password; // le password de l'utilisateur

    @FXML
    private Label infoLine;  // label pour afficher des message pendant le loge  si l'utilisateur a oublier un field

    @FXML
    private Label welcome; // label qui contient le grande mot LOGIN avant les champs de login et password

    @FXML
    private Button exitBtn; // boutton de sortie

    EmployeDao employeDao = new EmployeDao();  // crud du employe

    @FXML
    private void initialize() {
    close();
}

    @FXML
    private void loginUser(ActionEvent event) throws IOException, InterruptedException {
        String user = userName.getText();
        String pass = password.getText();

        if(!validFields()) {
            infoLine.setText("login and password can't be empty!");
            return;
        }

        if (!validateLogin()) {
            infoLine.setText("User not found!");
            return;
        }

        welcome.setText("Welcome, " + CurrentEmploye.getCurrentEmploye().getUserName() + "!");
        infoLine.setText("Redirecting to main dashboard...");

        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished( event2 -> {
            try {
                /*
                ici la place pour faire la diffrenece entre l'admin et le recep
                 */

                if(CurrentEmploye.getCurrentEmploye() instanceof Admin)
                {
                    SceneController.getAdminMainScene(event);
                }
                else
                {
                    SceneController.getRecepMainScene(event);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        delay.play();
    }

    boolean validFields() {
        // tester si aucun champ est vide
        return !userName.getText().isEmpty() && !password.getText().isEmpty();
    }

    private boolean validateLogin() {
        Employe empl = employeDao.getConnectedEmploye(userName.getText(), password.getText());
        if (empl == null) {
            return false;
        }
        CurrentEmploye.setCurrentEmploye(empl);
        return true;
    }

    private void close() {
        exitBtn.setOnAction(SceneController::close);
    }
}
