package utilities.restAssuredUtilities;

import endpoints.Endpoints;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.Map;

public class RestAssuredUtilities {
    public RequestSpecification request;
    RequestSpecBuilder builder = new RequestSpecBuilder();

    public RestAssuredUtilities(){
        RequestSpecification requestSpec = builder.build();
        request = RestAssured.given().spec(requestSpec);
    }

    public  void setRequestBaseUri(String baseURL) { request.baseUri(baseURL); }

    public  void addPathParametersToRequest(Map<String, String> pathParams) {
        request.pathParams(pathParams);
    }

    public  void addQueryParametersToRequest(Map<String, String> queryParams) {
        request.queryParams(queryParams);
    }

    public  void addJsonBodyToRequest(String requestBody) { request.body(requestBody); }

    public  void addFileBodyToRequest(File file) {
        request.body(file);
    }

    public  void addMapBodyToRequest(Map<String, String> mapBody) {
        request.body(mapBody);
    }

    public  void addFormParametersToRequest(Map<String, String> body) {
        request.formParams(body);
    }

    public ResponseOptions<Response> executeAPI(String method, String url) {
        if (method.equals(Endpoints.GET))
            return request.get(url);
        if (method.equals(Endpoints.POST))
            return request.post(url);
        if (method.equals(Endpoints.UPDATE))
            return request.put(url);
        if (method.equals(Endpoints.DELETE))
            return request.delete(url);
        if (method.equals(Endpoints.PUT))
            return request.put(url);
        if (method.equals(Endpoints.PATCH))
            return request.patch(url);

        return null;
    }

    public  void setRequestContentType(ContentType contentType){
        request.header("Content-Type", contentType);
    }

    public void addAuthorizationHeader(String token){
        request.header("Authorization", "Bearer " + token);
    }

}
