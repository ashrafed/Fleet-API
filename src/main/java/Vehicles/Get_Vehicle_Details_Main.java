package Vehicles;

import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static Authantication.Login_Main.Platform;

public class Get_Vehicle_Details_Main {

    private RestActions apiobject;
    private String Get_Vehicle_Details_URL = "v1/companies/1/vehicles/2";


    public Get_Vehicle_Details_Main(RestActions apiobject){
        this.apiobject = apiobject;
    }

    public Response Get_Vehicle_Details_Successfully_English(){
        return apiobject.buildNewRequest(Get_Vehicle_Details_URL , RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept","application/json")
                .addHeader("Accept-Language","en")
                .performRequest();
    }

    public Response Get_Vehicle_Details_Successfully_Arabic(){
        return apiobject.buildNewRequest(Get_Vehicle_Details_URL , RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept","application/json")
                .addHeader("Accept-Language","ar")
                .performRequest();
    }

}
