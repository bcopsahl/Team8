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
		testMain.populateWith(true);
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
	public void aliveOrDead() {
		assertAll(" alive or dead ", 
			() -> assertFalse(testMain.aliveOrDead(loc)),
			() -> {
				testMain.populateWith(true);
				assertTrue(testMain.aliveOrDead(loc));
			}
		);
	}

	@Test 
	public void evolvingLiveFields(){
		testMain.populateWith(true);
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
