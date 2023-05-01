package Orders;

import Authantication.Login_Main;
import MainPackage.DataGenerator.AuthanticationDataGenerator;
import MainPackage.DataGenerator.DriversDataGenerator;
import MainPackage.Main_Variables;
import com.shaft.api.RestActions;
import com.shaft.driver.DriverFactory;
import com.shaft.validation.Validations;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class List_Orders {
    private RestActions apiobject;
    private Main_Variables main_variables;
    private AuthanticationDataGenerator authanticationDataGenerator;
    private DriversDataGenerator driversDataGenerator;

    private Login_Main login_main;
    private Create_Order_Main create_order_main;

    private List_Orders_Main list_orders_main;


    @BeforeClass
    public void Beforclase(){
        apiobject = DriverFactory.getAPIDriver(Login_Main.Base_Url);
        main_variables = new Main_Variables(apiobject);
        authanticationDataGenerator = new AuthanticationDataGenerator(apiobject);
        driversDataGenerator = new DriversDataGenerator(apiobject);
        login_main = new Login_Main(apiobject);
        create_order_main = new Create_Order_Main(apiobject);

        list_orders_main = new List_Orders_Main(apiobject);


        login_main.Login_Successfully_English(
                authanticationDataGenerator.Email_Address_1 ,
                authanticationDataGenerator.Password);
    }

    @Test
    public void TC_01_List_Orders_Successfully_English_And_Arabic(){

        Response List_Orders_Response_English = list_orders_main.List_Orders_Successfully_English();

        Validations.assertThat().response(List_Orders_Response_English)
                .extractedJsonValue("message")
                .contains("Order retrieved successfully")
                .perform();


        Response List_Orders_Response_Arabic = list_orders_main.List_Orders_Successfully_Arabic();

        Validations.assertThat().response(List_Orders_Response_Arabic)
                .extractedJsonValue("message")
                .contains("تم إسترجاع الطلبات بنجاح")
                .perform();
    }

    @Test
    public void TC_02_List_Orders_Fail_English_And_Arabic(){

        Response List_Orders_Response_English = list_orders_main.List_Orders_Fail_English();

        Validations.assertThat().response(List_Orders_Response_English)
                .extractedJsonValue("errors")
                .contains("Company is invalid")
                .perform();
    }
    @Test
    public void TC_02_List_Orders_Fail_Arabic() {


        Response List_Orders_Response_Arabic = list_orders_main.List_Orders_Fail_Arabic();

        Validations.assertThat().response(List_Orders_Response_Arabic)
                .extractedJsonValue("errors")
                .contains("الشركة غير صالحة")
                .perform();
    }

}
