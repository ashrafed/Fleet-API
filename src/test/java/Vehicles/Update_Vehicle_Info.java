package Vehicles;

import Authantication.Login_Main;
import Company.Create_Company_Main;
import Constants.Get_Constants_Main;
import Drivers.Create_Driver_Main;
import MainPackage.DataGenerator.AuthanticationDataGenerator;
import MainPackage.DataGenerator.CompanyDataGenerator;
import MainPackage.DataGenerator.DriversDataGenerator;
import MainPackage.DataGenerator.VinNumberGenerator;
import MainPackage.Main_Variables;
import com.shaft.api.RestActions;
import com.shaft.driver.DriverFactory;
import com.shaft.validation.Validations;
import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static Authantication.Login_Main.Platform;

public class Update_Vehicle_Info {

    private RestActions apiobject;
    private Main_Variables main_variables;
    private AuthanticationDataGenerator authanticationDataGenerator;
    private DriversDataGenerator driversDataGenerator;
    private Login_Main login_main;

    private Get_Constants_Main get_constants_main;

    private Create_Driver_Main create_driver_main;
    private Create_Vehicle_Main create_vehicle_main;
    private Update_Vehicle_Info_Main update_vehicle_info_main;

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
    public void BeforClass(){
        apiobject = DriverFactory.getAPIDriver(Login_Main.Base_Url);
        main_variables = new Main_Variables(apiobject);

        login_main = new Login_Main(apiobject);

        get_constants_main = new Get_Constants_Main(apiobject);

        create_company_main = new Create_Company_Main(apiobject);

        companyDataGenerator = new CompanyDataGenerator(apiobject);
        create_driver_main = new Create_Driver_Main(apiobject);

        authanticationDataGenerator = new AuthanticationDataGenerator(apiobject);
        driversDataGenerator = new DriversDataGenerator(apiobject);

        create_vehicle_main =new Create_Vehicle_Main(apiobject);

        update_vehicle_info_main = new Update_Vehicle_Info_Main(apiobject);



        login_main.Login_Successfully_English(
                authanticationDataGenerator.Email_Address_1 ,
                authanticationDataGenerator.Password);
    }



    @Test
    public void TC_01_Update_Vehicle_Info_Successfully_English() {

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

        int Company_ID =Create_Company_Successfully_Response_English.jsonPath().get("data.id");  // Get Company ID :


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



        // ****************  Add_Vehicle  ******************* :
        // ****************  Add_Vehicle  English ******************* :


        String Create_Vehicle_URL = "v1/companies/"+Company_ID+"/vehicles";   // Create Vehicle URL .

        JSONObject Add_Vehicle_Body = new JSONObject();
        Add_Vehicle_Body.put("driver_id",Driver_ID);
        Add_Vehicle_Body.put("vehicle_vin_number", VinNumberGenerator.Vin_Number_Generator_1);
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


        // ****************  Update Vehicle  ******************* :


        String Update_Vehicle_Info_URL = "v1/companies/"+Company_ID+"/vehicles/"+Vehicle_id;
        


        int Update_Engine_Type_ID = get_constants_response.jsonPath().get("data.engine_types[1].id");  // Get Engine_Type_ID   From Constants
        int Update_Modele_ID = get_constants_response.jsonPath().get("data.models[1].id");   // Get Modele_ID   From Constants
        int Update_Brand_ID = get_constants_response.jsonPath().get("data.brands[1].id");   // Get Brand_ID  From Constants
        int Update_Color_ID = get_constants_response.jsonPath().get("data.colors[1].id");   // Get Color_ID  From Constants
        int Update_Model_Year_ID = get_constants_response.jsonPath().get("data.models[1].id");   // Get Model_Year_ID From Constants



        JSONObject Update_Vehicle_Body = new JSONObject();
        Update_Vehicle_Body.put("driver_id",Driver_ID);
        Update_Vehicle_Body.put("vin_number","");
        Update_Vehicle_Body.put("model_id",Update_Modele_ID);
        Update_Vehicle_Body.put("brand_id",Update_Brand_ID);
        Update_Vehicle_Body.put("color_id",Update_Color_ID);
        Update_Vehicle_Body.put("year",Update_Model_Year_ID);
        Update_Vehicle_Body.put("engine_type_id",Update_Engine_Type_ID);
        Update_Vehicle_Body.put("plate_number","435");
        Update_Vehicle_Body.put("plate_letter_1_id",1);
        Update_Vehicle_Body.put("plate_letter_2_id",2);
        Update_Vehicle_Body.put("plate_letter_3_id",3);
        Update_Vehicle_Body.put("Status","");


      Response Update_Vehicle_Response = apiobject.buildNewRequest(Update_Vehicle_Info_URL , RestActions.RequestType.PUT)
                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept","application/json")
                .addHeader("Accept-Language","en")
                .setRequestBody(Update_Vehicle_Body)
                .performRequest();

      Validations.assertThat().response(Update_Vehicle_Response)
              .extractedJsonValue("message")
              .contains("Vehicle information updated successfully")
              .perform();


    }



    @Description("\"errors\": [\n" +
            "    \"The driver id must be a number.\",\n" +
            "    \"Model must be numeric\",\n" +
            "    \"Brand must be numeric\",\n" +
            "    \"The color id must be a number.\",\n" +
            "    \"Plate number must be string\",\n" +
            "    \"Engine type must be numeric\",\n" +
            "    \"plate letter 1 id must be numeric\",\n" +
            "    \"plate letter 2 id must be numeric\",\n" +
            "    \"plate letter 3 id must be numeric\"\n" +
            "  ]")

