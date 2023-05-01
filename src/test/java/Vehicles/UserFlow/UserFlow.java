//package Vehicles.UserFlow;
//
//import Authantication.Login_Main;
//import Drivers.Create_Driver_Main;
//import MainPackage.DataGenerator.AuthanticationDataGenerator;
//import MainPackage.DataGenerator.DriversDataGenerator;
//import MainPackage.DataGenerator.VinNumberGenerator;
//import MainPackage.Main_Variables;
//import Vehicles.*;
//import com.shaft.api.RestActions;
//import com.shaft.driver.DriverFactory;
//import com.shaft.validation.Validations;
//import io.restassured.response.Response;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//public class UserFlow {
//    private RestActions apiobject;
//
//    private Main_Variables main_variables;
//    private AuthanticationDataGenerator authanticationDataGenerator;
//    private DriversDataGenerator driversDataGenerator;
//    private Login_Main login_main;
//    private Create_Driver_Main create_driver_main;
//
//    private Change_Vehicle_Status_Main change_vehicle_status_main;
//
//
//    private Create_Vehicle_Main create_vehicle_main;
//
//    private List_Vehicles_Main list_vehicles_main;
//
//    private Get_Vehicle_Details_Main get_vehicle_details_main;
//
//    private Update_Vehicle_Info_Main update_vehicle_info_main;
//
//
//
//
//    @BeforeClass
//    public void BeforClass(){
//        apiobject = DriverFactory.getAPIDriver(Login_Main.Base_Url);
//        main_variables = new Main_Variables(apiobject);
//
//        login_main = new Login_Main(apiobject);
//        create_driver_main = new Create_Driver_Main(apiobject);
//
//        authanticationDataGenerator = new AuthanticationDataGenerator(apiobject);
//        driversDataGenerator = new DriversDataGenerator(apiobject);
//
//        create_vehicle_main =new Create_Vehicle_Main(apiobject);
//        change_vehicle_status_main = new Change_Vehicle_Status_Main(apiobject);
//
//        list_vehicles_main = new List_Vehicles_Main(apiobject);
//
//        get_vehicle_details_main = new Get_Vehicle_Details_Main(apiobject);
//
//        update_vehicle_info_main = new Update_Vehicle_Info_Main(apiobject);
//
//
//
//        login_main.Login_Successfully_English(
//                authanticationDataGenerator.Email_Address_1 ,
//                authanticationDataGenerator.Password);
//
//    }
//
//
//    @Test
//    public void TC_AddVehicle_$_ChangeVehicleStatus_$_GetVehicleDetails_$_ListVehicles_$_UpdateVehicleInfo(){
//        Response Add_Vehicle_Response_English = create_vehicle_main.Add_Vehicle_successfully_English(
//                350,
//                VinNumberGenerator.Vin_Number_Generator_5,
//                "6",
//                "1",
//                "1",
//                "2022",
//                "4",
//                "435",
//                "1",
//                "3",
//                "5");
//
//        Validations.assertThat().response(Add_Vehicle_Response_English)
//                .extractedJsonValue("message")
//                .contains("Vehicle stored successfully")
//                .perform();
//
//
//        // Change_Vehicle_Status :
//
//        Response Change_Vehicle_Status_Response_English = change_vehicle_status_main.Change_Vehicle_Status_successfully_English(
//                "1");
//
//        Validations.assertThat().response(Change_Vehicle_Status_Response_English)
//                .extractedJsonValue("message")
//                .contains("Vehicle status updated successfully")
//                .perform();
//
//
//        // Get_Vehicle_Details
//
//        Response Get_Vehicle_Details_Response_English = get_vehicle_details_main.Get_Vehicle_Details_Successfully_English();
//
//        Validations.assertThat().response(Get_Vehicle_Details_Response_English)
//                .extractedJsonValue("message")
//                .contains("Vehicle information retrieved successfully")
//                .perform();
//
//        // List_Vehicles
//
//        Response List_Vehicles_Response_English = list_vehicles_main.List_Vehicles_Successfully_English();
//
//        Validations.assertThat().response(List_Vehicles_Response_English)
//                .extractedJsonValue("message")
//                .contains("Vehicles retrieved successfully")
//                .perform();
//
//
//        // Update_vehicle_info
//
//        Response update_vehicle_info_Respons_English = update_vehicle_info_main.Update_Vehicle_Info_successfully_English(
//                350,
//                VinNumberGenerator.Vin_Number_Generator_10,
//                "6",
//                "1",
//                "1",
//                "2023",
//                "4",
//                "435",
//                "1",
//                "3",
//                "5");
//
//        Validations.assertThat().response(update_vehicle_info_Respons_English)
//                .extractedJsonValue("message")
//                .contains("Vehicle information updated successfully")
//                .perform();
//    }
//
//
//}