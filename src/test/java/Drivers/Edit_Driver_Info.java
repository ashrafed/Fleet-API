package Drivers;

import Authantication.Login_Main;
import MainPackage.DataGenerator.AuthanticationDataGenerator;
import MainPackage.DataGenerator.DriversDataGenerator;
import MainPackage.Main_Variables;
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

import java.util.concurrent.TimeUnit;

import static Authantication.Login_Main.Platform;

public class Edit_Driver_Info {
    private RestActions apiobject;
    private Main_Variables main_variables;
    private AuthanticationDataGenerator authanticationDataGenerator;
    private DriversDataGenerator driversDataGenerator;
    private Login_Main login_main;
    private Create_Driver_Main create_driver_main;
    private Edit_Driver_Info_Main edit_driver_info_main;
    private Get_Drivers_Details_Main get_drivers_details_main;

    @BeforeClass
    public void beforClass() {
        apiobject = DriverFactory.getAPIDriver(Login_Main.Base_Url);
        main_variables = new Main_Variables(apiobject);
        authanticationDataGenerator = new AuthanticationDataGenerator(apiobject);
        driversDataGenerator = new DriversDataGenerator(apiobject);

        login_main = new Login_Main(apiobject);
        create_driver_main = new Create_Driver_Main(apiobject);
        edit_driver_info_main = new Edit_Driver_Info_Main(apiobject);
        get_drivers_details_main = new Get_Drivers_Details_Main(apiobject);

        login_main.Login_Successfully_English(
                authanticationDataGenerator.Email_Address_1,
                authanticationDataGenerator.Password);
    }

    /*

     * [] validate data send in API
     * [] validate API response body
     * [] validate response status code
     * [] validate API response time
     * [] validate response Structure
     * [] validate API type (get,post,delete)
     * [] validate response in multiple languages (ar,en)

     */

