package Orders;

import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Create_Order_Main {
    private RestActions apiobject;
    private String Create_Order_URL = "v1/companies/1/orders";

    public Create_Order_Main(RestActions apiobject) {
        this.apiobject = apiobject;
    }


    public Response Create_Order_Successfully_English(int info_id,
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
                                                      int media_ids_3) {


        JSONObject Create_Order_Body = new JSONObject();

        JSONArray items_array = new JSONArray();    // Creat array

        JSONObject itms_info_id = new JSONObject();      // Creat info_id (1) JSONObject
        itms_info_id.put("info_id", info_id);
        items_array.add(itms_info_id);

//         JSONObject itms_info_id_2 = new JSONObject();      // Creat info_id (2) JSONObject
//        itms_info_id_2.put("info_id" ,info_id);
//        items_array.add(itms_info_id_2);


        JSONObject items = new JSONObject();
        Create_Order_Body.put("items", items_array);


        Create_Order_Body.put("vehicle_id", vehicle_id);


        JSONObject service_location = new JSONObject();
        service_location.put("type", service_location_Type);
        service_location.put("longitude", service_location_longitude);
        service_location.put("latitude", service_location_latitude);
        Create_Order_Body.put("service_location", service_location);

        JSONObject pickup_details = new JSONObject();
        pickup_details.put("type", pickup_details_type);
        pickup_details.put("longitude", pickup_details_longitude);
        pickup_details.put("latitude", pickup_details_latitude);
        pickup_details.put("hydraulic", hydraulic);
        Create_Order_Body.put("pickup_details", pickup_details);

        Create_Order_Body.put("comment", comment);
        Create_Order_Body.put("order_reference_number", order_reference_number);

        JSONArray media_ids = new JSONArray();
        media_ids.add(media_ids_1);
        media_ids.add(media_ids_2);
        media_ids.add(media_ids_3);
        Create_Order_Body.put("media_ids", media_ids);


        return apiobject.buildNewRequest(Create_Order_URL, RestActions.RequestType.POST)
                .setContentType(ContentType.JSON)
                .addHeader("platform", "@vb~sD~KS#2>]pq")
                .addHeader("Accept", "application/json")
                .addHeader("Accept-Language", "en")
                .setRequestBody(Create_Order_Body)
                .setTargetStatusCode(200)
                .performRequest();
    }


    public Response Create_Order_Successfully_Arabic(int info_id,
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
                                                     int media_ids_3) {


        JSONObject Create_Order_Body = new JSONObject();

        JSONArray items_array = new JSONArray();    // Creat array

        JSONObject itms_info_id = new JSONObject();      // Creat info_id (1) JSONObject
        itms_info_id.put("info_id", info_id);
        items_array.add(itms_info_id);

//         JSONObject itms_info_id_2 = new JSONObject();      // Creat info_id (2) JSONObject
//        itms_info_id_2.put("info_id" ,info_id);
//        items_array.add(itms_info_id_2);


        JSONObject items = new JSONObject();
        Create_Order_Body.put("items", items_array);


        Create_Order_Body.put("vehicle_id", vehicle_id);


        JSONObject service_location = new JSONObject();
        service_location.put("type", service_location_Type);
        service_location.put("longitude", service_location_longitude);
        service_location.put("latitude", service_location_latitude);
        Create_Order_Body.put("service_location", service_location);

        JSONObject pickup_details = new JSONObject();
        pickup_details.put("type", pickup_details_type);
        pickup_details.put("longitude", pickup_details_longitude);
        pickup_details.put("latitude", pickup_details_latitude);
        pickup_details.put("hydraulic", hydraulic);
        Create_Order_Body.put("pickup_details", pickup_details);

        Create_Order_Body.put("comment", comment);
        Create_Order_Body.put("order_reference_number", order_reference_number);

        JSONArray media_ids = new JSONArray();
        media_ids.add(media_ids_1);
        media_ids.add(media_ids_2);
        media_ids.add(media_ids_3);
        Create_Order_Body.put("media_ids", media_ids);


        return apiobject.buildNewRequest(Create_Order_URL, RestActions.RequestType.POST)
                .setContentType(ContentType.JSON)
                .addHeader("platform", "@vb~sD~KS#2>]pq")
                .addHeader("Accept", "application/json")
                .addHeader("Accept-Language", "ar")
                .setRequestBody(Create_Order_Body)
                .setTargetStatusCode(200)
                .performRequest();
    }


    public Response Create_Order_Fail_English(String info_id,
                                              //int items_info_id_2 ,
                                              String vehicle_id,
                                              String service_location_Type,
                                              String service_location_longitude,
                                              String service_location_latitude,
                                              String pickup_details_type,
                                              String pickup_details_longitude,
                                              String pickup_details_latitude,
                                              String pickup_details_hydraulic,
                                              String comment,
                                              String order_reference_number,
                                              String media_ids_1,
                                              String media_ids_2,
                                              String media_ids_3) {


        JSONObject Create_Order_Body = new JSONObject();

        JSONArray items_array = new JSONArray();    // Creat array

        JSONObject itms_info_id = new JSONObject();      // Creat info_id (1) JSONObject
        itms_info_id.put("info_id", info_id);
        items_array.add(itms_info_id);

//         JSONObject itms_info_id_2 = new JSONObject();      // Creat info_id (2) JSONObject
//        itms_info_id_2.put("info_id" ,info_id);
//        items_array.add(itms_info_id_2);


        JSONObject items = new JSONObject();
        Create_Order_Body.put("items", items_array);


        Create_Order_Body.put("vehicle_id", vehicle_id);


        JSONObject service_location = new JSONObject();
        service_location.put("type", service_location_Type);
        service_location.put("longitude", service_location_longitude);
        service_location.put("latitude", service_location_latitude);
        Create_Order_Body.put("service_location", service_location);

        JSONObject pickup_details = new JSONObject();
        pickup_details.put("type", pickup_details_type);
        pickup_details.put("longitude", pickup_details_longitude);
        pickup_details.put("latitude", pickup_details_latitude);
        pickup_details.put("pickup_details_hydraulic", pickup_details_hydraulic);
        Create_Order_Body.put("pickup_details", pickup_details);

        Create_Order_Body.put("comment", comment);
        Create_Order_Body.put("order_reference_number", order_reference_number);

        JSONArray media_ids = new JSONArray();
        media_ids.add(media_ids_1);
        media_ids.add(media_ids_2);
        media_ids.add(media_ids_3);
        Create_Order_Body.put("media_ids", media_ids);


        return apiobject.buildNewRequest(Create_Order_URL, RestActions.RequestType.POST)
                .setContentType(ContentType.JSON)
                .addHeader("platform", "@vb~sD~KS#2>]pq")
                .addHeader("Accept", "application/json")
                .addHeader("Accept-Language", "en")
                .setRequestBody(Create_Order_Body)
                .setTargetStatusCode(422)
                .performRequest();
    }


    public Response Create_Order_Fail_Arabic(String info_id,
                                             //int items_info_id_2 ,
                                             String vehicle_id,
                                             String service_location_Type,
                                             String service_location_longitude,
                                             String service_location_latitude,
                                             String pickup_details_type,
                                             String pickup_details_longitude,
                                             String pickup_details_latitude,
                                             String pickup_details_hydraulic,
                                             String comment,
                                             String order_reference_number,
                                             String media_ids_1,
                                             String media_ids_2,
                                             String media_ids_3) {


        JSONObject Create_Order_Body = new JSONObject();

        JSONArray items_array = new JSONArray();    // Creat array

        JSONObject itms_info_id = new JSONObject();      // Creat info_id (1) JSONObject
        itms_info_id.put("info_id", info_id);
        items_array.add(itms_info_id);

//         JSONObject itms_info_id_2 = new JSONObject();      // Creat info_id (2) JSONObject
//        itms_info_id_2.put("info_id" ,info_id);
//        items_array.add(itms_info_id_2);


        JSONObject items = new JSONObject();
        Create_Order_Body.put("items", items_array);


        Create_Order_Body.put("vehicle_id", vehicle_id);


        JSONObject service_location = new JSONObject();
        service_location.put("type", service_location_Type);
        service_location.put("longitude", service_location_longitude);
        service_location.put("latitude", service_location_latitude);
        Create_Order_Body.put("service_location", service_location);

        JSONObject pickup_details = new JSONObject();
        pickup_details.put("type", pickup_details_type);
        pickup_details.put("longitude", pickup_details_longitude);
        pickup_details.put("latitude", pickup_details_latitude);
        // pickup_details.put("pickup_details_hydraulic" ,pickup_details_hydraulic);
        Create_Order_Body.put("pickup_details", pickup_details);

        Create_Order_Body.put("comment", comment);
        Create_Order_Body.put("order_reference_number", order_reference_number);

        JSONArray media_ids = new JSONArray();
        media_ids.add(media_ids_1);
        media_ids.add(media_ids_2);
        media_ids.add(media_ids_3);
        Create_Order_Body.put("media_ids", media_ids);


        return apiobject.buildNewRequest(Create_Order_URL, RestActions.RequestType.POST)
                .setContentType(ContentType.JSON)
                .addHeader("platform", "@vb~sD~KS#2>]pq")
                .addHeader("Accept", "application/json")
                .addHeader("Accept-Language", "ar")
                .setRequestBody(Create_Order_Body)
                .setTargetStatusCode(422)
                .performRequest();
    }

}
