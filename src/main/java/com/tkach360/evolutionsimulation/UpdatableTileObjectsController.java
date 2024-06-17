package com.tkach360.evolutionsimulation;

public interface UpdatableTileObjectsController {
    void add(UpdatableTileObject obj, int parentIndex);
    void erase(UpdatableTileObject obj);
    UpdatableTileObject getNext(UpdatableTileObject obj);
    UpdatableTileObject getFirst();
}
