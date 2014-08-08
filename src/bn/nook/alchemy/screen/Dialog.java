package bn.nook.alchemy.screen;

import bn.nook.alchemy.utils.TestManager;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by Alecs on 25.06.2014.
 */
public class Dialog extends ScreenHelper {

    private static int currentScreenId = Constant.ScreenId.UNKNOWN_SCREEN;
    private DialogScreen dialog = null;
    ActionOnPushNotificationDialog actionOnPushNotificationDialog;
    public Dialog(AppiumDriver driver) {
        super(driver);
        dialog = new DialogScreen();
        actionOnPushNotificationDialog = new ActionOnPushNotificationDialog();
    }

    public static class Constant{
        public static class ScreenId{
            public static final int UNKNOWN_SCREEN = -1;
            public static final int POPUP_VALIDATION_ERRORS = 0;
            public static final int DIALOG_NO_INTERNET = 1;
            public static final int SELECT_COUNTRY_ERROR_DIALOG = 2;
            public static final int PUSH_NOTIFICATIONS_DIALOG = 3;
            public static final int WRONG_EMAIL_OR_PASSWORD_DIALOG = 4;
        }

        public static class Text{
            public static final String TRY_AGAIN_BUTTON = "Try again";
            public static final String NO_INTERNET_TITLE = "Let's get connected";
            public static final String OK_BUTTON = "OK";
            public static final String SELECT_COUNTRY_TEXT = "Please select your country in order to proceed.";
            public static final String PUSH_NOTIFICANIONS = "Nook would like to send you push Notifications";
            public static final String ALLOW_BTN = "Allow";
            public static final String DO_NOT_ALLOW_BTN = "Don't Allow";
            public static final String WRONG_CREDENTIAL = "Please enter a valid email address or password and attempt to register again. Code(AD1101)";
        }
    }

    public void start() {
        switch (detectedScreen()) {

            case Constant.ScreenId.POPUP_VALIDATION_ERRORS:
                TestManager.log("Login screen : \"Validation errors\" popup:" ,false);
                dialog.closeTryAgainDialog();
                break;

            case Constant.ScreenId.DIALOG_NO_INTERNET:
                TestManager.log("Dialog no Internet:" ,false);
                dialog.clickOkBtn();
                break;

            case Constant.ScreenId.SELECT_COUNTRY_ERROR_DIALOG:
                TestManager.log("Select Country Error Dialog:" ,false);
                dialog.clickOkBtn();
                break;
            case Constant.ScreenId.PUSH_NOTIFICATIONS_DIALOG:
                TestManager.log("Push notifications dialog:" ,false);
                randomActionPushNotificationsDialog();
                break;
            case Constant.ScreenId.WRONG_EMAIL_OR_PASSWORD_DIALOG:
                TestManager.log("\"Wrong Email or Password\" dialog:" ,false);
                dialog.clickOkBtn();
                break;
        }
    }

    @Override
    public int detectedScreen() {
        return currentScreenId;
    }

    public boolean isVisible() {
        if(isExistPopupValidationErrors() ||
                isExistDialogNoInternet() ||
                isExistSelectCountryDialog()||
                isExistPushNotificationsDialog())
            return true;
        return false;
    }

    private class ActionOnPushNotificationDialog{

        private void clickAllowButton(){
            By textAllow = By.name(Constant.Text.ALLOW_BTN);
            WebElement allowBtn = waitForElement(textAllow, driver, 60);
            if (allowBtn == null){
                TestManager.log("\"Allow\" button was not found!");
            }else {
                allowBtn.click();
                TestManager.log("Click \"Allow\" button.",false);
            }
        }

        private void clickDontAllowButton(){
            By textDontAllow = By.name(Constant.Text.DO_NOT_ALLOW_BTN);
            WebElement dontAllowBtn = waitForElement(textDontAllow, driver, 60);
            if (dontAllowBtn == null){
                TestManager.log("\"Don't Allow\" button was not found!");
            }else {
                dontAllowBtn.click();
                TestManager.log("Click \"Don't Allow\" button.",false);
            }
        }
    }

    private boolean isExistDialogNoInternet(){

        if (isWebElementPresent(By.name(Constant.Text.NO_INTERNET_TITLE))){
            currentScreenId = Constant.ScreenId.DIALOG_NO_INTERNET;
            return true;
        }else return false;
    }

    private boolean isExistSelectCountryDialog(){

        if (isWebElementPresent(By.name(Constant.Text.SELECT_COUNTRY_TEXT))){
            currentScreenId = Constant.ScreenId.SELECT_COUNTRY_ERROR_DIALOG;
            return true;
        }else return false;
    }

    private boolean isExistPopupValidationErrors(){

        if (isWebElementPresent(By.name(Constant.Text.TRY_AGAIN_BUTTON))){
            currentScreenId = Constant.ScreenId.POPUP_VALIDATION_ERRORS;
            return true;
        }else return false;
    }

    private boolean isExistPushNotificationsDialog(){

        if(isWebElementPresent(By.name(Constant.Text.PUSH_NOTIFICANIONS))){
            currentScreenId = Constant.ScreenId.PUSH_NOTIFICATIONS_DIALOG;
            return true;
        }else  return false;
    }

    private boolean isExistWrongCredentialdDialog(){

        if (isWebElementPresent(By.name(Constant.Text.WRONG_CREDENTIAL))){
            currentScreenId = Constant.ScreenId.WRONG_EMAIL_OR_PASSWORD_DIALOG;
            return true;
        }else  return false;
    }

    private class DialogScreen {

        private void closeTryAgainDialog(){
            By textTryAgainBtn = By.name(Constant.Text.TRY_AGAIN_BUTTON);
            WebElement tryAgainBtn = waitForElement(textTryAgainBtn, driver, 60);
            if(tryAgainBtn == null){
                TestManager.log("\"Try again\" button was not found");
            }else {
                tryAgainBtn.click();
                TestManager.log("Click \"Try Again\" button" ,false);
            }
        }

        private void clickOkBtn(){
            By textOkBtn = By.name(Constant.Text.OK_BUTTON);
            WebElement okBtn = waitForElement(textOkBtn, driver, 60);
            if (okBtn == null){
                TestManager.log("\"OK\" button was not found");
            }else okBtn.click();
            TestManager.log("Click on the \"OK\" button." ,false);
        }
    }

    public void randomActionPushNotificationsDialog(){
        switch (getRandomNumber(2)){
            case 0: actionOnPushNotificationDialog.clickAllowButton();
                break;
            case 1: actionOnPushNotificationDialog.clickDontAllowButton();
                break;
        }
    }
}




