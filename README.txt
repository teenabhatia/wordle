
  1. 2D Arrays: I have used 2D arrays to implement the grid which is displayed on the user's screen. I have created a
  class called Box which extends JLabel, then I have created a 2D Array of Boxes (hence the type of the 2D Array is Box).
  This is an appropriate use of the concept because I am iterating through and accessing entries of the array properly.
  I am only using one 2D Array (no redundant usage). My array is encapsulated properly.

  2. Collections: I have used collections for 2 functionalities. The first being word verification.
  In my Dictionary Class, I am using an ArrayList to store the words I am reading from the file dict.txt file. This file contains
  thousands of words that do not all meet the conditions required for Wordle words. Hence, I iterate through the collection and
  remove any words that contain any characters except letters and any words that are not of length 5. An ArrayList is better for
  storing and accessing data which is why I chose it. The second functionality for collections that I have implemented is for
  checking if each of the letters of the word inputted by the user matches the answer. Then, I use those collections to accordingly
  change the color of each Box to either Green, Yellow, or Grey. The reason I am using a collection
  and not an array is because an array would require a fixed size. Additionally, I would have keep iterating through the
  array to check if it contains a certain value.

  3. File I/O: I am reading from the dict.txt file as mentioned above so that every time the game is refreshed,
  a random new word is present for the user to guess. Additionally, I am storing the state of the game everytime
  the user presses save which includes the entire game board and the history of all words inputted and the letters that
  the user has gotten correct/partially correct till now. Additionally, once the user presses the button Restore Progress,
  I read from the savedGame.txt file and restore their progress and the user can continue guessing.


  4. JUnit Testable: I have tested different cases including but not limited to: if the game state is being stored correctly
  when a user presses save and if the progress is being restored properly (file is being read from correctly), if the
  collections are storing the characters that the user got correct/partially correct, if the blocks on the grid are
  changing color when the user inputs a correct/partially correct word, etc.


Description of each class:

  Class @Box
  Extends the JLabel class. Box represents each singular box on the grid. The use of this class is so that I can
  update the state of each Box accordingly - either the letter displayed in the box or the color of the box.

  Class @CurrentTurn
  Keeps a track of the current turn of the user in terms of the column and row on the grid.

  Class @Dictionary
  Reads from the dict.txt file and filters out the words that cannot be used. Also, contains methods that allow
  for setting the 'Correct' answer for a game, getting the current 'Correct' answer, randomizing the 'Correct' answer.

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


========================
=: External Resources :=
========================

  https://www.bragitoff.com/2016/03/english-dictionary-in-csv-format/
  https://www.youtube.com/watch?v=2alIWd1-jlI&t=179s
  https://stackoverflow.com/
  JavaDocs
