package warehouse;

import java.util.HashMap;
import java.util.Map;


public class Floor {

  /** Map of location as the key, amount as the value. */
  private Map<String, Integer> pickFace;

  /**
   * Construct a floor with its pickFace.
   * 
   */
  public Floor() {
    pickFace = new HashMap<>();
  }

  /**
   * Get this floor's pickFace.
   * 
   * @return Map<@String, Integer@> the pick face of this floor
   */
  public Map<String, Integer> getPickFace() {
    return pickFace;
  }
}