    @Test
    public void TC_02_Check_Update_Vehicle_Info_Error_English() {
        Response Update_Vehicle_Info_Response_English = update_vehicle_info_main.Update_Vehicle_Info_Fail_English(
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


        Validations.assertThat().response(Update_Vehicle_Info_Response_English)
                .extractedJsonValue("errors")
                .contains("Model must be numeric")
                .perform();

        Validations.assertThat().response(Update_Vehicle_Info_Response_English)
                .extractedJsonValue("errors")
                .contains("The color id must be a number.")
                .perform();

        Validations.assertThat().response(Update_Vehicle_Info_Response_English)
                .extractedJsonValue("errors")
                .contains("The driver id must be a number.")
                .perform();

        Validations.assertThat().response(Update_Vehicle_Info_Response_English)
                .extractedJsonValue("errors")
                .contains("Brand must be numeric")
                .perform();


        Validations.assertThat().response(Update_Vehicle_Info_Response_English)
                .extractedJsonValue("errors")
                .contains("Plate number must be string")
                .perform();

        Validations.assertThat().response(Update_Vehicle_Info_Response_English)
                .extractedJsonValue("errors")
                .contains("Engine type must be numeric")
                .perform();

        Validations.assertThat().response(Update_Vehicle_Info_Response_English)
                .extractedJsonValue("errors")
                .contains("plate letter 1 id must be numeric")
                .perform();

        Validations.assertThat().response(Update_Vehicle_Info_Response_English)
                .extractedJsonValue("errors")
                .contains("plate letter 2 id must be numeric")
                .perform();

        Validations.assertThat().response(Update_Vehicle_Info_Response_English)
                .extractedJsonValue("errors")
                .contains("plate letter 3 id must be numeric")
                .perform();



        long TimeOut = Update_Vehicle_Info_Response_English.getTimeIn(TimeUnit.MILLISECONDS);
        Validations.assertThat()
                .number(TimeOut)
                .isLessThanOrEquals(Main_Variables.timOut)
                .withCustomReportMessage("Validate Response Time")
                .perform();
    }


    @Description("  \"errors\": [\n" +
            "    \"يجب على الحقل driver id أن يكون رقمًا\",\n" +
            "    \"نوع السيارة يجب أن يكون رقم\",\n" +
            "    \"طراز السيارة يجب أن يكون رقم\",\n" +
            "    \"يجب على الحقل color id أن يكون رقمًا\",\n" +
            "    \"يجب أن تكون لوحة السيارة حروف\",\n" +
            "    \"نوع محرك السيارة رقم\",\n" +
            "    \"يجب أن تكون لوحة الارقام الاولى رقم\",\n" +
            "    \"يجب أن تكون لوحة الارقام الثانية رقم\",\n" +
            "    \"يجب أن تكون لوحة الارقام الثالثة رقم\"\n" +
            "  ] ")

    @Test
    public void TC_03_Check_Update_Vehicle_Info_Error_Arabic() {


        Response Update_Vehicle_Info_Response_Arabic = update_vehicle_info_main.Update_Vehicle_Info_Fail_Arabic(
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

//  Validations Resonse Arabic :


        Validations.assertThat().response(Update_Vehicle_Info_Response_Arabic)
                .extractedJsonValue("errors")
                .contains("يجب على الحقل driver id أن يكون رقمًا")
                .perform();

        Validations.assertThat().response(Update_Vehicle_Info_Response_Arabic)
                .extractedJsonValue("errors")
                .contains("نوع السيارة يجب أن يكون رقم")
                .perform();

        Validations.assertThat().response(Update_Vehicle_Info_Response_Arabic)
                .extractedJsonValue("errors")
                .contains("طراز السيارة يجب أن يكون رقم")
                .perform();

        Validations.assertThat().response(Update_Vehicle_Info_Response_Arabic)
                .extractedJsonValue("errors")
                .contains("يجب على الحقل color id أن يكون رقمًا")
                .perform();


        Validations.assertThat().response(Update_Vehicle_Info_Response_Arabic)
                .extractedJsonValue("errors")
                .contains("يجب أن تكون لوحة السيارة حروف")
                .perform();

        Validations.assertThat().response(Update_Vehicle_Info_Response_Arabic)
                .extractedJsonValue("errors")
                .contains("نوع محرك السيارة رقم")
                .perform();

        Validations.assertThat().response(Update_Vehicle_Info_Response_Arabic)
                .extractedJsonValue("errors")
                .contains("يجب أن تكون لوحة الارقام الاولى رقم")
                .perform();

        Validations.assertThat().response(Update_Vehicle_Info_Response_Arabic)
                .extractedJsonValue("errors")
                .contains("يجب أن تكون لوحة الارقام الثانية رقم")
                .perform();

        Validations.assertThat().response(Update_Vehicle_Info_Response_Arabic)
                .extractedJsonValue("errors")
                .contains("يجب أن تكون لوحة الارقام الثالثة رقم")
                .perform();

        long TimeOut = Update_Vehicle_Info_Response_Arabic.getTimeIn(TimeUnit.MILLISECONDS);
        Validations.assertThat()
                .number(TimeOut)
                .isLessThanOrEquals(Main_Variables.timOut)
                .withCustomReportMessage("Validate Response Time")
                .perform();

    }
}