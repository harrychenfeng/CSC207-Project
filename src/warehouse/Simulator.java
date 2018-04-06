package warehouse;

import event.EventProcessor;
import event.FileHelper;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import javax.swing.*;

public class Simulator extends JFrame {

  /** serialVersionUID. */
  private static final long serialVersionUID = 1L;
  /** processor of this simulator. */
  private EventProcessor processor;
  /** mySystem of this simulator. */
  private MySystem mySystem;

  // GUI part
  private JLabel content;
  private JButton button1;
  private JButton button2;
  private JButton button3;
  private JPanel panel;


  /**
   * Construct a new simulator to read input files
   * 
   * @param setUpFile the translation.csv
   * @param traversalFile the traversal.csv
   * @param initialFile the initial.csv
   * @throws IOException throw the exceptions just in case
   */
  public Simulator(String setUpFile, String traversalFile, String initialFile) throws IOException {
    super("My Warehouse");
    processor = new EventProcessor();
    mySystem = new MySystem();
    this.initAll(setUpFile, traversalFile, initialFile);
    // GUI part
    setLayout(new FlowLayout());

    content = new JLabel("Welcome to Warehouse Manager");
    button1 = new JButton("final.csv");
    button2 = new JButton("order.csv");
    button3 = new JButton("Simulate");
    panel=new JPanel(new FlowLayout());

    button1.setPreferredSize(new Dimension(60, 20));
    button2.setPreferredSize(new Dimension(65, 20));
    button3.setPreferredSize(new Dimension(60, 20));
    
    button1.addActionListener(new FinalHanlder());
    button2.addActionListener(new OrderHanlder());
    button3.addActionListener(new SimulateHanlder());

    panel.add(content);
    panel.add(button1);
    panel.add(button2);
    panel.add(button3);
    add(panel);
  }

  /**
   * Get the system from the simulator for testing.
   * 
   * @return MySystem
   */
  public MySystem getSystem() {
    return mySystem;
  }

  /**
   * Set up the warehouse.
   * 
   * @param setUpFile the translation.csv
   * @param traversalFile the traversal_table.csv
   * @param initialFile the initial.csv
   * @throws IOException throw the exceptions just in case
   */
  public void initAll(String setUpFile, String traversalFile, String initialFile)
      throws IOException {
    List<String> setUpLines = FileHelper.readCsvFile(setUpFile);
    List<String> trasversalLines = FileHelper.readFile(traversalFile);
    List<String> initialLines = FileHelper.readFile(initialFile);
    Map<String, String> temp = new HashMap<>();

    for (String locations : trasversalLines) {
      String subLine = locations.substring(0, 7);
      String[] split = locations.split(",");
      temp.put(split[4], subLine);
      mySystem.getWareHouse().getSkuToLocation().put(split[4], subLine);
      mySystem.getWareHouse().getFloor().getPickFace().put(subLine, 30);// initial floor with 30
    }

    for (String fascia : setUpLines) {
      String[] split = fascia.split(",");

      String skuFront = split[2];
      String skuBack = split[3];

      Fascia fasciaFront = new Fascia(split[1], split[0], skuFront);
      Fascia fasciaBack = new Fascia(split[1], split[0], skuBack);

      mySystem.getWareHouse().getStorage().put(fasciaFront, temp.get(fasciaFront.getSku()));
      mySystem.getWareHouse().getStorage().put(fasciaBack, temp.get(fasciaBack.getSku()));

      mySystem.getWareHouse().getLocationToFascia().put(temp.get(fasciaFront.getSku()),
          fasciaFront);
      mySystem.getWareHouse().getLocationToFascia().put(temp.get(fasciaBack.getSku()), fasciaBack);

      Order newOrder = new Order(fasciaFront, fasciaBack);
      mySystem.getWareHouse().getOrderList().add(newOrder);

      mySystem.getWareHouse().getMap().put(split[1] + " " + split[0], split[2] + " " + split[3]);
    }

    for (String line : initialLines) {
      String subline = line.substring(0, 7);
      String[] value = line.split(",");
      int amount = Integer.valueOf(value[4]);
      mySystem.getWareHouse().getFloor().getPickFace().replace(subline, amount);
    }
  }


  /**
   * The main method for running this program.
   * 
   * @param args the parameter for a main method
   * @throws Exception throw the exceptions just in case
   */
  public static void main(String[] args) throws Exception {
    Simulator simulator = new Simulator("translation.csv", "traversal_table.csv", "initial.csv");
    // The GUI part
//    simulator.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("warehouse.png")))));
    simulator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    simulator.setVisible(true);
    simulator.setSize(450, 400);
  }

  private class FinalHanlder implements ActionListener {// write to final.csv

    @Override
    public void actionPerformed(ActionEvent e) {
        List<String> lines = new ArrayList<>();
        Set<Entry<String, Integer>> mySet =
            mySystem.getWareHouse().getFloor().getPickFace().entrySet();
        for (Entry<String, Integer> entry : mySet) {
          String store = entry.getKey() + "," + entry.getValue();
          lines.add(store);
        }
        try {
          FileHelper.writeFile("final.csv", lines);
        } catch (IOException e1) {
          e1.printStackTrace();
        }
    }
  }
  
  private class OrderHanlder implements ActionListener{// write to orders.csv

    @Override
    public void actionPerformed(ActionEvent e) {
      List<String> lines = new ArrayList<>();
      for (Order order : mySystem.getTruck().getListSequencedOrders()) {
        String store = "Order" + "," + order.getFrontFascia().getModel() + ","
            + order.getFrontFascia().getColour();
        lines.add(store);
      }
      try {
        FileHelper.writeFile("orders.csv", lines);
      } catch (IOException e1) {
        e1.printStackTrace();
      }      
    }
  }
  
  private class SimulateHanlder implements ActionListener {// simulate myorders.txt

    @Override
    public void actionPerformed(ActionEvent e) {
      try {
        for (String line : FileHelper.readFile("myorders.txt")) {
          mySystem.getLogger().log(Level.INFO, "Line from event file: " + line);
          processor.process(line, mySystem);
        }
      } catch (IOException e1) {
        e1.printStackTrace();
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    }
  }

}
