package Authantication.ResetPassword;


import com.shaft.api.RestActions;
import com.shaft.driver.SHAFT;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.stringtemplate.v4.ST;

import static Authantication.Login_Main.Platform;

public class Confirm_Change_Password_Main {

    private RestActions apiobject;


    private String Confirm_Change_Password_URL = "v1/auth/confirm-change-password";


    public Confirm_Change_Password_Main(RestActions apiobject) {
        this.apiobject = apiobject;
    }


    public Response Confirm_Change_Password_Successfully_English(String email,
                                                                 String OTP) {

        JSONObject Confirm_Change_Password_Body = new JSONObject();
        Confirm_Change_Password_Body.put("email", email);
        Confirm_Change_Password_Body.put("otp", OTP);


        return apiobject.buildNewRequest(Confirm_Change_Password_URL , RestActions.RequestType.POST)
                .setContentType(ContentType.JSON)
                .addHeader("Platform", Platform)
                .addHeader("Accept", "application/json")
                .addHeader("Accept-Language", "en")
                .setRequestBody(Confirm_Change_Password_Body)
                .performRequest();

    }
}
