package warehouse;

import java.util.ArrayList;
import java.util.List;

public class Worker {

  /** name of worker. */
  private String name;
  /** position of worker. */
  private String position;
  /** list of scanned Skus. */
  private List<String> scannedSkuList;

  /**
   * Construct a new worker.
   * 
   * @param name the name of the worker
   * @param position the position of the worker
   */
  public Worker(String name, String position) {
    this.name = name;
    this.position = position;
    this.scannedSkuList = new ArrayList<>();
  }

  /**
   * Scan a sku and store it in scannedSkuList.
   * 
   * @param sku the sku of a fascia
   */
  public void scan(String sku) {
    scannedSkuList.add(sku);
  }

  /**
   * Get the name.
   * 
   * @return name of the worker
   */
  public String getName() {
    return name;
  }

  /**
   * Get the position.
   * 
   * @return position of the worker
   */
  public String getPosition() {
    return position;
  }

  /**
   * Get the scannedSkuList.
   * 
   * @return list of scanned skus
   */
  public List<String> getScannedSkuList() {
    return scannedSkuList;
  }
}
