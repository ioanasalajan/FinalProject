import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.LoginPage;
import pages.ProductsPage;

public class LoginTests extends BasePage {
    public static final String DINO = "dino";
    public static final String CHOOCHOO = "choochoo";
    public static final String ACCOUNT = "Account";
    private LoginPage myLoginPage;

    @BeforeMethod
    public void setUp() {
        super.setUp();
        myLoginPage = new LoginPage(driver);
    }

    @Description("Input username and password for normal user")
    @Test
    public void testSuccessfulLogin() {
        myLoginPage.clickLoginPageButton();
        myLoginPage.typeUsername(DINO);
        myLoginPage.typePassword(CHOOCHOO);
        myLoginPage.clickLoginButton();
        String actualResult = driver.findElement(By.xpath("/html/body/div/div/div[1]/nav/div/div[2]/span/span/span/a")).getText();
        Assert.assertEquals(actualResult, DINO);
    }

    @Description("Input username and password for normal user and check the account link")
    @Test
    public void testSuccessfulLoginAccountLink() {
        myLoginPage.clickLoginPageButton();
        myLoginPage.typeUsername(DINO);
        myLoginPage.typePassword(CHOOCHOO);
        myLoginPage.clickLoginButton();
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.clickAccountLink();
        Assert.assertEquals(driver.findElement(By.tagName("small")).getText(), ACCOUNT);
    }

    @Description("Input username and password for normal user and check logout button")
    @Test
    public void testSuccessfulLoginThenLogout() {
        myLoginPage.clickLoginPageButton();
        myLoginPage.typeUsername(DINO);
        myLoginPage.typePassword(CHOOCHOO);
        myLoginPage.clickLoginButton();
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.clickLogoutButton();
        Assert.assertTrue(driver.getPageSource().contains("Hello guest!"));
    }

    @Description("Input username and password for locked out user")
    @Test
    public void testLockedOutUserLogin() {
        myLoginPage.clickLoginPageButton();
        myLoginPage.typeUsername("locked");
        myLoginPage.typePassword(CHOOCHOO);
        myLoginPage.clickLoginButton();
        String actualResult = driver.findElement(By.cssSelector(".error")).getText();
        Assert.assertEquals(actualResult, "The user has been locked out.");
    }

    @Description("Input username and password for a non-existent user")
    @Test
    public void testIncorrectUserLogin() {
        myLoginPage.clickLoginPageButton();
        myLoginPage.typeUsername("ioana");
        myLoginPage.typePassword(CHOOCHOO);
        myLoginPage.clickLoginButton();
        String actualResult = driver.findElement(By.cssSelector(".error")).getText();
        Assert.assertEquals(actualResult, "Incorrect username or password!");
    }

    @Description("Test login without providing a password")
    @Test
    public void testLoginWithNoPassword() {
        myLoginPage.clickLoginPageButton();
        myLoginPage.typeUsername("ioana");
        myLoginPage.typePassword("");
        myLoginPage.clickLoginButton();
        String actualResult = driver.findElement(By.cssSelector(".error")).getText();
        Assert.assertEquals(actualResult, "Please fill in the password!");
    }

    @Description("Test login without providing a username")
    @Test
    public void testLoginWithNoUsername() {
        myLoginPage.clickLoginPageButton();
        myLoginPage.typeUsername("");
        myLoginPage.typePassword(CHOOCHOO);
        myLoginPage.clickLoginButton();
        String actualResult = driver.findElement(By.cssSelector(".error")).getText();
        Assert.assertEquals(actualResult, "Please fill in the username!");
    }

    @Description("Test the position of the username input relative to other elements")
    @Test
    public void testUsernameWithRelativeLocators() {
        myLoginPage.clickLoginPageButton();
        WebElement inputElemAbovePassword = driver.findElement(RelativeLocator.with(By.tagName("input")).above(By.id("password")));
        Assert.assertEquals(inputElemAbovePassword.getDomProperty("placeholder"), "Username");
    }

    @Description("Test the position of the password input relative to other elements")
    @Test
    public void testPasswordWithRelativeLocators() {
        myLoginPage.clickLoginPageButton();
        WebElement inputElemBelowUsername = driver.findElement(RelativeLocator.with(By.tagName("input")).below(By.id("user-name")));
        Assert.assertEquals(inputElemBelowUsername.getDomProperty("placeholder"), "Password");
    }

    @Description("Test the position of the login button relative to other elements")
    @Test
    public void testLoginButtonWithRelativeLocators() {
        myLoginPage.clickLoginPageButton();
        WebElement buttonBelowPassword = driver.findElement(RelativeLocator.with(By.tagName("button")).below(By.id("password")));
        Assert.assertEquals(buttonBelowPassword.getText(), "Login");
    }
}