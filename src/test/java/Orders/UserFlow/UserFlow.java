//package Orders.UserFlow;
//
//
//import Authantication.Login_Main;
//import Drivers.*;
//import MainPackage.DataGenerator.AuthanticationDataGenerator;
//import MainPackage.DataGenerator.DriversDataGenerator;
//import MainPackage.DataGenerator.VinNumberGenerator;
//import MainPackage.Main_Variables;
//import Orders.Create_Order_Main;
//import Orders.List_Orders_Main;
//import Vehicles.Create_Vehicle_Main;
//import com.shaft.api.RestActions;
//import com.shaft.driver.DriverFactory;
//import com.shaft.validation.Validations;
//import io.restassured.http.ContentType;
//import io.restassured.response.Response;
//import org.json.simple.JSONObject;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//public class UserFlow {
//
//    private RestActions apiobject;
//
//    private Main_Variables main_variables;
//    private DriversDataGenerator driversDataGenerator;
//    private Login_Main login_main;
//    private AuthanticationDataGenerator authanticationDataGenerator;
//    private Create_Driver_Main create_driver_main;
//    private Edit_Driver_Info_Main edit_driver_info_main;
//
//    private Get_Drivers_Details_Main get_drivers_details_main;
//
//    private List_Drivers_Main list_drivers_main;
//
//    private Update_Driver_Status_Main update_driver_status_main;
//
//    private Create_Vehicle_Main create_vehicle_main;
//
//    private Create_Order_Main create_order_main;
//
//    private List_Orders_Main list_orders_main;
//
//    @BeforeClass
//    public void BeforeClass() {
//        apiobject = DriverFactory.getAPIDriver(Login_Main.Base_Url);
//
//        main_variables = new Main_Variables(apiobject);
//        authanticationDataGenerator = new AuthanticationDataGenerator(apiobject);
//
//        driversDataGenerator = new DriversDataGenerator(apiobject);
//        login_main = new Login_Main(apiobject);
//        create_driver_main = new Create_Driver_Main(apiobject);
//        edit_driver_info_main = new Edit_Driver_Info_Main(apiobject);
//
//        get_drivers_details_main =new Get_Drivers_Details_Main(apiobject);
//
//        list_drivers_main = new List_Drivers_Main(apiobject);
//        update_driver_status_main = new Update_Driver_Status_Main(apiobject);
//
//
//        create_vehicle_main = new Create_Vehicle_Main(apiobject);
//
//        create_order_main = new Create_Order_Main(apiobject);
//        list_orders_main = new List_Orders_Main(apiobject);
//
//        // Login_Test :
//        login_main.Login_Successfully_Arabic(
//                authanticationDataGenerator.Email_Address_1,
//                authanticationDataGenerator.Password);
//
//
//    }
//    @Test
//    public void TC_01_Login_AddVehicle_createOrder_GetOrderDetails_GetOrderStatusDetails_CancelOrder_Arabic(){
//
//        // Add Vehicle :
//        Response Add_Vehicle_Response = create_vehicle_main.Add_Vehicle_successfully_Arabic(
//                350,
//                VinNumberGenerator.Vin_Number_Generator,
//                "6",
//                "1",
//                "1",
//                "2022",
//                "4",
//                "435",
//                "1",
//                "3",
//                "5");
//        Validations.assertThat().response(Add_Vehicle_Response)
//                .extractedJsonValue("message")
//                .equals("تم حفظ السيارة بنجاح");
//
//        int Vehicle_id = Add_Vehicle_Response.jsonPath().get("data.id");
//
//
//        // Create Order:
//        Response Create_Order_Response = create_order_main.Create_Order_Successfully_Arabic(
//                3,
//                Vehicle_id,
//                2,
//                "21.263781",
//                "46.351390",
//                1,
//                "21.263781",
//                "46.351390",
//                "2",
//                "test Automation",
//                "CAR-44242",
//                1,
//                2,
//                3);
//
//        Validations.assertThat().response(Create_Order_Response)
//                .extractedJsonValue("message")
//                .equals("تم حفظ الطلب بنجاح");
//
//        String Order_id = Create_Order_Response.jsonPath().get("data.order_no");
//
//        // Get Order Details :
//
//        String Get_Order_Details_URL = "v1/companies/1/orders/"+Order_id;
//
//        Response List_Orders_Response = apiobject.buildNewRequest(Get_Order_Details_URL , RestActions.RequestType.GET)
//                .setContentType(ContentType.JSON)
//                .addHeader("platform","@vb~sD~KS#2>]pq")
//                .addHeader("Accept","application/json")
//                .addHeader("Accept-Language","ar")
//                .performRequest();
//
//        Validations.assertThat().response(List_Orders_Response)
//                .extractedJsonValue("message")
//                .equals("تم إسترجاع بيانات الطلب");
//
//        // Get order status details :
//
//        String Get_order_status_details_URL = "v1/companies/1/orders/"+Order_id+"/status/details";
//
//        Response Get_order_status_details_Response = apiobject.buildNewRequest( Get_order_status_details_URL, RestActions.RequestType.GET)
//                .setContentType(ContentType.JSON)
//                .addHeader("platform","@vb~sD~KS#2>]pq")
//                .addHeader("Accept","application/json")
//                .addHeader("Accept-Language","ar")
//                .performRequest();
//
//        Validations.assertThat().response(Get_order_status_details_Response)
//                .extractedJsonValue("message")
//                .equals("تم استرجاع تفاصيل الحالات بنجاح");
//
//        // Cancel Order:
//        String Cancel_Order_url = "v1/companies/1/orders/"+Order_id;
//
//        JSONObject Cancel_Order_Body = new JSONObject();
//
//        Cancel_Order_Body.put("reason_id", "13");
//        Cancel_Order_Body.put("comment", "Test Automation");
//        Response Cancel_Order_Response =apiobject.buildNewRequest(Cancel_Order_url, RestActions.RequestType.DELETE)
//                .setContentType(ContentType.JSON)
//                .addHeader("Accept","application/json")
//                .addHeader("Platform","@vb~sD~KS#2>]pq")
//                .addHeader("Accept-Language","ar")
//                .setRequestBody(Cancel_Order_Body)
//                .performRequest();
//
//        Validations.assertThat().response(Cancel_Order_Response)
//                .extractedJsonValue("message")
//                .equals("تم إلغاء الطلب بنجاح");
//    }
//    @Test
//    public void TC_01_Login_AddVehicle_createOrder_GetOrderDetails_GetOrderStatusDetails_CancelOrder_English(){
//
//        // Add Vehicle :
//        Response Add_Vehicle_Response = create_vehicle_main.Add_Vehicle_successfully_English(
//                350,
//                VinNumberGenerator.Vin_Number_Generator_1,
//                "6",
//                "1",
//                "1",
//                "2022",
//                "4",
//                "435",
//                "1",
//                "3",
//                "5");
//        Validations.assertThat().response(Add_Vehicle_Response)
//                .extractedJsonValue("message")
//                .equals("Vehicle stored successfully");
//
//        int Vehicle_id = Add_Vehicle_Response.jsonPath().get("data.id");
//
//
//        // Create Order:
//        Response Create_Order_Response = create_order_main.Create_Order_Successfully_English(
//                3,
//                Vehicle_id,
//                2,
//                "21.263781",
//                "46.351390",
//                1,
//                "21.263781",
//                "46.351390",
//                "2",
//                "test Automation",
//                "CAR-44242",
//                1,
//                2,
//                3);
//
//        Validations.assertThat().response(Create_Order_Response)
//                .extractedJsonValue("message")
//                .equals("Order stored successfully");
//
//        String Order_id = Create_Order_Response.jsonPath().get("data.order_no");
//
//        // Get Order Details :
//
//        String Get_Order_Details_URL = "v1/companies/1/orders/"+Order_id;
//
//        Response List_Orders_Response = apiobject.buildNewRequest(Get_Order_Details_URL , RestActions.RequestType.GET)
//                .setContentType(ContentType.JSON)
//                .addHeader("platform","@vb~sD~KS#2>]pq")
//                .addHeader("Accept","application/json")
//                .addHeader("Accept-Language","en")
//                .performRequest();
//
//        Validations.assertThat().response(List_Orders_Response)
//                .extractedJsonValue("message")
//                .equals("Order information retrieved successfully");
//
//        // Get order status details :
//
//        String Get_order_status_details_URL = "v1/companies/1/orders/"+Order_id+"/status/details";
//
//        Response Get_order_status_details_Response = apiobject.buildNewRequest( Get_order_status_details_URL, RestActions.RequestType.GET)
//                .setContentType(ContentType.JSON)
//                .addHeader("platform","@vb~sD~KS#2>]pq")
//                .addHeader("Accept","application/json")
//                .addHeader("Accept-Language","en")
//                .performRequest();
//
//        Validations.assertThat().response(Get_order_status_details_Response)
//                .extractedJsonValue("message")
//                .equals("Order status details retrieved successfully");
//
//        // Cancel Order:
//        String Cancel_Order_url = "v1/companies/1/orders/"+Order_id;
//
//        JSONObject Cancel_Order_Body = new JSONObject();
//
//        Cancel_Order_Body.put("reason_id", "13");
//        Cancel_Order_Body.put("comment", "Test Automation");
//        Response Cancel_Order_Response =apiobject.buildNewRequest(Cancel_Order_url, RestActions.RequestType.DELETE)
//                .setContentType(ContentType.JSON)
//                .addHeader("Accept","application/json")
//                .addHeader("Platform","@vb~sD~KS#2>]pq")
//                .addHeader("Accept-Language","en")
//                .setRequestBody(Cancel_Order_Body)
//                .performRequest();
//
//        Validations.assertThat().response(Cancel_Order_Response)
//                .extractedJsonValue("message")
//                .equals("Order cancelled successfully");
//    }
//    @Test
//    public void TC_02_Login_AddVehicle_createOrder_createcomplaint_Getcomplaint_Arabic(){
//
//
//        // Add Vehicle :
//        Response Add_Vehicle_Response = create_vehicle_main.Add_Vehicle_successfully_Arabic(
//                350,
//                VinNumberGenerator.Vin_Number_Generator_2,
//                "6",
//                "1",
//                "1",
//                "2022",
//                "4",
//                "435",
//                "1",
//                "3",
//                "5");
//
//        Validations.assertThat().response(Add_Vehicle_Response)
//                .extractedJsonValue("message")
//                .equals("تم حفظ السيارة بنجاح");
//
//        int Vehicle_id = Add_Vehicle_Response.jsonPath().get("data.id");
//
//        // Create Order:
//        Response Create_Order_Response = create_order_main.Create_Order_Successfully_Arabic(
//                3,
//                Vehicle_id,
//                2,
//                "21.263781",
//                "46.351390",
//                1,
//                "21.263781",
//                "46.351390",
//                "2",
//                "test Automation",
//                "CAR-44242",
//                1,
//                2,
//                3);
//
//        Validations.assertThat().response(Create_Order_Response)
//                .extractedJsonValue("message")
//                .equals("تم حفظ الطلب بنجاح");
//
//        String Order_id = Create_Order_Response.jsonPath().get("data.order_no");
//
//        // Create order complaint :
//        String Create_order_complaint_url = "v1/companies/1/orders/"+Order_id+"/complaints";
//
//        JSONObject Create_order_complaint_Body = new  JSONObject();
//        Create_order_complaint_Body.put("description","Test automation");
//        Response Create_Order_Response_2 =apiobject.buildNewRequest(Create_order_complaint_url, RestActions.RequestType.POST)
//                .setContentType(ContentType.JSON)
//                .addHeader("Accept","application/json")
//                .addHeader("Platform","@vb~sD~KS#2>]pq")
//                .addHeader("Accept-Language","ar")
//                .setRequestBody(Create_order_complaint_Body)
//                .performRequest();
//
//        Validations.assertThat().response(Create_Order_Response_2)
//                .extractedJsonValue("message")
//                .equals("تم عمل شكوي");
//
//        // get order complaints:
//        String Get_order_complaint_url = "v1/companies/1/orders/"+Order_id+"/complaints";
//
//        Response Get_Order_Response =apiobject.buildNewRequest(Get_order_complaint_url, RestActions.RequestType.GET)
//                .setContentType(ContentType.JSON)
//                .addHeader("Accept","application/json")
//                .addHeader("Platform","@vb~sD~KS#2>]pq")
//                .addHeader("Accept-Language","ar")
//                .performRequest();
//
//        Validations.assertThat().response(Get_Order_Response)
//                .extractedJsonValue("message")
//                .equals("تم استرجاع الشكاوي بنجاح");
//    }
//    @Test
//    public void TC_02_Login_AddVehicle_createOrder_createcomplaint_Getcomplaint_English(){
//
//
//        // Add Vehicle :
//        Response Add_Vehicle_Response = create_vehicle_main.Add_Vehicle_successfully_English(
//                350,
//                VinNumberGenerator.Vin_Number_Generator_3,
//                "6",
//                "1",
//                "1",
//                "2022",
//                "4",
//                "435",
//                "1",
//                "3",
//                "5");
//
//        Validations.assertThat().response(Add_Vehicle_Response)
//                .extractedJsonValue("message")
//                .equals("Vehicle stored successfully");
//
//        int Vehicle_id = Add_Vehicle_Response.jsonPath().get("data.id");
//
//        // Create Order:
//        Response Create_Order_Response = create_order_main.Create_Order_Successfully_English(
//                3,
//                Vehicle_id,
//                2,
//                "21.263781",
//                "46.351390",
//                1,
//                "21.263781",
//                "46.351390",
//                "2",
//                "test Automation",
//                "CAR-44242",
//                1,
//                2,
//                3);
//
//        Validations.assertThat().response(Create_Order_Response)
//                .extractedJsonValue("message")
//                .equals("Order stored successfully");
//
//        String Order_id = Create_Order_Response.jsonPath().get("data.order_no");
//
//        // Create order complaint :
//        String Create_order_complaint_url = "v1/companies/1/orders/"+Order_id+"/complaints";
//
//        JSONObject Create_order_complaint_Body = new  JSONObject();
//        Create_order_complaint_Body.put("description","Test automation");
//        Response Create_Order_Response_2 =apiobject.buildNewRequest(Create_order_complaint_url, RestActions.RequestType.POST)
//                .setContentType(ContentType.JSON)
//                .addHeader("Accept","application/json")
//                .addHeader("Platform","@vb~sD~KS#2>]pq")
//                .addHeader("Accept-Language","en")
//                .setRequestBody(Create_order_complaint_Body)
//                .performRequest();
//
//        Validations.assertThat().response(Create_Order_Response_2)
//                .extractedJsonValue("message")
//                .equals("Complain t Created");
//
//        // get order complaints:
//        String Get_order_complaint_url = "v1/companies/1/orders/"+Order_id+"/complaints";
//
//        Response Get_Order_Response =apiobject.buildNewRequest(Get_order_complaint_url, RestActions.RequestType.GET)
//                .setContentType(ContentType.JSON)
//                .addHeader("Accept","application/json")
//                .addHeader("Platform","@vb~sD~KS#2>]pq")
//                .addHeader("Accept-Language","en")
//                .performRequest();
//
//        Validations.assertThat().response(Get_Order_Response)
//                .extractedJsonValue("message")
//                .equals("Complaints retrieved successfully");
//    }
//    @Test
//    public void TC_03_Login_AddVehicle_CancelOrder_Arabic(){
//
//        // Add Vehicle :
//        Response Add_Vehicle_Response = create_vehicle_main.Add_Vehicle_successfully_Arabic(
//                350,
//                VinNumberGenerator.Vin_Number_Generator_4,
//                "6",
//                "1",
//                "1",
//                "2022",
//                "4",
//                "435",
//                "1",
//                "3",
//                "5");
//        int Vehicle_id = Add_Vehicle_Response.jsonPath().get("data.id");
//
//        // Cancel Order:
//        String Cancel_Order_url = "v1/companies/1/orders/0000";
//
//        JSONObject Cancel_Order_Body = new JSONObject();
//
//        Cancel_Order_Body.put("reason_id", "13");
//        Cancel_Order_Body.put("comment", "Test Automation");
//        Response Cancel_Order_Response =apiobject.buildNewRequest(Cancel_Order_url, RestActions.RequestType.DELETE)
//                .setContentType(ContentType.JSON)
//                .addHeader("Accept","application/json")
//                .addHeader("Platform","@vb~sD~KS#2>]pq")
//                .addHeader("Accept-Language","ar")
//                .setTargetStatusCode(404)
//                .setRequestBody(Cancel_Order_Body)
//                .performRequest();
//        Validations.assertThat().response(Cancel_Order_Response)
//                .extractedJsonValue("message")
//                .equals("لم يتم العثور على الطلب");
//    }
//    @Test
//    public void TC_03_Login_AddVehicle_CancelOrder_English(){
//
//
//        // Add Vehicle :
//        Response Add_Vehicle_Response = create_vehicle_main.Add_Vehicle_successfully_Arabic(
//                350,
//                VinNumberGenerator.Vin_Number_Generator_5,
//                "6",
//                "1",
//                "1",
//                "2022",
//                "4",
//                "435",
//                "1",
//                "3",
//                "5");
//        int Vehicle_id = Add_Vehicle_Response.jsonPath().get("data.id");
//
//        // Cancel Order:
//        String Cancel_Order_url = "v1/companies/1/orders/0000";
//
//        JSONObject Cancel_Order_Body = new JSONObject();
//
//        Cancel_Order_Body.put("reason_id", "13");
//        Cancel_Order_Body.put("comment", "Test Automation");
//        Response Cancel_Order_Response =apiobject.buildNewRequest(Cancel_Order_url, RestActions.RequestType.DELETE)
//                .setContentType(ContentType.JSON)
//                .addHeader("Accept","application/json")
//                .addHeader("Platform","@vb~sD~KS#2>]pq")
//                .addHeader("Accept-Language","en")
//                .setTargetStatusCode(404)
//                .setRequestBody(Cancel_Order_Body)
//                .performRequest();
//        Validations.assertThat().response(Cancel_Order_Response)
//                .extractedJsonValue("message")
//                .equals("Order not found");
//    }
//    @Test
//    public void TC_04_Login_AddVehicle_createOrder_CancelOrder_Arabic() {
//
//        // Add Vehicle :
//        Response Add_Vehicle_Response = create_vehicle_main.Add_Vehicle_successfully_Arabic(
//                350,
//                VinNumberGenerator.Vin_Number_Generator_6,
//                "6",
//                "1",
//                "1",
//                "2022",
//                "4",
//                "435",
//                "1",
//                "3",
//                "5");
//        Validations.assertThat().response(Add_Vehicle_Response)
//                .extractedJsonValue("message")
//                .equals("تم حفظ السيارة بنجاح");
//
//        int Vehicle_id = Add_Vehicle_Response.jsonPath().get("data.id");
//
//        // Create Order:
//        Response Create_Order_Response = create_order_main.Create_Order_Successfully_Arabic(
//                3,
//                Vehicle_id,
//                2,
//                "21.263781",
//                "46.351390",
//                1,
//                "21.263781",
//                "46.351390",
//                "2",
//                "test Automation",
//                "CAR-44242",
//                1,
//                2,
//                3);
//
//        Validations.assertThat().response(Create_Order_Response)
//                .extractedJsonValue("message")
//                .equals("تم حفظ الطلب بنجاح");
//
//        String Order_id = Create_Order_Response.jsonPath().get("data.order_no");
//
//        // Cancel Order:
//        String Cancel_Order_url = "v1/companies/1/orders/"+Order_id;
//
//        JSONObject Cancel_Order_Body = new JSONObject();
//
//        Cancel_Order_Body.put("reason_id", "13");
//        Cancel_Order_Body.put("comment", "Test Automation");
//        Response Cancel_Order_Response =apiobject.buildNewRequest(Cancel_Order_url, RestActions.RequestType.DELETE)
//                .setContentType(ContentType.JSON)
//                .addHeader("Accept","application/json")
//                .addHeader("Platform","@vb~sD~KS#2>]pq")
//                .addHeader("Accept-Language","ar")
//                .setRequestBody(Cancel_Order_Body)
//                .performRequest();
//
//        Validations.assertThat().response(Cancel_Order_Response)
//                .extractedJsonValue("message")
//                .equals("تم إلغاء الطلب بنجاح");
//    }
//    @Test
//    public void TC_04_Login_AddVehicle_createOrder_CancelOrder_English() {
//
//        // Add Vehicle :
//        Response Add_Vehicle_Response = create_vehicle_main.Add_Vehicle_successfully_English(
//                350,
//                VinNumberGenerator.Vin_Number_Generator_7,
//                "6",
//                "1",
//                "1",
//                "2022",
//                "4",
//                "435",
//                "1",
//                "3",
//                "5");
//        Validations.assertThat().response(Add_Vehicle_Response)
//                .extractedJsonValue("message")
//                .equals("Vehicle stored successfully");
//
//        int Vehicle_id = Add_Vehicle_Response.jsonPath().get("data.id");
//
//        // Create Order:
//        Response Create_Order_Response = create_order_main.Create_Order_Successfully_English(
//                3,
//                Vehicle_id,
//                2,
//                "21.263781",
//                "46.351390",
//                1,
//                "21.263781",
//                "46.351390",
//                "2",
//                "test Automation",
//                "CAR-44242",
//                1,
//                2,
//                3);
//
//        Validations.assertThat().response(Create_Order_Response)
//                .extractedJsonValue("message")
//                .equals("Order stored successfully");
//
//        String Order_id = Create_Order_Response.jsonPath().get("data.order_no");
//
//        // Cancel Order:
//        String Cancel_Order_url = "v1/companies/1/orders/" + Order_id;
//
//        JSONObject Cancel_Order_Body = new JSONObject();
//
//        Cancel_Order_Body.put("reason_id", "13");
//        Cancel_Order_Body.put("comment", "Test Automation");
//        Response Cancel_Order_Response = apiobject.buildNewRequest(Cancel_Order_url, RestActions.RequestType.DELETE)
//                .setContentType(ContentType.JSON)
//                .addHeader("Accept", "application/json")
//                .addHeader("Platform", "@vb~sD~KS#2>]pq")
//                .addHeader("Accept-Language", "en")
//                .setRequestBody(Cancel_Order_Body)
//                .performRequest();
//
//        Validations.assertThat().response(Cancel_Order_Response)
//                .extractedJsonValue("message")
//                .equals("Order cancelled successfully");
//    }
//    @Test
//    public void TC_05_Login_AddVehicle_Create_complaint_Arabic() {
//
//        // Add Vehicle :
//        Response Add_Vehicle_Response = create_vehicle_main.Add_Vehicle_successfully_Arabic(
//                350,
//                VinNumberGenerator.Vin_Number_Generator_8,
//                "6",
//                "1",
//                "1",
//                "2022",
//                "4",
//                "435",
//                "1",
//                "3",
//                "5");
//        Validations.assertThat().response(Add_Vehicle_Response)
//                .extractedJsonValue("message")
//                .equals("تم حفظ السيارة بنجاح");
//
//        int Vehicle_id = Add_Vehicle_Response.jsonPath().get("data.id");
//        // Create order complaint :
//        String Create_order_complaint_url = "v1/companies/1/orders/number/complaints";
//
//        JSONObject Create_order_complaint_Body = new JSONObject();
//        Create_order_complaint_Body.put("description", "Test automation");
//        Response Create_Order_Response_2 = apiobject.buildNewRequest(Create_order_complaint_url, RestActions.RequestType.POST)
//                .setContentType(ContentType.JSON)
//                .addHeader("Accept", "application/json")
//                .addHeader("Platform", "@vb~sD~KS#2>]pq")
//                .addHeader("Accept-Language", "ar")
//                .setTargetStatusCode(404)
//                .setRequestBody(Create_order_complaint_Body)
//                .performRequest();
//
//        Validations.assertThat().response(Create_Order_Response_2)
//                .extractedJsonValue("message")
//                .equals("لم يتم العثور على الطلب");
//    }
//    @Test
//    public void TC_05_Login_AddVehicle_Create_complaint_English() {
//        // Add Vehicle :
//        Response Add_Vehicle_Response = create_vehicle_main.Add_Vehicle_successfully_English(
//                350,
//                VinNumberGenerator.Vin_Number_Generator_9,
//                "6",
//                "1",
//                "1",
//                "2022",
//                "4",
//                "435",
//                "1",
//                "3",
//                "5");
//        Validations.assertThat().response(Add_Vehicle_Response)
//                .extractedJsonValue("message")
//                .equals("Vehicle stored successfully");
//
//        int Vehicle_id = Add_Vehicle_Response.jsonPath().get("data.id");
//        // Create order complaint :
//        String Create_order_complaint_url = "v1/companies/1/orders/number/complaints";
//
//        JSONObject Create_order_complaint_Body = new  JSONObject();
//        Create_order_complaint_Body.put("description","Test automation");
//        Response Create_Order_Response_2 =apiobject.buildNewRequest(Create_order_complaint_url, RestActions.RequestType.POST)
//                .setContentType(ContentType.JSON)
//                .addHeader("Accept","application/json")
//                .addHeader("Platform","@vb~sD~KS#2>]pq")
//                .addHeader("Accept-Language","en")
//                .setRequestBody(Create_order_complaint_Body)
//                .setTargetStatusCode(404)
//                .performRequest();
//
//        Validations.assertThat().response(Create_Order_Response_2)
//                .extractedJsonValue("message")
//                .equals("Order not found");
//    }
//
//}
