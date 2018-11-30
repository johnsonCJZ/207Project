package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Board<T> implements Serializable, IObservable<Game2048Board> {
    private int dimension;

    private List<T> tiles = new ArrayList<>();

    private boolean changed = false;

    private ArrayList<IObserver> observers = new ArrayList<>();

    public void setTiles(List<T> tiles) {
        this.tiles = tiles;
    }

    public List<T> getTiles(){
        return tiles;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    @Override
    public void addObserver(IObserver o) {
        observers.add(o);
    }

    @Override
    public void deleteObserver(IObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        if(changed){
            int i = observers.size();
            while(--i >= 0){
                observers.get(i).update(this);
            }

        }
    }

    @Override
    public void clearChanged() {
        changed = false;
    }

    @Override
    public boolean hasChanged() {
        return changed;
    }

    @Override
    public void setChanged() {
        changed = true;
    }
}

