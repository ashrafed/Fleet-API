package User_Flow;


import Authantication.Login_Main;
import Company.Create_Company_Main;
import Constants.Get_Constants_Main;
import Drivers.*;
import MainPackage.DataGenerator.AuthanticationDataGenerator;
import MainPackage.DataGenerator.CompanyDataGenerator;
import MainPackage.DataGenerator.DriversDataGenerator;
import MainPackage.DataGenerator.VinNumberGenerator;
import MainPackage.Main_Variables;
import Orders.Create_Order_Main;
import Orders.List_Orders_Main;
import Vehicles.Create_Vehicle_Main;
import Vehicles.Get_Vehicle_Details_Main;
import Vehicles.List_Vehicles_Main;
import com.shaft.api.RestActions;
import com.shaft.driver.DriverFactory;
import com.shaft.validation.Validations;
import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Authantication.Login_Main.Platform;

public class TCF_01_Login_CreateVehicle_GetVehicle_ListVehicle {
    private RestActions apiobject;
    private Main_Variables main_variables;
    private DriversDataGenerator driversDataGenerator;
    private AuthanticationDataGenerator authanticationDataGenerator;
    private Login_Main login_main;
    private Create_Vehicle_Main create_vehicle_main;
    private Get_Vehicle_Details_Main get_vehicle_details_main;
    private List_Vehicles_Main list_vehicles_main;

    private Get_Constants_Main get_constants_main;
    private CompanyDataGenerator companyDataGenerator;
    private Create_Company_Main create_company_main;


    @BeforeClass
    public void BeforeClass() {
        apiobject = DriverFactory.getAPIDriver(Login_Main.Base_Url);
        main_variables = new Main_Variables(apiobject);
        authanticationDataGenerator = new AuthanticationDataGenerator(apiobject);
        driversDataGenerator = new DriversDataGenerator(apiobject);
        login_main = new Login_Main(apiobject);
        create_vehicle_main = new Create_Vehicle_Main(apiobject);
        get_vehicle_details_main = new Get_Vehicle_Details_Main(apiobject);
        list_vehicles_main = new List_Vehicles_Main(apiobject);

        get_constants_main = new Get_Constants_Main(apiobject);

        companyDataGenerator = new CompanyDataGenerator(apiobject);
        create_company_main = new Create_Company_Main(apiobject);

        // Login_Test :
        login_main.Login_Successfully_Arabic(
                authanticationDataGenerator.Email_Address_1,
                authanticationDataGenerator.Password);

    }

    @Test
    @Link(Main_Variables.MIND_MAP_LINK)
    @Description("-Login_Test\n" +
            "-Create Company\n" +
            "-Create Driver\n" +
            "-Create Vehicle\n" +
            "-Get Vehicle Details\n" +
            "-List Vehicle\n")
    public void TCF_01_Login_CreateCompany_CreateDriver_CreateVehicle_GetVehicleDetails_ListVehicle_English() {

        //********** ################################ **************** :
        // **************** Create_Company   **************** :

        Response Create_Company_Successfully_Response_English = create_company_main.Create_Company_Successfully_English(
                companyDataGenerator.Company_Name,
                companyDataGenerator.contact_number_6,
                companyDataGenerator.Email_Address_5,
                companyDataGenerator.commercial_number_6,
                companyDataGenerator.additional_amount,
                companyDataGenerator.admin_name,
                "123456");

        int Company_ID = Create_Company_Successfully_Response_English.jsonPath().get("data.id");  // Get Company ID :


        // ************** Create Driver  *************************** :

        String Create_Driver_URL = "v1/companies/" + Company_ID + "/drivers";      //Create Driver URL :

        JSONObject Create_Driver_Body = new JSONObject();
        Create_Driver_Body.put("name", driversDataGenerator.Driver_Name_Generator);
        Create_Driver_Body.put("mobile", driversDataGenerator.Register_PhoneNumber_Generattor_2);
        Create_Driver_Body.put("residence_number", driversDataGenerator.Residence_Number_Generattor_2);
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
        Add_Vehicle_Body.put("vehicle_vin_number", VinNumberGenerator.Vin_Number_Generator);
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


        // ****************  List Vehicle: ******************* :


        String List_Vehicles_URL = "v1/companies/"+Company_ID+"/vehicles";

        Response List_Vehicles_Response = apiobject.buildNewRequest(List_Vehicles_URL, RestActions.RequestType.GET)

                .setContentType(ContentType.JSON)
                .addHeader("Platform", Platform)
                .addHeader("Accept", "application/json")
                .addHeader("Accept-Language", "en")
                .performRequest();

        Validations.assertThat().response(List_Vehicles_Response)
                .extractedJsonValue("message")
                .equals("Vehicles retrieved successfully");
    }

