package Test;

import Pages.PaginaTransferencias;
import Report.ExtentFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTransferencias {
    public WebDriver webDriver;
    public WebDriverWait webDriverWait;
    ExtentSparkReporter info = new ExtentSparkReporter("target/REPORTE_TRANSFER_FUNDS.html");
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
        PaginaTransferencias paginaTransferencias = new PaginaTransferencias(webDriver);
        paginaTransferencias.setup();
        paginaTransferencias.url("https://parabank.parasoft.com/parabank/index.htm");
    }
    
    @AfterEach
    public void finish() {
        PaginaTransferencias paginaTransferencias = new PaginaTransferencias(webDriver);
        paginaTransferencias.close();
        extent.flush();
    }
    
    @Test
    @Name("Proceso de transferencia de fondos")
    @Tag("suite-final")
    public void transferFunds() throws InterruptedException {
        ExtentTest test = extent.createTest("Test transferencia de fondos");
        test.log(Status.INFO, "Test comenzado");
        PaginaTransferencias paginaTransferencias = new PaginaTransferencias(webDriver);
        paginaTransferencias.login();
        test.log(Status.PASS, "Sesión iniciada");
        paginaTransferencias.clickTransferFundsLink();
        paginaTransferencias.typeAmount("1000");
        paginaTransferencias.clickTransferButton();
        assertEquals("Transfer Complete!", paginaTransferencias.getConfirmMessage());
        test.log(Status.PASS, "Transferencia de fondos exitosa");
    }
}
