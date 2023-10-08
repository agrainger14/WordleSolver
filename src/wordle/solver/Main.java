package wordle.solver;

import java.io.IOException;

/**
 * The Main class serves as the entry point for the WordleSolver application.
 * It initializes the WordleSolver and runs the game for a specified word length.
 */
public class Main {
    private static final int LETTER_LENGTH = 5;

    /**
     * The main method initializes the WordleSolver and starts the game for a specified word length.
     *
     */
    public static void main(String[] args) {
        WordleSolver wordleSolver = new WordleSolver();
        wordleSolver.run(LETTER_LENGTH);
    }

}
