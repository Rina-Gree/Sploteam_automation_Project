package com.juneit;


import com.juneit.Pages.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Alert;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;
import static org.junit.Assert.*;
import java.util.Calendar;
import  java.util.List;
import  java.util.concurrent.TimeUnit;


public class SploteamTestPOM {
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
    public void assertMainPageIsLoaded() {
        assertTrue(mainPage.getSignInButton().isDisplayed());
        assertTrue(mainPage.getLogoHeader().isDisplayed());
    }

    @Test
    public void assertCreateGameTabOpen() {
        mainPage.getCreateGameButton().click();
        for (int i = 0; i < eventsPage.getEventsAndCreateGamesTabs().size(); i++) {
            if (eventsPage.getEventsAndCreateGamesTabs().get(i).getAttribute("class").contains("active")) {
                assertEquals("Создать игру", eventsPage.getEventsAndCreateGamesTabs().get(i).getText());
            }
        }
    }

    @Test
    public void assertEventsTabOpen() {
        mainPage.getMoreGamesButton().click();
        for (int i = 0; i < eventsPage.getEventsAndCreateGamesTabs().size(); i++) {
            if (eventsPage.getEventsAndCreateGamesTabs().get(i).getAttribute("class").contains("active")) {
                assertEquals("События", eventsPage.getEventsAndCreateGamesTabs().get(i).getText());
            }
        }
    }

    @Test
    public void CreateGameIfNotLogin() {
        mainPage.getCreateGameButton().click();
        eventsPage.getFirstDate().click();
        eventsPage.getDropdownArena().click();
        eventsPage.changeArenaName().click();
        eventsPage.ChangeTimeForGame().click();
        eventsPage.getChangeGamesButton().click();
        assertTrue(eventsPage.getLoginWindow().isDisplayed());
    }

    @Test
    public void CreateGameForTver() {
        mainPage.getCreateGameButton().click();
        Assert.assertTrue(eventsPage.getCityList().isDisplayed());
        eventsPage.getCityList().click();
        eventsPage.getCityTverChange().click();
        eventsPage.getCalendarForCreateGame();
        for (int i = 0; i < eventsPage.getCalendarForCreateGame().size(); i++) {
            assertTrue(eventsPage.getTimeSlotsForCreateGames().isDisplayed());
//            wait(3);
//            if
//            (driver.findElement(eventsPage.getTimeSlotsForCreateGames).isEmpty()){
//                assertEquals("Нет доступных вариантов. Попробуйте изменить дату или фильтр.", eventsPage.getTextIfNotCardsForGames().getText());
//            assertTrue(eventsPage.getTextIfNotCardsForGames().isDisplayed());

        }
    }

    @Test
    public void AssertThatFirstSlotOfCalendarIsToday() {
        mainPage.getCreateGameButton().click();
        eventsPage.getTodayDate().click();
        assertTrue(eventsPage.getTodayDate().isDisplayed());
        assertEquals("Сегодня", eventsPage.getTodayDate().getText());


    }
    //10.03.2025

