package converter;

import java.io.BufferedReader;
import java.io.IOException;

public class MyFileReader {

  public String readFile(String file) throws IOException {
    BufferedReader reader = new BufferedReader(new java.io.FileReader(file));
    String line;
    StringBuilder stringBuilder = new StringBuilder();
    String ls = System.getProperty("line.separator");

    try {
      while ((line = reader.readLine()) != null) {
        stringBuilder.append(line);
        stringBuilder.append(ls);
      }

      return stringBuilder.toString();
    } finally {
      reader.close();
    }
  }

}
