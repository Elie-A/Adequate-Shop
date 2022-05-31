package utilities;

import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.json.JSONArray;
import org.json.JSONObject;
import utilities.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MiscUtilities {

    public static HashMap<Integer, User> usersJsonToHashMap(ResponseOptions<Response> response){

        HashMap<Integer, User> userMap = new HashMap<>();

        String jsonString = response.getBody().prettyPrint();
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        for(int i = 0; i < jsonArray.length(); i++){
            User user = new User();
            user.setId(jsonArray.getJSONObject(i).getInt("id"));
            user.setName(jsonArray.getJSONObject(i).getString("name"));
            user.setEmail(jsonArray.getJSONObject(i).getString("email"));
            user.setProfilePicture(jsonArray.getJSONObject(i).getString("profilepicture"));
            user.setLocation(jsonArray.getJSONObject(i).getString("location"));
            user.setCreatedAt(jsonArray.getJSONObject(i).getString("createdat"));
            userMap.put(jsonArray.getJSONObject(i).getInt("id"), user);
        }

        return userMap;
    }

    public static List<User> usersJsonToList(ResponseOptions<Response> response){

        List<User> userList = new ArrayList<>();

        String jsonString = response.getBody().prettyPrint();
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        for(int i = 0; i < jsonArray.length(); i++){
            User user = new User();
            user.setId(jsonArray.getJSONObject(i).getInt("id"));
            user.setName(jsonArray.getJSONObject(i).getString("name"));
            user.setEmail(jsonArray.getJSONObject(i).getString("email"));
            user.setProfilePicture(jsonArray.getJSONObject(i).getString("profilepicture"));
            user.setLocation(jsonArray.getJSONObject(i).getString("location"));
            user.setCreatedAt(jsonArray.getJSONObject(i).getString("createdat"));
            userList.add(user);
        }

        return userList;
    }

    public static String modelToJsonString(User user){
        String jsonString = "{"
                + "\"id\"" + ":" + user.getId() + ","
                + "\"name\"" + ":" + "\"" + user.getName() + "\"" + ","
                + "\"email\"" + ":" + "\"" + user.getEmail() + "\"" + ","
                + "\"location\"" + ":" + "\"" + user.getLocation() + "\""
                + "}";

        return jsonString;
    }
}
