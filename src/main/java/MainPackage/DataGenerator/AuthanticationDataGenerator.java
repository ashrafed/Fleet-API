package MainPackage.DataGenerator;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.shaft.api.RestActions;

import java.util.Locale;

public class AuthanticationDataGenerator {

    public AuthanticationDataGenerator(RestActions apiobject) {
        this.apiobject = apiobject;
    }

    private RestActions apiobject;

    Faker faker = new Faker();
    FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-GB"), new RandomService());

    // Login

    public String Email_Address = "mock@gmail.com";   //Email static
    public String Password = "123456";           //password static

    public String Email_Address_1 = "TestAutomation@gmail.com";   //Email static 1
    public String Email_Address_2 = "TestAutomation2@gmail.com";   //Email static 2
    public String Email_Address_3 = "TestAutomation3@gmail.com";   //Email static 3
    public String Email_Address_4 = "mramadan@carefer.co";   //Email static 4

    public String Operation_Mail = "operation@test.com";   // Operation Mail to Display All Company



}
