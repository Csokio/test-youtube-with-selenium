import com.codecool.Comment;
import com.codecool.ProfileSettings;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommentTest {

    WebDriver driver;

    String commentText = "wow";
    String newComment = "wow!!";

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
    public void testLogIn() throws InterruptedException {
        ProfileSettings profileSettings = new ProfileSettings(driver);
        profileSettings.navigate();

        profileSettings.logInToYouTube();

        profileSettings.addEmail("automationtester00666@gmail.com");
        profileSettings.clickONEmailButton();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@id='password']//input[@data-initial-value='']"))));
        profileSettings.addPassword("Fecske111!");
        profileSettings.clickLogIn();
        Thread.sleep(3000);

    }

    @Test
    public void writeCommentTest(){
        Comment comment = new Comment(driver);
        comment.navigate();
        comment.writeComment(commentText);
        String actualResult = comment.getAddedComment(commentText);
        Assertions.assertEquals(commentText, actualResult);
    }

    @Test
    public void editCommentTest() throws InterruptedException {
        testLogIn();
        Comment comment = new Comment(driver);
        comment.navigate();
        Thread.sleep(2000);
        comment.scrollDown();
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//*[@id='like-button'][1]"))).perform();
        comment.editComment(newComment);

        String expectedResult = commentText+newComment;
        String actualResult = comment.getAddedComment("wowwow!!");
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void likeDislikeCommentTest() throws InterruptedException {
        testLogIn();

        Comment comment = new Comment(driver);
        comment.navigate();
        Thread.sleep(2000);
        comment.scrollDown();


        comment.likeComment();

        Assertions.assertTrue(comment.likeComment());
        Thread.sleep(2000);

        comment.dislikeComment();

        Assertions.assertTrue(comment.dislikeComment());
    }

    /*
    @Test
    public void dislikeCommentTest(){
        Comment comment = new Comment(driver);
        comment.navigate();
        comment.dislikeComment();

        boolean expectedResult = ;     //TODO
        boolean actualResult = ;       //TODO
        Assertions.assertTrue(expectedResult, actualResult);
    }

    */


    @Test
    public void deleteCommentTest(){
        Comment comment = new Comment(driver);
        comment.navigate();
        comment.deleteComment();

        String expectedResult = "";     //TODO
        String actualResult = "";       //TODO
        Assertions.assertEquals(expectedResult, actualResult);
    }

    /*
    @AfterEach
    public void quitDriver(){
        driver.quit();
    }
    */

}
