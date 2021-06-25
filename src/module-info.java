module HomeLibrary {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;
    requires java.sql;
    opens sample;
    opens sample.model;
}