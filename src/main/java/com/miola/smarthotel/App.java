package com.miola.smarthotel;

import com.miola.smarthotel.controller.mainwindowcontrollers.SceneController;
import com.miola.smarthotel.helpers.TypeChambre;
import javafx.application.Application;
import javafx.stage.Stage;
import org.hibernate.Session;

/**
 * Code created by Andrius on 2020-09-26
 */
public class App extends Application {

    public static void main(String[] args) {
        App.launch();
    }

/*    @Override
    public void init() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.close();
    }*/

    @Override
    public void start(Stage stage) throws Exception
    {
        SceneController.getInitialScene(stage);
    }

/*    @Override
    public void stop() throws Exception {
        super.stop();
        HibernateUtil.shutdown();
    }*/
}