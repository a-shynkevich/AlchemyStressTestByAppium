package bn.nook.alchemy.screen;

import bn.nook.alchemy.utils.TestManager;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by Alecs on 29.07.2014.
 */
public class Oobe extends ScreenHelper {
    private static int currentScreenId = Constant.ScreenId.UNKNOWN_SCREEN;
    private ActionOnMainScreen actionOnMainScreen = null;
    private ActionOnMainWithCountryList actionOnMainWithCountryList = null;
    private ActionOnLoginScreen actionOnLoginScreen = null;
    private ActionOnPasswordScreen actionPasswScreen = null;
    private ActionProgressBar actionProgressBar = null;


    public Oobe(AppiumDriver driver) {
        super(driver);
        actionOnMainScreen = new ActionOnMainScreen();
        actionOnMainWithCountryList = new ActionOnMainWithCountryList();
        actionOnLoginScreen = new ActionOnLoginScreen();
        actionPasswScreen = new ActionOnPasswordScreen();
        actionProgressBar = new ActionProgressBar();
    }

    public static class Constant{

        public static class Id{

            public static final String LOGIN = "bn.ereader:id/sign_in";
            public static final String EMAIL_FIELD = "bn.ereader:id/account";
            public static final String FIRST_NAME = "bn.ereader:id/first_name";
            public static final String LAST_NAME = "bn.ereader:id/last_name";
            public static final String SEQURITY_ANSWER = "bn.ereader:id/security_answer";
            public static final String SEQURITY_QUESTION = "bn.ereader:id/security_question_spinner";
            public static final String PSSWD_FIELD = "bn.ereader:id/password";
            public static final String NEXT_BTN = "bn.ereader:id/next";
            public static final String SIGN_UP_BUTTN = "bn.ereader:id/sign_in";
            public static final String EXPLORE_APP_BUTN = "bn.ereader:id/start_reading";
            public static final String VIEW_PAGER = "bn.ereader:id/pager";
            public static final String COUNTRY_SPINNER = "bn.ereader:id/cor_spinner";
            public static final String BACK_BTN = "bn.ereader:id/back";
            public static final String CLOSE_BTN = "bn.ereader:id/close";
            public static final String SHOW_PSSW_CHECK_BOX = "bn.ereader:id/show_password";
        }

        public  static class Account{
            public static final String EMAIL_ADDRESS = TestManager.getInstance().getLogin();
            public static final String PASSWORD = TestManager.getInstance().getPassword();
        }

        public static class ScreenId {
            public static final int UNKNOWN_SCREEN = -1;
            public static final int MAIN_SCREEN = 0;
            public static final int MAIN_WITH_COUNTRY_LIST = 1;
            public static final int LOGIN_SCREEN = 2;
            public static final int PASSWORD_SCREEN = 3;
            public static final int PROGRESS_BAR = 4;

        }

        public static class Text{
            public static final String LOGIN = "LOG IN";
            public static final String EXPLORE_APP_BUTN = "Explore the app";
            public static final String UNITED_STATES = "United States";
            public static final String UNITED_KINGDOM = "United Kingdom";
            public static final String USE_SOCIAL_ACC = "Use a social account";
        }

        public static class ClassName{
            public static final String PROGRESS_BAR = "android.widget.ProgressBar";
        }

    }



    public void start(){
        switch (detectedScreen()){
            case Constant.ScreenId.MAIN_SCREEN:
                TestManager.log("Main screen:" ,false);
                randomActionMainScreen();
                break;
            case Constant.ScreenId.MAIN_WITH_COUNTRY_LIST:
                TestManager.log("Main screen with opened country list:" ,false);
                randomActionMainCountryList();
                break;
            case Constant.ScreenId.LOGIN_SCREEN:
                TestManager.log("Log in screen:" ,false);
                randomActionLoginScreen();
                break;

            case Constant.ScreenId.PASSWORD_SCREEN:
                TestManager.log("Password screen:" ,false);
                randomActionPasswordField();
                break;
            case Constant.ScreenId.PROGRESS_BAR:
                TestManager.log("Progress bar:" ,false);
                actionProgressBar.waitEndOfLoading();
                break;
        }
    }


    public int detectedScreen() {
        return currentScreenId;
    }

    public boolean isVisible() {
        if(isExistMainScreen() ||
                isExistLoginScreen() ||
                isExistMainScreenWithCountryList() ||
                isExistPasswordScreen()||
                isExistProgressBarScreen())
            return true;
        return false;
    }

