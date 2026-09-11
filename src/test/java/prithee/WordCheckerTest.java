package prithee;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WordCheckerTest {

    @Test
    public void matchesExactWord() {
        assertTrue(WordChecker.matches("summer", "summer"));
    }

    @Test
    public void ignoresCase() {
        assertTrue(WordChecker.matches("SUMMER", "summer"));
    }

    @Test
    public void ignoresPunctuation() {
        assertTrue(WordChecker.matches("day", "day?"));
    }

    @Test
    public void ignoresExtraSpaces() {
        assertTrue(WordChecker.matches("  May  ", "May,"));
    }

    @Test
    public void rejectsWrongWord() {
        assertFalse(WordChecker.matches("winter", "summer"));
    }

    @Test
    public void rejectsEmptyAnswer() {
        assertFalse(WordChecker.matches("", "summer"));
    }

    @Test
    public void rejectsNullAnswer() {
        assertFalse(WordChecker.matches(null, "summer"));
    }

    @Test
    public void normalizeRemovesNonLetters() {
        assertEquals("owst", WordChecker.normalize("ow'st;"));
    }
}
