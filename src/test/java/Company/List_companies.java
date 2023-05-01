package Company;

import Authantication.Login_Main;
import MainPackage.DataGenerator.AuthanticationDataGenerator;
import MainPackage.DataGenerator.CompanyDataGenerator;
import MainPackage.Main_Variables;
import com.shaft.api.RestActions;
import com.shaft.driver.DriverFactory;
import com.shaft.validation.Validations;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class List_companies {
    private RestActions apiobject;
    private Main_Variables main_variables;
    private AuthanticationDataGenerator authanticationDataGenerator;
    private Login_Main login_main;
    private List_companies_Main list_companies_main;
    private CompanyDataGenerator companyDataGenerator;
    @BeforeClass
    public void BeforClass() {
        apiobject = DriverFactory.getAPIDriver(Login_Main.Base_Url);
        main_variables = new Main_Variables(apiobject);
        authanticationDataGenerator = new AuthanticationDataGenerator(apiobject);
        login_main = new Login_Main(apiobject);
        list_companies_main = new List_companies_Main(apiobject);
        companyDataGenerator = new CompanyDataGenerator(apiobject);
        login_main.Login_Successfully_English(authanticationDataGenerator.Email_Address, authanticationDataGenerator.Password);
    }
    @Test
    public void TC_01_List_company_details_Successfully_English_And_Arabic() {
        Response List_companies_Successfully_Response_English = list_companies_main.List_companies_Main_Successfully_English();

        Validations.assertThat().response(List_companies_Successfully_Response_English)
                .extractedJsonValue("message")
                .contains("Companys retrieved successfully")
                .perform();

        Response List_companies_Successfully_Response_Arabic = list_companies_main.List_companies_Main_Successfully_Arabic();

        Validations.assertThat().response(List_companies_Successfully_Response_Arabic)
                .extractedJsonValue("message")
                .contains("تم إسترجاع الشركات بنجاح")
                .perform();
    }
    @Test
    public void TC_02_List_company_details_Failed_English_And_Arabic() {
        Response List_companies_Failed_Response_English = list_companies_main.List_companies_Main_Failed_English();

        Validations.assertThat().response(List_companies_Failed_Response_English)
                .extractedJsonValue("message")
                .contains("Company is invalid")
                .perform();

        Response List_companies_Failed_Response_Arabic = list_companies_main.List_companies_Main_Failed_Arabic();

        Validations.assertThat().response(List_companies_Failed_Response_Arabic)
                .extractedJsonValue("message")
                .contains("الشركة غير صالحة")
                .perform();
    }

}
