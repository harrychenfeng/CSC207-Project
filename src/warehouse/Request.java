package warehouse;

import java.util.ArrayList;
import java.util.List;

public class Request {

  /** list of orders. */
  private List<Order> orders;
  /** The picker assigned to this request. */
  private String picker;
  /** The ready status of this request. */
  private boolean pickready;

  /**
   * Create a new request with default picker and ready status.
   */
  public Request() {
    orders = new ArrayList<>();
    picker = null;
    pickready = false;
  }

  /**
   * Get the list of orders.
   * 
   * @return List<@Order@> the list of orders
   */
  public List<Order> getOrders() {
    return orders;
  }


  /**
   * Get the picker of this request.
   * 
   * @return String the name of picker
   */
  public String getPicker() {
    return picker;
  }

  /**
   * Set the picker of this request.
   * 
   */
  public void setPicker(String picker) {
    this.picker = picker;
  }

  /**
   * Form this request with four orders.
   * 
   */
  public void formRequest(Order o1, Order o2, Order o3, Order o4) {
    orders.add(o1);
    orders.add(o2);
    orders.add(o3);
    orders.add(o4);
  }

  /**
   * Check if there are 4 orders in this request.
   * 
   * @return boolean whether this request has 4 orders
   */
  public boolean requestConfirmed() {
    return (orders.size() == 4);
  }

  /**
   * Check whether this request is ready for picking.
   * 
   * @return boolean whether this request is for picking
   */
  public boolean isPickready() {
    return pickready;
  }

  /**
   * Set the status of the request to be ready.
   * 
   */
  public void setPickready() {
    this.pickready = true;
  }
}
