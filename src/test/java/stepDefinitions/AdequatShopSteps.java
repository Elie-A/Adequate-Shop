package stepDefinitions;

import endpoints.Endpoints;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import utilities.MiscUtilities;
import utilities.baseAPI.BaseAPI;
import utilities.dataGenerator.DataGenerator;
import utilities.models.User;
import utilities.restAssuredUtilities.RestAssuredUtilities;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdequatShopSteps extends TestBase {


    public static RestAssuredUtilities restAssuredUtilities;
    private static ResponseOptions<Response> response;
    private static String token = "";
    private static List<User> usersList = new ArrayList<>();

    String jsonBody = "";
    HashMap<String, String> body = new HashMap<>();
    Map<String, String> requestMap = new HashMap<>();


    @Given("user is authenticated using valid credentials from {string}")
    public void userIsAuthenticatedUsingValidCredentialsFrom(String fileName) throws Exception{
        restAssuredUtilities = new RestAssuredUtilities();
        Map<String, String> map = new HashMap<>();

        if (token == null || token.length() <= 0) {
            map = dataParser.jsonToHashMap(fileName);
        }

        restAssuredUtilities.setRequestContentType(ContentType.JSON);
        restAssuredUtilities.setRequestBaseUri(BaseAPI.baseURL);
        restAssuredUtilities.addMapBodyToRequest(map);
    }

    @Then("I should get an authentication token {string} via url {string}")
    public void i_should_get_an_authentication_token_via_url(String tokenKey, String uri) {
        response = restAssuredUtilities.executeAPI(Endpoints.POST, uri);
        boolean validStatusCode = String.valueOf(response.getStatusCode()).matches("2\\d{2}");
        Assert.assertTrue("Invalid Status code " + response.getStatusCode(), validStatusCode);
        token = response.getBody().jsonPath().get(tokenKey);
    }

    @Then("response matches {string} schema")
    public void response_matches_schema(String schema) {
        String responseBody = response.getBody().asString().trim();
        boolean validStatusCode = String.valueOf(response.getStatusCode()).matches("2\\d{2}");
        Assert.assertTrue("Invalid Status code " + response.getStatusCode(), validStatusCode);
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(BaseAPI.jsonSchemaPath + schema);
        MatcherAssert.assertThat(responseBody, JsonSchemaValidator.matchesJsonSchema(inputStream));
    }

    @Given("user registers using valid entries")
    public void user_registers_using_valid_entries() throws Exception {
        restAssuredUtilities = new RestAssuredUtilities();
        body = DataGenerator.generateRegistrationData(8, 8, 8);
        jsonBody = dataParser.hashMapToJson(body);
        restAssuredUtilities.setRequestContentType(ContentType.JSON);
        restAssuredUtilities.setRequestBaseUri(BaseAPI.baseURL);
        restAssuredUtilities.addMapBodyToRequest(body);
    }

    @Then("I should get registered via url {string}")
    public void i_should_get_registered_via_url(String uri) {
        response = restAssuredUtilities.executeAPI(Endpoints.POST, uri);
        boolean validStatusCode = String.valueOf(response.getStatusCode()).matches("2\\d{2}");
        Assert.assertTrue("Invalid Status code " + response.getStatusCode(), validStatusCode);
    }

    @And("I save the response in {string}")
    public void iSaveTheResponseIn(String fileName) throws Exception {
        File file = new File(System.getProperty("user.dir") + File.separator + BaseAPI.srcTestResources + BaseAPI.testDataPath + fileName);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(jsonBody);
        fileWriter.flush();
        fileWriter.close();
    }

    @Given("I invoke {string} operation of {string} with query parameters {string} and values {string}")
    public void iInvokeOperationOfWithQueryParametersAndValues(String method, String uri, String parameters, String values) {
        restAssuredUtilities = new RestAssuredUtilities();
        restAssuredUtilities.setRequestContentType(ContentType.JSON);
        restAssuredUtilities.setRequestBaseUri(BaseAPI.baseURL);
        restAssuredUtilities.addAuthorizationHeader(token);

        String paramsSplit[] = BaseAPI.requestParametersProperties.getProperty(parameters).split(", ");
        String valuesSplit[] = BaseAPI.requestParametersProperties.getProperty(values).split(", ");

        for(int i = 0; i <= paramsSplit.length - 1; i++){
            requestMap.put(paramsSplit[i], valuesSplit[i]);
        }

        restAssuredUtilities.addQueryParametersToRequest(requestMap);
        response = restAssuredUtilities.executeAPI(method, uri);
    }

    @Given("I invoke {string} operation of {string} with valid entries")
    public void iInvokeOperationOfWithValidEntries(String method, String uri) {
        restAssuredUtilities = new RestAssuredUtilities();
        restAssuredUtilities.setRequestContentType(ContentType.JSON);
        restAssuredUtilities.setRequestBaseUri(BaseAPI.baseURL);
        restAssuredUtilities.addAuthorizationHeader(token);

        body = DataGenerator.generateUserCreationData(8, 8);
        restAssuredUtilities.addMapBodyToRequest(body);
        response = restAssuredUtilities.executeAPI(method, uri);
    }

    @Given("I invoke {string} operation of {string} with path parameters {string} and values {string}")
    public void iInvokeOperationOfWithPathParametersAndValues(String method, String uri, String parameters, String values) {
        String newUriFinal = "";
        restAssuredUtilities = new RestAssuredUtilities();
        restAssuredUtilities.setRequestContentType(ContentType.JSON);
        restAssuredUtilities.setRequestBaseUri(BaseAPI.baseURL);
        restAssuredUtilities.addAuthorizationHeader(token);

        String paramsSplit[] = BaseAPI.requestParametersProperties.getProperty(parameters).split(", ");
        String valuesSplit[] = BaseAPI.requestParametersProperties.getProperty(values).split(", ");

        for(int i = 0; i <= paramsSplit.length - 1; i++){
            requestMap.put(paramsSplit[i], valuesSplit[i]);
        }
        restAssuredUtilities.addPathParametersToRequest(requestMap);

        String pathParamInURL = "";
        for(String pathParam : paramsSplit){
            pathParamInURL = uri.replaceFirst("paramToReplace","{" + pathParam + "}");
            uri = pathParamInURL;
        }

        newUriFinal = uri;

        response = restAssuredUtilities.executeAPI(method, newUriFinal);
    }

    @Then("I save all users")
    public void iSaveAllUsers() {
        usersList = MiscUtilities.usersJsonToList(response);
    }

    @Given("I invoke {string} operation of {string}")
    public void iInvokeOperationOf(String method, String uri) {
        String newUriFinal = "";
        restAssuredUtilities = new RestAssuredUtilities();
        restAssuredUtilities.setRequestContentType(ContentType.JSON);
        restAssuredUtilities.setRequestBaseUri(BaseAPI.baseURL);
        restAssuredUtilities.addAuthorizationHeader(token);

        String pathParamInURL = "";
        pathParamInURL = uri.replaceFirst("paramToReplace","{" + "id" + "}");
        uri = pathParamInURL;

        newUriFinal = uri;

        int randomNumber = (int) ((Math.random() * (usersList.size())) + 0);
        User user = usersList.get(randomNumber);
        requestMap.put("id", String.valueOf(user.getId()));
        restAssuredUtilities.addPathParametersToRequest(requestMap);

        response = restAssuredUtilities.executeAPI(method, newUriFinal);
    }

    @Given("I invoke {string} operation of {string} with path parameters {string} with body")
    public void iInvokeOperationOfWithPathParametersWithBody(String method, String uri, String parameters) {
        String newUriFinal = "";
        restAssuredUtilities = new RestAssuredUtilities();
        restAssuredUtilities.setRequestContentType(ContentType.JSON);
        restAssuredUtilities.setRequestBaseUri(BaseAPI.baseURL);
        restAssuredUtilities.addAuthorizationHeader(token);

        int randomNumber = (int) ((Math.random() * (usersList.size())) + 0);
        User user = usersList.get(randomNumber);

        String paramsSplit[] = BaseAPI.requestParametersProperties.getProperty(parameters).split(", ");

        for(int i = 0; i <= paramsSplit.length - 1; i++){
            requestMap.put(paramsSplit[i], String.valueOf(user.getId()));
        }
        restAssuredUtilities.addPathParametersToRequest(requestMap);

        String pathParamInURL = "";
        for(String pathParam : paramsSplit){
            pathParamInURL = uri.replaceFirst("paramToReplace","{" + pathParam + "}");
            uri = pathParamInURL;
        }

        newUriFinal = uri;

        String jsonString = MiscUtilities.modelToJsonString(user);
        restAssuredUtilities.addJsonBodyToRequest(jsonString);

        response = restAssuredUtilities.executeAPI(method, newUriFinal);
    }

    @Given("I invoke {string} operation of {string} with path parameters {string}")
    public void iInvokeOperationOfWithPathParameters(String method, String uri, String parameters) {
        String newUriFinal = "";
        restAssuredUtilities = new RestAssuredUtilities();
        restAssuredUtilities.setRequestContentType(ContentType.JSON);
        restAssuredUtilities.setRequestBaseUri(BaseAPI.baseURL);
        restAssuredUtilities.addAuthorizationHeader(token);

        int randomNumber = (int) ((Math.random() * (usersList.size())) + 0);
        User user = usersList.get(randomNumber);

        String paramsSplit[] = BaseAPI.requestParametersProperties.getProperty(parameters).split(", ");

        for(int i = 0; i <= paramsSplit.length - 1; i++){
            requestMap.put(paramsSplit[i], String.valueOf(user.getId()));
        }
        restAssuredUtilities.addPathParametersToRequest(requestMap);

        String pathParamInURL = "";
        for(String pathParam : paramsSplit){
            pathParamInURL = uri.replaceFirst("paramToReplace","{" + pathParam + "}");
            uri = pathParamInURL;
        }

        newUriFinal = uri;

        response = restAssuredUtilities.executeAPI(method, newUriFinal);
    }
}
