
package ohjelmistotekniikka.player;

public class Player {
    
    private int number;
    private String name;
    
    public Player(int number, String name) {
        this.number = number;
        this.name = name;
    }
    
    public void setNumber(int number) {
        this.number = number;
    }
    
    public int getNumber() {
        return this.number;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
}
