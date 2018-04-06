package warehouse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MySystem {

  /** Deque of orders waiting to been picked. */
  private Deque<Order> bufferOrders;
  /** List of request for loading event. */
  private List<Request> requestList;
  /** List of fascias waiting to be replenished. */
  private List<Fascia> replenishList;
  /** Warehouse for the system. */
  private WareHouse wareHouse;
  /** Truck for the system. */
  private Truck truck;
  /** List of orders that had been sequenced. */
  private List<Order> savedlist;
  /** List of scanned skus by sequencer. */
  private List<String> scannedSkuList;
  /** Number of picked fascia. */
  private int picked;
  /** The list of ready replenisher. */
  private List<Worker> replenisherList;
  /** The list of loaded sku. */
  private List<String> loadedList;

  /** Logger for recording events happened. */
  private final Logger log;
  /** The file which contains all the logs. */
  private static final String LOGGER_FILENAME = "log.txt";

  /**
   * Construct a new warehouse system.
   * 
   */
  public MySystem() {
    bufferOrders = new LinkedList<>();
    requestList = new ArrayList<>();
    replenishList = new ArrayList<>();
    truck = new Truck();
    savedlist = new ArrayList<>();
    wareHouse = new WareHouse();
    scannedSkuList = new ArrayList<>();
    picked = 0;
    replenisherList = new ArrayList<>();
    loadedList = new ArrayList<>();
    log = Logger.getLogger(MySystem.class.getName());
    try {
      log.setUseParentHandlers(false);
      log.setLevel(Level.ALL);
      Handler consoleHandler = new ConsoleHandler();
      Handler fileHandler = new FileHandler(LOGGER_FILENAME);
      fileHandler.setFormatter(new SimpleFormatter());
      log.addHandler(consoleHandler);
      log.addHandler(fileHandler);
    } catch (SecurityException | IOException exception) {
      exception.printStackTrace();
    }
  }

  /**
   * Get the list of buffer orders.
   * 
   * @return Deque<@Order@> list of buffer orders
   */
  public Deque<Order> getBufferOrders() {
    return bufferOrders;
  }

  /**
   * Get the list of requestList.
   * 
   * @return List<@Request@> list of requests.
   */
  public List<Request> getRequestList() {
    return requestList;
  }

  /**
   * Get the list of fascias which need to be replenished.
   * 
   * @return List<@Fascia@> list of fascias
   */
  public List<Fascia> getReplenishList() {
    return replenishList;
  }

  /**
   * Get the picked number.
   * 
   * @return int the picked number
   */
  public int getPicked() {
    return picked;
  }

  /**
   * Set the picked number.
   * 
   * @param picked the picked number
   */
  public void setPicked(int picked) {
    this.picked = picked;
  }

  /**
   * Get the list of ready replenishers.
   * 
   * @return List<@Worker@> list of ready replenishers
   */
  public List<Worker> getReplenisherList() {
    return replenisherList;
  }

  /**
   * Set the replenisherlist of the system.
   * 
   * @param replenisherList a new list of replenishers
   */
  public void setReplenisherList(List<Worker> replenisherList) {
    this.replenisherList = replenisherList;
  }

  /**
   * Get the warehouse of the system.
   * 
   * @return WareHouse the warehouse of the system
   */
  public WareHouse getWareHouse() {
    return wareHouse;
  }

  /**
   * Get the truck of the system.
   * 
   * @return Truck the truck of system
   */
  public Truck getTruck() {
    return truck;
  }

  /**
   * Get the logger.
   * 
   * @return Logger the log of the system
   */
  public Logger getLogger() {
    return log;
  }

  /**
   * Get the list of loadedList.
   * 
   * @return List<@String@> list of sku numbers that loaded
   */
  public List<String> getLoadedList() {
    return loadedList;
  }


  /**
   * Get the scanned sku list.
   * 
   * @return List<@String@> the scannedskuList
   */
  public List<String> getScannedSkuList() {
    return scannedSkuList;
  }

  /**
   * Set the savedlist.
   * 
   * @param savedlist a new list of saved order
   */
  public void setSavedlist(List<Order> savedlist) {
    this.savedlist = savedlist;
  }

  /**
   * Create the order of specific model and colour specified by description.
   * 
   * @param description the input description
   * @return Order the order which specified by event file
   */
  public Order createOrder(String description) {
    String[] temp = description.split(" ");
    String[] list = wareHouse.getMap().get(description).split(" ");
    Fascia f1 = new Fascia(temp[0], temp[1], list[0]);
    Fascia f2 = new Fascia(temp[0], temp[1], list[1]);
    Order order = new Order(f1, f2);
    return order;
  }

  /**
   * Receive the order from the OrderEvent.
   * 
   * @param oneOrder the order received from the OrderEvent
   */
  public void receiveOrder(Order oneOrder) {
    log.log(Level.INFO, "Barcode reader: Order " + oneOrder.getFrontFascia().getModel() + " "
        + oneOrder.getFrontFascia().getColour() + " received");
    bufferOrders.add(oneOrder);
    if (bufferOrders.size() == 4) {
      Order o1 = bufferOrders.removeFirst();
      Order o2 = bufferOrders.removeFirst();
      Order o3 = bufferOrders.removeFirst();
      Order o4 = bufferOrders.removeFirst();
      Request request = new Request();
      request.formRequest(o1, o2, o3, o4);
      requestList.add(request);
      log.log(Level.INFO, "Barcode: 4 Orders are received !");
    }
  }

  /**
   * Set a picker to the first order in bufferOrders if there is an order in bufferOrders .
   * 
   * @param string the name of the picker who is ready for the order
   */
  public void setOrderPicker(String string) {
    if (bufferOrders.size() != 0) {
      Order order = bufferOrders.getFirst();
      order.setPicker(string);
    }
  }

  /**
   * Set a picker to the first order of first request in requestList if there is a request in
   * requestList.
   * 
   * @param string the name of the picker who is ready for the request
   */
  public void setRequestPicker(String string) {
    if (requestList.size() != 0) {
      Order order = requestList.get(0).getOrders().get(0);
      order.setPicker(string);
    }
  }

  /**
   * Distinguishing the type of ready workers and giving corresponding feedback.
   * 
   * @param worker the string represents the type of the worker
   * @param name the name of the worker
   */
  public void readyDistinguishing(String worker, String name) {
    switch (worker) {
      case "Picker": {
        log.log(Level.INFO, "Barcode reader: " + name + ", please go picking orders");
        this.setOrderPicker(name);
      } ;
        break;
      case "Sequencer": {
        log.log(Level.INFO, "Barcode reader: " + name + ", please go sequencing orders");
      } ;
        break;
      case "Loader": {
        log.log(Level.INFO, "Barcode reader: " + name + ", please go loading this request");
      } ;
        break;
      case "Replenisher": {
        Worker replenisher = new Worker(name, "Replenisher");
        replenisherList.add(replenisher);
        log.log(Level.INFO, "Barcode reader: " + name + ", please go replenishing");
      } ;
        break;
      default: {
        log.log(Level.INFO, "Barcode reader: this type of worker does not exit");
      }
    }
  }

  /**
   * Update the locations before start picking.
   * 
   * @return List<@String@> list of updated locations
   */
  public List<String> updateLocations() {
    List<String> skus = new ArrayList<>();
    if (requestList.size() != 0) {
      for (Order order : requestList.get(0).getOrders()) {
        skus.add(order.getFrontFascia().getSku());
        skus.add(order.getBackFascia().getSku());
      }
    }
    return skus;
  }

  /**
   * Pick this fascia specified by its sku.
   * 
   * @param picker the name of the picker
   * @param sku the sku of the fascia
   * @param optimized the optimized locations
   * @param updatelocations the locations for fascias
   * 
   */
  public void pickFascia(String picker, String sku, Map<Integer, String> optimized,
      List<String> updatelocations) {
    String location = wareHouse.getSkuToLocation().get(sku);
    int amount = wareHouse.getFloor().getPickFace().get(location);
    Fascia fascia = wareHouse.getLocationToFascia().get(location);
    this.setRequestPicker(picker);
    amount -= 1;
    wareHouse.getFloor().getPickFace().replace(location, amount);
    picked += 1;
    if (picked == 9) {
      picked = 1;
    }
    String correct = this.getWareHouse().getLocationToFascia().get(optimized.get(picked)).getSku();
    if (amount <= 5) {
      replenishList.add(fascia);
    }
    if (updatelocations.size() != 0) {
      log.log(Level.INFO, "Barcode reader: " + picker + ", please pick " + correct);
    }
    log.log(Level.INFO, picker + ": picked fascia sku #" + sku);
    if (!sku.equals(correct)) {
      log.log(Level.INFO, "Barcode reader: you picked the wrong fascia, please pick #" + correct);
    }

  }

  /**
   * Marshaling the order by the picker.
   * 
   * @param picker the name of the picker
   */
  public void marshaling(String picker) {
    log.log(Level.INFO, "Barcode reader: " + picker + ", please go to marshalling area");
    Request request = requestList.get(0);
    if (request.getOrders().get(0).getPicker().equals(picker)) {
      request.setPickready();
    }
    log.log(Level.INFO, picker + ": done marshalling");
  }

  /**
   * Sequencing every fascia in the request list.
   * 
   * @return List<@Order@> list of orders that have been sequenced
   */
  public List<Order> sequencing() {
    Request request = requestList.get(0);
    if (request.isPickready()) {
      Order o1 = request.getOrders().get(0);
      Order o2 = request.getOrders().get(1);
      Order o3 = request.getOrders().get(2);
      Order o4 = request.getOrders().get(3);
      savedlist.add(o1);
      savedlist.add(o2);
      savedlist.add(o3);
      savedlist.add(o4);
      truck.setListSequencedOrders(savedlist);
      Pallet front = new Pallet();
      front.palletConfirmed(o1.getFrontFascia(), o2.getFrontFascia(), o3.getFrontFascia(),
          o4.getFrontFascia());
      Pallet back = new Pallet();
      back.palletConfirmed(o1.getBackFascia(), o2.getBackFascia(), o3.getBackFascia(),
          o4.getBackFascia());
      truck.addFrontPallet(front);
      truck.addBackPallet(back);
      requestList.remove(0);
    }
    return savedlist;
  }


  /**
   * Sequencing the order if the inspectedResult is true otherwise tell the picker to repick the
   * order.
   * 
   * @param inspectedResult the result of visually inspecting
   */
  public void visuallyInspecting(String inspectedResult) {
    if (inspectedResult.equals("false")) {
      log.log(Level.INFO, "Barcode reader: picker please repick the orders");
    } else {
      List<Order> list = this.sequencing();
      this.getTruck().setListSequencedOrders(list);
    }
  }

  /**
   * Save all skus for the orders to a list.
   * 
   * @return List<@String@> list of String to represent the sku
   */
  public List<String> getSavedList() {
    List<String> saved = new ArrayList<>();
    for (int i = 0; i < savedlist.size(); i++) {
      saved.add(savedlist.get(i).getFrontFascia().getSku());
      saved.add(savedlist.get(i).getBackFascia().getSku());
    }
    return saved;
  }

  /**
   * Checking if the scanned is in the same order with original. the size of scanned is smaller or
   * equall to the size of original
   * 
   * @param scanned the list of scanned number
   * @param original the list of number that in the correct order
   * @return boolean
   */
  public boolean scanChecking(List<String> scanned, List<String> original) {
    for (int i = 0; i < scanned.size(); i++) {
      if (!scanned.get(i).equals(original.get(i))) {
        return false;
      }
    }
    return true;
  }

  /**
   * Storing the scanned sku by sequencer and comparing to the correspond request if the
   * corresponding request does not match the scanned sku, tell sequencer to rescan.
   * 
   * @param scannedNum the sku of the fascia scanned by sequencer
   * @param sequencer the name of the sequencer
   * @param scanStatus the status for scanning (scan or rescan)
   */
  public void sequencerScanning(String scannedNum, String sequencer, String scanStatus) {
    scannedSkuList.add(scannedNum);
    List<String> saved = this.getSavedList();
    log.log(Level.INFO, "Barcode reader: " + sequencer + ", please " + scanStatus + " #"
        + saved.get(scannedSkuList.size() - 1));
    log.log(Level.INFO, sequencer + ": " + scanStatus + "ned fascia sku #" + scannedNum);
    if (!this.scanChecking(scannedSkuList, saved)) {
      log.log(Level.INFO, sequencer + ": please rescan this set of orders");
      int size = scannedSkuList.size();
      for (int i = 0; i < (size % 8); i++) {
        scannedSkuList.remove(scannedSkuList.size() - 1);
      }
    } else {
      if (scannedSkuList.size() % 8 == 0) {
        log.log(Level.INFO, sequencer + ": done sequencing");
        log.log(Level.INFO,
            "Barcode reader: " + sequencer + ", please sequence the next set of orders");
      }
    }
  }

  /**
   * Checking if the orders are in correct order and Loading the fascias that have been sequenced
   * already.
   * 
   * @param loader the name of the loader
   * @param loadedNumber the sku of the fascia loaded by loader
   * @param scanStatus the status for scanning (scan or rescan)
   */
  public void loading(String loader, String loadedNumber, String scanStatus) {
    loadedList.add(loadedNumber);
    List<String> saved = this.getSavedList();
    log.log(Level.INFO, "Barcode reader: " + loader + ", please " + scanStatus + " #"
        + saved.get(loadedList.size() - 1));
    log.log(Level.INFO, loader + ": " + scanStatus + "ned fascia sku #" + loadedNumber);
    if (!this.scanChecking(loadedList, saved)) {
      log.log(Level.INFO, "Barcode reader: please rescan this set of orders.");
      int size = loadedList.size();
      for (int i = 0; i < (size % 8); i++) {
        loadedList.remove(loadedList.size() - 1);
      }
    } else {
      if (loadedList.size() % 8 == 0) {
        int size = loadedList.size() / 8;
        truck.getListRequestId().add(size);
        log.log(Level.INFO,
            "Barcode reader: orders are in correct order, please load into the truck.");
        log.log(Level.INFO, loader + ": the picking request has been loaded.");
      }
    }
    if (truck.getListRequestId().size() == 10) {
      truck.getListRequestId().clear();
      log.log(Level.INFO, "The truck is full!");
      log.log(Level.INFO, "The truck leaves.");
    }
  }

  /**
   * Set the listRequestID every time a new request confirmed.
   */
  public void setListRequestIdOfTruck() {
    for (int count = 1; count < requestList.size() + 1; count++) {
      truck.getListRequestId().add(count);
    }
  }

  /**
   * Replenishing the fascia whose amount is five or less.
   * 
   * @param replenisher the name of replenisher
   * @param location the location of fascia which need to be replenished
   */
  public void replenishing(String replenisher, String location) {
    if (replenishList.size() != 0) {
      log.log(Level.INFO, "Barcode reader: " + replenisher + " please replenish " + location);
      wareHouse.getFloor().getPickFace().replace(location, 30);
      replenishList.remove(0);
      log.log(Level.INFO, replenisher + ": replenished " + location);
    }
  }
}
