package database;


public class House implements Comparable<House> {

  private String title;
  private String address;
  private String description;
  private String link;
  private String price;

  /**
   * Create new instance of House
   *
   * @param title this instance's title
   * @param address this instance's address
   * @param description this instance's description
   * @param link this instance's link
   * @param price this instance's price
   */
  public House(String title, String address, String description, String link, String price) {
    this.title = title;
    this.address = address;
    this.description = description;
    this.link = link;
    this.price = price;
  }

  /**
   * Return string representatoin of this house
   *
   * @return String representation
   */
  public String toString() {
    String result = "Listing Title: " + this.title + "\n";
    result += "Address: " + this.address + "\n";
    result += "Description: " + this.description + "\n";
    result += "Link: " + this.link + "\n";
    result += "Price: " + this.price + "\n\n";

    return result;
  }

  public String getTitle() {
    return this.title;
  }

  public String getAddress() {
    return this.address;
  }

  public String getDescription() {
    return this.description;
  }

  public String getLink() {
    return this.link;
  }

  public String getPrice() {
    return this.price;
  }

  @Override
  /**
   * Compare this house to given house
   * @param o the house object to compare to
   */
  public int compareTo(House o) {
    return this.title.compareTo(o.getTitle());
  }
}
