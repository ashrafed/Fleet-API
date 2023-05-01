package Company;

import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static Authantication.Login_Main.Platform;

public class Create_Company_Main {
    private RestActions apiobject;
    private String Create_Company_URL = "v1/companies";

    public Create_Company_Main(RestActions apiobject) {
        this.apiobject = apiobject;
    }





    public Response Create_Company_Successfully_English(String name,
                                                        String contact_number,
                                                        String email,
                                                        String commercial_number,
                                                        String additional_amount,
                                                        String admin_name,
                                                        String password) {

        JSONObject Create_Company_Body = new JSONObject();

        Create_Company_Body.put("name", name);
        Create_Company_Body.put("contact_number", contact_number);
        Create_Company_Body.put("email", email);
        Create_Company_Body.put("commercial_number", commercial_number);
        Create_Company_Body.put("additional_amount", additional_amount);
        Create_Company_Body.put("admin_name", admin_name);
        Create_Company_Body.put("password", password);


        return apiobject.buildNewRequest(Create_Company_URL, RestActions.RequestType.POST)
                .setContentType(ContentType.JSON)
                .addHeader("Platform", Platform)
                .addHeader("Accept", "application/json")
                .addHeader("Accept-Language", "en")
                .setRequestBody(Create_Company_Body)
                .setTargetStatusCode(201)
                .performRequest();
    }

    public Response Create_Company_Successfully_Arabic(String name,
                                                       String contact_number,
                                                       String email,
                                                       String commercial_number,
                                                       String additional_amount,
                                                       String admin_name,
                                                       String password) {

        JSONObject Create_Company_Body = new JSONObject();

        Create_Company_Body.put("name", name);
        Create_Company_Body.put("contact_number", contact_number);
        Create_Company_Body.put("email", email);
        Create_Company_Body.put("commercial_number", commercial_number);
        Create_Company_Body.put("additional_amount", additional_amount);
        Create_Company_Body.put("admin_name", admin_name);
        Create_Company_Body.put("password", password);


        return apiobject.buildNewRequest(Create_Company_URL, RestActions.RequestType.POST)
                .setContentType(ContentType.JSON)
                .addHeader("Platform", Platform)
                .addHeader("Accept", "application/json")
                .addHeader("Accept-Language", "ar")
                .setRequestBody(Create_Company_Body)
                .setTargetStatusCode(201)
                .performRequest();
    }

    public Response Create_Company_Failed_English(String name,
                                                  String contact_number,
                                                  String email,
                                                  String commercial_number,
                                                  String additional_amount,
                                                  String admin_name,
                                                  String password) {

        JSONObject Create_Company_Body = new JSONObject();

        Create_Company_Body.put("name", name);
        Create_Company_Body.put("contact_number", contact_number);
        Create_Company_Body.put("email", email);
        Create_Company_Body.put("commercial_number", commercial_number);
        Create_Company_Body.put("additional_amount", additional_amount);
        Create_Company_Body.put("admin_name", admin_name);
        Create_Company_Body.put("password", password);


        return apiobject.buildNewRequest(Create_Company_URL, RestActions.RequestType.POST)
                .setContentType(ContentType.JSON)
                .addHeader("Platform", Platform)
                .addHeader("Accept", "application/json")
                .addHeader("Accept-Language", "en")
                .setRequestBody(Create_Company_Body)
                .setTargetStatusCode(422)
                .performRequest();
    }


    public Response Create_Company_Failed_Arabic(String name,
                                                 String contact_number,
                                                 String email,
                                                 String commercial_number,
                                                 String additional_amount,
                                                 String admin_name,
                                                 String password) {

        JSONObject Create_Company_Body = new JSONObject();

        Create_Company_Body.put("name", name);
        Create_Company_Body.put("contact_number", contact_number);
        Create_Company_Body.put("email", email);
        Create_Company_Body.put("commercial_number", commercial_number);
        Create_Company_Body.put("additional_amount", additional_amount);
        Create_Company_Body.put("admin_name", admin_name);
        Create_Company_Body.put("password", password);


        return apiobject.buildNewRequest(Create_Company_URL, RestActions.RequestType.POST)
                .setContentType(ContentType.JSON)
                .addHeader("Platform", Platform)
                .addHeader("Accept", "application/json")
                .addHeader("Accept-Language", "ar")
                .setRequestBody(Create_Company_Body)
                .setTargetStatusCode(422)
                .performRequest();
    }


}
