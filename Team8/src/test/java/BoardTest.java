package main;
import static org.junit.jupiter.api.Assertions.*;
import  org.junit.jupiter.api.*;
import java.io.PrintStream;
import java.lang.String;
import java.io.ByteArrayOutputStream;
import main.*;
public class BoardTest{
	static Board board;
	static Location location;
	@BeforeEach
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
		board.save("/tmp/Board.ser");
		board = new Board();
		board.populate(false);
		board = board.load("/tmp/Board.ser");
		assertEquals(3,board.detectNearby(location));
	}
    @Test
    public void print(){

        board = new Board(0,0);
        //this is adopted from a stack overflow answer, its confusing. 
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(b);
        board.setStdOut(out);
        board.print();
        //ByteArrayInputStream in = new ByteArrayInputStream(b.toByteArray());
        //this is lazy I admit it but it is hard to test 
        assertEquals(" -\n\n",new String(b.toByteArray()));
    }
}
