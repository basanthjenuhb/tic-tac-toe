# tic-tac-toe
tic-tac-toe


## Components

We have components who work in tandem to keep the game going.

### GameManager
- Deals with aspects that are not core to the tic-tac-toe game. 
- In our case, `GameManager` helps in coducting toss and assigning symbols to different players who are playing the game.
- In future, it can be used for other purposes, like conducting a tournament, score management etc.
- `GameManager` accepts a bunch of players who are interested to play the game, does the pre work, and delegates to `GameEngine` to play the actual game.

### GameEngine
- Responsible for conducting the actual game.
- Maintains the state of the game.
- Ask the players to make the next move and update the state of the game.
- Check if the game has finished or draw etc.
