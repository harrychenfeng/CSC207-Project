package event;

import warehouse.MySystem;
import warehouse.Order;

public class OrderEvent implements Event {

  /**
   * the implemented methods of process event when command asks for order event.
   */
  @Override
  public void processEvent(String command, MySystem system) throws Exception {
    String[] orderCommand = command.split(" ");
    String model = orderCommand[1];
    String color = orderCommand[2];
    Order order = system.createOrder(model + " " + color);
    system.receiveOrder(order);
  }
}

