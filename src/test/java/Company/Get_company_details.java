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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Authantication.Login_Main.Platform;

public class Get_company_details {
    private RestActions apiobject;
    private Main_Variables main_variables;
    private AuthanticationDataGenerator authanticationDataGenerator;
    private Login_Main login_main;
    private Create_Company_Main create_company_main;

    private Get_company_details_Main get_company_details_main;
    private CompanyDataGenerator companyDataGenerator;
    @BeforeClass
    public void BeforClass() {
        apiobject = DriverFactory.getAPIDriver(Login_Main.Base_Url);
        main_variables = new Main_Variables(apiobject);
        authanticationDataGenerator = new AuthanticationDataGenerator(apiobject);
        login_main = new Login_Main(apiobject);

        create_company_main = new Create_Company_Main(apiobject);

        get_company_details_main = new Get_company_details_Main(apiobject);
        companyDataGenerator = new CompanyDataGenerator(apiobject);
        login_main.Login_Successfully_English(authanticationDataGenerator.Email_Address, authanticationDataGenerator.Password);
    }
    @Test
    public void TC_01_Get_company_details_Successfully_English_And_Arabic() {

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

        // **************** Get Company Details   **************** :

        // ******* Get Company Details Successfully Response English  ************ :

        String Get_company_details_URL = "v1/companies/"+Company_ID;     // Get Company Details URL .

        Response Get_company_details_Successfully_Response_English=  apiobject.buildNewRequest(Get_company_details_URL , RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept" , "application/json")
                .addHeader("Accept-Language", "en")
                .performRequest();

        Validations.assertThat().response(Get_company_details_Successfully_Response_English)
                .extractedJsonValue("message")
                .contains("Company information retrieved successfully")
                .perform();


        // ******* Get_company_details_Successfully_Response_Arabic  ************ :

        Response Get_company_details_Successfully_Response_Arabic =   apiobject.buildNewRequest(Get_company_details_URL , RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform)
                .addHeader("Accept" , "application/json")
                .addHeader("Accept-Language", "ar")
                .performRequest();

        Validations.assertThat().response(Get_company_details_Successfully_Response_Arabic)
                .extractedJsonValue("message")
                .contains("تم إسترجاع بيانات الشركة بنجاح")
                .perform();


    }
    @Test
    public void TC_01_Get_company_details_Failed_English_And_Arabic() {

        //****** Get_company_details_Failed_Response_English ******** :

        Response Get_company_details_Failed_Response_English = get_company_details_main.Get_company_details_Failed_English();

        Validations.assertThat().response(Get_company_details_Failed_Response_English)
                .extractedJsonValue("message")
                .contains("Company is invalid")
                .perform();


        // ***** Get_company_details_Failed_Response_Arabic ***********:

        Response Get_company_details_Failed_Response_Arabic = get_company_details_main.Get_company_details_Failed_Arabic();

        Validations.assertThat().response(Get_company_details_Failed_Response_Arabic)
                .extractedJsonValue("message")
                .contains("الشركة غير صالحة")
                .perform();
    }
}
