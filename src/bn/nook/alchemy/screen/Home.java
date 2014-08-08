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
    ActionHomeScreen actionHomeScreen = null;
    private String nameOfCurrentTab = null;

    public Home(AppiumDriver driver) {
        super(driver);
        actionHomeScreen = new ActionHomeScreen();
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

    }

    private class ActionTemporaryScreen {

    }

    private class ActionQuckReadsScreen{

    }

    private class ActionLibraryScreen{

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
