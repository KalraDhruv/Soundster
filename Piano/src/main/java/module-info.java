module files.piano {
    requires javafx.controls;
    requires javafx.fxml;


    opens files.piano to javafx.fxml;
    exports files.piano;
}