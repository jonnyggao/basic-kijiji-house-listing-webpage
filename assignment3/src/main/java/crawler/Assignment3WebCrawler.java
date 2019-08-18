package crawler;

import java.io.IOException;

import crawler.TooManyParamsException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import database.IllegalHouseRecordException;
import database.SQLiteDatabase;
import database.House;

public class Assignment3WebCrawler {

  private SQLiteDatabase db;

  public Assignment3WebCrawler() {
    this.db = SQLiteDatabase.getInstance();
    this.db.deleteHouseListingTable();
    this.db.createHouseListingTable();
  }

  /**
   * Extract the title, address, description, link and price from given element
   */
  private String extractData(Document doc, Element info) {
    // Get title description link and price of this listing
    Elements title = info.select("div.title");
    String titleStr = title.first().select("a[href]").first().ownText().trim();
//    System.out.println("TITLE: " + titleStr); // FOR DEBUGGING

    Elements description = info.select("div.description");
    String descriptionStr = description.first().ownText().trim();
//    System.out.println("DECRIPTION: " + descriptionStr); // FOR DEBUGGING

    Elements price = info.select("div.price");
    String priceStr = price.first().ownText().trim();
//    System.out.println("PRICE: " + priceStr); // FOR DEBUGGING

    Elements link = title.select("a[href]");
    String linkStr = "https://www.kijiji.ca" + link.first().attr("href");
//    System.out.println("LINK: " + linkStr); // FOR DEBUGGING

    // Get address of this listing by going into the link
    String addressStr = "";
    try {
      Document listingDoc = Jsoup.connect(linkStr).get();
      addressStr = extractAddress(listingDoc);
      try {
        this.db.insertHouse(titleStr, addressStr, descriptionStr, linkStr, priceStr);
      } catch (IllegalHouseRecordException e) {
        System.err.println(e.getMessage());
      }
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }

    String result = "Title: " + titleStr + "\n";
    result += "Address: " + addressStr + "\n";
    result += "Description: " + descriptionStr + "\n";
    result += "Link: " + linkStr + "\n";
    result += "Price: " + priceStr + "\n\n";

    System.out.println(result);
    return result;
  }

  private String extractAddress(Document listingDoc) {
    String result = "";

    Elements address = listingDoc.select("span[itemprop='address']");
    result = address.first().ownText().trim();
//      System.out.println("ADDRESS: " + result + "\n"); // FOR DEBUGGING

    return result;
  }

  /**
   * Crawl the kijiji website
   */
  public void crawl(String seed, int limit) {

    try {
      int count = 0;
      Document doc = Jsoup.connect(seed).get();

      // Select every house listing in this link
      Elements houseListingBox = doc.select("div.info");

      //Extract data from each elemetn
      for (Element e : houseListingBox) {
        extractData(doc, e);
        // Increment the total number of house listings extracted
        count++;
        if (count >= limit) {
          break;
        }
      }

      // If more listings need to be crawled
      // Get the next link with attr title=Next and crawl that page
      if (count < limit) {
        Elements nextButton = doc.select("a[title='Next']");
        String nextPage = "https://www.kijiji.ca" + nextButton.first().attr("href");
//        System.out.println("\n" + nextPage + "\n"); // FOR DEBUGGING
        crawl(nextPage, limit - count);
      }
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }


  /**
   *
   */
  public static void main(String[] args) throws TooManyParamsException {
    if (args.length > 2) {
      throw new TooManyParamsException("Too many params");
    }

    String seed = args[0];
    int limit = Integer.parseInt(args[1]);

    Assignment3WebCrawler crawler = new Assignment3WebCrawler();
    crawler.crawl(seed, limit);

  }
}
