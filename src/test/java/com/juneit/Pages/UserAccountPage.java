package com.juneit.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.List;




public class UserAccountPage {
    private WebDriver driver;
    public UserAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getNotificationsGroup() {
        List<WebElement> getNotificationsGroup = driver.findElements(By.className(NOTIFICATION_LIST_GROUP_CLASS));
        return getNotificationsGroup;
    }
    public WebElement assertNamePage() {
        WebElement assertNamePage = driver.findElement(By.xpath(NAME_ACCOUNT_PAGE_XPATH));
        return assertNamePage;
    }
    public WebElement getNewNotificationCount() {
        WebElement getNewNotificationCount = driver.findElement(By.xpath(NEW_NOTIFICATION_XPATH));
        return getNewNotificationCount;
    }
    public  int getcount(){
        List<WebElement> notifications = driver.findElements(By.className(NOTIFICATION_LIST_CLASS));
        return notifications.size();
    }
    public  void printNotification(){
        List<WebElement> notifications = driver.findElements(By.className(NOTIFICATION_LIST_CLASS));
        System.out.println("Количество уведомлений" + notifications.size());
    }
    public WebElement CheckBox() {
        WebElement CheckBox = driver.findElement(By.xpath(CHECK_BOX_XPATH));
        return CheckBox;
    }
    public boolean isCheckBoxSelected() {; // найти чек-бокс на странице
        return CheckBox().isSelected();
    }
    public void toggleCheckBox() {;  //выбрать чек-бокс
        CheckBox().click();
    }
    public WebElement deleteNotificationButton() {  //удалить
        WebElement deleteNotificationButton = driver.findElement(By.xpath(NOTIFICATION_DELETE_XPATH));
        return deleteNotificationButton;
    }
    public WebElement readNotificationButton() {  //прочитать
        WebElement readNotificationButton = driver.findElement(By.xpath(NOTIFICATION_READ_XPATH));
        return readNotificationButton;
    }
    public WebElement changeAllNotificationButton() {  //выбрать все
        WebElement changeAllNotificationButton = driver.findElement(By.xpath(NOTIFICATION_ALL_READ_XPATH));
        return changeAllNotificationButton;
    }

    public WebElement messageNotFondNotification() { //сообщение, что уведомлений нет
        WebElement messageNotFondNotification = driver.findElement(By.xpath(NOTIFICATION_NOT_FOUND_XPATH));
        return messageNotFondNotification;
    }
    public WebElement eventsGroupNotification() { //События
        WebElement eventsGroupNotification = driver.findElement(By.xpath(EVENTS_NOTIFICATION_XPATH));
        return eventsGroupNotification;
    }
    public List<WebElement> checkBoxNotificationListElement() { //
       List<WebElement> checkBoxNotificationListElement = driver.findElements(By.className(CHECK_BOX_LIST_LIST_CLASS));
        return checkBoxNotificationListElement;
    }
    public WebElement getAvatarAreaUserProfile(){
        WebElement avatarAreaUserProfile = driver.findElement(By.className(AVATAR_AREA_USER_PROFILE_CLASS));
        return avatarAreaUserProfile;}

    public WebElement getScreenshotAs(OutputType<File> file){
        WebElement getScreenshotAs = driver.findElement(By.className(AVATAR_AREA_USER_PROFILE_CLASS));
        return getScreenshotAs;}
    public WebElement getChooseFileForAvatarInput(){
        WebElement chooseFileForAvatarInput = driver.findElement(By.cssSelector(CHOOSE_FILE_FOR_AVATA_CSS));
        return chooseFileForAvatarInput;
    }

    public WebElement getConfirmChooseFileForAvatarButton(){
        WebElement confirmChooseFileForAvatarInput = driver.findElement(By.xpath(CONFIRM_CHOOSE_FILE_FOR_AVATAR_XPATH));
        return confirmChooseFileForAvatarInput;
    }
    public WebElement getChangeAvatarButton(){
        WebElement changeAvatarButton = driver.findElement(By.className(CHANGE_OR_DELETE_AVATAR_CLASS));
        return changeAvatarButton;}
    public WebElement getChangeAvatarCropMessage(){
        WebElement changeAvatarCropMessage = driver.findElement(By.className(CHANGE_AVATAR_CROP_MESSAGE_CLASS));
        return changeAvatarCropMessage;
    }

    public WebElement getAvatarAreaDialogWindow(){
        WebElement avatarAreaDialogWindow = driver.findElement(By.className(AVATAR_AREA_DIALOG_WINDOW_CLASS));
        return avatarAreaDialogWindow;
    }

