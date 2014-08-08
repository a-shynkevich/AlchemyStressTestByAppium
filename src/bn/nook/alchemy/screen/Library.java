package bn.nook.alchemy.screen;

import bn.nook.alchemy.utils.TestManager;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by Alecs on 08.08.2014.
 */
public class Library extends ScreenHelper {

    private static int currentScreenId = Constant.ScreenId.UNKNOWN_SCREEN;
    ActionLibraryScreen actionLibraryScreen;
    public Library(AppiumDriver driver) {
        super(driver);
        actionLibraryScreen = new ActionLibraryScreen();
    }

    public static class Constant{
        public static class ScreenId{
            public static final int UNKNOWN_SCREEN = -1;
            public static final int LIBRARY_SCREEN = 0;
            public static final int LIBRARY_FILTER_SCREEN =1;
        }
        public static class Id{
            public static final String LIBRARY_BAR_TITLE = "android:id/action_bar_title";
            public static final String FILTER_BUTTON = "bn.ereader:id/action_filter_drawer";
            public static final String FILTER_DRAWER = "bn.ereader:id/filter_drawer";
        }
    }

    @Override
    public int detectedScreen(){
        return currentScreenId;
    }

    @Override
    public boolean isVisible() {
        if (isExistLibraryScreen() ||
                isExistLibraryFilterScreen()){

            return true;
        }
        return false;
    }

    public void start() {
        switch (detectedScreen()) {
            case Constant.ScreenId.LIBRARY_SCREEN:
                TestManager.log("Library main screen:" ,false);
                randomActionLibraryScreen();
                break;
            case  Constant.ScreenId.LIBRARY_FILTER_SCREEN:
                TestManager.log("Library filter screen:", false);
                randomActionLibraryFilterScreen();
                break;
        }
    }

    private class ActionLibraryScreen{

        public void openSideBarMenu(){
            By idSideBar = By.id(Constant.Id.LIBRARY_BAR_TITLE);
            WebElement libBarTitle = waitForElement(idSideBar, driver, 60);
            if(libBarTitle == null){
                TestManager.log("\"Menu\" button was not found!");
            }else {
                libBarTitle.clear();
                TestManager.log("Click on the \"Library bar title\".", false);
            }
        }

        public void openFilterMenu(){
            By idFilter = By.id(Constant.Id.FILTER_BUTTON);
            WebElement filterDrawer = waitForElement(idFilter, driver, 60);
            if (filterDrawer == null){
                TestManager.log("\"Filter\" button was not found!");
            }else {
                filterDrawer.click();
                TestManager.log("Click on the \"Filter\" menu button.", false);
            }
        }

    }

    private class ActionLibraryFilterScreen{

    }

    public boolean isExistLibraryScreen(){
        if(isWebElementPresent(By.id(Constant.Id.LIBRARY_BAR_TITLE))){
            if (driver.findElement(By.id(Constant.Id.LIBRARY_BAR_TITLE)).getText().equals("Library")){
                currentScreenId = Constant.ScreenId.LIBRARY_SCREEN;
                return true;
            }
            return false;
        }else return false;
    }

    public boolean isExistLibraryFilterScreen(){
        if (isWebElementPresent(By.id(Constant.Id.FILTER_DRAWER))){
            currentScreenId = Constant.ScreenId.LIBRARY_FILTER_SCREEN;
            return true;
        }else return false;
    }

    public void randomActionLibraryScreen(){
        switch (getRandomNumber(2)){
            case 0 :
                actionLibraryScreen.openSideBarMenu();
                break;
            case 1:
                actionLibraryScreen.openFilterMenu();
                break;
        }
    }

    public void randomActionLibraryFilterScreen(){
        switch (getRandomNumber(2)){
            case 0 :
                actionLibraryScreen.openSideBarMenu(); //the same buttons
                break;
            case 1:
                actionLibraryScreen.openFilterMenu(); //the same buttons
                break;
        }
    }

}
