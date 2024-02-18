import io.qameta.allure.Description;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import pages.*;

//ProductsPage (not logged in) --> Login Page --> ProductsPage (logged in) --> Your cart page -->
//--> Your Information Page --> Order Summary --> Order Complete

public class ProductsTests extends BasePage {
    public static final String YOUR_CART = "Your cart";
    public static final String PRODUCTS = "Products";
    private ProductsPage productsPage;

    @BeforeMethod
    public void setUp() {
        super.setUp();
        productsPage = new ProductsPage(driver);
    }

    @Description("Test the main page title")
    @Test
    public void testTitle() {
        Assert.assertEquals(driver.getTitle(), "Demo shop");
    }

   /*

    @Description("Search by a word and check the displayed products")
    @Test(enabled = false)
    public void searchByAWordAndCheckTheResult() {
        String keyword = "soft";
        productsPage.typeInTheTextField(keyword);
        productsPage.clickOnSearchButton();
        Assert.assertTrue(productsPage.checkTheDisplayedProducts(keyword));
    }

    //alternative for searchByAWordAndCheckTheResult using DataProvider:

    @Description("Search by a word and check the displayed products")
    @Test(dataProvider = "KeyProvider", enabled = false)
    public void searchByAWordAndCheckTheResultWithDataProvider(String keyword) {
        productsPage.typeInTheTextField(keyword);
        productsPage.clickOnSearchButton();
        Assert.assertTrue(productsPage.checkTheDisplayedProducts(keyword));
    }

    @DataProvider(name = "KeyProvider")
    public Object[][] getDataFromDataProvider() {
        return new Object[][]
                {
                        {"Alex"},
                        {"Pizza"},
                        {"Soft"}
                };
    }
    */

    //alternative for searchByAWordAndCheckTheResult using @Parameters:

    @Description("Search by a word and check the displayed products")
    @Test
    @Parameters({"keyword"})
    public void searchByAWordAndCheckTheResultWithParametrization(@Optional("Soft") String keyword) {
        productsPage.typeInTheTextField(keyword);
        productsPage.clickOnSearchButton();
        Assert.assertTrue(productsPage.checkTheDisplayedProducts(keyword));
    }

    @Description("Click on wishlist icon to display the wishlist")
    @Test
    public void testWishlistIcon() {
        productsPage.clickWishlistIcon();
        String actualResult = driver.findElement(By.cssSelector(".text-muted")).getText();
        String expected = "Wishlist";
        Assert.assertEquals(actualResult, expected, "Expected " + expected + " but actual was: " + actualResult);
    }

    @Description("Test the cart icon")
    @Test
    public void testCartIcon() {
        productsPage.clickCartIcon();
        String actualResult = driver.findElement(By.cssSelector(".text-muted")).getText();
        Assert.assertEquals(actualResult, YOUR_CART);
    }

    @Description("Add a product in your cart and check the shopping cart counter")
    @Test
    public void testClickCartPlusIconToAddProductToCart() {
        productsPage.clickProductName();
        productsPage.clickOnCartPlusIcon();
        String actualResult = driver.findElement(By.cssSelector(".fa-layers-counter.shopping_cart_badge")).getText();
        Assert.assertTrue(actualResult.contains("1"));
    }

    @Description("Click on heart button and check the wishlist counter")
    @Test
    public void testHeartButton() {
        productsPage.clickHeartButton();
        String actualResult = driver.findElement(By.cssSelector(".fa-layers-counter.shopping_cart_badge")).getText();
        Assert.assertTrue(actualResult.contains("1"));
    }

    @Description("Click on the shopping bag logo and check the displayed products")
    @Test
    public void testShoppingBagLogo() {
        productsPage.clickProductName();
        productsPage.clickShoppingBagLogo();
        Assert.assertEquals(driver.findElement(By.tagName("small")).getText(), PRODUCTS);
    }

    @Description("Click on question button and check the valid usernames and passwords")
    @Test
    public void testQuestionButton() {
        productsPage.clickQuestionButton();
        String actualResult = driver.findElement(By.cssSelector(".modal-content")).getText();
        Assert.assertTrue(actualResult.contains("Valid usernames"));
    }

