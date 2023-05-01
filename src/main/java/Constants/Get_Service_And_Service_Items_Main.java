package Constants;

import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static Authantication.Login_Main.Platform;

public class Get_Service_And_Service_Items_Main {
    private RestActions apiobject;
    private String Get_Service_And_Service_Items_URL = "v1/constant/services/home";

    public Get_Service_And_Service_Items_Main(RestActions apiobject){
        this.apiobject = apiobject;
    }


    public Response Get_Service_And_Service_Items_Successfully_English(){
        return apiobject.buildNewRequest(Get_Service_And_Service_Items_URL , RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept-Language", "en")
                .performRequest();
    }


    public Response Get_Service_And_Service_Items_Successfully_Arabic(){
        return apiobject.buildNewRequest(Get_Service_And_Service_Items_URL , RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept-Language", "ar")
                .performRequest();
    }
}