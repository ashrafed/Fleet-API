package Orders;

import Authantication.Login_Main;
import Company.Create_Company_Main;
import Constants.Get_Constants_Main;
import MainPackage.DataGenerator.AuthanticationDataGenerator;
import MainPackage.DataGenerator.CompanyDataGenerator;
import MainPackage.DataGenerator.DriversDataGenerator;
import MainPackage.DataGenerator.VinNumberGenerator;
import MainPackage.Main_Variables;
import com.shaft.api.RestActions;
import com.shaft.driver.DriverFactory;
import com.shaft.driver.SHAFT;
import com.shaft.validation.Validations;
import io.qameta.allure.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static Authantication.Login_Main.Platform;

public class Create_Order {
    private RestActions apiobject;
    private Main_Variables main_variables;
    private AuthanticationDataGenerator authanticationDataGenerator;
    private DriversDataGenerator driversDataGenerator;

    private Login_Main login_main;
    private Create_Order_Main create_order_main;

    private Get_Constants_Main get_constants_main;
    private Create_Company_Main create_company_main;
    private CompanyDataGenerator companyDataGenerator;


    /*
     * [] validate data send in API
     * [] validate API response body
     * [] validate response status code
     * [] validate API response time
     * [] validate response Structure
     * [] validate API type (get,post,delete)
     * [] validate response in multiple languages (ar,en)
     */

    @BeforeClass
    public void Beforclase() {
        apiobject = DriverFactory.getAPIDriver(Login_Main.Base_Url);
        main_variables = new Main_Variables(apiobject);
        authanticationDataGenerator = new AuthanticationDataGenerator(apiobject);
        driversDataGenerator = new DriversDataGenerator(apiobject);
        login_main = new Login_Main(apiobject);
        create_order_main = new Create_Order_Main(apiobject);

        get_constants_main = new Get_Constants_Main(apiobject);
        create_company_main = new Create_Company_Main(apiobject);
        companyDataGenerator = new CompanyDataGenerator(apiobject);


        login_main.Login_Successfully_English(
                authanticationDataGenerator.Email_Address_1,
                authanticationDataGenerator.Password);
    }

