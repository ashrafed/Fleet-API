package Orders;

import MainPackage.Main_Variables;
import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class List_Orders_Main {
    private RestActions apiobject;
    private Main_Variables main_variables;
    private String List_Orders_URL = "v1/companies/1/orders";

    public List_Orders_Main(RestActions apiobject){
        this.apiobject = apiobject;
    }

    public Response List_Orders_Successfully_English(){

        return apiobject.buildNewRequest(List_Orders_URL , RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .addHeader("platform","@vb~sD~KS#2>]pq")
                .addHeader("Accept","application/json")
                .addHeader("Accept-Language","en")
                .setTargetStatusCode(200)
                .performRequest();
    }

    public Response List_Orders_Successfully_Arabic(){

        return apiobject.buildNewRequest(List_Orders_URL , RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .addHeader("platform","@vb~sD~KS#2>]pq")
                .addHeader("Accept","application/json")
                .addHeader("Accept-Language","ar")
                .setTargetStatusCode(200)
                .performRequest();
    }

    private String List_Orders_URL_Fail = "v1/companies/String/orders";

    public Response List_Orders_Fail_English(){

        return apiobject.buildNewRequest(List_Orders_URL_Fail , RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .addHeader("platform","@vb~sD~KS#2>]pq")
                .addHeader("Accept","application/json")
                .addHeader("Accept-Language","en")
                .setTargetStatusCode(422)
                .performRequest();
    }

    public Response List_Orders_Fail_Arabic(){

        return apiobject.buildNewRequest(List_Orders_URL_Fail , RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .addHeader("platform","@vb~sD~KS#2>]pq")
                .addHeader("Accept","application/json")
                .addHeader("Accept-Language","ar")
                .setTargetStatusCode(422)
                .performRequest();
    }

}
