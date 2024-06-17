package com.tkach360.evolutionsimulation;

public class UpdatableObjectNode {
    private UpdatableTileObject obj;
    private int next;
    private int prev;
    private final int index;
    private final UpdatableTileObjectsController updatableObjectsController;

    public UpdatableObjectNode(UpdatableTileObjectsController updatableObjectsController, int index) {
        this.updatableObjectsController = updatableObjectsController;
        this.obj = null;
        this.next = 0;
        this.prev = 0;
        this.index = index;
    }

    public void registerNewObject(UpdatableTileObject obj){
        updatableObjectsController.add(obj, this.index);
    }

    public void unRegisterObject(){
        updatableObjectsController.erase(this.obj);
        this.obj = null;
    }

    public int getIndex(){
        return this.index;
    }

    public UpdatableTileObject getObj() {
        return obj;
    }

    public void setObj(UpdatableTileObject obj) {
        this.obj = obj;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public int getPrev() {
        return prev;
    }

    public void setPrev(int prev) {
        this.prev = prev;
    }
}
