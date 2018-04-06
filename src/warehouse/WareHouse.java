package warehouse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WareHouse {

  /** Map of fascia as the key, its location as the value. */
  private Map<Fascia, String> storage;
  /** List of potential orders. */
  private List<Order> orderList;
  /** Floor for this warehouse. */
  private Floor floor;
  /** Map of colour and model as the key, associated skus as the value. */
  private Map<String, String> map;
  /** Map of location as the key, the fascia as the value. */
  private Map<String, Fascia> locationToFascia;
  /** Map of sku as the key, the location as the value. */
  private Map<String, String> skuToLocation;

  /**
   * Construct a new warehouse.
   * 
   */
  public WareHouse() {
    storage = new HashMap<>();
    orderList = new ArrayList<>();
    floor = new Floor();
    map = new HashMap<>();
    locationToFascia = new HashMap<>();
    skuToLocation = new HashMap<>();
  }

  /**
   * Get the storage.
   * 
   * @return Map<@Fascia, String@> the storage map of this warehouse
   */
  public Map<Fascia, String> getStorage() {
    return storage;
  }

  /**
   * Get the skuToLocation.
   * 
   * @return Map<@Interger, String@> the skuToLocation map of this warehouse
   */
  public Map<String, String> getSkuToLocation() {
    return skuToLocation;
  }

  /**
   * Get the map.
   * 
   * @return Map<@String, String@> the map of this warehouse
   */
  public Map<String, String> getMap() {
    return map;
  }

  /**
   * Get the orderList.
   * 
   * @return List<@Order@> the orderList of this warehouse
   */
  public List<Order> getOrderList() {
    return orderList;
  }

  /**
   * Get the floor.
   * 
   * @return Floor the floor of this warehouse
   */
  public Floor getFloor() {
    return floor;
  }

  /**
   * Get the locationToFascia.
   * 
   * @return Map<@String, Fascia@> the locationToFascia map of this warehouse
   */
  public Map<String, Fascia> getLocationToFascia() {
    return locationToFascia;
  }
}
