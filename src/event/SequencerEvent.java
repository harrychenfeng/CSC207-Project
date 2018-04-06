package event;

import warehouse.MySystem;

public class SequencerEvent implements Event {

  /**
   * the implemented methods of process event when command asks for sequencing event.
   */
  @Override
  public void processEvent(String command, MySystem system) throws Exception {
    String[] sequenceCommand = command.split(" ");
    if (sequenceCommand.length == 5) {
      String result = sequenceCommand[4];
      system.visuallyInspecting(result);
    } else if (sequenceCommand.length == 4) {
      String scannedNumber = sequenceCommand[3];
      String sequencer = sequenceCommand[1];
      String scanStatus = sequenceCommand[2];
      system.sequencerScanning(scannedNumber, sequencer, scanStatus);
    }
  }
}
