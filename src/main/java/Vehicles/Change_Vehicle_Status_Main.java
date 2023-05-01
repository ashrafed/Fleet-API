package Vehicles;

import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static Authantication.Login_Main.Platform;

public class Change_Vehicle_Status_Main {
    private RestActions apiobject;

    private String Create_Vehicle_URL = "v1/companies/1/vehicles/changeStatus/7952";


    public Change_Vehicle_Status_Main(RestActions apiobject){
        this.apiobject = apiobject;
    }

    public Response Change_Vehicle_Status_successfully_English(String status_id){

        JSONObject Change_Vehicle_Status_Body = new JSONObject();
        Change_Vehicle_Status_Body.put("status_id",status_id);

        return apiobject.buildNewRequest(Create_Vehicle_URL , RestActions.RequestType.PUT)

                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept","application/json")
                .addHeader("Accept-Language","en")
                .setRequestBody(Change_Vehicle_Status_Body)
                .performRequest();

    }


    public Response Change_Vehicle_Status_successfully_Arabic(String status_id){

        JSONObject Change_Vehicle_Status_Body = new JSONObject();
        Change_Vehicle_Status_Body.put("status_id",status_id);

        return apiobject.buildNewRequest(Create_Vehicle_URL , RestActions.RequestType.PUT)

                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept","application/json")
                .addHeader("Accept-Language","ar")
                .setRequestBody(Change_Vehicle_Status_Body)
                .performRequest();

    }



    public Response Change_Vehicle_Status_Fail_English(String status_id){

        JSONObject Change_Vehicle_Status_Body = new JSONObject();
        Change_Vehicle_Status_Body.put("status_id",status_id);

        return apiobject.buildNewRequest(Create_Vehicle_URL , RestActions.RequestType.PUT)

                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept","application/json")
                .addHeader("Accept-Language","en")
                .setRequestBody(Change_Vehicle_Status_Body)
                .setTargetStatusCode(422)
                .performRequest();

    }


    public Response Change_Vehicle_Status_Fail_Arabic(String status_id){

        JSONObject Change_Vehicle_Status_Body = new JSONObject();
        Change_Vehicle_Status_Body.put("status_id",status_id);

        return apiobject.buildNewRequest(Create_Vehicle_URL , RestActions.RequestType.PUT)

                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept","application/json")
                .addHeader("Accept-Language","ar")
                .setRequestBody(Change_Vehicle_Status_Body)
                .setTargetStatusCode(422)
                .performRequest();

    }


}
