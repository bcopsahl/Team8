import org.junit.*;
import static org.junit.jupiter.api.Assertions.*;
import main.*;
public class wraparoundBoardTest{
	static Board board;
	static Location location;
	@Before
	public void setup(){
		board = new wraparoundBoard();
		location = new Location();
	}
	@Test
	public void populateALive(){
		board.populate(true);
		assertAll("all alive tests",
			() -> assertFalse(board.aliveOrDead(location)),
			() -> assertEquals(8,board.detectNearby(location))
			);
	}
	@Test
	public void evolveAlive(){
		board.populate(true);
		board.evolve();
		assertAll("evolving alive fields",
			() -> assertFalse(board.aliveOrDead(location)),
			() -> assertEquals(0,board.detectNearby(location))
			);
	}
	@Test
	public void evolveTwice(){
		board.populate(true);
		board.evolve();
		board.evolve();
		assertAll("evolving alive fields a second time", 
			() -> assertFalse(board.aliveOrDead(location)),
			() -> assertEquals(0,board.detectNearby(location))
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
	public void deadEvolve() {
		board.populate(false);
		board.evolve();
		assertAll("evolving Dead board",
			() -> assertFalse(board.aliveOrDead(location)),
			() -> assertEquals(0,board.detectNearby(location))
		);
	}
}
