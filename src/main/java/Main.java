import converter.Converter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

  public static void main(String[] args) throws IOException {

    String fileXml = "User.xml";
    String fileJson = "JSONFile.json";
    Map<String, String> addValueMap = new HashMap<>();
    addValueMap.put("city", "Odessa");
    addValueMap.put("street", "new_street");
    List<String> removeValueKey = new ArrayList<>();
    removeValueKey.add("password");
    Converter converter = new Converter();
    try {
      converter.startConvert(fileXml, fileJson, addValueMap, removeValueKey);
    } catch (IOException e) {
      throw new IOException();
    }
  }
}