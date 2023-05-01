package Company;

import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static Authantication.Login_Main.Platform;

public class List_companies_Main {
    private RestActions apiobject;
    private String List_companies_URL = "v1/companies";
    private String Get_company_details__FailURL = "v1/companies/Test";


    public List_companies_Main(RestActions apiobject) {
        this.apiobject = apiobject;
    }
    public Response List_companies_Main_Successfully_English(){

        return apiobject.buildNewRequest(List_companies_URL , RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .addHeader("id" , "2ferfre")
                .addHeader("limit" , "2")
                .addHeader("Accept-Language", "en")
                .performRequest();
    }
    public Response List_companies_Main_Successfully_Arabic(){

        return apiobject.buildNewRequest(List_companies_URL , RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .addHeader("id" , "2ferfre")
                .addHeader("limit" , "2")
                .addHeader("Accept-Language", "ar")
                .performRequest();
    }
    public Response List_companies_Main_Failed_English(){

        return apiobject.buildNewRequest(Get_company_details__FailURL , RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .addHeader("id" , "2ferfre")
                .addHeader("limit" , "2")
                .addHeader("Accept-Language", "en")
                .setTargetStatusCode(406)
                .performRequest();
    }
    public Response List_companies_Main_Failed_Arabic(){

        return apiobject.buildNewRequest(Get_company_details__FailURL , RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .addHeader("id" , "2ferfre")
                .addHeader("limit" , "2")
                .addHeader("Accept-Language", "ar")
                .setTargetStatusCode(406)
                .performRequest();
    }

}
