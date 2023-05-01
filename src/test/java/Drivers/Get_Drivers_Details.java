package Drivers;

import Authantication.Login_Main;
import Authantication.Logout_Main;
import Company.Create_Company_Main;
import MainPackage.DataGenerator.AuthanticationDataGenerator;
import MainPackage.DataGenerator.CompanyDataGenerator;
import MainPackage.DataGenerator.DriversDataGenerator;
import MainPackage.Main_Variables;
import com.shaft.api.RestActions;
import com.shaft.driver.DriverFactory;
import com.shaft.driver.SHAFT;
import com.shaft.validation.Validations;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static Authantication.Login_Main.Platform;

public class Get_Drivers_Details {
    private RestActions apiobject;
    private Login_Main login_main;

    private Get_Drivers_Details_Main get_drivers_details_main;

    private AuthanticationDataGenerator authanticationDataGenerator;
    private DriversDataGenerator driversDataGenerator;
    private Create_Company_Main create_company_main;
    private CompanyDataGenerator companyDataGenerator;


    /*

     * [] validate API response body
     * [] validate response status code
     * [] validate API response time
     * [] validate response Structure
     * [] validate API type (get,post,delete)
     * [] validate response in multiple languages (ar,en)

     */

    @BeforeClass
    public void beforClass() {
        apiobject = DriverFactory.getAPIDriver(Login_Main.Base_Url);
        login_main = new Login_Main(apiobject);
        authanticationDataGenerator = new AuthanticationDataGenerator(apiobject);
        driversDataGenerator = new DriversDataGenerator(apiobject);

        get_drivers_details_main = new Get_Drivers_Details_Main(apiobject);
        create_company_main = new Create_Company_Main(apiobject);

        companyDataGenerator = new CompanyDataGenerator(apiobject);

        login_main.Login_Successfully_English(
                authanticationDataGenerator.Email_Address_1,
                authanticationDataGenerator.Password);
    }


    @Test
    public void TC_01_Get_Drivers_Details_Successfully_English() {


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

        // ************** Get_Drivers_Details  ********************** :

        // ************** Get_Drivers_Details English  ********************** :


        String Get_Drivers_Details_URL = "v1/companies/" + Company_ID + "/drivers/" + Driver_ID;    // Get_Drivers_Details_URL

        Response Get_Drivers_Details_Response = apiobject.buildNewRequest(Get_Drivers_Details_URL, RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .addHeader("Platform", Platform)
                .addHeader("Accept", "application/json")
                .addHeader("Accept-Language", "en")
                .performRequest();


        Validations.assertThat().response(Get_Drivers_Details_Response)
                .extractedJsonValue("message")
                .contains("Driver information retrieved successfully")
                .perform();



        // ************** Get_Drivers_Details Arabic  ********************** :



        Response Get_Drivers_Details_Response_Arabic = apiobject.buildNewRequest(Get_Drivers_Details_URL, RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .addHeader("Platform", Platform)
                .addHeader("Accept", "application/json")
                .addHeader("Accept-Language", "ar")
                .performRequest();

        Validations.assertThat().response(Get_Drivers_Details_Response_Arabic)
                .extractedJsonValue("message")
                .contains("تم إسترجاع بيانات السائق بنجاح")
                .perform();


        long TimeOut = Get_Drivers_Details_Response.getTimeIn(TimeUnit.MILLISECONDS);
        Validations.assertThat()
                .number(TimeOut)
                .isLessThanOrEquals(Main_Variables.timOut)
                .withCustomReportMessage("Validate Response Time")
                .perform();
    }

}