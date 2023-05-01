package MainPackage.DataGenerator;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.shaft.api.RestActions;

import java.util.Locale;

public class CompanyDataGenerator {

    public CompanyDataGenerator(RestActions apiobject) {
        this.apiobject = apiobject;
    }

    private RestActions apiobject;
    Faker faker = new Faker();
    FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-GB"), new RandomService());

    // Company_Name :

    public String Company_Name = fakeValuesService.bothify("Test_Automation_??##??##??##??##");
    public String Company_Name_1 = fakeValuesService.bothify("Test_Automation_1_??##??##??##??##");
    public String Company_Name_2 = fakeValuesService.bothify("Test_Automation_2_??##??##??##??##");
    public String Company_Name_3 = fakeValuesService.bothify("Test_Automation_3_??##??##??##??##");
    public String Company_Name_4 = fakeValuesService.bothify("Test_Automation_4_??##??##??##??##");
    public String Company_Name_5 = fakeValuesService.bothify("Test_Automation_5_??##??##??##??##");
    public String Company_Name_6 = fakeValuesService.bothify("Test_Automation_6_??##??##??##??##");
    public String Company_Name_7 = fakeValuesService.bothify("Test_Automation_7_??##??##??##??##");
    public String Company_Name_8 = fakeValuesService.bothify("Test_Automation_8_??##??##??##??##");
    public String Company_Name_9 = fakeValuesService.bothify("Test_Automation_9_??##??##??##??##");
    public String Company_Name_10 = fakeValuesService.bothify("Test_Automation_10_??##??##??##??##");

    // Contact_Number :

    public String contact_number = fakeValuesService.bothify("96650#######");
    public String contact_number_1 = fakeValuesService.bothify("96651#######");
    public String contact_number_2 = fakeValuesService.bothify("96653#######");
    public String contact_number_3 = fakeValuesService.bothify("96653#######");
    public String contact_number_4 = fakeValuesService.bothify("96654#######");
    public String contact_number_5 = fakeValuesService.bothify("96655#######");
    public String contact_number_6 = fakeValuesService.bothify("96656#######");
    public String contact_number_7 = fakeValuesService.bothify("96657#######");
    public String contact_number_8 = fakeValuesService.bothify("96658#######");
    public String contact_number_9 = fakeValuesService.bothify("96659#######");
    public String contact_number_10 = fakeValuesService.bothify("966510######");


    // Email_Address :

    public String Email_Address = fakeValuesService.bothify("Test_Automation##????@gmail.com");
    public String Email_Address_1= fakeValuesService.bothify("Test_Automation_1_##????@gmail.com");
    public String Email_Address_2= fakeValuesService.bothify("Test_Automation_2_##????@gmail.com");
    public String Email_Address_3= fakeValuesService.bothify("Test_Automation_3_##????@gmail.com");
    public String Email_Address_4= fakeValuesService.bothify("Test_Automation_4_##????@gmail.com");
    public String Email_Address_5= fakeValuesService.bothify("Test_Automation_5_##????@gmail.com");
    public String Email_Address_6= fakeValuesService.bothify("Test_Automation_6_##????@gmail.com");
    public String Email_Address_7= fakeValuesService.bothify("Test_Automation_7_##????@gmail.com");
    public String Email_Address_8= fakeValuesService.bothify("Test_Automation_8_##????@gmail.com");
    public String Email_Address_9= fakeValuesService.bothify("Test_Automation_9_##????@gmail.com");
    public String Email_Address_10= fakeValuesService.bothify("Test_Automation_10_##????@gmail.com");

    // Commercial_number :

    public String commercial_number = fakeValuesService.regexify("[A-Z][0-9]{10}");
    public String commercial_number_2 = fakeValuesService.regexify("[A-Z][0-9]{10}");
    public String commercial_number_3 = fakeValuesService.regexify("[A-Z][0-9]{10}");
    public String commercial_number_4 = fakeValuesService.regexify("[A-Z][0-9]{10}");
    public String commercial_number_5 = fakeValuesService.regexify("[A-Z][0-9]{10}");
    public String commercial_number_6 = fakeValuesService.regexify("[A-Z][0-9]{10}");



    public String additional_amount = fakeValuesService.regexify("(BE)?0[0-9]{9}");
    public String admin_name = fakeValuesService.bothify("Test_Automation_??##??##");

}
