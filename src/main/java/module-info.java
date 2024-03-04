module com.example.practica7javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.practica7javafx to javafx.fxml;
    exports com.example.practica7javafx;
}