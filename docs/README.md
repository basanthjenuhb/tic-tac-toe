# Tic-Tac-Toe

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
- Uses `GameState` to manage the state.

### GameState
- It is a dumb state object who keeps track of the `tic-tac-toe` game board.
- Has logic to validate that illegal moves are not made.

### Player
- `Player` is anyone who is interested to play the `tic-tac-toe` game.
- It can be a `Human Player` or a `Computer player`.
- The game engine doesn't know and care about which players are playing as long as they maintain the contract of providing the next move to play the game.
- `Human Player` is us, and just takes input from `STDIN` to tell the next move.
- `Computer player` is the smart computer, it uses `MiniMax algorithm` to find the next best move given a state.
