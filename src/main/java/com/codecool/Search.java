package com.codecool;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Search extends BasePage{
    public Search(WebDriver driver) {
        super(driver);
    }

    private final String URL = "https://www.youtube.com/";

    private final By searchInputField = By.xpath("//*[@class='ytd-searchbox-spt']");

    private final By searchSubmitButton = By.id("search-icon-legacy");

    private final By searchHistoryFirstMatch = By.xpath("//*[@class='sbsb_c gsfs']");

    private final By searchHistoryFirstMatchDelete = By.xpath("//*[@class='fr sbpqs_b']");

    //TODO "Remove item from search history" task

    public void navigate(){
        driver.navigate().to(URL);
    }

    public void searchVideoWithWrittenSearchCriteria(String word){
        driver.findElement(searchInputField).sendKeys(word);
        driver.findElement(searchSubmitButton).click();

    }

    public void searchVideoWithSearchHistory(){
        driver.findElement(searchInputField).click();
        driver.findElement(searchHistoryFirstMatch).click();
    }

    public void removeItemFromSearchHistory(){
        driver.findElement(searchInputField).click();
        driver.findElement(searchHistoryFirstMatchDelete).click();
    }
}
