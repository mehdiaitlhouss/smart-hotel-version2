package com.miola.smarthotel.controller.mainwindowcontrollers;

import com.miola.smarthotel.controller.popupwindowcontrollers.NewWindowController;
import com.miola.smarthotel.helpers.CurrentEmploye;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class EtagesEclairageController {
    @FXML Button buttonTemp;
    @FXML
    public Button etage6;
    @FXML public Button etage5;
    @FXML public Button etage4;
    @FXML public Button etage2;
    @FXML public Button etage3;
    @FXML public Button etage1;

    @FXML
    private Label date;

    @FXML
    private Button exitBtn;

    @FXML
    private Label userInfo;


    public static int nrEtage;
    Map<VBox,VBox> map = new HashMap<VBox,VBox>();
    @FXML
    VBox secondSubVBox;
    @FXML
    VBox secondSubMenuList;
    @FXML
    Button secondMenu;


    @FXML
    private void initialize()
    {
        setTexts();
        exitBtn.setOnAction(SceneController::close);
        secondMenu.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                toolsSlider(secondSubVBox,secondSubMenuList);
                removeOtherMenus(secondSubVBox);
            }
        });
        buttonTemp.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    SceneController.getEtagesTemperaturesScene(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setTexts() {
        date.setText(LocalDate.now().toString());
        setUserInfo();
    }




    private void setUserInfo() {
        userInfo.setText(String.format("User : %s", CurrentEmploye.getCurrentEmploye().getUserName()));
    }



    @FXML
    void showDashboard(ActionEvent event) throws IOException {
        SceneController.getAdminMainScene(event);
    }

    @FXML
    void refreshScreen(ActionEvent event) throws IOException {
        SceneController.getClientsScene(event);
    }

    @FXML
    void showChambresScreen(ActionEvent event) throws IOException {
        SceneController.getChambresScene(event);
    }
    @FXML
    void showReservationsScreen(ActionEvent event) throws IOException {
        SceneController.getReservationsScene(event);
    }

    @FXML
    void showEmployesScreens(ActionEvent event) throws IOException
    {
        SceneController.getEmployesScene(event);
    }



    public void toolsSlider(VBox menu,VBox subMenu){
        toolsSliderImpl(menu,subMenu);
    }

    private void toolsSliderImpl(VBox menu,VBox subMenu) {
        if(menu.getChildren().contains(subMenu)){
            final FadeTransition transition = new FadeTransition(Duration.millis(500), menu);
            transition.setFromValue(0.5);
            transition.setToValue(1);
            transition.setInterpolator(Interpolator.EASE_IN);
            menu.getChildren().remove(subMenu);
            transition.play();
        }else{
            final FadeTransition transition = new FadeTransition(Duration.millis(500), menu);
            transition.setFromValue(0.5);
            transition.setToValue(1);
            transition.setInterpolator(Interpolator.EASE_IN);
            menu.getChildren().add(subMenu);
            transition.play();
        }
    }

    public void removeOtherMenus(VBox menu){
        removeOtherMenusImpl(menu);
    }
    private void removeOtherMenusImpl(VBox menu) {
        for (Map.Entry<VBox,VBox> entry : map.entrySet()) {
            if(!entry.getKey().equals(menu))
                entry.getKey().getChildren().remove(entry.getValue());
        }
    }

    public void clickEtage(ActionEvent actionEvent) throws IOException {
        if(((Button) actionEvent.getSource()) == etage1){
            System.out.println("etage 1 clicked");
            nrEtage=1;
        }else if (((Button) actionEvent.getSource()) == etage2){
            System.out.println("etage 2 clicked");
            nrEtage=2;

        }else if (((Button) actionEvent.getSource()) == etage3){
            System.out.println("etage 3 clicked");
            nrEtage=3;

        }else if (((Button) actionEvent.getSource()) == etage4){
            System.out.println("etage 4 clicked");
            nrEtage=4;

        }else if (((Button) actionEvent.getSource()) == etage5){
            System.out.println("etage 5 clicked");
            nrEtage=5;

        }else if (((Button) actionEvent.getSource()) == etage6){
            System.out.println("etage 6 clicked");
            nrEtage=6;

        }else {
            System.out.println("not found");
        }
        NewWindowController.getEclairagesWindow();

    }

    @FXML
    void showClientsScreen(ActionEvent event) throws IOException {
        SceneController.getClientsScene(event);
    }
}
