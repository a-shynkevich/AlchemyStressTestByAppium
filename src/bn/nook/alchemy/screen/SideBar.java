package bn.nook.alchemy.screen;

import bn.nook.alchemy.utils.TestManager;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by Alecs on 02.07.2014.
 */
public class SideBar extends ScreenHelper {
    private static int currentScreenId = Constant.ScreenId.UNKNOWN_SCREEN;
    ActionSideBar actionSideBar = null;

    public SideBar(AppiumDriver driver) {
        super(driver);
        actionSideBar = new ActionSideBar();
    }

    public static class Constant{
        public static class ScreenId{
            public static final int UNKNOWN_SCREEN = -1;
            public static final int SIDE_BAR = 0;
        }
        public static class Id{
            public static final String NAVIGATION_DRAWER = "bn.ereader:id/navigation_drawer";


        }
        public static class Text{
            public static final String MY_HOME = "My Home";
            public static final String SHOP = "Shop";
            public static final String QUICK_READS = "Quick Reads";
            public static final String LIBRARY = "Library";
            public static final String SETTINGS = "Settings";
        }
    }

    public void start(){
        switch(detectedScreen()){
            case Constant.ScreenId.SIDE_BAR:
                rundomActionSideBar();
                break;
        }
    }

    public int detectedScreen() {
        return currentScreenId;
    }

    @Override
    public boolean isVisible() {
        if (isExistSideBar())
            return true;
        return false;
    }

    private boolean isExistSideBar() {
        if (isWebElementPresent(By.id(Constant.Id.NAVIGATION_DRAWER))){
            currentScreenId = Constant.ScreenId.SIDE_BAR;
            return true;
        }
        return false;
    }

    private class ActionSideBar{

        private void openSettingsScreen(){
            By textSettings = By.name(Constant.Text.SETTINGS);
            WebElement settingsTab = waitForElement(textSettings, driver, 60);
            if(settingsTab == null){
                TestManager.log("\"Settings tab\" was not found.");
            }else {
                settingsTab.click();
                TestManager.log("clisk on the \"Settings tab\"." ,false);
            }
        }

        private void openMyHomeScreen(){
            By textMyHome = By.name(Constant.Text.MY_HOME);
            WebElement myHomeTab = waitForElement(textMyHome, driver, 60);
            if(myHomeTab == null){
                TestManager.log("\"My Home\" tab was not found.");
            }else {
                myHomeTab.click();
                TestManager.log("Click on the \"My home\" tab." ,false);
            }
        }

        private void openDiscoveryScreen(){
            By textDiscovery = By.name(Constant.Text.SHOP);
            WebElement discoveryTab = waitForElement(textDiscovery, driver, 60);
            if(discoveryTab == null){
                TestManager.log("\"Shop\" tab was not found.");
            }else {
                discoveryTab.click();
                TestManager.log("Click on the \"Shop\" tab." ,false);
            }
        }

        private void openQuickReadsScreen(){
            By textQuickReads = By.name(Constant.Text.QUICK_READS);
            WebElement quickReadsTab = waitForElement(textQuickReads, driver, 60);
            if(quickReadsTab == null){
                TestManager.log("\"Quick Reads\" tab was not found.");
            }else {
                quickReadsTab.click();
                TestManager.log("Click on the \"Quick Reads\" tab." ,false);
            }
        }

        private void openLibraryScreen(){
            By textlibrary = By.name(Constant.Text.LIBRARY);
            WebElement libraryTab = waitForElement(textlibrary, driver, 60);
            if(libraryTab == null){
                TestManager.log("\"Library\" tab was not found.");
            }else {
                libraryTab.click();
                TestManager.log("Click on the \"Library\" tab." ,false);
            }
        }
    }

    public void rundomActionSideBar(){
        switch (getRandomNumber(5)){
            case 0:
                actionSideBar.openSettingsScreen();
                break;
            case 1:
                actionSideBar.openMyHomeScreen();
                break;
            case 2:
                actionSideBar.openDiscoveryScreen();
                break;
            case 3:
                actionSideBar.openQuickReadsScreen();
                break;
            case 4:
                actionSideBar.openLibraryScreen();
                break;
        }
    }

}
