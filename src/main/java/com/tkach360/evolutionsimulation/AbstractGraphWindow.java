package com.tkach360.evolutionsimulation;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.stage.Stage;

public abstract class AbstractGraphWindow {

    protected int showXAxisRange;
    protected int yMinUpperBound;

    protected final NumberAxis xAxis;
    protected final NumberAxis yAxis;
    protected final LineChart<Number,Number> lineChart;

    protected final Scene scene;
    protected final Stage stage;

    protected int xAxisUpperBound;
    protected int xAxisLowerBound;

    public AbstractGraphWindow(String graphName, double width, double height, String xName, int showXAxisRange, int xTick, String yName, int yMinUpperBound, int yTick) {
        this.xAxis = new NumberAxis();
        this.xAxis.setLabel(xName);
        this.xAxisLowerBound = 0;
        this.xAxisUpperBound = showXAxisRange;
        this.xAxis.setUpperBound(this.xAxisUpperBound);
        this.xAxis.setLowerBound(this.xAxisLowerBound);
        this.xAxis.setAutoRanging(false);
        this.xAxis.setTickUnit(xTick);

        this.yAxis = new NumberAxis();
        this.yAxis.setLabel(yName);
        this.yMinUpperBound = yMinUpperBound;
        this.yAxis.setUpperBound(yMinUpperBound);
        this.yAxis.setAutoRanging(false);
        this.yAxis.setTickUnit(yTick);

        this.lineChart = new LineChart<Number,Number>(xAxis,yAxis);
        this.lineChart.setTitle(graphName);
        this.lineChart.setCreateSymbols(false);

        this.scene = new Scene(lineChart,width,height);
        this.stage = new Stage();
        this.stage.setTitle(graphName);
        this.stage.setScene(scene);
    }

    public void show(){
        stage.show();
    }
}
