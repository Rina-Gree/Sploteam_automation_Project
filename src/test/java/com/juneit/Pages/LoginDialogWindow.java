package com.juneit.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class LoginDialogWindow {
    private WebDriver driver;
    public LoginDialogWindow(WebDriver driver) {
        this.driver = driver;
    }

    public void LoginAccount() {
        driver.findElement(By.xpath(INPUT_FORM_LOGIN_XPATH)).sendKeys("teleshovai@bk.ru");
        driver.findElement(By.xpath(INPUT_FORM_EMAIL_XPATH)).sendKeys("Splot2025");
        driver.findElement(By.xpath(SIGNIN_BUTTON_XPATH)).click();
    }






    public static final String INPUT_FORM_LOGIN_XPATH = "/html/body/div[3]/div/div/div/div[2]/form/div[1]/input";

    public static final String INPUT_FORM_EMAIL_XPATH = "/html/body/div[3]/div/div/div/div[2]/form/div[2]/input";
    public static final String SIGNIN_BUTTON_XPATH = "/html/body/div[3]/div/div/div/div[2]/form/button";




}