package converter;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class MyFileWriter {

  public void writeJsonToFile(String json, String fileName) throws FileNotFoundException {
    PrintWriter pw = new PrintWriter(fileName);
    pw.write(json);
    pw.flush();
    pw.close();
  }
}
