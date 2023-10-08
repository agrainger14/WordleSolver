# WordleSolver
The Wordle Solver is a basic program developed with vanilla Java to play the Wordle game, it loads a list of valid 5 letter words used in the game from a CSV file and attempts to guess the target word based on the user input to the console.
Upon receiving valid user input, a basic algorithm loops through all the word list and returns all possible words which could be the correct guess.

## How It Works

On run, the words are loaded and the first word attempt is provided to the user.
The user then inputs into a console a valid response (String with 5 valid chars either (g, y or e)
  * G = valid word letter in the correct position
  * Y = valid word letter in the incorrect position
  * E = not a valid word letter

![image](https://github.com/agrainger14/wordle-solver/assets/132609173/89401d17-77ad-4ada-bba5-66447a1e9c9c)
![image](https://github.com/agrainger14/wordle-solver/assets/132609173/6e9243f6-05e7-49d1-87f3-32a05d522227)




