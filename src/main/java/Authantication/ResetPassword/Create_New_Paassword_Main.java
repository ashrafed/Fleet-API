package Authantication.ResetPassword;

import com.shaft.api.RestActions;
import com.shaft.driver.SHAFT;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static Authantication.Login_Main.Platform;

public class Create_New_Paassword_Main {
    private RestActions apiobject;

    private String Create_New_Paassword_URL = "v1/auth/password";


    public Create_New_Paassword_Main(RestActions apiobject) {
        this.apiobject = apiobject;
    }

    public Response Create_New_Paassword_Successfully_English(String email,
                                                                 String password ,
                                                              String confirm_password) {


        JSONObject Create_New_Paassword_Body = new JSONObject();
        Create_New_Paassword_Body.put("email", email);
        Create_New_Paassword_Body.put("password", password);
        Create_New_Paassword_Body.put("confirm_password", confirm_password);



        return apiobject.buildNewRequest(Create_New_Paassword_URL , RestActions.RequestType.POST)
                .setContentType(ContentType.JSON)
                .addHeader("Platform", Platform)
                .addHeader("Accept", "application/json")
                .addHeader("Accept-Language", "en")
                .setRequestBody(Create_New_Paassword_Body)
                .performRequest();

    }



}

