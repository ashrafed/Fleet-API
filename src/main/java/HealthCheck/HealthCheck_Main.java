package HealthCheck;

import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static Authantication.Login_Main.Platform;

public class HealthCheck_Main {
    private RestActions apiobject;

    private String HealthCheck_URL ="api/health-check";

    public static  final String Fleet_Base_URL  = System.getProperty("Fleet_Base_URL");


    public HealthCheck_Main(RestActions apiobject){
        this.apiobject = apiobject;
    }


    public Response HealthCheck_Successfully_English(){
        return apiobject.buildNewRequest(HealthCheck_URL , RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept" , "application/json")
                .addHeader("Accept-Language", "en")
                .performRequest();

    }


    public Response HealthCheck_Successfully_Arabic(){
        return apiobject.buildNewRequest(HealthCheck_URL , RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept" , "application/json")
                .addHeader("Accept-Language", "ar")
                .performRequest();

    }


}