    @Test
    @Link("https://carefer.atlassian.net/browse/CP-5046")
    public void TC_01_Create_Order_Successfully_English_And_Arabic() {


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

        String Create_Driver_URL = "v1/companies/"+Company_ID + "/drivers";      //Create Driver URL :

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


        // ****************  Add_Vehicle  ******************* :
        // ****************  Add_Vehicle  English ******************* :


        String Create_Vehicle_URL = "v1/companies/"+Company_ID+"/vehicles";   // Create Vehicle URL .

        JSONObject Add_Vehicle_Body = new JSONObject();
        Add_Vehicle_Body.put("driver_id",Driver_ID);
        Add_Vehicle_Body.put("vehicle_vin_number", VinNumberGenerator.Vin_Number_Generator_8);
        Add_Vehicle_Body.put("model_id",Modele_ID);
        Add_Vehicle_Body.put("brand_id",Brand_ID);
        Add_Vehicle_Body.put("color_id",Color_ID);
        Add_Vehicle_Body.put("model_year",Model_Year_ID);
        Add_Vehicle_Body.put("engine_type_id",Engine_Type_ID);
        Add_Vehicle_Body.put("plate_number","435");
        Add_Vehicle_Body.put("plate_letter_1_id",1);
        Add_Vehicle_Body.put("plate_letter_2_id",2);
        Add_Vehicle_Body.put("plate_letter_3_id",3);

        Response Add_Vehicle_Response_English = apiobject.buildNewRequest(Create_Vehicle_URL , RestActions.RequestType.POST)
                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept","application/json")
                .addHeader("Accept-Language","en")
                .setRequestBody(Add_Vehicle_Body)
                .setTargetStatusCode(201)
                .performRequest();

        Validations.assertThat().response(Add_Vehicle_Response_English)
                .extractedJsonValue("message")
                .contains("Vehicle stored successfully")
                .perform();



        int Vehicle_id = Add_Vehicle_Response_English.jsonPath().get("data.id");   // Get Vehicle ID .


        ///////////////////////// *********  ///////////////////////////// :

        //*****************   Create Order :  *************************    :

        String Create_Order_URL = "v1/companies/"+Company_ID+"/orders";   // Create Order URL  .


        JSONObject Create_Order_Body = new JSONObject();
        JSONArray items_array = new JSONArray();    // Creat array
        JSONObject itms_info_id = new JSONObject();      // Creat info_id (1) JSONObject
        itms_info_id.put("info_id" ,47);
        items_array.add(itms_info_id);


        JSONObject items = new JSONObject();
        Create_Order_Body.put("items" , items_array);


        Create_Order_Body.put("vehicle_id" ,Vehicle_id);


        JSONObject service_location = new JSONObject();
        service_location.put("type" , 2);
        service_location.put("longitude" ,"21.263781");
        service_location.put("latitude" ,"46.351390");
        Create_Order_Body.put("service_location" ,service_location);

        JSONObject pickup_details = new JSONObject();
        pickup_details.put("type" ,1);
        pickup_details.put("longitude" ,"21.263781");
        pickup_details.put("latitude" ,"46.351390");
        pickup_details.put("hydraulic" ,2);
        Create_Order_Body.put("pickup_details" ,pickup_details);

        Create_Order_Body.put("comment" ,"test Automation comment");
        Create_Order_Body.put("order_reference_number" ,"CAR-44242");

        JSONArray  media_ids = new JSONArray();
        media_ids.add(1);
        media_ids.add(2);
        media_ids.add(3);
        Create_Order_Body.put("media_ids" ,media_ids);


        Response create_Order_response =  apiobject.buildNewRequest(Create_Order_URL , RestActions.RequestType.POST)
                .setContentType(ContentType.JSON)
                .addHeader("platform","@vb~sD~KS#2>]pq")
                .addHeader("Accept","application/json")
                .addHeader("Accept-Language","en")
                .setRequestBody(Create_Order_Body)
                .setTargetStatusCode(200)
                .performRequest();



        Validations.assertThat().response(create_Order_response)
                .extractedJsonValue("message")
                .contains("Order stored successfully")
                .perform();


        long TimeOut = create_Order_response.getTimeIn(TimeUnit.MILLISECONDS);
        Validations.assertThat()
                .number(TimeOut)
                .isLessThanOrEquals(Main_Variables.timOut)
                .withCustomReportMessage("Validate Response Time")
                .perform();

    }

    @Description("    \"Check :: \"\n" +
            "    \"service location type is mandatory\",\n" +
            "    \"pickup details type is mandatory\",\n" +
            "    \"hydraulic is mandatory\",\n" +
            "    \"comment must be string\",\n" +
            "    \"refrence number is mandatory\",\n" +
            "    \"The items.0.info_id field is required.\",\n" +
            "    \"media id is required with media array\",\n" +
            "    \"media id is required with media array\",\n" +
            "    \"media id is required with media array\"\n" +
            "    ")

    @Test
    @Link("https://carefer.atlassian.net/browse/CP-4124")
    @Issue("CP-4124")
    public void TC_02_Check_Create_Order_Fail_English() {

        Response Create_Order_Response = create_order_main.Create_Order_Fail_English(
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "");

        Validations.assertThat().response(Create_Order_Response)
                .extractedJsonValue("message")
                .contains("Invalid input data")
                .perform();

        Validations.assertThat().response(Create_Order_Response)
                .extractedJsonValue("status_code")
                .contains(422)
                .perform();


        long TimeOut = Create_Order_Response.getTimeIn(TimeUnit.MILLISECONDS);
        Validations.assertThat()
                .number(TimeOut)
                .isLessThanOrEquals(Main_Variables.timOut)
                .withCustomReportMessage("Validate Response Time")
                .perform();
    }

