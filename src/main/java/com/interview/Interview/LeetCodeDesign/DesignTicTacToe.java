package com.interview.Interview.LeetCodeDesign;

/*
The given problem describes a tic-tac-toe game which is generalized for an n x n board, as opposed to the traditional
3 x 3 board. Two players take turns to place their mark on the board. The goal for each player is to get n of their own
marks in a row, either horizontally, vertically, or diagonally. The challenge is to implement a class TicTacToe that
can process each move made by the players and determine the status of the game after each move, specifically:

If the game continues with no winner.
If player 1 has won.
If player 2 has won.

solution thinking:

main intuition is to keep track of the number of marks each player has in rows, columns and diagonals

✅ We have a count for every row, column, and diagonal.
✅ We want an easy way to track if an entire line is dominated by one player.

How?
If:
	•	Player 1 plays -> Add +1
	•	Player 2 plays -> Add -1

Then:
	•	If a row becomes n ➔ All positions by Player 1 (win).
	•	If a row becomes -n ➔ All positions by Player 2 (win).

👉 This saves you from checking every spot every time.

🎯 Simple example (size = 3):

Row counts after moves:

Player      Position        Action      rows[] Result
p1           [0,0]         row[0]+=1      [1,0,0]
p2           [1,1]         row[1]-=1      [1,-1,0]
p1           [0,1]         row[0]+=1      [2,-1,0]
p2           [2,2]         row[2]-=1      [2,-1,-1]
p3           [0,2]         row[0]+=1      [3,-1,-1]  --> 3 means player 1 wins


✅ So:
	•	You’re NOT checking every spot.
	•	You’re updating one number per line.
	•	Result == n ➔ Player 1 wins.
	•	Result == -n ➔ Player 2 wins.
 */
public class DesignTicTacToe {

    int size;
    int[] rows;     //→ track net sum per row/column
    int[] cols;
    int diagonal;   //→ for top-left to bottom-right
    int anti_diagonal;  //→ for top-right to bottom-left
    int latsPlayerId = 0;

    /*
    This structure:
    Works for entire row or column
    Works for main diagonal (when row == col)
    Works for anti-diagonal (when row + col == size - 1)
     */

    DesignTicTacToe(int size) {
        this.size = size;
        this.rows = new int[size];
        this.cols = new int[size];
        this.diagonal = 0;
        this.anti_diagonal = 0;
    }

    public int move(Move move) {
        int row = move.getRow();
        int col = move.getCol();
        int playerId = move.getPlayer().getId();

        if(latsPlayerId != -1 && latsPlayerId == playerId) {
            throw new IllegalArgumentException("No Player can be play twice in a row");
        }

        latsPlayerId = playerId;

        int value = (playerId == 1) ? 1:-1;

        rows[row] += value;
        cols[col] += value;

        if(row == col) {
            diagonal += value;
        }

        if (row + col == (size-1)) {
            anti_diagonal += value;
        }

        //Win detection
        if (Math.abs(rows[row]) == size || Math.abs(cols[col]) == size || Math.abs(diagonal) == size || Math.abs(anti_diagonal) ==size) {
            return playerId;
        }

        return 0; // No win
    }

    public static void main(String[] args) {
        DesignTicTacToe t = new DesignTicTacToe(3);

        Player p1 = new Player(1);
        Player p2 = new Player(-1);

        System.out.println(t.move(new Move(0,0,p1)));
        System.out.println(t.move(new Move(1,1,p2)));
        System.out.println(t.move(new Move(0,1,p1)));
        System.out.println(t.move(new Move(2,2,p2)));
        System.out.println(t.move(new Move(0,2,p1))); //p1 win

    }

}

class Player {
    private final int id;

    public Player(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

class Move {
    private final int row;
    private final int col;
    private final Player player;

    public Move(int row, int col, Player player) {
        this.row = row;
        this.col = col;
        this.player = player;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Player getPlayer() {
        return player;
    }
}


/*
✅ Let's Test Other Scenarios:
1. Column Win
t.move(0,0,p1)
t.move(0,1,p2)
t.move(1,0,p1)
t.move(1,1,p2)
t.move(2,0,p1) → P1 wins by column 0

cols[0] = 3 → works ✅

2. Diagonal Win (top-left to bottom-right)
t.move(0,0,p1)
t.move(0,1,p2)
t.move(1,1,p1)
t.move(1,2,p2)
t.move(2,2,p1) → P1 wins by diagonal

diagonal = 3 → works ✅

row == col

     0   1   2   ← column indices
   +---+---+---+
 0 | P1|   |   |
   +---+---+---+
 1 |   | P1|   |
   +---+---+---+
 2 |   |   | P1|
   +---+---+---+


3. Anti-Diagonal Win (top-right to bottom-left)
t.move(0,2,p1)
t.move(0,0,p2)
t.move(1,1,p1)
t.move(1,0,p2)
t.move(2,0,p1) → P1 wins by anti-diagonal

anti_diagonal = 3 → works ✅


 */