package application.dao;

public class Listing {

  private String title;
  private String address;
  private String description;
  private String price;
  private String link;
  private Double lat;
  private Double lng;

  public Listing(String title, String address, String description, String price, String link,
      Double lat, Double lng) {
    this.title = title;
    this.address = address;
    this.description = description;
    this.price = price;
    this.link = link;
    this.lat = lat;
    this.lng = lng;
  }

  public Listing() {

  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public Double getLat() {
    return lat;
  }

  public void setLat(Double lat) {
    this.lat = lat;
  }

  public Double getLng() {
    return lng;
  }

  public void setLng(Double lng) {
    this.lng = lng;
  }
}
