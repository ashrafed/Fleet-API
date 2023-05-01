package User_Flow;

import Authantication.Login_Main;
import Company.Create_Company_Main;
import Constants.Get_Constants;
import Constants.Get_Constants_Main;
import Drivers.Create_Driver_Main;
import MainPackage.DataGenerator.AuthanticationDataGenerator;
import MainPackage.DataGenerator.CompanyDataGenerator;
import MainPackage.DataGenerator.DriversDataGenerator;
import MainPackage.DataGenerator.VinNumberGenerator;
import MainPackage.Main_Variables;
import Orders.Create_Order_Main;
import Vehicles.Create_Vehicle_Main;
import Vehicles.Get_Vehicle_Details_Main;
import Vehicles.Update_Vehicle_Info_Main;
import com.shaft.api.RestActions;
import com.shaft.driver.DriverFactory;
import com.shaft.validation.Validations;
import io.qameta.allure.Link;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static Authantication.Login_Main.Platform;

public class TCF_04_Login_CreateVehicle_CreateOrder_CreateDriver_UpdateVehicleInfo_GetVehicleOrders {

    private RestActions apiobject;
    private Main_Variables main_variables;
    private DriversDataGenerator driversDataGenerator;
    private AuthanticationDataGenerator authanticationDataGenerator;
    private Login_Main login_main;
    private Get_Constants_Main get_constants_main;
    private Create_Vehicle_Main create_vehicle_main;
    private Create_Order_Main create_order_main;
    private Create_Driver_Main create_driver_main;
    private Update_Vehicle_Info_Main update_vehicle_info_main;
    private Get_Vehicle_Details_Main get_vehicle_details_main;
    private Create_Company_Main create_company_main;

    private CompanyDataGenerator companyDataGenerator;
    @BeforeClass
    public void Beforeclass() {
        apiobject = DriverFactory.getAPIDriver(Login_Main.Base_Url);
        main_variables = new Main_Variables(apiobject);
        authanticationDataGenerator = new AuthanticationDataGenerator(apiobject);
        driversDataGenerator = new DriversDataGenerator(apiobject);
        login_main = new Login_Main(apiobject);
        get_constants_main =new Get_Constants_Main(apiobject);

        create_company_main = new Create_Company_Main(apiobject);
        companyDataGenerator = new CompanyDataGenerator(apiobject);


        create_vehicle_main = new Create_Vehicle_Main(apiobject);
        create_order_main = new Create_Order_Main(apiobject);
        create_driver_main = new Create_Driver_Main(apiobject);
        update_vehicle_info_main = new Update_Vehicle_Info_Main(apiobject);
        get_vehicle_details_main = new Get_Vehicle_Details_Main(apiobject);

        // Login_Test :
        login_main.Login_Successfully_Arabic(
                authanticationDataGenerator.Email_Address_1,
                authanticationDataGenerator.Password);
    }

