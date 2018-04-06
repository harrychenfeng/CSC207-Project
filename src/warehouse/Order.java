package warehouse;

public class Order {

  /** Front fascia of this order. */
  private Fascia frontFascia;
  /** Back fascia of this order. */
  private Fascia backFascia;
  /** The name of assigned picker. */
  private String picker;
  /** The status of the order. */
  private boolean pickready;

  /**
   * Create a new order.
   * 
   * @param f1 the front fascia of this order
   * @param f2 the back fascia of this order
   */
  public Order(Fascia f1, Fascia f2) {
    if (pairConfirmed(f1, f2)) {
      frontFascia = f1;
      backFascia = f2;
    }
    picker = null;
    pickready = false;
  }

  /**
   * Get the order's picker.
   * 
   * @return String the name of the picker
   */
  public String getPicker() {
    return picker;
  }

  /**
   * Set this order's picker.
   * 
   * @param picker name of the picker
   */
  public void setPicker(String picker) {
    this.picker = picker;
  }

  /**
   * Get this order's front fascia.
   * 
   * @return Fascia the front fascia of this order
   */
  public Fascia getFrontFascia() {
    return frontFascia;
  }

  /**
   * Set this order's front fascia.
   * 
   * @param frontFascia the new front fascia
   */
  public void setFrontFascia(Fascia frontFascia) {
    this.frontFascia = frontFascia;
  }

  /**
   * Get this order's back fascia.
   * 
   * @return Fascia the back fascia of this order
   */
  public Fascia getBackFascia() {
    return backFascia;
  }

  /**
   * Set this order's back fascia.
   * 
   * @param backFascia the new back fascia
   */
  public void setBackFascia(Fascia backFascia) {
    this.backFascia = backFascia;
  }

  /**
   * Check whether the picker is ready.
   * 
   * @return boolean whether the order has been picked
   */
  public boolean isPickready() {
    return pickready;
  }

  /**
   * Set the picker to be ready.
   * 
   */
  public void setPickready() {
    this.pickready = true;
  }

  /**
   * Check if given fascias can make an order.
   * 
   * @param f1 the front fascia of the order
   * @param f2 the back fascia of the order
   * @return boolean whether they are a pair of fascias
   */
  private boolean pairConfirmed(Fascia f1, Fascia f2) {
    return ((f1.getColour() == f2.getColour()) && (f1.getModel() == f2.getModel())
        && !f1.getSku().equals(f2.getSku()));
  }
}

