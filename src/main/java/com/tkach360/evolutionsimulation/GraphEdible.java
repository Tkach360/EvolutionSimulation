package com.tkach360.evolutionsimulation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

public class GraphEdible {

    private PieChart edible;
    private PieChart.Data predationData;
    private PieChart.Data soilData;
    private PieChart.Data photosyntesisData;
    private ObservableList<PieChart.Data> pieChartData;

    protected final Scene scene;
    protected final Stage stage;

    public GraphEdible(String graphName, double width, double height) {

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
        this.stage.setAlwaysOnTop(true);
        Main.addChildWindow(this.stage);
    }

    public void addData(int countPhotosyntesis, int countPredation, int countSoil){
        this.predationData.setPieValue(countPredation);
        this.soilData.setPieValue(countSoil);
        this.photosyntesisData.setPieValue(countPhotosyntesis);
    }

    public void show(){
        if(this.stage.isShowing()) this.stage.hide();
        else this.stage.show();
    }
}
