module com.miola.smarthotel
        {
        requires javafx.controls;
        requires javafx.fxml;
        requires javafx.web;
        requires java.naming;
        requires org.controlsfx.controls;
        requires com.dlsc.formsfx;
        requires validatorfx;
        requires org.kordamp.ikonli.javafx;
        requires org.kordamp.bootstrapfx.core;
        requires eu.hansolo.tilesfx;
        requires lombok;
        //requires javax.persistence;
        requires java.persistence;
        requires org.hibernate.orm.core;
        requires java.sql;

        opens com.miola.smarthotel to javafx.fxml;
        exports com.miola.smarthotel;
        opens com.miola.smarthotel.controller.mainwindowcontrollers to javafx.fxml;
        exports com.miola.smarthotel.controller.mainwindowcontrollers;
        }