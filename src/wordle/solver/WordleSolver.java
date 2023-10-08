package wordle.solver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * WordleSolver is a simple program to play the Wordle game.
 * It loads a list of words from a file and attempts to guess
 * the target word based on user input to the console.
 */
public class WordleSolver {
    private static final int MAX_GUESSES = 6;
    private static final String WORDLE_LIST_PATH = "real_wordles.csv";

    /**
     * Default constructor for WordleSolver.
     */
    public WordleSolver() {
    }

    /**
     * Run the WordleSolver with a specified word length.
     *
     * @param wordLength The length of the target word.
     */
    public void run(int wordLength) {
        Set<String> dictionary = getWords(wordLength);
        Set<String> words = new HashSet<>(dictionary);

        try (Scanner scan = new Scanner(System.in)) {
            System.out.println("Loaded " + dictionary.size() + " words");

            for (int i = 0; i < MAX_GUESSES; i++) {
                String tryWord = getTryWord(words);
                String result;

                System.out.println("Attempt #" + (i + 1) + " Try this word : " + tryWord + " (out of " + words.size() + " words)");
                System.out.println("(g = green, y = yellow, e = empty)");
                result = scan.nextLine().trim();

                while (!isGuessValid(result, wordLength)) {
                    result = scan.nextLine().trim();
                }

                words = getNewWords(words, tryWord, result);
            }
        }
    }

    /**
     * Checks if a given guess is valid based on the feedback provided.
     *
     * @param result     The feedback string representing the correctness of each letter in the guess.
     * @param wordLength The expected length of the guess.
     * @return True if the guess is valid or else, false.
     */
    private boolean isGuessValid(String result, int wordLength) {
        if (result.length() != wordLength) {
            System.out.println("Try again, the guess must be " + wordLength + " chars long");
            return false;
        }

        for (int i = 0; i < result.length(); i++) {
            char wordChar = result.charAt(i);

            if (wordChar != 'g' && wordChar != 'y' && wordChar != 'e') {
                System.out.println("Try again, the guess must be a combination of these chars (g = green, y = yellow, e = empty)");
                return false;
            }
        }
        return true;
    }

    /**
     * Get a set of new words based on the current set of words,
     * a guessed word, and the input provided by the user.
     *
     * @param words   The current set of words.
     * @param tryWord The guessed word.
     * @param result  The user's input for the guessed word.
     * @return A new set of words based on the input.
     */
    private Set<String> getNewWords(Set<String> words, String tryWord, String result) {
        Set<String> newWords = new HashSet<>();

        for (String word : words) {
            if (isWordValid(word, tryWord, result)) {
                newWords.add(word);
            }
        }

        return newWords;
    }

    /**
     * Checks if a given word is valid based on the input provided in the result string.
     *
     * @param word     The word to be validated.
     * @param tryWord  The word that was guessed.
     * @param result   The feedback string representing the correctness of each letter.
     * @return True if the word is valid according to the input or else, false.
     */
    private boolean isWordValid(String word, String tryWord, String result) {
        char[] wordChars = word.toCharArray();

        for (int i = 0; i < tryWord.length(); i++) {
            char r = result.charAt(i);

            // green: correct letter + correct position
            if (r == 'g' && wordChars[i] != tryWord.charAt(i)) {
                return false;
            }

            // yellow: correct letter + incorrect position
            if (r == 'y' && (wordChars[i] == tryWord.charAt(i) || !contains(wordChars, tryWord.charAt(i)))) {
                return false;
            }

            // grey = not a valid letter in word
            if (r == 'e' && contains(wordChars, tryWord.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Check if a character is contained within an array of characters.
     *
     * @param wordChars    The array of characters to check.
     * @param targetChar The character to search for.
     * @return True if the character is found, false otherwise.
     */
    private boolean contains(char[] wordChars, char targetChar) {
        for (char c : wordChars) {
            if (c == targetChar) {
                return true;
            }
        }

        return false;
    }

    /**
     * Get the best word to try from a set of words based on
     * the number of unique letters in each word.
     *
     * @param words The set of words to choose from.
     * @return The best word to try.
     */
    private String getTryWord(Set<String> words) {
        String bestWord = null;
        int maxUniqueLetters = 0;
        int maxScore = 0;

        for (String word : words) {
            int score = getUniqueLetterCount(word);

            if (score == maxScore) {
                return word;
            } else if (score > maxUniqueLetters) {
                maxUniqueLetters = score;
                bestWord = word;
            }
        }

        return bestWord;
    }

    /**
     * Calculate the number of unique letters in a word.
     *
     * @param word The word to calculate the unique letter count for.
     * @return The number of unique letters in the word.
     */
    private int getUniqueLetterCount(String word) {
        Set<Character> letters = new HashSet<>();

        for (int i = 0; i < word.length(); i++) {
            letters.add(word.charAt(i));
        }

        return letters.size();
    }

    /**
     * Get a set of words of a specified word length from a word list.
     *
     * @param wordLength The desired word length.
     * @return A set of words of the specified length.
     */
    private Set<String> getWords(int wordLength) {
        Set<String> words = new HashSet<>();
        List<String> list = readWordList();

        for (String word : list) {
            if (wordLength == word.length()) {
                words.add(word);
            }
        }

        return words;
    }

    /**
     * Read a list of words from a CSV file and return them as a list.
     *
     * @return A list of words read from the file.
     */
    private List<String> readWordList() {
        List<String> list = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(WORDLE_LIST_PATH))) {
            String line;

            while ((line = br.readLine()) != null) {
                list.addAll(Arrays.asList(line.split(",")));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}
