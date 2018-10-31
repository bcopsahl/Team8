import org.junit.*;
import static org.junit.jupiter.api.Assertions.*;
import main.*;
public class LocationTest {
	static Location loc;
	@Before
	public void setup(){
		loc = new Location(0,0);	
		
	}

	@Test
	public void updateLocation() {
		loc.setLocation(1,1);
		assertEquals(new Location(1,1),loc);
	}
	@Test
	public void checkEquals() {
		assertAll(".equals Checks",
		() -> assertTrue(loc.equals(loc)),
		()-> {
			Location Test = new Location(1,1);
			assertFalse(Test.equals(loc));
			}
		);
	}
	@Test
	public void getParams() {
		assertAll("getting X and Y",
		() -> assertEquals(0,loc.getX()),
		() -> assertEquals(0,loc.getY())
		);
	}

	@Test
	public void initializations() {
		assertAll("Make all sure the init methods behave",
		() -> assertEquals(loc,new Location()),
		() -> assertEquals(new Location(), new Location(0,0))
		);
	}

	@Test
	public void aroundTest(){
		Location a = new Location();
		assertEquals(loc.around(),a.around());
	}

}
