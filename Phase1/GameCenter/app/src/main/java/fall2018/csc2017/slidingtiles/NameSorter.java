package fall2018.csc2017.slidingtiles;
import java.util.ArrayList;
import java.lang.String;

public class NameSorter implements Sorter{
    public void sort(ArrayList<Object[]> nameAndScore){
        for (int i = (nameAndScore.size() - 1); i >= 0; i--){
            for (int j = 1; j <= i; j++){
                String a = (String) nameAndScore.get(j - 1)[0];
                String b = (String) nameAndScore.get(j)[0];
                if (a.compareTo(b) < 0){
                    Object[] temp = nameAndScore.get(j - 1);
                    nameAndScore.set(j - 1, nameAndScore.get(j));
                    nameAndScore.set(j, temp);
                }
            }
        }
    }
}
