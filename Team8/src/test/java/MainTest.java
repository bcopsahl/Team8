import org.junit.*;
import static org.junit.Assert.*;
import main.*;
public class MainTest {
	static Main testMain;
	static Location loc;
	@Before
	public void setup(){
		testMain = new Main(3,3);
		loc = new Location(0,0);	
		
	}

	@Test
	public void populateAlive() {
		testMain.populateWith(true);
		assertEquals(3,testMain.detectNearby(loc));
		loc.setLocation(1,1);
		assertEquals(8,testMain.detectNearby(loc));
	}
	@Test
	public void populateDead() {
		testMain.populateWith(false);
		assertEquals(0,testMain.detectNearby(loc));

	}
	@Test
	public void evolveDead() {
		testMain.populateWith(false);
		testMain.evolve();
		assertEquals(0,testMain.detectNearby(loc));
	}

	@Test
	public void locationHash() {
		assertTrue(loc.equals(loc));

	}

	@Test 
	public void aliveOrDead() {
		assertFalse(testMain.aliveOrDead(loc));
		testMain.populateWith(true);
		assertTrue(testMain.aliveOrDead(loc));
	}

	@Test 
	public void evolvingLiveFields(){
		testMain.populateWith(true);
		testMain.evolve();
		assertTrue(testMain.aliveOrDead(loc));
		testMain.evolve();
		assertFalse(testMain.aliveOrDead(loc));
	}


}
