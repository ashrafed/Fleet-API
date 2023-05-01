package MainPackage.DataGenerator;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.shaft.api.RestActions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

// Drivers Data Generator :
public class DriversDataGenerator {
    public DriversDataGenerator(RestActions apiobject) {
        this.apiobject = apiobject;
    }

    private RestActions apiobject;


    // Create Driver  =Data Generator

    Faker faker = new Faker();
    FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-GB"), new RandomService());

    String Get_Current_Time = String.valueOf(System.currentTimeMillis());


    // ********* Driver Name Generator ********* :
    public String Driver_Name_Generator = "Test_Automation"+ Get_Current_Time;     //Name_Generator
    public String Driver_Name_Generator_2 = "Test_Automation2" + Get_Current_Time;    //Name_Generator 2
    public String Driver_Name_Generator_3 = "Test_Automation3" + Get_Current_Time;    //Name_Generator 3
    public String Driver_Name_Generator_4 = "Test_Automation4" + Get_Current_Time;    //Name_Generator 4
    public String Driver_Name_Generator_5 = "Test_Automation5" + Get_Current_Time;    //Name_Generator 5


    // ********* License Expire Date ********* :

    public String license_expire_date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());



    public String Register_PhoneNumber_Generattor = fakeValuesService.regexify("(966)(5)(0)[0-9]{7}");  //PhoneNumber_Generattor//
    public String Register_PhoneNumber_Generattor_2 = fakeValuesService.regexify("(966)(5)(1)[0-9]{7}");  //PhoneNumber_Generattor 2// public String  Register_PhoneNumber_Generattor_3= fakeValuesService.regexify("(966)(5)(11)[0-9]{6}");  //PhoneNumber_Generattor 3
    public String Register_PhoneNumber_Generattor_4 = fakeValuesService.regexify("(966)(5)(3)[0-9]{7}");  //PhoneNumber_Generattor 4

    public String Register_PhoneNumber_Generattor_5 = fakeValuesService.regexify("(966)(5)(4)[0-9]{7}");   //PhoneNumber_Generattor 5


    public String Register_PhoneNumber_Generattor_6 = fakeValuesService.regexify("(966)(5)(5)[0-9]{7}");   //PhoneNumber_Generattor 6
    public String Register_PhoneNumber_Generattor_7 = fakeValuesService.regexify("(966)(5)(6)[0-9]{7}");   //PhoneNumber_Generattor 7

    public String Register_PhoneNumber_Generattor_8 = fakeValuesService.regexify("(966)(5)(7)[0-9]{7}");   //PhoneNumber_Generattor 8

    public String Register_PhoneNumber_Generattor_9 = fakeValuesService.regexify("(966)(5)(8)[0-9]{7}");   //PhoneNumber_Generattor 9

    public String Register_PhoneNumber_Generattor_10 = fakeValuesService.regexify("(966)(5)(9)[0-9]{7}");   //PhoneNumber_Generattor 10






    // These Phone Numbers Are Created For User Flow Only And Doesn't Allow To Use Them In Any Test Case To Avoid Repeating.
    public String TCF_Register_PhoneNumber_Generator_01 = fakeValuesService.regexify("(966)(5)(5)[0-9]{7}");   //PhoneNumber_Generattor 6
    public String TCF_Register_PhoneNumber_Generator_02 = fakeValuesService.regexify("(966)(5)(6)[0-9]{7}");   //PhoneNumber_Generattor 7
    public String TCF_Register_PhoneNumber_Generator_03 = fakeValuesService.regexify("(966)(5)(7)[0-9]{7}");   //PhoneNumber_Generattor 8
    public String TCF_Register_PhoneNumber_Generator_04 = fakeValuesService.regexify("(966)(5)(8)[0-9]{7}");   //PhoneNumber_Generattor 9
    public String TCF_Register_PhoneNumber_Generator_05 = fakeValuesService.regexify("(966)(5)(9)[0-9]{7}");   //PhoneNumber_Generattor 10
    public String TCF_Register_PhoneNumber_Generator_06 = fakeValuesService.regexify("(966)(5)(10)[0-9]{6}");   //PhoneNumber_Generattor 10
