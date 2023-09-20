package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaginaTransferencias extends PaginaPrincipal {
    public PaginaTransferencias(WebDriver webDriver) {
        super(webDriver);
    }
    private final By transferFundsLink = By.xpath("//*[@id=\"leftPanel\"]/ul/li[3]/a");
    private final By amountInput = By.xpath("//*[@id=\"amount\"]");
    private final By transferButton = By.xpath("//*[@id=\"rightPanel\"]/div/div/form/div[2]/input");
    private final By confirmMessage = By.xpath("//*[@id=\"rightPanel\"]/div/div/h1");
    public void clickTransferFundsLink() throws InterruptedException {
        this.click(transferFundsLink);
        Thread.sleep(2000);
    }
    public void typeAmount(String amount) {
        this.sendText(amount,amountInput);
    }
    public void clickTransferButton() throws InterruptedException {
        this.click(transferButton);
        Thread.sleep(2000);
    }
    public String getConfirmMessage() {
        return this.getText(confirmMessage);
    }
}
