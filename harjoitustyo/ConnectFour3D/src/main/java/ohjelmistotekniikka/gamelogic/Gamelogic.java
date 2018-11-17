
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
        this.turn = 0;
        this.width = 7;
        this.height = 7;
        this.length = 7;
        this.board = new int[7][7][7];
    }
    
    public void newGame() {
        this.turn = 0;
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                for (int k = 0; k < this.length; k++) {
                    this.board[i][j][k] = -1;
                }
            }
        }
    }
    
    // Place players piece and return true if placing piece in the column is possible
    public boolean placePiece(int i, int k, int[][][] currentBoard) {
        // Find the bottom most free slot
        for (int j = 0; j < this.height; j++) {
            if (currentBoard[i][j][k] == -1) {
                // Place the piece and end loop
                this.board[i][j][k] = this.turn % this.players;
                return true;
            }
        }
        return false;
    }
    
    // Return an array where every slot a player has a piece in, is set to 1.
    public int[][][] checkBoard(int playerNumber) {
        int[][][] playerBoard = new int[this.width][this.height][this.length];
        // Check the ownership of each piece on the board
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                for (int k = 0; k < this.length; k++) {
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
    
    // Go through every winCheck and return true if any of them are true
    public Boolean checkWin(int[][][] playerBoard) {
        if (this.checkWinX(playerBoard) ||
                this.checkWinY(playerBoard) ||
                this.checkWinZ(playerBoard) ||
                this.checkWinXY1(playerBoard) ||
                this.checkWinXY2(playerBoard) ||
                this.checkWinXZ1(playerBoard) ||
                this.checkWinXZ2(playerBoard) ||
                this.checkWinYZ1(playerBoard) ||
                this.checkWinYZ2(playerBoard)) {
            return true;
        }
        return false;
    }
    
    // Check every row parallel to X-axis
    public boolean checkWinX(int[][][] playerBoard) {
        for (int k = 0; k < this.length; k++) {
            for (int j = 0; j < this.height; j++) {
                for (int i = 0; i < this.width; i++) {
                    if (i < this.width - 3) {
                        if (playerBoard[i][j][k] == 1 &&
                                playerBoard[i + 1][j][k] == 1 &&
                                playerBoard[i + 2][j][k] == 1 &&
                                playerBoard[i + 3][j][k] == 1) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    // Check every row parallel to Y-axis
    public boolean checkWinY(int[][][] playerBoard) {
        for (int i = 0; i < this.width; i++) {
            for (int k = 0; k < this.length; k++) {
                for (int j = 0; j < this.height; j++) {
                    if (j < this.height - 3) {
                        if (playerBoard[i][j][k] == 1 &&
                                playerBoard[i][j + 1][k] == 1 &&
                                playerBoard[i][j + 2][k] == 1 &&
                                playerBoard[i][j + 3][k] == 1) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    // Check every row parallel to Z-axis
    public boolean checkWinZ(int[][][] playerBoard) {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                for (int k = 0; k < this.length; k++) {
                    if (k < this.length - 3) {
                        if (playerBoard[i][j][k] == 1 &&
                                playerBoard[i][j][k + 1] == 1 &&
                                playerBoard[i][j][k + 2] == 1 &&
                                playerBoard[i][j][k + 3] == 1) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    // Check every row parallel to diagonal where X increases and Y increases
    public boolean checkWinXY1(int[][][] playerBoard) {
        for (int k = 0; k < this.length; k++) {
            for (int i = 0; i < this.width; i++) {
                for (int j = 0; j < this.height; j++) {
                    if (i < this.width - 3 && j < this.height - 3) {
                        if (playerBoard[i][j][k] == 1 &&
                                playerBoard[i + 1][j + 1][k] == 1 &&
                                playerBoard[i + 2][j + 2][k] == 1 &&
                                playerBoard[i + 3][j + 3][k] == 1) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    // Check every row parallel to diagonal where X increases and Y decreases
    public boolean checkWinXY2(int[][][] playerBoard) {
        for (int k = 0; k < this.length; k++) {
            for (int i = 0; i < this.width; i++) {
                for (int j = 0; j < this.height; j++) {
                    if (i < this.width - 3 && j >= 3) {
                        if (playerBoard[i][j][k] == 1 &&
                                playerBoard[i + 1][j - 1][k] == 1 &&
                                playerBoard[i + 2][j - 2][k] == 1 &&
                                playerBoard[i + 3][j - 3][k] == 1) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    // Check every row parallel to diagonal where X increases and Z increases
    public boolean checkWinXZ1(int[][][] playerBoard) {
        for (int j = 0; j < this.height; j++) {
            for (int k = 0; k < this.length; k++) {
                for (int i = 0; i < this.width; i++) {
                    if (i < this.width - 3 && k < this.length - 3) {
                        if (playerBoard[i][j][k] == 1 &&
                                playerBoard[i + 1][j][k + 1] == 1 &&
                                playerBoard[i + 2][j][k + 2] == 1 &&
                                playerBoard[i + 3][j][k + 3] == 1) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    // Check every row parallel to diagonal where X increases and Z decreases
    public boolean checkWinXZ2(int[][][] playerBoard) {
        for (int j = 0; j < this.height; j++) {
            for (int k = 0; k < this.length; k++) {
                for (int i = 0; i < this.width; i++) {
                    if (i < this.width - 3 && k >= 3) {
                        if (playerBoard[i][j][k] == 1 &&
                                playerBoard[i + 1][j][k - 1] == 1 &&
                                playerBoard[i + 2][j][k - 2] == 1 &&
                                playerBoard[i + 3][j][k - 3] == 1) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    // Check every row parallel to diagonal where Y increases and Z increases
    public boolean checkWinYZ1(int[][][] playerBoard) {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                for (int k = 0; k < this.length; k++) {
                    if (j < this.height - 3 && k < this.length - 3) {
                        if (playerBoard[i][j][k] == 1 &&
                                playerBoard[i][j + 1][k + 1] == 1 &&
                                playerBoard[i][j + 2][k + 2] == 1 &&
                                playerBoard[i][j + 3][k + 3] == 1) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    // Check every row parallel to diagonal where Y increases and Z decreases
    public boolean checkWinYZ2(int[][][] playerBoard) {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                for (int k = 0; k < this.length; k++) {
                    if (j < this.height - 3 && k >= 3) {
                        if (playerBoard[i][j][k] == 1 &&
                                playerBoard[i][j + 1][k - 1] == 1 &&
                                playerBoard[i][j + 2][k - 2] == 1 &&
                                playerBoard[i][j + 3][k - 3] == 1) {
                            return true;
                        }
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
    
    public void setSlot(int i, int j, int k, int value) {
        this.board[i][j][k] = value;
    }
    
    public int getSlot(int i, int j, int k) {
        return this.board[i][j][k];
    }
}
