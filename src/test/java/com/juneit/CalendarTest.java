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
import java.time.*;
import java.time.format.DateTimeFormatter;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class CalendarTest<ExpectedDate> {
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
    public void assertTodayDateEqualsDateOfSBP(){
        ZonedDateTime now = ZonedDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String expectedTime = now.format(formatter);
        assertEquals(expectedTime,mainPage.getLocalTime().getText().substring(0,5));
    }
    @Test
    public void assertYearInFooter(){
        String footerYear = String.valueOf(Year.now().getValue());
        assertEquals(footerYear, mainPage.getFooterInfo().getText().substring(10));

    }
    @Test
    public void assertFirstSecondAndLastDatesForCreateGame(){
        mainPage.getCreateGameButton().click();
        eventsPage.getTodayDate().click();
        assertTrue(eventsPage.getTodayDate().isDisplayed());
        assertEquals("Сегодня", eventsPage.getTodayDate().getText());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d");
        String today = LocalDate.now().format(formatter);
        assertEquals("Сегодня", eventsPage.getDateOfCalendar().get(0).getText().substring(3));
        String tomorrow = LocalDate.now().plusDays(1).format(formatter);
        assertEquals(tomorrow, eventsPage.getDateOfCalendar().get(1).getText().substring(3));
        String lastDay = LocalDate.now().plusDays(13).format(formatter);
        assertEquals(lastDay, eventsPage.getDateOfCalendar().get(13).getText().substring(3));
    }
}
