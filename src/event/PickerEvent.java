package event;

import java.util.List;
import java.util.Map;

import warehouse.MySystem;
import warehouse.WareHousePicking;

public class PickerEvent implements Event {

  /**
   * the implemented methods of process event when command asks for picking event.
   */
  @Override
  public void processEvent(String command, MySystem system) throws Exception {
    String[] pickCommand = command.split(" ");
    String picker = pickCommand[1];
    String skunum = pickCommand[3];
    List<String> updatelist = system.updateLocations();
    Map<Integer, String> optimized = WareHousePicking.optimize(updatelist);
    system.pickFascia(picker, skunum, optimized, updatelist);
  }
}