    @Test
    @Link("https://carefer.atlassian.net/browse/CP-3539")
    public void TC_01_Edit_Driver_Info_Successfully_English_And_Arabic() {

        // ********  Create Driver ******* :
        Response create_driver_Response =  create_driver_main.Create_Driver_Successfully_English(
                driversDataGenerator.Driver_Name_Generator,
                driversDataGenerator.Register_PhoneNumber_Generattor_5,
                driversDataGenerator.Residence_Number_Generattor,
                driversDataGenerator.job_Data_Generattor,
                driversDataGenerator.license_expire_date,
                driversDataGenerator.City_Id_Generattor,
                "12");

        int Driver_ID = create_driver_Response.jsonPath().get("data.user_id");   // Get Driver ID



        //********  Edit Driver English ******* :

        String Edit_Driver_Info_URL = "v1/companies/1/drivers/"+Driver_ID;

        JSONObject Edit_Driver_Info_Body = new JSONObject();
        Edit_Driver_Info_Body.put("status_id", 2);
        Edit_Driver_Info_Body.put("name", driversDataGenerator.Driver_Name_Generator);
        Edit_Driver_Info_Body.put("residence_number", driversDataGenerator.Residence_Number_Generattor_3);
        Edit_Driver_Info_Body.put("license_expire_date", driversDataGenerator.license_expire_date);
        Edit_Driver_Info_Body.put("mobile" , driversDataGenerator.Register_PhoneNumber_Generattor_5);
        Edit_Driver_Info_Body.put("city_id", driversDataGenerator.City_Id_Generattor);
        Edit_Driver_Info_Body.put("area_id", "12");

        Response Edit_Driver_Info_Response_English = apiobject.buildNewRequest(Edit_Driver_Info_URL, RestActions.RequestType.PUT)
                .setContentType(ContentType.JSON)
                .addHeader("Platform", Platform)
                .addHeader("Accept", "application/json")
                .addHeader("Accept-Language", "en")
                .setRequestBody(Edit_Driver_Info_Body)
                .performRequest();


        Validations.assertThat().response(Edit_Driver_Info_Response_English)
                .extractedJsonValue("message")
                .contains("Driver information updated successfully")
                .perform();

        // ******** Edit Driver Arabic ******** :


        Response Edit_Driver_Info_Response_Arabic = apiobject.buildNewRequest(Edit_Driver_Info_URL, RestActions.RequestType.PUT)
                .setContentType(ContentType.JSON)
                .addHeader("Platform", Platform)
                .addHeader("Accept", "application/json")
                .addHeader("Accept-Language", "ar")
                .setRequestBody(Edit_Driver_Info_Body)
                .performRequest();


        Validations.assertThat().response(Edit_Driver_Info_Response_Arabic)
                .extractedJsonValue("message")
                .contains("تم تعديل بيانات السائق بنجاح")
                .perform();



        long TimeOut = Edit_Driver_Info_Response_English.getTimeIn(TimeUnit.MILLISECONDS);
        Validations.assertThat()
                .number(TimeOut)
                .isLessThanOrEquals(Main_Variables.timOut)
                .withCustomReportMessage("Validate Response Time")
                .perform();

    }
    @Test
    public void TC_03_Check_Edit_Driver_Info_Mandatory_Fields_English() {

        Response Edit_Driver_Info_Response = edit_driver_info_main.Edit_Driver_Info_Main_Faild_English(
                "",
                "",
                "",
                "",
                "",
                "",
                "");


        Validations.assertThat().response(Edit_Driver_Info_Response)
                .extractedJsonValue("errors")
                .contains("Residence number must be string")
                .perform();

        Validations.assertThat().response(Edit_Driver_Info_Response)
                .extractedJsonValue("errors")
                .contains("Name must be string")
                .perform();

        Validations.assertThat().response(Edit_Driver_Info_Response)
                .extractedJsonValue("errors")
                .contains("City id must be numeric")
                .perform();



        Validations.assertThat().response(Edit_Driver_Info_Response)
                .extractedJsonValue("errors")
                .contains("License expire date must be valid date")
                .perform();

        Validations.assertThat().response(Edit_Driver_Info_Response)
                .extractedJsonValue("errors")
                .contains("Status is invalid")
                .perform();


        long TimeOut = Edit_Driver_Info_Response.getTimeIn(TimeUnit.MILLISECONDS);
        Validations.assertThat()
                .number(TimeOut)
                .isLessThanOrEquals(Main_Variables.timOut)
                .withCustomReportMessage("Validate Response Time")
                .perform();
    }
    @Test
    public void TC_04_Check_Edit_Driver_Info_Mandatory_Fields_Arabic() {

        Response Edit_Driver_Info_Response = edit_driver_info_main.Edit_Driver_Info_Main_Faild_Arabic(
                "",
                "",
                "",
                "",
                "",
                "",
                "");


        Validations.assertThat().response(Edit_Driver_Info_Response)
                .extractedJsonValue("errors")
                .contains("يجب أن يكون الإسم حروف")
                .perform();

        Validations.assertThat().response(Edit_Driver_Info_Response)
                .extractedJsonValue("errors")
                .contains(" يجب أن تكون المدينة رقم")
                .perform();


        Validations.assertThat().response(Edit_Driver_Info_Response)
                .extractedJsonValue("errors")
                .contains(" يجب أن يكون تاريخ إنتهاء رخصة القيادة تاريخ صالح")
                .perform();

        Validations.assertThat().response(Edit_Driver_Info_Response)
                .extractedJsonValue("errors")
                .contains("الحالة غير صالحة")
                .perform();


        long TimeOut = Edit_Driver_Info_Response.getTimeIn(TimeUnit.MILLISECONDS);
        Validations.assertThat()
                .number(TimeOut)
                .isLessThanOrEquals(Main_Variables.timOut)
                .withCustomReportMessage("Validate Response Time")
                .perform();
    }

