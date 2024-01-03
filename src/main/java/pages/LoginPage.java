package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[name()='svg'][@data-icon='sign-in-alt']/parent::button")
    private WebElement getLoginPageButton;

    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "/html/body/div[3]/div/div/div[2]/div/form/button")
    private WebElement loginButton;

    public void clickLoginPageButton() {
        getLoginPageButton.click();
    }

    public void typeUsername(String text) {
        usernameInput.sendKeys(text);
    }

    public void typePassword(String text) {
        passwordInput.sendKeys(text);
    }

    public void clickLoginButton() {
        loginButton.click();
    }


}