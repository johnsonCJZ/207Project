package fall2018.csc2017.slidingtiles;

import android.util.Pair;

import java.util.List;

public interface Sorter<K, V> {

    public void sort(List<Pair<K, V>> nameAndScore);

}
