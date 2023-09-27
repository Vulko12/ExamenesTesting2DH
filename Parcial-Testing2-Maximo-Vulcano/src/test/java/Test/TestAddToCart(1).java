package Test;

import static org.junit.jupiter.api.Assertions.*;
import Pages.SearchPage;
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

public class TestAddToCart {
    public WebDriver webDriver;
    public WebDriverWait webDriverWait;
    ExtentSparkReporter info = new ExtentSparkReporter("target/REPORTE_SEARCH_ADD.html");
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
        SearchPage searchPage = new SearchPage(webDriver);
        searchPage.setup();
        searchPage.url("https://opencart.abstracta.us");
    }
    
    @AfterEach
    public void finish() throws InterruptedException {
        SearchPage searchPage = new SearchPage(webDriver);
        searchPage.close();
        extent.flush();
    }
    
    @Test
    @Name("Búsqueda y Añadido de producto al carrito")
    @Tag("suite-parcial")
    public void searchAndAdd() throws InterruptedException {
    
        ExtentTest test = extent.createTest("Test Búsqueda y Añadido de producto al carrito");
        test.log(Status.INFO, "Test comenzado");
        
        SearchPage searchPage = new SearchPage(webDriver);
        searchPage.typeSearch("iphone");
        searchPage.clickSearch();
        test.log(Status.PASS, "Búsqueda realizada");
        searchPage.clickAddToCart();
        assertTrue(searchPage.getConfirmMessage());
        test.log(Status.PASS, "Producto añadido al carrito");
    }
}
