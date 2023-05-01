package MainPackage.DataGenerator;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.shaft.api.RestActions;

import java.util.Locale;

public class VehiclesDataGenerator {

    public VehiclesDataGenerator(RestActions apiobject) {
        this.apiobject = apiobject;
    }
    private RestActions apiobject;

    Faker faker = new Faker();
    FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-GB"), new RandomService());
    public String Driver_Name_Generator = "Test_Automation " + faker.name().username();   //Name_Generator

    /*
    {
    "driver_id": 5,
    "vin_number": 3,
    "type": "d",
    "model_id": "d",
    "brand_id": "d",
    "status" : "d",
    "year": "d",
    "engine_type_id": "d",
    "plate_number": 22,
    "plate_letter_1_id": "d",
    "plate_letter_2_id": "d",
    "plate_letter_3_id": "d"
}
     */



}
