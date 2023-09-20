package Test;

import Pages.AbrirCuenta;
import Report.ExtentFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import static org.junit.jupiter.api.Assertions.*;

import jdk.jfr.Name;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestAbrirCuenta {
    
    public WebDriver webDriver;
    public WebDriverWait webDriverWait;
    ExtentSparkReporter info = new ExtentSparkReporter("target/REPORTE_OPEN_ACCOUNT.html");
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
        AbrirCuenta abrirCuenta = new AbrirCuenta(webDriver);
        abrirCuenta.setup();
        abrirCuenta.url("https://parabank.parasoft.com/parabank/index.htm");
    }
    
    @AfterEach
    public void finish() {
        AbrirCuenta abrirCuenta = new AbrirCuenta(webDriver);
        abrirCuenta.close();
        extent.flush();
    }
    
    @Test
    @Name("Proceso de abrir una cuenta")
    @Tag("suite-final")
    public void openAccount() throws InterruptedException {
        ExtentTest test = extent.createTest("Test abrir cuenta");
        test.log(Status.INFO, "Test comenzado");
        AbrirCuenta abrirCuenta = new AbrirCuenta(webDriver);
        abrirCuenta.login();
        test.log(Status.PASS, "Sesión iniciada");
        abrirCuenta.clickOpenAccountLink();
        abrirCuenta.clickAccountTypeSelect();
        abrirCuenta.clickSavingsOption();
        abrirCuenta.clickOpenAccountButton();
        assertEquals("Congratulations, your account is now open.", abrirCuenta.getConfirmMessage());
        test.log(Status.PASS, "Apertura de cuenta exitosa");
    }
    
}
