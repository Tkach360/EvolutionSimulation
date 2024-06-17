package com.tkach360.evolutionsimulation;

public class UpdatableTileObjectsControllerWithArray implements UpdatableTileObjectsController {

    private final UpdatableObjectNode[] updatableObjectNodes;

    public UpdatableTileObjectsControllerWithArray(int maxCountObjects) {
        this.updatableObjectNodes = new UpdatableObjectNode[maxCountObjects + 1];
        for (int i = 0; i < maxCountObjects + 1; i++) this.updatableObjectNodes[i] = new UpdatableObjectNode(this, i);
    }

    @Override
    public void add(UpdatableTileObject obj, int parentIndex){
        for(int i = 1; i < updatableObjectNodes.length; i++){
            if(updatableObjectNodes[i].getObj() == null) {
                int next = updatableObjectNodes[parentIndex].getNext();

                obj.setUpdatableObjectNode(updatableObjectNodes[i]);
                updatableObjectNodes[i].setObj(obj);
                updatableObjectNodes[i].setPrev(parentIndex);
                updatableObjectNodes[i].setNext(next);

                updatableObjectNodes[parentIndex].setNext(i);
                updatableObjectNodes[next].setPrev(i);

                return;
            }
        }
    }

    @Override
    public void erase(UpdatableTileObject obj){
        int prevInd = obj.getUpdatableObjectNode().getPrev();
        int nextInd = obj.getUpdatableObjectNode().getNext();

        updatableObjectNodes[nextInd].setPrev(prevInd);
        updatableObjectNodes[prevInd].setNext(nextInd);
    }

    @Override
    public UpdatableTileObject getNext(UpdatableTileObject obj) {
        UpdatableTileObject nextObj;
        int next = obj.getUpdatableObjectNode().getNext();

        if(next == 0) nextObj = null;
        else nextObj = updatableObjectNodes[next].getObj();

        return nextObj;
    }

    @Override
    public UpdatableTileObject getFirst(){
        return updatableObjectNodes[updatableObjectNodes[0].getNext()].getObj();
    }

    public int getCount(TypeTileObject type) {
        int count = 0;
        UpdatableTileObject obj = getFirst();
        while (obj != null){
            if(obj.getType() == type) count++;
            obj = getNext(obj);
        }
        return count;
    }
}
