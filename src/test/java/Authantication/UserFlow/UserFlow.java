//package Authantication.UserFlow;
//
//import Authantication.Login_Main;
//import Authantication.Logout_Main;
//import MainPackage.DataGenerator.AuthanticationDataGenerator;
//import MainPackage.Main_Variables;
//import com.shaft.api.RestActions;
//import com.shaft.driver.DriverFactory;
//import com.shaft.validation.Validations;
//import io.restassured.response.Response;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import java.util.concurrent.TimeUnit;
//
//public class UserFlow {
//    private RestActions apiobject ;
//    private Login_Main login_main;
//    private AuthanticationDataGenerator authanticationDataGenerator;
//
//    private Logout_Main logout_main;
//
//
//    @BeforeClass
//    public void BeforClass(){
//        apiobject = DriverFactory.getAPIDriver(Login_Main.Base_Url);
//        login_main = new Login_Main(apiobject);
//        authanticationDataGenerator = new AuthanticationDataGenerator(apiobject);
//        logout_main = new Logout_Main(apiobject);
//    }
//
//    @Test
//    public void TC_01_Login_$_Logout(){
//     login_main.Login_Successfully_English(
//                authanticationDataGenerator.Email_Address_1,
//                authanticationDataGenerator.Password);
//        Response logout_Response = logout_main.Logout_Successfully_English();
//
//        Validations.assertThat().response(logout_Response)
//                .extractedJsonValue("message")
//                .contains("User logged out successfully")
//                .perform();
//
//
//
//        long TimeOut = logout_Response.getTimeIn(TimeUnit.MILLISECONDS);
//        Validations.assertThat()
//                .number(TimeOut)
//                .isLessThanOrEquals(Main_Variables.timOut)
//                .withCustomReportMessage("Validate Response Time")
//                .perform();
//    }
//}
