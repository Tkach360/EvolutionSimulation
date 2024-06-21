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

    protected NumberAxis xAxis;
    protected NumberAxis yAxis;
    protected LineChart<Number,Number> lineChart;
    private Series series;

    protected int xAxisUpperBound;
    protected int showXAxisRange;

    public GraphBotsAndTime(String graphName, double width, double height, int showXAxisRange, int xTick, int yMinUpperBound, int yTick) {

        this.xAxis = new NumberAxis();
        this.xAxis.setLabel("Время");
        this.showXAxisRange = showXAxisRange;
        this.xAxis.setUpperBound(showXAxisRange);
        this.xAxis.setLowerBound(0);
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
        this.stage.setAlwaysOnTop(true);
        Main.addChildWindow(this.stage);
    }

    public void addData(int time, int count){

        this.series.getData().add(new XYChart.Data<>(time, count));

        if(count > yMinUpperBound) {
            this.yAxis.setUpperBound(count + 100);
            yMinUpperBound = count;
        }

        if (this.series.getData().size() > showXAxisRange) {
            this.xAxis.setUpperBound(this.xAxis.getUpperBound() + 1);
            this.xAxis.setLowerBound(this.xAxis.getLowerBound() + 1);
            this.series.getData().removeFirst();
        }
    }

    public void show(){
        if(this.stage.isShowing()) this.stage.hide();
        else this.stage.show();
    }
}