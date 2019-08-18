import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import database.House;


public class HouseTest {

  private House house;

  @Before
  public void setup() {
    house = new House("House1", "Address1", "Description1", "Link1", "$100.00");
  }

  /**
   * Tests the toString method of House class
   */
  @Test
  public void testToString() {
    String expected = "Listing Title: House1\n";
    expected += "Address: Address1\n";
    expected += "Description: Description1\n";
    expected += "Link: Link1\n";
    expected += "Price: $100.00\n\n";

    assertEquals(expected, house.toString());
  }

  /**
   * Tests compareTo method when comparing two houses that are not equal
   */
  @Test
  public void testCompareToNotEqual() {
    House house2 = new House("House3", "a", "a", "a", "$200.00");

    assertEquals(-2, house.compareTo(house2));
  }

  /**
   * Tests compareTo method when comparing two equal houses
   */
  @Test
  public void testCompareToEqual() {
    House house2 = new House("House1", "Address1", "Description1", "Link1", "$100.00");

    assertEquals(0, house.compareTo(house2));
  }

}
