module com.example.stardewvalley {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.stardewvalley to javafx.fxml;
    exports com.example.stardewvalley;
    exports com.example.stardewvalley.controller;
    opens com.example.stardewvalley.controller to javafx.fxml;
}