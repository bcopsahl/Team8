package main;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import main.*;
public class PlayerTest {
    static Player testPlayer;
    
    @BeforeEach
    public void setup(){
        testPlayer = new Player("Fred",'F');   
    }

    @Test
    public void equals(){
        Player p = new Player("Fred",'F');
        assertAll("Equals tests",
            () -> assertTrue(testPlayer.equals(p)),
            () -> assertFalse(testPlayer.equals(new Board())),
            () -> assertFalse(testPlayer.equals(new Player()))
            );
    }

    @Test
    public void toStringTest(){
        assertEquals(testPlayer.toString(),"F");
    }

    @Test
    public void getName(){
        assertEquals(testPlayer.getName(),"Fred");
    }
    @Test
    public void getId(){
        assertEquals(testPlayer.getId(),'F');
    }

    
}
