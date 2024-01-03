package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BasePage {
    public static WebDriver driver;
    private String BASE_URL = "https://fasttrackit-test.netlify.app/#/";

    public BasePage() {
        // Default constructor
    }

    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
    }

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        driver.manage().window().maximize();
        driver.get(BASE_URL);                     //driver.navigate().to(BASE_URL);
        /*
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver(options);
        FirefoxOptions options = new FirefoxOptions();
        */
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}