import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BaseTest {
    private static final int WEB_DRIVER_WAIT_TIMEOUT;
    private static final String DEFAULT_BASE_URL;

    protected static WebDriver driver;
    protected static WebDriverWait driverWait;
    protected static JavascriptExecutor javascriptExecutor;

    // initialize static variables
    static {
        DEFAULT_BASE_URL = "https://www.demoblaze.com/";
        WEB_DRIVER_WAIT_TIMEOUT = 10; // 10 second timeout
    }

    protected static void setup(){
        setup(false);
    }

    protected static void setup(boolean maximizeBrowser){
        setup(DEFAULT_BASE_URL, maximizeBrowser);
    }

    protected static void setup(
            String url, boolean maximizeBrowser){
        setup(new ChromeDriver(), url, maximizeBrowser);
    }
    protected static void setup(WebDriver customDriver){
        setup(customDriver,false);
    }
    protected static void setup(
            WebDriver customDriver,
            boolean maximizeBrowser){
        setup(customDriver, DEFAULT_BASE_URL, maximizeBrowser);
    }
    protected static void setup(
            WebDriver customDriver,
            String pageUrl,
            boolean maximizeBrowser){

        if(!isWebDriverAvailable()){
            driver = customDriver;
            javascriptExecutor = (JavascriptExecutor) driver;
        }

        if(!isWebDriverWaitAvailable() && isWebDriverAvailable()){
            driverWait = new WebDriverWait(
                    driver,
                    java.time.Duration.ofSeconds(WEB_DRIVER_WAIT_TIMEOUT));
        }

        try {
            if(maximizeBrowser){
                driver.manage().window().maximize();
            }

            driver.get(pageUrl);
        }catch (RuntimeException e){
            log(e.getMessage());
        }
    }

    protected static void cleanup(){
       if(isWebDriverAvailable()){
           driver.quit();
       }

       driver = null;
       driverWait = null;
    }

    protected static String  createXPathFindByText(String text){
        return  createXPathFindByText("*",text);}
    protected static String  createXPathFindByText(String tagname , String text){
        return  ".//%s[normalize-space()='%s']".formatted(tagname ,text);}
    protected static void locateElement(SearchContext element){
        if(element instanceof WebElement){
            log("Locating Element : " + element);
            javascriptExecutor.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    element);
        }
    }

    protected static boolean handleAlert(String expected_alertText){
        return handleAlert(expected_alertText,false);
    }
    protected static boolean handleAlert(String expected_alertText,boolean logAlertText){
        Alert alert = driverWait.until(ExpectedConditions.alertIsPresent());
        String alert_text = alert.getText();
        if(logAlertText){ log("Alert Text : " + alert_text);}
        boolean pass = (!alert_text.isEmpty()) && alert_text.contains(expected_alertText);
        if(pass){ alert.accept();}else {alert.dismiss();}
        return pass;
    }

    protected static WebElement waitUntilVisibilityOfElementLocated(By by){
        return driverWait.until(
                ExpectedConditions.visibilityOfElementLocated(by)
        );
    }
    protected static WebElement waitUntilElementToBeClickable(By by){
        return driverWait.until(
                ExpectedConditions.elementToBeClickable(by)
        );
    }
    protected static WebElement findElement(By by){
        return findElements(driver,by).getFirst();
    }
    protected static WebElement findElement(SearchContext sc, By by){
        return findElements(sc,by).getFirst();
    }
    protected static List<WebElement> findElements(By by){
        return findElements(driver,by);
    }
    protected static List<WebElement> findElements(SearchContext sc, By by){
        waitUntilVisibilityOfElementLocated(by);
        return sc.findElements(by);
    }

    protected static WebElement findElementByText(String text){
        return driver.findElement(By.xpath(createXPathFindByText(text)));
    }

    private static boolean isWebDriverAvailable(){
        return isObjectAvailable(driver);
    }
    private static boolean isWebDriverWaitAvailable(){
        return isObjectAvailable(driverWait);
    }
    protected static boolean isObjectAvailable(Object o){
        return o != null;
    }

    protected static void log(Object message){
        System.out.println("[LOG] " + message);
    }
}
