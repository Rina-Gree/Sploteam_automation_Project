package com.juneit;


import com.juneit.Pages.EventsPage;
import com.juneit.Pages.LoginDialogWindow;
import com.juneit.Pages.MainPage;
import com.juneit.Pages.UserAccountPage;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class NotificationTest {
    private final PropertiesLoader properties = new PropertiesLoader();
    private final WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    MainPage mainPage = new MainPage(driver);
    EventsPage eventsPage = new EventsPage(driver);
    LoginDialogWindow loginDialogWindow = new LoginDialogWindow(driver);
    UserAccountPage userAccountPage = new UserAccountPage(driver);

    @Before
    public void setup() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        ((HasAuthentication) driver).register(UsernameAndPassword.of(properties.username,
                properties.password));
        driver.get(properties.baseUrl);
    }

    @After
    public void close() {
        driver.close();
    }


    @Test
    public void AssertMainPageIsLoaded() {
        assertTrue(mainPage.getSignInButton().isDisplayed());
        assertTrue(mainPage.getLogoHeader().isDisplayed());
    }
    @Ignore
    @Test
    public void AssertCountsNotification() {
        mainPage.getSignInButton().click();
        loginDialogWindow.LoginAccount();
        mainPage.getLogoNotification().isDisplayed();
        mainPage.getCountNotification().isDisplayed();
        mainPage.getLogoNotification().click();
        wait.until(ExpectedConditions.textToBePresentInElement(userAccountPage.assertNamePage(),"Личный кабинет"));
        assertEquals("Личный кабинет", userAccountPage.assertNamePage().getText());
        for (int i = 0; i < userAccountPage.getNotificationsGroup().size(); i++) {
            if (userAccountPage.getNotificationsGroup().get(i).getAttribute("class").contains("active")) {
                assertEquals("Непрочитанные", userAccountPage.getNotificationsGroup().get(i).getText());
            }
           int count = userAccountPage.getcount();
            System.out.println("Найдено уведомлений" +  count);
        }
    }
    @Test
    public void CReadAllNotification() {
        mainPage.getSignInButton().click();
        loginDialogWindow.LoginAccount();
        mainPage.getLogoNotification().isDisplayed();
        mainPage.getLogoNotification().click();
        wait.until(ExpectedConditions.textToBePresentInElement(userAccountPage.assertNamePage(),"Личный кабинет"));
        assertEquals("Личный кабинет", userAccountPage.assertNamePage().getText());
        for (int i = 0; i < userAccountPage.getNotificationsGroup().size(); i++) {
            if (userAccountPage.getNotificationsGroup().get(i).getAttribute("class").contains("active")) {
                assertEquals("Непрочитанные", userAccountPage.getNotificationsGroup().get(i).getText());
            }}
        userAccountPage.changeAllNotificationButton().click();
        userAccountPage.readNotificationButton().click();
        driver.navigate().refresh();
        userAccountPage.getcount();
        int count = userAccountPage.getcount();
        System.out.println("Найдено уведомлений" +  count);
        userAccountPage.messageNotFondNotification().isDisplayed();
        assertEquals("Не найдено уведомлений по выбранной категории.", userAccountPage.messageNotFondNotification().getText());
    }

    @Test
    public void BReadOneNotification() {
        mainPage.getSignInButton().click();
        loginDialogWindow.LoginAccount();
        mainPage.getLogoNotification().isDisplayed();
        mainPage.getLogoNotification().click();
        wait.until(ExpectedConditions.textToBePresentInElement(userAccountPage.assertNamePage(),"Личный кабинет"));
        assertEquals("Личный кабинет", userAccountPage.assertNamePage().getText());
        for (int i = 0; i < userAccountPage.getNotificationsGroup().size(); i++) {
            if (userAccountPage.getNotificationsGroup().get(i).getAttribute("class").contains("active")) {
                assertEquals("Непрочитанные", userAccountPage.getNotificationsGroup().get(i).getText());
            }}
        userAccountPage.CheckBox().isDisplayed();
        assertFalse(userAccountPage.isCheckBoxSelected());
        userAccountPage.toggleCheckBox();
        userAccountPage.readNotificationButton().click();
        driver.navigate().refresh();
        userAccountPage.getcount();
        int count = userAccountPage.getcount();
        System.out.println("Найдено уведомлений" +  count);
        }


    @Test
    public void DeleteToNotification() {
        mainPage.getSignInButton().click();
        loginDialogWindow.LoginAccount();
        mainPage.getLogoNotification().isDisplayed();
        mainPage.getLogoNotification().click();
        wait.until(ExpectedConditions.textToBePresentInElement(userAccountPage.assertNamePage(),"Личный кабинет"));
        assertEquals("Личный кабинет", userAccountPage.assertNamePage().getText());
        userAccountPage.eventsGroupNotification().click();
        for (int i = 0; i < userAccountPage.getNotificationsGroup().size(); i++) {
            if (userAccountPage.getNotificationsGroup().get(i).getAttribute("class").contains("active")) {
                assertEquals("События", userAccountPage.getNotificationsGroup().get(i).getText());
            }
        }
        int count = userAccountPage.getcount();
        System.out.println("Найдено уведомлений" +  count);
        int beforeCount = userAccountPage.getcount();
        userAccountPage.checkBoxNotificationListElement().get(3).click();
        userAccountPage.checkBoxNotificationListElement().get(1).click();
        userAccountPage.deleteNotificationButton().click();
        driver.navigate().refresh();
        userAccountPage.eventsGroupNotification().click();
        userAccountPage.getcount();
        System.out.println("Найдено уведомлений" +  count);
        int afterCount = userAccountPage.getcount();
        assertEquals(beforeCount - 2, afterCount);
    }

    @Test
    public void IDeleteAllNotification() {
        mainPage.getSignInButton().click();
        loginDialogWindow.LoginAccount();
        mainPage.getLogoNotification().isDisplayed();
        mainPage.getLogoNotification().click();
        wait.until(ExpectedConditions.textToBePresentInElement(userAccountPage.assertNamePage(),"Личный кабинет"));
        assertEquals("Личный кабинет", userAccountPage.assertNamePage().getText());
        userAccountPage.eventsGroupNotification().click();
        for (int i = 0; i < userAccountPage.getNotificationsGroup().size(); i++) {
            if (userAccountPage.getNotificationsGroup().get(i).getAttribute("class").contains("active")) {
                assertEquals("События", userAccountPage.getNotificationsGroup().get(i).getText());
            }
        }
        int count = userAccountPage.getcount();
        System.out.println("Найдено уведомлений" +  count);
        int beforeCount = userAccountPage.getcount();
        userAccountPage.changeAllNotificationButton().click();
        userAccountPage.deleteNotificationButton().click();
        driver.navigate().refresh();
        userAccountPage.eventsGroupNotification().click();
        userAccountPage.getcount();
        userAccountPage.messageNotFondNotification().isDisplayed();
        assertEquals("Не найдено уведомлений по выбранной категории.", userAccountPage.messageNotFondNotification().getText());
}}

















