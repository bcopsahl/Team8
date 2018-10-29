import org.junit.*;
import static org.junit.Assert.*;
import main.*;
public class CellTest {
	static Cell testCell;

	@Test
	public void Example() {
		System.out.println("Example test runs");
	}

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
		System.out.println("test we can kill a cell");
	}

}