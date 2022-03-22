import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class HerokuTests {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        String browser = System.getProperty("browser");

        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        driver.get("https://demoqa.com/automation-practice-form");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS );
    }

    @Test
    public void checkCurrentUrl() throws InterruptedException {
        Assertions.assertEquals("https://demoqa.com/automation-practice-form", driver.getCurrentUrl());
        Thread.sleep(2000);

    }

    @Test
    public void checkTitle() {
        Assertions.assertEquals("The Internet", driver.getTitle());
    }

    @Test
    public void authenticationTest() throws InterruptedException {
        String firstName = "Tom";
        String lastName = "Brown";
        String email = "TomBrown@google.com";
        String number = "012345678";
        String dateOfBirth = "13 Jun 1989";
        String address = "New York, Mainlane Street, 2";


        driver.findElement(By.id("firstName")).sendKeys(firstName);
        driver.findElement(By.id("lastName")).sendKeys(lastName);
        driver.findElement(By.id("userEmail")).sendKeys(email);
        WebElement testingRadioButton = driver.findElement(By .xpath("//*[@id=\"genterWrapper\"]/div[2]/div[1]/label"));
        testingRadioButton.click();
        driver.findElement(By .id("userNumber")).sendKeys(number);
        driver.findElement(By .id("dateOfBirthInput")).sendKeys(dateOfBirth);
        WebElement autocomplete = driver.findElement(By .id("subjectsContainer"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();",autocomplete);
        autocomplete.sendKeys("Arts");
        autocomplete.sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("//*[@id=\"hobbiesWrapper\"]/div[2]/div[2]/label")).click();
        driver.findElement(By.id("currentAddress")).sendKeys(address);
        WebElement elementDropdown = driver.findElement(By .id("state"));
        Select select = new Select(elementDropdown);
        select.selectByVisibleText("Haryana");
        Assertions.assertEquals("Haryana", elementDropdown.getAttribute("value"));
        WebElement elementDropdown1 = driver.findElement(By .id("city"));
        Select select1 = new Select(elementDropdown1);
        select1.selectByVisibleText("Karnal");
        driver.findElement(By .id("submit")).click();
        WebElement successfulLoginMessageLabel = driver.findElement(By .id("example-modal-sizes-title-lg"));
        String successfulLoginMessage = successfulLoginMessageLabel.getText();









        Thread.sleep(3000);
        Assertions.assertTrue(successfulLoginMessageLabel.isDisplayed());
        Assertions.assertTrue(successfulLoginMessage.contains("Thanks for submitting the form"));

    }


        @AfterEach
        public void tearDown() {
            driver.quit();
        }
}

