package com.codecool;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Comment extends ProfileSettings {
    public Comment(WebDriver driver) {
        super(driver);
    }

    private final String URL = "https://youtu.be/clnNC4eZ9Tw";

    private final By commentInputBox = By.id("contenteditable-root");

    private final By commentSubmitButton = By.id("submit-button");

    private final By commentMenuButton = By.xpath("//*[contains(@aria-label, 'Művelet menü')][1]");

    private final By commentEditButton = By.xpath("//*[contains(text(), 'Szerkesztés')]");

    private final By commentLikeButton = By.xpath("//*[@id='like-button'][1]");

    private final By commentDislikeButton = By.id("//*[@id='dislike-button'][1]");

    private final By commentDeleteButton = By.xpath("//*[@role='option']/*[contains(text(), 'Törlés')]");

    private final By commentDeleteConfirmButton = By.id("confirm-button");

    private String addedComment = "//*[@class='style-scope ytd-comment-renderer']//*[contains(text(), '@@@@@')]";

// By.partialLinkText()

    public void navigate(){
        driver.navigate().to(URL);
    }

    public void writeComment(String comment){
       driver.findElement(commentInputBox).sendKeys(comment);
       driver.findElement(commentSubmitButton).click();
    }

    public String getAddedComment(String word){
        addedComment = addedComment.replace("@@@@@", word);
        String commentText = driver.findElement(By.xpath(addedComment)).getText();
        return commentText;
    }

    public void editComment(String editedComment){
        driver.findElement(commentMenuButton).click();
        driver.findElement(commentEditButton).click();
        driver.findElement(commentInputBox).sendKeys(editedComment);
        driver.findElement(commentSubmitButton).click();
    }

    public void likeComment(){
        driver.findElement(commentLikeButton).click();
    }

    public void dislikeComment(){
        driver.findElement(commentDislikeButton).click();
    }

    public void deleteComment(){
        driver.findElement(commentMenuButton).click();
        driver.findElement(commentDeleteButton).click();
        driver.findElement(commentDeleteConfirmButton).click();
    }
}