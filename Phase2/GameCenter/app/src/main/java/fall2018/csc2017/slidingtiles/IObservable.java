package fall2018.csc2017.slidingtiles;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Observer;

public interface IObservable<T> {

    void addObserver(IObserver o);

    void deleteObserver(IObserver o);

    void notifyObservers();

    void clearChanged();

    boolean hasChanged();

    void setChanged();

}