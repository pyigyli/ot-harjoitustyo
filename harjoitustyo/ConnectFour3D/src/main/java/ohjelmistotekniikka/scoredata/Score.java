
package ohjelmistotekniikka.scoredata;

public class Score {
    
    private String player1;
    private String player2;
    private String player3;
    private String player4;
    private String winner;
    
    public Score(String player1, String player2, String player3, String player4, String winner) {
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
        this.player4 = player4;
        this.winner = winner;
    }
    
    public String getPlayer1() {
        return this.player1;
    }
    
    public String getPlayer2() {
        return this.player2;
    }
    
    public void setPlayer3(String name) {
        this.player3 = name;
    }
    
    public String getPlayer3() {
        return this.player3;
    }
    
    public void setPlayer4(String name) {
        this.player4 = name;
    }
    
    public String getPlayer4() {
        return this.player4;
    }
    
    public String getWinner() {
        return this.winner;
    }
}
