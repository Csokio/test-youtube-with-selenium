import com.codecool.Comment;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

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
    public void writeCommentTest(){
        Comment comment = new Comment(driver);
        comment.navigate();
        comment.writeComment(commentText);
        String actualResult = comment.getAddedComment(commentText);
        Assertions.assertEquals(commentText, actualResult);
    }

    @Test
    public void editCommentTest(){
        Comment comment = new Comment(driver);
        comment.navigate();
        comment.editComment(newComment);

        String expectedResult = commentText+newComment;
        String actualResult = driver.findElement(comment.getAddedComment(newComment);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void likeCommentTest(){
        Comment comment = new Comment(driver);
        comment.navigate();
        comment.likeComment();

        String expectedResult = "";     //TODO
        String actualResult = "";       //TODO
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void dislikeCommentTest(){
        Comment comment = new Comment(driver);
        comment.navigate();
        comment.dislikeComment();

        String expectedResult = "";     //TODO
        String actualResult = "";       //TODO
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void deleteCommentTest(){
        Comment comment = new Comment(driver);
        comment.navigate();
        comment.deleteComment();

        String expectedResult = "";     //TODO
        String actualResult = "";       //TODO
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @AfterEach
    public void quitDriver(){
        driver.quit();
    }
}
