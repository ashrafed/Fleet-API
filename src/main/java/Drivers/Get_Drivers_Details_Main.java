package Drivers;

import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static Authantication.Login_Main.Platform;

public class Get_Drivers_Details_Main {
    private RestActions apiobject;

    public Get_Drivers_Details_Main(RestActions apiobject){
        this.apiobject = apiobject;
    }


}
