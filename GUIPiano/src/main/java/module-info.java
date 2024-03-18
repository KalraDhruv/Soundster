module org.example.guipiano {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.guipiano to javafx.fxml;
    exports org.example.guipiano;
}