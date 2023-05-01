package HealthCheck;

import com.shaft.api.RestActions;
import com.shaft.driver.DriverFactory;
import com.shaft.validation.Validations;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HealthCheck {
    private RestActions apiobject;

    private HealthCheck_Main healthCheck_main;



    @BeforeClass
    public void BeforeClass(){
        apiobject = DriverFactory.getAPIDriver(HealthCheck_Main.Fleet_Base_URL);
        healthCheck_main = new HealthCheck_Main(apiobject);
    }

    @Test
    public void TC_01_HealthCheck_successfully_English_And_Arabic(){
        Response HealthCheck_Response_English = healthCheck_main.HealthCheck_Successfully_English();
        Response HealthCheck_Response_Arabic = healthCheck_main.HealthCheck_Successfully_English();


        Validations.assertThat().response(HealthCheck_Response_English)
                .extractedJsonValue("status_code")
                .contains("200")
                .perform();


        Validations.assertThat().response(HealthCheck_Response_Arabic)
                .extractedJsonValue("status_code")
                .contains("200")
                .perform();


    }
}
