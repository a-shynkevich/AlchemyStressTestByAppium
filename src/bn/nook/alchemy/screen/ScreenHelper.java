package bn.nook.alchemy.screen;

import bn.nook.alchemy.utils.TestManager;
import io.appium.java_client.AppiumDriver;
import net.bugs.testhelper.TestHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * Created by Alecs on 29.07.2014.
 */
public class ScreenHelper implements IScreenHelper {
    private static final int SWIPE_LEFT = -1;
    private static final int SWIPE_RIGHT = 0;
    private static final int SWIPE_DOWN = 1;
    private static final int SWIPE_UP = 2;

    protected AppiumDriver driver = null;
    protected TestHelper testHelper;

    public ScreenHelper(AppiumDriver driver){
        this.driver = driver;
        testHelper = TestManager.getInstance().getTestHelper();
    }

    protected WebElement waitForElement(final By by,  WebDriver driver, long timeoutInSec) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSec);
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            if (element.isDisplayed()) {
                return element;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    protected WebElement waitForElement(final By by, int index,  WebDriver driver, long timeoutInSec) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSec);
        try {
            List <WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
            WebElement element =  elements.get(index);
            if (element.isDisplayed()) {
                return element;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    protected WebElement getWebElementByIndex(final By by, int index){
        List <WebElement> elements = driver.findElements(by);
        WebElement element = elements.get(index);
        return element;
    }

    protected boolean isWebElementPresent(By by){
        try {
            WebElement element = driver.findElement(by);
            if(element.isDisplayed()) {
                return true;
            }
        }catch (Exception e){
            return false;
        }
        return false;
    }

    public WebElement getNativeViewElement(WebDriver driver, By parentBy, By childBy, int indexChild){
        WebElement webElement = null;
        WebElement parent = driver.findElement(parentBy);
        if(parent.isDisplayed()){
            List<WebElement> list = parent.findElements(childBy);
            if(list.size() > 0){
                webElement = list.get(indexChild);
            }
        }
        return webElement;
    }

    public WebElement getNativeViewElementByIndex(WebDriver driver, By parentBy, By childBy, int indexChild){
        WebElement webElement = null;
        WebElement parent = driver.findElement(parentBy);
        List<WebElement> list = parent.findElements(childBy);
        for(int i = 0; i < list.size(); i++){
            webElement = list.get(i);
            TestManager.log(webElement.getText());
        }
        return webElement;
    }
    public WebElement getNativeViewElementByText(WebDriver driver, By parentBy, By childBy, String text) {
        WebElement webElement = null;
        WebElement parent = driver.findElement(parentBy);
        List<WebElement> list = parent.findElements(childBy);

        for (int i = 0; i < list.size(); i++) {
            webElement = list.get(i);
            TestManager.log("TEXT: " + webElement.getText());
            if (webElement.getText().equals(text)) {
                return webElement;
            }
        }
        return null;
    }

    public void start(){}

    public int detectedScreen() {
        return 0;
    }

    public boolean isVisible() {
        return false;
    }

    public int getRandomNumber(int maxNumber){
//        Random random = new Random();
//        return random.nextInt(maxNumber);
        return (int) (Math.random() * maxNumber);
    }

    private Point getCenter(WebElement element) {

        Point upperLeft = element.getLocation();
        Dimension dimensions = element.getSize();
        return new Point(upperLeft.getX() + dimensions.getWidth()/2, upperLeft.getY() + dimensions.getHeight()/2);
    }

    public boolean swipe(WebElement webElement, int swipeSide){
        return swipe(webElement, swipeSide, 0);
    }

    public boolean swipe(WebElement webElement, int swipeSide, int distance) {
        if (webElement == null || !webElement.isDisplayed())
            return false;
        int width = webElement.getSize().getWidth();
        int height = webElement.getSize().getHeight();
        int startPoint;

        Point point = webElement.getLocation();
        int x = point.getX();
        int y = point.getY();

        switch (swipeSide) {
            case SWIPE_LEFT:
                startPoint = x+ width - 10;
                if (distance == 0)
                    distance = x+ width - width + 10;
                else
                    distance = startPoint - distance;
                driver.swipe(startPoint, height/2, distance, height/2, 1000);
                break;
            case SWIPE_RIGHT:
                startPoint = x+ 10;
                if (distance == 0)
                    distance = x + width - 10;
                else
                distance = startPoint + distance;
                driver.swipe(startPoint, height/2, distance, height/2, 1000);
                break;
            case SWIPE_UP:
                startPoint = y + height-10;
                if (distance == 0)
                    distance = y + 10;
                else
                distance = startPoint -distance;
                driver.swipe(width/2, startPoint, width/2, distance, 1000);
                break;
            case SWIPE_DOWN:
                startPoint = y + 10;
                if (distance == 0)
                    distance = y+ height - 10;
                else
                distance = startPoint + distance;
                driver.swipe(width/2, startPoint, width/2, distance, 1000);
                break;
        }
        return true;
    }

    public WebElement findElement (By byElement){
        WebElement element = null;
        try {
            element = driver.findElement(byElement);
        }catch (Exception e) {
            return null;
        }
        if(!element.isDisplayed()){
            return null;
        }
        return element;
    }

    public WebElement getWidestChild(String className){
        WebElement element = null;
        int width = 0;

        List<WebElement> elements = driver.findElements(By.className(className));
        for (int i = 0; i<elements.size(); i++){
            int tabWidth = elements.get(i).getSize().getWidth();
            if (tabWidth > width) {
                width = tabWidth;
                element = elements.get(i);
            }
        }
        return element;
    }
    public void closeKeyboard(){
        try {
            driver.hideKeyboard();
        }catch (Exception e){}
    }

}
