package Drivers;

import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static Authantication.Login_Main.Platform;

public class Edit_Driver_Info_Main {
    private RestActions apiobject;
    private String Edit_Driver_Info_URL = "v1/companies/1/drivers/870";

    public Edit_Driver_Info_Main(RestActions apiobject) {
        this.apiobject = apiobject;
    }



    public Response Edit_Driver_Info_Main_Faild_English(String status_id,
                                                        String name,
                                                        String residence_number,
                                                        String license_expire_date,
                                                        String mobile,
                                                        String city_id,
                                                        String area_id) {

        JSONObject Edit_Driver_Info_Body = new JSONObject();
        Edit_Driver_Info_Body.put("status_id", status_id);
        Edit_Driver_Info_Body.put("name", name);
        Edit_Driver_Info_Body.put("residence_number", residence_number);
        Edit_Driver_Info_Body.put("license_expire_date", license_expire_date);
        Edit_Driver_Info_Body.put("mobile", mobile);
        Edit_Driver_Info_Body.put("city_id", city_id);
        Edit_Driver_Info_Body.put("area_id", area_id);

        return apiobject.buildNewRequest(Edit_Driver_Info_URL, RestActions.RequestType.PUT)
                .setContentType(ContentType.JSON)
                .addHeader("Platform", Platform)
                .addHeader("Accept", "application/json")
                .addHeader("Accept-Language", "en")
                .setRequestBody(Edit_Driver_Info_Body)
                .setTargetStatusCode(422)
                .performRequest();
    }


    public Response Edit_Driver_Info_Main_Faild_Arabic(String status_id,
                                                       String name,
                                                       String residence_number,
                                                       String license_expire_date,
                                                       String mobile,
                                                       String city_id,
                                                       String area_id) {

        JSONObject Edit_Driver_Info_Body = new JSONObject();
        Edit_Driver_Info_Body.put("status_id", status_id);
        Edit_Driver_Info_Body.put("name", name);
        Edit_Driver_Info_Body.put("residence_number", residence_number);
        Edit_Driver_Info_Body.put("license_expire_date", license_expire_date);
        Edit_Driver_Info_Body.put("mobile", mobile);
        Edit_Driver_Info_Body.put("city_id", city_id);
        Edit_Driver_Info_Body.put("area_id", area_id);

        return apiobject.buildNewRequest(Edit_Driver_Info_URL, RestActions.RequestType.PUT)
                .setContentType(ContentType.JSON)
                .addHeader("Platform", Platform)
                .addHeader("Accept", "application/json")
                .addHeader("Accept-Language", "ar")
                .setRequestBody(Edit_Driver_Info_Body)
                .setTargetStatusCode(422)
                .performRequest();
    }

    public Response Edit_Driver_Info_Main_Error_Keys_English(String status_id,
                                                             int name,
                                                             int residence_number,
                                                             int license_expire_date,
                                                             String mobile,
                                                             String city_id,
                                                             String area_id) {

        JSONObject Edit_Driver_Info_Body = new JSONObject();
        Edit_Driver_Info_Body.put("status_id", status_id);
        Edit_Driver_Info_Body.put("name", name);
        Edit_Driver_Info_Body.put("residence_number", residence_number);
        Edit_Driver_Info_Body.put("license_expire_date", license_expire_date);
        Edit_Driver_Info_Body.put("mobile", mobile);
        Edit_Driver_Info_Body.put("city_id", city_id);
        Edit_Driver_Info_Body.put("area_id", area_id);

        return apiobject.buildNewRequest(Edit_Driver_Info_URL, RestActions.RequestType.PUT)
                .setContentType(ContentType.JSON)
                .addHeader("Platform", Platform)
                .addHeader("Accept", "application/json")
                .addHeader("Accept-Language", "en")
                .setRequestBody(Edit_Driver_Info_Body)
                .setTargetStatusCode(422)
                .performRequest();
    }


    public Response Edit_Driver_Info_Main_Error_Keys_Arabic(String status_id,
                                                            int name,
                                                            int residence_number,
                                                            int license_expire_date,
                                                            String mobile,
                                                            String city_id,
                                                            String area_id) {

        JSONObject Edit_Driver_Info_Body = new JSONObject();
        Edit_Driver_Info_Body.put("status_id", status_id);
        Edit_Driver_Info_Body.put("name", name);
        Edit_Driver_Info_Body.put("residence_number", residence_number);
        Edit_Driver_Info_Body.put("license_expire_date", license_expire_date);
        Edit_Driver_Info_Body.put("mobile", mobile);
        Edit_Driver_Info_Body.put("city_id", city_id);
        Edit_Driver_Info_Body.put("area_id", area_id);

        return apiobject.buildNewRequest(Edit_Driver_Info_URL, RestActions.RequestType.PUT)
                .setContentType(ContentType.JSON)
                .addHeader("Platform", Platform)
                .addHeader("Accept", "application/json")
                .addHeader("Accept-Language", "ar")
                .setRequestBody(Edit_Driver_Info_Body)
                .setTargetStatusCode(422)
                .performRequest();
    }


}



