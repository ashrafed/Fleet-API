package Export_Import;

import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static Authantication.Login_Main.Platform;

public class Export_Main {
    private RestActions apiobject;
    private String Export_URL = "v1/companies/1/export/drivers?extension=pdf&limit=3";

    public Export_Main(RestActions apiobject){
        this.apiobject = apiobject;
    }


    public Response Export_Successfully_English(){

        return apiobject.buildNewRequest(Export_URL , RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept" , "application/json")
                .addHeader("Accept-Language", "en")
                .performRequest();
    }

}
