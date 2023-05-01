package Drivers;

import Authantication.Login_Main;
import MainPackage.DataGenerator.AuthanticationDataGenerator;
import MainPackage.DataGenerator.DriversDataGenerator;
import MainPackage.Main_Variables;
import com.shaft.api.RestActions;
import com.shaft.driver.DriverFactory;
import com.shaft.validation.Validations;
import io.qameta.allure.Link;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static Authantication.Login_Main.Platform;

public class Update_Driver_Status {
    private RestActions apiobject;
    private Main_Variables main_variables;
    private AuthanticationDataGenerator authanticationDataGenerator;
    private DriversDataGenerator driversDataGenerator;

    private Login_Main login_main;
    private Create_Driver_Main create_driver_main;
    private Get_Drivers_Details_Main get_drivers_details_main;

    private Update_Driver_Status_Main update_driver_status_main;

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
    public void beforClass(){
        apiobject = DriverFactory.getAPIDriver(Login_Main.Base_Url);
        main_variables = new Main_Variables(apiobject);
        authanticationDataGenerator = new AuthanticationDataGenerator(apiobject);
        driversDataGenerator = new DriversDataGenerator(apiobject);

        login_main = new Login_Main(apiobject);
        create_driver_main = new Create_Driver_Main(apiobject);

        get_drivers_details_main = new Get_Drivers_Details_Main(apiobject);
        update_driver_status_main = new Update_Driver_Status_Main(apiobject);

        login_main.Login_Successfully_English(
                authanticationDataGenerator.Email_Address_1
                ,authanticationDataGenerator.Password);
    }

    @Test
    @Link("https://carefer.atlassian.net/browse/CP-3558")
    public void TC_01_Update_Driver_Status_Successfully_English_And_Arabic(){
        /*
        Create_driver :
         */
       Response create_driver_Response = create_driver_main.Create_Driver_Successfully_English(
                driversDataGenerator.Driver_Name_Generator,
                driversDataGenerator.Register_PhoneNumber_Generattor,
                driversDataGenerator.Residence_Number_Generattor_5,
                driversDataGenerator.job_Data_Generattor,
                "2021-04-03",
                driversDataGenerator.City_Id_Generattor,
                "12");

        int Driver_ID = create_driver_Response.jsonPath().get("data.user_id");   //Driver ID Path

        String Update_Driver_Status_URL = "v1/companies/1/drivers/changeStatus/"+Driver_ID;  // URL


        /*
        Update_Driver_Status_Body :
         */
        JSONObject Update_Driver_Status_Body = new JSONObject();
        Update_Driver_Status_Body.put("status_id" ,1);

        /*
        Update_Driver English  :
         */


        Response Update_Driver_Status_Response_English = apiobject.buildNewRequest(Update_Driver_Status_URL , RestActions.RequestType.PUT)
                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept" , "application/json")
                .addHeader("Accept-Language", "en")
                .setRequestBody(Update_Driver_Status_Body)
                .performRequest();


        Validations.assertThat().response(Update_Driver_Status_Response_English)
                .extractedJsonValue("message")
                .contains("Driver status updated successfully")
                .perform();


         /*
        Update_Driver Arabic  :
         */


        Response Update_Driver_Status_Response_Arabic = apiobject.buildNewRequest(Update_Driver_Status_URL , RestActions.RequestType.PUT)
                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept" , "application/json")
                .addHeader("Accept-Language", "ar")
                .setRequestBody(Update_Driver_Status_Body)
                .performRequest();


        Validations.assertThat().response(Update_Driver_Status_Response_Arabic)
                .extractedJsonValue("message")
                .contains("تم تعديل حالة السائق بنجاح")
                .perform();


        long TimeOut = Update_Driver_Status_Response_English.getTimeIn(TimeUnit.MILLISECONDS);
        Validations.assertThat()
                .number(TimeOut)
                .isLessThanOrEquals(Main_Variables.timOut)
                .withCustomReportMessage("Validate Response Time")
                .perform();
    }


    @Test
    @Link("https://carefer.atlassian.net/browse/CP-3559")
    public void TC_03_Update_Driver_Status_Mandatory_Input_English(){

        Response Update_Driver_Status_Response = update_driver_status_main.Update_Driver_Status_Fail_English(
                "");

        Validations.assertThat().response(Update_Driver_Status_Response)
                .extractedJsonValue("errors")
                .contains("Status is required")
                .perform();

        long TimeOut = Update_Driver_Status_Response.getTimeIn(TimeUnit.MILLISECONDS);
        Validations.assertThat()
                .number(TimeOut)
                .isLessThanOrEquals(Main_Variables.timOut)
                .withCustomReportMessage("Validate Response Time")
                .perform();

    }

    @Test
    @Link("https://carefer.atlassian.net/browse/CP-3559")
    public void TC_04_Update_Driver_Status_Mandatory_Input_Arabic(){

        Response Update_Driver_Status_Response = update_driver_status_main.Update_Driver_Status_Fail_Arabic(
                "");

        Validations.assertThat().response(Update_Driver_Status_Response)
                .extractedJsonValue("errors")
                .contains("الحالة مطلوبة")
                .perform();


        long TimeOut = Update_Driver_Status_Response.getTimeIn(TimeUnit.MILLISECONDS);
        Validations.assertThat()
                .number(TimeOut)
                .isLessThanOrEquals(Main_Variables.timOut)
                .withCustomReportMessage("Validate Response Time")
                .perform();
    }


    @Test
    public void TC_05_Update_Driver_Status_Is_Invalid_English(){

        Response Update_Driver_Status_Response = update_driver_status_main.Update_Driver_Status_Fail_English(
                "test");

        Validations.assertThat().response(Update_Driver_Status_Response)
                .extractedJsonValue("errors")
                .contains("Status is invalid")
                .perform();


        long TimeOut = Update_Driver_Status_Response.getTimeIn(TimeUnit.MILLISECONDS);
        Validations.assertThat()
                .number(TimeOut)
                .isLessThanOrEquals(Main_Variables.timOut)
                .withCustomReportMessage("Validate Response Time")
                .perform();

    }

    @Test

    public void TC_06_Update_Driver_Status_Is_Invalid_Arabic(){

        Response Update_Driver_Status_Response = update_driver_status_main.Update_Driver_Status_Fail_Arabic(
                "test");

        Validations.assertThat().response(Update_Driver_Status_Response)
                .extractedJsonValue("errors")
                .contains("الحالة غير صالحة")
                .perform();


        long TimeOut = Update_Driver_Status_Response.getTimeIn(TimeUnit.MILLISECONDS);
        Validations.assertThat()
                .number(TimeOut)
                .isLessThanOrEquals(Main_Variables.timOut)
                .withCustomReportMessage("Validate Response Time")
                .perform();
    }


}
