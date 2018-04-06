package warehouse;

public class Fascia {

  /** Model of this fascia. */
  private String model;
  /** Colour of this fascia. */
  private String colour;
  /** SKU of this fascia. */
  private String sku;


  /**
   * Create a new fascia.
   * 
   * @param model the model of this fascia
   * @param colour the colour of this fascia
   * @param sku the sku number of this fascia
   */
  public Fascia(String model, String colour, String sku) {
    this.model = model;
    this.colour = colour;
    this.sku = sku;
  }

  /**
   * Get this fascia's model.
   * 
   * @return String the model of the fascia
   */
  public String getModel() {
    return model;
  }

  /**
   * Get this fascia's colour.
   * 
   * @return String the colour of the fascia
   */
  public String getColour() {
    return colour;
  }

  /**
   * Get this fascia's Sku.
   * 
   * @return String the Sku of the fascia
   */
  public String getSku() {
    return sku;
  }

}
