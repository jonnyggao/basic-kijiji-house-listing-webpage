package database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class HouseCollection implements Iterable<House> {

  // Collection of houses
  private ArrayList<House> houses;

  /**
   * Create new House collection instance
   */
  public HouseCollection() {
    houses = new ArrayList<>();
  }

  /**
   * String representation of collection of houses
   *
   * @return String representation of houses
   */
  public String toString() {
    String result = "";

    // Concat each string rep in collection
    for (House h : this.houses) {
      result += h.toString();
    }

    return result;
  }

  /**
   * Insert a house into the collection
   *
   * @param h house object to be inserted
   */
  public void insertHouse(House h) {
    // Insert into list
    houses.add(h);
    // Sort
    Collections.sort(this.houses);
  }

  /**
   * Deletes the house with the given title. If more than one house has same title in collection,
   * deletes the first one. Does nothing if no such house has given title
   *
   * @param title the title of the house listing to delete
   */
  public void deleteHouse(String title) {
    int i = contains(title);

    if (i > -1) {
      houses.remove(i);
    }
  }

  /**
   * Checks if there is a house in the collection with the given title and returns the index of it,
   * returns -1 if no such house
   *
   * @param title the title of house listing to search for
   * @return index of house or -1 if house not in collection
   */
  public int contains(String title) {
    int result = -1;
    int i = 0;

    // Iterate through the houses and find the index where the title matches
    while (i < this.houses.size() && !this.houses.get(i).getTitle().equals(title)) {
      i++;
    }

    // If final counter is out of bounds then return -1 else just return the index
    if (i < this.houses.size()) {
      result = i;
    }

    return result;
  }

  @Override
  /**
   * Return iterator for house collection
   */
  public Iterator<House> iterator() {
    return new HouseCollectionIterator();
  }

  public class HouseCollectionIterator implements Iterator<House> {

    int i;

    public HouseCollectionIterator() {
      i = 0;
    }

    @Override
    public boolean hasNext() {
      return (i < houses.size());
    }

    @Override
    public House next() {
      House next = houses.get(i);

      i++;

      return next;
    }
  }

}
