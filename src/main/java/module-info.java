module com.example.tuxedo3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tuxedo3 to javafx.fxml;
    exports com.example.tuxedo3;
}