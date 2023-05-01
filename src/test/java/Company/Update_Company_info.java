package Company;

import Authantication.Login_Main;
import MainPackage.DataGenerator.AuthanticationDataGenerator;
import MainPackage.DataGenerator.CompanyDataGenerator;
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

public class Update_Company_info {
    private RestActions apiobject;
    private Main_Variables main_variables;
    private AuthanticationDataGenerator authanticationDataGenerator;
    private Login_Main login_main;
    private Create_Company_Main create_company_main;
    private CompanyDataGenerator companyDataGenerator;
    @BeforeClass
    public void BeforClass() {
        apiobject = DriverFactory.getAPIDriver(Login_Main.Base_Url);
        main_variables = new Main_Variables(apiobject);
        authanticationDataGenerator = new AuthanticationDataGenerator(apiobject);
        login_main = new Login_Main(apiobject);

        create_company_main = new Create_Company_Main(apiobject);

        companyDataGenerator = new CompanyDataGenerator(apiobject);

        login_main.Login_Successfully_English(
                authanticationDataGenerator.Email_Address,
                authanticationDataGenerator.Password);

    }
    @Test
    public void TC_01_Update_Company_Successfully_English_And_Arabic() {

        // *********** Create_Company  ************************ :

        Response Create_Company_Successfully_Response_English = create_company_main.Create_Company_Successfully_English(
                companyDataGenerator.Company_Name,
                companyDataGenerator.contact_number,
                companyDataGenerator.Email_Address_1,
                companyDataGenerator.commercial_number,
                companyDataGenerator.additional_amount,
                companyDataGenerator.admin_name,
                "123456");



       int Company_ID =Create_Company_Successfully_Response_English.jsonPath().get("data.id");  // Get Company ID :


        //*****************  Update Company  ********************************:

        //*****************  Update Company English  ********************************:

        String   Update_Compnay_URL = "v1/companies/"+Company_ID;

        JSONObject Create_Company_Body =new JSONObject();

        Create_Company_Body.put("name" ,companyDataGenerator.Company_Name_1);
        Create_Company_Body.put("contact_number" , companyDataGenerator.contact_number_5);
        Create_Company_Body.put("email" , companyDataGenerator.Email_Address_6);
        Create_Company_Body.put("commercial_number" ,companyDataGenerator.commercial_number_5);
        Create_Company_Body.put("additional_amount" ,companyDataGenerator.additional_amount);
        Create_Company_Body.put("admin_name" ,companyDataGenerator.admin_name);




        Response Update_Compnay_Response_English  = apiobject.buildNewRequest(Update_Compnay_URL , RestActions.RequestType.PUT)
                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept" , "application/json")
                .addHeader("Accept-Language", "en")
                .setRequestBody(Create_Company_Body)
                .performRequest();

        Validations.assertThat().response(Update_Compnay_Response_English)
                .extractedJsonValue("message")
                .contains("Company information updated successfully")
                .perform();


        //*****************  Update Company Arabic  ********************************:

        Response Update_Compnay_Response_Arabic  = apiobject.buildNewRequest(Update_Compnay_URL , RestActions.RequestType.PUT)
                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept" , "application/json")
                .addHeader("Accept-Language", "ar")
                .setRequestBody(Create_Company_Body)
                .performRequest();

        Validations.assertThat().response(Update_Compnay_Response_Arabic)
                .extractedJsonValue("message")
                .contains("تم تعديل بيانات الشركة بنجاح")
                .perform();

    }

}
