
package ohjelmistotekniikka.gamelogic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class GamelogicTest {
    
    Gamelogic game;
    
    @Before
    public void setUp() {
        game = new Gamelogic();
        game.setWidth(4);
        game.setHeight(4);
        game.setLength(4);
        game.newGame();
    }
    
    @Test
    public void newGameChangesTurnToZero() {
        game.setTurn(3);
        game.newGame();
        assertEquals(0, game.getTurn());
    }
    
    @Test
    public void newGameEmptiesBoard() {
        game.setSlot(0, 0, 0, 3);
        game.newGame();
        assertEquals(-1, game.getSlot(0, 0, 0));
    }
    
    @Test
    public void placePieceWorks() {
        assertEquals(-1, game.getSlot(0, 0, 0));
        game.setPlayers(1);
        game.placePiece(0, 0, game.getBoard());
        assertEquals(0, game.getSlot(0, 0, 0));
    }
    
    @Test
    public void placePieceReturnsTrue() {
        assertTrue(game.placePiece(0, 0, game.getBoard()));
    }
    
    @Test
    public void placePieceReturnsFalse() {
        game.setHeight(1);
        game.setSlot(0, 0, 0, 3);
        assertTrue(!game.placePiece(0, 0, game.getBoard()));
    }
    
    @Test
    public void checkBoardWorks() {
        for (int i = 0; i < game.getWidth(); i++) {
            for (int j = 0; j < game.getHeight(); j++) {
                for (int k = 0; k < game.getLength(); k++) {
                    game.setSlot(i, j, k, 3);
                }
            }
        }
        int[][][] playerBoard = game.checkBoard(3);
        for (int i = 0; i < game.getWidth(); i++) {
            for (int j = 0; j < game.getHeight(); j++) {
                for (int k = 0; k < game.getLength(); k++) {
                    assertEquals(1, playerBoard[i][j][k]);
                }
            }
        }
    }
    
    @Test
    public void checkWinReturnsTrueWithCheckWinX() {
        game.setSlot(0, 0, 0, 3);
        game.setSlot(1, 0, 0, 3);
        game.setSlot(2, 0, 0, 3);
        int[][][] playerBoard = game.checkBoard(3);
        assertTrue(!game.checkWin(playerBoard));
        game.setSlot(3, 0, 0, 3);
        playerBoard = game.checkBoard(3);
        assertTrue(game.checkWin(playerBoard));
    }
    
    @Test 
    public void checkWinReturnsTrueWithCheckWinY() {
        game.setSlot(0, 0, 0, 3);
        game.setSlot(0, 1, 0, 3);
        game.setSlot(0, 2, 0, 3);
        int[][][] playerBoard = game.checkBoard(3);
        assertTrue(!game.checkWin(playerBoard));
        game.setSlot(0, 3, 0, 3);
        playerBoard = game.checkBoard(3);
        assertTrue(game.checkWin(playerBoard));
    }
    
    @Test 
    public void checkWinReturnsTrueWithCheckWinZ() {
        game.setSlot(0, 0, 0, 3);
        game.setSlot(0, 0, 1, 3);
        game.setSlot(0, 0, 2, 3);
        int[][][] playerBoard = game.checkBoard(3);
        assertTrue(!game.checkWin(playerBoard));
        game.setSlot(0, 0, 3, 3);
        playerBoard = game.checkBoard(3);
        assertTrue(game.checkWin(playerBoard));
    }
    
    @Test 
    public void checkWinReturnsTrueWithCheckWinXY1() {
        game.setSlot(0, 0, 0, 3);
        game.setSlot(1, 1, 0, 3);
        game.setSlot(2, 2, 0, 3);
        int[][][] playerBoard = game.checkBoard(3);
        assertTrue(!game.checkWin(playerBoard));
        game.setSlot(3, 3, 0, 3);
        playerBoard = game.checkBoard(3);
        assertTrue(game.checkWin(playerBoard));
    }
    
    @Test 
    public void checkWinReturnsTrueWithCheckWinXY2() {
        game.setSlot(0, 3, 0, 3);
        game.setSlot(1, 2, 0, 3);
        game.setSlot(2, 1, 0, 3);
        int[][][] playerBoard = game.checkBoard(3);
        assertTrue(!game.checkWin(playerBoard));
        game.setSlot(3, 0, 0, 3);
        playerBoard = game.checkBoard(3);
        assertTrue(game.checkWin(playerBoard));
    }
    
    @Test 
    public void checkWinReturnsTrueWithCheckWinXZ1() {
        game.setSlot(0, 0, 0, 3);
        game.setSlot(1, 0, 1, 3);
        game.setSlot(2, 0, 2, 3);
        int[][][] playerBoard = game.checkBoard(3);
        assertTrue(!game.checkWin(playerBoard));
        game.setSlot(3, 0, 3, 3);
        playerBoard = game.checkBoard(3);
        assertTrue(game.checkWin(playerBoard));
    }
    
    @Test 
    public void checkWinReturnsTrueWithCheckWinXZ2() {
        game.setSlot(0, 0, 3, 3);
        game.setSlot(1, 0, 2, 3);
        game.setSlot(2, 0, 1, 3);
        int[][][] playerBoard = game.checkBoard(3);
        assertTrue(!game.checkWin(playerBoard));
        game.setSlot(3, 0, 0, 3);
        playerBoard = game.checkBoard(3);
        assertTrue(game.checkWin(playerBoard));
    }
    
    @Test 
    public void checkWinReturnsTrueWithCheckWinYZ1() {
        game.setSlot(0, 0, 0, 3);
        game.setSlot(0, 1, 1, 3);
        game.setSlot(0, 2, 2, 3);
        int[][][] playerBoard = game.checkBoard(3);
        assertTrue(!game.checkWin(playerBoard));
        game.setSlot(0, 3, 3, 3);
        playerBoard = game.checkBoard(3);
        assertTrue(game.checkWin(playerBoard));
    }
    
    @Test 
    public void checkWinReturnsTrueWithCheckWinYZ2() {
        game.setSlot(0, 0, 3, 3);
        game.setSlot(0, 1, 2, 3);
        game.setSlot(0, 2, 1, 3);
        int[][][] playerBoard = game.checkBoard(3);
        assertTrue(!game.checkWin(playerBoard));
        game.setSlot(0, 3, 0, 3);
        playerBoard = game.checkBoard(3);
        assertTrue(game.checkWin(playerBoard));
    }
    
    @Test 
    public void checkWinReturnsTrueWithCheckWinXYZ1() {
        game.setSlot(0, 0, 0, 3);
        game.setSlot(1, 1, 1, 3);
        game.setSlot(2, 2, 2, 3);
        int[][][] playerBoard = game.checkBoard(3);
        assertTrue(!game.checkWin(playerBoard));
        game.setSlot(3, 3, 3, 3);
        playerBoard = game.checkBoard(3);
        assertTrue(game.checkWin(playerBoard));
    }
    
    @Test 
    public void checkWinReturnsTrueWithCheckWinXYZ2() {
        game.setSlot(3, 0, 0, 3);
        game.setSlot(2, 1, 1, 3);
        game.setSlot(1, 2, 2, 3);
        int[][][] playerBoard = game.checkBoard(3);
        assertTrue(!game.checkWin(playerBoard));
        game.setSlot(0, 3, 3, 3);
        playerBoard = game.checkBoard(3);
        assertTrue(game.checkWin(playerBoard));
    }
    
    @Test 
    public void checkWinReturnsTrueWithCheckWinXYZ3() {
        game.setSlot(0, 3, 0, 3);
        game.setSlot(1, 2, 1, 3);
        game.setSlot(2, 1, 2, 3);
        int[][][] playerBoard = game.checkBoard(3);
        assertTrue(!game.checkWin(playerBoard));
        game.setSlot(3, 0, 3, 3);
        playerBoard = game.checkBoard(3);
        assertTrue(game.checkWin(playerBoard));
    }
    
    @Test 
    public void checkWinReturnsTrueWithCheckWinXYZ4() {
        game.setSlot(0, 0, 3, 3);
        game.setSlot(1, 1, 2, 3);
        game.setSlot(2, 2, 1, 3);
        int[][][] playerBoard = game.checkBoard(3);
        assertTrue(!game.checkWin(playerBoard));
        game.setSlot(3, 3, 0, 3);
        playerBoard = game.checkBoard(3);
        assertTrue(game.checkWin(playerBoard));
    }
    
    @Test
    public void getPlayersWorks() {
        game.setPlayers(4);
        assertEquals(4, game.getPlayers());
    }
    
    @Test
    public void nextTurnWorks() {
        game.setTurn(1);
        game.nextTurn();
        assertEquals(2, game.getTurn());
    }
}