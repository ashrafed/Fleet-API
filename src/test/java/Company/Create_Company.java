package Company;

import Authantication.Login_Main;
import Drivers.Create_Driver_Main;
import Drivers.Edit_Driver_Info_Main;
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

import static Authantication.Login_Main.Platform;

public class Create_Company {
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
    public void TC_01_Create_Company_Successfully_English_And_Arabic() {
        Response Create_Company_Successfully_Response_English = create_company_main.Create_Company_Successfully_English(
                companyDataGenerator.Company_Name,
                companyDataGenerator.contact_number,
                companyDataGenerator.Email_Address_1,
                companyDataGenerator.commercial_number,
                companyDataGenerator.additional_amount,
                companyDataGenerator.admin_name,
               "123456");

        Validations.assertThat().response(Create_Company_Successfully_Response_English)
                .extractedJsonValue("message")
                .contains("Company stored successfully")
                .perform();

        /*
        =>> Create_Company_Successfully_Arabic
        */
        Response Create_Company_Successfully_Response_Arabic = create_company_main.Create_Company_Successfully_Arabic(
                companyDataGenerator.Company_Name,
                companyDataGenerator.contact_number_1,
                companyDataGenerator.Email_Address_2,
                companyDataGenerator.commercial_number_2,
                companyDataGenerator.additional_amount,
                companyDataGenerator.admin_name,
                "123456");

        Validations.assertThat().response(Create_Company_Successfully_Response_Arabic)
                .extractedJsonValue("message")
                .contains("تم حفظ الشركة بنجاح")
                .perform();
    }
    @Test
    public void TC_02_Create_Company_Failed_English_And_Arabic() {
        Response Create_Company_Failed_Response_English = create_company_main.Create_Company_Failed_English("",
                companyDataGenerator.contact_number_3,
                companyDataGenerator.Email_Address_3,
                companyDataGenerator.commercial_number_3,
                companyDataGenerator.additional_amount,
                companyDataGenerator.admin_name,
                "123456");

        Validations.assertThat().response(Create_Company_Failed_Response_English)
                .extractedJsonValue("message")
                .contains("Invalid input data")
                .perform();

        /*
        =>> Create_Company_Failed_Arabic
        */
        Response Create_Company_Failed_Response_Arabic = create_company_main.Create_Company_Failed_Arabic("",
                companyDataGenerator.contact_number_4,
                companyDataGenerator.Email_Address_4,
                companyDataGenerator.commercial_number_4,
                companyDataGenerator.additional_amount,
                companyDataGenerator.admin_name,
                "123456");

        Validations.assertThat().response(Create_Company_Failed_Response_Arabic)
                .extractedJsonValue("message")
                .contains("خطأ فى بيانات الإدخال")
                .perform();
    }
    @Test
    public void TC_03_Create_Company_Failed_Errors() {

        Response Create_Company_Failed_Response_English_errors = create_company_main.Create_Company_Failed_English(
                "", "", "", "", "", "" ,"");

        Validations.assertThat().response(Create_Company_Failed_Response_English_errors)
                .extractedJsonValue("errors")
                .contains("Name is mandatory")
                .perform();
        Validations.assertThat().response(Create_Company_Failed_Response_English_errors)
                .extractedJsonValue("errors")
                .contains("Commercial number is mandatory")
                .perform();
        Validations.assertThat().response(Create_Company_Failed_Response_English_errors)
                .extractedJsonValue("errors")
                .contains("Additional amount is mandatory")
                .perform();
        Validations.assertThat().response(Create_Company_Failed_Response_English_errors)
                .extractedJsonValue("errors")
                .contains("Admin name is mandatory")
                .perform();
        Validations.assertThat().response(Create_Company_Failed_Response_English_errors)
                .extractedJsonValue("errors")
                .contains("Contct number id must be numeric")
                .perform();
        Validations.assertThat().response(Create_Company_Failed_Response_English_errors)
                .extractedJsonValue("errors")
                .contains("Login mobile format is invalid")
                .perform();
        Validations.assertThat().response(Create_Company_Failed_Response_English_errors)
                .extractedJsonValue("errors")
                .contains("Email is required")
                .perform();

        /*
        =>> Create_Company_Failed_Arabic
        */
        Response Create_Company_Failed_Response_Arabic_errors = create_company_main.Create_Company_Failed_Arabic(
                "", "", "", "", "", "" ,"");

        Validations.assertThat().response(Create_Company_Failed_Response_Arabic_errors)
                .extractedJsonValue("errors")
                .contains("الإسم مطلوب")
                .perform();
        Validations.assertThat().response(Create_Company_Failed_Response_Arabic_errors)
                .extractedJsonValue("errors")
                .contains("رقم السجل الضريبي مطلوب")
                .perform();
        Validations.assertThat().response(Create_Company_Failed_Response_Arabic_errors)
                .extractedJsonValue("errors")
                .contains("رقم القيمة المضافة مطلوب")
                .perform();
        Validations.assertThat().response(Create_Company_Failed_Response_Arabic_errors)
                .extractedJsonValue("errors")
                .contains("إسم المسئول مطلوب")
                .perform();
        Validations.assertThat().response(Create_Company_Failed_Response_Arabic_errors)
                .extractedJsonValue("errors")
                .contains("رقم الجوال يجب أن يكون رقم")
                .perform();
        Validations.assertThat().response(Create_Company_Failed_Response_Arabic_errors)
                .extractedJsonValue("errors")
                .contains("خطأ فى تنسيق رقم الجوال")
                .perform();
        Validations.assertThat().response(Create_Company_Failed_Response_Arabic_errors)
                .extractedJsonValue("errors")
                .contains("البريد الإلكتروني مطلوب")
                .perform();
    }








}
