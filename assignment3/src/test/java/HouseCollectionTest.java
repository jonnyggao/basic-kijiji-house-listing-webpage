import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import database.HouseCollection;
import database.HouseCollection.HouseCollectionIterator;
import database.House;


public class HouseCollectionTest {

  private HouseCollection collection;

  @Before
  public void setup() {
    collection = new HouseCollection();
  }

  /**
   * Tests inserting a house to an empty collection
   */
  @Test
  public void testInsertFirst() {
    House h1 = new House("House1", "1", "1", "1", "1");

    collection.insertHouse(h1);

    String expected = "Listing Title: House1\n";
    expected += "Address: 1\n";
    expected += "Description: 1\n";
    expected += "Link: 1\n";
    expected += "Price: 1\n\n";

    assertEquals(expected, collection.toString());
  }

  /**
   * Tests inserting houses to collection that is not empty
   */
  @Test
  public void testInsertMultiple() {
    House h1 = new House("House1", "1", "1", "1", "1");
    House h2 = new House("House2", "1", "1", "1", "1");

    collection.insertHouse(h1);
    collection.insertHouse(h2);

    String expected = "Listing Title: House1\n";
    expected += "Address: 1\n";
    expected += "Description: 1\n";
    expected += "Link: 1\n";
    expected += "Price: 1\n\n";
    expected += "Listing Title: House2\n";
    expected += "Address: 1\n";
    expected += "Description: 1\n";
    expected += "Link: 1\n";
    expected += "Price: 1\n\n";

    assertEquals(expected, collection.toString());
  }

  /**
   * Tests the inserting to collection in unsorted order
   */
  @Test
  public void testInsertMultipleSort() {
    House h1 = new House("House1", "1", "1", "1", "1");
    House h2 = new House("House2", "1", "1", "1", "1");
    House h3 = new House("House3", "1", "1", "1", "1");

    collection.insertHouse(h1);
    collection.insertHouse(h3);
    collection.insertHouse(h2);

    String expected = "Listing Title: House1\n";
    expected += "Address: 1\n";
    expected += "Description: 1\n";
    expected += "Link: 1\n";
    expected += "Price: 1\n\n";
    expected += "Listing Title: House2\n";
    expected += "Address: 1\n";
    expected += "Description: 1\n";
    expected += "Link: 1\n";
    expected += "Price: 1\n\n";
    expected += "Listing Title: House3\n";
    expected += "Address: 1\n";
    expected += "Description: 1\n";
    expected += "Link: 1\n";
    expected += "Price: 1\n\n";

    assertEquals(expected, collection.toString());
  }

  /**
   * Tests contain method when house is not in collection
   */
  @Test
  public void testDoesNotContain() {
    int expected = -1;
    assertEquals(expected, collection.contains("House1"));
  }

  /**
   * Tests contain method when hous is in collection
   */
  @Test
  public void testContain() {
    House h1 = new House("House1", "1", "1", "1", "1");

    collection.insertHouse(h1);

    int expected = 0;
    assertEquals(expected, collection.contains("House1"));
  }

  /**
   * Tests deleting house in collection
   */
  @Test
  public void testDelete() {
    House h1 = new House("House1", "1", "1", "1", "1");

    collection.insertHouse(h1);
    collection.deleteHouse("House1");

    String expected = "";

    assertEquals(expected, collection.toString());
  }

  /**
   * Tests deleting a house not in collection
   */
  @Test
  public void testDeleteNotInCollection() {
    House h1 = new House("House1", "1", "1", "1", "1");

    collection.insertHouse(h1);

    collection.deleteHouse("House2");

    String expected = "Listing Title: House1\n";
    expected += "Address: 1\n";
    expected += "Description: 1\n";
    expected += "Link: 1\n";
    expected += "Price: 1\n\n";

    assertEquals(expected, collection.toString());
  }

  /**
   * Tests iterator of the house collection
   */
  @Test
  public void testIterator() {
    String h1 = "Listing Title: House1\n";
    h1 += "Address: 1\n";
    h1 += "Description: 1\n";
    h1 += "Link: 1\n";
    h1 += "Price: 1\n\n";
    String h2 = "Listing Title: House2\n";
    h2 += "Address: 1\n";
    h2 += "Description: 1\n";
    h2 += "Link: 1\n";
    h2 += "Price: 1\n\n";
    String h3 = "Listing Title: House3\n";
    h3 += "Address: 1\n";
    h3 += "Description: 1\n";
    h3 += "Link: 1\n";
    h3 += "Price: 1\n\n";

    HouseCollectionIterator iterator = mock(HouseCollectionIterator.class);
    House house1 = mock(House.class);
    House house2 = mock(House.class);
    House house3 = mock(House.class);

    when(iterator.next()).thenReturn(house1).thenReturn(house2).thenReturn(house3);

    when(house1.toString())
        .thenReturn("Listing Title: House1\nAddress: 1\nDescription: 1\nLink: 1\nPrice: 1\n\n");
    when(house2.toString())
        .thenReturn("Listing Title: House2\nAddress: 1\nDescription: 1\nLink: 1\nPrice: 1\n\n");
    when(house3.toString())
        .thenReturn("Listing Title: House3\nAddress: 1\nDescription: 1\nLink: 1\nPrice: 1\n\n");

    String actual =
        iterator.next().toString() + iterator.next().toString() + iterator.next().toString();

    assertEquals(h1 + h2 + h3, actual);
  }

}
