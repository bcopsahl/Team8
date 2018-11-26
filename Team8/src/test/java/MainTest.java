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
		location = new Location(0,0);	
		
	}

	


}
