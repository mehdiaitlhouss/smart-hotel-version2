package com.miola.smarthotel.controller.popupwindowcontrollers;

import com.miola.smarthotel.dao.ClientDao;
import com.miola.smarthotel.helpers.UpdateStatus;
import com.miola.smarthotel.model.Client;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AddClientController {
    @FXML
    private Button cancelBtn;

    @FXML
    private TextField prenom;

    @FXML
    private TextField nom;

    @FXML
    private TextField email;

    @FXML
    private TextField cin;

    @FXML
    private TextField telephone;

    @FXML
    private TextField pays;

    @FXML
    private TextArea adresse;

    @FXML
    private Label alertText;

    @FXML
    private ComboBox<String> selectVille;

    @FXML
    private void initialize()
    {
        ObservableList<String> list = FXCollections.observableArrayList();
        String[] villes = {"agadir", "Marrakech", "Rabat"};
        list.addAll(villes);
        selectVille.setItems(list);
    }

    @FXML
    private void closeWindow(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void saveNewClient(ActionEvent event)
    {
        if(validateInputs())
        {
            Client client = createClientFromInput();
            boolean isSaved = new ClientDao().add(client);
            if (isSaved)
            {
                UpdateStatus.setIsClientAdded(true);
                alertText.setText("Client is added!");
                delayWindowClose(event);
            }
        }
    }


    private Client createClientFromInput() {
        Client client = new Client(prenom.getText(),
                                    nom.getText(),
                                    cin.getText(),
                                    email.getText(),
                                    telephone.getText(),
                                    adresse.getText(),
                                    pays.getText(),
                                    selectVille.getValue()
        );
        return client;
    }


    private boolean validateInputs() {

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
        if (selectVille.getValue() == null) {
            alertText.setText("ville is required");
            return false;
        }
        if (pays.getText() == null) {
            alertText.setText("pays is required");
            return false;
        }
        if (cin.getText() == null) {
            alertText.setText("cin is required");
            return false;
        }
        if (email.getText() == null) {
            alertText.setText("email is required");
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

/*
   JSONParser jsonParser = new JSONParser();
        try(FileReader reader = new FileReader("cities.json")) {
            Object obj = jsonParser.parse(reader);
            JSONArray villeList=(JSONArray) obj;
            ArrayList<Ville> list = new ArrayList<Ville>();
            if (villeList != null) {
                int len = villeList.size();
                for (int i=0;i<len;i++){
                    list.add((Ville) villeList.get(i));
                }
            }
            list.forEach(e -> {
                System.out.println(e.getVille());
            });


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
 */