
package ohjelmistotekniikka.player;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
    
    Player player;
    
    @Before
    public void setUp() {
        player = new Player("name");
    }
    
    @Test
    public void getNameWorks() {
        assertEquals("name", player.getName());
    }
    
    @Test
    public void setNameWorks() {
        player.setName("test");
        assertEquals("test", player.getName());
    }
}
