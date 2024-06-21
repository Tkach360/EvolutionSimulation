module com.example.evolutionsimulation {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.tkach360.evolutionsimulation to javafx.fxml;
    exports com.tkach360.evolutionsimulation;
    exports com.tkach360.evolutionsimulation.neuralnetwork;
    opens com.tkach360.evolutionsimulation.neuralnetwork to javafx.fxml;
}