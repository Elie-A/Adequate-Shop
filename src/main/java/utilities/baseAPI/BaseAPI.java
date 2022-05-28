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

    public BaseAPI(){
        testDataPath = properties.getProperty("test_date");
        jsonSchemaPath = properties.getProperty("json_schema");

        baseURL = properties.getProperty("base_url");
    }

}
