
package ohjelmistotekniikka.scoredata;

/**
* Class is used in conjunction with the Database and ScoreDao
* to store and retrieve scores from previous games.
*/
public class Score {
    
    private String player1;
    private String player2;
    private String player3;
    private String player4;
    private String winner;
    
    /**
    * Method creates a new score containing names of all players
    * in the game and the winner.
    * 
    * @param    player1 the name of player1
    * @param    player2 the name of player2
    * @param    player3 the name of player3
    * @param    player4 the name of player4
    * @param    winner  the name of winner
    */
    public Score(String player1, String player2, String player3, String player4, String winner) {
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
        this.player4 = player4;
        this.winner = winner;
    }
    
    /**
    * Method returns the name of player1.
    * 
    * @return   the name of player1
    */
    public String getPlayer1() {
        return this.player1;
    }
    
    /**
    * Method returns the name of player2.
    * 
    * @return   the name of player2
    */
    public String getPlayer2() {
        return this.player2;
    }
    
    /**
    * Method sets the name of player3.
    * 
    * @param    name    the name of player3
    */
    public void setPlayer3(String name) {
        this.player3 = name;
    }
    
    /**
    * Method returns the name of player3.
    * 
    * @return   the name of player3
    */
    public String getPlayer3() {
        return this.player3;
    }
    
    /**
    * Method sets the name of player4.
    * 
    * @param    name    the name of player4
    */
    public void setPlayer4(String name) {
        this.player4 = name;
    }
    
    /**
    * Method returns the name of player4.
    * 
    * @return   the name of player4
    */
    public String getPlayer4() {
        return this.player4;
    }
    
    /**
    * Method sets the name of winner.
    * 
    * @param    name    the name of winner
    */
    public void setWinner(String name) {
        this.winner = name;
    }
    
    /**
    * Method returns the name of the winner.
    * 
    * @return   the name of the winner1
    */
    public String getWinner() {
        return this.winner;
    }
}
