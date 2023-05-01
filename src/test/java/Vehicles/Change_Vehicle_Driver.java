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
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Authantication.Login_Main.Platform;

public class Change_Vehicle_Driver {


    private RestActions apiobject;
    private Main_Variables main_variables;
    private AuthanticationDataGenerator authanticationDataGenerator;
    private DriversDataGenerator driversDataGenerator;
    private Login_Main login_main;

    private Get_Constants_Main get_constants_main;
    private Create_Driver_Main create_driver_main;

    private Create_Vehicle_Main create_vehicle_main;

    private Create_Company_Main create_company_main;


    private CompanyDataGenerator companyDataGenerator ;



    /*
     * [] validate data send in API
     * [] validate API response body
     * [] validate response status code
     * [] add test cases for the business logic
     * [] validate API response time
     * [] validate response Structure
     * [] validate API type (get,post,delete)
     * [] validate response in multiple languages (ar,en)

     */

    @BeforeClass
    public void BeforClass() {
        apiobject = DriverFactory.getAPIDriver(Login_Main.Base_Url);
        main_variables = new Main_Variables(apiobject);

        login_main = new Login_Main(apiobject);
        get_constants_main = new Get_Constants_Main(apiobject);
        create_driver_main = new Create_Driver_Main(apiobject);

        authanticationDataGenerator = new AuthanticationDataGenerator(apiobject);
        driversDataGenerator = new DriversDataGenerator(apiobject);

        create_vehicle_main = new Create_Vehicle_Main(apiobject);

        companyDataGenerator = new CompanyDataGenerator(apiobject);

        create_company_main = new Create_Company_Main(apiobject);


        login_main.Login_Successfully_English(
                authanticationDataGenerator.Email_Address_1,
                authanticationDataGenerator.Password);
    }


    @Test
    public void TC_01_Change_Vehicle_Driver_Successfully() {

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
        Add_Vehicle_Body.put("vehicle_vin_number", VinNumberGenerator.Vin_Number_Generator_22);
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


        // ***************** Change Vehicle Driver  ************************* :


        String Change_Vehicle_Driver_URL = "v1/companies/"+Company_ID+"/vehicles/changeStatus/"+Vehicle_id;  // Change Vehicle Driver URL

        JSONObject Change_Vehicle_Driver_Body = new JSONObject();
        Change_Vehicle_Driver_Body.put("status_id", "2");

        Response Change_Vehicle_Driver_Response = apiobject.buildNewRequest(Change_Vehicle_Driver_URL, RestActions.RequestType.PUT)
                .setContentType(ContentType.JSON)
                .addHeader("Platform", Platform)
                .addHeader("Accept", "application/json")
                .addHeader("Accept-Language", "en")
                .setRequestBody(Change_Vehicle_Driver_Body)
                .performRequest();

        Validations.assertThat().response(Change_Vehicle_Driver_Response)
                .extractedJsonValue("message")
                .contains("Vehicle status updated successfully")
                .perform();

    }
}