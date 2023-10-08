# WordleSolver
A simple Java application that assists you in playing the popular word puzzle game called Wordle. It loads a list of words from a CSV file, allows you to make guesses, and provides attemps to provide the correct word based on the inputted guesses.

## How it Works
- Loads a dictionary of valid 5 letter words using in the Wordle Game from a CSV file.
- Allows the user to make up to a specified number of guesses (6).
- Provides a list of correct words on each guess using the following codes:
  - **`g`** : Green - Correct letter in the correct position.
  - **`y`** : Yellow - Correct letter in the wrong position.
  - **`e`** : Empty - Incorrect letter.
- Validates the length and format of user guesses.

## Example
![image](https://github.com/agrainger14/wordle-solver/assets/132609173/89401d17-77ad-4ada-bba5-66447a1e9c9c)
![image](https://github.com/agrainger14/wordle-solver/assets/132609173/6e9243f6-05e7-49d1-87f3-32a05d522227)


