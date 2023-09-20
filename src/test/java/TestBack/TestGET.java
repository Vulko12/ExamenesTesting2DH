package TestBack;

import Report.ExtentFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import jdk.jfr.Name;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.testng.annotations.BeforeSuite;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;


public class TestGET {
    
    private String username = "maxivulcano";
    private String password = "12345Maxi!";
    private String customerId;
    private String accountId;
    ExtentSparkReporter info = new ExtentSparkReporter("target/REPORTE_GET.html");
    ExtentReports extent;
    
    @BeforeEach
    public void setup() {
        // Login
        String responseLogin = given()
                .get("https://parabank.parasoft.com/parabank/services/bank/login/" + username + "/" + password)
                .then()
                .statusCode(200)
                .extract().asString();
        int tagInicioLogin = responseLogin.indexOf("<id>") + "<id>".length();
        int tagFinalLogin = responseLogin.indexOf("</id>");
        customerId = responseLogin.substring(tagInicioLogin,tagFinalLogin);
        // Obtener cuenta
        String responseAccount = given()
                .get("http://parabank.parasoft.com/parabank/services/bank/customers/" + customerId + "/accounts")
                .then()
                .statusCode(200)
                .extract().asString();
        int tagInicioAccount = responseAccount.indexOf("<id>") + "<id>".length();
        int tagFinalAccount = responseAccount.indexOf("</id>");
        accountId = responseAccount.substring(tagInicioAccount,tagFinalAccount);
        // Extent Report
        extent = ExtentFactory.getInstance();
        extent.attachReporter(info);
    }
    
    @AfterEach
    public void finish() {
        extent.flush();
    }
    
    @Test
    @Name("Test Registro")
    @Tag("suite-final")
    public void register() {
        Response response = RestAssured.get("https://parabank.parasoft.com/parabank/register.htm");
        ExtentTest test = extent.createTest("Test Registro Backend");
        assertEquals(200,response.getStatusCode());
        test.log(Status.PASS,"Registro exitoso");
    }
    
    @Test
    @Name("Test Actividad de la cuenta (cada mes)")
    @Tag("suite-final")
    public void transactionsByMonth() {
        ExtentTest test = extent.createTest("Test Visualización de actividad de la cuenta");
        String response = given()
                .get("https://parabank.parasoft.com/parabank/services/bank/accounts/" + accountId + "/transactions/month/All/type/All")
                .then()
                .statusCode(200)
                .log().body()
                .extract().asString();
        System.out.println(response);
        test.log(Status.PASS,"Visualización de actividad de la cuenta exitosa");
    }
    
}
