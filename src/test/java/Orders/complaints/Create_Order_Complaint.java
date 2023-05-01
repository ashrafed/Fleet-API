package Orders.complaints;

import Authantication.Login_Main;
import Constants.Get_Constants_Main;
import Drivers.Create_Driver_Main;
import MainPackage.DataGenerator.AuthanticationDataGenerator;
import MainPackage.DataGenerator.DriversDataGenerator;
import MainPackage.DataGenerator.VinNumberGenerator;
import MainPackage.Main_Variables;
import Orders.Create_Order_Main;
import Vehicles.Create_Vehicle_Main;
import com.shaft.api.RestActions;
import com.shaft.driver.DriverFactory;
import com.shaft.validation.Validations;
import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Create_Order_Complaint {
    private RestActions apiobject;
    private Main_Variables main_variables;
    private AuthanticationDataGenerator authanticationDataGenerator;
    private DriversDataGenerator driversDataGenerator;
    private Login_Main login_main;
    private Get_Constants_Main get_constants_main;
    private Create_Vehicle_Main create_vehicle_main;
    private Create_Driver_Main create_driver_main;
    private Create_Order_Main create_order_main;



    @BeforeClass
    public void Beforclase() {
        apiobject = DriverFactory.getAPIDriver(Login_Main.Base_Url);
        main_variables = new Main_Variables(apiobject);
        authanticationDataGenerator = new AuthanticationDataGenerator(apiobject);
        driversDataGenerator = new DriversDataGenerator(apiobject);
        login_main = new Login_Main(apiobject);

        get_constants_main = new Get_Constants_Main(apiobject);

        create_vehicle_main = new Create_Vehicle_Main(apiobject);

        create_driver_main = new Create_Driver_Main(apiobject);

        create_order_main = new Create_Order_Main(apiobject);

        login_main.Login_Successfully_English(
                authanticationDataGenerator.Email_Address_1,
                authanticationDataGenerator.Password);

    }

    @Test
    @Description(" -Login_Test\n" +
            " -Create Driver\n" +
            " -Create Vehicle\n" +
            " -Create Order\n" +
            " -Create Complaint English\n" +
            " -Create Complaint Arabic ")
    public void TC_01_Create_Order_Complaint_Successfully_English_And_Arabic() {

        //******** Create Driver ************** :

        Response create_driver_Response = create_driver_main.Create_Driver_Successfully_English(
                driversDataGenerator.Driver_Name_Generator,
                driversDataGenerator.Register_PhoneNumber_Generattor,
                driversDataGenerator.Residence_Number_Generattor,
                driversDataGenerator.job_Data_Generattor,
                driversDataGenerator.license_expire_date,
                driversDataGenerator.City_Id_Generattor,
                "12");

        int driver_Id = create_driver_Response.jsonPath().get("data.user_id");


        //******** Get_Constants  ************** :

        Response get_constants_response = get_constants_main.Get_Constants_Successfully_English();
        int Engine_Type_ID = get_constants_response.jsonPath().get("data.engine_types[0].id");
        int Modele_ID = get_constants_response.jsonPath().get("data.models[0].id");
        int Brand_ID = get_constants_response.jsonPath().get("data.brands[0].id");
        int Color_ID = get_constants_response.jsonPath().get("data.colors[0].id");
        int Model_Year_ID = get_constants_response.jsonPath().get("data.models[0].id");
        int Plate_Letter_ID = get_constants_response.jsonPath().get("data.plate_letters[0].id");

        // ****************** ###### *******************:

        //******** Create Vehicle ************** :

        Response Create_Vehicle_Response_English = create_vehicle_main.Add_Vehicle_successfully_English(
                driver_Id,
                VinNumberGenerator.Vin_Number_Generator_24,
                Modele_ID,
                Brand_ID,
                Color_ID,
                Model_Year_ID,
                Engine_Type_ID,
                "435",
                "1",
                "3",
                "5");

        int Vehicle_ID = Create_Vehicle_Response_English.jsonPath().get("data.id");  // Get Vehicle ID

        //******** Create Order ************** :

        Response create_Order_response = create_order_main.Create_Order_Successfully_English(
                47,
                 Vehicle_ID,
                2,
                "21.263781",
                "46.351390",
                1,
                "21.263781",
                "46.351390",
                "2",
                "test Automation",
                "CAR-44242",
                1,
                2,
                3);

        Validations.assertThat().response(create_Order_response)
                .extractedJsonValue("message")
                .contains("Order stored successfully")
                .perform();

        String Order_ID = create_Order_response.jsonPath().get("data.order_no");  // Get Order Id


        //*********** Create Complaint English ***********:

        String Create_Order_Complaint_URL = "v1/companies/1/orders/"+Order_ID+"/complaints";

        JSONObject Create_Order_Complaint_Body = new JSONObject();
        Create_Order_Complaint_Body.put("description" , "Test Automation Complaint");


        Response create_Order_Complaint_response_english = apiobject.buildNewRequest(Create_Order_Complaint_URL , RestActions.RequestType.POST)
                .setContentType(ContentType.JSON)
                .addHeader("platform","@vb~sD~KS#2>]pq")
                .addHeader("Accept","application/json")
                .addHeader("Accept-Language","en")
                .setTargetStatusCode(200)
                .setRequestBody(Create_Order_Complaint_Body)
                .performRequest();

        Validations.assertThat().response(create_Order_Complaint_response_english)
                .extractedJsonValue("message")
                .contains("Complain t Created")
                .perform();


        //*********** Create Complaint Arabic ***********:

        Response create_Order_Complaint_response_Arabic = apiobject.buildNewRequest(Create_Order_Complaint_URL , RestActions.RequestType.POST)
                .setContentType(ContentType.JSON)
                .addHeader("platform","@vb~sD~KS#2>]pq")
                .addHeader("Accept","application/json")
                .addHeader("Accept-Language","ar")
                .setTargetStatusCode(200)
                .setRequestBody(Create_Order_Complaint_Body)
                .performRequest();

        Validations.assertThat().response(create_Order_Complaint_response_Arabic)
                .extractedJsonValue("message")
                .contains("تم عمل شكوي")
                .perform();


        long TimeOut = create_Order_Complaint_response_Arabic.getTimeIn(TimeUnit.MILLISECONDS);
        Validations.assertThat()
                .number(TimeOut)
                .isLessThanOrEquals(Main_Variables.timOut)
                .withCustomReportMessage("Validate Response Time")
                .perform();


    }

}