    @Description("Click on Reset the application state button and check that shopping cart icon is empty")
    @Test(expectedExceptions = NoSuchElementException.class)
    public void testResetTheApplicationStateButton() {
        productsPage.clickProductName();
        productsPage.clickOnCartPlusIcon();
        productsPage.clickResetButton();
        driver.findElement(By.cssSelector(".fa-layers-counter.shopping_cart_badge"));
    }

    @Description("Click on Plus Circle Button and check the increased shopping cart counter")
    @Test
    public void testPlusCircleIcon() {
        productsPage.clickProductName();
        productsPage.clickOnCartPlusIcon();
        productsPage.clickOnCartPlusIcon();
        productsPage.clickCartIcon();
        productsPage.clickPlusCircleIcon();
        productsPage.clickPlusCircleIcon();
        String actualResult = driver.findElement(By.cssSelector(".fa-layers-counter.shopping_cart_badge")).getText();
        Assert.assertTrue(actualResult.contains("4"));
    }

    @Description("Test the heart button when there are no more hearts selected")
    @Test(expectedExceptions = NoSuchElementException.class)
    public void testHeartButtonNoMoreHeartsSelected() {
        productsPage.clickHeartButton();
        String actualResult = driver.findElement(By.cssSelector(".fa-layers-counter.shopping_cart_badge")).getText();
        Assert.assertTrue(actualResult.contains("1"));
        productsPage.clickHeartBrokenButton();
        driver.findElement(By.cssSelector(".fa-layers-counter.shopping_cart_badge")).getText();
    }

    @Description("Test the get product page")
    @Test
    public void testGetProductPage() {
        WebElement productElement = driver.findElement(By.cssSelector("a[href='#/product/3']"));
        String productName = productElement.getText();
        productElement.click();
        Assert.assertEquals(driver.findElement(By.tagName("small")).getText(), productName);
    }

    @Description("Test the complete order flow")
    @Test
    public void testCompleteYourOrder() {
        productsPage.clickProductName();
        productsPage.clickOnCartPlusIcon();
        productsPage.clickCartIcon();
        YourCartPage yourCartPage = new YourCartPage(driver);
        yourCartPage.clickCheckoutButton();
        YourInformationPage yourInformationPage = new YourInformationPage(driver);
        yourInformationPage.typeFirstName("Fred");
        yourInformationPage.typeLastName("Flintstone");
        yourInformationPage.typeAddress("Rocky Drive 32");
        yourInformationPage.clickContinueCheckoutButton();
        OrderSummaryPage orderSummaryPage = new OrderSummaryPage(driver);
        orderSummaryPage.clickCompleteOrderButton();
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[1]")).getText(), "Thank you for your order!");
    }

    @Description("Test the cancel button from the order summary page")
    @Test
    public void testCancelBeforeCompleteYourOrder() {
        productsPage.clickProductName();
        productsPage.clickOnCartPlusIcon();
        productsPage.clickCartIcon();
        YourCartPage yourCartPage = new YourCartPage(driver);
        yourCartPage.clickCheckoutButton();
        YourInformationPage yourInformationPage = new YourInformationPage(driver);
        yourInformationPage.typeFirstName("Fred");
        yourInformationPage.typeLastName("Flintstone");
        yourInformationPage.typeAddress("Rocky Drive 32");
        yourInformationPage.clickContinueCheckoutButton();
        OrderSummaryPage orderSummaryPage = new OrderSummaryPage(driver);
        orderSummaryPage.clickCancelButton();
        Assert.assertEquals(driver.findElement(By.tagName("small")).getText(), YOUR_CART);
    }

    @Description("Test the cancel button from the your information page")
    @Test
    public void testCancelOnYourInformationPage() {
        productsPage.clickProductName();
        productsPage.clickOnCartPlusIcon();
        productsPage.clickCartIcon();
        YourCartPage yourCartPage = new YourCartPage(driver);
        yourCartPage.clickCheckoutButton();
        YourInformationPage yourInformationPage = new YourInformationPage(driver);
        yourInformationPage.clickCancelButton();
        Assert.assertEquals(driver.findElement(By.tagName("small")).getText(), YOUR_CART);
    }

