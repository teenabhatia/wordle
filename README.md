# wordle
The NY Times Wordle Game Recreated on Java Swing

Description of each class:

  Class @Box 
  Extends the JLabel class. Box represents each singular box on the grid. The use of this class is so that I can update the state of each Box accordingly - either the letter displayed in the box or the color of the box.

  Class @CurrentTurn
  Keeps a track of the current turn of the user in terms of the column and row on the grid.

  Class @Dictionary
  Reads from the dict.txt file and filters out the words that cannot be used. Also, contains methods that allow for setting the 'Correct' answer for a game, getting the current 'Correct' answer, randomizing the 'Correct' answer.

  Class @GameBoard
  This class is dependent on my model. It updates the GUI Grid that is viewed by the user according to the model. It
  acts as the interface between the model and the GUI. It gets the words inputted by the user on the screen,
  checks with the model, and then changes the color/displays the correct message to the user according to the user.
  It also has all the methods for the users interaction with the game in terms of typing on to the grid, saving the game,
  restoring a saved game, and restarting the game.

  Class @Model
  This is the backend for my game. It stores in a grid all the letters/words inputted by the user, has a method to restart the game,
  has a method to check if a word inputted is correct/partially correct/wrong in comparison to answer. Also, it has
  relevant getters and setters.

  Class @RunWordle
  This class implements Runnable, adds all the components including the game board and the buttons to the screen.

External Resources:
https://www.bragitoff.com/2016/03/english-dictionary-in-csv-format/
https://www.youtube.com/watch?v=2alIWd1-jlI&t=179s
https://stackoverflow.com/
JavaDocs
