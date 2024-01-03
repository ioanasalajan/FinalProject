package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Objects;

public class ProductsPage extends BasePage {

    public ProductsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "a[href='#/wishlist']")
    private WebElement wishlistIcon;

    @FindBy(css = ".svg-inline--fa.fa-shopping-cart.fa-w-18")
    private WebElement cartIcon;

    @FindBy(css = ".card-link")
    private WebElement productName;

    @FindBy(css = ".svg-inline--fa.fa-cart-plus.fa-w-18.fa-3x")
    private WebElement cartPlusIcon;

    @FindBy(css = ".svg-inline--fa.fa-heart.fa-w-16.fa-2x")
    private WebElement heartButton;

    @FindBy(css = ".svg-inline--fa.fa-heart-broken.fa-w-16.fa-2x")
    private WebElement heartBrokenButton;

    @FindBy(css = ".svg-inline--fa.fa-shopping-bag.fa-w-14.fa-3x.brand-logo")
    private WebElement shoppingBagLogo;

    @FindBy(css = ".svg-inline--fa.fa-question.fa-w-12")
    private WebElement questionButton;

    @FindBy (css=".svg-inline--fa.fa-undo.fa-w-16")
    private WebElement resetButton;

    @FindBy (css=".svg-inline--fa.fa-plus-circle.fa-w-16")
    private WebElement plusCircleIcon;

    @FindBy(id = "input-search")
    private WebElement textField;

    @FindBy(css = "a[href='#/account']")
    private WebElement accountLink;

    @FindBy(xpath = "//*[name()='svg'][@data-icon='sign-out-alt']/parent::button")
    private WebElement logoutButton;

    public void typeInTheTextField(String text) {
        textField.sendKeys(text);
    }

    @FindBy(xpath = "/html/body/div/div/div[2]/div[2]/div[1]/form/div[1]/button")
    private WebElement searchButton;

    public void clickOnSearchButton() {
        searchButton.click();
    }

    //@FindBy (xpath = "/html/body/div/div/div[2]/div[2]/div[2]/div[3]")
    @FindBy(css = ".card-link")
    private List<WebElement> displayedProducts;

    public void clickWishlistIcon() {
        wishlistIcon.click();
    }

    public void clickCartIcon() {
        cartIcon.click();
    }

    public void clickProductName() {
        productName.click();
    }

    public void clickOnCartPlusIcon(){
        cartPlusIcon.click();
    }

    public void clickHeartButton(){
        heartButton.click();
    }

    public void clickHeartBrokenButton(){
        heartBrokenButton.click();
    }

    public void clickShoppingBagLogo(){
        shoppingBagLogo.click();
    }

    public void clickQuestionButton(){
        questionButton.click();
    }

    public void clickResetButton(){
        resetButton.click();
    }

    public void clickPlusCircleIcon(){
        plusCircleIcon.click();

    }

    public void clickAccountLink() {
        accountLink.click();
    }

    public void clickLogoutButton() {
        logoutButton.click();
    }

    public Boolean checkTheDisplayedProducts(String textToFind) {
        for (WebElement webElement : displayedProducts) {
            if (Objects.nonNull(webElement.getText())) {
                System.out.println(webElement.getText());
                if (!webElement.getText().toLowerCase().contains(textToFind.toLowerCase())) {
                    return false;
                }
            }
        }
        return true;
    }
}