    @Test
    public void TC_02_0_Create_Order_Validate_On_Errors_English() {

        Response Create_Order_Response = create_order_main.Create_Order_Fail_English(
                "", "", "", "", "", "", "", "",
                "", "", "", "", "", "");

        Validations.assertThat().response(Create_Order_Response)
                .extractedJsonValue("errors[*]")
                .contains("service location type is mandatory")
                .perform();
    }

    @Test
    public void TC_02_1_Create_Order_Validate_On_Errors_English() {

        Response Create_Order_Response = create_order_main.Create_Order_Fail_English(
                "", "", "", "", "", "", "", "",
                "", "", "", "", "", "");

        Validations.assertThat().response(Create_Order_Response)
                .extractedJsonValue("errors[*]")
                .contains("vehicle id is mandatory")
                .perform();
    }

    @Test
    public void TC_02_2_Create_Order_Validate_On_Errors_English() {

        Response Create_Order_Response = create_order_main.Create_Order_Fail_English(
                "", "", "", "", "", "", "", "",
                "", "", "", "", "", "");

        Validations.assertThat().response(Create_Order_Response)
                .extractedJsonValue("errors[*]")
                .contains("The pickup details.type field is required when pickup details is present.")
                .perform();
    }

    @Test
    public void TC_02_3_Create_Order_Validate_On_Errors_English() {

        Response Create_Order_Response = create_order_main.Create_Order_Fail_English(
                "", "", "", "", "", "", "", "",
                "", "", "", "", "", "");

        Validations.assertThat().response(Create_Order_Response)
                .extractedJsonValue("errors[*]")
                .contains("comment must be string")
                .perform();
    }

    @Test
    public void TC_02_4_Create_Order_Validate_On_Errors_English() {

        Response Create_Order_Response = create_order_main.Create_Order_Fail_English(
                "", "", "", "", "", "", "", "",
                "", "", "", "", "", "");

        Validations.assertThat().response(Create_Order_Response)
                .extractedJsonValue("errors[*]")
                .contains("The order reference number must be a string.")
                .perform();
    }

    @Test
    public void TC_02_5_Create_Order_Validate_On_Errors_English() {

        Response Create_Order_Response = create_order_main.Create_Order_Fail_English(
                "", "", "", "", "", "", "", "",
                "", "", "", "", "", "");

        Validations.assertThat().response(Create_Order_Response)
                .extractedJsonValue("errors[*]")
                .contains("item_is_mandatory")
                .perform();
    }

    @Test
    public void TC_02_6_Create_Order_Validate_On_Errors_English() {

        Response Create_Order_Response = create_order_main.Create_Order_Fail_English(
                "", "", "", "", "", "", "", "",
                "", "", "", "", "", "");

        Validations.assertThat().response(Create_Order_Response)
                .extractedJsonValue("errors[*]")
                .contains("media id is required with media array")
                .perform();
    }

    @Description("    \"Check :: \"\n" +
            "\t\"نوع المكان مطلوب\",\n" +
            "    \"نوع إلتقاط الطلب مطلوب\",\n" +
            "    \"الهيدروليك مطلوب\",\n" +
            "    \"يجب أن تكون الملاحظات نصاً\",\n" +
            "    \"الرقم المرجعي مطلوب\",\n" +
            "    \"الحقل items.0.info_id مطلوب.\",\n" +
            "    \"الصورة مطلوبة فى حالة وجود مصفوفة الصور\",\n" +
            "    \"الصورة مطلوبة فى حالة وجود مصفوفة الصور\",\n" +
            "    \"الصورة مطلوبة فى حالة وجود مصفوفة الصور\"\n" +
            "    ")