    @Description("\"Mobile already taken\",\n" +
            "    \"Residence number must be string\",\n" +
            "    \"Name must be string\",\n" +
            "    \"City id must be numeric\",\n" +
            "    \"License expire date must be valid date\",\n" +
            "    \"Status is invalid\"")
    @Test
    public void TC_05_Check_Edit_Driver_Info_All_Error_Keys_English() {

        Response Edit_Driver_Info_Response = edit_driver_info_main.Edit_Driver_Info_Main_Error_Keys_English(
                "",
                1,
                1,
                1,
                "966504810293",
                "test",
                "tets");


//        Validations.assertThat().response(Edit_Driver_Info_Response)
//                .extractedJsonValue("errors")
//                .contains("Mobile already taken")
//                .perform();


        Validations.assertThat().response(Edit_Driver_Info_Response)
                .extractedJsonValue("errors")
                .contains("Residence number must be string")
                .perform();

        Validations.assertThat().response(Edit_Driver_Info_Response)
                .extractedJsonValue("errors")
                .contains("Name must be string")
                .perform();


        Validations.assertThat().response(Edit_Driver_Info_Response)
                .extractedJsonValue("errors")
                .contains("City id must be numeric")
                .perform();




        Validations.assertThat().response(Edit_Driver_Info_Response)
                .extractedJsonValue("errors")
                .contains("License expire date must be valid date")
                .perform();


        Validations.assertThat().response(Edit_Driver_Info_Response)
                .extractedJsonValue("errors")
                .contains("Status is invalid")
                .perform();


        long TimeOut = Edit_Driver_Info_Response.getTimeIn(TimeUnit.MILLISECONDS);
        Validations.assertThat()
                .number(TimeOut)
                .isLessThanOrEquals(Main_Variables.timOut)
                .withCustomReportMessage("Validate Response Time")
                .perform();
    }

    @Description("    \"رقم الجوال موجود بالفعل\",\n" +
            "    \" يجب أن يكون رقم السكن حروفاً\",\n" +
            "    \"يجب أن يكون الإسم حروف\",\n" +
            "    \" يجب أن تكون المدينة رقم\",\n" +
            "    \" يجب أن يكون تاريخ إنتهاء رخصة القيادة تاريخ صالح\",\n" +
            "    \"الحالة غير صالحة\"")
    @Test
    public void TC_06_Check_Edit_Driver_Info_All_Error_Keys_Arabic() {

        Response Edit_Driver_Info_Response = edit_driver_info_main.Edit_Driver_Info_Main_Error_Keys_Arabic(
                "",
                1,
                1,
                1,
                "966504810293",
                "test",
                "tets");


//        Validations.assertThat().response(Edit_Driver_Info_Response)
//                .extractedJsonValue("errors")
//                .contains("رقم الجوال موجود بالفعل")
//                .perform();

        Validations.assertThat().response(Edit_Driver_Info_Response)
                .extractedJsonValue("errors")
                .contains("يجب أن يكون رقم السكن حروفا")
                .perform();

        Validations.assertThat().response(Edit_Driver_Info_Response)
                .extractedJsonValue("errors")
                .contains("يجب أن يكون الإسم حروف")
                .perform();

        Validations.assertThat().response(Edit_Driver_Info_Response)
                .extractedJsonValue("errors")
                .contains("يجب أن تكون المدينة رقم")
                .perform();


        Validations.assertThat().response(Edit_Driver_Info_Response)
                .extractedJsonValue("errors")
                .contains(" يجب أن يكون تاريخ إنتهاء رخصة القيادة تاريخ صالح")
                .perform();

        Validations.assertThat().response(Edit_Driver_Info_Response)
                .extractedJsonValue("errors")
                .contains("الحالة غير صالحة")
                .perform();

        long TimeOut = Edit_Driver_Info_Response.getTimeIn(TimeUnit.MILLISECONDS);
        Validations.assertThat()
                .number(TimeOut)
                .isLessThanOrEquals(Main_Variables.timOut)
                .withCustomReportMessage("Validate Response Time")
                .perform();
    }



}

