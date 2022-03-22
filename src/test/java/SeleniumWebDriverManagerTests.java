import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

public class SeleniumWebDriverManagerTests {

    @Test
    public void openPageWithParameterTest () throws InterruptedException {
        WebDriver driver= null;

        String browser = System.getProperty("browser");

        if(browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equals("opera")){
            WebDriverManager.operadriver().setup();
            driver = new OperaDriver();
        }

        driver.get("https://demoqa.com/automation-practice-form");
        driver.manage().window().maximize();
        long millis;

        Thread.sleep(2000);

        driver.quit();
    }
}
