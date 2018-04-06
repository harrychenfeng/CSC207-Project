package warehouse;

import java.util.ArrayList;
import java.util.List;

public class Pallet {

  /** List of fascia. */
  private List<Fascia> fascias;

  /**
   * Create a new pallet.
   */
  public Pallet() {
    fascias = new ArrayList<>();
  }

  /**
   * Add four fascias into a pallet.
   * 
   * @param f1 first fascia of the pallet
   * @param f2 second fascia of the pallet
   * @param f3 third fascia of the pallet
   * @param f4 fourth fascia of the pallet
   */
  public void palletConfirmed(Fascia f1, Fascia f2, Fascia f3, Fascia f4) {
    fascias.add(f1);
    fascias.add(f2);
    fascias.add(f3);
    fascias.add(f4);
  }

  /**
   * Get the list of fascias of this pallet.
   * 
   * @return List<@Fascia@> the list of fascias of this pallet
   */
  public List<Fascia> getPallet() {
    return fascias;
  }
}
