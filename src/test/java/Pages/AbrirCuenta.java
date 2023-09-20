package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AbrirCuenta extends PaginaPrincipal {
    public AbrirCuenta(WebDriver webDriver) {
        super(webDriver);
    }
    
    private final By openAccountLink = By.xpath("//*[@id=\"leftPanel\"]/ul/li[1]/a");
    private final By accountTypeSelect = By.id("type");
    private final By savingsOption = By.xpath("//*[@id=\"type\"]/option[2]");
    private final By openAccountButton = By.xpath("//*[@id=\"rightPanel\"]/div/div/form/div/input");
    private final By confirmMessage = By.xpath("//*[@id=\"rightPanel\"]/div/div/p[1]");
    
    public void clickOpenAccountLink() {
        this.click(openAccountLink);
    }
    public void clickAccountTypeSelect() {
        this.click(accountTypeSelect);
    }
    public void clickSavingsOption() {
        this.click(savingsOption);
    }
    public void clickOpenAccountButton() throws InterruptedException {
        this.click(openAccountButton);
        this.click(openAccountButton);
        Thread.sleep(2000);
    }
    public String getConfirmMessage() {
        return this.getText(confirmMessage);
    }
    
}
