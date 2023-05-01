package Orders;

import Authantication.Login_Main;
import MainPackage.DataGenerator.AuthanticationDataGenerator;
import MainPackage.Main_Variables;
import User_Profile.Update_app_details_Main;
import com.shaft.api.RestActions;
import com.shaft.driver.DriverFactory;
import com.shaft.validation.Validations;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Verify_otp {
    private RestActions apiobject ;
    private Main_Variables main_variables;
    private AuthanticationDataGenerator authanticationDataGenerator;
    private Login_Main login_main;
    private Verify_otp_Main verify_otp_main;

    @BeforeClass
    public void BeforClass(){
        apiobject = DriverFactory.getAPIDriver(Login_Main.Base_Url);
        main_variables = new Main_Variables(apiobject);

        login_main = new Login_Main(apiobject);

        authanticationDataGenerator = new AuthanticationDataGenerator(apiobject);

        verify_otp_main =new Verify_otp_Main(apiobject);

        login_main.Login_Successfully_English(
                authanticationDataGenerator.Email_Address_1 ,
                authanticationDataGenerator.Password);
    }
    @Test
    public void TC_01_Verify_otp_successfully_English(){
       // Response Verify_otp_Response_English = verify_otp_main.Verify_otp_Successfully_English(5, 9531);

//        Validations.assertThat().response(Verify_otp_Response_English)
//                .extractedJsonValue("message")
//                .contains("Vehicle stored successfully")
//                .perform();
    }
}
