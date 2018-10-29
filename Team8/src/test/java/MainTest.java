import org.junit.*;
import static org.junit.Assert.*;
import main.*;
public class MainTest {
	static Main testMain;

	@Before
	public void setup(){
		testMain = new Main(3);
	}

	@Test
	public void populateAlive() {
		testMain.populateWith(true);
		assertEquals(8,testMain.detectNearby(4));
		assertEquals(5,testMain.detectNearby(1));
	}
	@Test
	public void populateDead() {
		testMain.populateWith(false);
		assertEquals(0,testMain.detectNearby(4));
		assertEquals(0,testMain.detectNearby(0));

	}
	@Test
	public void evolveDead() {
		testMain.populateWith(false);
		testMain.evolve();
		assertEquals(0,testMain.detectNearby(4));
		assertEquals(0,testMain.detectNearby(0));
	}







}
