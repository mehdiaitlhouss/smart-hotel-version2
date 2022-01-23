package com.miola.smarthotel.controller.mainwindowcontrollers;

import com.miola.smarthotel.dao.UserDao;
import com.miola.smarthotel.helpers.CurrentUser;
import com.miola.smarthotel.model.User;
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
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label infoLine;

    @FXML
    private Label welcome;

    @FXML
    private Button exitBtn;

    UserDao userDao = new UserDao();

    @FXML
    private void initialize() {
    close();
}

    @FXML
    private void loginUser(ActionEvent event) throws IOException, InterruptedException {
        String user = username.getText();
        String pass = password.getText();

        if(!validFields()) {
            infoLine.setText("Username and password can't be empty!");
            return;
        }

        if (!validateLogin()) {
            infoLine.setText("User not found!");
            return;
        }

        welcome.setText("Welcome, " + CurrentUser.getCurrentUser().getUserName() + "!");
        infoLine.setText("Redirecting to main dashboard...");

        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished( event2 -> {
            try {
                SceneController.getMainScene(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        delay.play();
    }

    boolean validFields() {
        return !username.getText().isEmpty() && !password.getText().isEmpty();
    }

    private boolean validateLogin() {
        User user = userDao.getConnectedUser(username.getText(), password.getText());
        if (user == null) {
            return false;
        }
        CurrentUser.setCurrentUser(user);
        return true;
    }

    private void close() {
        exitBtn.setOnAction(SceneController::close);
    }


}
