package event;

import warehouse.MySystem;

public class ReplenisherEvent implements Event {

  /**
   * the implemented methods of process event when command asks for replenishing event.
   */
  @Override
  public void processEvent(String command, MySystem system) throws Exception {
    String[] replenishCommand = command.split(" ");
    String replenisher = replenishCommand[1];
    String location = replenishCommand[3] + "," + replenishCommand[4] + "," + replenishCommand[5]
        + "," + replenishCommand[6];
    if (system.getReplenisherList().size() != 0) {
      system.replenishing(replenisher, location);
      system.getReplenisherList().remove(0);
    }
  }
}
