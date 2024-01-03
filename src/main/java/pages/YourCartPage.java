package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YourCartPage extends BasePage {

    public YourCartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "a[href='#/products']")
    private WebElement continueShoppingButton;

    @FindBy(css = ".btn.btn-success")
    private WebElement checkoutButton;

    @FindBy(xpath = "//*[name()='svg'][@data-icon='trash']/parent::button")
    private WebElement deleteProductButton;

    public void clickContinueShoppingButton() {
        continueShoppingButton.click();
    }

    public void clickCheckoutButton(){
        checkoutButton.click();
    }

    public void clickDeleteProductButton(){
        deleteProductButton.click();
    }

}
