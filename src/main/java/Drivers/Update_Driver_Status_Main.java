package Drivers;

import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static Authantication.Login_Main.Platform;

public class Update_Driver_Status_Main {
    private RestActions apiobject;

    private String Update_Driver_Status_URL = "v1/companies/1/drivers/changeStatus/968";

    public Update_Driver_Status_Main(RestActions apiobject){
        this.apiobject = apiobject;
    }


    public Response Update_Driver_Status_Successfully_English(String status_id){

        JSONObject Update_Driver_Status_Body = new JSONObject();
        Update_Driver_Status_Body.put("status_id" ,status_id);

        return apiobject.buildNewRequest(Update_Driver_Status_URL , RestActions.RequestType.PUT)
                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept" , "application/json")
                .addHeader("Accept-Language", "en")
                .setRequestBody(Update_Driver_Status_Body)
                .performRequest();
    }
    public Response Update_Driver_Status_Successfully_Arabic(String status_id){

        JSONObject Update_Driver_Status_Body = new JSONObject();
        Update_Driver_Status_Body.put("status_id" ,status_id);

        return apiobject.buildNewRequest(Update_Driver_Status_URL , RestActions.RequestType.PUT)
                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept" , "application/json")
                .addHeader("Accept-Language", "ar")
                .setRequestBody(Update_Driver_Status_Body)
                .performRequest();
    }

    public Response Update_Driver_Status_Fail_English(String status_id){

        JSONObject Update_Driver_Status_Body = new JSONObject();
        Update_Driver_Status_Body.put("status_id" ,status_id);

        return apiobject.buildNewRequest(Update_Driver_Status_URL , RestActions.RequestType.PUT)
                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept" , "application/json")
                .addHeader("Accept-Language", "en")
                .setRequestBody(Update_Driver_Status_Body)
                .setTargetStatusCode(422)
                .performRequest();
    }

    public Response Update_Driver_Status_Fail_Arabic(String status_id){

        JSONObject Update_Driver_Status_Body = new JSONObject();
        Update_Driver_Status_Body.put("status_id" ,status_id);

        return apiobject.buildNewRequest(Update_Driver_Status_URL , RestActions.RequestType.PUT)
                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept" , "application/json")
                .addHeader("Accept-Language", "ar")
                .setRequestBody(Update_Driver_Status_Body)
                .setTargetStatusCode(422)
                .performRequest();
    }
}