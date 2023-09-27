package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {
    public RegisterPage(WebDriver webDriver) {
        super(webDriver);
    }
    private By firstNameInput = By.id("input-firstname");
    private By lastNameInput = By.id("input-lastname");
    private By emailInput = By.id("input-email");
    private By telephoneInput = By.id("input-telephone");
    private By passwordInput = By.id("input-password");
    private By passwordConfirmInput = By.id("input-confirm");
    private By privacyPolicyCheckbox = By.xpath("//*[@id=\"content\"]/form/div/div/input[1]");
    private By myAccountButton = By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a");
    private By enterRegisterPageButton = By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/li[1]/a");
    private By registerButton = By.xpath("//*[@id=\"content\"]/form/div/div/input[2]");
    public void clickEnterRegisterPageButton() throws InterruptedException {
        this.click(myAccountButton);
        this.click(enterRegisterPageButton);
        Thread.sleep(2000);
    }
    public void typeFirstName(String name) throws InterruptedException {
        this.sendText(name, firstNameInput);
    }
    public void typeLastName(String lastName) throws InterruptedException {
        this.sendText(lastName, lastNameInput);
    }
    public void typeEmail(String email) throws InterruptedException {
        this.sendText(email, emailInput);
    }
    public void typeTelephone(String telephone) throws InterruptedException {
        this.sendText(telephone, telephoneInput);
    }
    public void typePassword(String password) throws InterruptedException {
        this.sendText(password, passwordInput);
    }
    public void typePasswordConfirm(String passwordConfirm) throws InterruptedException {
        this.sendText(passwordConfirm, passwordConfirmInput);
    }
    public void clickPrivacyPolicyCheckbox() throws InterruptedException {
        this.click(privacyPolicyCheckbox);
    }
    public void clickRegisterButton() throws InterruptedException {
        this.click(registerButton);
        Thread.sleep(2000);
    }
}
