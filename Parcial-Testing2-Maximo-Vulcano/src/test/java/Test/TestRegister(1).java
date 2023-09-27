package Test;

import static org.junit.jupiter.api.Assertions.*;
import Pages.RegisterPage;
import Report.ExtentFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import jdk.jfr.Name;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestRegister {
    
    public WebDriver webDriver;
    public WebDriverWait webDriverWait;
    ExtentSparkReporter info = new ExtentSparkReporter("target/REPORTE_REGISTRO.html");
    ExtentReports extent;
    
    @BeforeEach
    public void setUp() throws InterruptedException {
        // Setup Driver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
        webDriverWait = new WebDriverWait(webDriver, Duration.ofMillis(2000));
        // Setup Extent Report
        extent = ExtentFactory.getInstance();
        extent.attachReporter(info);
        // Setup browser
        RegisterPage registerPage = new RegisterPage(webDriver);
        registerPage.setup();
        registerPage.url("https://opencart.abstracta.us");
    }
    
    @AfterEach
    public void finish() throws InterruptedException {
        RegisterPage registerPage = new RegisterPage(webDriver);
        registerPage.close();
        extent.flush();
    }
    
    @Test
    @Name("Proceso de registro")
    @Tag("suite-parcial")
    public void register() throws InterruptedException {
    
        ExtentTest test = extent.createTest("Registro de usuario");
        test.log(Status.INFO, "Registro de usuario comenzado");
        
        RegisterPage registerPage = new RegisterPage(webDriver);
        registerPage.clickEnterRegisterPageButton();
        test.log(Status.PASS, "Ingreso a la pagina de registro");
        registerPage.typeFirstName("Maximo");
        registerPage.typeLastName("Vulcano");
        registerPage.typeEmail("Maxivulcano12@gmail.com");
        registerPage.typeTelephone("12345678");
        registerPage.typePassword("12345");
        registerPage.typePasswordConfirm("12345");
        registerPage.clickPrivacyPolicyCheckbox();
        test.log(Status.PASS, "Datos ingresados correctamente");
        registerPage.clickRegisterButton();
        String currentUrl = registerPage.getUrl();
        assertEquals("http://opencart.abstracta.us/index.php?route=account/success",currentUrl);
        test.log(Status.PASS, "Registro de usuario exitoso");
    }
    
}
