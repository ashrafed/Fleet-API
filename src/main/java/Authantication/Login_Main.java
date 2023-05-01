package Authantication;

import MainPackage.HeaderVariable;
import MainPackage.Main_Variables;
import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

public class Login_Main {
    private RestActions apiobject;

    private String Login_URL= "v1/auth/login";

    public static  final String Base_Url  = System.getProperty("Base_Url");
    public static  final String Platform  = System.getProperty("Platform");


    public Login_Main(RestActions apiobject){
        this.apiobject = apiobject;
    }





    public Response Login_Successfully_English(String email,
                                               String password){

        JSONObject Login_Body  = new JSONObject();
        Login_Body.put("email" , email);
        Login_Body.put("password" , password);


        Response CreatToken = apiobject.buildNewRequest(Login_URL , RestActions.RequestType.POST)
                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform )
                .addHeader("Accept" , "application/json")
                .addHeader("Accept-Language", "en")
                .setRequestBody(Login_Body)
                .performRequest();



        String token = RestActions.getResponseJSONValue(CreatToken, "data.token");

        apiobject.addHeaderVariable("Authorization", "Bearer " + token);

        return CreatToken;

    }


    public Response Login_Successfully_Arabic(String email,
                                               String password){

        JSONObject Login_Body  = new JSONObject();
        Login_Body.put("email" , email);
        Login_Body.put("password" , password);

        Response CreatToken = apiobject.buildNewRequest(Login_URL , RestActions.RequestType.POST)
                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept" , "application/json")
                .addHeader("Accept-Language", "ar")
                .setRequestBody(Login_Body)
                .performRequest();

        String token = RestActions.getResponseJSONValue(CreatToken, "data.token");
        apiobject.addHeaderVariable("Authorization", "Bearer " + token);
        return CreatToken;

    }


    public Response Login_Fail_English(String email,
                                               String password){

        JSONObject Login_Body  = new JSONObject();
        Login_Body.put("email" , email);
        Login_Body.put("password" , password);

       return apiobject.buildNewRequest(Login_URL , RestActions.RequestType.POST)
                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept" , "application/json")
                .addHeader("Accept-Language", "en")
                .setRequestBody(Login_Body)
                .setTargetStatusCode(422)
                .performRequest();


    }


    public Response Login_Fail_Arabic(String email,
                                              String password){

        JSONObject Login_Body  = new JSONObject();
        Login_Body.put("email" , email);
        Login_Body.put("password" , password);

        return apiobject.buildNewRequest(Login_URL , RestActions.RequestType.POST)
                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept" , "application/json")
                .addHeader("Accept-Language", "ar")
                .setRequestBody(Login_Body)
                .setTargetStatusCode(422)
                .performRequest();



    }
}
