module com.example.evolutionsimulation {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.evolutionsimulation to javafx.fxml;
    exports com.example.evolutionsimulation;
}