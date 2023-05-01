package Orders;

import MainPackage.Main_Variables;
import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Get_Order_Details_Main {
    private RestActions apiobject;
    private Main_Variables main_variables;
    private String Get_Order_Details_URL = "v1/companies/1/orders/572461";

    public Get_Order_Details_Main(RestActions apiobject){
        this.apiobject = apiobject;
    }

    public Response Get_Order_Details_Successfully_English(){

        return apiobject.buildNewRequest(Get_Order_Details_URL , RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .addHeader("platform","@vb~sD~KS#2>]pq")
                .addHeader("Accept","application/json")
                .addHeader("Accept-Language","en")
                .setTargetStatusCode(200)
                .performRequest();
    }

    public Response Get_Order_Details_Successfully_Arabic(){

        return apiobject.buildNewRequest(Get_Order_Details_URL , RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .addHeader("platform","@vb~sD~KS#2>]pq")
                .addHeader("Accept","application/json")
                .addHeader("Accept-Language","ar")
                .setTargetStatusCode(200)
                .performRequest();
    }


    private String Get_Order_Details_URL_Fail = "v1/companies/1/orders/" + Main_Variables.special_characters_Generattor;

    public Response Get_Order_Details_Fail_English(){

        return apiobject.buildNewRequest(Get_Order_Details_URL_Fail , RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .addHeader("platform","@vb~sD~KS#2>]pq")
                .addHeader("Accept","application/json")
                .addHeader("Accept-Language","en")
                .setTargetStatusCode(406)
                .performRequest();
    }

    public Response Get_Order_Details_Fail_Arabic(){

        return apiobject.buildNewRequest(Get_Order_Details_URL_Fail , RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .addHeader("platform","@vb~sD~KS#2>]pq")
                .addHeader("Accept","application/json")
                .addHeader("Accept-Language","ar")
                .setTargetStatusCode(406)
                .performRequest();
    }




}
