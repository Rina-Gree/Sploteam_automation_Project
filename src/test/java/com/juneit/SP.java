package com.juneit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;


public class SP {
    public static final String url = "https://frontend.beta-spltm.ru/";
    WebDriver driver = new ChromeDriver();

    @Before
    public void setup() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get(url);
    }

    @After
    public void close() {
        driver.close();
    }

    @Test
    public void openMainPage() {

        Assert.assertTrue(driver.findElement(By.className("header__signIn")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath(CREATE_GAMES_BUTTON_XPATH)).isDisplayed());
        Assert.assertEquals("Играй, тренируйся\n" +
                "и улучшай свои навыки\n" +
                "в командных видах спорта", driver.findElement(By.className(COMING_GAMES_TITLE_CLASS)).getText());
        Assert.assertTrue(driver.findElement(By.className(HEADER_FAQ_CLASS)).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath(ARENDA_BUTTON_XPATH)).isDisplayed());
        Assert.assertTrue(driver.findElement(By.className(LOCATION_BUTTON_CLASS)).isDisplayed());
        Assert.assertEquals("Санкт-Петербург", driver.findElement(By.className(LOCATION_TITLE_CLASS)).getText());
        Assert.assertTrue(driver.findElement(By.className(BASKETBALL_BUTTON_CLASS)).isDisplayed());
        Assert.assertEquals("Баскетбол", driver.findElement(By.className(BASKETBALL_TITLE_CLASS)).getText());
        Assert.assertTrue("Приглашаем к сотрудничеству\n +" +
                "владельцев площадок и тренеров", driver.findElement(By.xpath(COOPERATION_TITLE_XPATH)).isDisplayed());

    }
    @Test
    public void sportsCamps() {

        Assert.assertTrue(driver.findElement(By.className(CAMPS_BUTTON_CLASS)).isDisplayed());
        Assert.assertTrue(driver.findElement(By.className(CAMPS_LOGO_CLASS)).isDisplayed());
        Assert.assertEquals("Новинка!\n" +
                "Спортивные кемпы", driver.findElement(By.className(CAMPS_TITLE_CLASS)).getText());
    }

    @Test
    public void sploteamLogin() {

        userLogin();
        String actualUserName = driver.findElement(By.className(USER_NAME_HEADER_CLASS)).getText();
        Assert.assertEquals("Rina", actualUserName);
        Assert.assertTrue(driver.findElement(By.className(AVATAR_CLASS)).isDisplayed());
        //sign out
        driver.findElement(By.className(USER_NAME_HEADER_CLASS)).click();
        driver.findElement(By.xpath(LOGOUT_BUTTON_XPATH)).click();
    }

    private void userLogin() {
        driver.findElement(By.className(SIGNIN_BUTTON_CLASS)).click();
        driver.findElement(By.xpath(EMAIL_INPUT_XPATH)).sendKeys("teleshovai@bk.ru");
        driver.findElement(By.name(PASSWORD_INPUT_NAME)).sendKeys("Sploteam_2023");
        driver.findElement(By.xpath(LOGIN_BUTTON_XPATH)).click();
    }

    @Test
    public void sploteamLoginWrong() {
        driver.findElement(By.className(SIGNIN_BUTTON_CLASS)).click();
        driver.findElement(By.xpath(EMAIL_INPUT_XPATH)).sendKeys("tatyana.sinyakova.08+111@gmail.com");
        driver.findElement(By.name(PASSWORD_INPUT_NAME)).sendKeys("sss@gmail.com");
        driver.findElement(By.xpath(LOGIN_BUTTON_XPATH)).click();
        Assert.assertTrue(driver.findElement(By.className(WRONG_PASSWORD_MESSEGE_CLASS)).isDisplayed());
        String actualErrorMessage = driver.findElement(By.className(WRONG_PASSWORD_MESSEGE_CLASS)).getText();
        Assert.assertEquals("Неверный пароль", actualErrorMessage);
        Assert.assertTrue(driver.findElement(By.className(SIGNIN_BUTTON_CLASS)).isDisplayed());
    }

    @Test
    public void sploteamLoginWrongPasswordField() {
        driver.findElement(By.className(SIGNIN_BUTTON_CLASS)).click();
        driver.findElement(By.xpath(EMAIL_INPUT_XPATH)).sendKeys("teleshovai@bk.ru");
        driver.findElement(By.name(PASSWORD_INPUT_NAME)).sendKeys("");
        driver.findElement(By.xpath(LOGIN_BUTTON_XPATH)).click();
        Assert.assertTrue(driver.findElement(By.xpath(AUTORIZATION_FORM_XPATH)).isDisplayed());
        Assert.assertTrue(driver.findElement(By.className(SIGNIN_BUTTON_CLASS)).isDisplayed());
    }

    @Test
    public void changeUserName() {
        driver.findElement(By.className(SIGNIN_BUTTON_CLASS)).click();
        driver.findElement(By.xpath(EMAIL_INPUT_XPATH)).sendKeys("teleshovai@bk.ru");
        driver.findElement(By.name(PASSWORD_INPUT_NAME)).sendKeys("Sploteam_2023");
        driver.findElement(By.xpath(LOGIN_BUTTON_XPATH)).click();
        String actualUserName = driver.findElement(By.className(USER_NAME_HEADER_CLASS)).getText();
        Assert.assertEquals("Rina", actualUserName);
        driver.findElement(By.className(AVATAR_CLASS)).click();
        String actualName = driver.findElement(By.xpath(PROFILE_NAME)).getText();
        Assert.assertEquals("Rina", actualName);
        driver.findElement(By.xpath(CHANGE_PROFILE_BUTTON_XPATH)).isDisplayed();
        driver.findElement(By.xpath(CHANGE_PROFILE_BUTTON_XPATH)).click();
        driver.findElement(By.xpath(USER_NAME_INPUT)).sendKeys("Рина");
        driver.findElement(By.xpath(SAVE_BUTTON)).click();
        Assert.assertEquals("Ваш профиль успешно обновлён", driver.findElement(By.xpath(TEXT_ALIGN)).getText());
        driver.findElement(By.className(CLOSE_MODAL_BUTTON)).click();
        driver.findElement(By.xpath(CLEAR_NAME_INPUT)).click();
        driver.findElement(By.xpath(USER_NAME_INPUT)).sendKeys("Rina");
        driver.findElement(By.xpath(SAVE_BUTTON)).click();
        Assert.assertEquals("Ваш профиль успешно обновлён", driver.findElement(By.xpath(TEXT_ALIGN)).getText());
        driver.findElement(By.className(CLOSE_MODAL_BUTTON)).click();
    }

    @Test
    public void sploteamChangeCity() {
        Assert.assertTrue(driver.findElement(By.className(HEADER_CITY_CLASS)).isDisplayed());
        Assert.assertEquals("Санкт-Петербург", driver.findElement(By.className(HEADER_CITY_CLASS)).getText());
        driver.findElement(By.className(HEADER_CITY_CLASS)).click();
        driver.findElements(By.className(DROPDOWN_CITY_OPTION_CLASS)).get(4).click();
        Assert.assertEquals("Барнаул", driver.findElement(By.className(HEADER_CITY_CLASS)).getText());
        Assert.assertTrue(driver.findElement(By.className(HEADER_CITY_CLASS)).isDisplayed());
        driver.findElement(By.className(HEADER_CITY_CLASS)).click();
        driver.findElements(By.className(DROPDOWN_CITY_OPTION_CLASS)).get(0).click();
        Assert.assertEquals("Санкт-Петербург", driver.findElement(By.className(HEADER_CITY_CLASS)).getText());
    }

    @Test
    public void sploteamFilterGames() {
        driver.findElement(By.xpath(MORE_GAMES_BUTTON_XPATH)).click();
        driver.findElement(By.xpath(FIRST_DATE_XPATH)).click();
        Assert.assertTrue(driver.findElement(By.xpath(FILTER_BY_ARENA_XPATH)).isDisplayed());

        Assert.assertEquals("Все арены", driver.findElement(By.xpath(FILTER_BY_ARENA_XPATH)).getText());
        driver.findElement(By.xpath(FILTER_BY_ARENA_XPATH)).click();
        driver.findElements(By.className(DROPDOWN_ARENA_OPTION_CLASS)).get(1).click();
        Assert.assertEquals("ВЦПС \"Песок\"", driver.findElement(By.xpath(FILTER_BY_ARENA_XPATH)).getText());
        driver.findElements(By.className(DROPDOWN_LEVEL_PLAYS)).get(1).click();
        Assert.assertEquals("Любой уровень", driver.findElement(By.xpath(LEVEL_XPATH)).getText());
    }

    // Homework 3(1)
    @Test
    public void sploteamFilterEvents() {
        driver.findElement(By.xpath(MORE_GAMES_BUTTON_XPATH)).click();
        driver.findElement(By.xpath(FIRST_DATE_XPATH)).click();
        Assert.assertTrue(driver.findElement(By.xpath(FILTER_BY_KIND_SPORTS_XPATH)).isDisplayed());
        Assert.assertEquals("Все виды спорта", driver.findElement(By.xpath(FILTER_BY_KIND_SPORTS_XPATH)).getText());
        //filter by sports_kind
        driver.findElement(By.xpath(FILTER_BY_KIND_SPORTS_XPATH)).click();
        driver.findElements(By.className(DROPDOWN_ALL_SPORTS_CLASS)).get(1).click();
        Assert.assertEquals("Пляжный волейбол", driver.findElement(By.xpath(FILTER_BY_KIND_SPORTS_XPATH)).getText());

    }

    // Homework 3(2)
    @Test
    public void sploteamQuizCityChange() {
        //click sign in button
        driver.findElement(By.className(SIGNIN_BUTTON_CLASS)).click();
        //type email
        driver.findElement(By.xpath(EMAIL_INPUT_XPATH)).sendKeys("teleshovai@bk.ru");
        //type password
        driver.findElement(By.name(PASSWORD_INPUT_NAME)).sendKeys("Sploteam_2023");
        //click login
        driver.findElement(By.xpath(LOGIN_BUTTON_XPATH)).click();
        //assert user logged in
        String actualUserName = driver.findElement(By.className(USER_NAME_HEADER_CLASS)).getText();
        Assert.assertEquals("Rina", actualUserName);
        driver.findElement(By.className(AVATAR_CLASS)).click();
        String actualName = driver.findElement(By.xpath(PROFILE_NAME)).getText();
        Assert.assertEquals("Rina", actualName);
        driver.findElement(By.xpath(QUIZ_BUTTON_XPATH)).isDisplayed();
        driver.findElement(By.xpath(QUIZ_BUTTON_XPATH)).click();
        driver.findElement(By.xpath(QUIZ_CITY_CHANGE_XPATH)).click();
        driver.findElements(By.className(DROPDOWN_QUIZ_CITY_CHANGE_CLASS)).get(45).click();
        driver.findElement(By.xpath(SAVE_BUTTON_XPATH)).click();
        Assert.assertTrue(driver.findElement(By.className(WRONG_QUIZ_MESSAGE_CLASS)).isDisplayed());
        String actualErrorMessage = driver.findElement(By.className(WRONG_QUIZ_MESSAGE_CLASS)).getText();
        Assert.assertEquals("Ответьте, пожалуйста, на все вопросы", actualErrorMessage);


    }
    // Homework 3(3)
    @Test
    public void sploteamChangeGender(){
        //click sign in button
        driver.findElement(By.className(SIGNIN_BUTTON_CLASS)).click();
        //type email
        driver.findElement(By.xpath(EMAIL_INPUT_XPATH)).sendKeys("teleshovai@bk.ru");
        //type password
        driver.findElement(By.name(PASSWORD_INPUT_NAME)).sendKeys("Sploteam_2023");
        //click login
        driver.findElement(By.xpath(LOGIN_BUTTON_XPATH)).click();
        //assert user logged in
        String actualUserName = driver.findElement(By.className(USER_NAME_HEADER_CLASS)).getText();
        Assert.assertEquals("Rina", actualUserName);
        driver.findElement(By.className(AVATAR_CLASS)).click();
        String actualName = driver.findElement(By.xpath(PROFILE_NAME)).getText();
        Assert.assertEquals("Rina", actualName);
        driver.findElement(By.xpath(CHANGE_PROFILE_BUTTON_XPATH)).isDisplayed();
        driver.findElement(By.xpath(CHANGE_PROFILE_BUTTON_XPATH)).click();
        driver.findElement(By.xpath(GENDER_CHANGE_XPATH));
        driver.findElement(By.xpath(GENDER_CHANGE_XPATH)).click();
        //change gender
        driver.findElements(By.className(DROPDOWN_GENDER_CHANGE_CLASS)).get(1).click();
        driver.findElement(By.xpath(SAVE_BUTTON)).click();
        Assert.assertEquals("Ваш профиль успешно обновлён", driver.findElement(By.xpath(TEXT_ALIGN)).getText());
        driver.findElement(By.className(CLOSE_MODAL_BUTTON)).click();
        driver.findElement(By.xpath(GENDER_CHANGE_XPATH)).isDisplayed();
        Assert.assertEquals("Женский", driver.findElement(By.xpath(GENDER_CHANGE_XPATH)).getText());
        driver.findElement(By.xpath(SAVE_BUTTON)).click();
        Assert.assertEquals("Ваш профиль успешно обновлён", driver.findElement(By.xpath(TEXT_ALIGN)).getText());
        driver.findElement(By.className(CLOSE_MODAL_BUTTON)).click();
        driver.findElement(By.xpath(GENDER_CHANGE_XPATH));
        driver.findElement(By.xpath(GENDER_CHANGE_XPATH)).click();
        //change gender
        driver.findElements(By.className(DROPDOWN_GENDER_CHANGE_CLASS)).get(0).click();
        Assert.assertEquals("Мужской", driver.findElement(By.xpath(GENDER_CHANGE_XPATH)).getText());
    }

    //Занятие 4
    @Test
    public void fillQuizNotComplete() throws InterruptedException {
        //login
        UserLogin("teleshovai@bk.ru", "Sploteam_2023");
        String actualUserName = driver.findElement(By.className(USER_NAME_HEADER_CLASS)).getText();
        Assert.assertEquals("Rina", actualUserName);
        //open user account
        driver.findElement(By.className(USER_NAME_HEADER_CLASS)).click();
        //go to quiz
        driver.findElement(By.xpath(QUIZ_BUTTON_XPATH)).click();

        //fill the quiz
        //1. city
        Assert.assertEquals("Санкт-Петербург", driver.findElement(By.className(QUIZ_CITY_CLASS)).getText());
        driver.findElement(By.className(QUIZ_CITY_CLASS)).click();
        driver.findElements(By.className(QUIZ_CITY_OPTIONS_CLASS)).get(1).click();
        Thread.sleep(2000);
        Assert.assertEquals("Москва", driver.findElement(By.className(QUIZ_CITY_CLASS)).getText());
        driver.findElement(By.xpath(REGION_TUSHINO_XPATH)).click();
        Assert.assertTrue(driver.findElement(By.xpath(REGION_TUSHINO_INPUT_XPATH)).isSelected());
        Assert.assertEquals("Тушино", driver.findElement(By.xpath(REGION_TUSHINO_XPATH)).getText());
        driver.findElement(By.xpath(REGION_CENTER_XPATH)).click();
        Assert.assertTrue(driver.findElement(By.xpath(REGION_CENTER_INPUT_XPATH)).isSelected());
        Assert.assertEquals("Центр", driver.findElement(By.xpath(REGION_CENTER_XPATH)).getText());
        driver.findElement(By.xpath(SPORTS_XPATH)).click();
        Assert.assertTrue(driver.findElement(By.xpath(SPORTS_INPUT_XPATH)).isSelected());
        Assert.assertEquals("Пляжный волейбол", driver.findElement(By.xpath(SPORTS_XPATH)).getText());
        driver.findElement(By.xpath(LEVEL1_XPATH)).click();
        Assert.assertTrue(driver.findElement(By.xpath(LEVEL_INPUT_XPATH)).isSelected());
        Assert.assertEquals("Лайт", driver.findElement(By.xpath(LEVEL1_XPATH)).getText());
        driver.findElement(By.xpath(SPORTS1_XPATH)).click();
        Assert.assertTrue(driver.findElement(By.xpath(SPORTS1_INPUT_XPATH)).isSelected());
        Assert.assertEquals("Стритбол", driver.findElement(By.xpath(SPORTS1_XPATH)).getText());
        driver.findElement(By.xpath(LEVEL1_XPATH)).click();
        Assert.assertTrue(driver.findElement(By.xpath(LEVEL_INPUT_XPATH)).isSelected());
        Assert.assertEquals("Лайт", driver.findElement(By.xpath(LEVEL1_XPATH)).getText());
    }

    private void UserLogin(String mail, String Sploteam_2023) {
        driver.findElement(By.className(SIGNIN_BUTTON_CLASS)).click();
        driver.findElement(By.xpath(EMAIL_INPUT_XPATH)).sendKeys(mail);
        driver.findElement(By.name(PASSWORD_INPUT_NAME)).sendKeys(Sploteam_2023);
        driver.findElement(By.xpath(LOGIN_BUTTON_XPATH)).click();
    }

    @Test
    public void checkGamesForEveryDateInCalender() {
        driver.findElement(By.xpath(MORE_GAMES_BUTTON_XPATH)).click();
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div/div[3]/div[1]/div[3]/div[1]")).click();
        driver.findElements(By.className("GrayRoundedSelect_dropdownItem__18xe6")).get(3).click();
        for (int i = 0; i < driver.findElements(By.className(DATE_CALENDER_CLASS)).size(); i++) {
            driver.findElements(By.className(DATE_CALENDER_CLASS)).get(i).click();
            if (!driver.findElements(By.className(GAME_CARD_CLASS)).isEmpty()) {
                Assert.assertTrue(driver.findElements(By.className(GAME_CARD_CLASS)).size() > 1);
            } else
            {
                Assert.assertEquals("Упс... на этот день нет ни одной игры, но Вы можете найти подходящую игру в другой день.", driver.findElement(By.className("SearchPage_tabPanelContainer__1Lxhs")).getText());
            }
        }
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div/div[3]/div[1]/div[3]/div[1]")).click();
        driver.findElements(By.className("GrayRoundedSelect_dropdownItem__18xe6")).get(1).click();
        for (int i = 0; i < driver.findElements(By.className(DATE_CALENDER_CLASS)).size(); i++) {
            driver.findElements(By.className(DATE_CALENDER_CLASS)).get(i).click();
            if (!driver.findElements(By.className(GAME_CARD_CLASS)).isEmpty()) {
                Assert.assertTrue(driver.findElements(By.className(GAME_CARD_CLASS)).size() > 1);
            } else
            {
                Assert.assertEquals("Упс... на этот день нет ни одной игры, но Вы можете найти подходящую игру в другой день.", driver.findElement(By.className("SearchPage_tabPanelContainer__1Lxhs")).getText());
            }
        }
    }
    // LastWork
    @Test
    public void depositPaymentCorrectSum() {
        //click sign in button
        driver.findElement(By.className(SIGNIN_BUTTON_CLASS)).click();
        //type email
        driver.findElement(By.xpath(EMAIL_INPUT_XPATH)).sendKeys("teleshovai@bk.ru");
        //type password
        driver.findElement(By.name(PASSWORD_INPUT_NAME)).sendKeys("Sploteam_2023");
        //click login
        driver.findElement(By.xpath(LOGIN_BUTTON_XPATH)).click();
        //assert user logged in
        String actualUserName = driver.findElement(By.className(USER_NAME_HEADER_CLASS)).getText();
        Assert.assertEquals("Rina", actualUserName);
        driver.findElement(By.className(AVATAR_CLASS)).click();
        String actualName = driver.findElement(By.xpath(PROFILE_NAME)).getText();
        Assert.assertEquals("Rina", actualName);
        driver.findElement(By.className(DEPOSIT_PAYMENT_BUTTON)).isDisplayed();
        driver.findElement(By.className(DEPOSIT_PAYMENT_BUTTON)).click();
        driver.findElement(By.className(DEPOSIT_PAYMENT_INPUT)).sendKeys("10");
        driver.findElement(By.xpath(PAYMENT_INPUT_BUTTON)).click();
        driver.findElement(By.className(CARD_DATE_CLASS)).isDisplayed();
        String actualText = driver.findElement(By.xpath(SEND_BUTTON_XPATH)).getText();
        Assert.assertEquals("Оплатить 10 ₽\n" +
                "← Вернуться в магазин", actualText);
        driver.findElement(By.xpath(BACK_BUTTON_XPATH)).click();
        String actualErrorMessage = driver.findElement(By.className("Common_pageTitle__6GYuY")).getText();
        Assert.assertEquals("Ошибка оплаты заказа", actualErrorMessage);
        driver.findElement(By.className(AVATAR_CLASS)).click();
        String actualName1 = driver.findElement(By.xpath(PROFILE_NAME)).getText();
        Assert.assertEquals("Rina", actualName1);
        driver.findElement(By.xpath(LOGOUT_BUTTON_XPATH)).click();

        //*[@id="root"]/div[2]/div/div/button
        //String strUrl = driver.getCurrentUrl("https://ishop.multicarta.ru/index.jsp?ORDERID=446793327&SESSIONID=70EE305E11708F8B7882A3DAC54CE4B6");
    }

    @Test
    public void depositPaymentKeysNull(){

        driver.findElement(By.className(SIGNIN_BUTTON_CLASS)).click();
        driver.findElement(By.xpath(EMAIL_INPUT_XPATH)).sendKeys("teleshovai@bk.ru");
        driver.findElement(By.name(PASSWORD_INPUT_NAME)).sendKeys("Sploteam_2023");
        driver.findElement(By.xpath(LOGIN_BUTTON_XPATH)).click();
        String actualUserName = driver.findElement(By.className(USER_NAME_HEADER_CLASS)).getText();
        Assert.assertEquals("Rina", actualUserName);
        driver.findElement(By.className(AVATAR_CLASS)).click();
        String actualName = driver.findElement(By.xpath(PROFILE_NAME)).getText();
        Assert.assertEquals("Rina", actualName);
        driver.findElement(By.className(DEPOSIT_PAYMENT_BUTTON)).isDisplayed();
        driver.findElement(By.className(DEPOSIT_PAYMENT_BUTTON)).click();
        driver.findElement(By.className(DEPOSIT_PAYMENT_INPUT)).sendKeys("0");
        String NewBalance= driver.findElement(By.className(DEPOSIT_PAYMENT_INPUT)).getAttribute("error");
        Assert.assertEquals(null, NewBalance);
        driver.findElement(By.xpath(DEPOSIT_PAYMENT_CLEAR_XPATH)).click();
        driver.findElement(By.xpath(LOGOUT_BUTTON_XPATH)).click();

    }

    @Test
    public void depositPaymentWrongSum() {
        //click sign in button
        driver.findElement(By.className(SIGNIN_BUTTON_CLASS)).click();
        //type email
        driver.findElement(By.xpath(EMAIL_INPUT_XPATH)).sendKeys("teleshovai@bk.ru");
        //type password
        driver.findElement(By.name(PASSWORD_INPUT_NAME)).sendKeys("Sploteam_2023");
        //click login
        driver.findElement(By.xpath(LOGIN_BUTTON_XPATH)).click();
        //assert user logged in
        String actualUserName = driver.findElement(By.className(USER_NAME_HEADER_CLASS)).getText();
        Assert.assertEquals("Rina", actualUserName);
        driver.findElement(By.className(AVATAR_CLASS)).click();
        String actualName = driver.findElement(By.xpath(PROFILE_NAME)).getText();
        Assert.assertEquals("Rina", actualName);
        driver.findElement(By.className(DEPOSIT_PAYMENT_BUTTON)).isDisplayed();
        driver.findElement(By.className(DEPOSIT_PAYMENT_BUTTON)).click();
        driver.findElement(By.className(DEPOSIT_PAYMENT_INPUT)).sendKeys("-");;
        driver.findElement(By.xpath(PAYMENT_INPUT_BUTTON)).click();
        Assert.assertTrue(driver.findElement(By.className(WRONG_SUM_MESSAGE_CLASS)).isDisplayed());
        String actualErrorMessage = driver.findElement(By.className(WRONG_SUM_MESSAGE_CLASS)).getText();
        Assert.assertEquals("Неверное значение", actualErrorMessage);
        driver.findElement(By.xpath(DEPOSIT_PAYMENT_CLEAR_XPATH)).click();
        Assert.assertTrue(driver.findElement(By.xpath(LOGOUT_BUTTON_XPATH)).isDisplayed());
        driver.findElement(By.xpath(LOGOUT_BUTTON_XPATH)).click();
    }

    @Test
    public void payGameFromDeposits() {
        driver.findElement(By.className(SIGNIN_BUTTON_CLASS)).click();
        //type email
        driver.findElement(By.xpath(EMAIL_INPUT_XPATH)).sendKeys("teleshovai@bk.ru");
        //type password
        driver.findElement(By.name(PASSWORD_INPUT_NAME)).sendKeys("Sploteam_2023");
        //click login
        driver.findElement(By.xpath(LOGIN_BUTTON_XPATH)).click();
        //assert user logged in
        String actualUserName = driver.findElement(By.className(USER_NAME_HEADER_CLASS)).getText();
        Assert.assertEquals("Rina", actualUserName);
        driver.findElement(By.className(AVATAR_CLASS)).click();
        String actualName = driver.findElement(By.xpath(PROFILE_NAME)).getText();
        Assert.assertEquals("Rina", actualName);
        //driver.findElement(By.xpath(MY_GAMES_XPATH)).isDisplayed();
        driver.findElement(By.xpath(MY_GAMES_XPATH)).click();
        driver.findElement(By.xpath(COOSE_GAMES_XPATH)).click();
        driver.findElement(By.xpath(FIRST_DATE_XPATH)).click();
        driver.findElement(By.className(GAME_CARD_CLASS)).isDisplayed();
        driver.findElement(By.className(JOIN_GAME_CLASS)).click();
        driver.findElement(By.className(JOIN_STEP2_GAME_CLASS)).click();
        driver.findElement(By.xpath(ODER_PAYMENT_BUTTON_XPATH)).click();
        String actualErrorMessage = driver.findElement(By.className(ERROR_MESSAGE_JOIN_CLASS)).getText();
        Assert.assertEquals("Недостаточно средств на личном счете", actualErrorMessage);
        driver.findElement(By.xpath(RETURN_BACK_BUTTON_XPATH)).click();
        driver.findElement(By.className(AVATAR_CLASS)).click();
        driver.findElement(By.xpath(LOGOUT_BUTTON_XPATH)).click();
    }
