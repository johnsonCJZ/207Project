package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The scoreBoard to display rankings.
 */
public class ScoreBoard implements Serializable {
    private int scoreBoardSize;
    private List<Object[]> scoreList = new ArrayList<>();
    private ScoreStrategy scoreStrategy;
    private ScoreSorter scoreSorter;

    ScoreBoard(String name){
        setScoreBoardSize(10);
        setScoreSorter(new ScoreSorter());
        setSlidscoreStrategy(new SlidingTilesScoreStrategy());
        switch(name){
            case "2048":
                setScoreStrategy(new Game2048ScoreStrategy());
                break;
            case "MineSweeper":
                setScoreStrategy(new MineSweeperScoreStrategy());
                break;
            case "SlidingTiles":
                setScoreStrategy(new SlidingTilesScoreStrategy());
                break;
        }
    }

    /**
     * Set the scoreBoardSize to size.
     * @param size the new size to set
     */
    void setScoreBoardSize(int size) {
        scoreBoardSize = size;
    }

    /**
     * Return a score calculated by using slidscoreStrategy.
     * @param boardManager the boardManager that is finished and to be calculated for score
     * @return a score calculated by using slidscoreStrategy
     */
    int calculateScore(Manager boardManager) {
        return scoreStrategy.calculateScore(boardManager);
    }

    /**
     * Set the slidscoreStrategy to strategy. Open to modification.
     * @param strategy the ScoreStrategy to set
     */
    void setScoreStrategy(ScoreStrategy strategy) {
        scoreStrategy = strategy;
    }

    /**
     * Set the scoreSorter to sorter. Open to modification.
     * @param sorter the Sorter to set
     */
    void setScoreSorter(ScoreSorter sorter) {
        scoreSorter = sorter;
    }

    /**
     * Add a new result to the scoreList and sort the list to get the new ranking.
     * @param scoreArray the new result to add
     */
    void addAndSort(Object[] scoreArray) {
        scoreList.add(scoreArray);
        scoreSorter.sort(scoreList);
        if (scoreList.size() == scoreBoardSize + 1) {
            scoreList.remove(scoreBoardSize);
        }
    }

    /**
     * Return the scoreList.
     * @return the scoreList
     */
    List<Object[]> getScoreList() {
        return scoreList;
    }

}


