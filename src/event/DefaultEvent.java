package event;

import warehouse.MySystem;

public class DefaultEvent implements Event {

  /**
   * the implemented methods of process event when the Hearder does not fit any event.
   */
  @Override
  public void processEvent(String command, MySystem system) throws Exception {
    EventNotFoundException exp = new EventNotFoundException("Event Not Found");
    throw exp;

  }
}
