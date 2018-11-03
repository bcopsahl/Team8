import org.junit.*;
import static org.junit.jupiter.api.Assertions.*;
import main.*;
public class BoardTest{
	static Board board;
	static Location location;
	@Before
	public void setup(){
		board = new Board();
		location = new Location();
	}
	@Test
	public void populateALive(){
		board.populate(true);
		assertAll("all alive tests",
			() -> assertTrue(board.aliveOrDead(location)),
			() -> assertEquals(3,board.detectNearby(location))
		);
	}
	@Test
	public void populateDead(){
		board.populate(false);
		assertAll("all dead tests",
			() -> assertFalse(board.aliveOrDead(location)),
			() -> assertEquals(0,board.detectNearby(location))
		);
	}	
	@Test
	public void populateDiffSize(){
		board = new Board(2,2);
		board.populate(false);	
		assertAll("dead two by two",
			() -> assertFalse(board.aliveOrDead(location)),
			() -> assertEquals(0,board.detectNearby(location))
		);
		
	}
	@Test
	public void serialize() {
		board.populate(true);
		board.serialize();
		board.evolve();
		board.deSerialize();
		assertTrue(board.aliveOrDead(location));
	}
}