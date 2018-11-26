package main;
import static org.junit.jupiter.api.Assertions.*;
import  org.junit.jupiter.api.*;
import  org.junit.jupiter.params.ParameterizedTest;
import  org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import main.*;
import java.util.List;
import java.util.ArrayList;
public class LocationTest {
	static Location loc;
	@BeforeEach
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
    @ParameterizedTest(name = "{index} Location= ''{0}''")
    @MethodSource
    public void Equals(Location l){
            assertFalse(loc.equals(l));
    }

    public static Stream<Location> Equals(){
        List<Location> l = new ArrayList<Location>();
        Location loc;
        for(int x = -20; x <= 20; x++){
            for(int y = -20; y <= 20; y++){
                l.add(new Location(x,y));
            }
        }
        return l.stream().filter(h -> !(h.getX()==0) && !(h.getY()==0));
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
