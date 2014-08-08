package bn.nook.alchemy;

import bn.nook.alchemy.screen.*;
import bn.nook.alchemy.utils.MainConstants;
import bn.nook.alchemy.utils.ScreenDefinition;
import bn.nook.alchemy.utils.TestManager;
public class TestRunner {

    private static TestManager testManager;


    public static void runTest() throws InterruptedException {
        int countUnknownScreen = 0;
        ScreenDefinition screenDefinition = new ScreenDefinition(TestManager.driver);
        while(true){
//            TestManager.driver.switchTo().window(Constant.DriverSwitcher.NATIVE);
            switch (screenDefinition.detectedScreen()){
                case MainConstants.EnumScreen.OOBE_SCREEN:
                    TestManager.log("Oobe screen: ");
                    (new Oobe(TestManager.driver)).start();
                    break;
                case MainConstants.EnumScreen.DIALOG_SCREEN:
                    TestManager.log("Dialogs: ");
                    (new Dialog(TestManager.driver)).start();
                    break;
                case MainConstants.EnumScreen.HOME_SCREEN:
                    TestManager.log("Home screen: ");
                    (new Home(TestManager.driver)).start();
                    break;
                case MainConstants.EnumScreen.SIDE_BAR_SCREEN:
                    TestManager.log("Side Bar screen:");
                    (new SideBar(TestManager.driver)).start();
                    break;
                case MainConstants.EnumScreen.SETTINGS_SCREEN:
                    TestManager.log("Settings screen:");
                    (new Settings(TestManager.driver)).start();
                    break;
                case MainConstants.EnumScreen.LIBRARY_SCREEN:
                    TestManager.log("Library screen:");
                    (new Library(TestManager.driver)).start();
                    break;
                case MainConstants.EnumScreen.UNKNOWN_SCREEN:
                    TestManager.log("Unknown screen!");
                    countUnknownScreen++;
                    break;
            }
            if(countUnknownScreen > 5){
                testManager.takeScreenshot();
                System.out.println("BREAK");
                break;
            }
        }

    }


    public static void main(String[] args) throws InterruptedException {
        TestManager.start();
        runTest();
        TestManager.stop();
    }
}
