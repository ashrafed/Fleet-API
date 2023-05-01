package Authantication.ResetPassword;

import Authantication.Login_Main;
import MainPackage.DataGenerator.AuthanticationDataGenerator;
import com.shaft.api.RestActions;
import com.shaft.driver.DriverFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Forget_Pssword {
    private RestActions apiobject;
    private Login_Main login_main;

    private AuthanticationDataGenerator authanticationDataGenerator;

    private Forget_Pssword_Main forget_pssword_main;
    private Confirm_Change_Password_Main confirm_change_password_main;
    private Create_New_Paassword_Main create_new_paassword_main;


    @BeforeClass
    public void BeforClass() {
        apiobject = DriverFactory.getAPIDriver(Login_Main.Base_Url);
        login_main = new Login_Main(apiobject);
        forget_pssword_main = new Forget_Pssword_Main(apiobject);

        confirm_change_password_main = new Confirm_Change_Password_Main(apiobject);

        create_new_paassword_main = new Create_New_Paassword_Main(apiobject);


        authanticationDataGenerator = new AuthanticationDataGenerator(apiobject);


    }


    @Test
    public void TC_01_Forget_Pssword_Successfully_() {

        forget_pssword_main.Forget_Pssword_Successfully_English(authanticationDataGenerator.Email_Address_4);

        //forget_pssword_main.Forget_Pssword_Successfully_English(authanticationDataGenerator.Email_Address_4);

        //forget_pssword_main.Forget_Pssword_Successfully_English(authanticationDataGenerator.Email_Address_4);


        confirm_change_password_main.Confirm_Change_Password_Successfully_English(
                authanticationDataGenerator.Email_Address_4, "9531");

        create_new_paassword_main.Create_New_Paassword_Successfully_English(
                authanticationDataGenerator.Email_Address_4,
                "123456",
                "123456");
    }
}
