package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage extends BasePage {
    public SearchPage(WebDriver webDriver) {
        super(webDriver);
    }
    private By searchInput = By.xpath("//*[@id=\"search\"]/input");
    private By searchButton = By.xpath("//*[@id=\"search\"]/span/button");
    private By addToCartButton = By.xpath("//*[@id=\"content\"]/div[3]/div/div/div[2]/div[2]/button[1]");
    private By confirmMessage = By.xpath("//*[@id=\"product-search\"]/div[1]");
    public void typeSearch(String text) throws InterruptedException {
        this.sendText(text,searchInput);
    }
    public void clickSearch() throws InterruptedException {
        this.click(searchButton);
        Thread.sleep(2000);
    }
    public void clickAddToCart() throws InterruptedException {
        this.click(addToCartButton);
        Thread.sleep(2000);
    }
    public boolean getConfirmMessage() throws InterruptedException {
        return this.findElement(confirmMessage).isDisplayed();
    }
}
