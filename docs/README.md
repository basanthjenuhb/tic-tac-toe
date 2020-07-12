# Tic-Tac-Toe

## Components

We have components who work in tandem to keep the game going.

![image](https://user-images.githubusercontent.com/20828431/87240113-48fb6580-c434-11ea-80b4-0db22cd39089.png)

[Demo](#demo)

### GameManager
- Deals with aspects that are not core to the tic-tac-toe game. 
- In our case, `GameManager` helps in coducting toss and assigning symbols to different players who are playing the game.
- In future, it can be used for other purposes, like conducting a tournament, score management etc.
- `GameManager` accepts a bunch of players who are interested to play the game, does the pre work, and delegates to `GameEngine` to play the actual game.

```
GameManager gameManager = new GameManager(new HumanPlayer(), new ComputerPlayer());
gameManager.playgame();
```
- We initialize `GameManager` to play with 1 `HumanPlayer` and 1 `ComputerPlayer`
- An interesting thng is to see how 2 computer players would play against each other.
- It always ends in a draw as both players try to make optimum moves.
```
GameManager gameManager = new GameManager(new ComputerPlayer(), new ComputerPlayer());
gameManager.playgame();
```

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

### Benefits of this design
- Non core game aspects are handled by `Gamemanager` component and doesn't affect how the `tic-tac-toe` game is played.
- Only `GameEngine` is responsible for rules of the game. If there are rules that need to be extended, we can do it without altering other components.
- `ComputerPlayer` and `HumanPlayer` are implementations of `Player` interface. So, if we want to change make the human play with a more smarter computer, we can do so without altering other components. We can have different more smarter algorithms implemented as a new implememntation of the `Player` interface.

### Demo

```
[19:05:47] bjenuhb@ ~/work/tic-tac-toe (master ) $ java -jar app/target/tic-tac-toe-exec.jar
Hello human player, Please enter your name: 
basanth
Conducting toss between 2 players
First player is Computer - (X)
Second player is basanth - (O)


|  -1-  |  -2-  |  -3-  |
-------------------------
|  -4-  |  -5-  |  -6-  |
-------------------------
|  -7-  |  -8-  |  -9-  |
-------------------------


Player Computer's move
Player Computer filled the position 5


|  -1-  |  -2-  |  -3-  |
-------------------------
|  -4-  |   X   |  -6-  |
-------------------------
|  -7-  |  -8-  |  -9-  |
-------------------------


Player basanth's move
Please enter a position, Your symbol is O
1
Player basanth filled the position 1


|   O   |  -2-  |  -3-  |
-------------------------
|  -4-  |   X   |  -6-  |
-------------------------
|  -7-  |  -8-  |  -9-  |
-------------------------


Player Computer's move
Player Computer filled the position 2


|   O   |   X   |  -3-  |
-------------------------
|  -4-  |   X   |  -6-  |
-------------------------
|  -7-  |  -8-  |  -9-  |
-------------------------


Player basanth's move
Please enter a position, Your symbol is O
8
Player basanth filled the position 8


|   O   |   X   |  -3-  |
-------------------------
|  -4-  |   X   |  -6-  |
-------------------------
|  -7-  |   O   |  -9-  |
-------------------------


Player Computer's move
Player Computer filled the position 7


|   O   |   X   |  -3-  |
-------------------------
|  -4-  |   X   |  -6-  |
-------------------------
|   X   |   O   |  -9-  |
-------------------------


Player basanth's move
Please enter a position, Your symbol is O
9
Player basanth filled the position 9


|   O   |   X   |  -3-  |
-------------------------
|  -4-  |   X   |  -6-  |
-------------------------
|   X   |   O   |   O   |
-------------------------


Player Computer's move
Player Computer filled the position 3


|   O   |   X   |   X   |
-------------------------
|  -4-  |   X   |  -6-  |
-------------------------
|   X   |   O   |   O   |
-------------------------


Player Computer won the game. Congratulations 😀
```
