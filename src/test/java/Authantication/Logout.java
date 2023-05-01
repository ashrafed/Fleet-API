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

public class Logout {

    private RestActions apiobject ;
    private Login_Main login_main;
    private Logout_Main logout_main;
    private AuthanticationDataGenerator authanticationDataGenerator;

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
        login_main = new Login_Main(apiobject);
        logout_main = new Logout_Main(apiobject);
        authanticationDataGenerator = new AuthanticationDataGenerator(apiobject);



    }

    @Test
    public void TC_01_Logout_Successfully_English(){
        login_main.Login_Successfully_English(
                authanticationDataGenerator.Email_Address_1 ,
                authanticationDataGenerator.Password);



        Response logout_Response = logout_main.Logout_Successfully_English();


        long TimeOut = logout_Response.getTimeIn(TimeUnit.MILLISECONDS);
        Validations.assertThat()
                .number(TimeOut)
                .isLessThanOrEquals(Main_Variables.timOut)
                .withCustomReportMessage("Validate Response Time")
                .perform();

    }

    @Test
    public void TC_02_Logout_Successfully_Arabic(){
        login_main.Login_Successfully_Arabic(
                authanticationDataGenerator.Email_Address_1 ,
                authanticationDataGenerator.Password);

        Response logout_Response = logout_main.Logout_Successfully_Arabic();


        long TimeOut = logout_Response.getTimeIn(TimeUnit.MILLISECONDS);
        Validations.assertThat()
                .number(TimeOut)
                .isLessThanOrEquals(Main_Variables.timOut)
                .withCustomReportMessage("Validate Response Time")
                .perform();
    }

}


