package bn.nook.alchemy.screen;

import bn.nook.alchemy.utils.TestManager;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by Alecs on 25.06.2014.
 */
public class Home extends ScreenHelper {

    private static int currentScreenId = Constant.ScreenId.UNKNOWN_SCREEN;
    private static int currentShopScreenId;
    ActionHomeScreen actionHomeScreen = null;
    ActionShopScreen actionShopScreen = null;
    private String nameOfCurrentTab = null;

    public Home(AppiumDriver driver) {
        super(driver);
        actionHomeScreen = new ActionHomeScreen();
        actionShopScreen = new ActionShopScreen();
    }

    public static class Constant{
        public static class ScreenId{
            public static final int UNKNOWN_SCREEN = -1;
            public static final int MY_HOME_SCREEN = 0;
            public static final int SHOP_SCREEN = 1;
            //            public static final int TEMPORARY_SCREEN = 2;
            public static final int QUICK_READS_SCREEN = 3;
//            public static final int LIBRARY_SCREEN = 4;
        }

        public static class ShopScreenID{
            public static final int BOOKS_SCREEN = 1;
            public static final int MAGAZINES_Screen = 2;
            public static final int KIDS_SCREEN = 3;
            public static final int NEWSPAPERS = 4;
        }

        public static class Id{
            public static final String VIEW_PAGER = "bn.ereader:id/pager";
            public static final String UP_BUTTON = "bn.ereader:id/up";
        }

        public static class Text{
            public static final String HOME = "Home";
            public static final String SHOP = "Shop";
            public static final String CLASS_NAME_TABS_BAR = "android.widget.TextView";
            public static final String TEMPORARY_SCREEN = "Temporary";
            public static final String QUICK_READS_SCREEN = "Quick Reads";
            public static final String LIBRARY_SCREEN = "Library";
        }

        public static class ContentDescID{
            public static final String BOOKS = "Books";
            public static final String MAGAZINES = "Magazines";
            public static final String KIDS = "Kids";
            public static final String NEWSPAPERS = "Newspapers";
        }
    }

    @Override
    public int detectedScreen(){
        return currentScreenId;
    }

    @Override
    public boolean isVisible() {
        WebElement tab =  getWidestChild(Constant.Text.CLASS_NAME_TABS_BAR);
        if(tab == null)
            return false;

        nameOfCurrentTab = tab.getText();
        if (isExistMyHomeScreen() ||
                isExistShop() ||
//                isExistTemporaryScreen() ||
//                isExistLibraryScreen()||
                isExistQuickReadsScreen()){

            return true;
        }

        return false;
    }

    public void start() {
        switch (detectedScreen()) {
            case Constant.ScreenId.MY_HOME_SCREEN:
                TestManager.log("\"My Home\" screen:" ,false);
                randomActionMyHomeScreen();
                break;
            case Constant.ScreenId.SHOP_SCREEN:
                TestManager.log("\"Shop\" screen:",false);
                randomActionShopScreen();
                break;
//            case Constant.ScreenId.TEMPORARY_SCREEN:
//                TestManager.log("\"Temporary\" screen is detected");
//                randomActionTemporaryScreen();
//                break;
            case Constant.ScreenId.QUICK_READS_SCREEN:
                TestManager.log("\"Quck Reads\" screen:");
                randomActionQuickReadsScreen();
                break;
//            case Constant.ScreenId.LIBRARY_SCREEN:
//                TestManager.log("\"Library\" screen is detected");
//                randomActionLibraryScreen();
//                break;
        }
    }

    private class ActionHomeScreen {

        private void swipeRightLeft(){
            By idPager = By.id(Constant.Id.VIEW_PAGER);
            WebElement pager = waitForElement(idPager, driver, 60);
            if(pager == null){
                TestManager.log("\"Pager\" element was not found!");
            }else {
                swipe(pager, ((int) (Math.random() * 2) - 1));
                TestManager.log("Swipe left/right." ,false);
            }
        }

        private void swipeUpDown(){
            By idPager = By.id(Constant.Id.VIEW_PAGER);
            WebElement pager = waitForElement(idPager, driver, 60);
            if(pager == null){
                TestManager.log("\"Pager\" element was not found!");
            }else {
                swipe(pager, ((int)(Math.random()*2) +1));
                TestManager.log("Swipe Up/Down." ,false);
            }
        }

        private void openSideBar(){
            By idSideBarButton = By.className("android.widget.LinearLayout");
            WebElement sideBurButton = waitForElement(idSideBarButton, 0 , driver, 60);
            if (sideBurButton == null){
                TestManager.log("\"Side bar\" button was not found");
            }else {
                sideBurButton.click();
                TestManager.log("Click on the \"Side bar\" button." ,false);
            }
        }
    }

    private class ActionMyHomeScreen{

    }

    private class ActionShopScreen {