    @Description("Test the continue shopping button from the your cart page")
    @Test
    public void testCancelOnYourCartPage() {
        productsPage.clickProductName();
        productsPage.clickOnCartPlusIcon();
        productsPage.clickCartIcon();
        YourCartPage yourCartPage = new YourCartPage(driver);
        yourCartPage.clickContinueShoppingButton();
        Assert.assertEquals(driver.findElement(By.tagName("small")).getText(), PRODUCTS);
    }

    @Description("Test continue checkout button on your information page when address is missing")
    @Test
    public void testYourInformationPageWhenAddressMissing() {
        productsPage.clickProductName();
        productsPage.clickOnCartPlusIcon();
        productsPage.clickCartIcon();
        YourCartPage yourCartPage = new YourCartPage(driver);
        yourCartPage.clickCheckoutButton();
        YourInformationPage yourInformationPage = new YourInformationPage(driver);
        yourInformationPage.typeFirstName("Fred");
        yourInformationPage.typeLastName("Flintstone");
        yourInformationPage.clickContinueCheckoutButton();
        Assert.assertEquals(driver.findElement(By.cssSelector(".error")).getText(), "Address is required");
    }

    @Description("Test continue checkout button on your information page when first name is missing")
    @Test
    public void testYourInformationPageFirstNameMissing() {
        productsPage.clickProductName();
        productsPage.clickOnCartPlusIcon();
        productsPage.clickCartIcon();
        YourCartPage yourCartPage = new YourCartPage(driver);
        yourCartPage.clickCheckoutButton();
        YourInformationPage yourInformationPage = new YourInformationPage(driver);
        yourInformationPage.typeAddress("Fred");
        yourInformationPage.typeLastName("Flintstone");
        yourInformationPage.clickContinueCheckoutButton();
        Assert.assertEquals(driver.findElement(By.cssSelector(".error")).getText(), "First Name is required");
    }

    @Description("Test continue checkout button on your information page when last name is missing")
    @Test
    public void testYourInformationPageLastNameMissing() {
        productsPage.clickProductName();
        productsPage.clickOnCartPlusIcon();
        productsPage.clickCartIcon();
        YourCartPage yourCartPage = new YourCartPage(driver);
        yourCartPage.clickCheckoutButton();
        YourInformationPage yourInformationPage = new YourInformationPage(driver);
        yourInformationPage.typeFirstName("Fred");
        yourInformationPage.typeAddress("Flintstone");
        yourInformationPage.clickContinueCheckoutButton();
        Assert.assertEquals(driver.findElement(By.cssSelector(".error")).getText(), "Last Name is required");
    }

    @Description("Test the complete order flow and click on the continue shopping button")
    @Test
    public void testCompleteYourOrderAndContinueShopping() {
        productsPage.clickProductName();
        productsPage.clickOnCartPlusIcon();
        productsPage.clickCartIcon();
        YourCartPage yourCartPage = new YourCartPage(driver);
        yourCartPage.clickCheckoutButton();
        YourInformationPage yourInformationPage = new YourInformationPage(driver);
        yourInformationPage.typeFirstName("Fred");
        yourInformationPage.typeLastName("Flintstone");
        yourInformationPage.typeAddress("Rocky Drive 32");
        yourInformationPage.clickContinueCheckoutButton();
        OrderSummaryPage orderSummaryPage = new OrderSummaryPage(driver);
        orderSummaryPage.clickCompleteOrderButton();
        OrderCompletePage orderCompletePage = new OrderCompletePage(driver);
        orderCompletePage.clickContinueShoppingButton();
        Assert.assertEquals(driver.findElement(By.tagName("small")).getText(), PRODUCTS);
    }

    @Description("Test remove the product from cart to have it empty")
    @Test
    public void testAddToCartAndRemoveFromCart() {
        productsPage.clickProductName();
        productsPage.clickOnCartPlusIcon();
        productsPage.clickCartIcon();
        YourCartPage yourCartPage = new YourCartPage(driver);
        yourCartPage.clickDeleteProductButton();
        Assert.assertEquals(driver.findElement(By.cssSelector(".text-center.container")).getText(), "How about adding some products in your cart?");
    }

    }