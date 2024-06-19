package com.tkach360.evolutionsimulation;

import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

public class GraphBotsAndTime extends AbstractGraphWindow {

    private Series series;

    public GraphBotsAndTime(String graphName, double width, double height, int showXAxisRange, int xTick, int yMinUpperBound, int yTick) {
        super(graphName, width, height, "Время", showXAxisRange, xTick, "Количество", yMinUpperBound, yTick);
        this.series = new Series();
        this.lineChart.getData().add(series);
        this.series.setName("Боты");
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
}