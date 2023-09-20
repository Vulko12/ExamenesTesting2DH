package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaginaPrincipal extends PaginaBase {
    public PaginaPrincipal(WebDriver webDriver) {
        super(webDriver);
    }
    private final By usernameInput = By.xpath("//*[@id=\"loginPanel\"]/form/div[1]/input");
    private final By passwordInput = By.xpath("//*[@id=\"loginPanel\"]/form/div[2]/input");
    private final By loginButton = By.xpath("//*[@id=\"loginPanel\"]/form/div[3]/input");
    public void login() throws InterruptedException {
        this.sendText("",usernameInput);
        this.sendText("",passwordInput);
        this.click(loginButton);
        Thread.sleep(3000);
    }
}
