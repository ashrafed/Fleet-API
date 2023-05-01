package Orders;

import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Cancel_Order_Main {


    private RestActions apiobject;
    private String Cancel_Order_URL = "v1/companies/1/orders/538602";

    public Cancel_Order_Main(RestActions apiobject){
        this.apiobject = apiobject;
    }

    public Response Cancel_Order_Successfully_English(){

        return apiobject.buildNewRequest(Cancel_Order_URL , RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .addHeader("platform","@vb~sD~KS#2>]pq")
                .addHeader("Accept","application/json")
                .addHeader("Accept-Language","en")
                .setTargetStatusCode(200)
                .performRequest();
    }


    public Response Cancel_Order_Successfully_Arabic(){

        return apiobject.buildNewRequest(Cancel_Order_URL , RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .addHeader("platform","@vb~sD~KS#2>]pq")
                .addHeader("Accept","application/json")
                .addHeader("Accept-Language","ar")
                .setTargetStatusCode(200)
                .performRequest();
    }




}
