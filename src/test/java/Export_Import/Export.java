package Export_Import;

import Authantication.Login_Main;
import Drivers.Create_Driver_Main;
import Drivers.Edit_Driver_Info_Main;
import MainPackage.DataGenerator.AuthanticationDataGenerator;
import MainPackage.DataGenerator.DriversDataGenerator;
import MainPackage.Main_Variables;
import com.shaft.api.RestActions;
import com.shaft.driver.DriverFactory;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Export {
    private RestActions apiobject ;
    private Main_Variables main_variables;
    private AuthanticationDataGenerator authanticationDataGenerator;
    private DriversDataGenerator driversDataGenerator;
    private Login_Main login_main;

    private Export_Main export_main;


    @BeforeClass
    public void BeforClass(){
        apiobject = DriverFactory.getAPIDriver(Login_Main.Base_Url);
        main_variables = new Main_Variables(apiobject);
        authanticationDataGenerator = new AuthanticationDataGenerator(apiobject);
        driversDataGenerator = new DriversDataGenerator(apiobject);


        login_main = new Login_Main(apiobject);

        login_main.Login_Successfully_English(
                authanticationDataGenerator.Email_Address_1 ,
                authanticationDataGenerator.Password);

        export_main = new Export_Main(apiobject);
    }

    @Test
    public void TC_01_Export_Successfully_English(){
        Response Export_Response = export_main.Export_Successfully_English();
    }
}
