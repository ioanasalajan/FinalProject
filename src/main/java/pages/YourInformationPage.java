package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YourInformationPage extends BasePage {

    public YourInformationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "first-name")
    private WebElement firstNameInput;

    @FindBy(id = "last-name")
    private WebElement lastNameInput;

    @FindBy(id = "address")
    private WebElement addressTextarea;

    @FindBy(css = ".btn.btn-success")
    private WebElement continueCheckoutButton;

    @FindBy(css = "a[href='#/cart']")
    private WebElement cancelButton;


    public void typeFirstName(String text) {
        firstNameInput.sendKeys(text);
    }

    public void typeLastName(String text) {
        lastNameInput.sendKeys(text);
    }

    public void typeAddress(String text) {
        addressTextarea.sendKeys(text);
    }

    public void clickCancelButton() {
        cancelButton.click();
    }

    public void clickContinueCheckoutButton() {
        continueCheckoutButton.click();
    }
}
