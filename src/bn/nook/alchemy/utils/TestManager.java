package bn.nook.alchemy.utils;

import io.appium.java_client.AppiumDriver;
import net.bugs.testhelper.TestHelper;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

/**
 * Created by Alecs on 29.07.2014.
 */
public class TestManager {


    private static TestManager instanceTestManager = null;
    private static TestHelper mTestHelper = null;
    private static String mDeviceId = null;
    private static String mLogin = null;
    private static String mPassword = null;
    private static String mPathToRootFolder = null;
    private static net.bugs.testhelper.helpers.PropertiesManager mPropertiesManager;
    private static String timeLog;
    private static long rowNumber = 0;
    public static AppiumDriver driver = null;
    private static String mAppID = null;
    private static String mDeviceHW = null;
    private static String mDeviceOS = null;

    public static void start(){
        ConfigManager configManager;
        System.out.println("setUp");
        configManager = new ConfigManager();
        File appDir = new File(configManager.getProperty(ConfigurationParametersEnum.ANDROID_APP_DIR.name()));
        File app = new File(appDir, configManager.getProperty(ConfigurationParametersEnum.ANDROID_APP.name()));
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME,"");
        capabilities.setCapability("platformName", configManager.getProperty(ConfigurationParametersEnum.ANDROID_PLATFORM_NAME.name()));
        capabilities.setCapability("deviceName", configManager.getProperty(ConfigurationParametersEnum.ANDROID_DEVICE_ID.name()));
        capabilities.setCapability("platformVersion", configManager.getProperty(ConfigurationParametersEnum.ANDROID_PLATFORM_VERSION.name()));
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", configManager.getProperty(ConfigurationParametersEnum.ANDROID_APP_PACKAGE.name()));
        capabilities.setCapability("appActivity", configManager.getProperty(ConfigurationParametersEnum.ANDROID_APP_ACTIVITY.name()) );
        try {
            driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void stop(){
        if(driver != null){
            driver.close();
        }
    }

    public static void log(String message){
        log(message, true);
    }

    public static void log(String message, boolean isNewLine){
        String fileName = mDeviceHW +" (" + mDeviceOS + ").txt";
        PrintWriter out = null;
        try {
            timeLog = new SimpleDateFormat("d.MM.YYYY HH:MM:s ").format(Calendar.getInstance().getTime());
            out = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
            if (isNewLine) {
                out.print("\n" + ++rowNumber + ". " + timeLog + message);
            }else
                out.print(" "+message);
        }catch (IOException e) {
            System.err.println(e);
        }finally {
            if (out != null) {
                out.close();
            }
        }

        System.out.println(message);
    }


    private TestManager(String deviceId){
        mTestHelper = new TestHelper(deviceId);
        mDeviceHW = mTestHelper.getHwDevice();
        mDeviceOS = mTestHelper.getOsDevice();
        mPropertiesManager = mTestHelper.propertiesManager;
        readProperties();
    }

    public static TestManager getInstance(final String deviceId){
        mDeviceId = deviceId;
        if(instanceTestManager == null){
            synchronized (TestManager.class){
                if(instanceTestManager == null)
                    instanceTestManager = new TestManager(mDeviceId);
            }
        }
        return instanceTestManager;
    }

    public static TestManager getInstance(){
        return getInstance(mDeviceId);
    }

    public void runIntent(String intent){
        mTestHelper.executeShellCommand(" am start -n " + intent, mTestHelper.defaultCallBack);
    }

    public TestHelper getTestHelper() {
        return mTestHelper;
    }

    private void readProperties(){
        PropertiesManager.init();
    }

    public static int generateRandom(int n){
        if(n == 0)
            return 0;
        Random random = new Random();
        return Math.abs(random.nextInt()) % n;
    }

    private static String getAppID(){
        if(mAppID == null)
            mAppID = mPropertiesManager.getProperty(MainConstants.Config.APP_ID_PROPERTY);
        return mAppID;
    }

    public String getLogin(){
        if(mLogin == null)
            mLogin = mPropertiesManager.getProperty(MainConstants.Config.LOGIN_PROPERTY);
        return mLogin;
    }

    public String getPassword(){
        if(mPassword == null)
            mPassword = mPropertiesManager.getProperty(MainConstants.Config.PASSWORD_PROPERTY);
        return mPassword;
    }

    public static int getRandomInt(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    public static void takeScreenshot(){
        mTestHelper.takeScreenshot("screenshot"+".png");
    }

    public static String getPathToReportFolder(){
        if(mPathToRootFolder == null)
            mPathToRootFolder = mPropertiesManager.getProperty(MainConstants.Config.REPORT_FOLDER_PROPERTY);
        return mPathToRootFolder;
    }

}
