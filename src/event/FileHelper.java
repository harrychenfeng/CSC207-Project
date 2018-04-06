package event;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {

  /**
   * Read the file that first line is counted in.
   * 
   * @param file1 the input file that need to be read
   * @return List<@String@>
   * @throws IOException throw the exceptions just in case
   */
  public static List<String> readFile(String file1) throws IOException {
    List<String> lines = new ArrayList<>();
    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file1)));
    String line = reader.readLine();
    while (line != null) {
      lines.add(line);
      line = reader.readLine();
    }
    reader.close();
    return lines;
  }

  /**
   * Read the file that first line is not counted in.
   * 
   * @param file2 the input file that need to be read, such as Translation.csv
   * @return List<@String@>
   * @throws IOException throw the exceptions just in case
   */
  public static List<String> readCsvFile(String file2) throws IOException {
    List<String> lines = new ArrayList<>();
    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file2)));
    reader.readLine();// ignore the first line of translation.csv
    String line = reader.readLine();
    while (line != null) {
      lines.add(line);
      line = reader.readLine();
    }
    reader.close();
    return lines;
  }

  /**
   * Write a new .csv file that shows the updated traversal_table.csv.
   * 
   * @param fileName the input file that need to be wrote
   * @param lines the list of lines that need to be wrote in the file
   * @throws IOException throw the exceptions just in case
   */
  public static void writeFile(String fileName, List<String> lines) throws IOException {
    PrintWriter writer = new PrintWriter(fileName);
    for (String line : lines) {
      writer.println(line);
    }
    writer.close();
  }
}
