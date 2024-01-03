package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderCompletePage extends BasePage {

    public OrderCompletePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "a[href='#/products']")
    private WebElement continueShoppingButton;

    public void clickContinueShoppingButton() {
        continueShoppingButton.click();
    }
}
