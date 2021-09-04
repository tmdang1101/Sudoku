# Sudoku

### Main.java

- Runs code, creates Sudoku Game.
- Three games available to be loaded from text files: Basic, Easy, and Medium.

### Sudoku.java

- Creates a game board, a key board, and a quit button
- The player select a key first, the program will remember that key, then the player can choose the squares they want to put that digit into.
- If the player puts in the wrong number, the program will send an error message.
- When the player finished the puzzle, the program will send a victory message.

Methods:
1. Sudoku(): constructor, creates the board as a JFrame with JButtons for the digits, and also the quit button
2. createKeyBoard(): creates the key board, also a JFrame with JButtons, when a key is selected, the program sets that value to the instance variable newValue to change the squares in the board
3. errorMessage() and victoryMessage(): prints messages to the player
4. validBoard(): checks each row, column, and box to see if it follows Sudoku rules
5. updateBoard(): updates the board after each answer
6. finishedBoard(): checks to see if the puzzle is finished

### BetaVersion

- Running BetaVersion_Main.java will create a nice looking Sudoku board, but it doesn't actually work, yet.
- This is what I wanted it to look like. The game still works fine.
