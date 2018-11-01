import org.junit.*;
import static org.junit.jupiter.api.Assertions.*;
import main.*;
public class infiniteBoardTest{
	static Board board;
	static Location location;
	@Before
	public void setup(){
		board = new infiniteBoard();
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
	
}
