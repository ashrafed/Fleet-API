package User_Profile;

import Authantication.Login_Main;
import Drivers.Create_Driver_Main;
import MainPackage.DataGenerator.AuthanticationDataGenerator;
import MainPackage.DataGenerator.DriversDataGenerator;
import MainPackage.Main_Variables;
import com.shaft.api.RestActions;
import com.shaft.driver.DriverFactory;
import com.shaft.validation.Validations;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Update_app_details {
    private RestActions apiobject ;
    private Main_Variables main_variables;
    private AuthanticationDataGenerator authanticationDataGenerator;
    private Login_Main login_main;
    private Update_app_details_Main update_app_details_main;

    @BeforeClass
    public void BeforClass(){
        apiobject = DriverFactory.getAPIDriver(Login_Main.Base_Url);
        main_variables = new Main_Variables(apiobject);

        login_main = new Login_Main(apiobject);

        authanticationDataGenerator = new AuthanticationDataGenerator(apiobject);

        update_app_details_main =new Update_app_details_Main(apiobject);

        login_main.Login_Successfully_English(
                authanticationDataGenerator.Email_Address_1 ,
                authanticationDataGenerator.Password);

    }
//    @Test
//    public void TC_01_Update_app_details_successfully_English(){
//        Response Update_app_details_Response_English = update_app_details_main.Update_app_details_Successfully_English("2022-10-04 15:04:50",
//                "",
//                "2",
//                "5.0.1");
//
//        Validations.assertThat().response(Update_app_details_Response_English)
//                .extractedJsonValue("message")
//                .contains("Vehicle stored successfully")
//                .perform();
//    }
//


}