
package ohjelmistotekniikka.gamelogic;

public class Gamelogic {
    
    private int players;
    private int turn;
    // Board size
    private int width; // i-axis
    private int height;  // j-axis
    private int length; // k-axis
    private int[][][] board;
    
    public Gamelogic() {
        this.players = 2;
        this.turn = 1;
        this.width = 7;
        this.height = 7;
        this.length = 7;
        this.board = new int[7][7][7];
    }
    
    public void newGame() {
        this.turn = 1;
        for (int i = 1; i <= this.width; i++) {
            for (int j = 1; j <= this.height; j++) {
                for (int k = 1; k <= this.length; k++) {
                    this.board[i][j][k] = 0;
                }
            }
        }
    }
    
    // Place players piece and return true if placing piece in the column is possible
    public boolean placePiece(int i, int k, int[][][] currentBoard) {
        // Find the bottom most free slot
        for (int j = this.height; j >= 0; j--) {
            if (currentBoard[i][j][k] == 0) {
                // Place the piece and end loop
                this.board[i][j][k] = this.turn / this.players;
                return true;
            }
        }
        return false;
    }
    
    // Return an array where every slot a player has a piece in, is set to 1.
    public int[][][] checkBoard(int playerNumber) {
        int[][][] playerBoard = new int[this.width][this.height][this.length];
        // Check the ownership of each piece on the board
        for (int i = 1; i <= this.width; i++) {
            for (int j = 1; j <= this.height; j++) {
                for (int k = 1; k <= this.length; k++) {
                    // Numbers determinate what piece belongs to whom
                    if (this.board[i][j][k] == playerNumber) {
                        playerBoard[i][j][k] = 1;
                    } else {
                        playerBoard[i][j][k] = 0;
                    }
                }
            }
        }
        return playerBoard;
    }
    
    // Return true if playerBoard has four in a row. Diagonal rows don't count
    public boolean checkWin(int[][][] playerBoard) {
        // Check every row along every axis
        // k-axis
        for (int i = 1; i <= this.width; i++) {
            for (int j = 1; j <= this.height; j++) {
                // Create string of ones and zeroes that indicates if player has a piece in a slot
                String checkString = "";
                for (int k = 1; k <= this.length; k++) {
                    if (k == 0) {
                        checkString = checkString + "0";
                    } else if (k == 1) {
                        checkString = checkString + "1";
                    }
                    // If player has four in a row, return true
                    if (checkString.contains("1111")) {
                        return true;
                    }
                }
            }
        }
        // j-axis
        for (int i = 1; i <= this.width; i++) {
            for (int k = 1; k <= this.length; k++) {
                // Create string of ones and zeroes that indicates if player has a piece in a slot
                String checkString = "";
                for (int j = 1; j <= this.height; j++) {
                    if (j == 0) {
                        checkString = checkString + "0";
                    } else if (j == 1) {
                        checkString = checkString + "1";
                    }
                    // If player has four in a row, return true
                    if (checkString.contains("1111")) {
                        return true;
                    }
                }
            }
        }
        // i-axis
        for (int k = 1; k <= this.length; k++) {
            for (int j = 1; j <= this.height; j++) {
                // Create string of ones and zeroes that indicates if player has a piece in a slot
                String checkString = "";
                for (int i = 1; i <= this.width; i++) {
                    if (i == 0) {
                        checkString = checkString + "0";
                    } else if (i == 1) {
                        checkString = checkString + "1";
                    }
                    // If player has four in a row, return true
                    if (checkString.contains("1111")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public int[][][] getBoard() {
        return this.board;
    }
    
    public void setPlayers(int players) {
        this.players = players;
    }
    
    public int getPlayers() {
        return this.players;
    }
    
    public void nextTurn() {
        this.turn++;
    }
    
    public void setTurn(int turn) {
        this.turn = turn;
    }
    
    public int getTurn() {
        return this.turn;
    }
    
    public void setWidth(int width) {
        this.width = width;
        this.board = new int[width][this.height][this.length];
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public void setHeight(int height) {
        this.height = height;
        this.board = new int[this.width][height][this.length];
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public void setLength(int length) {
        this.length = length;
        this.board = new int[this.width][this.height][length];
    }
    
    public int getLength() {
        return this.length;
    }
}
