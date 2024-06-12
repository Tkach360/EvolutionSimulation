package com.tkach360.evolutionsimulation;

public class MouseFunctionController {
    private int widthBrush;
    private IMouseFunction mouseFunction;

    public MouseFunctionController(int widthBrush, IMouseFunction mouseFunction) {
        this.widthBrush = widthBrush;
        this.mouseFunction = mouseFunction;
    }

    public void changeTiles(Tile tile){
        int leftBorder, rightBorder, topBorder, downBorder;

        leftBorder = tile.getCx() - widthBrush;
        if(leftBorder < 0) leftBorder = 0;

        topBorder = tile.getCy() - widthBrush;
        if(topBorder < 0) topBorder = 0;

        rightBorder = tile.getCx() + widthBrush;
        if(rightBorder > TileMap.getInstance().getCountColumns() - 1) rightBorder = TileMap.getInstance().getCountColumns() - 1;

        downBorder = tile.getCy() + widthBrush;
        if(downBorder > TileMap.getInstance().getCountRows() - 1) downBorder = TileMap.getInstance().getCountRows() - 1;

        for(int cX = leftBorder; cX <= rightBorder; cX++){
            for(int cY = topBorder; cY <= downBorder; cY++){
                mouseFunction.doFunc(TileMap.getInstance().getTiles()[cX][cY]);
            }
        }
    }

    public int getWidthBrush() {
        return widthBrush;
    }

    public void setWidthBrush(int widthBrush) {
        this.widthBrush = widthBrush;
    }

    public IMouseFunction getMouseFunction() {
        return mouseFunction;
    }

    public void setMouseFunction(IMouseFunction mouseFunction) {
        this.mouseFunction = mouseFunction;
    }
}
