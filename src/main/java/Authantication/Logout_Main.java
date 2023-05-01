package Authantication;

import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static Authantication.Login_Main.Platform;

public class Logout_Main {
    private RestActions apiobject;


    private String Logout_URL= "v1/auth/logout";



    public Logout_Main(RestActions apiobject){
        this.apiobject = apiobject;
    }

    public Response  Logout_Successfully_English(){
        return apiobject.buildNewRequest(Logout_URL , RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept" , "application/json")
                .addHeader("Accept-Language", "en")
                .performRequest();
}

    public Response  Logout_Successfully_Arabic(){
        return apiobject.buildNewRequest(Logout_URL , RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .addHeader("Platform" ,Platform )
                .addHeader("Accept" , "application/json")
                .addHeader("Accept-Language", "ar")
                .performRequest();
    }

}
