package utilities.dataParser;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import utilities.baseAPI.BaseAPI;

import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class DataParser {

    public Map<String, String> jsonToHashMap(String filePath) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JSONParser parser = new JSONParser();

        File file = getFileFromResource(BaseAPI.testDataPath, filePath);
        FileReader reader = new FileReader(file);

        Object jsonObject = parser.parse(reader);
        JSONObject jsonObj = (JSONObject) jsonObject;

        String jsonObjStr = jsonObj.toJSONString();
        Map<String, String> jsonData = mapper.readValue(jsonObjStr,
                Map.class);
        return jsonData;
    }

    public String hashMapToJson(HashMap<String, String> map) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(map);
            return json;
    }

    private File getFileFromResource(String absolutePath, String fileName) throws Exception {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(absolutePath + fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return new File(resource.toURI());
        }
    }
}
