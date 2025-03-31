package com.juneit.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;



import  java.util.List;


public class EventsPage {
    private final WebDriver driver;

    public EventsPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getEventsAndCreateGamesTabs() {
        List<WebElement> eventsAndCreateGamesTabs = driver.findElements(By.className(EVENTS_AND_CREATE_GAMES_TABS_CLASS));
        return eventsAndCreateGamesTabs;
    }
    public WebElement getFirstDate() {
        WebElement getFirstDate = driver.findElement(By.xpath(FIRST_DATE_XPATH));
        return getFirstDate;
    }
    public WebElement getDropdownArena() {
        WebElement getDropdownArena = driver.findElement(By.xpath(EVENT_DROPDOWN_ARENA_XPATH));
        return getDropdownArena;
    }
    public WebElement changeArenaName() {
        WebElement changeArenaName = driver.findElement(By.xpath(ARENA_NAME_XPATH));
        return changeArenaName;
    }
    public WebElement ChangeTimeForGame() {
        WebElement ChangeTimeForGame = driver.findElement(By.xpath(TIME_FOR_GAME_XPATH));
        return ChangeTimeForGame;
    }
    public WebElement getChangeGamesButton() {
        WebElement getChangeGamesButton = driver.findElement(By.xpath(CREATE_GAME_BUTTON_XPATH));
        return getChangeGamesButton;
    }
    public WebElement getLoginWindow() {
        WebElement getLoginWindow = driver.findElement(By.xpath(MODAL_WINDOW_LOGIN_XPATH));
        return getLoginWindow;
    }
    public WebElement getCityList() {
        WebElement getCityList = driver.findElement(By.xpath(CHANGE_CITY_XPATH));
        return getCityList;
    }
    public WebElement getCityTverChange() {
        WebElement getCityTverChange = driver.findElement(By.xpath(CHANGE_CITY_TVER_XPATH));
        return getCityTverChange;
    }
    public WebElement getTimeSlotsForCreateGames() {
        WebElement getTimeSlotsForCreateGames = driver.findElement(By.className(CREATE_GAME_TIME_CARDS));
        return getTimeSlotsForCreateGames;
    }
    public List<WebElement> getCalendarForCreateGame() {
        List<WebElement> getCalendarForCreateGame = driver.findElements(By.className(CALENDAR_CLASS));
        return getCalendarForCreateGame;
    }
    public WebElement getCardsForCreateGames() {
        WebElement getCardsForCreateGames = driver.findElement(By.className(EVENT_GAME_CREATE_CARDS_XPATH));
        return getCardsForCreateGames;
    }
    public WebElement getTextIfNotCardsForGames() {
        WebElement getTextIfNotCardsForGames = driver.findElement(By.xpath(TEXT_IF_EMPTY_CARDS_GAME_XPATH));
        return getTextIfNotCardsForGames;
    }
    public WebElement getTodayDate() {
        WebElement getTodayDate = driver.findElement(By.xpath(FIRST_DATE_CHANGE_XPATH));
        return getTodayDate;
    }
    public List<WebElement> getBeginTimeForEveryCard() {
        List<WebElement> getBeginTimeForEveryCard = driver.findElements(By.className(BEGIN_AND_END_TIME_CLASS));
        return getBeginTimeForEveryCard;
    }
    public WebElement getAssertBeginTimeForEveryCard() {
        WebElement getAssertBeginTimeForEveryCard = driver.findElement(By.className(TIME_EVERY_CARD_XPATH));
        return getAssertBeginTimeForEveryCard;
    }
    public List<WebElement> getDateOfCalendar() {
        List<WebElement> getDateOfCalendar = driver.findElements(By.className(CALENDAR_DATE_CLASS));
        return getDateOfCalendar;
    }

        public static final String EVENTS_AND_CREATE_GAMES_TABS_CLASS = "Tabs_tab__3e8GV";
        public static final String EVENT_DROPDOWN_ARENA_XPATH = "//*[@id=\"root\"]/div[2]/div[1]/div/div[3]/div[1]/div[1]";

        // CreateGameIfNotLogin
        public static final String FIRST_DATE_XPATH = "//*[@id=\"root\"]/div[2]/div[1]/div/div[1]/div/div[2]/span[2]";
        public static final String ARENA_NAME_XPATH = "//*[@id=\"root\"]/div[2]/div[1]/div/div[3]/div[1]/div[1]/div[2]/div[2]";
        public static final String TIME_FOR_GAME_XPATH = "//*[@id=\"root\"]/div[2]/div[1]/div/div[4]/div/div/div[2]/div[1]/div[1]";
        public static final String CREATE_GAME_BUTTON_XPATH = "//*[@id=\"root\"]/div[2]/div[1]/div/div[4]/div/div/div[3]/div[4]/button";
        public static final String MODAL_WINDOW_LOGIN_XPATH = "/html/body/div[3]/div/div";

        //CreateGameForTver
        public static final String CHANGE_CITY_XPATH = "//*[@id=\"root\"]/header/div/div/div[1]/div[1]";
        public static final String CHANGE_CITY_TVER_XPATH = "//*[@id=\"root\"]/header/div/div/div[1]/div[3]/div[44]";

        public static final String CALENDAR_XPATH = "//*[@id=\"root\"]/div[2]/div[1]/div/div[1]/div/div[4]";
        public static final String CALENDAR_CLASS = "NavLink_navLink__3OIyY";
        public static final String CREATE_GAME_TIME_CARDS = "Tabs_tabPanel__N-v3z";
        public static final String EVENT_GAME_CREATE_CARDS_XPATH = "//*[@id=\"root\"]/div[2]/div[1]/div/div[4]/div";
        public static final String TEXT_IF_EMPTY_CARDS_GAME_XPATH = "//*[@id=\"root\"]/div[2]/div[1]/div/div[4]/div";
        public static final String FIRST_DATE_CHANGE_XPATH = "//*[@id=\"root\"]/div[2]/div[1]/div/div[1]/div/div[1]/span[2]";
        public static final String TIME_EVERY_CARD_XPATH = "//*[@id=\"root\"]/div[2]/div[1]/div/div[4]/div/div/div[2]/div[2]/div[1]";

        public static final String BEGIN_AND_END_TIME_CLASS = "create-event__time";
        public static final String BEGIN_AND_END_TIME_CLASS = "create-event__time";
        public static final String CALENDAR_DATE_CLASS = "NavLink_navLink__3OIyY";

    }
}