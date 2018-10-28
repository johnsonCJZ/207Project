package fall2018.csc2017.slidingtiles;

import java.util.ArrayList;

public class ScoreSorter implements Sorter{

    public void sort(ArrayList<Object[]> nameAndScore){
        for (int i = (nameAndScore.size() - 1); i >= 0; i--){
            for (int j = 1; j <= i; j++){
                if ((Integer)nameAndScore.get(j-1)[1] < (Integer)nameAndScore.get(j)[1]){
                    Object[] temp = nameAndScore.get(j-1);
                    nameAndScore.set(j - 1, nameAndScore.get(j));
                    nameAndScore.set(j, temp);
                }
            }
        }
    }

}
