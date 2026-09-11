package prithee;

public class WordLocation {

    private int line;
    private int word;

    public WordLocation(int line, int word) {
        this.line = line;
        this.word = word;
    }

    public int getLine() {
        return line;
    }

    public int getWord() {
        return word;
    }

    public boolean sameAs(WordLocation other) {
        return this.line == other.getLine() && this.word == other.getWord();
    }
}
