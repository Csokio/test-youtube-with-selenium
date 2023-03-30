import com.codecool.Search;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class SearchTest {

    WebDriver driver;

    String searchText = "Linz";

    @BeforeEach
    public void init() {
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
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }


    @Test
    public void searchVideoWithWrittenSearchCriteriaTest(){


        Search search = new Search(driver);
        search.navigate();
        search.searchVideoWithWrittenSearchCriteria(searchText);

        String expectedResult = searchText;
        String actualResult = "";       //TODO
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void searchVideoWithSearchHistoryTest(){
        Search search = new Search(driver);
        search.navigate();
        search.searchVideoWithSearchHistory();

        String expectedResult = "";     //TODO
        String actualResult = "";       //TODO
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void removeItemFromSearchHistoryTest(){
        Search search = new Search(driver);
        search.navigate();
        search.removeItemFromSearchHistory();

        String expectedResult = "";     //TODO
        String actualResult = "";       //TODO
        Assertions.assertEquals(expectedResult, actualResult);

    }



    @AfterEach
    public void quitDriver(){
        driver.quit();
    }




}
