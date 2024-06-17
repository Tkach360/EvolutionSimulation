package com.tkach360.evolutionsimulation;

public interface UpdatableTileObjectsController {
    void add(UpdatableTileObject obj, int parentIndex);
    void erase(UpdatableTileObject obj);
    int getCount(TypeTileObject type);
    UpdatableTileObject getNext(UpdatableTileObject obj);
    UpdatableTileObject getFirst();
}
