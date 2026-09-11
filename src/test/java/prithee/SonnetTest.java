package prithee;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SonnetTest {

    @Test
    public void sonnetHasFourteenLines() {
        Sonnet sonnet = Sonnet.createDefault();
        assertEquals(14, sonnet.lineCount());
    }

    @Test
    public void everyWordHasALocation() {
        Sonnet sonnet = Sonnet.createDefault();
        List<WordLocation> locations = sonnet.allLocations();
        int expected = 0;
        for (int line = 0; line < sonnet.lineCount(); line++) {
            expected = expected + sonnet.getWords(line).length;
        }
        assertEquals(expected, locations.size());
    }

    @Test
    public void getWordReturnsTheWordAtTheLocation() {
        Sonnet sonnet = Sonnet.createDefault();
        WordLocation location = new WordLocation(0, 0);
        assertEquals("Shall", sonnet.getWord(location));
    }

    @Test
    public void maskedTextHidesTheChosenWord() {
        Sonnet sonnet = Sonnet.createDefault();
        WordLocation location = new WordLocation(0, 1);
        String masked = sonnet.maskedText(location);
        assertEquals("Shall _", masked);
    }

    @Test
    public void maskedTextStopsAfterTheChosenWord() {
        Sonnet sonnet = Sonnet.createDefault();
        WordLocation location = new WordLocation(1, 0);
        String masked = sonnet.maskedText(location);
        assertTrue(masked.startsWith("Shall I compare thee to a summer's day?"));
        assertTrue(masked.endsWith("____"));
        assertFalse(masked.contains("Rough winds"));
    }

    @Test
    public void maskedTextKeepsIndentedLines() {
        Sonnet sonnet = Sonnet.createDefault();
        WordLocation location = new WordLocation(12, 0);
        String masked = sonnet.maskedText(location);
        assertTrue(masked.endsWith("   __"));
    }

    @Test
    public void fullTextContainsTheLastLine() {
        Sonnet sonnet = Sonnet.createDefault();
        assertTrue(sonnet.fullText().contains("gives life to thee."));
    }
}
