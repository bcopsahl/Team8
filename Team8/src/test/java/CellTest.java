import org.junit.*;
import static org.junit.jupiter.api.Assertions.*;
import main.*;
public class CellTest {
	static Cell testCell;


	@Before
	public  void setup(){
		testCell = new Cell(true);
	}

	@Test 
	public void CellisAlive() {
		assertTrue(testCell.getAlive());
		System.out.println("test that cells are initialized right");
	}

	@Test
	public void killCell() {
		testCell.setAlive(false);
		assertFalse(testCell.getAlive());
	}

	@Test
	public void reviveCell(){
		testCell.setAlive(false);
		testCell.setAlive(true);
		assertTrue(testCell.getAlive());
	}

	@Test 
	public void checkEquality() {
		// I think this is pretty much useless now but migh be better later 
		assertTrue(testCell.equals(testCell));
		assertFalse(testCell.equals(new Cell(false)));

	}

}
