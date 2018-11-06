package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.util.List;

/**
 * The sorter that sorts based on score.
 */
public class ScoreSorter implements Sorter<Object[]>, Serializable {

    @Override
    public void sort(List<Object[]> nameAndScore){
        for (int i = (nameAndScore.size() - 1); i >= 0; i--){
            for (int j = 1; j <= i; j++){
                Object[] pair1 = nameAndScore.get(j-1);
                Object[] pair2 = nameAndScore.get(j);
                Integer val1 = (Integer)pair1[1];
                Integer val2 = (Integer)pair2[1];
                if (val1 < val2){
                    Object[] temp = nameAndScore.get(j-1);
                    nameAndScore.set(j - 1, nameAndScore.get(j));
                    nameAndScore.set(j, temp);
                }
            }
        }
    }
}
