package MainPackage;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.shaft.api.RestActions;

import java.util.Locale;

public class Main_Variables {

    private RestActions apiobject;

    public Main_Variables(RestActions apiobject){this.apiobject = apiobject;}

    Faker faker = new Faker();
    static FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-GB"), new RandomService());

    public static final int    timOut = 100000;    //Time out


    public static final String special_characters_Generattor = fakeValuesService.regexify("[$&@#%!]{10}");


    public static final String Unlimited_character = fakeValuesService.regexify("[A-Z][a-z]{500}");
    public static final String MIND_MAP_LINK ="https://mm.tt/map/2214366919?t=CFxsQ428gX";


}

