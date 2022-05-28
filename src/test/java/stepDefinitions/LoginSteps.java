package stepDefinitions;

import endpoints.Endpoints;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.junit.Assert;
import utilities.baseAPI.BaseAPI;
import utilities.restAssuredUtilities.RestAssuredUtilities;

import java.util.HashMap;

public class LoginSteps extends TestBase{


    public static RestAssuredUtilities restAssuredUtilities;
    private static ResponseOptions<Response> response;
    String token = "";

    @Given("user is authenticated using valid credentials")
    public void user_is_authenticated_using_valid_credentials() {
        restAssuredUtilities = new RestAssuredUtilities();
        HashMap<String, String> map = new HashMap<>();
        map.put("email", "Developer13231@gmail.com");
        map.put("password", "123456");
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
}
