module com.example.evolutionsimulation {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.tkach360.evolutionsimulation to javafx.fxml;
    exports com.tkach360.evolutionsimulation;
}