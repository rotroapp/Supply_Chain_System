module com.example.supplychainmngmt {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.supplychainmngmt to javafx.fxml;
    exports com.example.supplychainmngmt;
}