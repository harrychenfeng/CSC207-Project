package event;

public class EventNotFoundException extends Exception {

  /** My Serializable id. */
  private static final long serialVersionUID = 1L;

  /**
   * Constructor created our own exception when the command does not fit any event.
   * 
   * @param message the command line
   */
  public EventNotFoundException(String message) {
    super(message);
  }
}
