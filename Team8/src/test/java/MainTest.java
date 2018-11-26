package main;
import org.junit.*;
import static org.junit.jupiter.api.Assertions.*;
import  org.junit.jupiter.api.*;
import main.*;
public class MainTest {
	static Main main;
	static Location location;
	static Board board;
	@BeforeEach
	public void setup(){
		board = new infiniteBoard();
		main = new Main(board);
		location = new Location();	
		
	}
	@Test
	public void evolve() {
		main.populate(false);
		main.evolve();
		assertAll("evolving the board when dead",
			() -> assertFalse(board.aliveOrDead(location)),
			() -> assertEquals(0,board.detectNearby(location))
		);
	}
	


}
