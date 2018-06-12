package converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

public class Converter {

  private static final Logger logger = Logger.getLogger(Converter.class);

  private String json = "";
  private MyFileReader myFileReader = new MyFileReader();
  private MyFileWriter jsonFileWriter = new MyFileWriter();
  private ObjectMapper objectMapper = new ObjectMapper();

  public void startConvert(String fileXml, String fileJson,
      Map<String, String> addValueMap, List<String> removeValueKey) throws IOException {
    String readXmlFile;
    try {
      readXmlFile = myFileReader.readFile(fileXml);
      logger.info("Reading xml file " + readXmlFile);
    } catch (IOException e) {
      logger.error(e);
      throw new IOException();
    }

    /** Convert XML to Json*/
    try {
      json = convert(readXmlFile);
      logger.info("The converted file in JSON format" + json);
    } catch (IOException e) {
      logger.error(e);
      throw new IOException();
    }

    /** Modify Json */
    String modifiedJson = modifyJson(json, addValueMap, removeValueKey);
    logger.info("Modified JSONObject " + modifiedJson);

    /** Write Json to file with name JSONFile.json
     * @throw FileNotFoundException
     * */
    try {
      jsonFileWriter.writeJsonToFile(modifiedJson, fileJson);
      logger.info("JSONObject Write to file");
    } catch (FileNotFoundException e) {
      logger.error(e);
      throw new FileNotFoundException();
    }
  }


  public String convert(String xmlFile) throws IOException {
    XmlMapper xmlMapper = new XmlMapper();
    try {
      JsonNode node = xmlMapper.readTree(xmlFile.getBytes());
      json = objectMapper.writeValueAsString(node);
    } catch (IOException e) {
      logger.error(e);
      throw new IOException(e);
    }
    return json;
  }

  public String modifyJson(String json, Map<String, String> addValueMap,
      List<String> removeValueKey) throws IOException {
    Map<String, Object> jsonMap;
    try {
      jsonMap = objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {
      });
    } catch (IOException e) {
      logger.error(e);
      throw new IOException(e);
    }
    for (Map.Entry<String, String> e : addValueMap.entrySet()) {
      jsonMap.put(e.getKey(), e.getValue());
      logger.info("To Json add " + e.getKey() + ":" + e.getValue());
    }
    for (String key : removeValueKey) {
      jsonMap.remove(key);
      logger.info("From Json removed " + key);
    }
    return objectMapper.writeValueAsString(jsonMap);
  }

}
