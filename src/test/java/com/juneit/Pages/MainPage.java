package com.juneit.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class MainPage {
    private WebDriver driver;
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getSignInButton(){
        WebElement signInButton = driver.findElement(By.className(SIGNIN_BUTTON_CLASS));
        return signInButton;
    }
    public WebElement getLogoHeader(){
        WebElement logoHeader = driver.findElement(By.className(LOGO_HEADER_SPLOTEAM_CLASS));
        return logoHeader;
    }
    public WebElement getMoreGamesButton(){
        WebElement moreGamesButton = driver.findElement(By.xpath(SHOW_MORE_GAMES_BUTTON_XPATH));
        return moreGamesButton;
    }
    public WebElement getCreateGameButton(){
        WebElement moreGamesButton = driver.findElement(By.xpath(CREATE_GAME_BUTTON_XPATH));
        return moreGamesButton;}

    public WebElement getUserName() {
        WebElement getUserName = driver.findElement(By.xpath(USER_NAME_XPATH));
        String userName = getUserName().getText();
        assertEquals("Irina_test", userName);
        return getUserName;
    }
    public WebElement getLogoNotification(){
        WebElement getLogoNotification = driver.findElement(By.xpath(LOGO_NOTIFICATION_XPATH));
        return getLogoNotification;}
    public WebElement getCountNotification(){
        WebElement getCountNotification = driver.findElement(By.className(COUNT_NOTIFICATION_CLASS));
        return getCountNotification;}
    public WebElement getUserNameHeader(){
        WebElement userNameHeader = driver.findElement(By.className(USER_NAME_HEADER_CLASS));
        return userNameHeader;
    }
    public WebElement getCampImage(){
        WebElement CampImage = driver.findElement(By.className(CAMP_IMAGE_CLASS));
        return CampImage;
    }
    public WebElement getLocalTime(){
        WebElement getLocalTime = driver.findElement(By.className(LOCAL_TIME_CLASS));
        return getLocalTime;
    }
    public WebElement getFooterInfo(){
        WebElement getFooterInfo = driver.findElement(By.className(FOOTER_DATE_CLASS));
        return getFooterInfo;
    }



    public static final String SIGNIN_BUTTON_CLASS = "header__signIn";
    public static final String LOGO_HEADER_SPLOTEAM_CLASS = "header__logo";
    public static final String SHOW_MORE_GAMES_BUTTON_XPATH = "//*[@id=\"root\"]/div[2]/section[2]/div/div[3]/a";
    public static final String COMING_GAMES_TEXT_CLASS = "coming-games__text";
    public static final String CREATE_GAME_BUTTON_XPATH = "//*[@id=\"root\"]/div[2]/section[3]/div/div[2]/a";
    public static final String USER_NAME_XPATH = "//*[@id=\"root\"]/header/div/div/div[3]/div[2]/div[1]";
    public static final String LOGO_NOTIFICATION_XPATH = "//*[@id=\"root\"]/header/div/div/div[2]/div[1]/img";
    public static final String COUNT_NOTIFICATION_CLASS = "Widget_label__3nbvW";
    public static final String USER_NAME_HEADER_CLASS =  "profileText__name";
    public static final String CAMP_IMAGE_CLASS = "CampsButton_img__3CoXw";
    public static final String LOCAL_TIME_CLASS = "locationTimezone";
    public static final String FOOTER_DATE_CLASS ="footer__copyright";
    }
