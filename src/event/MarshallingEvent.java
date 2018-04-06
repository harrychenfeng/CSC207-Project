package event;

import warehouse.MySystem;

public class MarshallingEvent implements Event {

  /**
   * the implemented methods of process event when command asks for marshalling event.
   * 
   */
  @Override
  public void processEvent(String command, MySystem system) throws Exception {
    String[] marshalCommand = command.split(" ");
    String marshal = marshalCommand[2];
    system.marshaling(marshal);
  }
}