    private boolean isExistMainScreen(){
        if (isWebElementPresent(By.id(Constant.Id.EXPLORE_APP_BUTN))){
            currentScreenId = Constant.ScreenId.MAIN_SCREEN;
            return true;
        }else  return false;
    }

    private boolean isExistMainScreenWithCountryList(){

            if (isWebElementPresent(By.name(Constant.Text.UNITED_STATES))){
            currentScreenId = Constant.ScreenId.MAIN_WITH_COUNTRY_LIST;
            return true;
        }else return false;
    }

    private boolean isExistLoginScreen(){

        if (isWebElementPresent(By.id(Constant.Id.EMAIL_FIELD))
                && !isWebElementPresent(By.id(Constant.Id.PSSWD_FIELD))){
            currentScreenId = Constant.ScreenId.LOGIN_SCREEN;
            return true;
        }
        return false;
    }

    private boolean isExistPasswordScreen(){

        if (isWebElementPresent(By.id(Constant.Id.PSSWD_FIELD))){
            currentScreenId = Constant.ScreenId.PASSWORD_SCREEN;
            return true;
        }else return false;
    }

    private boolean isExistProgressBarScreen(){
        if (isWebElementPresent(By.className(Constant.ClassName.PROGRESS_BAR))){
            currentScreenId = Constant.ScreenId.PROGRESS_BAR;
            return true;
        }
        return false;
    }

    public class ActionOnMainScreen{

        public void signIn(){
            By idSignBtn = By.name(Constant.Text.LOGIN);
            WebElement signBtn =waitForElement(idSignBtn, driver, 60);
            if (signBtn == null){
                TestManager.log("\"LOG IN\" button was not found!");
            }else {
                signBtn.click();
                TestManager.log("LOG IN click." ,false);
            }
        }
        private void swipeImages(){
            By idPager = By.id(Constant.Id.VIEW_PAGER);
            WebElement pager = waitForElement(idPager, driver, 60);
            if(pager == null){
                TestManager.log("\"Pager\" element was not found!");
            }else {
                swipe(pager, ((int) (Math.random() * 2) - 1));
                TestManager.log("SWIPE." ,false);
            }
        }

        private void tapOnExplroeAppBtn(){
            By idExploreAppBtn = By.id(Constant.Id.EXPLORE_APP_BUTN);
            WebElement exploreAppBtn = waitForElement(idExploreAppBtn, driver , 60);
            if(exploreAppBtn == null){
                TestManager.log("\"Explore the app\" button was not found!");
            }else {
                exploreAppBtn.click();
                TestManager.log("Click on the \"EXPLORE BUTTON\"." ,false);
            }
        }

        public void clickOnCountrySpinner(){
            By idCountrySpinner = By.id(Constant.Id.COUNTRY_SPINNER);
            WebElement countrySpinner = waitForElement(idCountrySpinner, driver, 60);
            if(countrySpinner == null){
                TestManager.log("\"Country Spinner\" was not found!");
            }else {
                countrySpinner.click();
                TestManager.log("Click on the \"Country dropdown list\"." ,false);
            }
        }
    }

    private class ActionOnMainWithCountryList{

        private void chooseUSA() {
            By textUSA = By.name(Constant.Text.UNITED_STATES);
            WebElement USA = waitForElement(textUSA, driver, 60);
            if (USA == null) {
                TestManager.log("\"United States\" was not found");
            } else {
                USA.click();
                TestManager.log("Choose \"United States\"." ,false);
            }
        }

        private void chooseUK(){
            By textUK = By.name(Constant.Text.UNITED_KINGDOM);
            WebElement UK = waitForElement(textUK, driver , 60);
            if(UK == null){
                TestManager.log("\"United Kingdom\" was not found");
            }else {
                UK.click();
                TestManager.log("Choose \"United Kingdom\"." ,false);
            }
        }
    }

    private class ActionOnLoginScreen{

        private void enterEmail(){
            By idEmail = By.id(Constant.Id.EMAIL_FIELD);
            WebElement email = waitForElement(idEmail, driver, 60);
            if (email == null){
                TestManager.log("Email field was not found");
            }else {
                email.clear();
                email.sendKeys(Constant.Account.EMAIL_ADDRESS);
                TestManager.log("Enter Email address " + Constant.Account.EMAIL_ADDRESS ,false);
            }
        }

        private void clickBackButton(){
            By idBackBtn = By.id(Constant.Id.BACK_BTN);
            WebElement back = waitForElement(idBackBtn, driver, 60);
            if (back == null){
                TestManager.log("Back button was not found");
            }else {
                back.click();
                TestManager.log("Click on the \"Back\" button." ,false);
            }
        }

