package prithee;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PritheeGameTest {

    private PritheeGame newGame() {
        return new PritheeGame(Sonnet.createDefault(), new Random(42));
    }

    @Test
    public void newGameIsNotOver() {
        PritheeGame game = newGame();
        assertFalse(game.isOver());
        assertEquals(0, game.getCorrectCount());
        assertEquals(0, game.getIncorrectCount());
    }

    @Test
    public void correctAnswerIncreasesCorrectCount() {
        PritheeGame game = newGame();
        game.nextPrompt();
        assertTrue(game.submitAnswer(game.getCurrentWord()));
        assertEquals(1, game.getCorrectCount());
        assertEquals(0, game.getIncorrectCount());
    }

    @Test
    public void wrongAnswerIncreasesIncorrectCount() {
        PritheeGame game = newGame();
        game.nextPrompt();
        assertFalse(game.submitAnswer("banana"));
        assertEquals(0, game.getCorrectCount());
        assertEquals(1, game.getIncorrectCount());
    }

    @Test
    public void threeCorrectAnswersEndTheGame() {
        PritheeGame game = newGame();
        for (int i = 0; i < 3; i++) {
            game.nextPrompt();
            game.submitAnswer(game.getCurrentWord());
        }
        assertTrue(game.isOver());
        assertTrue(game.isWin());
    }

    @Test
    public void threeWrongAnswersEndTheGame() {
        PritheeGame game = newGame();
        for (int i = 0; i < 3; i++) {
            game.nextPrompt();
            game.submitAnswer("banana");
        }
        assertTrue(game.isOver());
        assertFalse(game.isWin());
    }

    @Test
    public void twoCorrectAndTwoWrongDoesNotEndTheGame() {
        PritheeGame game = newGame();
        for (int i = 0; i < 2; i++) {
            game.nextPrompt();
            game.submitAnswer(game.getCurrentWord());
            game.nextPrompt();
            game.submitAnswer("banana");
        }
        assertFalse(game.isOver());
    }

    @Test
    public void promptsDoNotRepeat() {
        PritheeGame game = newGame();
        List<WordLocation> seen = new ArrayList<WordLocation>();
        for (int i = 0; i < 20; i++) {
            WordLocation location = game.nextPrompt();
            for (int j = 0; j < seen.size(); j++) {
                assertFalse(location.sameAs(seen.get(j)));
            }
            seen.add(location);
        }
    }

    @Test
    public void promptTextHidesTheCurrentWord() {
        PritheeGame game = newGame();
        game.nextPrompt();
        assertTrue(game.getPromptText().contains("_"));
    }
}
