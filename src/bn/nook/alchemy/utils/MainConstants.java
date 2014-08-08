package bn.nook.alchemy.utils;

/**
 * Created by Alecs on 29.07.2014.
 */
public class MainConstants {

    public static final String CONFIGURATION_FILE_PATH = "config.properties";
    public static int DEFAULT_TIMEOUT = 5000;


    public static class Config{
        public static final String LOGIN_PROPERTY = "LOGIN";
        public static final String APP_ID_PROPERTY = "APP_ID";
        public static final String PASSWORD_PROPERTY = "PASSWORD";
        public static final String REPORT_FOLDER_PROPERTY = "REPORT_FOLDER";
    }

    public static class EnumScreen{
        public static final int UNKNOWN_SCREEN = -1;
        public static final int OOBE_SCREEN = 0;
        public static final int DIALOG_SCREEN = 1;
        public static final int HOME_SCREEN = 2;
        public static final int SIDE_BAR_SCREEN = 3;
        public static final int SETTINGS_SCREEN = 4;
        public static final int LIBRARY_SCREEN = 5;
    }
    public static class DriverSwitcher{
        public static final String NATIVE = "NATIVE_APP";
        public static final String WEBVIEW = "WEBVIEW";
    }
}
