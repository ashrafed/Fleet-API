package Drivers;

import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static Authantication.Login_Main.Platform;

public class Create_Driver_Main {
    private RestActions apiobject;

    private String Create_Driver_URL ="v1/companies/1/drivers";


    public Create_Driver_Main(RestActions apiobject){
        this.apiobject = apiobject;
    }

    public Response Create_Driver_Successfully_English(String name ,
                                                       String mobile ,
                                                       String residence_number,
                                                       String job ,
                                                       String license_expire_date ,
                                                       int city_id,
                                                       String area_id){

        JSONObject Create_Driver_Body =new JSONObject();
        Create_Driver_Body.put("name" ,name);
        Create_Driver_Body.put("mobile",mobile);
        Create_Driver_Body.put("residence_number",residence_number);
        Create_Driver_Body.put("job",job);
        Create_Driver_Body.put("license_expire_date",license_expire_date);
        Create_Driver_Body.put("city_id",city_id);
        Create_Driver_Body.put("area_id",area_id);

        return apiobject.buildNewRequest(Create_Driver_URL , RestActions.RequestType.POST)
                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept" , "application/json")
                .addHeader("Accept-Language", "en")
                .setRequestBody(Create_Driver_Body)
                .setTargetStatusCode(201)
                .performRequest();
    }

    public Response Create_Driver_Successfully_Arabic(String name ,
                                                       String mobile ,
                                                       String residence_number,
                                                       String job ,
                                                       String license_expire_date ,
                                                       int city_id,
                                                       String area_id){

        JSONObject Create_Driver_Body =new JSONObject();
        Create_Driver_Body.put("name" ,name);
        Create_Driver_Body.put("mobile",mobile);
        Create_Driver_Body.put("residence_number",residence_number);
        Create_Driver_Body.put("job",job);
        Create_Driver_Body.put("license_expire_date",license_expire_date);
        Create_Driver_Body.put("city_id",city_id);
        Create_Driver_Body.put("area_id",area_id);

        return apiobject.buildNewRequest(Create_Driver_URL , RestActions.RequestType.POST)
                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept" , "application/json")
                .addHeader("Accept-Language", "ar")
                .setRequestBody(Create_Driver_Body)
                .setTargetStatusCode(201)
                .performRequest();
    }


    public Response Create_Driver_Fail_English(        String name ,
                                                       String mobile ,
                                                       String residence_number,
                                                       String job ,
                                                       String license_expire_date ,
                                                       String city_id,
                                                       String area_id){

        JSONObject Create_Driver_Body =new JSONObject();
        Create_Driver_Body.put("name" ,name);
        Create_Driver_Body.put("mobile",mobile);
        Create_Driver_Body.put("residence_number",residence_number);
        Create_Driver_Body.put("job",job);
        Create_Driver_Body.put("license_expire_date",license_expire_date);
        Create_Driver_Body.put("city_id",city_id);
        Create_Driver_Body.put("area_id",area_id);

        return apiobject.buildNewRequest(Create_Driver_URL , RestActions.RequestType.POST)
                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept" , "application/json")
                .addHeader("Accept-Language", "en")
                .setRequestBody(Create_Driver_Body)
                .setTargetStatusCode(422)
                .performRequest();
    }

    public Response Create_Driver_Fail_Arabic(String name ,
                                                      String mobile ,
                                                      String residence_number,
                                                      String job ,
                                                      String license_expire_date ,
                                                      String city_id,
                                                      String area_id){

        JSONObject Create_Driver_Body =new JSONObject();
        Create_Driver_Body.put("name" ,name);
        Create_Driver_Body.put("mobile",mobile);
        Create_Driver_Body.put("residence_number",residence_number);
        Create_Driver_Body.put("job",job);
        Create_Driver_Body.put("license_expire_date",license_expire_date);
        Create_Driver_Body.put("city_id",city_id);
        Create_Driver_Body.put("area_id",area_id);

        return apiobject.buildNewRequest(Create_Driver_URL , RestActions.RequestType.POST)
                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept" , "application/json")
                .addHeader("Accept-Language", "ar")
                .setRequestBody(Create_Driver_Body)
                .setTargetStatusCode(422)
                .performRequest();
    }


    public Response Create_Driver_Fail_Error_Keys_English( int name ,
                                                       String mobile ,
                                                       int residence_number,
                                                       int job ,
                                                       int license_expire_date ,
                                                       String city_id,
                                                       String area_id){

        JSONObject Create_Driver_Body =new JSONObject();
        Create_Driver_Body.put("name" ,name);
        Create_Driver_Body.put("mobile",mobile);
        Create_Driver_Body.put("residence_number",residence_number);
        Create_Driver_Body.put("job",job);
        Create_Driver_Body.put("license_expire_date",license_expire_date);
        Create_Driver_Body.put("city_id",city_id);
        Create_Driver_Body.put("area_id",area_id);

        return apiobject.buildNewRequest(Create_Driver_URL , RestActions.RequestType.POST)
                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept" , "application/json")
                .addHeader("Accept-Language", "en")
                .setRequestBody(Create_Driver_Body)
                .setTargetStatusCode(422)
                .performRequest();
    }


    public Response Create_Driver_Fail_Error_Keys_Arabic( int name ,
                                                           String mobile ,
                                                           int residence_number,
                                                           int job ,
                                                           int license_expire_date ,
                                                           String city_id,
                                                           String area_id){

        JSONObject Create_Driver_Body =new JSONObject();
        Create_Driver_Body.put("name" ,name);
        Create_Driver_Body.put("mobile",mobile);
        Create_Driver_Body.put("residence_number",residence_number);
        Create_Driver_Body.put("job",job);
        Create_Driver_Body.put("license_expire_date",license_expire_date);
        Create_Driver_Body.put("city_id",city_id);
        Create_Driver_Body.put("area_id",area_id);

        return apiobject.buildNewRequest(Create_Driver_URL , RestActions.RequestType.POST)
                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept" , "application/json")
                .addHeader("Accept-Language", "ar")
                .setRequestBody(Create_Driver_Body)
                .setTargetStatusCode(422)
                .performRequest();
    }

}
