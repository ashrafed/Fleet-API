package Constants;

import Authantication.Login_Main;
import Drivers.Get_Drivers_Details_Main;
import com.shaft.api.RestActions;
import com.shaft.driver.DriverFactory;
import com.shaft.driver.SHAFT;
import com.shaft.validation.Validations;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Authantication.Login_Main.Platform;

public class Get_Constants {
    private RestActions apiobject;
    private Get_Constants_Main get_constants_main;


    @BeforeClass
    public void beforClass() {
        apiobject = DriverFactory.getAPIDriver(Login_Main.Base_Url);
        get_constants_main = new Get_Constants_Main(apiobject);
    }


    @Test
    public void TC_01_Get_Constants_Successfully_English() {
        Response Get_Constants_Response = get_constants_main.Get_Constants_Successfully_English();

        Validations.assertThat().response(Get_Constants_Response).extractedJsonValue("message")
                .contains("Constants retrieved successfully").perform();
    }


    @Test
    public void TC_02_Get_Constants_Successfully_Arabic() {
        Response Get_Constants_Response = get_constants_main.Get_Constants_Successfully_Arabic();


//        Validations.assertThat().response(Get_Constants_Response).extractedJsonValue("message")
//                .contains("").perform();
    }




}

