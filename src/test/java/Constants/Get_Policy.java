package Constants;

import Authantication.Login_Main;
import com.shaft.api.RestActions;
import com.shaft.driver.DriverFactory;
import com.shaft.validation.Validations;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Get_Policy {
    private RestActions apiobject;
    private Get_Policy_Main get_policy_main;


    @BeforeClass
    public void beforClass(){
        apiobject = DriverFactory.getAPIDriver(Login_Main.Base_Url);
        get_policy_main = new Get_Policy_Main(apiobject);


    }


    @Test
    public void TC_01_Get_Policy_Successfully_English(){
        Response Get_Policy_Response = get_policy_main.Get_Policy_Successfully_English();

        Validations.assertThat().response(Get_Policy_Response).extractedJsonValue("message")
                .contains("Policy retrieved successfully").perform();

    }

    @Test
    public void TC_02_Get_Policy_Successfully_Arabic(){
        Response Get_Policy_Response = get_policy_main.Get_Policy_Successfully_Arabic();

//        Validations.assertThat().response(Get_Policy_Response).extractedJsonValue("")
//                .contains("").perform();

    }
}