    @Test  //Загрузка аватара
    public void asserLoadingImageForAvatar() throws URISyntaxException, IOException {
        mainPage.getSignInButton().click();
        loginDialogWindow.LoginAccount();
        mainPage.getUserNameHeader().click();
        userAccountPage.getEditUserProfileButton().click();
        userAccountPage.getChangeAvatarButton().click();

        ClassLoader classLoader = SploteamTestPOM.class.getClassLoader();
        URL resourceURL = classLoader.getResource("ExpectedDefaultAvatarScreenshot.png");
        File avatarFile = Paths.get(resourceURL.toURI()).toFile();

        userAccountPage.getChooseFileForAvatarInput().sendKeys(avatarFile.getAbsolutePath());

        wait.until(ExpectedConditions.elementToBeClickable(userAccountPage.getConfirmChooseFileForAvatarButton()));
        wait.until(ExpectedConditions.textToBePresentInElement(userAccountPage.getChangeAvatarCropMessage(),
                "Пожалуйста, выберите часть изображения, которую Вы хотите использовать в качестве аватара."));

      File avatarInDialogWindowScreenshot = userAccountPage.getAvatarAreaDialogWindow().getScreenshotAs(OutputType.FILE);

//        URL resourceUrlExpectedImage = classLoader.getResource("ExpectedAvatarScreenshot.png");
//        File expectedAvatarFile = Paths.get(resourceUrlExpectedImage.toURI()).toFile();
//      assertTrue(compareImages(avatarInDialogWindowScreenshot, expectedAvatarFile));

        userAccountPage.getCloseChangeAvatarButton().click();
        userAccountPage.getSaveEditProfileButton().click();
       userAccountPage.getCloseSaveEditProfileMessageButton().click();
   }
    @Test
    public void assertDefaultAvatarInUserProfile() throws IOException, URISyntaxException {

        mainPage.getSignInButton().click();
        loginDialogWindow.LoginAccount();
        mainPage.getUserNameHeader().click();
        File defaultAvatarInDialogWindowScreenshot = userAccountPage.getAvatarAreaUserProfile().getScreenshotAs(OutputType.FILE);
       File defaultScreenshotFile = new File("target/defaultAvatarScreenshot.png");
       FileHandler.copy(defaultAvatarInDialogWindowScreenshot, defaultScreenshotFile);

        ClassLoader classLoader = SploteamTestPOM.class.getClassLoader();
        URL resourceUrlExpectedDefaultAvatar = classLoader.getResource("ExpectedDefaultAvatarScreenshot.png");
        File expectedDefaultAvatarFile = Paths.get(resourceUrlExpectedDefaultAvatar.toURI()).toFile();
        assertTrue(compareImages(defaultAvatarInDialogWindowScreenshot, expectedDefaultAvatarFile));

        //  logout();
    }
    @Test
    public void assertDefaultAvatarInMainPageAvatar() throws IOException, URISyntaxException {
//        login("stn08101989+Automation@gmail.com", "Password1");

        mainPage.getSignInButton().click();
        loginDialogWindow.LoginAccount();
        mainPage.getUserNameHeader().click();
        File defaultAvatarInDialogWindowScreenshot = userAccountPage.getAvatarAreaUserProfile().getScreenshotAs(OutputType.FILE);
        File defaultScreenshotFile = new File("target/defaultAvatarScreenshot.png");
        FileHandler.copy(defaultAvatarInDialogWindowScreenshot, defaultScreenshotFile);

        ClassLoader classLoader = SploteamTestPOM.class.getClassLoader();
        URL resourceUrlExpectedDefaultAvatar = classLoader.getResource("ExpectedDefaultAvatarScreenshot.png");
        File expectedDefaultAvatarFile = Paths.get(resourceUrlExpectedDefaultAvatar.toURI()).toFile();
        assertTrue(compareImages(defaultAvatarInDialogWindowScreenshot, expectedDefaultAvatarFile));

        //  logout();
    }

    @Test
    public void assertPasswordHiddenStyle() {
        mainPage.getSignInButton().click();
        // loginDialogWindow.getEmailInput().sendKeys("email@email.com");
        driver.findElement(By.xpath(INPUT_FORM_EMAIL_XPATH)).sendKeys("Splot2025");
        driver.findElement(By.className("FormInput_togglePassVisibleHide__3KKSI")).click();
        String defaultPasswordAttributeType =  driver.findElement(By.xpath(INPUT_FORM_EMAIL_XPATH)).getAttribute("type");
       assertEquals("password", defaultPasswordAttributeType);

        JavascriptExecutor JSscript = (JavascriptExecutor) driver;
        String textStyleHiddenPassword = (String) JSscript.executeScript("return window.getComputedStyle(arguments[0]).getPropertyValue('-webkit-text-security');", driver.findElement(By.xpath(INPUT_FORM_EMAIL_XPATH)));
       // assertEquals("disc", textStyleHiddenPassword);

        driver.findElement(By.className("FormInput_togglePassVisibleHide__3KKSI")).click();

    }

    @Test
    public void assertPasswordShowStyle() {
        mainPage.getSignInButton().click();
        // loginDialogWindow.getEmailInput().sendKeys("email@email.com");

        driver.findElement(By.xpath(INPUT_FORM_EMAIL_XPATH)).sendKeys("Splot2025");
        driver.findElement(By.className("FormInput_togglePassVisibleHide__3KKSI")).click();
        String showPasswordAttributeType =  driver.findElement(By.xpath(INPUT_FORM_EMAIL_XPATH)).getAttribute("type");
        assertEquals("text", showPasswordAttributeType);

        JavascriptExecutor JSscript = (JavascriptExecutor) driver;
        String textStyleHiddenPassword = (String) JSscript.executeScript("return window.getComputedStyle(arguments[0]).getPropertyValue('-webkit-text-security');", driver.findElement(By.xpath(INPUT_FORM_EMAIL_XPATH)));
        assertEquals("none", textStyleHiddenPassword);
    }


    //HomeWork_3

