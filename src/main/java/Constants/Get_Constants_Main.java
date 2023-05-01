package Constants;

import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static Authantication.Login_Main.Platform;

public class Get_Constants_Main {

    private RestActions apiobject;
    private String Get_Constants_URL = "v1/constant";

    public Get_Constants_Main(RestActions apiobject){
        this.apiobject = apiobject;
    }

    public Response Get_Constants_Successfully_English(){
        return apiobject.buildNewRequest(Get_Constants_URL , RestActions.RequestType.GET)
                .addHeader("Platform" ,Platform)
                .addHeader("Accept-Language" , "en")
                .performRequest();
    }


    public Response Get_Constants_Successfully_Arabic(){
        return apiobject.buildNewRequest(Get_Constants_URL , RestActions.RequestType.GET)
                .addHeader("Platform" ,Platform)
                .addHeader("Accept-Language" , "ar")
                .performRequest();
    }


}

