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
import Orders.Create_Order;
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

public class Create_Vehicle {

    private RestActions apiobject;
    private Main_Variables main_variables;
    private AuthanticationDataGenerator authanticationDataGenerator;
    private DriversDataGenerator driversDataGenerator;
    private Login_Main login_main;
    private Get_Constants_Main get_constants_main;

    private Create_Driver_Main create_driver_main;

    private Create_Vehicle_Main create_vehicle_main;

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
    public void BeforClass() {
        apiobject = DriverFactory.getAPIDriver(Login_Main.Base_Url);
        main_variables = new Main_Variables(apiobject);

        get_constants_main = new Get_Constants_Main(apiobject);

        login_main = new Login_Main(apiobject);
        create_driver_main = new Create_Driver_Main(apiobject);

        authanticationDataGenerator = new AuthanticationDataGenerator(apiobject);
        driversDataGenerator = new DriversDataGenerator(apiobject);

        create_vehicle_main = new Create_Vehicle_Main(apiobject);

        create_company_main = new Create_Company_Main(apiobject);

        companyDataGenerator = new CompanyDataGenerator(apiobject);


        login_main.Login_Successfully_English(
                authanticationDataGenerator.Email_Address_1,
                authanticationDataGenerator.Password);

    }


    @Test
    public void TC_01_Add_Vehicle_successfully_English_And_Arabic() {

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
        int Plate_Letter_ID = get_constants_response.jsonPath().get("data.plate_letters[0].id");


        // ****************  Add_Vehicle  ******************* :
        // ****************  Add_Vehicle  English ******************* :


        String Create_Vehicle_URL = "v1/companies/"+Company_ID+"/vehicles";   // Create Vehicle URL .

        JSONObject Add_Vehicle_Body = new JSONObject();
        Add_Vehicle_Body.put("driver_id",Driver_ID);
        Add_Vehicle_Body.put("vehicle_vin_number", VinNumberGenerator.Vin_Number_Generator_18);
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


        // ****************  Add_Vehicle  Arabic ******************* :


    }