    @Test
    public void TC_03_Check_Create_Order_Fail_Arabic() {

        Response Create_Order_Response = create_order_main.Create_Order_Fail_Arabic(
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "");

        Validations.assertThat().response(Create_Order_Response)
                .extractedJsonValue("message")
                .contains("خطأ فى بيانات الإدخال")
                .perform();

        Validations.assertThat().response(Create_Order_Response)
                .extractedJsonValue("status_code")
                .contains(422)
                .perform();
    }

    @Test
    public void TC_03_0_Create_Order_Validate_On_Errors_Arabic() {

        Response Create_Order_Response = create_order_main.Create_Order_Fail_Arabic(
                "", "", "", "", "", "", "", "",
                "", "", "", "", "", "");

        Validations.assertThat().response(Create_Order_Response)
                .extractedJsonValue("errors[*]")
                .contains("العربة مطلوبة")
                .perform();
    }

    @Test
    public void TC_03_1_Create_Order_Validate_On_Errors_Arabic() {

        Response Create_Order_Response = create_order_main.Create_Order_Fail_Arabic(
                "", "", "", "", "", "", "", "",
                "", "", "", "", "", "");

        Validations.assertThat().response(Create_Order_Response)
                .extractedJsonValue("errors[*]")
                .contains("يجب أن تكون الملاحظات نصاً")
                .perform();
    }

    @Test
    public void TC_03_2_Create_Order_Validate_On_Errors_Arabic() {

        Response Create_Order_Response = create_order_main.Create_Order_Fail_Arabic(
                "", "", "", "", "", "", "", "",
                "", "", "", "", "", "");


        Validations.assertThat().response(Create_Order_Response)
                .extractedJsonValue("errors[*]")
                .contains("نوع موقع الخدمة مطلوب")
                .perform();
    }

    @Test
    public void TC_03_3_Create_Order_Validate_On_Errors_Arabic() {

        Response Create_Order_Response = create_order_main.Create_Order_Fail_Arabic(
                "", "", "", "", "", "", "", "",
                "", "", "", "", "", "");

        Validations.assertThat().response(Create_Order_Response)
                .extractedJsonValue("errors[*]")
                .contains("نوع موقع")
                .perform();
    }

    @Test
    public void TC_03_4_Create_Order_Validate_On_Errors_Arabic() {

        Response Create_Order_Response = create_order_main.Create_Order_Fail_Arabic(
                "", "", "", "", "", "", "", "",
                "", "", "", "", "", "");


        Validations.assertThat().response(Create_Order_Response)
                .extractedJsonValue("errors[*]")
                .contains("الصورة مطلوبة فى حالة وجود مصفوفة الصور")
                .perform();
    }

    @Test
    public void TC_03_5_Create_Order_Validate_On_Errors_Arabic() {

        Response Create_Order_Response = create_order_main.Create_Order_Fail_Arabic(
                "", "", "", "", "", "", "", "",
                "", "", "", "", "", "");

        Validations.assertThat().response(Create_Order_Response)
                .extractedJsonValue("errors[*]")
                .contains("لصورة مطلوبة فى حالة وجود مصفوفة الصور")
                .perform();
    }

    @Test
    public void TC_03_6_Create_Order_Validate_On_Errors_Arabic() {

        Response Create_Order_Response = create_order_main.Create_Order_Fail_Arabic(
                "", "", "", "", "", "", "", "",
                "", "", "", "", "", "");


        Validations.assertThat().response(Create_Order_Response)
                .extractedJsonValue("errors[*]")
                .contains("الصورة مطلوبة فى حالة وجود مصفوفة الصور")
                .perform();


        long TimeOut = Create_Order_Response.getTimeIn(TimeUnit.MILLISECONDS);
        Validations.assertThat()
                .number(TimeOut)
                .isLessThanOrEquals(Main_Variables.timOut)
                .withCustomReportMessage("Validate Response Time")
                .perform();

    }

