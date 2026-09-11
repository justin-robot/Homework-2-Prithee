Prithee
Homework 2

What it does
The program prints the sonnet "Shall I compare thee to a summer's day?" and stops at a
random word, which is replaced by underscores. The user types the missing word. The
program says whether the answer is correct or an error, then restarts the sonnet and
stops at a different word. The game ends after three correct answers or three errors.

Requirements
Java 26
Gradle (the included Gradle wrapper will download what it needs)

How to run
1. Open a terminal in the project folder.
2. Run the program:
   Mac or Linux:  ./gradlew run --console=plain
   Windows:       gradlew.bat run --console=plain
3. Type the missing word at the prompt and press Enter.

How to run the tests
   Mac or Linux:  ./gradlew test
   Windows:       gradlew.bat test
The test report is written to build/reports/tests/test/index.html

Running from IntelliJ
Open the project folder, let Gradle sync, then run the main method in
src/main/java/prithee/Main.java

Notes
Answers are not case sensitive and punctuation is ignored, so "day" and "Day?" are
both accepted for the word "day?".
