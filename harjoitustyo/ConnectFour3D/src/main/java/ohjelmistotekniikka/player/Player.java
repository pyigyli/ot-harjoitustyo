
package ohjelmistotekniikka.player;

/**
* Class is used to maintain the names for the players.
*/
public class Player {
    
    private String name;
    
    /**
    * Method creates a new player with desired name.
    * 
    * @param    name    the name of the player
    */
    public Player(String name) {
        this.name = name;
    }
    
    /**
    * Method changes player's name with new one.
    * 
    * @param    name    the name of the player
    */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
    * Method creates a new player with desired name.
    * 
    * @return   the name of the player
    */
    public String getName() {
        return this.name;
    }
}
