package com.tkach360.evolutionsimulation;
// TODO: подумать
/** класс содержащий методы для установки значения в определенном диапазоне */
public class NumRangeController {

    public static int setInRange(int num, int minValue, int maxValue){
        if(num < minValue) num = minValue;
        if(num > maxValue) num = maxValue;
        return num;
    }

    public static double setInRange(double num, double minValue, double maxValue){
        if(num < minValue) num = minValue;
        if(num > maxValue) num = maxValue;
        return num;
    }
}
