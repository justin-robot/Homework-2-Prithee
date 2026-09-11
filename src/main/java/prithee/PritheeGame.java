package prithee;

import java.util.List;
import java.util.Random;

public class PritheeGame {

    public static final int LIMIT = 3;

    private Sonnet sonnet;
    private Random random;
    private List<WordLocation> unusedLocations;
    private WordLocation currentLocation;
    private int correctCount;
    private int incorrectCount;

    public PritheeGame(Sonnet sonnet, Random random) {
        this.sonnet = sonnet;
        this.random = random;
        this.unusedLocations = sonnet.allLocations();
        this.currentLocation = null;
        this.correctCount = 0;
        this.incorrectCount = 0;
    }

    public boolean isOver() {
        return correctCount >= LIMIT || incorrectCount >= LIMIT || unusedLocations.isEmpty();
    }

    public WordLocation nextPrompt() {
        int index = random.nextInt(unusedLocations.size());
        currentLocation = unusedLocations.remove(index);
        return currentLocation;
    }

    public WordLocation getCurrentLocation() {
        return currentLocation;
    }

    public String getCurrentWord() {
        return sonnet.getWord(currentLocation);
    }

    public String getPromptText() {
        return sonnet.maskedText(currentLocation);
    }

    public boolean submitAnswer(String answer) {
        boolean correct = WordChecker.matches(answer, getCurrentWord());
        if (correct) {
            correctCount = correctCount + 1;
        } else {
            incorrectCount = incorrectCount + 1;
        }
        return correct;
    }

    public int getCorrectCount() {
        return correctCount;
    }

    public int getIncorrectCount() {
        return incorrectCount;
    }

    public boolean isWin() {
        return correctCount >= LIMIT;
    }
}
