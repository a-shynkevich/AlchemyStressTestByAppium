package bn.nook.alchemy.utils;

import bn.nook.alchemy.screen.*;
import io.appium.java_client.AppiumDriver;
import net.bugs.testhelper.TestHelper;

public class ScreenDefinition {
    private static AppiumDriver driver = null;
    TestHelper testHelperh = TestManager.getInstance().getTestHelper();

    public ScreenDefinition(AppiumDriver driver){
        this.driver = driver;
    }

    public int detectedScreen() {
        TestManager.log("Search of screen: ");
//        testHelperh.updateViews();
        if(oobeDetected())
            return MainConstants.EnumScreen.OOBE_SCREEN;
        if(dialogDetected())
            return MainConstants.EnumScreen.DIALOG_SCREEN;
        if (homeDetected())
            return MainConstants.EnumScreen.HOME_SCREEN;
        if(sideBarDetected())
            return MainConstants.EnumScreen.SIDE_BAR_SCREEN;
        if(settingsDetected())
            return MainConstants.EnumScreen.SETTINGS_SCREEN;
        if (libraryDetected())
            return MainConstants.EnumScreen.LIBRARY_SCREEN;
        return MainConstants.EnumScreen.UNKNOWN_SCREEN;
    }

    private boolean oobeDetected(){
        Oobe oobe = new Oobe(driver);
        TestManager.log("Check oobe screens; ", false);
        return oobe.isVisible();
    }

    private boolean dialogDetected(){
        Dialog dialogScreen = new Dialog(driver);
        TestManager.log("Check Dialogs; ", false);
        return dialogScreen.isVisible();
    }

    public boolean homeDetected(){
        Home home = new Home(driver);
        TestManager.log("Check Home screens; ", false);
        return home.isVisible();
    }

    private boolean sideBarDetected(){
        SideBar sideBar = new SideBar(driver);
        TestManager.log("Check SideBar screen; ", false);
        return sideBar.isVisible();
    }

    private boolean settingsDetected(){
        Settings  settings = new Settings(driver);
        TestManager.log("Check Settings screen; ", false);
        return settings.isVisible();
    }

    private boolean libraryDetected(){
        Library library = new Library(driver);
        TestManager.log("Check Library screen; ", false);
        return library.isVisible();
    }
}
