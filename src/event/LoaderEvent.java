package event;

import warehouse.MySystem;

public class LoaderEvent implements Event {

  /**
   * the implemented methods of process event when command asks for load event.
   */
  @Override
  public void processEvent(String command, MySystem system) throws Exception {
    String[] loadCommand = command.split(" ");
    String loader = loadCommand[1];
    String scannedNumber = loadCommand[3];
    String scanStatus = loadCommand[2];
    system.loading(loader, scannedNumber, scanStatus);
  }
}
