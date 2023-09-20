package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaginaRegistro extends PaginaBase {
    public PaginaRegistro(WebDriver webDriver) {
        super(webDriver);
    }
    private final By firstNameInput = By.id("customer.firstName");
    private final By lastNameInput = By.id("customer.lastName");
    private final By addressInput = By.id("customer.address.street");
    private final By cityInput = By.id("customer.address.city");
    private final By stateInput = By.id("customer.address.state");
    private final By zipCodeInput = By.id("customer.address.zipCode");
    private final By telephoneInput = By.id("customer.phoneNumber");
    private final By ssnInput = By.id("customer.ssn");
    private final By usernameInput = By.id("customer.username");
    private final By passwordInput = By.id("customer.password");
    private final By passwordConfirmInput = By.id("repeatedPassword");
    private final By enterRegisterPageButton = By.xpath("//*[@id=\"loginPanel\"]/p[2]/a");
    private final By registerButton = By.xpath("//*[@id=\"customerForm\"]/table/tbody/tr[13]/td[2]/input");
    private final By confirmMessage = By.xpath("//*[@id=\"rightPanel\"]/p");
    public void clickEnterRegisterPageButton() throws InterruptedException {
        this.click(enterRegisterPageButton);
        Thread.sleep(2000);
    }
    public void typeFirstName(String name) {
        this.sendText(name, firstNameInput);
    }
    public void typeLastName(String lastName) {
        this.sendText(lastName, lastNameInput);
    }
    public void typeAddress(String address) {
        this.sendText(address,addressInput);
    }
    public void typeCity(String city) {
        this.sendText(city,cityInput);
    }
    public void typeState(String state) {
        this.sendText(state,stateInput);
    }
    public void typeZipCode(String zipCode) {
        this.sendText(zipCode,zipCodeInput);
    }
    public void typeTelephone(String telephone) {
        this.sendText(telephone, telephoneInput);
    }
    public void typeSsn(String ssn) {
        this.sendText(ssn,ssnInput);
    }
    public void typeUsername(String username) {
        this.sendText(username,usernameInput);
    }
    public void typePassword(String password) {
        this.sendText(password, passwordInput);
    }
    public void typePasswordConfirm(String passwordConfirm) {
        this.sendText(passwordConfirm, passwordConfirmInput);
    }
    public void clickRegisterButton() throws InterruptedException {
        this.click(registerButton);
        Thread.sleep(2000);
    }
    public String getConfirmMessage() {
        return this.getText(confirmMessage);
    }
    
}