//Locators

    public static final String CREATE_GAMES_BUTTON_XPATH = "//*[@id=\"root\"]/div[2]/section[3]/div/div[2]/a";
    public static final String COMING_GAMES_TITLE_CLASS = "all-games__title";
    public static final String HEADER_FAQ_CLASS = "FAQButton_button__v0fPl";
    public static final String ARENDA_BUTTON_XPATH = "//*[@id=\"root\"]/div[2]/section[4]/div/div[2]/a";
    public static final String LOCATION_BUTTON_CLASS = "location__default";
    public static final String LOCATION_TITLE_CLASS = "location__default";
    public static final String BASKETBALL_BUTTON_CLASS = "games-list__item_basketball";
    public static final String BASKETBALL_TITLE_CLASS = "games-list__item_basketball";
    public static final String COOPERATION_TITLE_XPATH = "//*[@id=\"root\"]/div[2]/section[5]/div/h2";
    public static final String CAMPS_BUTTON_CLASS = "CampsButton_button__2sQY1";
    public static final String CAMPS_LOGO_CLASS = "CampsButton_img__3CoXw";
    public static final String CAMPS_TITLE_CLASS = "CampsButton_textWrapper__33gm5";
    public static final String EMAIL_INPUT_XPATH = "/html/body/div[3]/div/div/div/div[2]/form/div[1]/input";
    public static final String LOGIN_BUTTON_XPATH = "/html/body/div[3]/div/div/div/div[2]/form/button";
    public static final String PASSWORD_INPUT_XPATH = "/html/body/div[4]/div/div/div/div[2]/form/div[2]/input";
    public static final String SIGNIN_BUTTON_CLASS = "header__signIn";
    public static final String WRONG_EMAIL_LOGIN_MESSAGE_CLASS = "vertical-align: inherit;";
    public static final String PASSWORD_INPUT_NAME = "password";
    public static final String WRONG_PASSWORD_MESSEGE_CLASS = "form_error__2xL0z";
    public static final String AUTORIZATION_FORM_XPATH = "/html/body/div[3]/div/div";
    public static final String USER_NAME_HEADER_CLASS = "profileText__name";
    public static final String LOGOUT_BUTTON_XPATH = "//*[@id=\"root\"]/div[2]/div/div[3]/div[1]/div/div[8]";
    public static final String AVATAR_CLASS = "UserBlock_initials__9jNSe";
    public static final String NAME_XPATH = "//*[@id=\"root\"]/div[2]/div/div[3]/div[2]/div/div/div[2]/div[1]/b";
    public static final String PROFILE_NAME = "//*[@id=\"root\"]/div[2]/div/div[3]/div[2]/div/div/div[2]/div[1]/b";
    public static final String PROFILE_EMAIL = "//*[@id=\"root\"]/div[2]/div/div[3]/div[2]/div/div/div[2]/div[3]/span[2]";
    public static final String ACCOUNT_BUTTON_XPATH = "//*[@id=\"root\"]/div[2]/div/div[3]/div[2]/div/div/div[3]/div[3]/button";
    public static final String MY_ACCOUNT_TITLE_XPATH = "//*[@id=\"root\"]/div[2]/div/div[3]/div[2]/div/div/div[3]/div[1]";
    public static final String CHANGE_PROFILE_BUTTON_XPATH = "//*[@id=\"root\"]/div[2]/div/div[3]/div[2]/div/div/div[2]/div[4]";
    public static final String USER_NAME_INPUT = "//*[@id=\"root\"]/div[2]/div/div[3]/div/div[2]/form/div[1]/input";
    public static final String SAVE_BUTTON = "//*[@id=\"root\"]/div[2]/div/div[3]/div/div[2]/form/button";
    public static final String TEXT_ALIGN = "/html/body/div[3]/div/div/p";
    public static final String CLOSE_MODAL_BUTTON = "modal__close";
    public static final String DROPDOWN_CITY_OPTION_CLASS = "location__item";
    public static final String HEADER_CITY_CLASS = "location__default";
    public static final String FIRST_DATE_XPATH = "//*[@id=\"root\"]/div[2]/div[1]/div/div[1]/div/div[2]/span[2]";
    public static final String FILTER_BY_ARENA_XPATH = "//*[@id=\"root\"]/div[2]/div[1]/div/div[3]/div[1]/div[1]";
    public static final String DROPDOWN_ARENA_OPTION_CLASS = "GrayRoundedSelect_dropdownItem__18xe6";
    public static final String ARENA_ON_GAME_CARD_CLASS = "EventCard_eventTypeRow__arena__3ljYS";
    public static final String MORE_GAMES_BUTTON_XPATH = "//*[@id=\"root\"]/div[2]/section[2]/div/div[3]/a";
    public static final String DROPDOWN_LEVEL_PLAYS = "GrayRoundedSelect_controlValue__2EqXC";
    public static final String LEVEL_XPATH = "//*[@id=\"root\"]/div[2]/div[1]/div/div[3]/div[1]/div[3]/div/div";
    public static final String FILTER_PLAYS_XPATH = "//*[@id=\"root\"]/div[2]/div[1]/div/div[3]/div[1]/div[3]/div[1]";

    public static final String FILTER_BY_EVENTS_XPATH = "//*[@id=\"root\"]/div[2]/div[1]/div/div[3]/div[1]/div[4]";
    public static final String DROPDOWN_EVENTS_OPTION_CLASS = "GrayRoundedSelect_dropdownItem__18xe6 GrayRoundedSelect_itemSelected__g-sTB";
    public static final String ARENA_ON_EVENT_CLASS = "GrayRoundedSelect_dropdownItem__18xe6";
    public static final String FILTER_BY_KIND_SPORTS_XPATH ="//*[@id=\"root\"]/div[2]/div[1]/div/div[3]/div[1]/div[2]";

    public static final String DROPDOWN_ALL_SPORTS_CLASS ="GrayRoundedSelect_dropdownItem__18xe6";
    public static final String QUIZ_BUTTON_XPATH = "//*[@id=\"root\"]/div[2]/div/div[3]/div[1]/div/div[4]";
    public static final String QUIZ_CITY_CHANGE_XPATH = "//*[@id=\"root\"]/div[2]/div/div[3]/div[2]/div/div/div[1]/div";
    public static final String DROPDOWN_QUIZ_CITY_CHANGE_CLASS = "Select_dropdownItem__2T2FU";
    public static final String DROPDOWN_QUIZ_CITY_CLASS = "Select_dropdown__300bN";
    public static final String SAVE_BUTTON_XPATH ="//*[@id=\"root\"]/div[2]/div/div[3]/div[2]/div/div/button";
    public static final String WRONG_QUIZ_MESSAGE_CLASS="Quiz_quiz__errorBlock__I9tK3";
    public static final String GENDER_CHANGE_XPATH="//*[@id=\"root\"]/div[2]/div/div[3]/div/div[2]/form/div[2]/div/div[2]";
    public static final String DROPDOWN_GENDER_CHANGE_CLASS="Select_dropdownItem__3GhCz";
    // public static final String QUIZ_BUTTON_XPATH = "//*[@id=\"root\"]/div[2]/div/div[3]/div[1]/div/div[4]";
    public static final String QUIZ_CITY_CLASS = "Select_select__2JwMt";
    public static final String QUIZ_CITY_OPTIONS_CLASS ="Select_dropdownItem__2T2FU";
    public static final String REGION_TUSHINO_XPATH = "//*[@id=\"root\"]/div[2]/div/div[3]/div[2]/div/div/div[2]/div[1]/div/label[2]/span[2]";
    public static final String REGION_TUSHINO_INPUT_XPATH ="//*[@id=\"root\"]/div[2]/div/div[3]/div[2]/div/div/div[2]/div[1]/div/label[2]/input";
    public static final String REGION_CENTER_XPATH="//*[@id=\"root\"]/div[2]/div/div[3]/div[2]/div/div/div[2]/div[1]/div/label[9]/span[2]";
    public static final String REGION_CENTER_INPUT_XPATH="//*[@id=\"root\"]/div[2]/div/div[3]/div[2]/div/div/div[2]/div[1]/div/label[9]/input";
    public static final String SPORTS_XPATH="//*[@id=\"root\"]/div[2]/div/div[3]/div[2]/div/div/div[2]/div[2]/div/label[1]/span[2]";
    public static final String SPORTS_INPUT_XPATH="//*[@id=\"root\"]/div[2]/div/div[3]/div[2]/div/div/div[2]/div[2]/div/label[1]/input";
    public static final String LEVEL1_XPATH="//*[@id=\"root\"]/div[2]/div/div[3]/div[2]/div/div/div[2]/div[3]/div/label[1]/span[2]";
    public static final String LEVEL_INPUT_XPATH= "//*[@id=\"root\"]/div[2]/div/div[3]/div[2]/div/div/div[2]/div[3]/div/label[1]/input";
    public static final String DATE_CALENDER_CLASS = "NavLink_navLink__3OIyY";
    public static final String GAME_CARD_CLASS = "Card_card__2jlaB";
    public static final String CLEAR_NAME_INPUT="//*[@id=\"root\"]/div[2]/div/div[3]/div/div[2]/form/div[1]/span";
    public static final String PLAY_LEVEL_MEDIUM_INRUT_XPATH="//*[@id=\"root\"]/div[2]/div/div[3]/div[2]/div/div/div[2]/div[3]/div/label[2]/input";
    public static final String SPORTS1_INPUT_XPATH ="//*[@id=\"root\"]/div[2]/div/div[3]/div[2]/div/div/div[2]/div[2]/div/label[6]/input";
    public static final String SPORTS1_XPATH="//*[@id=\"root\"]/div[2]/div/div[3]/div[2]/div/div/div[2]/div[2]/div/label[6]/span[2]";

    //
    public static final String DEPOSIT_PAYMENT_BUTTON="ProfileCard_depositPayment__1R088";
    public static final String DEPOSIT_PAYMENT_INPUT="FormInput_formInputField__1HTcx";
    public static final String DEZP_PAYMENT_BUTTON="//*[@id=\"root\"]/div[2]/div/div[3]/div[2]/div/div/div[3]/div[3]/form[1]/div/input";
    public static final String WRONG_SUM_MESSAGE_CLASS= "form_error__2xL0z";
    public static final String DEPOSIT_PAYMENT_CLEAR_XPATH ="//*[@id=\"root\"]/div[2]/div/div[3]/div[2]/div/div/div[3]/div[3]/form[1]/div/span";
    public static final String PAYMENT_INPUT_BUTTON ="//*[@id=\"root\"]/div[2]/div/div[3]/div[2]/div/div/div[3]/div[3]/form[1]/button";
    public static final String MY_GAMES_XPATH="//*[@id=\"root\"]/div[2]/div/div[3]/div[1]/div/div[2]";
    public static final String COOSE_GAMES_XPATH="//*[@id=\"root\"]/div[2]/div/div[3]/div[2]/div/div/div/div/div[1]";
    public static final String FREE_SLOTS_CS="EventCard_freeSlots__value__3DspA";
    public static final String FREE_SLOTS_XPATH="//*[@id=\"root\"]/div[2]/div[1]/div/div[4]/div/div/div[1]/div/div[3]/div[2]/span[1]";
    public static final String CARD_DATE_CLASS="card";

    public static final String SEND_BUTTON_XPATH= "//*[@id=\"mF\"]/div[3]";
    public static final String BACK_BUTTON_XPATH="//*[@id=\"mF\"]/div[3]/a";
    public static final String FREE_SLOTS_CLAS="EventCard_freeSlots__16SMe";
    public static final String JOIN_GAME_CLASS ="EventCard_freeSlots__value__3DspA";
    public static final String JOIN_STEP2_GAME_CLASS="ViewEventPage_btn__1w8j1";
    public static final String ODER_PAYMENT_BUTTON_XPATH ="//*[@id=\"root\"]/div[2]/div/div[2]/div/div[4]/span[3]/form[1]/button[2]";
    public static final String ERROR_MESSAGE_JOIN_CLASS="form_error__2xL0z";
    public static final String RETURN_BACK_BUTTON_XPATH="//*[@id=\"root\"]/div[2]/div/div[1]/span";

}
