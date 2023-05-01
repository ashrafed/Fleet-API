package Drivers;

import Authantication.Login_Main;
import Company.Create_Company_Main;
import MainPackage.DataGenerator.AuthanticationDataGenerator;
import MainPackage.DataGenerator.CompanyDataGenerator;
import MainPackage.DataGenerator.DriversDataGenerator;
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

public class Create_Driver {
    private static RestActions apiobject ;
    private Main_Variables main_variables;
    private AuthanticationDataGenerator authanticationDataGenerator;
    private DriversDataGenerator driversDataGenerator;
    private Login_Main login_main;
    private Create_Company_Main create_company_main;
    private CompanyDataGenerator companyDataGenerator;

    private Create_Driver_Main create_driver_main;
    private Edit_Driver_Info_Main edit_driver_info_main;




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
        authanticationDataGenerator = new AuthanticationDataGenerator(apiobject);
        driversDataGenerator = new DriversDataGenerator(apiobject);


        login_main = new Login_Main(apiobject);
        create_company_main = new Create_Company_Main(apiobject);
        companyDataGenerator = new CompanyDataGenerator(apiobject);

        create_driver_main = new Create_Driver_Main(apiobject);

        edit_driver_info_main = new Edit_Driver_Info_Main(apiobject);


        login_main.Login_Successfully_English(
                authanticationDataGenerator.Email_Address_1 ,
                authanticationDataGenerator.Password);
    }

    @Test
    public  void TC_01_Create_Driver_Successfully_English(){

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

        String Create_Driver_URL ="v1/companies/"+Company_ID+"/drivers";      //Create Driver URL :

        JSONObject Create_Driver_Body =new JSONObject();
        Create_Driver_Body.put("name" , driversDataGenerator.Driver_Name_Generator);
        Create_Driver_Body.put("mobile",driversDataGenerator.Register_PhoneNumber_Generattor);
        Create_Driver_Body.put("residence_number",driversDataGenerator.Residence_Number_Generattor);
        Create_Driver_Body.put("job", driversDataGenerator.job_Data_Generattor);
        Create_Driver_Body.put("license_expire_date",driversDataGenerator.license_expire_date);
        Create_Driver_Body.put("city_id",driversDataGenerator.City_Id_Generattor);
        Create_Driver_Body.put("area_id","12");


        Response create_driver_Response =   apiobject.buildNewRequest(Create_Driver_URL , RestActions.RequestType.POST)
                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept" , "application/json")
                .addHeader("Accept-Language", "en")
                .setRequestBody(Create_Driver_Body)
                .setTargetStatusCode(201)
                .performRequest();


        Validations.assertThat().response(create_driver_Response)
                .extractedJsonValue("message")
                .contains("Driver stored successfully")
                .perform();


        long TimeOut = create_driver_Response.getTimeIn(TimeUnit.MILLISECONDS);
        Validations.assertThat()
                .number(TimeOut)
                .isLessThanOrEquals(Main_Variables.timOut)
                .withCustomReportMessage("Validate Response Time")
                .perform();

    }


    @Test
    public void TC_03_Check_Create_Driver_Field_Mandatory_English(){
        Response create_driver_Response =  create_driver_main.Create_Driver_Fail_English(
                "",
                "",
                "",
                "",
                "",
                "",
                "");


        Validations.assertThat().response(create_driver_Response)
                .extractedJsonValue("errors")
                .contains("Mobile is mandatory")
                .perform();

        Validations.assertThat().response(create_driver_Response)
                .extractedJsonValue("errors")
                .contains("Residence number is mandatory")
                .perform();

        Validations.assertThat().response(create_driver_Response)
                .extractedJsonValue("errors")
                .contains("Name is mandatory")
                .perform();

        Validations.assertThat().response(create_driver_Response)
                .extractedJsonValue("errors")
                .contains("City id is mandatory")
                .perform();




        Validations.assertThat().response(create_driver_Response)
                .extractedJsonValue("errors")
                .contains("Job is mandatory")
                .perform();

        Validations.assertThat().response(create_driver_Response)
                .extractedJsonValue("errors")
                .contains("The license expire date field is required.")
                .perform();

        long TimeOut = create_driver_Response.getTimeIn(TimeUnit.MILLISECONDS);
        Validations.assertThat()
                .number(TimeOut)
                .isLessThanOrEquals(Main_Variables.timOut)
                .withCustomReportMessage("Validate Response Time")
                .perform();
    }

    @Test
    public void TC_04_Check_Create_Driver_Field_Mandatory_Arabic(){
        Response create_driver_Response =  create_driver_main.Create_Driver_Fail_Arabic(
                "",
                "",
                "",
                "",
                "",
                "",
                "");


        Validations.assertThat().response(create_driver_Response)
                .extractedJsonValue("errors")
                .contains("رقم الجوال مطلوب")
                .perform();

        Validations.assertThat().response(create_driver_Response)
                .extractedJsonValue("errors")
                .contains("رقم السكن مطلوب")
                .perform();

        Validations.assertThat().response(create_driver_Response)
                .extractedJsonValue("errors")
                .contains("الإسم مطلوب")
                .perform();

        Validations.assertThat().response(create_driver_Response)
                .extractedJsonValue("errors")
                .contains("المدينة مطلوبة")
                .perform();


        Validations.assertThat().response(create_driver_Response)
                .extractedJsonValue("errors")
                .contains("الوظيفة مطلوبة")
                .perform();

        Validations.assertThat().response(create_driver_Response)
                .extractedJsonValue("errors")
                .contains("الحقل license expire date مطلوب.")
                .perform();

        long TimeOut = create_driver_Response.getTimeIn(TimeUnit.MILLISECONDS);
        Validations.assertThat()
                .number(TimeOut)
                .isLessThanOrEquals(Main_Variables.timOut)
                .withCustomReportMessage("Validate Response Time")
                .perform();



    }

    @Test
    public void TC_05_Check_Create_Driver_Invalid_Input_Data_English(){
        Response create_driver_Response =  create_driver_main.Create_Driver_Fail_English(
                ";",
                "Test Automation",
                "Test Automation",
                "1",
                "Test Automation",
                "Test Automation",
                "Test Automation");


        Validations.assertThat().response(create_driver_Response)
                .extractedJsonValue("errors")
                .contains("Mobile must be numeric")
                .perform();

        Validations.assertThat().response(create_driver_Response)
                .extractedJsonValue("errors")
                .contains("Login mobile format is invalid")
                .perform();

        Validations.assertThat().response(create_driver_Response)
                .extractedJsonValue("errors")
                .contains("City id must be numeric")
                .perform();



        Validations.assertThat().response(create_driver_Response)
                .extractedJsonValue("errors")
                .contains("License expire date must be valid date")
                .perform();


        long TimeOut = create_driver_Response.getTimeIn(TimeUnit.MILLISECONDS);
        Validations.assertThat()
                .number(TimeOut)
                .isLessThanOrEquals(Main_Variables.timOut)
                .withCustomReportMessage("Validate Response Time")
                .perform();

    }

    @Test
    public void TC_06_Check_Create_Driver_Invalid_Input_Data_Arabic(){
        Response create_driver_Response =  create_driver_main.Create_Driver_Fail_Arabic(
                ";",
                "Test Automation",
                "Test Automation",
                ";",
                "Test Automation",
                "Test Automation",
                "Test Automation");


        Validations.assertThat().response(create_driver_Response)
                .extractedJsonValue("errors")
                .contains("رقم الجوال يجب أن يكون رقم")
                .perform();

        Validations.assertThat().response(create_driver_Response)
                .extractedJsonValue("errors")
                .contains("خطأ فى تنسيق رقم الجوال")
                .perform();

        Validations.assertThat().response(create_driver_Response)
                .extractedJsonValue("errors")
                .contains("يجب أن تكون المدينة رقم")
                .perform();



        Validations.assertThat().response(create_driver_Response)
                .extractedJsonValue("errors")
                .contains("يجب أن يكون تاريخ إنتهاء رخصة القيادة تاريخ صالح")
                .perform();


        long TimeOut = create_driver_Response.getTimeIn(TimeUnit.MILLISECONDS);
        Validations.assertThat()
                .number(TimeOut)
                .isLessThanOrEquals(Main_Variables.timOut)
                .withCustomReportMessage("Validate Response Time")
                .perform();
    }

    @Description("Check Error Keys " + "\"Mobile already taken\",\n" +
            "    \"Residence number must be string\",\n" +
            "    \"Name must be string\",\n" +
            "    \"City id must be numeric\",\n" +
            "    \"Job must be string\",\n" +
            "    \"License expire date must be valid date\"")
    @Test
    public void TC_07_Check_Create_Driver_ALL_Error_keys_English(){
        Response create_driver_Response =  create_driver_main.Create_Driver_Fail_Error_Keys_English(
                1,
                "966504810293",
                12,
                1,
                12,
                "TEST",
                "test");


        Validations.assertThat().response(create_driver_Response)
                .extractedJsonValue("errors")
                .contains("Residence number must be string")
                .perform();

        Validations.assertThat().response(create_driver_Response)
                .extractedJsonValue("errors")
                .contains("Name must be string")
                .perform();

        Validations.assertThat().response(create_driver_Response)
                .extractedJsonValue("errors")
                .contains("City id must be numeric")
                .perform();



        Validations.assertThat().response(create_driver_Response)
                .extractedJsonValue("errors")
                .contains("Job must be string")
                .perform();

        Validations.assertThat().response(create_driver_Response)
                .extractedJsonValue("errors")
                .contains("License expire date must be valid date")
                .perform();



        long TimeOut = create_driver_Response.getTimeIn(TimeUnit.MILLISECONDS);
        Validations.assertThat()
                .number(TimeOut)
                .isLessThanOrEquals(Main_Variables.timOut)
                .withCustomReportMessage("Validate Response Time")
                .perform();

    }

    @Description("\"رقم الجوال موجود بالفعل\",\n" +
            "    \" يجب أن يكون رقم السكن حروفاً\",\n" +
            "    \"يجب أن يكون الإسم حروف\",\n" +
            "    \" يجب أن تكون المدينة رقم\",\n" +
            "    \"يجب أن تكون الوظيفة حروف\",\n" +
            "    \" يجب أن يكون تاريخ إنتهاء رخصة القيادة تاريخ صالح\"")
    @Test
    public void TC_08_Check_Create_Driver_ALL_Error_keys_Arabic(){
        Response create_driver_Response =  create_driver_main.Create_Driver_Fail_Error_Keys_Arabic(
                1,
                "966504810293",
                12,
                1,
                12,
                "TEST",
                "test"
        );


        Validations.assertThat().response(create_driver_Response)
                .extractedJsonValue("errors")
                .contains(" يجب أن يكون رقم السكن حروفاً")
                .perform();

        Validations.assertThat().response(create_driver_Response)
                .extractedJsonValue("errors")
                .contains("يجب أن يكون الإسم حروف")
                .perform();

        Validations.assertThat().response(create_driver_Response)
                .extractedJsonValue("errors")
                .contains(" يجب أن تكون المدينة رقم")
                .perform();

        Validations.assertThat().response(create_driver_Response)
                .extractedJsonValue("errors")
                .contains("يجب أن تكون الوظيفة حروف")
                .perform();

        Validations.assertThat().response(create_driver_Response)
                .extractedJsonValue("errors")
                .contains(" يجب أن يكون تاريخ إنتهاء رخصة القيادة تاريخ صالح")
                .perform();



        long TimeOut = create_driver_Response.getTimeIn(TimeUnit.MILLISECONDS);
        Validations.assertThat()
                .number(TimeOut)
                .isLessThanOrEquals(Main_Variables.timOut)
                .withCustomReportMessage("Validate Response Time")
                .perform();

    }


//
//    @Test
//    public static void TC(){
//
//        JSONObject Create_Body =new JSONObject();
//
//        Create_Body.put("name" ,"name");
//        Create_Body.put("mobile","mobile");
//
//
//        Response create_Response = apiobject.buildNewRequest("URL" , RestActions.RequestType.POST)
//                .setContentType(ContentType.JSON)
//                .addHeader("Platform" , Platform)
//                .addHeader("Accept" , "application/json")
//                .addHeader("Accept-Language", "en")
//                .setRequestBody(Create_Body)
//                .setTargetStatusCode(201)
//                .performRequest();
//
//        int ID = create_Response.jsonPath().get("data.user_id");   // Get ID
//
//    }

    }