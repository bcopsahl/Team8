import org.junit.*;
import static org.junit.jupiter.api.Assertions.*;
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
		testMain.populate(true);
		assertAll("Populate All alive",
			() -> assertEquals(3,testMain.detectNearby(loc)),
			() -> {
				loc.setLocation(1,1);
				assertEquals(8,testMain.detectNearby(loc));
				}
		);
	}
	@Test
	public void populateDead() {
		testMain.populate(false);
		assertEquals(0,testMain.detectNearby(loc));

	}
	@Test
	public void evolveDead() {
		testMain.populate(false);
		testMain.evolve();
		assertEquals(0,testMain.detectNearby(loc));
	}


	@Test 
	public void aliveOrDead() {
		assertAll(" alive or dead ", 
			() -> assertFalse(testMain.aliveOrDead(loc)),
			() -> {
				testMain.populate(true);
				assertTrue(testMain.aliveOrDead(loc));
			}
		);
	}

	@Test 
	public void evolvingLiveFields(){
		testMain.populate(true);
		testMain.evolve();
		assertAll("evolving live non toiroidal fields",
			() -> assertTrue(testMain.aliveOrDead(loc)),
			() -> {
				testMain.evolve();
				assertFalse(testMain.aliveOrDead(loc));
			}
		);
	}



}
