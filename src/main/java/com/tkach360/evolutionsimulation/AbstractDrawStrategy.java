package com.tkach360.evolutionsimulation;

import javafx.scene.canvas.GraphicsContext;

/**
 * абстрактный класс для стратегии рисования
 * используется ка основа для режимов отрисовки
 */
public abstract class AbstractDrawStrategy {

    protected GraphicsContext gc;

    /*** рисует все объекты в  соответствии со стратегией рисования*/
    public abstract void drawAll();
}
