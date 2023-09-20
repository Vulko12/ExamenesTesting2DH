package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PaginaBase {
    
    public WebDriver webDriver;
    public WebDriverWait webDriverWait;
    
    public PaginaBase(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, Duration.ofMillis(2000));
    }
    
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver/chromedriver.exe");
        webDriver.manage().window().maximize();
    }
    
    public void url(String url) throws InterruptedException {
        webDriver.get(url);
        Thread.sleep(2000);
    }
    
    public void close() {
        webDriver.quit();
    }

    public String getUrl() {
        return webDriver.getCurrentUrl();
    }
    
    public WebElement findElement(By locator) {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return webDriver.findElement(locator);
    }
    
    public void sendText(String text, By locator) {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
        this.findElement(locator).clear();
        this.findElement(locator).sendKeys(text);
    }
    
    public void click(By locator) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(locator));
        this.findElement(locator).click();
    }
    
    public String getText(By locator) {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return this.findElement(locator).getText();
    }
    
}
