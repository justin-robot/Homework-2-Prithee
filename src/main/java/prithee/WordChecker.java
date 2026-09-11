package prithee;

public class WordChecker {

    public static String normalize(String word) {
        if (word == null) {
            return "";
        }
        String result = "";
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            if (Character.isLetter(letter)) {
                result = result + letter;
            }
        }
        return result.toLowerCase();
    }

    public static boolean matches(String answer, String expected) {
        String cleanAnswer = normalize(answer);
        String cleanExpected = normalize(expected);
        if (cleanAnswer.isEmpty()) {
            return false;
        }
        return cleanAnswer.equals(cleanExpected);
    }
}
