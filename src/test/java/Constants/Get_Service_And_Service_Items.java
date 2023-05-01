package Constants;

import Authantication.Login_Main;
import com.shaft.api.RestActions;
import com.shaft.driver.DriverFactory;
import com.shaft.driver.SHAFT;
import com.shaft.validation.Validations;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Get_Service_And_Service_Items {

    private RestActions apiobject;
    private Get_Service_And_Service_Items_Main get_service_and_service_items_main;


    @BeforeClass
    public void beforClass() {
        apiobject = DriverFactory.getAPIDriver(Login_Main.Base_Url);
        get_service_and_service_items_main = new Get_Service_And_Service_Items_Main(apiobject);
    }
    @Test
    public void TC_01_Get_Service_And_Service_Items_Successfully_English() {
        Response Get_Service_And_Service_Items_Response = get_service_and_service_items_main.Get_Service_And_Service_Items_Successfully_English();

        Validations.assertThat().response(Get_Service_And_Service_Items_Response)
                .extractedJsonValue("message")
                .contains("All services retrieved successfully")
                .perform();
    }

    @Test
    public void TC_01_Get_Service_And_Service_Items_Successfully_Arabic() {
        Response Get_Service_And_Service_Items_Response = get_service_and_service_items_main.Get_Service_And_Service_Items_Successfully_Arabic();

        Validations.assertThat().response(Get_Service_And_Service_Items_Response)
                .extractedJsonValue("message")
                .contains("تم استرجاع جميع الخدمات بنجاح")
                .perform();

    }
}

