package event;

import java.util.HashMap;
import java.util.Map;
import warehouse.MySystem;

public class EventProcessor {

  /** Map for all the commands. */
  private final Map<String, Event> eventMap;

  /**
   * Construct a eventProcessor.
   */
  public EventProcessor()  {
    eventMap= new HashMap<>();
  }

  /**
   * Dynamically new an event
   * 
   * @param command the command read from myorders.txt
   * @return Event
   * @throws EventNotFoundException throw the exceptions just in case
   */
  public Event createEvent(String command) throws EventNotFoundException {
    String[] sublines = command.split(" ");
    String eventName = sublines[0];
    String packageName = "event";
    try {
      Class<?> eventClass = Class.forName(packageName + "." + eventName + "Event");
      Event event = (Event) eventClass.newInstance();
      return event;
    } catch (Exception exception) {
      throw new EventNotFoundException("Cannot create event");
    }
  }

  /**
   * process each command when reading the myorders.txt
   * 
   * @param command each line read from myorders.txt
   * @param system the system that process all the commands
   * @throws Exception throw the exceptions just in case
   */
  public void process(String command, MySystem system) throws Exception {
    String sub = command.substring(0, command.indexOf(" "));
    if (eventMap.containsKey(sub)) {
      eventMap.get(sub).processEvent(command, system);
    } else {
      eventMap.put(sub, this.createEvent(command));
      eventMap.get(sub).processEvent(command, system);
    }
  }
}
