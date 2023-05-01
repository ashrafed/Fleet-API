package Authantication.ResetPassword;

import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static Authantication.Login_Main.Platform;

public class Forget_Pssword_Main {
    private RestActions apiobject;

    private String Forget_Pssword_URL= "v1/auth/forget-password";


    public Forget_Pssword_Main(RestActions apiobject){
        this.apiobject = apiobject;
    }


    public Response Forget_Pssword_Successfully_English(String email){

        JSONObject Login_Body  = new JSONObject();
        Login_Body.put("email" , email);


       return apiobject.buildNewRequest(Forget_Pssword_URL , RestActions.RequestType.POST)
                .setContentType(ContentType.JSON)
                .addHeader("Platform" , Platform )
                .addHeader("Accept" , "application/json")
                .addHeader("Accept-Language", "en")
                .setRequestBody(Login_Body)
                .performRequest();

}
}
