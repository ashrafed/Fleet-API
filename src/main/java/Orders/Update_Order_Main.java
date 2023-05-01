package Orders;

import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Update_Order_Main {
    private RestActions apiobject;
    private String Update_Order_URL = "v1/companies/1/orders/516371";

    public Update_Order_Main(RestActions apiobject){
        this.apiobject = apiobject;
    }



    public Response Update_Order_Successfully_English(int info_id ,
                                                      //int items_info_id_2 ,
                                                      int vehicle_id,
                                                      int service_location_Type,
                                                      String service_location_longitude,
                                                      String service_location_latitude,
                                                      int pickup_details_type,
                                                      String pickup_details_longitude,
                                                      String pickup_details_latitude,
                                                      String hydraulic,
                                                      String comment,
                                                      String order_reference_number,
                                                      int media_ids_1,
                                                      int media_ids_2,
                                                      int media_ids_3){


        JSONObject Update_Order_Body = new JSONObject();

        JSONArray items_array = new JSONArray();    // Creat array

        JSONObject itms_info_id = new JSONObject();      // Creat info_id (1) JSONObject
        itms_info_id.put("info_id" ,info_id);
        items_array.add(itms_info_id);

//         JSONObject itms_info_id_2 = new JSONObject();      // Creat info_id (2) JSONObject
//        itms_info_id_2.put("info_id" ,info_id);
//        items_array.add(itms_info_id_2);


        JSONObject items = new JSONObject();
        Update_Order_Body.put("items" , items_array);


        Update_Order_Body.put("vehicle_id" ,vehicle_id);


        JSONObject service_location = new JSONObject();
        service_location.put("type" , service_location_Type);
        service_location.put("longitude" ,service_location_longitude);
        service_location.put("latitude" ,service_location_latitude);
        Update_Order_Body.put("service_location" ,service_location);

        JSONObject pickup_details = new JSONObject();
        pickup_details.put("type" ,pickup_details_type);
        pickup_details.put("longitude" ,pickup_details_longitude);
        pickup_details.put("latitude" ,pickup_details_latitude);
        pickup_details.put("hydraulic" ,hydraulic);
        Update_Order_Body.put("pickup_details" ,pickup_details);

        Update_Order_Body.put("comment" ,comment);
        Update_Order_Body.put("order_reference_number" ,order_reference_number);

        JSONArray  media_ids = new JSONArray();
        media_ids.add(media_ids_1);
        media_ids.add(media_ids_2);
        media_ids.add(media_ids_3);
        Update_Order_Body.put("media_ids" ,media_ids);


        return apiobject.buildNewRequest(Update_Order_URL , RestActions.RequestType.PUT)
                .setContentType(ContentType.JSON)
                .addHeader("platform","@vb~sD~KS#2>]pq")
                .addHeader("Accept","application/json")
                .addHeader("Accept-Language","en")
                .setRequestBody(Update_Order_Body)
                .setTargetStatusCode(200)
                .performRequest();
    }



}
