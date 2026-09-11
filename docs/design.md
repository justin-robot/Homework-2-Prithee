# Prithee Design

## Overview
Prithee is a console program. It prints the sonnet up to a randomly chosen word, hides
that word with underscores, and asks the user to supply it. The user wins after three
correct answers and loses after three errors.

## Classes

### Main
Entry point. Holds the console loop. Reads input with a Scanner, calls the game for each
round, and prints the result and the running score. Main is the only class that uses
System.out or System.in.

### Sonnet
Stores the fourteen lines of the sonnet as an array of strings. Responsibilities:
- getWords: splits a line into words
- getWord: returns the word at a given location
- allLocations: returns a WordLocation for every word in the poem
- maskedText: returns the poem from the first line up to the chosen word, with that word
  replaced by underscores and the rest of the poem left out
- fullText: returns the whole poem

### WordLocation
A small value class holding a line index and a word index. It identifies one word in the
sonnet and is used to pick, hide, and check a word.

### WordChecker
Compares the user answer to the hidden word. The normalize method removes every character
that is not a letter and converts the result to lower case, so case and punctuation do not
affect the result. An empty answer is always an error.

### PritheeGame
Holds the rules and the state of one game:
- a list of word locations that have not been used yet
- the current location
- the correct count and the incorrect count

nextPrompt removes a random location from the unused list so a word is never repeated.
submitAnswer checks the answer, updates one of the two counts, and returns the result.
isOver returns true when either count reaches three.

## Flow
1. Main creates a Sonnet and a PritheeGame.
2. While the game is not over:
   - the game picks a new word location
   - Main prints the masked poem and prompts the user
   - the game checks the answer and updates the score
   - Main prints correct or error, plus the score
3. Main prints the final result.

## Design notes
All game logic is kept out of Main so it can be tested without console input. Random is
passed into PritheeGame through the constructor, so tests can use a fixed seed and get
repeatable results.

## Tests
Located in src/test/java/prithee.
- WordCheckerTest: exact match, case, punctuation, extra spaces, wrong word, empty answer,
  null answer
- SonnetTest: line count, location count, word lookup, masking, stopping after the hidden
  word, indented lines
- PritheeGameTest: starting state, scoring, ending at three correct, ending at three
  errors, a two and two game that continues, and no repeated words