    @Test  //Проверить загрузку аватара с компьютера
    public void assertLoadingImageForAvatarFromDesktop() throws URISyntaxException, IOException {
//
        mainPage.getSignInButton().click();
        loginDialogWindow.LoginAccount();
        mainPage.getUserNameHeader().click();
        userAccountPage.getEditUserProfileButton().click();
        userAccountPage.getChangeAvatarButton().click();

        ClassLoader classLoader = SploteamTestPOM.class.getClassLoader();
        URL resourceURL = classLoader.getResource("ExpectedDefaultAvatarScreenshot.png");
        File avatarFile = Paths.get(resourceURL.toURI()).toFile();

       String filePath = "C:\\Users\\iSimpleLab\\Desktop\\ФОНЫ\\Avatar.jpg";
        userAccountPage.getChooseFileForAvatarInput().sendKeys(filePath);

        wait.until(ExpectedConditions.elementToBeClickable(userAccountPage.getConfirmChooseFileForAvatarButton()));
        wait.until(ExpectedConditions.textToBePresentInElement(userAccountPage.getChangeAvatarCropMessage(),
                "Пожалуйста, выберите часть изображения, которую Вы хотите использовать в качестве аватара."));

        File avatarInDialogWindowScreenshot = userAccountPage.getAvatarAreaDialogWindow().getScreenshotAs(OutputType.FILE);
        File defaultAvatarInDialogWindowScreenshot = userAccountPage.getAvatarAreaUserProfile().getScreenshotAs(OutputType.FILE);
        File defaultScreenshotFile = new File("target/desktopAvatarScreenshot.png");
        FileHandler.copy(defaultAvatarInDialogWindowScreenshot, defaultScreenshotFile);

        userAccountPage.getCloseChangeAvatarButton().click();
        userAccountPage.getSaveEditProfileButton().click();
        userAccountPage.getCloseSaveEditProfileMessageButton().click();

        mainPage.getUserNameHeader().click();
        userAccountPage.logout();


    }
    @Test  //Проверить неуспешную загрузку аватара
    public void ErrorLoadingImageForAvatarFromDesktop(){

        mainPage.getSignInButton().click();
        loginDialogWindow.LoginAccount();
        mainPage.getUserNameHeader().click();
        userAccountPage.getEditUserProfileButton().click();
        userAccountPage.getChangeAvatarButton().click();

        String filePath = "C:\\Users\\iSimpleLab\\Desktop\\Сертификаты ФЛ.txt";
        userAccountPage.getChooseFileForAvatarInput().sendKeys(filePath);

        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertMessage= driver.switchTo().alert().getText();
        System.out.println(alertMessage);
        Assert.assertEquals(alertMessage, "Тип файла должен быть jpeg или png.");
        alert.accept();
        userAccountPage.getCloseSaveEditProfileMessageButton().click();

        mainPage.getUserNameHeader().click();
        userAccountPage.logout();
    }

    @Test  //Проверить логотип с помощью скриншота
    public void AssertLogoScreenshot() throws IOException, URISyntaxException {

        mainPage.getSignInButton().click();
        loginDialogWindow.LoginAccount();

        File LogoScreenshot = mainPage.getLogoHeader().getScreenshotAs(OutputType.FILE);//сделпть скриншот
        File ScreenshotFile = new File("target/logoScreenshot.png");//Создать файл для скриншота
        FileHandler.copy(LogoScreenshot, ScreenshotFile);// Положить скриншот в файл

        ClassLoader classLoader = SploteamTestPOM.class.getClassLoader();
        URL resourceURLExpectedLogo = classLoader.getResource("ExpectedLogoScreenshot.png");
        File expectedLogoScreenshotFile = Paths.get(resourceURLExpectedLogo.toURI()).toFile();
        assertTrue(compareImages(LogoScreenshot,expectedLogoScreenshotFile));

    }

    @Test
    public void AssertCampScreenshot() throws IOException, URISyntaxException {

        mainPage.getSignInButton().click();
        loginDialogWindow.LoginAccount();

        File CampScreenshot = mainPage.getCampImage().getScreenshotAs(OutputType.FILE);//сделaть скриншот
        File ScreenshotFile = new File("target/CampScreenshot.png");//Создать файл для скриншота
        FileHandler.copy(CampScreenshot, ScreenshotFile);// Положить скриншот в файл

        ClassLoader classLoader = SploteamTestPOM.class.getClassLoader();
        URL resourceURLExpectedCamp = classLoader.getResource("ExpectedCampScreenshot.png");
        File expectedCampScreenshotFile = Paths.get(resourceURLExpectedCamp.toURI()).toFile();
        assertTrue(compareImages(CampScreenshot, expectedCampScreenshotFile));

    }



        private boolean compareImages(File screenshot, File referenceImage) throws IOException {
        // Compare images pixel by pixel
        BufferedImage img1 = ImageIO.read(screenshot);
        BufferedImage img2 = ImageIO.read(referenceImage);

        if (img1.getWidth() != img2.getWidth() || img1.getHeight() != img2.getHeight()) {
            return false;
        }

        for (int x = 0; x < img1.getWidth(); x++) {
            for (int y = 0; y < img1.getHeight(); y++) {
                if (img1.getRGB(x, y) != img2.getRGB(x, y)) {
                    return false;
                }
            }
        }
        return true;
    }


    public static final String INPUT_FORM_EMAIL_XPATH = "/html/body/div[3]/div/div/div/div[2]/form/div[2]/input";
}