import com.codecool.ProfileSettings;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class IndexPageTest {

    WebDriver driver;

    @BeforeEach
    public void init()
    {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("ignore-certificate-errors");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        //options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }


    @Test
    public void testLogIn() throws InterruptedException {
        ProfileSettings profileSettings = new ProfileSettings(driver);
        profileSettings.navigate();
        if(driver.findElement(By.xpath("//ytd-button-renderer[@class='signin style-scope ytd-consent-bump-v2-lightbox']")).isDisplayed()){
            profileSettings.clickOnLogInYouTubePopUp();
        }   else {
            profileSettings.clickLogInYouTubeWithoutPopUp();
        }
        profileSettings.addEmail("automationtester00666@gmail.com");
        profileSettings.clickONEmailButton();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@id='password']//input[@data-initial-value='']"))));
        profileSettings.addPassword("Fecske111!");
        profileSettings.clickLogIn();
        Thread.sleep(3000);



    }

    @Test
    public void testLogOut() throws InterruptedException {
        ProfileSettings profileSettings = new ProfileSettings(driver);
        profileSettings.clickProfileButton();
        Thread.sleep(1000);
        profileSettings.logOutYouTube();
        WebDriverWait wait = new WebDriverWait(driver, 20);

        wait.until(ExpectedConditions.urlMatches("https://www.youtube.com/"));

        String urlExpected = "https://www.youtube.com/";
        Assertions.assertEquals(urlExpected, driver.getCurrentUrl());
    }
    @Test
    public void testChangeSettings() throws InterruptedException {

        testLogIn();

        ProfileSettings profileSettings = new ProfileSettings(driver);
        profileSettings.scrollDown();
        Thread.sleep(4000);
        profileSettings.clickSettings();
        Thread.sleep(2000);
        profileSettings.clickNotification();
        Thread.sleep(4000);
        profileSettings.clickToggle();
        Thread.sleep(2000);
        profileSettings.diSelectToggle();
        Thread.sleep(2000);

        testLogOut();
    }

}
