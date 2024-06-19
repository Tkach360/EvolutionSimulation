package com.tkach360.evolutionsimulation;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.stage.Stage;

public class GraphBotsAndTime {
    protected Scene scene;
    protected Stage stage;

    protected int yMinUpperBound = 1000;

    protected final NumberAxis xAxis;
    protected final NumberAxis yAxis;
    protected final LineChart<Number,Number> lineChart;
    private Series series;

    protected int xAxisUpperBound;
    protected int xAxisLowerBound;

    public GraphBotsAndTime(String graphName, double width, double height, int showXAxisRange, int xTick, int yMinUpperBound, int yTick) {

        this.xAxis = new NumberAxis();
        this.xAxis.setLabel("Время");
        this.xAxisLowerBound = 0;
        this.xAxisUpperBound = showXAxisRange;
        this.xAxis.setUpperBound(this.xAxisUpperBound);
        this.xAxis.setLowerBound(this.xAxisLowerBound);
        this.xAxis.setAutoRanging(false);
        this.xAxis.setTickUnit(xTick);

        this.yAxis = new NumberAxis();
        this.yAxis.setLabel("Количество");
        this.yAxis.setUpperBound(yMinUpperBound);
        this.yAxis.setAutoRanging(false);
        this.yAxis.setTickUnit(yTick);

        this.lineChart = new LineChart<Number,Number>(xAxis,yAxis);
        this.lineChart.setTitle(graphName);
        this.lineChart.setCreateSymbols(false);
        this.series = new Series();
        this.lineChart.getData().add(series);
        this.series.setName("Боты");

        this.scene = new Scene(lineChart,width,height);
        this.stage = new Stage();
        this.stage.setScene(scene);
        this.stage.setTitle(graphName);
    }

    public void addData(int time, int count){

        this.series.getData().add(new XYChart.Data<>(time, count));

        if(count > yMinUpperBound) {
            this.yAxis.setUpperBound(count + 100);
            yMinUpperBound = count;
        }

        if (this.series.getData().size() > xAxisUpperBound) {
            this.xAxis.setLowerBound(xAxisLowerBound++);
            this.xAxis.setUpperBound(xAxisUpperBound++);
            this.series.getData().removeFirst();
        }
    }

    public void show(){
        this.stage.show();
    }
}