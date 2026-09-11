package prithee;

import java.util.ArrayList;
import java.util.List;

public class Sonnet {

    private String[] lines;

    public Sonnet(String[] lines) {
        this.lines = lines;
    }

    public static Sonnet createDefault() {
        String[] text = {
            "Shall I compare thee to a summer's day?",
            "Thou art more lovely and more temperate:",
            "Rough winds do shake the darling buds of May,",
            "And summer's lease hath all too short a date;",
            "Sometime too hot the eye of heaven shines,",
            "And often is his gold complexion dimm'd;",
            "And every fair from fair sometime declines,",
            "By chance or nature's changing course untrimm'd;",
            "But thy eternal summer shall not fade,",
            "Nor lose possession of that fair thou ow'st;",
            "Nor shall death brag thou wander'st in his shade,",
            "When in eternal lines to time thou grow'st:",
            "   So long as men can breathe or eyes can see,",
            "   So long lives this, and this gives life to thee."
        };
        return new Sonnet(text);
    }

    public int lineCount() {
        return lines.length;
    }

    public String getLine(int lineIndex) {
        return lines[lineIndex];
    }

    public String[] getWords(int lineIndex) {
        return lines[lineIndex].trim().split(" ");
    }

    public String getWord(WordLocation location) {
        String[] words = getWords(location.getLine());
        return words[location.getWord()];
    }

    public List<WordLocation> allLocations() {
        List<WordLocation> locations = new ArrayList<WordLocation>();
        for (int line = 0; line < lines.length; line++) {
            String[] words = getWords(line);
            for (int word = 0; word < words.length; word++) {
                locations.add(new WordLocation(line, word));
            }
        }
        return locations;
    }

    public String fullText() {
        String text = "";
        for (int i = 0; i < lines.length; i++) {
            if (i > 0) {
                text = text + "\n";
            }
            text = text + lines[i];
        }
        return text;
    }

    public String maskedText(WordLocation location) {
        String text = "";
        for (int i = 0; i < location.getLine(); i++) {
            text = text + lines[i] + "\n";
        }
        String[] words = getWords(location.getLine());
        String currentLine = indentOf(lines[location.getLine()]);
        for (int i = 0; i <= location.getWord(); i++) {
            if (i > 0) {
                currentLine = currentLine + " ";
            }
            if (i == location.getWord()) {
                currentLine = currentLine + underscores(words[i].length());
            } else {
                currentLine = currentLine + words[i];
            }
        }
        return text + currentLine;
    }

    private String indentOf(String line) {
        int index = 0;
        while (index < line.length() && line.charAt(index) == ' ') {
            index = index + 1;
        }
        return line.substring(0, index);
    }

    private String underscores(int count) {
        String result = "";
        for (int i = 0; i < count; i++) {
            result = result + "_";
        }
        return result;
    }
}