    @Test
    @Link(Main_Variables.MIND_MAP_LINK)
    public void TCF_01_Login_CreateCompany_CreateDriver_CreateVehicle_GetVehicleDetails_ListVehicle_Arabic() {

        //********** ################################ **************** :
        // **************** Create_Company   **************** :

        Response Create_Company_Successfully_Response_English = create_company_main.Create_Company_Successfully_Arabic(
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
                .addHeader("Accept-Language", "ar")
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
        // ****************  Add_Vehicle  Arabic ******************* :


        String Create_Vehicle_URL = "v1/companies/" + Company_ID + "/vehicles";   // Create Vehicle URL .

        JSONObject Add_Vehicle_Body = new JSONObject();
        Add_Vehicle_Body.put("driver_id", Driver_ID);
        Add_Vehicle_Body.put("vehicle_vin_number", VinNumberGenerator.Vin_Number_Generator_9);
        Add_Vehicle_Body.put("model_id", Modele_ID);
        Add_Vehicle_Body.put("brand_id", Brand_ID);
        Add_Vehicle_Body.put("color_id", Color_ID);
        Add_Vehicle_Body.put("model_year", Model_Year_ID);
        Add_Vehicle_Body.put("engine_type_id", Engine_Type_ID);
        Add_Vehicle_Body.put("plate_number", "435");
        Add_Vehicle_Body.put("plate_letter_1_id", 1);
        Add_Vehicle_Body.put("plate_letter_2_id", 2);
        Add_Vehicle_Body.put("plate_letter_3_id", 3);

        Response Add_Vehicle_Response_Arabic = apiobject.buildNewRequest(Create_Vehicle_URL, RestActions.RequestType.POST)
                .setContentType(ContentType.JSON)
                .addHeader("Platform", Platform)
                .addHeader("Accept", "application/json")
                .addHeader("Accept-Language", "ar")
                .setRequestBody(Add_Vehicle_Body)
                .setTargetStatusCode(201)
                .performRequest();

        Validations.assertThat().response(Add_Vehicle_Response_Arabic)
                .extractedJsonValue("message")
                .contains("تم حفظ السيارة بنجاح")
                .perform();


        int ID_Of_Vehicle = Add_Vehicle_Response_Arabic.jsonPath().get("data.id");  //  Get Vehicle ID


        // ****************  Get Vehicle  ******************* :


        String Get_Vehicle_Details_URL = "v1/companies/" + Company_ID + "/vehicles/" + ID_Of_Vehicle;

        Response Get_Vehicle_Response = apiobject.buildNewRequest(Get_Vehicle_Details_URL, RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .addHeader("Platform", Platform)
                .addHeader("Accept", "application/json")
                .addHeader("Accept-Language", "ar")
                .performRequest();

        Validations.assertThat().response(Get_Vehicle_Response)
                .extractedJsonValue("message")
                .equals("تم إسترجاع بيانات السيارة بنجاح");


        // ****************  List Vehicle: ******************* :


        String List_Vehicles_URL = "v1/companies/"+Company_ID+"/vehicles";

        Response List_Vehicles_Response = apiobject.buildNewRequest(List_Vehicles_URL, RestActions.RequestType.GET)

                .setContentType(ContentType.JSON)
                .addHeader("Platform", Platform)
                .addHeader("Accept", "application/json")
                .addHeader("Accept-Language", "ar")
                .performRequest();

        Validations.assertThat().response(List_Vehicles_Response)
                .extractedJsonValue("message")
                .equals("تم إسترجاع العربات بنجاح");


    }
}
