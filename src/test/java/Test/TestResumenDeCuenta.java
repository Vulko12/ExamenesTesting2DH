package Test;

import Pages.ResumenDeCuenta;
import Report.ExtentFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import jdk.jfr.Name;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestResumenDeCuenta {
    
    public WebDriver webDriver;
    public WebDriverWait webDriverWait;
    ExtentSparkReporter info = new ExtentSparkReporter("target/REPORTE_ACCOUNT_OVERVIEW.html");
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
        ResumenDeCuenta resumenDeCuenta = new ResumenDeCuenta(webDriver);
        resumenDeCuenta.setup();
        resumenDeCuenta.url("https://parabank.parasoft.com/parabank/index.htm");
    }
    
    @AfterEach
    public void finish() {
        ResumenDeCuenta resumenDeCuenta = new ResumenDeCuenta(webDriver);
        resumenDeCuenta.close();
        extent.flush();
    }
    
    @Test
    @Name("Proceso de verificar cuentas")
    @Tag("suite-final")
    public void accountsOverview() throws InterruptedException {
        ExtentTest test = extent.createTest("Test Resumen de las cuentas");
        test.log(Status.INFO,"Test comenzado");
        ResumenDeCuenta resumenDeCuenta = new ResumenDeCuenta(webDriver);
        resumenDeCuenta.login();
        test.log(Status.PASS, "Sesión iniciada");
        assertEquals("*Balance includes deposits that may be subject to holds", resumenDeCuenta.getMessageHolds());
        test.log(Status.PASS, "Visualización de resumen de cuentas exitosa");
    }
    
    @Test
    @Name("Proceso de verificar cuentas por mes")
    @Tag("suite-final")
    public void accountOverviewMonth() throws InterruptedException {
        ExtentTest test = extent.createTest("Test Resumen de las cuentas por mes");
        test.log(Status.INFO,"Test comenzado");
        ResumenDeCuenta resumenDeCuenta = new ResumenDeCuenta(webDriver);
        resumenDeCuenta.login();
        test.log(Status.PASS, "Sesión iniciada");
        if (resumenDeCuenta.getMessageHolds().equals("*Balance includes deposits that may be subject to holds")) {
            resumenDeCuenta.clickAccountLink();
            if (resumenDeCuenta.getDetailsTitle().equals("Account Details")) {
                resumenDeCuenta.clickGoButton();
            }
        }
    }
}
