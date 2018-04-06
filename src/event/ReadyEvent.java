package event;

import warehouse.MySystem;

public class ReadyEvent implements Event {

  /**
   * the implemented methods of process event when command asks for ready to pick event.
   */
  @Override
  public void processEvent(String command, MySystem system) throws Exception {
    String[] readyCommand = command.split(" ");
    String worker = readyCommand[1];
    String name = readyCommand[2];
    system.readyDistinguishing(worker, name);
  }
}
