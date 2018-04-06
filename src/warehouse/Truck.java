package warehouse;

import event.FileHelper;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;


public class Truck {

  /** List of request IDs. */
  private List<Integer> listRequestId;
  /** List of sequenced orders. */
  private List<Order> listSequencedOrders;
  /** List of front pallet. */
  private List<Pallet> listFrontPallet;
  /** List of back pallet. */
  private List<Pallet> listBackPallet;

  /**
   * Create a new truck.
   */
  public Truck() {
    listRequestId = new ArrayList<>();
    listSequencedOrders = new ArrayList<>();
    listFrontPallet = new ArrayList<>();
    listBackPallet = new ArrayList<>();
  }

  /**
   * get the front pallet list.
   * 
   * @return List<@Pallet@>
   */
  public List<Pallet> getListFrontPallet() {
    return listFrontPallet;
  }

  /**
   * get the back pallet list.
   * 
   * @return List<@Pallet@>
   */
  public List<Pallet> getListBackPallet() {
    return listBackPallet;
  }


  /**
   * Show how many left for each fascias in the pick face of this warehouse
   * 
   * @param fileName the file name of target .csv file to show left fascias.
   * @param sequencedOrders the list of orders that have been sequenced.
   * @throws IOException throw the exceptions just in case
   */
  public void showSequencedOrders(String fileName) throws IOException {
    List<String> lines = new ArrayList<>();
    for (Order order : listSequencedOrders) {
      String store = "Order" + "," + order.getFrontFascia().getModel() + ","
          + order.getFrontFascia().getColour();
      lines.add(store);
    }
    FileHelper.writeFile(fileName, lines);
  }

  /**
   * Add a new front pallet in to the truck.
   * 
   * @param front the new front pallet to the truck
   */
  public void addFrontPallet(Pallet front) {
    listFrontPallet.add(front);
  }

  /**
   * Add a new back pallet in to the truck.
   * 
   * @param back the new back pallet to the truck
   */
  public void addBackPallet(Pallet back) {
    listBackPallet.add(back);
  }

  /**
   * Get the listRequestID.
   * 
   * @return List<@Integer@> the list of request id
   */
  public List<Integer> getListRequestId() {
    return listRequestId;
  }

  /**
   * Get the listSequencedOrders.
   * 
   * @return List<@Order@> the list of sequenced orders
   */
  public List<Order> getListSequencedOrders() {
    return listSequencedOrders;
  }

  /**
   * Set the listSequencedOrders.
   * 
   * @param listSequencedOrders the list of sequenced orders
   */
  public void setListSequencedOrders(List<Order> listSequencedOrders) {
    this.listSequencedOrders = listSequencedOrders;
  }
}
