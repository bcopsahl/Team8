import org.junit.*;
import static org.junit.jupiter.api.Assertions.*;
import main.*;
public class MainTest {
	static Main testMain;
	static Location loc;
	static Board board;
	@Before
	public void setup(){
		board = new infiniteBoard();
		testMain = new Main(board);
		loc = new Location(0,0);	
		
	}


}