    @Test
    public void TC_02_Check_Add_Vehicle_Required_Input_Field_English() {
        Response Add_Vehicle_Response_English = create_vehicle_main.Add_Vehicle_Fail_English(
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

        Validations.assertThat().response(Add_Vehicle_Response_English)
                .extractedJsonValue("errors")
                .contains("Vin number is mandatory")
                .perform();


        Validations.assertThat().response(Add_Vehicle_Response_English)
                .extractedJsonValue("errors")
                .contains("Model id is mandatory")
                .perform();

        Validations.assertThat().response(Add_Vehicle_Response_English)
                .extractedJsonValue("errors")
                .contains("Color id is mandatory")
                .perform();

        Validations.assertThat().response(Add_Vehicle_Response_English)
                .extractedJsonValue("errors")
                .contains("Driver id is mandatory")
                .perform();

        Validations.assertThat().response(Add_Vehicle_Response_English)
                .extractedJsonValue("errors")
                .contains("Brand id is mandatory")
                .perform();

        Validations.assertThat().response(Add_Vehicle_Response_English)
                .extractedJsonValue("errors")
                .contains("Year is mandatory")
                .perform();

        Validations.assertThat().response(Add_Vehicle_Response_English)
                .extractedJsonValue("errors")
                .contains("Plate number is mandatory")
                .perform();

        Validations.assertThat().response(Add_Vehicle_Response_English)
                .extractedJsonValue("errors")
                .contains("Engine type id is mandatory")
                .perform();

        Validations.assertThat().response(Add_Vehicle_Response_English)
                .extractedJsonValue("errors")
                .contains("Plate letter 1 id is mandatory")
                .perform();

        Validations.assertThat().response(Add_Vehicle_Response_English)
                .extractedJsonValue("errors")
                .contains("Plate letter 2 id is mandatory")
                .perform();

        Validations.assertThat().response(Add_Vehicle_Response_English)
                .extractedJsonValue("errors")
                .contains("Plate letter 3 id is mandatory")
                .perform();


        long TimeOut = Add_Vehicle_Response_English.getTimeIn(TimeUnit.MILLISECONDS);
        Validations.assertThat()
                .number(TimeOut)
                .isLessThanOrEquals(Main_Variables.timOut)
                .withCustomReportMessage("Validate Response Time")
                .perform();


    }

    @Test
    public void TC_03_Check_Add_Vehicle_Required_Input_Field_Arabic() {


        Response Add_Vehicle_Response_Arabic = create_vehicle_main.Add_Vehicle_Fail_Arabic(
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


        Validations.assertThat().response(Add_Vehicle_Response_Arabic)
                .extractedJsonValue("errors")
                .contains("رقم السيارة مطلوب")
                .perform();

        Validations.assertThat().response(Add_Vehicle_Response_Arabic)
                .extractedJsonValue("errors")
                .contains("موديل السيارة مطلوب")
                .perform();

        Validations.assertThat().response(Add_Vehicle_Response_Arabic)
                .extractedJsonValue("errors")
                .contains("لون السيارة مطلوب")
                .perform();

        Validations.assertThat().response(Add_Vehicle_Response_Arabic)
                .extractedJsonValue("errors")
                .contains("السائق مطلوب")
                .perform();

        Validations.assertThat().response(Add_Vehicle_Response_Arabic)
                .extractedJsonValue("errors")
                .contains("براند السيارة مطلوب")
                .perform();

        Validations.assertThat().response(Add_Vehicle_Response_Arabic)
                .extractedJsonValue("errors")
                .contains("سنة السيارة مطلوبة")
                .perform();

        Validations.assertThat().response(Add_Vehicle_Response_Arabic)
                .extractedJsonValue("errors")
                .contains("لوحة السبارة مطلوبة")
                .perform();

        Validations.assertThat().response(Add_Vehicle_Response_Arabic)
                .extractedJsonValue("errors")
                .contains("نوع محرك السيارة مطلوب")
                .perform();

        Validations.assertThat().response(Add_Vehicle_Response_Arabic)
                .extractedJsonValue("errors")
                .contains("لوحة السيارة الأولى مطلوبة")
                .perform();

        Validations.assertThat().response(Add_Vehicle_Response_Arabic)
                .extractedJsonValue("errors")
                .contains("لوحة السيارة الثانية مطلوبة")
                .perform();

        Validations.assertThat().response(Add_Vehicle_Response_Arabic)
                .extractedJsonValue("errors")
                .contains("لوحة السيارة الثالثة مطلوبة")
                .perform();


        long TimeOut = Add_Vehicle_Response_Arabic.getTimeIn(TimeUnit.MILLISECONDS);
        Validations.assertThat()
                .number(TimeOut)
                .isLessThanOrEquals(Main_Variables.timOut)
                .withCustomReportMessage("Validate Response Time")
                .perform();

    }

    @Description(" \"errors\": [\n" +
            "    \"Vin number is mandatory\",\n" +
            "    \"Model must be numeric\",\n" +
            "    \"The color id must be a number.\",\n" +
            "    \"The driver id must be a number.\",\n" +
            "    \"Brand must be numeric\",\n" +
            "    \"Year is mandatory\",\n" +
            "    \"Engine type must be numeric\",\n" +
            "    \"plate letter 1 id must be numeric\",\n" +
            "    \"plate letter 2 id must be numeric\",\n" +
            "    \"plate letter 3 id must be numeric\"\n" +
            "  ]")

    @Test
    public void TC_04_Check_Add_Vehicle_Error_English() {

        Response Add_Vehicle_Response_English = create_vehicle_main.Add_Vehicle_Fail_English(
                "Test",
                "test",
                "Test",
                "Test",
                "Test",
                "Test",
                "Test",
                "Test",
                "Test",
                "Test",
                "Test");

        Validations.assertThat().response(Add_Vehicle_Response_English)
                .extractedJsonValue("errors")
                .contains("Invalid VIN number")
                .perform();

        Validations.assertThat().response(Add_Vehicle_Response_English)
                .extractedJsonValue("errors")
                .contains("Model must be numeric")
                .perform();


        Validations.assertThat().response(Add_Vehicle_Response_English)
                .extractedJsonValue("errors")
                .contains("The color id must be a number.")
                .perform();


        Validations.assertThat().response(Add_Vehicle_Response_English)
                .extractedJsonValue("errors")
                .contains("The driver id must be a number.")
                .perform();


        Validations.assertThat().response(Add_Vehicle_Response_English)
                .extractedJsonValue("errors")
                .contains("Brand must be numeric")
                .perform();


        Validations.assertThat().response(Add_Vehicle_Response_English)
                .extractedJsonValue("errors")
                .contains("Year must be numeric")
                .perform();


        Validations.assertThat().response(Add_Vehicle_Response_English)
                .extractedJsonValue("errors")
                .contains("Engine type must be numeric")
                .perform();

        Validations.assertThat().response(Add_Vehicle_Response_English)
                .extractedJsonValue("errors")
                .contains("plate letter 1 id must be numeric")
                .perform();


        Validations.assertThat().response(Add_Vehicle_Response_English)
                .extractedJsonValue("errors")
                .contains("plate letter 2 id must be numeric")
                .perform();


        Validations.assertThat().response(Add_Vehicle_Response_English)
                .extractedJsonValue("errors")
                .contains("plate letter 3 id must be numeric")
                .perform();


        long TimeOut = Add_Vehicle_Response_English.getTimeIn(TimeUnit.MILLISECONDS);
        Validations.assertThat()
                .number(TimeOut)
                .isLessThanOrEquals(Main_Variables.timOut)
                .withCustomReportMessage("Validate Response Time")
                .perform();

    }

    @Test
    public void TC_05_Check_Add_Vehicle_Error_Arabic() {
        Response Add_Vehicle_Response_Arabic = create_vehicle_main.Add_Vehicle_Fail_Arabic(
                "Test",
                "Test",
                "Test",
                "Test",
                "Test",
                "Test",
                "Test",
                "Test",
                "Test",
                "Test",
                "Test");

        Validations.assertThat().response(Add_Vehicle_Response_Arabic)
                .extractedJsonValue("errors")
                .contains("رقم الهيكل غير صالح")
                .perform();

        Validations.assertThat().response(Add_Vehicle_Response_Arabic)
                .extractedJsonValue("errors")
                .contains("نوع السيارة يجب أن يكون رقم")
                .perform();

        Validations.assertThat().response(Add_Vehicle_Response_Arabic)
                .extractedJsonValue("errors")
                .contains("يجب على الحقل color id أن يكون رقمًا")
                .perform();

        Validations.assertThat().response(Add_Vehicle_Response_Arabic)
                .extractedJsonValue("errors")
                .contains("يجب على الحقل driver id أن يكون رقمًا")
                .perform();


        Validations.assertThat().response(Add_Vehicle_Response_Arabic)
                .extractedJsonValue("errors")
                .contains("طراز السيارة يجب أن يكون رقم")
                .perform();


        Validations.assertThat().response(Add_Vehicle_Response_Arabic)
                .extractedJsonValue("errors")
                .contains("يجب أن تكون السنة رقم")
                .perform();


        Validations.assertThat().response(Add_Vehicle_Response_Arabic)
                .extractedJsonValue("errors")
                .contains("نوع محرك السيارة رقم")
                .perform();


        Validations.assertThat().response(Add_Vehicle_Response_Arabic)
                .extractedJsonValue("errors")
                .contains("يجب أن تكون لوحة الارقام الاولى رقم")
                .perform();


        Validations.assertThat().response(Add_Vehicle_Response_Arabic)
                .extractedJsonValue("errors")
                .contains("يجب أن تكون لوحة الارقام الثانية رقم")
                .perform();

        Validations.assertThat().response(Add_Vehicle_Response_Arabic)
                .extractedJsonValue("errors")
                .contains("يجب أن تكون لوحة الارقام الثالثة رقم")
                .perform();


        long TimeOut = Add_Vehicle_Response_Arabic.getTimeIn(TimeUnit.MILLISECONDS);
        Validations.assertThat()
                .number(TimeOut)
                .isLessThanOrEquals(Main_Variables.timOut)
                .withCustomReportMessage("Validate Response Time")
                .perform();


    }


}
