# Ultimate TicTacToe
Play Ultimate TicTacToe, a highly strategical game of TicTacToe involving "checkmates" much like in chess!

The goal of Ultimate TicTacToe is to complete three tictactoe boards to win the entire game. 

The catch is that your move on one of the smaller boards will send your opponent to the corresponding board. 
Example below:

!["X moves and sends O to the right-middle board"](https://github.com/aaron-gu/UltimateTicTacToe/blob/master/ExampleMove.jpg)

If a player sends the other player to a completed board, the other player can then move to any open board. This is what allows you to "corner" your opponent into a guaranteed loss if you play correctly, and is the strategic element behind a seemingly simple game!

I built the game using Java Swing. Features I would like to add are:

* checking if a board is won after each move
* undo move
* highlighting the current board to move, and disabling all other boards
* and a more daring feature - checking if a game can be won (I imagine this would require some kind of recursion to check all possible moves)

For now, the functionality is pretty basic, but enjoy playing with a friend!
