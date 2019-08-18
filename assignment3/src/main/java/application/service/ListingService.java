package application.service;

import application.dao.Listing;
import database.House;
import database.SQLiteDatabase;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.util.ArrayList;

@Service
public class ListingService {

  private ArrayList<Listing> list = new ArrayList<>();
  private SQLiteDatabase db = SQLiteDatabase.getInstance();

  public ArrayList<Listing> getAllListings() {
    for (House e : db.getAllHouses()) {
      String address = e.getAddress().replaceAll(",", "").replaceAll("\\s", ",+");
      HttpResponse<JsonNode> response = Unirest
          .get("https://maps.googleapis.com/maps/api/geocode/json?address={address}&key={key}")
          .routeParam("address", address)
          .routeParam("key", "AIzaSyAFNwBzIYLGe-3658gf2q20HY36gzDzAfc").asJson();

      Double lat = response.getBody().getObject().getJSONArray("results").getJSONObject(0)
          .getJSONObject("geometry").getJSONObject("location").getDouble("lat");
      Double lng = response.getBody().getObject().getJSONArray("results").getJSONObject(0)
          .getJSONObject("geometry").getJSONObject("location").getDouble("lng");
//
//            System.out.println(lat);
//            System.out.println(lng);

      list.add(
          new Listing(e.getTitle(), e.getAddress(), e.getDescription(), e.getPrice(), e.getLink(),
              lat, lng));
    }

    return list;
  }
}
