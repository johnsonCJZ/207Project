package fall2018.csc2017.slidingtiles;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Observer;

public interface IObservable<T> {

    void subscribe(Observer o);

    void unsubscribe(Observer o);

    // For debug purpose, internal use only
    ArrayList<Observer> getAllObservers();

    void notifyChanged(Object initiator);

    void notifyChanged(Collection<Object> initiators);

    void notifyChanged();

    void set(T newValue, Collection<Object> initiators);

    void set(T newValue);

    /**
     * _setObject() is a type unchecked version of set()
     * This is intended for internal use only
     * @param newValue
     * @param initiators
     */
    void setObject(Object newValue, Collection<Object> initiators);

    T get();

    boolean isNull();
}