//______________________________________________________________________________________________________________________________________//

    public String job_Data_Generattor = faker.job().title();

    public String Update_Email_Generattor = faker.internet().emailAddress();

    public int City_Id_Generattor = faker.number().numberBetween(10, 15);


    // Residence number
    public String  Residence_Number_Generattor = String.valueOf(faker.number().numberBetween(10000000 , 10100000));
    public String  Residence_Number_Generattor_2 = String.valueOf(faker.number().numberBetween(10100000 , 10200000));
    public String  Residence_Number_Generattor_3 = String.valueOf(faker.number().numberBetween(10200000 , 10300000));
    public String  Residence_Number_Generattor_4 = String.valueOf(faker.number().numberBetween(10300000 , 10400000));
    public String  Residence_Number_Generattor_5 = String.valueOf(faker.number().numberBetween(10400000 , 10500000));
    public String  Residence_Number_Generattor_6 = String.valueOf(faker.number().numberBetween(10500000 , 10600000));


    // These Residence Numbers Are Created For User Flow Only And Doesn't Allow To Use Them In Any Test Case To Avoid Repeating.
    public String  TCF_Residence_Number_Generator_01 = String.valueOf(faker.number().numberBetween(10600000 , 10700000));
    public String  TCF_Residence_Number_Generator_02 = String.valueOf(faker.number().numberBetween(10700001 , 10800000));
    public String  TCF_Residence_Number_Generator_03 = String.valueOf(faker.number().numberBetween(10800001 , 10900000));
    public String  TCF_Residence_Number_Generator_04 = String.valueOf(faker.number().numberBetween(10900001 , 11000000));
    public String  TCF_Residence_Number_Generattor_05 = String.valueOf(faker.number().numberBetween(11000001 , 11100000));
    public String  TCF_Residence_Number_Generattor_06 = String.valueOf(faker.number().numberBetween(11100001 , 11200000));
    public String  TCF_Residence_Number_Generattor_07 = String.valueOf(faker.number().numberBetween(11200001 , 11300000));
    public String  TCF_Residence_Number_Generattor_08 = String.valueOf(faker.number().numberBetween(11300001 , 11400000));
    public String  TCF_Residence_Number_Generattor_09 = String.valueOf(faker.number().numberBetween(11400001 , 11500000));
    public String  TCF_Residence_Number_Generattor_10 = String.valueOf(faker.number().numberBetween(11500000 , 11600000));

    //____________________________________________________________________________________________________________________//


    public String  Residence_Number_Generattor_7 = String.valueOf(faker.number().numberBetween(11600000 , 11700000));
    public String  Residence_Number_Generattor_8 = String.valueOf(faker.number().numberBetween(11700000 , 11800000));
    public String  Residence_Number_Generattor_9 = String.valueOf(faker.number().numberBetween(11800000 , 11900000));
    public String  Residence_Number_Generattor_10 = String.valueOf(faker.number().numberBetween(11900000 , 12000000));
    public String  Residence_Number_Generattor_11 = String.valueOf(faker.number().numberBetween(12000000 , 12100000));
    public String  Residence_Number_Generattor_12 = String.valueOf(faker.number().numberBetween(12100000 , 12200000));
    public String  Residence_Number_Generattor_13 = String.valueOf(faker.number().numberBetween(12200000 , 12300000));
    public String  Residence_Number_Generattor_14 = String.valueOf(faker.number().numberBetween(12300000 , 12400000));
    public String  Residence_Number_Generattor_15 = String.valueOf(faker.number().numberBetween(12400000 , 12500000));
    public String  Residence_Number_Generattor_16 = String.valueOf(faker.number().numberBetween(12500000 , 12600000));
    public String  Residence_Number_Generattor_17 = String.valueOf(faker.number().numberBetween(12600000 , 12700000));
    public String  Residence_Number_Generattor_18 = String.valueOf(faker.number().numberBetween(12700000 , 12800000));
    public String  Residence_Number_Generattor_19 = String.valueOf(faker.number().numberBetween(12800000 , 12900000));
    public String  Residence_Number_Generattor_20 = String.valueOf(faker.number().numberBetween(12900000 , 13000000));
    public String  Residence_Number_Generattor_21 = String.valueOf(faker.number().numberBetween(13000000 , 13100000));
    public String  Residence_Number_Generattor_22 = String.valueOf(faker.number().numberBetween(13100000 , 13200000));
    public String  Residence_Number_Generattor_23 = String.valueOf(faker.number().numberBetween(13200000 , 13300000));
    public String  Residence_Number_Generattor_24 = String.valueOf(faker.number().numberBetween(13300000 , 13400000));
    public String  Residence_Number_Generattor_25 = String.valueOf(faker.number().numberBetween(13400000 , 13500000));
    public String  Residence_Number_Generattor_26 = String.valueOf(faker.number().numberBetween(13500000 , 13600000));
    public String  Residence_Number_Generattor_27 = String.valueOf(faker.number().numberBetween(13600000 , 13700000));
    public String  Residence_Number_Generattor_28 = String.valueOf(faker.number().numberBetween(13700000 , 13800000));
    public String  Residence_Number_Generattor_29 = String.valueOf(faker.number().numberBetween(13800000 , 13900000));
    public String  Residence_Number_Generattor_30 = String.valueOf(faker.number().numberBetween(13900000 , 14000000));

    public String  Residence_Number_Generattor_31 = String.valueOf(faker.number().numberBetween(14000000 , 14100000));
    public String  Residence_Number_Generattor_32 = String.valueOf(faker.number().numberBetween(14100000 , 14200000));
    public String  Residence_Number_Generattor_33 = String.valueOf(faker.number().numberBetween(14200000 , 14300000));
    public String  Residence_Number_Generattor_34 = String.valueOf(faker.number().numberBetween(14300000 , 14400000));
    public String  Residence_Number_Generattor_35 = String.valueOf(faker.number().numberBetween(14400000 , 14500000));
    public String  Residence_Number_Generattor_36 = String.valueOf(faker.number().numberBetween(14500000 , 14600000));
    public String  Residence_Number_Generattor_37 = String.valueOf(faker.number().numberBetween(14600000 , 14700000));
    public String  Residence_Number_Generattor_38 = String.valueOf(faker.number().numberBetween(14700000 , 14800000));
    public String  Residence_Number_Generattor_39 = String.valueOf(faker.number().numberBetween(14700000 , 14900000));
    public String  Residence_Number_Generattor_40 = String.valueOf(faker.number().numberBetween(14900000 , 15000000));











    public String otp = "9531";
    public String Register_email_Generattor = faker.internet().emailAddress(); //email_Generattor
    public String Register_password_Generattor = fakeValuesService.regexify("[A-Z][1-6]{6}");  //password_Generattor
    public String Register_responsible_person_Generattor = faker.name().name();  //


    // Add Vehicle

    public String Vehicle_Name_Generattor_successfully = "Test_Automation_" + faker.company().name();
    public String Vehicle_Name_Generattor = "Test_Automation_" + faker.company().name();
    public String Vehicle_Name_Generattor_2 = "Test_Automation_" + Register_PhoneNumber_Generattor + faker.company().name();


    String Name1 = fakeValuesService.regexify("[A-Z][1-6]{6}");
    public String Vehicle_Name_Generattor_3 = "Test_Automation_" + Name1 + faker.company().name();


    String Name2 = fakeValuesService.regexify("[A-Z][6-20]{5}");
    public String Vehicle_Name_Generattor_4 = "Test_Automation_" + Name2 + faker.company().name();


    String Name3 = fakeValuesService.regexify("[A-Z][6-20]{5}");
    public String Vehicle_Name_Generattor_5 = "Test_Automation_" + Name3 + faker.company().name();


    String Name4 = fakeValuesService.regexify("[A-Z][6-20]{5}");
    public String Vehicle_Name_Generattor_6 = "Test_Automation_" + Name4 + faker.company().name();


    String Name5 = fakeValuesService.regexify("[A-Z][6-20]{5}");
    public String Vehicle_Name_Generattor_7 = "Test_Automation_" + Name4 + faker.company().name();


    String Name6 = fakeValuesService.regexify("[A-Z][6-20]{5}");
    public String Vehicle_Name_Generattor_8 = "Test_Automation_" + Name4 + faker.company().name();

    String Name7 = fakeValuesService.regexify("[A-Z][6-20]{5}");
    public String Vehicle_Name_Generattor_9 = "Test_Automation_" + Name4 + faker.company().name();


    String Name10 = fakeValuesService.regexify("[A-Z][6-20]{5}");
    public String Vehicle_Name_Generattor_10 = "Test_Automation_" + Name10 + faker.company().name();


    String Name11 = fakeValuesService.regexify("[A-Z][6-20]{5}");
    public String Vehicle_Name_Generattor_11 = "Test_Automation_" + Name11 + faker.company().name();


    String Name12 = fakeValuesService.regexify("[A-Z][6-20]{5}");
    public String Vehicle_Name_Generattor_12 = "Test_Automation_" + Name12 + faker.company().name();


    String Name13 = fakeValuesService.regexify("[A-Z][6-20]{5}");
    public String Vehicle_Name_Generattor_13 = "Test_Automation_" + Name13 + faker.company().name();


    String Name14 = fakeValuesService.regexify("[A-Z][6-20]{5}");
    public String Vehicle_Name_Generattor_14 = "Test_Automation_" + Name14 + faker.company().name();

    String Name15 = fakeValuesService.regexify("[A-Z][6-20]{5}");
    public String Vehicle_Name_Generattor_15 = "Test_Automation_" + Name15 + faker.company().name();


    String Name16 = fakeValuesService.regexify("[A-Z][6-20]{5}");
    public String Vehicle_Name_Generattor_16 = "Test_Automation_" + Name16 + faker.company().name();


    String Name17 = fakeValuesService.regexify("[A-Z][6-20]{5}");
    public String Vehicle_Name_Generattor_17 = "Test_Automation_" + Name17 + faker.company().name();


    String Name18 = fakeValuesService.regexify("[A-Z][6-20]{5}");
    public String Vehicle_Name_Generattor_18 = "Test_Automation_" + Name18 + faker.company().name();

    String Name19 = fakeValuesService.regexify("[A-Z][6-20]{5}");
    public String Vehicle_Name_Generattor_19 = "Test_Automation_" + Name19 + faker.company().name();

    String Name20 = fakeValuesService.regexify("[A-Z][6-20]{5}");
    public String Vehicle_Name_Generattor_20 = "Test_Automation_" + Name20 + faker.company().name();

    String Name21 = fakeValuesService.regexify("[A-Z][6-20]{5}");
    public String Vehicle_Name_Generattor_21 = "Test_Automation_" + Name21 + faker.company().name();


    public String Vehicle_person_id_Generattor = fakeValuesService.regexify("([1-8][0-9]{4}|9[0-8][0-9]{3}|99[0-8][0-9]{2}|999[0-8][0-9]|9999[0-9]|100000)");   // numberBetween(10000, 100000)
    public String Vehicle_sequence_number_Generattor = fakeValuesService.regexify("[1-9]{9}");

    public String Vehicle_sequence_number_ElM_Generattor = fakeValuesService.regexify("(3538831[1-8][0-9]|35388319[0-9]|353883[2-9][0-9]{2}|35388[4-9][0-9]{3}|35389[0-9]{4}|3539[0-9]{5}|35[4-9][0-9]{6}|3[6-9][0-9]{7}|[4-8][0-9]{8}|9[0-4][0-9]{7}|95[0-2][0-9]{6}|953[0-7][0-9]{5}|9538[0-7][0-9]{4}|95388[0-2][0-9]{3}|9538830[0-9]{2}|95388310[0-9]|953883110)");  // Number Betwen [353883110 :  953883110]
    public String Vehicle_sequence_number_ElM_Generattor_2 = fakeValuesService.regexify("(3538831[1-8][0-9]|35388319[0-9]|353883[2-9][0-9]{2}|35388[4-9][0-9]{3}|35389[0-9]{4}|3539[0-9]{5}|35[4-9][0-9]{6}|3[6-9][0-9]{7}|[4-8][0-9]{8}|9[0-4][0-9]{7}|95[0-2][0-9]{6}|953[0-7][0-9]{5}|9538[0-7][0-9]{4}|95388[0-2][0-9]{3}|9538830[0-9]{2}|95388310[0-9]|953883110)");  // Number Betwen [353883110 :  953883110]

    public String Vehicle_brand_id_Generattor = fakeValuesService.regexify("([1-6])");
    public String Vehicle_model_id_Generattor = fakeValuesService.regexify("(61[0-3])");  // numberBetween(610 , 613)
    public String Vehicle_color_id_Generattor = fakeValuesService.regexify("([1-9]|10)");
    public String Vehicle_engine_type_id_Generattor = fakeValuesService.regexify("([4-9]|1[01])");  // numberBetween(4, 11)
    public String Vehicle_model_year_Generattor = fakeValuesService.regexify("(201[2-9]|202[0-2])"); // numberBetween(2012, 2022)
    public String Vehicle_plate_number_Generattor = fakeValuesService.regexify("([1-8][0-9]{3}|9[0-8][0-9]{2}|99[0-8][0-9]|999[0-9])");   // numberBetween(1000, 9999)
    public String Vehicle_plate_letter_1_id_Generattor = fakeValuesService.regexify("([1-4])");  //numberBetween(1 , 4)
    public String Vehicle_plate_letter_2_id_Generattor = fakeValuesService.regexify("([4-7])");  //numberBetween(4,7)
    public String Vehicle_plate_letter_3_id_Generattor = fakeValuesService.regexify("([6-9]|10)"); //numberBetween(6,10);
    public String Vehicle_vin_number_Generattor = fakeValuesService.regexify("b[(A-H|J-N|P|R-Z|0-9)]{17}b");


    // Check_city :
    public String City_Latitude = faker.address().latitude();
    public String City_longitude = faker.address().longitude();

    // Timestamp


}


