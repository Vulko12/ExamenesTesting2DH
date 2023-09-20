package Test;

import static org.junit.jupiter.api.Assertions.*;
import Pages.PaginaRegistro;
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

public class TestRegistro {
    
    public WebDriver webDriver;
    public WebDriverWait webDriverWait;
    ExtentSparkReporter info = new ExtentSparkReporter("target/REPORTE_REGISTER.html");
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
        PaginaRegistro paginaRegistro = new PaginaRegistro(webDriver);
        paginaRegistro.setup();
        paginaRegistro.url("https://parabank.parasoft.com/parabank/index.htm");
    }
    
    @AfterEach
    public void finish() {
        PaginaRegistro paginaRegistro = new PaginaRegistro(webDriver);
        paginaRegistro.close();
        extent.flush();
    }
    
    @Test
    @Name("Proceso de registro")
    @Tag("suite-final")
    public void register() throws InterruptedException {
    
        ExtentTest test = extent.createTest("Test Registro");
        test.log(Status.INFO, "Test comenzado");
        
        PaginaRegistro paginaRegistro = new PaginaRegistro(webDriver);
        paginaRegistro.clickEnterRegisterPageButton();
        test.log(Status.PASS, "Ingreso de datos en el formulario de registro comenzado");
        paginaRegistro.typeFirstName("Maximo");
        paginaRegistro.typeLastName("Vulcano");
        paginaRegistro.typeAddress("delyak");
        paginaRegistro.typeCity("Buenos Aires");
        paginaRegistro.typeState("Argentina");
        paginaRegistro.typeZipCode("1648");
        paginaRegistro.typeTelephone("1154361187");
        paginaRegistro.typeSsn("1234567890");
        paginaRegistro.typeUsername("maxivulcano");
        paginaRegistro.typePassword("12345Maxi!");
        paginaRegistro.typePassword("12345Maxi!");
        paginaRegistro.typePasswordConfirm("12345Maxi!");
        test.log(Status.PASS, "Ingreso de datos en el formulario de registro finalizado");
        paginaRegistro.clickRegisterButton();
        assertEquals("Your account was created successfully. You are now logged in.", paginaRegistro.getConfirmMessage());
        test.log(Status.PASS, "Registro Exitoso");
    }
    
}
