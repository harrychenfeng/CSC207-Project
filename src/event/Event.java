package event;

import warehouse.MySystem;

public interface Event {

  /**
   * Process specific event.
   * 
   * @throws Exception throw the exceptions just in case
   */
  public void processEvent(String command, MySystem system) throws Exception;
}
