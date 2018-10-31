package fall2018.csc2017.slidingtiles;

import android.util.Pair;
import java.lang.String;
import java.util.List;

public class NameSorter implements Sorter<String, Integer>{

    @Override
    public void sort(List<Pair<String, Integer>> nameAndScore){
        for (int i = (nameAndScore.size() - 1); i >= 0; i--){
            for (int j = 1; j <= i; j++){
                String a = (String) nameAndScore.get(j - 1).first;
                String b = (String) nameAndScore.get(j).first;
                if (a.compareTo(b) < 0){
                    Pair temp = nameAndScore.get(j - 1);
                    nameAndScore.set(j - 1, nameAndScore.get(j));
                    nameAndScore.set(j, temp);
                }
            }
        }
    }
}
