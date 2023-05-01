package Vehicles;

import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static Authantication.Login_Main.Platform;

public class Update_Vehicle_Info_Main {

    private RestActions apiobject;

    private String Update_Vehicle_Info_URL = "v1/companies/1/vehicles/3";


    public Update_Vehicle_Info_Main(RestActions apiobject){
        this.apiobject = apiobject;
    }


    public Response Update_Vehicle_Info_successfully_English(int driver_id,
                                                     String vehicle_vin_number,
                                                     String model_id,
                                                     String brand_id,
                                                     String color_id,
                                                     String year,
                                                     String engine_type_id,
                                                     String plate_number,
                                                     String plate_letter_1_id,
                                                     String plate_letter_2_id,
                                                     String plate_letter_3_id,
                                                     String Status){


        JSONObject Add_Vehicle_Body = new JSONObject();
        Add_Vehicle_Body.put("driver_id",driver_id);
        Add_Vehicle_Body.put("vin_number",vehicle_vin_number);
        Add_Vehicle_Body.put("model_id",model_id);
        Add_Vehicle_Body.put("brand_id",brand_id);
        Add_Vehicle_Body.put("color_id",color_id);
        Add_Vehicle_Body.put("year",year);
        Add_Vehicle_Body.put("engine_type_id",engine_type_id);
        Add_Vehicle_Body.put("plate_number",plate_number);
        Add_Vehicle_Body.put("plate_letter_1_id",plate_letter_1_id);
        Add_Vehicle_Body.put("plate_letter_2_id",plate_letter_2_id);
        Add_Vehicle_Body.put("plate_letter_3_id",plate_letter_3_id);
        Add_Vehicle_Body.put("Status",Status);



        return apiobject.buildNewRequest(Update_Vehicle_Info_URL , RestActions.RequestType.PUT)
                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept","application/json")
                .addHeader("Accept-Language","en")
                .setRequestBody(Add_Vehicle_Body)
                .performRequest();



    }



    public Response Update_Vehicle_Info_Fail_English(String driver_id,
                                                             String vehicle_vin_number,
                                                             String model_id,
                                                             String brand_id,
                                                             String color_id,
                                                             String year,
                                                             String engine_type_id,
                                                             String plate_number,
                                                             String plate_letter_1_id,
                                                             String plate_letter_2_id,
                                                             String plate_letter_3_id){


        JSONObject Add_Vehicle_Body = new JSONObject();
        Add_Vehicle_Body.put("driver_id",driver_id);
        Add_Vehicle_Body.put("vin_number",vehicle_vin_number);
        Add_Vehicle_Body.put("model_id",model_id);
        Add_Vehicle_Body.put("brand_id",brand_id);
        Add_Vehicle_Body.put("color_id",color_id);
        Add_Vehicle_Body.put("year",year);
        Add_Vehicle_Body.put("engine_type_id",engine_type_id);
        Add_Vehicle_Body.put("plate_number",plate_number);
        Add_Vehicle_Body.put("plate_letter_1_id",plate_letter_1_id);
        Add_Vehicle_Body.put("plate_letter_2_id",plate_letter_2_id);
        Add_Vehicle_Body.put("plate_letter_3_id",plate_letter_3_id);



        return apiobject.buildNewRequest(Update_Vehicle_Info_URL , RestActions.RequestType.PUT)

                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept","application/json")
                .addHeader("Accept-Language","en")
                .setRequestBody(Add_Vehicle_Body)
                .setTargetStatusCode(422)
                .performRequest();



    }


    public Response Update_Vehicle_Info_Fail_Arabic(String driver_id,
                                                            String vehicle_vin_number,
                                                            String model_id,
                                                            String brand_id,
                                                            String color_id,
                                                            String year,
                                                            String engine_type_id,
                                                            String plate_number,
                                                            String plate_letter_1_id,
                                                            String plate_letter_2_id,
                                                            String plate_letter_3_id){


        JSONObject Add_Vehicle_Body = new JSONObject();
        Add_Vehicle_Body.put("driver_id",driver_id);
        Add_Vehicle_Body.put("vin_number",vehicle_vin_number);
        Add_Vehicle_Body.put("model_id",model_id);
        Add_Vehicle_Body.put("brand_id",brand_id);
        Add_Vehicle_Body.put("color_id",color_id);
        Add_Vehicle_Body.put("year",year);
        Add_Vehicle_Body.put("engine_type_id",engine_type_id);
        Add_Vehicle_Body.put("plate_number",plate_number);
        Add_Vehicle_Body.put("plate_letter_1_id",plate_letter_1_id);
        Add_Vehicle_Body.put("plate_letter_2_id",plate_letter_2_id);
        Add_Vehicle_Body.put("plate_letter_3_id",plate_letter_3_id);



        return apiobject.buildNewRequest(Update_Vehicle_Info_URL , RestActions.RequestType.PUT)

                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept","application/json")
                .addHeader("Accept-Language","ar")
                .setRequestBody(Add_Vehicle_Body)
                .setTargetStatusCode(422)
                .performRequest();



    }
}