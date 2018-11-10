
package ohjelmistotekniikka.gamelogic;

public class Gamelogic {
    
    private int players;
    // Board size
    private int length;
    private int width;
    private int height;
    private int[][][] board;
    
    public Gamelogic(int players, int length, int width, int height) {
        this.players = players;
        this.length = length;
        this.width = width;
        this.height = height;
        this.board = new int[length][width][height];
    }
    
    public void setSettings(int players, int length, int width, int height) {
        this.players = players;
        this.length = length;
        this.width = width;
        this.height = height;
        this.board = new int[length][width][height];
    }
    
    // Return an array where every slot a player has a piece in, is set to 1.
    public int[][][] checkBoard(int playerNumber) {
        int[][][] playerBoard = new int[this.length][this.width][this.height];
        // Check the ownership of each piece on the board
        for (int i = 1; i <= this.length; i++) {
            for (int j = 1; j <= this.width; j++) {
                for (int k = 1; k <= this.height; k++) {
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
        for (int i = 1; i <= this.length; i++) {
            for (int j = 1; j <= this.width; j++) {
                for (int k = 1; k <= this.height; k++) {
                    if (checkFourInRow(playerBoard[i][j][k])) {
                        return true;
                    }
                }
            }
        }
        // j-axis
        for (int i = 1; i <= this.length; i++) {
            for (int k = 1; k <= this.height; k++) {
                for (int j = 1; j <= this.width; j++) {
                    if (checkFourInRow(playerBoard[i][j][k])) {
                        return true;
                    }
                }
            }
        }
        // i-axis
        for (int k = 1; k <= this.height; k++) {
            for (int j = 1; j <= this.width; j++) {
                for (int i = 1; i <= this.length; i++) {
                    if (checkFourInRow(playerBoard[i][j][k])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public boolean checkFourInRow(int x) {
        // Create string of ones and zeroes that indicates if player has a piece in a slot
        String checkString = "";
        if (x == 0) {
            checkString = checkString + "0";
        } else if (x == 1) {
            checkString = checkString + "1";
        }
        // If the checkString contains four 1's in a row, player wins
        if (checkString.contains("1111")) {
            return true;
        }
        return false;
    }
    
    public void setPlayers(int players) {
        this.players = players;
    }
    
    public int getPlayers() {
        return this.players;
    }
    
    public void setLength(int length) {
        this.length = length;
        this.board = new int[length][this.width][this.height];
    }
    
    public int getLength() {
        return this.length;
    }
    
    public void setWidth(int width) {
        this.width = width;
        this.board = new int[this.length][width][this.height];
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public void setHeight(int height) {
        this.height = height;
        this.board = new int[this.length][this.width][height];
    }
    
    public int getHeight() {
        return this.height;
    }
}
