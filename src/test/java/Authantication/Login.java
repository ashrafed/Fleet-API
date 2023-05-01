package Authantication;

import MainPackage.DataGenerator.AuthanticationDataGenerator;
import MainPackage.Main_Variables;
import com.shaft.api.RestActions;
import com.shaft.driver.DriverFactory;
import com.shaft.validation.Validations;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Login {
    private RestActions apiobject ;
    private Login_Main login_main;
    private AuthanticationDataGenerator authanticationDataGenerator;



    @BeforeClass
    public void BeforClass(){
        apiobject = DriverFactory.getAPIDriver(Login_Main.Base_Url);
        login_main = new Login_Main(apiobject);
        authanticationDataGenerator = new AuthanticationDataGenerator(apiobject);

    }


    /*

     * [] validate data send in API
     * [] validate API response body
     * [] validate response status code
     * [] validate API response time
     * [] validate response Structure
     * [] validate API type (get,post,delete)
     * [] validate response in multiple languages (ar,en)
     *
     */

    @Test
    public void TC_01_Login_Successfully_English(){
        Response login_Response = login_main.Login_Successfully_English(
                authanticationDataGenerator.Email_Address_1,
                authanticationDataGenerator.Password);


        long TimeOut = login_Response.getTimeIn(TimeUnit.MILLISECONDS);
        Validations.assertThat()
                .number(TimeOut)
                .isLessThanOrEquals(Main_Variables.timOut)
                .withCustomReportMessage("Validate Response Time")
                .perform();
    }

    @Test
    public void TC_02_Login_Successfully_Arabic(){
        Response login_Response = login_main.Login_Successfully_Arabic(
                authanticationDataGenerator.Email_Address_1,
                authanticationDataGenerator.Password);

        long TimeOut = login_Response.getTimeIn(TimeUnit.MILLISECONDS);
        Validations.assertThat()
                .number(TimeOut)
                .isLessThanOrEquals(Main_Variables.timOut)
                .withCustomReportMessage("Validate Response Time")
                .perform();
    }

    @Test
    public void TC_03_check_Login_With_Mandatory_Input_Field_English(){
        Response login_Response = login_main.Login_Fail_English(
                "" ,
                "" );

        Validations.assertThat().response(login_Response)
                .extractedJsonValue("errors")
                .contains("Email is required")
                .perform();


        Validations.assertThat().response(login_Response)
                .extractedJsonValue("errors")
                .contains("Password is required")
                .perform();



        long TimeOut = login_Response.getTimeIn(TimeUnit.MILLISECONDS);
        Validations.assertThat()
                .number(TimeOut)
                .isLessThanOrEquals(Main_Variables.timOut)
                .withCustomReportMessage("Validate Response Time")
                .perform();


    }

    @Test
    public void TC_04_check_Login_With_Mandatory_Input_Field_Arabic(){
        Response login_Response = login_main.Login_Fail_Arabic(
                "" ,
                "" );

        Validations.assertThat().response(login_Response)
                .extractedJsonValue("errors")
                .contains("البريد الإلكتروني مطلوب")
                .perform();


        Validations.assertThat().response(login_Response)
                .extractedJsonValue("errors")
                .contains("كلمة المرور مطلوبة")
                .perform();



        long TimeOut = login_Response.getTimeIn(TimeUnit.MILLISECONDS);
        Validations.assertThat()
                .number(TimeOut)
                .isLessThanOrEquals(Main_Variables.timOut)
                .withCustomReportMessage("Validate Response Time")
                .perform();


    }

    @Test
    public void TC_05_check_Login_With_Invalid_Email_And_Password_English(){
        Response login_Response = login_main.Login_Fail_English(
                "test" ,
                "test" );

        Validations.assertThat().response(login_Response)
                .extractedJsonValue("errors")
                .contains("Wrong email format")
                .perform();

        Validations.assertThat().response(login_Response)
                .extractedJsonValue("errors")
                .contains("User role is invalid")
                .perform();



        long TimeOut = login_Response.getTimeIn(TimeUnit.MILLISECONDS);
        Validations.assertThat()
                .number(TimeOut)
                .isLessThanOrEquals(Main_Variables.timOut)
                .withCustomReportMessage("Validate Response Time")
                .perform();

    }

    @Test
    public void TC_06_check_Login_With_Invalid_Email_And_Password_Arabic(){
        Response login_Response = login_main.Login_Fail_Arabic(
                "test" ,
                "test" );



        Validations.assertThat().response(login_Response)
                .extractedJsonValue("errors")
                .contains("يجب أن يكون البريد الإلكتروني منسق")
                .perform();

        Validations.assertThat().response(login_Response)
                .extractedJsonValue("errors")
                .contains("دور المستخدم غير صالح")
                .perform();



        long TimeOut = login_Response.getTimeIn(TimeUnit.MILLISECONDS);
        Validations.assertThat()
                .number(TimeOut)
                .isLessThanOrEquals(Main_Variables.timOut)
                .withCustomReportMessage("Validate Response Time")
                .perform();





    }



    }




