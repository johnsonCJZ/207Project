package fall2018.csc2017.slidingtiles;

import android.util.Pair;

import java.util.ArrayList;

public class ScoreSorter implements Sorter{

    @Override
    public void sort(ArrayList<Pair<String, Integer>> nameAndScore){
        for (int i = (nameAndScore.size() - 1); i >= 0; i--){
            for (int j = 1; j <= i; j++){
                Pair pair1 = nameAndScore.get(j-1);
                Pair pair2 = nameAndScore.get(j);
                Integer val1 = (Integer)pair1.second;
                Integer val2 = (Integer)pair2.second;
                if (val1 < val2){
                    Pair temp = nameAndScore.get(j-1);
                    nameAndScore.set(j - 1, nameAndScore.get(j));
                    nameAndScore.set(j, temp);
                }
            }
        }
    }

}
