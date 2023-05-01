package Company;

import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static Authantication.Login_Main.Platform;

public class Get_company_details_Main {
    private RestActions apiobject;

    private String Get_company_details__FailURL = "v1/companies/Test";


    public Get_company_details_Main(RestActions apiobject) {
        this.apiobject = apiobject;
    }
    public Response Get_company_details_Failed_English(){

        return apiobject.buildNewRequest(Get_company_details__FailURL , RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept" , "application/json")
                .addHeader("Accept-Language", "en")
                .setTargetStatusCode(406)
                .performRequest();
    }
    public Response Get_company_details_Failed_Arabic(){

        return apiobject.buildNewRequest(Get_company_details__FailURL , RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept" , "application/json")
                .addHeader("Accept-Language", "ar")
                .setTargetStatusCode(406)
                .performRequest();
    }

}
