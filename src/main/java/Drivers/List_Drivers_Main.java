package Drivers;

import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static Authantication.Login_Main.Platform;

public class List_Drivers_Main {
    private RestActions apiobject;

    public List_Drivers_Main(RestActions apiobject){
        this.apiobject = apiobject;
    }


}
