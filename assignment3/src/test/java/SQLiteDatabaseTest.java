import static org.junit.Assert.*;

import database.HouseCollection;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import database.IllegalHouseRecordException;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import database.SQLiteDatabase;

public class SQLiteDatabaseTest {

  private SQLiteDatabase db;

  @Before
  public void setup() {
    db = SQLiteDatabase.getInstance();
  }

  /**
   * Tests connecting to SQLite database
   */
  @Test
  public void testConnect()
      throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

    Method m = db.getClass().getDeclaredMethod("connect", new Class<?>[0]);
    m.setAccessible(true);
    boolean output = (boolean) m.invoke(db);
    assertTrue(output);

  }

  /**
   * Tests creating a new house listing table
   */
  @Test
  public void testCreateNewTable() {
    assertTrue(db.createHouseListingTable());
  }

  /**
   * Tests creating a house listing table when it already exists in the database
   */
  @Test
  public void testCreateSameTable() {
    assertTrue(db.createHouseListingTable());
  }

  /**
   * Tests deleting the house listing table
   */
  @Test
  public void testDeletingTable() throws IllegalHouseRecordException {
    db.createHouseListingTable();
    assertTrue(db.deleteHouseListingTable());
  }

  /**
   * Tests inserting a record into empty database
   */
  @Test
  public void testInsertFirst() throws IllegalHouseRecordException {
    db.createHouseListingTable();

    assertTrue(db.insertHouse("House1", "Main St", "Body1", "Link1", "$500.00"));

  }

  /**
   * Tests toString method for a single house in database
   */
  @Test
  public void testToStringSingleHouse() throws IllegalHouseRecordException {

    String expected = "Listing Title: House1\n";
    expected += "Address: Main St\n";
    expected += "Description: Body1\n";
    expected += "Link: Link1\n";
    expected += "Price: $500.00\n\n";

    db.createHouseListingTable();

    db.insertHouse("House1", "Main St", "Body1", "Link1", "$500.00");

    assertEquals(expected, db.toString());
  }

  /**
   * Tests getCollection method for a single house in database
   */
  @Test
  public void testGetCollectionSingleHouse() throws IllegalHouseRecordException {
    HouseCollection expetedCollection = mock(HouseCollection.class);
    String expectedStr = "Listing Title: House1\n";
    expectedStr += "Address: Main St\n";
    expectedStr += "Description: Body1\n";
    expectedStr += "Link: Link1\n";
    expectedStr += "Price: $500.00\n\n";
    when(expetedCollection.toString()).thenReturn(expectedStr);

    db.createHouseListingTable();

    db.insertHouse("House1", "Main St", "Body1", "Link1", "$500.00");
    assertEquals(expetedCollection.toString(), db.getAllHouses().toString());

  }

  /**
   * Tests inserting multiple houses into database
   */
  @Test
  public void testInsertMultiple() throws IllegalHouseRecordException {
    db.createHouseListingTable();

    db.insertHouse("House1", "Main St", "Body1", "Link1", "$500.00");
    assertTrue(db.insertHouse("House2", "Second St", "Body2", "Link2", "$600.00"));
  }

  /**
   * Tests toString method for multiple houses in database
   */
  @Test
  public void testToStringMultipleHouse() throws IllegalHouseRecordException {
    String expected = "Listing Title: House1\n";
    expected += "Address: Main St\n";
    expected += "Description: Body1\n";
    expected += "Link: Link1\n";
    expected += "Price: $500.00\n\n";

    expected += "Listing Title: House2\n";
    expected += "Address: Second St\n";
    expected += "Description: Body2\n";
    expected += "Link: Link2\n";
    expected += "Price: $600.00\n\n";

    db.createHouseListingTable();

    db.insertHouse("House1", "Main St", "Body1", "Link1", "$500.00");
    db.insertHouse("House2", "Second St", "Body2", "Link2", "$600.00");
    assertEquals(expected, db.toString());

  }

  /**
   * Tests getCollection method for multiple houses in database
   */
  @Test
  public void testGetCollectionMultipleHouse() throws IllegalHouseRecordException {
    HouseCollection expetedCollection = mock(HouseCollection.class);
    String expectedStr = "Listing Title: House1\n";
    expectedStr += "Address: Main St\n";
    expectedStr += "Description: Body1\n";
    expectedStr += "Link: Link1\n";
    expectedStr += "Price: $500.00\n\n";

    expectedStr += "Listing Title: House2\n";
    expectedStr += "Address: Second St\n";
    expectedStr += "Description: Body2\n";
    expectedStr += "Link: Link2\n";
    expectedStr += "Price: $600.00\n\n";
    when(expetedCollection.toString()).thenReturn(expectedStr);

    db.createHouseListingTable();

    db.insertHouse("House1", "Main St", "Body1", "Link1", "$500.00");
    db.insertHouse("House2", "Second St", "Body2", "Link2", "$600.00");
    assertEquals(expetedCollection.toString(), db.getAllHouses().toString());

  }

  /**
   * Tests inserting a house with empty fields
   */
  @Test
  public void testInsertEmptyField() {
    db.createHouseListingTable();

    try {
      db.insertHouse("", "", "", "", "");
    } catch (IllegalHouseRecordException e) {
      assertTrue(true);
    }
  }

  /**
   * Tests replcaing an existing house record with updated information
   */
  @Test
  public void testReplaceExistingHouse() throws IllegalHouseRecordException {
    String expected = "Listing Title: House1\n";
    expected += "Address: NotMain St\n";
    expected += "Description: Body2\n";
    expected += "Link: Link2\n";
    expected += "Price: $600.00\n\n";

    db.createHouseListingTable();

    db.insertHouse("House1", "Main St", "Body1", "Link1", "$500.00");
    db.insertHouse("House1", "NotMain St", "Body2", "Link2", "$600.00");

    assertEquals(expected, db.toString());

  }

}
