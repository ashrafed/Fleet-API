package Constants;

import Authantication.Login_Main;
import com.shaft.api.RestActions;
import com.shaft.driver.DriverFactory;
import com.shaft.validation.Validations;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Get_Brands {
    private RestActions apiobject;
    private Get_Brands_Main get_brands_main;



    @BeforeClass
    public void beforClass(){
        apiobject = DriverFactory.getAPIDriver(Login_Main.Base_Url);
       get_brands_main = new Get_Brands_Main(apiobject);

    }

    @Test
    public void TC_01_Get_Brands_Successfully_English(){
        Response Get_Brands_Response = get_brands_main.Get_Brands_Successfully_English();

        Validations.assertThat().response(Get_Brands_Response).extractedJsonValue("message")
                .contains("Brands retrieved successfully").perform();
    }

    @Test
    public void TC_02_Get_Brands_Successfully_Arabic(){
        Response Get_Brands_Response = get_brands_main.Get_Brands_Successfully_Arabic();

        Validations.assertThat().response(Get_Brands_Response).extractedJsonValue("message")
                .contains("").perform();
    }
}
