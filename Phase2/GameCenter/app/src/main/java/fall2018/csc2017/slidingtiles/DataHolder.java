package fall2018.csc2017.slidingtiles;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class DataHolder {
    Map<String, WeakReference<Object>> data = new HashMap<String, WeakReference<Object>>();

    public void save(String id, Object object) {
        data.put(id, new WeakReference<Object>(object));
    }

    public Object retrieve(String id) {
        WeakReference<Object> objectWeakReference = data.get(id);
        return objectWeakReference.get();
    }
    private static final DataHolder holder = new DataHolder();
    public static DataHolder getInstance() {return holder;}
}