    @Test
    @Link(Main_Variables.MIND_MAP_LINK)
    public void TCF_04_Login_CreateVehicle_CreateOrder_CreateDriver_UpdateVehicleInfo_GetVehicleOrders(){

        //********** ################################ **************** :
        // **************** Create_Company   **************** :

        Response Create_Company_Successfully_Response_English = create_company_main.Create_Company_Successfully_English(
                companyDataGenerator.Company_Name,
                companyDataGenerator.contact_number,
                companyDataGenerator.Email_Address_1,
                companyDataGenerator.commercial_number,
                companyDataGenerator.additional_amount,
                companyDataGenerator.admin_name,
                "123456");

        int Company_ID = Create_Company_Successfully_Response_English.jsonPath().get("data.id");  // Get Company ID :


        // ************** Create Driver  *************************** :

        String Create_Driver_URL = "v1/companies/" + Company_ID + "/drivers";      //Create Driver URL :

        JSONObject Create_Driver_Body = new JSONObject();
        Create_Driver_Body.put("name", driversDataGenerator.Driver_Name_Generator);
        Create_Driver_Body.put("mobile", driversDataGenerator.Register_PhoneNumber_Generattor);
        Create_Driver_Body.put("residence_number", driversDataGenerator.Residence_Number_Generattor);
        Create_Driver_Body.put("job", driversDataGenerator.job_Data_Generattor);
        Create_Driver_Body.put("license_expire_date", driversDataGenerator.license_expire_date);
        Create_Driver_Body.put("city_id", driversDataGenerator.City_Id_Generattor);
        Create_Driver_Body.put("area_id", "12");


        Response create_driver_Response = apiobject.buildNewRequest(Create_Driver_URL, RestActions.RequestType.POST)
                .setContentType(ContentType.JSON)
                .addHeader("Platform", Platform)
                .addHeader("Accept", "application/json")
                .addHeader("Accept-Language", "en")
                .setRequestBody(Create_Driver_Body)
                .setTargetStatusCode(201)
                .performRequest();


        int Driver_ID = create_driver_Response.jsonPath().get("data.user_id");  // Get Driver ID .


        //******** Get_Constants  ************** :

        Response get_constants_response = get_constants_main.Get_Constants_Successfully_English();
        int Engine_Type_ID = get_constants_response.jsonPath().get("data.engine_types[0].id");
        int Modele_ID = get_constants_response.jsonPath().get("data.models[0].id");
        int Brand_ID = get_constants_response.jsonPath().get("data.brands[0].id");
        int Color_ID = get_constants_response.jsonPath().get("data.colors[0].id");
        int Model_Year_ID = get_constants_response.jsonPath().get("data.models[0].id");
        int Plate_Letter_ID = get_constants_response.jsonPath().get("data.plate_letters[0].id");


        // ****************  Add_Vehicle  ******************* :
        // ****************  Add_Vehicle  English ******************* :


        String Create_Vehicle_URL = "v1/companies/" + Company_ID + "/vehicles";   // Create Vehicle URL .

        JSONObject Add_Vehicle_Body = new JSONObject();
        Add_Vehicle_Body.put("driver_id", Driver_ID);
        Add_Vehicle_Body.put("vehicle_vin_number", VinNumberGenerator.Vin_Number_Generator_6);
        Add_Vehicle_Body.put("model_id", Modele_ID);
        Add_Vehicle_Body.put("brand_id", Brand_ID);
        Add_Vehicle_Body.put("color_id", Color_ID);
        Add_Vehicle_Body.put("model_year", Model_Year_ID);
        Add_Vehicle_Body.put("engine_type_id", Engine_Type_ID);
        Add_Vehicle_Body.put("plate_number", "435");
        Add_Vehicle_Body.put("plate_letter_1_id", 1);
        Add_Vehicle_Body.put("plate_letter_2_id", 2);
        Add_Vehicle_Body.put("plate_letter_3_id", 3);

        Response Add_Vehicle_Response_English = apiobject.buildNewRequest(Create_Vehicle_URL, RestActions.RequestType.POST)
                .setContentType(ContentType.JSON)
                .addHeader("Platform", Platform)
                .addHeader("Accept", "application/json")
                .addHeader("Accept-Language", "en")
                .setRequestBody(Add_Vehicle_Body)
                .setTargetStatusCode(201)
                .performRequest();

        Validations.assertThat().response(Add_Vehicle_Response_English)
                .extractedJsonValue("message")
                .contains("Vehicle stored successfully")
                .perform();


        int ID_Of_Vehicle = Add_Vehicle_Response_English.jsonPath().get("data.id");  //  Get Vehicle ID



        // **************** Create Order :  ******************* :

        String Create_Order_URL = "v1/companies/" + Company_ID + "/orders";   //  //  Create_Order_URL


        JSONObject Create_Order_Body = new JSONObject();

        JSONArray items_array = new JSONArray();

        JSONObject itms_info_id = new JSONObject();
        itms_info_id.put("info_id", 47);    // info_id
        items_array.add(itms_info_id);


        JSONObject items = new JSONObject();
        Create_Order_Body.put("items", items_array);


        Create_Order_Body.put("vehicle_id", ID_Of_Vehicle); // vehicle_id


        JSONObject service_location = new JSONObject();
        service_location.put("type", 2);    //  service_location Type
        service_location.put("longitude", "21.263781");  //  service_location  longitude
        service_location.put("latitude", "46.351390");  //  service_location latitude
        Create_Order_Body.put("service_location", service_location);

        JSONObject pickup_details = new JSONObject();
        pickup_details.put("type", 1);    //  pickup_details Type
        pickup_details.put("longitude", "21.263781");   //  pickup_details longitude
        pickup_details.put("latitude", "46.351390");   //  pickup_details  latitude
        pickup_details.put("hydraulic", 2);    // ickup_details  hydraulic
        Create_Order_Body.put("pickup_details", pickup_details);

        Create_Order_Body.put("comment", "Test Automation");   // comment
        Create_Order_Body.put("order_reference_number", "CAR-44242");  // Order_Reference_number

        JSONArray media_ids = new JSONArray();
        media_ids.add(1);
        media_ids.add(2);
        media_ids.add(3);
        Create_Order_Body.put("media_ids", media_ids);



        Response Create_Order_Response  = apiobject.buildNewRequest(Create_Order_URL, RestActions.RequestType.POST)
                .setContentType(ContentType.JSON)
                .addHeader("platform", "@vb~sD~KS#2>]pq")
                .addHeader("Accept", "application/json")
                .addHeader("Accept-Language", "en")
                .setRequestBody(Create_Order_Body)
                .setTargetStatusCode(200)
                .performRequest();

        String Order_ID = Create_Order_Response.jsonPath().get("data.order_no");     // Order ID



        // ****************  Get Vehicle  ******************* :



        String Get_Vehicle_Details_URL = "v1/companies/" + Company_ID + "/vehicles/" + ID_Of_Vehicle;

        Response Get_Vehicle_Response = apiobject.buildNewRequest(Get_Vehicle_Details_URL, RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .addHeader("Platform", Platform)
                .addHeader("Accept", "application/json")
                .addHeader("Accept-Language", "en")
                .performRequest();

        Validations.assertThat().response(Get_Vehicle_Response)
                .extractedJsonValue("message")
                .equals("Vehicle information retrieved successfully");


        // ****************  Get Order  ******************* :



        String Get_Order_Details_URL = "v1/companies/"+Company_ID+"/orders/"+Order_ID;   // Get Order Details URL :


        Response Get_Order_Details_Response_English =  apiobject.buildNewRequest(Get_Order_Details_URL , RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .addHeader("platform","@vb~sD~KS#2>]pq")
                .addHeader("Accept","application/json")
                .addHeader("Accept-Language","en")
                .setTargetStatusCode(200)
                .performRequest();


        Validations.assertThat().response(Get_Order_Details_Response_English)
                .extractedJsonValue("message")
                .contains("Order information retrieved successfully")
                .perform();







    }
}
