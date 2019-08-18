package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;


public class SQLiteDatabase {

  // Singleton ref
  private static SQLiteDatabase ref;

  // The database string
  private String url = "jdbc:sqlite:assignment3.sqlite";

  // Connection object
  private Connection conn = null;

  /**
   * Create new instance of database
   */
  private SQLiteDatabase() {
    try {
      Class.forName("org.sqlite.JDBC");
    } catch (ClassNotFoundException e) {
      System.err.println(e.getMessage());
    }
  }

  /**
   * Return instance of database
   *
   * @return Singleton reference of database
   */
  public static SQLiteDatabase getInstance() {
    if (ref == null) {
      return new SQLiteDatabase();
    } else {
      return ref;
    }
  }


  /**
   * Returns a string representation of the database
   *
   * @return String of all records in the database
   */
  public String toString() {
    return getAllHousesString();
  }

  /**
   * Connect to database
   *
   * @return true iff connection is succesful
   */
  private boolean connect() {
    boolean result = false;

    try {
      // Connnect to database or create if not found
      this.conn = DriverManager.getConnection(this.url);

      // Output that connection was successful
      result = true;
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }

    return result;
  }

  /**
   * Close connection
   *
   * @return true iff connection is closed successfully
   */
  private boolean closeConnection() {
    boolean result = false;

    try {
      // Close connection
      this.conn.close();

      result = true;
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }

    return result;
  }


  /**
   * Create a table named tableName, if a table with that name already exists replace it
   *
   * @return true iff table creation is successful
   */
  public boolean createHouseListingTable() {
    boolean result = false;

    Statement stmt = null;

    try {
      connect();
      stmt = this.conn.createStatement();

      String sql = "CREATE TABLE HOUSELISTING "
          + "(LISTINGTITLE TEXT PRIMARY KEY, ADDRESS TEXT, DESCRIPTION TEXT, LINK TEXT, PRICE "
          + "TEXT)";

      stmt.executeUpdate(sql);

      stmt.close();

      closeConnection();

      result = true;
    } catch (SQLException e) {
      if (e.getMessage().equals("table HOUSELISTING already exists")) {
        deleteHouseListingTable();
        result = createHouseListingTable();
      } else {
        System.err.println("1" + e.getMessage());
      }
    }

    return result;
  }

  /**
   * Delete table tableName from database
   *
   * @return true iff table is deleted
   */
  public boolean deleteHouseListingTable() {
    boolean result = false;

    Statement stmt = null;
    try {
      connect();
      stmt = this.conn.createStatement();

      String sql = "DROP TABLE HOUSELISTING";

      stmt.executeUpdate(sql);
      stmt.close();
      closeConnection();

      result = true;
    } catch (SQLException e) {
      System.err.println("2" + e.getMessage());
    }

    return result;
  }

  /**
   * Insert a new housing record into a table, if record already exists in database replace it
   *
   * @param listingTitle the listing title
   * @param address the address of house
   * @param description the description of the house
   * @param link the link to listing
   * @param price the price of the house
   * @return true iff successfully inserted house listing into database
   * @throws IllegalHouseRecordException if listing title or address is empty string
   */
  public boolean insertHouse(String listingTitle, String address, String description, String link,
      String price) throws IllegalHouseRecordException {

    if (listingTitle.equals("") || address.equals("") || description.equals("") || link
        .equals("") || price.equals("")) {
      throw new IllegalHouseRecordException(
          "Params cannot be empty");
    }

    boolean result = false;
    PreparedStatement stmt = null;

    try {
      connect();
      this.conn.setAutoCommit(false);

      String sql =
          "INSERT OR REPLACE INTO HOUSELISTING (LISTINGTITLE, ADDRESS, DESCRIPTION, LINK, PRICE) " +
              "VALUES (?, ?, ?, ?, ?)";

      stmt = this.conn.prepareStatement(sql);

      stmt.setString(1, listingTitle);
      stmt.setString(2, address);
      stmt.setString(3, description);
      stmt.setString(4, link);
      stmt.setString(5, price);

      stmt.executeUpdate();

      stmt.close();
      this.conn.commit();
      closeConnection();

      result = true;
    } catch (SQLException e) {
      System.err.println("3" + e.getMessage());
    }

    return result;
  }

  /**
   * Return a string of all the records in the database
   *
   * @return String of house listings
   */
  private String getAllHousesString() {
    Statement stmt = null;
    String result = "";

    try {
      connect();
      this.conn.setAutoCommit(false);

      stmt = this.conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM HOUSELISTING;");

      while (rs.next()) {
        String title = rs.getString("LISTINGTITLE");
        String address = rs.getString("ADDRESS");
        String description = rs.getString("DESCRIPTION");
        String link = rs.getString("LINK");
        String price = rs.getString("PRICE");

        result += "Listing Title: " + title + "\n";
        result += "Address: " + address + "\n";
        result += "Description: " + description + "\n";
        result += "Link: " + link + "\n";
        result += "Price: " + price + "\n\n";
      }

      rs.close();
      stmt.close();
      closeConnection();
    } catch (SQLException e) {
      System.err.println("4" + e.getMessage());
    }

    return result;
  }

  /**
   * Return a collection of all the house records in the database
   *
   * @return HouseCollection object with all records
   */
  public HouseCollection getAllHouses() {
    Statement stmt = null;
    HouseCollection collection = new HouseCollection();

    try {
      connect();
      this.conn.setAutoCommit(false);

      stmt = this.conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM HOUSELISTING;");

      while (rs.next()) {
        String title = rs.getString("LISTINGTITLE");
        String address = rs.getString("ADDRESS");
        String description = rs.getString("DESCRIPTION");
        String link = rs.getString("LINK");
        String price = rs.getString("PRICE");

        House house = new House(title, address, description, link, price);
        collection.insertHouse(house);
      }

      rs.close();
      stmt.close();
      closeConnection();
    } catch (SQLException e) {
      System.err.println("5" + e.getMessage());
    }

    return collection;
  }


}

