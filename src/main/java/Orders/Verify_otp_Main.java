package Orders;

import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

public class Verify_otp_Main {
    private RestActions apiobject;
    private String Verify_otp_URL = "v1/companies/1/orders/461067/verify-otp";

    public Verify_otp_Main(RestActions apiobject){
        this.apiobject = apiobject;
    }

    public Response Verify_otp_Successfully_English(int type, int otp){

        JSONObject Update_app_details_Body = new JSONObject();
        Update_app_details_Body.put("type",type);
        Update_app_details_Body.put("otp",otp);


        return apiobject.buildNewRequest(Verify_otp_URL , RestActions.RequestType.POST)
                .setContentType(ContentType.JSON)
                .addHeader("platform","@vb~sD~KS#2>]pq")
                .addHeader("Accept","application/json")
                .addHeader("Accept-Language","ar")
                .setRequestBody(Update_app_details_Body)
                .performRequest();
    }
}
