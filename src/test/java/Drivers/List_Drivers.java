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

import static Authantication.Login_Main.Platform;

public class List_Drivers {
    private RestActions apiobject;
    private Main_Variables main_variables;
    private AuthanticationDataGenerator authanticationDataGenerator;
    private DriversDataGenerator driversDataGenerator;
    private Login_Main login_main;

    private Create_Company_Main create_company_main;
    private Create_Driver_Main create_driver_main;
    private Edit_Driver_Info_Main edit_driver_info_main;
    private Get_Drivers_Details_Main get_drivers_details_main;
    private List_Drivers_Main list_drivers_main;

    private CompanyDataGenerator companyDataGenerator;



    @BeforeClass
    public void beforClass(){
        apiobject = DriverFactory.getAPIDriver(Login_Main.Base_Url);
        main_variables =new Main_Variables(apiobject);
        authanticationDataGenerator = new AuthanticationDataGenerator(apiobject);
        driversDataGenerator = new DriversDataGenerator(apiobject);

        login_main = new Login_Main(apiobject);
        create_company_main = new Create_Company_Main(apiobject);
        create_driver_main =new Create_Driver_Main(apiobject);
        edit_driver_info_main= new Edit_Driver_Info_Main(apiobject);
        get_drivers_details_main = new Get_Drivers_Details_Main(apiobject);

        list_drivers_main = new List_Drivers_Main(apiobject);

        companyDataGenerator= new CompanyDataGenerator(apiobject);


        login_main.Login_Successfully_English(
                authanticationDataGenerator.Email_Address_1 ,
                authanticationDataGenerator.Password);
    }


    @Test
    public void TC_01_List_Drivers_Successfully_English_And_Arabic(){


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

        // ************** List Drivers   **************************:

        // ************** List Drivers  English  **************************:

        String  List_Drivers_URL = "v1/companies/"+Company_ID+"/drivers?limit=30";  // List_Drivers_URL

        Response List_Drivers_Response =  apiobject.buildNewRequest(List_Drivers_URL , RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept" , "application/json")
                .addHeader("Accept-Language", "en")
                .performRequest();


        Validations.assertThat().response(List_Drivers_Response).extractedJsonValue("message")
                .contains("Drivers retrieved successfully")
                .perform();


        // ************** List Drivers  Arabic  **************************:

        Response List_Drivers_Response_Arabic =  apiobject.buildNewRequest(List_Drivers_URL , RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept" , "application/json")
                .addHeader("Accept-Language", "ar")
                .performRequest();


        Validations.assertThat().response(List_Drivers_Response_Arabic)
                .extractedJsonValue("message")
                .contains("تم إسترجاع السائقين بنجاح")
                .perform();

    }


    @Description("\"Limit must be numeric\",\n" +
            "    \"Login mobile format is invalid\",\n" +
            "    \"Residence number must be numeric\",\n" +
            "    \"City id must be numeric\",\n" +
            "    \"Date from must be date\",\n" +
            "    \"Date to must be date\"")
    @Test
    public void TC_03_List_Drivers_ALL_Error_Keys_English(){
        String  List_Drivers_URL = "v1/companies/1/drivers?name=12&city_id=ss&limit=ff&status=5&created_at_from=ss&created_at_to=333&mobile=966506425182111&residence_number=aa";

         Response List_Drivers_Response = apiobject.buildNewRequest(List_Drivers_URL , RestActions.RequestType.GET)
                    .setContentType(ContentType.JSON)
                    .addHeader("Platform" , "careferFleet.eyJhbGciOiJ2Ej!%IUzI1NiIs")
                    .addHeader("Accept" , "application/json")
                    .addHeader("Accept-Language", "en")
                    .setTargetStatusCode(422)
                    .performRequest();

         Validations.assertThat().response(List_Drivers_Response)
                 .extractedJsonValue("errors")
                 .contains("Limit must be numeric")
                 .perform();


        Validations.assertThat().response(List_Drivers_Response)
                .extractedJsonValue("errors")
                .contains("Login mobile format is invalid")
                .perform();



        Validations.assertThat().response(List_Drivers_Response)
                .extractedJsonValue("errors")
                .contains("City id must be numeric")
                .perform();


        Validations.assertThat().response(List_Drivers_Response)
                .extractedJsonValue("errors")
                .contains("Date from must be date")
                .perform();

        Validations.assertThat().response(List_Drivers_Response)
                .extractedJsonValue("errors")
                .contains("Date to must be date")
                .perform();


    }


    @Description("\"التحديد يجب أن يكون رقم\",\n" +
            "    \"خطأ فى تنسيق رقم الجوال\",\n" +
            "    \"رقم الأقامة يجب أن يكون رقم\",\n" +
            "    \" يجب أن تكون المدينة رقم\",\n" +
            "    \"بداية التاريخ يجب أن يكون تاريخ صالح\",\n" +
            "    \"نهاية التاريخ يجب أن يكون تاريخ صالح\"")
    @Test
    public void TC_04_List_Drivers_ALL_Error_Keys_Arabic(){
        String  List_Drivers_URL = "v1/companies/1/drivers?name=12&city_id=ss&limit=ff&status=5&created_at_from=ss&created_at_to=333&mobile=966506425182111&residence_number=aa";

        Response List_Drivers_Response = apiobject.buildNewRequest(List_Drivers_URL , RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .addHeader("Platform" , "careferFleet.eyJhbGciOiJ2Ej!%IUzI1NiIs")
                .addHeader("Accept" , "application/json")
                .addHeader("Accept-Language", "ar")
                .setTargetStatusCode(422)
                .performRequest();

        Validations.assertThat().response(List_Drivers_Response)
                .extractedJsonValue("errors")
                .contains("التحديد يجب أن يكون رقم")
                .perform();


        Validations.assertThat().response(List_Drivers_Response)
                .extractedJsonValue("errors")
                .contains("خطأ فى تنسيق رقم الجوال")
                .perform();

        Validations.assertThat().response(List_Drivers_Response)
                .extractedJsonValue("errors")
                .contains(" يجب أن تكون المدينة رقم")
                .perform();

        Validations.assertThat().response(List_Drivers_Response)
                .extractedJsonValue("errors")
                .contains("بداية التاريخ يجب أن يكون تاريخ صالح")
                .perform();

        Validations.assertThat().response(List_Drivers_Response)
                .extractedJsonValue("errors")
                .contains("نهاية التاريخ يجب أن يكون تاريخ صالح")
                .perform();





    }
}
