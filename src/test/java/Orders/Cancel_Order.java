package Orders;

import Authantication.Login_Main;
import MainPackage.DataGenerator.AuthanticationDataGenerator;
import MainPackage.DataGenerator.DriversDataGenerator;
import MainPackage.Main_Variables;
import com.shaft.api.RestActions;
import com.shaft.driver.DriverFactory;
import com.shaft.validation.Validations;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import netscape.javascript.JSObject;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Cancel_Order {


    private RestActions apiobject;
    private Main_Variables main_variables;
    private AuthanticationDataGenerator authanticationDataGenerator;
    private DriversDataGenerator driversDataGenerator;

    private Login_Main login_main;
    private Create_Order_Main create_order_main;
    private Cancel_Order_Main cancel_order_main ;


    @BeforeClass
    public void Beforclase() {
        apiobject = DriverFactory.getAPIDriver(Login_Main.Base_Url);
        main_variables = new Main_Variables(apiobject);
        authanticationDataGenerator = new AuthanticationDataGenerator(apiobject);
        driversDataGenerator = new DriversDataGenerator(apiobject);
        login_main = new Login_Main(apiobject);
        create_order_main = new Create_Order_Main(apiobject);


        login_main.Login_Successfully_English(
                authanticationDataGenerator.Email_Address_1,
                authanticationDataGenerator.Password);

        cancel_order_main = new Cancel_Order_Main(apiobject);

    }


//    @Test
//    public void TC_02_Cancel_Order_Successfully(){
//
//        Response Create_Order_Response = create_order_main.Create_Order_Successfully_English(
//                3,
//                3564,
//                2,
//                "21.263781",
//                "46.351390",
//                1,
//                "21.263781",
//                "46.351390",
//                "2",
//                "test Automation",
//                "CAR-44242",
//                1,
//                2,
//                3);
//
//        String Order_ID = Create_Order_Response.jsonPath().get("data.order_no");
//
//        String Cancel_Order_URL = "v1/companies/1/orders/"+Order_ID;
//
//        JSONObject Cancel_Order_Body = new JSONObject();
//        Cancel_Order_Body.put("reason_id" , "13");
//        Cancel_Order_Body.put("comment" , "Test Automation");
//
//        Response Cancel_Order_response =   apiobject.buildNewRequest(Cancel_Order_URL , RestActions.RequestType.DELETE)
//                .setContentType(ContentType.JSON)
//                .addHeader("platform","@vb~sD~KS#2>]pq")
//                .addHeader("Accept","application/json")
//                .addHeader("Accept-Language","en")
//                .setTargetStatusCode(200)
//                .setRequestBody(Cancel_Order_Body)
//                .performRequest();
//
//        Validations.assertThat().response(Cancel_Order_response)
//                .extractedJsonValue("message")
//                .contains("Order cancelled successfully")
//                .perform();
//
//
//
//
//    }


}
