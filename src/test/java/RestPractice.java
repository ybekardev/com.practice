import com.google.gson.JsonObject;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class RestPractice {

       // @BeforeClass
        public void  init(){
            System.out.println("Testing started...");
        }


        public void customerRequestObject() {

            RestAssured.baseURI = "https://petstore.swagger.io/";
            RestAssured.basePath = "#/pet/updatePetWithForm";
            String redirectUri = "";
            String RTBCust = "";

            JsonObject loginCredentials = new JsonObject();
            loginCredentials.addProperty("client_id", RTBCust);
            loginCredentials.addProperty("redirect_uri", redirectUri);
            loginCredentials.addProperty("service", "mdn-tree");
            loginCredentials.addProperty("state", "xyz");
            loginCredentials.addProperty("nonce", "Test5678");
            loginCredentials.addProperty("response_type", "code");
            loginCredentials.addProperty("scope", "openid");

            //The DefaultContent is declared as FALSE with the configuration, content type is considered as Json this way
            EncoderConfig encoderconfig = new EncoderConfig();

            Response response = RestAssured
                    .given()
                    .log().all()
                    .config(RestAssured.config().encoderConfig(encoderconfig.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                    .header("Content-Type", "application/json")
                    .header("messageId", "995e59ed-da1c-44e2-862f-fb87c58fd79c")
                    .header("messageDateTimeStamp", "Wed Nov 27 2019 10:05:26 GMT-0600 (Central Standard Time)")
                    .header("enterpriseMessageId", "995e59ed-da1c-44e2-862f-fb87c58fd79c")
                    .header("applicationUserId", "XNZ")
                    .header("applicationId", "XNZ")

                    .body(loginCredentials.toString())
                    .when()
                    .post("/requestObject")
                    .then()
                    .statusCode(200)
                    .extract()
                    .response();

            String generateCustJWT = response
                    .body()
                    .path("request");

            System.out.println("The JWT token : "+ generateCustJWT);
        }

       // @AfterClass
        public void finalize(){
            RestAssured.reset();
            System.out.println("Testing ended...");
        }
    }

