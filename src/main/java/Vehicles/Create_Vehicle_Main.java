package Vehicles;

import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static Authantication.Login_Main.Platform;

public class Create_Vehicle_Main {
    private RestActions apiobject;

    private String Create_Vehicle_URL = "v1/companies/1/vehicles";


    public Create_Vehicle_Main(RestActions apiobject){
        this.apiobject = apiobject;
    }


    public Response Add_Vehicle_successfully_English(int driver_id,
                                                     String vehicle_vin_number,
                                                     int model_id,
                                                     int brand_id,
                                                     int color_id,
                                                     int model_year,
                                                     int engine_type_id,
                                                     String plate_number,
                                                     String plate_letter_1_id,
                                                     String plate_letter_2_id,
                                                     String plate_letter_3_id){


        JSONObject Add_Vehicle_Body = new JSONObject();
        Add_Vehicle_Body.put("driver_id",driver_id);
        Add_Vehicle_Body.put("vehicle_vin_number",vehicle_vin_number);
        Add_Vehicle_Body.put("model_id",model_id);
        Add_Vehicle_Body.put("brand_id",brand_id);
        Add_Vehicle_Body.put("color_id",color_id);
        Add_Vehicle_Body.put("model_year",model_year);
        Add_Vehicle_Body.put("engine_type_id",engine_type_id);
        Add_Vehicle_Body.put("plate_number",plate_number);
        Add_Vehicle_Body.put("plate_letter_1_id",plate_letter_1_id);
        Add_Vehicle_Body.put("plate_letter_2_id",plate_letter_2_id);
        Add_Vehicle_Body.put("plate_letter_3_id",plate_letter_3_id);



        return apiobject.buildNewRequest(Create_Vehicle_URL , RestActions.RequestType.POST)

                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept","application/json")
                .addHeader("Accept-Language","en")
                .setRequestBody(Add_Vehicle_Body)
                .setTargetStatusCode(201)
                .performRequest();



    }


    public Response Add_Vehicle_successfully_Arabic(int driver_id,
                                                     String vehicle_vin_number,
                                                     String model_id,
                                                     String brand_id,
                                                     String color_id,
                                                     String model_year,
                                                     int engine_type_id,
                                                     String plate_number,
                                                     String plate_letter_1_id,
                                                     String plate_letter_2_id,
                                                     String plate_letter_3_id){


        JSONObject Add_Vehicle_Body = new JSONObject();
        Add_Vehicle_Body.put("driver_id",driver_id);
        Add_Vehicle_Body.put("vehicle_vin_number",vehicle_vin_number);
        Add_Vehicle_Body.put("model_id",model_id);
        Add_Vehicle_Body.put("brand_id",brand_id);
        Add_Vehicle_Body.put("color_id",color_id);
        Add_Vehicle_Body.put("model_year",model_year);
        Add_Vehicle_Body.put("engine_type_id",engine_type_id);
        Add_Vehicle_Body.put("plate_number",plate_number);
        Add_Vehicle_Body.put("plate_letter_1_id",plate_letter_1_id);
        Add_Vehicle_Body.put("plate_letter_2_id",plate_letter_2_id);
        Add_Vehicle_Body.put("plate_letter_3_id",plate_letter_3_id);



        return apiobject.buildNewRequest(Create_Vehicle_URL , RestActions.RequestType.POST)

                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept","application/json")
                .addHeader("Accept-Language","ar")
                .setRequestBody(Add_Vehicle_Body)
                .setTargetStatusCode(201)
                .performRequest();



    }


    public Response Add_Vehicle_Fail_English(String driver_id,
                                                     String vehicle_vin_number,
                                                     String model_id,
                                                     String brand_id,
                                                     String color_id,
                                                     String model_year,
                                                     String engine_type_id,
                                                     String plate_number,
                                                     String plate_letter_1_id,
                                                     String plate_letter_2_id,
                                                     String plate_letter_3_id){


        JSONObject Add_Vehicle_Body = new JSONObject();
        Add_Vehicle_Body.put("driver_id",driver_id);
        Add_Vehicle_Body.put("vehicle_vin_number",vehicle_vin_number);
        Add_Vehicle_Body.put("model_id",model_id);
        Add_Vehicle_Body.put("brand_id",brand_id);
        Add_Vehicle_Body.put("color_id",color_id);
        Add_Vehicle_Body.put("model_year",model_year);
        Add_Vehicle_Body.put("engine_type_id",engine_type_id);
        Add_Vehicle_Body.put("plate_number",plate_number);
        Add_Vehicle_Body.put("plate_letter_1_id",plate_letter_1_id);
        Add_Vehicle_Body.put("plate_letter_2_id",plate_letter_2_id);
        Add_Vehicle_Body.put("plate_letter_3_id",plate_letter_3_id);



        return apiobject.buildNewRequest(Create_Vehicle_URL , RestActions.RequestType.POST)

                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept","application/json")
                .addHeader("Accept-Language","en")
                .setRequestBody(Add_Vehicle_Body)
                .setTargetStatusCode(422)
                .performRequest();



    }


    public Response Add_Vehicle_Fail_Arabic(String driver_id,
                                                    String vehicle_vin_number,
                                                    String model_id,
                                                    String brand_id,
                                                    String color_id,
                                                    String model_year,
                                                    String engine_type_id,
                                                    String plate_number,
                                                    String plate_letter_1_id,
                                                    String plate_letter_2_id,
                                                    String plate_letter_3_id){


        JSONObject Add_Vehicle_Body = new JSONObject();
        Add_Vehicle_Body.put("driver_id",driver_id);
        Add_Vehicle_Body.put("vehicle_vin_number",vehicle_vin_number);
        Add_Vehicle_Body.put("model_id",model_id);
        Add_Vehicle_Body.put("brand_id",brand_id);
        Add_Vehicle_Body.put("color_id",color_id);
        Add_Vehicle_Body.put("model_year",model_year);
        Add_Vehicle_Body.put("engine_type_id",engine_type_id);
        Add_Vehicle_Body.put("plate_number",plate_number);
        Add_Vehicle_Body.put("plate_letter_1_id",plate_letter_1_id);
        Add_Vehicle_Body.put("plate_letter_2_id",plate_letter_2_id);
        Add_Vehicle_Body.put("plate_letter_3_id",plate_letter_3_id);



        return apiobject.buildNewRequest(Create_Vehicle_URL , RestActions.RequestType.POST)

                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept","application/json")
                .addHeader("Accept-Language","ar")
                .setRequestBody(Add_Vehicle_Body)
                .setTargetStatusCode(422)
                .performRequest();



    }
}
