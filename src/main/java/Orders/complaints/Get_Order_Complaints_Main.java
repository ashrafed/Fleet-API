package Orders.complaints;

import MainPackage.Main_Variables;
import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

public class Get_Order_Complaints_Main {
    private RestActions apiobject;
    private Main_Variables main_variables;
    private String Get_Order_Complaints_URL = "/v1/companies/1/orders/758176/complaints";

    public Get_Order_Complaints_Main(RestActions apiobject){
        this.apiobject = apiobject;
    }

    public Response Get_Order_Complaints_Successfully_English(){

        return apiobject.buildNewRequest(Get_Order_Complaints_URL , RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .addHeader("platform","@vb~sD~KS#2>]pq")
                .addHeader("Accept","application/json")
                .addHeader("Accept-Language","en")
                .setTargetStatusCode(200)
                .performRequest();
    }

    public Response Get_Order_Complaints_Successfully_Arabic(){

        return apiobject.buildNewRequest(Get_Order_Complaints_URL , RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .addHeader("platform","@vb~sD~KS#2>]pq")
                .addHeader("Accept","application/json")
                .addHeader("Accept-Language","ar")
                .setTargetStatusCode(200)
                .performRequest();
    }



}
