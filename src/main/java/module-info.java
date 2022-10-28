module com.example.eazee {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;
    requires java.desktop;


    opens com.example.eazee to javafx.fxml;
    exports com.example.eazee;
}