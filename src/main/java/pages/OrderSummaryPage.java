package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderSummaryPage extends BasePage {

    public OrderSummaryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "a[href='#/cart']")
    private WebElement cancelButton;

    @FindBy(css = "a[href='#/checkout-complete']")
    private WebElement completeOrderButton;

    public void clickCancelButton() {
        cancelButton.click();
    }

    public void clickCompleteOrderButton() {
        completeOrderButton.click();
    }
}
