package TestBack;

import Report.ExtentFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import jdk.jfr.Name;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;

public class TestPOST {
    
    private String username = "maxivulcano";
    private String password = "12345Maxi!";
    private String customerId;
    private String accountId;
    ExtentSparkReporter info = new ExtentSparkReporter("target/REPORTE_POST.html");
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
    @Name("Test Abrir una nueva cuenta")
    @Tag("suite-final")
    public void openAccount() {
        ExtentTest test = extent.createTest("Test Apertura de una nueva cuenta");
        String response = given()
                .post("https://parabank.parasoft.com/parabank/services/bank/createAccount?customerId=" + customerId + "&newAccountType=1&fromAccountId=" + accountId)
                .then()
                .statusCode(200)
                .log().status()
                .log().body()
                .extract().asString();
        int tagInicio = response.indexOf("<id>") + "<id>".length();
        int tagFinal = response.indexOf("</id>");
        String newAccountId = response.substring(tagInicio,tagFinal);
        System.out.println("New Account ID: " + newAccountId);
        test.log(Status.PASS,"Apertura de nueva cuenta exitosa");
    }
    
    @Test
    @Name("Test Descarga de fondos")
    @Tag("suite-final")
    public void transfer() {
        ExtentTest test = extent.createTest("Test Transferencia");
        String response = given()
                .post("https://parabank.parasoft.com/parabank/services/bank/transfer?fromAccountId=" + accountId + "&toAccountId=" + accountId + "&amount=1000")
                .then()
                .statusCode(200)
                .extract().asString();
        System.out.println(response);
        test.log(Status.PASS,"Transferencia exitosa");
    }
    
}