        private void cliclCloseButton(){
            By idCloseBtn = By.id(Constant.Id.CLOSE_BTN);
            WebElement close = waitForElement(idCloseBtn, driver, 60);
            if (close == null){
                TestManager.log("Close button was not found");
            }else{
                close.click();
                TestManager.log("Click on the \"Close\" button.",false);
            }
        }

        private void clickNextButton(){
            By idNextBtn = By.id(Constant.Id.NEXT_BTN);
            WebElement nextBtn = waitForElement(idNextBtn, driver, 60);
            if (nextBtn == null){
                TestManager.log("Next button was not found");
            }else{
                nextBtn.click();
                TestManager.log("Click on the \"Next\" button." ,false);
            }
        }
    }

    private class ActionOnPasswordScreen{

        private void enterPassword(){
            By idPassword = By.id(Constant.Id.PSSWD_FIELD);
            WebElement passwordField = waitForElement(idPassword, driver, 60);
            if(passwordField == null){
                TestManager.log("\"Password\" field was not found.");
            }else {
                passwordField.clear();
                passwordField.sendKeys(Constant.Account.PASSWORD);
                TestManager.log("Enter password: " + Constant.Account.PASSWORD ,false);
            }
        }

        private void showPassword(){
            By idCheckBox = By.id(Constant.Id.SHOW_PSSW_CHECK_BOX);
            WebElement checkBox = waitForElement(idCheckBox, driver, 60);
            if (checkBox == null){
                TestManager.log("\"Show password\" checkbox was not found");
            }else{
                checkBox.click();
                TestManager.log("Click on the \"Show password\" checkbox." ,false);
            }
        }

        private void clickSignUpBtn(){
            By idSignUpBtn = By.id(Constant.Id.SIGN_UP_BUTTN);
            WebElement signUpBtn = waitForElement(idSignUpBtn, driver, 60);
            if (signUpBtn == null){
                TestManager.log("\"Sign up\" button was not found");
            }else{
                signUpBtn.click();
                TestManager.log("Click on the \"SIGN UP\" button.",false);
            }
        }
    }

    private class ActionProgressBar{

        private void waitEndOfLoading(){
            TestManager.log("Wait end of loading.",false);
            while(isWebElementPresent(By.className(Constant.ClassName.PROGRESS_BAR)));
        }
    }

    public void randomActionMainScreen(){

        switch (getRandomNumber(4)){
            case 0: {
                actionOnMainScreen.signIn();
                break;
            }
            case 1:
                actionOnMainScreen.swipeImages();
                break;
            case 2:
                actionOnMainScreen.tapOnExplroeAppBtn();
//                TestManager.log("Click on EXPLORE BUTTON");
                break;
            case 3:
                actionOnMainScreen.clickOnCountrySpinner();
                break;
        }
    }

    public void randomActionMainCountryList(){
        switch (getRandomNumber(2)){
            case 0:
                actionOnMainWithCountryList.chooseUK();
                break;
            case 1:
                actionOnMainWithCountryList.chooseUSA();
                break;
        }
    }

    public void randomActionLoginScreen(){
        closeKeyboard();
        switch (getRandomNumber(6)){
            case 0:
                actionOnLoginScreen.clickBackButton();
                break;
            case 1:
                actionOnLoginScreen.cliclCloseButton();
                break;
            case 2:
                actionOnLoginScreen.enterEmail();
                break;
            case 3:
                actionOnLoginScreen.clickNextButton();
                break;
            case 4:
                actionOnLoginScreen.enterEmail();
                closeKeyboard();
                actionOnLoginScreen.clickNextButton();
                break;
            case 5:
                actionOnLoginScreen.enterEmail();
                closeKeyboard();
                actionOnLoginScreen.clickNextButton();
                actionPasswScreen.enterPassword();
                closeKeyboard();
                actionPasswScreen.clickSignUpBtn();
                break;
        }
    }

    public void randomActionPasswordField(){
        closeKeyboard();
        switch (getRandomNumber(6)){
            case 0:
                actionPasswScreen.enterPassword();
                break;
            case 1:
                actionOnLoginScreen.clickBackButton(); //the same element as on the Login screen
                break;
            case 2:
                actionOnLoginScreen.cliclCloseButton(); //the same element as on the Login screen
                break;
            case 3:
                actionPasswScreen.clickSignUpBtn(); //the same element as on the Login screen
                break;
            case 4:
                actionPasswScreen.showPassword();
                break;
            case 5:
                actionPasswScreen.enterPassword();
                closeKeyboard();
                actionPasswScreen.clickSignUpBtn();
                break;
        }
    }
}