    public WebElement getCloseChangeAvatarButton(){
        WebElement closeChangeAvatarButton = driver.findElement(By.className(CLOSE_CHANGE_AVATAR_BUTTON_CLASS));
        return closeChangeAvatarButton;
    }


    public WebElement getSaveEditProfileButton(){
        WebElement saveEditProfileButton = driver.findElement(By.xpath(SAVE_EDIT_USER_PROFILE_BUTTON_XPATH));
        return saveEditProfileButton;
    }

    public WebElement getEditUserProfileButton(){
        WebElement userProfileButton= driver.findElement(By.className(EDIT_USER_PROFILE_BUTTON_CLASS));
        return userProfileButton;
    }

    public WebElement getCloseSaveEditProfileMessageButton(){
        WebElement closeClearNameInProfileButton = driver.findElement(By.className(CLOSE_SAVE_EDIT_USER_PROFILE_MESSAGE_CLASS));
        return closeClearNameInProfileButton;
    }
    public WebElement logout(){
        WebElement logout = driver.findElement(By.xpath(LOGOUT_XPATH));
        return logout;
    }

    public static final String EDIT_USER_PROFILE_BUTTON_CLASS = "OrangeLink_orangeLink__34ZRK";
    public static final String CLOSE_SAVE_EDIT_USER_PROFILE_MESSAGE_CLASS = "modal__close";
    public static final String  SAVE_EDIT_USER_PROFILE_BUTTON_XPATH = "//*[@id=\"root\"]/div[2]/div/div[3]/div/div[2]/form/button";
    public static final String AVATAR_AREA_USER_PROFILE_CLASS = "cropper-drag-box";
    public static final String CHANGE_OR_DELETE_AVATAR_CLASS = "OrangeLink_orangeLink__34ZRK";
    public static final String AVATAR_AREA_DIALOG_WINDOW_CLASS = "cropper-drag-box";
    public static final String CLOSE_CHANGE_AVATAR_BUTTON_CLASS = "modal__close";
    public static final String CHANGE_AVATAR_CROP_MESSAGE_CLASS = "avatar_avatarCropHelpText__2HBWi";
    public static final String CONFIRM_CHOOSE_FILE_FOR_AVATAR_XPATH = "//button[contains(text(), 'Подтвердить')]";
    public static final String CHOOSE_FILE_FOR_AVATA_CSS = "input[type='file']";
    public static final String NAME_ACCOUNT_PAGE_XPATH = "//*[@id=\"root\"]/div[2]/div/div[2]";
    public static final String NOTIFICATION_LIST_GROUP_CLASS = "NotificationGroupsNav_categories__1Qsqm";
    public static final String NEW_NOTIFICATION_XPATH = "//*[@id=\"root\"]/div[2]/div/div[3]/div[2]/div/div/div[1]/div/div[2]/div[2]";

    public static final String NOTIFICATION_LIST_CLASS = "Notification_notification___WK9I";

    public static final String CHECK_BOX_XPATH = "//*[@id=\"root\"]/div[2]/div/div[3]/div[2]/div/div/div[2]/div[2]/div[1]/div[1]/label/span";
    public static final String NOTIFICATION_DELETE_XPATH = "//*[@id=\"root\"]/div[2]/div/div[3]/div[2]/div/div/div[2]/div[1]/div[2]/div[2]";

    public static final String NOTIFICATION_READ_XPATH = "//*[@id=\"root\"]/div[2]/div/div[3]/div[2]/div/div/div[2]/div[1]/div[2]/div[1]";
    public static final String NOTIFICATION_ALL_READ_XPATH = "//*[@id=\"root\"]/div[2]/div/div[3]/div[2]/div/div/div[2]/div[1]/div[1]/label";
    public static final String NOTIFICATION_NOT_FOUND_XPATH = "//*[@id=\"root\"]/div[2]/div/div[3]/div[2]/div/div/div[2]/div";
    public static final String EVENTS_NOTIFICATION_XPATH = "//*[@id=\"root\"]/div[2]/div/div[3]/div[2]/div/div/div[1]/div/div[3]/div[1]";

    public static final String CHECK_BOX_LIST_LIST_CLASS = "FlatOrangeCheckbox_checkbox__3PiR2";

    public static final String LOGOUT_XPATH = "//*[@id=\"root\"]/div[2]/div/div[3]/div[1]/div/div[8]";


   // public static final String AVATAR_AREA_USER_PROFILE_CLASS = "ProfileAvatar_avatar__tog10";



}