        private void tapOnBooks() {
            WebElement booksBtn = waitForElement(By.id(Constant.ContentDescID.BOOKS), driver, 60);
            if (booksBtn == null) {
                TestManager.log("\"Books\" button was not found!");
            } else {
                booksBtn.click();
                TestManager.log("Click on the \"Books\" button.", false);
            }
        }

        private void tapOnMagazines() {
            WebElement magazinesBtn = waitForElement(By.id(Constant.ContentDescID.MAGAZINES), driver, 60);
            if (magazinesBtn == null) {
                TestManager.log("\"Magazines\" button was not found!");
            } else {
                magazinesBtn.click();
                TestManager.log("Click on the \"Magazines\" button.", false);
            }
        }

        private void tapOnKids() {
            WebElement kidsBtn = waitForElement(By.id(Constant.ContentDescID.KIDS), driver, 60);
            if (kidsBtn == null) {
                TestManager.log("\"Kids\" button was not found!");
            } else {
                kidsBtn.click();
                TestManager.log("Click on the \"Kids\" button.", false);
            }
        }

        private void tapOnNewspapers() {
            WebElement newspapersBtn = waitForElement(By.id(Constant.ContentDescID.KIDS), driver, 60);
            if (newspapersBtn == null) {
                TestManager.log("\"Newspapers\" button was not found!");
            } else {
                newspapersBtn.click();
                TestManager.log("Click on the \"Newspapers\" button.", false);
            }
        }

        public class ActionShopBooksScreen {

        }

        public class ActionShopMagazinesScreen {

        }

        public class ActionShopKidsScreen {

        }

        public class ActionShopNewspapersScreen {

        }

        //todo
        private boolean isExistShopBookScreen() {
            if (isWebElementPresent(By.id("NYT Bestsellers"))) {
                currentShopScreenId = Constant.ShopScreenID.BOOKS_SCREEN;
                return true;
            } else return false;
        }



        private class ActionTemporaryScreen {

        }

        private class ActionQuckReadsScreen {

        }

        private class ActionLibraryScreen {

        }
    }

    public boolean isExistMyHomeScreen(){
        if(Constant.Text.HOME.equals(nameOfCurrentTab)){
            currentScreenId = Constant.ScreenId.MY_HOME_SCREEN;
            return true;
        }else return false;
    }

    public boolean isExistShop(){
        if (Constant.Text.SHOP.equals(nameOfCurrentTab)) {
            currentScreenId = Constant.ScreenId.SHOP_SCREEN;
            return true;
        }else return false;
    }

//    public boolean isExistTemporaryScreen(){
//        if (Constant.Text.TEMPORARY_SCREEN.equals(nameOfCurrentTab)) {
//            currentScreenId = Constant.ScreenId.TEMPORARY_SCREEN;
//            return true;
//        }else return false;
//    }

    public boolean isExistQuickReadsScreen(){
        if(Constant.Text.QUICK_READS_SCREEN.equals(nameOfCurrentTab)){
            currentScreenId = Constant.ScreenId.QUICK_READS_SCREEN;
            return true;
        }else return false;
    }

//    public boolean isExistLibraryScreen(){
//        if(Constant.Text.LIBRARY_SCREEN.equals(nameOfCurrentTab)){
//            currentScreenId = Constant.ScreenId.LIBRARY_SCREEN;
//            return true;
//        }else return false;
//    }

    public void randomActionMyHomeScreen(){
        switch (getRandomNumber(3)){
            case 0:
                actionHomeScreen.swipeRightLeft();
                break;
            case 1:
                actionHomeScreen.swipeUpDown();
                break;
            case 2:
                actionHomeScreen.openSideBar();
                break;
        }
    }

    public void randomActionShopScreen(){
        switch (3){
            case 0:
                actionHomeScreen.swipeRightLeft();
                break;
            case 1:
                actionHomeScreen.swipeUpDown();
                break;
            case 2:
                actionHomeScreen.openSideBar();
                break;
            case 3:
                actionShopScreen.tapOnBooks();
                break;
        }
    }

    public void randomActionTemporaryScreen(){
        switch (getRandomNumber(3)){
            case 0:
                actionHomeScreen.swipeRightLeft();
                break;
            case 1:
                actionHomeScreen.swipeUpDown();
                break;
            case 2:
                actionHomeScreen.openSideBar();
                break;
        }
    }

    public void randomActionQuickReadsScreen(){
        switch (getRandomNumber(3)){
            case 0:
                actionHomeScreen.swipeRightLeft();
                break;
            case 1:
                actionHomeScreen.swipeUpDown();
                break;
            case 2:
                actionHomeScreen.openSideBar();
                break;
        }
    }

    public void randomActionLibraryScreen(){
        switch (getRandomNumber(3)){
            case 0:
                actionHomeScreen.swipeRightLeft();
                break;
            case 1:
                actionHomeScreen.swipeUpDown();
                break;
            case 2:
                actionHomeScreen.openSideBar();
                break;
        }
    }

}
