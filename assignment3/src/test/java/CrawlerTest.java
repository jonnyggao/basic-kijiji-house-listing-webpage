import static org.junit.Assert.*;

import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import crawler.Assignment3WebCrawler;
import java.io.File;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;


public class CrawlerTest {

  private Assignment3WebCrawler crawler;

  @Before
  public void setup() {
    crawler = new Assignment3WebCrawler();
  }

  @Test
  public void testExtractAddress()
      throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException {
    String expected = "Address";

    File inputFile = new File(
        getClass().getClassLoader().getResource("testExtractAddress.html").getFile());
    Document doc = Jsoup.parse(inputFile, "UTF-8");

    Method extractAddress = crawler.getClass().getDeclaredMethod("extractAddress", Document.class);
    extractAddress.setAccessible(true);
    String actual = (String) extractAddress.invoke(crawler, doc);

    assertEquals(expected, actual);
  }

  @Test
  public void testExtractData()
      throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException, Exception {
//
//    Assignment3WebCrawler crawlerSpy = PowerMockito.spy(crawler);
//    PowerMockito.doReturn("Address: Address\n").when(crawlerSpy, "extractAddress", anyObject());
//
//
//
//    String expected = "Title: Title\n";
//    expected += "Address: Address\n";
//    expected += "Description: Description\n";
//    expected += "Link: Link\n";
//    expected += "Price: $1,000.00\n\n";
//
//    File inputFile = new File(
//        getClass().getClassLoader().getResource("testExtractData.html").getFile());
//    Document doc = Jsoup.parse(inputFile, "UTF-8");
//
//    Method extractData = crawler.getClass().getDeclaredMethod("extractData", Document.class);
//    extractData.setAccessible(true);
//    String actual = (String) extractData.invoke(crawler, doc);
//
//    assertEquals(expected, actual);
  }

}