    @Description("    \"Check :: \"\n" +
            "    \"Vehicle id is invalid\",\n" +
            "    \"The service location.type must be a number.\",\n" +
            "    \"The selected service location.type is invalid.\",\n" +
            "    \"The pickup details.type must be a number.\",\n" +
            "    \"The selected pickup details.type is invalid.\",\n" +
            "    \"hydraulic is mandatory\",\n" +
            "    \"comment must be string\",\n" +
            "    \"The items.0.info_id must be a number.\",\n" +
            "    \"media id must be numeric\",\n" +
            "    \"media id must be numeric\",\n" +
            "    \"media id must be numeric\"")


    @Test
    public void TC_04_Check_Create_Order_Mandatory_InValid_Input_English() {

        Response Create_Order_Response = create_order_main.Create_Order_Fail_English(
                "Test Automation",
                "Test Automation",
                "Test Automation",
                "Test Automation",
                "Test Automation",
                "Test Automation",
                "Test Automation",
                "Test Automation",
                "Test Automation",
                "",
                "Test Automation",
                "Test Automation",
                "Test Automation",
                "Test Automation");

        Validations.assertThat().response(Create_Order_Response)
                .extractedJsonValue("errors")
                .contains("Vehicle id is invalid")
                .perform();


        Validations.assertThat().response(Create_Order_Response)
                .extractedJsonValue("errors")
                .contains("Service location type must be number")
                .perform();

        Validations.assertThat().response(Create_Order_Response)
                .extractedJsonValue("errors")
                .contains("Service location type is invalid")
                .perform();

        Validations.assertThat().response(Create_Order_Response)
                .extractedJsonValue("errors")
                .contains("Pickup details type must be number")
                .perform();

        Validations.assertThat().response(Create_Order_Response)
                .extractedJsonValue("errors")
                .contains("Pickup details type is invalid")
                .perform();


        Validations.assertThat().response(Create_Order_Response)
                .extractedJsonValue("errors")
                .contains("comment must be string")
                .perform();

        Validations.assertThat().response(Create_Order_Response)
                .extractedJsonValue("errors")
                .contains("Item must be number")
                .perform();

        Validations.assertThat().response(Create_Order_Response)
                .extractedJsonValue("errors")
                .contains("media id must be numeric")
                .perform();

        Validations.assertThat().response(Create_Order_Response)
                .extractedJsonValue("errors")
                .contains("media id must be numeric")
                .perform();

        Validations.assertThat().response(Create_Order_Response)
                .extractedJsonValue("errors")
                .contains("media id must be numeric")
                .perform();


        long TimeOut = Create_Order_Response.getTimeIn(TimeUnit.MILLISECONDS);
        Validations.assertThat()
                .number(TimeOut)
                .isLessThanOrEquals(Main_Variables.timOut)
                .withCustomReportMessage("Validate Response Time")
                .perform();

    }

    @Link("https://carefer.atlassian.net/browse/CP-4125")
    @Issue("CP-4125")
    @Test
    public void TC_05_Check_Create_Order_Mandatory_InValid_Input_Arabic() {

        Response Create_Order_Response = create_order_main.Create_Order_Fail_Arabic(
                "Test Automation",
                "Test Automation",
                "Test Automation",
                "Test Automation",
                "Test Automation",
                "Test Automation",
                "Test Automation",
                "Test Automation",
                "Test Automation",
                "",
                "Test Automation",
                "Test Automation",
                "Test Automation",
                "Test Automation");

        Validations.assertThat().response(Create_Order_Response)
                .extractedJsonValue("errors")
                .contains("")
                .perform();


        long TimeOut = Create_Order_Response.getTimeIn(TimeUnit.MILLISECONDS);
        Validations.assertThat()
                .number(TimeOut)
                .isLessThanOrEquals(Main_Variables.timOut)
                .withCustomReportMessage("Validate Response Time")
                .perform();
    }


}