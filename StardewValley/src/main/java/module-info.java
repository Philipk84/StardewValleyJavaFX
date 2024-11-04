module com.example.stardewvalley {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    exports com.example.stardewvalley.model;
    opens com.example.stardewvalley to javafx.fxml;
    exports com.example.stardewvalley;
    exports com.example.stardewvalley.controller;
    opens com.example.stardewvalley.controller to javafx.fxml;
}