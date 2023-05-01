package Vehicles;

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

import java.util.concurrent.TimeUnit;

public class Get_Vehicle_Details {

    private RestActions apiobject ;
    private Main_Variables main_variables;
    private AuthanticationDataGenerator authanticationDataGenerator;
    private DriversDataGenerator driversDataGenerator;
    private Login_Main login_main;
    private Create_Driver_Main create_driver_main;

    private Create_Vehicle_Main create_vehicle_main;

    private List_Vehicles_Main list_vehicles_main;

    private Get_Vehicle_Details_Main get_vehicle_details_main;


    /*

     * [] validate data send in API
     * [] validate API response body
     * [] validate response status code
     * [] validate API response time
     * [] validate response Structure
     * [] validate API type (get,post,delete)
     * [] validate response in multiple languages (ar,en)

     */


    @BeforeClass
    public void BeforClass(){
        apiobject = DriverFactory.getAPIDriver(Login_Main.Base_Url);
        main_variables = new Main_Variables(apiobject);

        login_main = new Login_Main(apiobject);
        create_driver_main = new Create_Driver_Main(apiobject);

        authanticationDataGenerator = new AuthanticationDataGenerator(apiobject);
        driversDataGenerator = new DriversDataGenerator(apiobject);

        create_vehicle_main =new Create_Vehicle_Main(apiobject);

        list_vehicles_main = new List_Vehicles_Main(apiobject);

        get_vehicle_details_main = new Get_Vehicle_Details_Main(apiobject);


        login_main.Login_Successfully_English(
                authanticationDataGenerator.Email_Address_1 ,
                authanticationDataGenerator.Password);

    }

    @Test
    public void TC_01_Get_Vehicle_Details_Successfully_English_And_Arabic(){
        Response Get_Vehicle_Details_Response_English = get_vehicle_details_main.Get_Vehicle_Details_Successfully_English();

        Validations.assertThat().response(Get_Vehicle_Details_Response_English)
                .extractedJsonValue("message")
                .contains("Vehicle information retrieved successfully")
                .perform();



        Response Get_Vehicle_Details_Response_Arabic = get_vehicle_details_main.Get_Vehicle_Details_Successfully_Arabic();

        Validations.assertThat().response(Get_Vehicle_Details_Response_Arabic)
                .extractedJsonValue("message")
                .contains("تم إسترجاع بيانات السيارة بنجاح")
                .perform();



        long TimeOut = Get_Vehicle_Details_Response_Arabic.getTimeIn(TimeUnit.MILLISECONDS);
        Validations.assertThat()
                .number(TimeOut)
                .isLessThanOrEquals(Main_Variables.timOut)
                .withCustomReportMessage("Validate Response Time")
                .perform();


    }

}
