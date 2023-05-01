package Vehicles;

import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static Authantication.Login_Main.Platform;

public class List_Vehicles_Main {

    private RestActions apiobject;

    private String List_Vehicles_URL = "v1/companies/1/vehicles";


    public List_Vehicles_Main(RestActions apiobject){
        this.apiobject = apiobject;
    }

    public Response  List_Vehicles_Successfully_English(){
        return apiobject.buildNewRequest(List_Vehicles_URL , RestActions.RequestType.GET)

                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept","application/json")
                .addHeader("Accept-Language","en")
                .performRequest();
    }

    public Response  List_Vehicles_Successfully_Arabic(){
        return apiobject.buildNewRequest(List_Vehicles_URL , RestActions.RequestType.GET)

                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept","application/json")
                .addHeader("Accept-Language","ar")
                .performRequest();
    }


    private String List_Vehicles_Fail_URL = "v1/companies/1/vehicles?brand_id=f&model_id=g&vin_number=1&limit=s&page=2&created_at_from=s&created_at_to=q";


    public Response  List_Vehicles_Fail_English(){
        return apiobject.buildNewRequest(List_Vehicles_Fail_URL , RestActions.RequestType.GET)

                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept","application/json")
                .addHeader("Accept-Language","en")
                .setTargetStatusCode(422)
                .performRequest();
    }

    public Response  List_Vehicles_Fail_Arabic(){
        return apiobject.buildNewRequest(List_Vehicles_Fail_URL , RestActions.RequestType.GET)

                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept","application/json")
                .addHeader("Accept-Language","ar")
                .setTargetStatusCode(422)
                .performRequest();
    }

}
