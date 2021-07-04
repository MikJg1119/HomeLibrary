module HomeLibrary {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;
    requires java.sql;
    requires org.jsoup;
    opens sample;
    opens sample.model;
}