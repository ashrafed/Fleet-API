//package Drivers.UserFlow;
//
//import Authantication.Login_Main;
//import Drivers.*;
//import MainPackage.DataGenerator.AuthanticationDataGenerator;
//import MainPackage.DataGenerator.DriversDataGenerator;
//import MainPackage.Main_Variables;
//import com.shaft.api.RestActions;
//import com.shaft.driver.DriverFactory;
//import com.shaft.validation.Validations;
//import io.qameta.allure.Description;
//import io.restassured.response.Response;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import java.util.concurrent.TimeUnit;
//
//public class UserFlow {
//    private RestActions apiobject;
//    private Main_Variables main_variables;
//    private DriversDataGenerator driversDataGenerator;
//
//    private Login_Main login_main;
//    private AuthanticationDataGenerator authanticationDataGenerator;
//    private Create_Driver_Main create_driver_main;
//    private Edit_Driver_Info_Main edit_driver_info_main;
//
//    private Get_Drivers_Details_Main get_drivers_details_main;
//
//    private List_Drivers_Main list_drivers_main;
//
//    private Update_Driver_Status_Main update_driver_status_main;
//
//
//
//
//
//    @BeforeClass
//    public void BeforeClass() {
//        apiobject = DriverFactory.getAPIDriver(Login_Main.Base_Url);
//
//        main_variables = new Main_Variables(apiobject);
//        authanticationDataGenerator = new AuthanticationDataGenerator(apiobject);
//
//        driversDataGenerator = new DriversDataGenerator(apiobject);
//        login_main = new Login_Main(apiobject);
//        create_driver_main = new Create_Driver_Main(apiobject);
//        edit_driver_info_main = new Edit_Driver_Info_Main(apiobject);
//
//        get_drivers_details_main =new Get_Drivers_Details_Main(apiobject);
//
//        list_drivers_main = new List_Drivers_Main(apiobject);
//        update_driver_status_main = new Update_Driver_Status_Main(apiobject);
//
//
//
//
//    }
//
//
//    @Description("1-  login \n" +
//            "2- CreateDriver\n" +
//            "3- Edit_Driver_Info\n" +
//            "4- Get_Drivers_Details\n" +
//            "5- List_Drivers\n" +
//            "6- Update_Driver_Status\t\t\t")
//
//    @Test()
//    public void TC_01_Login_$_CreateDriver_$_Edit_Driver_Info_$_Get_Drivers_Details_$_List_Drivers_$_Update_Driver_Status() {
//        login_main.Login_Successfully_English(
//                authanticationDataGenerator.Email_Address_1,
//                authanticationDataGenerator.Password);
//
//       // Create_driver
//
//      Response create_driver_Response = create_driver_main.Create_Driver_Successfully_English(
//                driversDataGenerator.Driver_Name_Generator,
//                driversDataGenerator.Register_PhoneNumber_Generattor,
//                driversDataGenerator.Residence_Number_Generattor_7,
//                driversDataGenerator.job_Data_Generattor,
//                "2021-04-03",
//                driversDataGenerator.City_Id_Generattor,
//                "12");
//
//      Validations.assertThat().response(create_driver_Response)
//              .extractedJsonValue("message")
//              .contains("Driver stored successfully")
//              .perform();
//
//
//      //  Edit_Driver_Info :
//
//        Response Edit_Driver_Info_Response = edit_driver_info_main.Edit_Driver_Info_Main_Successfully_English(
//                "2",
//                driversDataGenerator.Driver_Name_Generator,
//                driversDataGenerator.Residence_Number_Generattor_3,
//                "2021-04-03",
//                driversDataGenerator.Register_PhoneNumber_Generattor_5,
//                driversDataGenerator.City_Id_Generattor,
//                "1");
//
//
//        Validations.assertThat().response(Edit_Driver_Info_Response)
//                .extractedJsonValue("message")
//                .contains("Driver information updated successfully")
//                .perform();
//
//        // Get_Drivers_Details
//
//
//        Response Get_Drivers_Details_Response = get_drivers_details_main.Get_Drivers_Details_Successfully_English();
//
//        Validations.assertThat().response(Get_Drivers_Details_Response)
//                .extractedJsonValue("message")
//                .contains("Driver information retrieved successfully")
//                .perform();
//
//
//        // List_Drivers
//
//        Response List_Drivers_Response = list_drivers_main.List_Drivers_Successfully_English();
//
//        Validations.assertThat().response(List_Drivers_Response).extractedJsonValue("message")
//                .contains("Drivers retrieved successfully")
//                .perform();
//
//
//        // Update_Driver_Status
//
//
//
//
//        Response Update_Driver_Status_Response = update_driver_status_main.Update_Driver_Status_Successfully_English(
//                "1");
//
//        Validations.assertThat().response(Update_Driver_Status_Response)
//                .extractedJsonValue("message")
//                .contains("Driver status updated successfully")
//                .perform();
//
//
//        long TimeOut = Update_Driver_Status_Response.getTimeIn(TimeUnit.MILLISECONDS);
//        Validations.assertThat()
//                .number(TimeOut)
//                .isLessThanOrEquals(Main_Variables.timOut)
//                .withCustomReportMessage("Validate Response Time")
//                .perform();
//    }
//}