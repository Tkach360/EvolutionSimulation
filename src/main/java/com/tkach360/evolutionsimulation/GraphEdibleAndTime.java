package com.tkach360.evolutionsimulation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

public class GraphEdibleAndTime {

    private PieChart edible;
    private PieChart.Data predationData;
    private PieChart.Data soilData;
    private PieChart.Data photosyntesisData;
    private ObservableList<PieChart.Data> pieChartData;

    protected final Scene scene;
    protected final Stage stage;

    public GraphEdibleAndTime(String graphName, double width, double height) {

        photosyntesisData = new PieChart.Data("Фотосинтез", 0);
        soilData = new PieChart.Data("Почва", 0);
        predationData = new PieChart.Data("Хищничество", 0);

        this.pieChartData = FXCollections.observableArrayList(
                photosyntesisData,
                predationData,
                soilData
        );

        this.edible = new PieChart(pieChartData);
        this.scene = new Scene(edible,width,height);
        this.stage = new Stage();
        this.stage.setScene(scene);
        this.stage.setTitle(graphName);

    }

    public void addData(int countPhotosyntesis, int countPredation, int countSoil){
        this.predationData.setPieValue(countPredation);
        this.soilData.setPieValue(countSoil);
        this.photosyntesisData.setPieValue(countPhotosyntesis);
    }

    public void show(){
        this.stage.show();
    }
}
