
package ohjelmistotekniikka.gamelogic;

/**
 * Class provides the gamelogic for three-dimensional connect four.
 */
public class Gamelogic {
    
    private int players;
    private int turn;
    // Board size
    private int width; // i-axis
    private int height;  // j-axis
    private int length; // k-axis
    private int[][][] board;
    
    /**
    * Method initializes the gamelogic with default settings.
    */
    public Gamelogic() {
        this.players = 2;
        this.turn = 0;
        this.width = 7;
        this.height = 7;
        this.length = 7;
        this.board = new int[7][7][7];
    }
    
    /**
    * Method starts a new game with current settings.
    */
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
    
    /**
    * Method takes two integers that must be within the sizes of the
    * board array and drops a piece on selected column as low as
    * there are empty slots available. If selected column has empty
    * slots a number representing current player's number is set on
    * the slot and method returns true. If this isn't possible,
    * piece is not placed and method returns false. Empty slots must
    * be represented as -1 in the array.
    *
    * @param    i               selected x-coordinate
    * @param    k               selected z-coordinate
    * @param    currentBoard    three-dimensional array of numbers
    * 
    * @return   Boolean value indicating whether the placement
    *           was accepted or not
    */
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
    
    /**
    * Method inspects every slot on the three-dimensional board
    * array and for every slot that has playerNumber in it, the
    * returned array will contain a integer 1 in those slots.
    *
    * @param    playerNumber    integer to be compared against
    *                           every slot in the board
    * 
    * @return   three-dimensional board that contains integer
    *           0 for every slot not owned by the player and
    *           integer 1 for every slot owned by the player
    */
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
    
    /**
    * Method takes three-dimensional array of integers that contains
    * 1's and 0's indicating if a player has a piece in that slot.
    * The method goes through every row and diagonal and return true
    * if playerBoard has four in a row in any row or diagonal.
    *
    * @param    playerBoard     integer to be compared against
    *                           every slot in the board
    * 
    * @return   boolean value indicating whether the player has won
    *           the game or not
    */
    public Boolean checkWin(int[][][] playerBoard) {
        return this.checkFourInRow(playerBoard, 1, 0, 0) ||
                this.checkFourInRow(playerBoard, 0, 1, 0) ||
                this.checkFourInRow(playerBoard, 0, 0, 1) ||
                this.checkFourInRow(playerBoard, 1, 1, 0) ||
                this.checkFourInRow(playerBoard, 1, -1, 0) ||
                this.checkFourInRow(playerBoard, 1, 0, 1) ||
                this.checkFourInRow(playerBoard, 1, 0, -1) ||
                this.checkFourInRow(playerBoard, 0, 1, 1) ||
                this.checkFourInRow(playerBoard, 0, 1, -1) ||
                this.checkFourInRow(playerBoard, 1, 1, 1) ||
                this.checkFourInRow(playerBoard, -1, 1, 1) ||
                this.checkFourInRow(playerBoard, 1, -1, 1) ||
                this.checkFourInRow(playerBoard, 1, 1, -1);
    }
    
    /**
    * Method takes three-dimensional array of integers that contains
    * 1's and 0's indicating if a player has a piece in that slot.
    * The method inspects every slot in the board and return true
    * if player has four pieces in a row along the axis or diagonal
    * specified by the parameters.
    *
    * @param    playerBoard     integer to be compared against
    *                           every slot in the board
    * @param    x               integer between values -1 and 1
    *                           indicating the direction of the row
    *                           under inspection on the x-axis
    * @param    y               integer between values -1 and 1
    *                           indicating the direction of the row
    *                           under inspection on the y-axis
    * @param    z               integer between values -1 and 1
    *                           indicating the direction of the row
    *                           under inspection on the z-axis
    * 
    * @return   boolean value indicating whether the player has four
    *           pieces in a row along the axis or diagonal specified
    *           by the parameters
    */
    public Boolean checkFourInRow(int[][][] playerBoard, int x, int y, int z) {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                for (int k = 0; k < this.length; k++) {
                    try {
                        if (playerBoard[i][j][k] == 1 &&
                                playerBoard[i + x][j + y][k + z] == 1 &&
                                playerBoard[i + x * 2][j + y * 2][k + z * 2] == 1 &&
                                playerBoard[i + x * 3][j + y * 3][k + z * 3] == 1) {
                            return true;
                        }
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        // Do nothing when outside of the array
                    }
                }
            }
        }
        return false;
    }
    
    /**
    * Method returns three-dimensional array that represents the
    * game's board.
    * 
    * @return   three-dimensional array that represents the
    *           game's board
    */
    public int[][][] getBoard() {
        return this.board;
    }
    
    /**
    * Method sets the amount of players in the game.
    * 
    * @param    players     the amount of players in the game
    */
    public void setPlayers(int players) {
        this.players = players;
    }
    
    /**
    * Method returns the amount of players in the game.
    * 
    * @return   the amount of players in the game
    */
    public int getPlayers() {
        return this.players;
    }
    
    /**
    * Method increments the turn number by one.
    */
    public void nextTurn() {
        this.turn++;
    }
    
    /**
    * Method sets the turn number.
    * 
    * @param    turn    integer indicating the number of turns
    *                   passed
    */
    public void setTurn(int turn) {
        this.turn = turn;
    }
    
    /**
    * Method returns the number of turns passed in the game.
    * 
    * @return   integer indicating the number of turns passed
    */
    public int getTurn() {
        return this.turn;
    }
    
    /**
    * Method sets the width of the three-dimensional array that
    * represents the game's board.
    * 
    * @param    width   the width of the array
    */
    public void setWidth(int width) {
        this.width = width;
        this.board = new int[width][this.height][this.length];
    }
    
    /**
    * Method returns the width of the board.
    * 
    * @return   integer indicating the width of the game's board
    */
    public int getWidth() {
        return this.width;
    }
    
    /**
    * Method sets the height of the three-dimensional array that
    * represents the game's board.
    * 
    * @param    height  the height of the array
    */
    public void setHeight(int height) {
        this.height = height;
        this.board = new int[this.width][height][this.length];
    }
    
    /**
    * Method returns the height of the board.
    * 
    * @return   integer indicating the height of the game's board
    */
    public int getHeight() {
        return this.height;
    }
    
    /**
    * Method sets the length of the three-dimensional array that
    * represents the game's board.
    * 
    * @param    length  the length of the array
    */
    public void setLength(int length) {
        this.length = length;
        this.board = new int[this.width][this.height][length];
    }
    
    /**
    * Method returns the length of the board.
    * 
    * @return   integer indicating the length of the game's board
    */
    public int getLength() {
        return this.length;
    }
    
    /**
    * Method sets an integer value into a slot on a
    * three-dimensional array that represents the game's board.
    * 
    * @param    i       the x-coordinate of the array
    * @param    j       the y-coordinate of the array
    * @param    k       the z-coordinate of the array
    * @param    value   the value to be set on the slot
    */
    public void setSlot(int i, int j, int k, int value) {
        this.board[i][j][k] = value;
    }
    
    /**
    * Method returns the integer in the slot of the 
    * three-dimensionl array that represents the game's board.
    * 
    * @param    i       the x-coordinate of the array
    * @param    j       the y-coordinate of the array
    * @param    k       the z-coordinate of the array
    * 
    * @return   integer that is in the slot
    */
    public int getSlot(int i, int j, int k) {
        return this.board[i][j][k];
    }
}
