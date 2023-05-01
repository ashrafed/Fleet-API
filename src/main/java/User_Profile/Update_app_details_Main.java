package User_Profile;

import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

public class Update_app_details_Main {
    private RestActions apiobject;
    private String Update_app_details_URL = "v1/profile/app/98989888367";

    public Update_app_details_Main(RestActions apiobject){
        this.apiobject = apiobject;
    }

    public Response Update_app_details_Successfully_English(String last_used_at,
                                                      String fcm_token,
                                                      String type,
                                                      String version){

        JSONObject Update_app_details_Body = new JSONObject();
        Update_app_details_Body.put("last_used_at",last_used_at);
        Update_app_details_Body.put("fcm_token",fcm_token);
        Update_app_details_Body.put("type",type);
        Update_app_details_Body.put("version",version);

        return apiobject.buildNewRequest(Update_app_details_URL , RestActions.RequestType.PATCH)
                .setContentType(ContentType.JSON)
                .addHeader("platform","@vb~sD~KS#2>]pq")
                .addHeader("Accept","application/json")
                .addHeader("Accept-Language","en")
                .setTargetStatusCode(200)
                .performRequest();
    }
}
