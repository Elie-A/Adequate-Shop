package utilities.baseAPI;

import utilities.configFileReader.ConfigurationFileReader;

import java.util.Properties;

public class BaseAPI {
    public static ConfigurationFileReader configurationFileReader = new ConfigurationFileReader();
    public static Properties properties = configurationFileReader.getPropertiesValues("config.properties");
    public static Properties requestParametersProperties = configurationFileReader.getPropertiesValues("requestParams.properties");

    public static String baseURL = "";
    public static String testDataPath = "";
    public static String jsonSchemaPath = "";
    public static String srcTestResources = "";

    public BaseAPI(){
        testDataPath = properties.getProperty("test_data");
        jsonSchemaPath = properties.getProperty("json_schema");
        srcTestResources = properties.getProperty("src_test_resources");

        baseURL = properties.getProperty("base_url");
    }

}
