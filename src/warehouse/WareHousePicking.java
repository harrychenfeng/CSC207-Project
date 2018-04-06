package warehouse;

import event.FileHelper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WareHousePicking {

  /**
   * Based on the String SKUs in List 'skus', return a List of locations, where each location is a
   * String containing 5 pieces of information: the zone character (in the range ['A'..'B']), the
   * aisle number (an integer in the range [0..1]), the rack number (an integer in the range
   * ([0..2]), and the level on the rack (an integer in the range [0..3]), and the SKU number.
   * 
   * @param updateList the list of SKUs to retrieve.
   * @return Map<@Integer,String@> the Map of locations, Integer means the order to be picked which
   *         starts from 1.
   * @throws IOException throw the exceptions just in case
   */
  public static Map<Integer, String> optimize(List<String> updateList) throws IOException {
    List<String> locationList = FileHelper.readFile("traversal_table.csv");
    Map<Integer, String> optimizeMap = new HashMap<>();
    List<Integer> num = new ArrayList<>();
    for (int i = 1; i < 9; i++) {
      num.add(i);
    }
    for (String location : locationList) {
      String[] temp = location.split(",");
      if (updateList.contains(temp[4]) && num.size() != 0) {
        optimizeMap.put(num.get(0), location.substring(0, 7));
        num.remove(0);
      }
    }
    return optimizeMap;
  }
}
