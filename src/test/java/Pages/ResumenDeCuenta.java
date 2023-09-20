package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResumenDeCuenta extends PaginaPrincipal {
    public ResumenDeCuenta(WebDriver webDriver) {
        super(webDriver);
    }
    private final By messageHolds = By.xpath("//*[@id=\"accountTable\"]/tfoot/tr/td");
    private final By accountLink = By.xpath("//*[@id=\"accountTable\"]/tbody/tr[1]/td[1]/a");
    private final By detailsTitle = By.xpath("//*[@id=\"rightPanel\"]/div/div[1]/h1");
    private final By goButton = By.xpath("//*[@id=\"rightPanel\"]/div/div[2]/form/table/tbody/tr[3]/td[2]/input");
    public String getMessageHolds() {
        return this.getText(messageHolds);
    }
    public void clickAccountLink() {
        this.click(accountLink);
    }
    public String getDetailsTitle() {
        return this.getText(detailsTitle);
    }
    public void clickGoButton() {
        this.click(goButton);
    }
}
