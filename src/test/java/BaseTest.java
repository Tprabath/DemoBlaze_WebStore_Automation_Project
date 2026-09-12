import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {
    private static final int WEB_DRIVER_WAIT_TIMEOUT;
    private static final String DEFAULT_BASE_URL;

    protected static WebDriver driver;
    protected static WebDriverWait driverWait;

    // initialize static variables
    static {
        DEFAULT_BASE_URL = "https://www.demoblaze.com/";
        WEB_DRIVER_WAIT_TIMEOUT = 5; // 5 second timeout
    }

    protected static void setup(){
        setup(DEFAULT_BASE_URL);
    }
    protected static void setup(String url){
        setup(new ChromeDriver(), url);
    }
    protected static void setup(WebDriver customDriver){
        setup(customDriver, DEFAULT_BASE_URL);
    }
    protected static void setup(WebDriver customDriver, String pageUrl){
        if(!isWebDriverAvailable()){
            driver = customDriver;
        }

        if(!isWebDriverWaitAvailable() && isWebDriverAvailable()){
            driverWait = new WebDriverWait(
                    driver,
                    java.time.Duration.ofSeconds(WEB_DRIVER_WAIT_TIMEOUT));
        }

        driver.get(pageUrl);
    }

    protected static void cleanup(){
       if(isWebDriverAvailable()){
           driver.quit();
       }

       driver = null;
       driverWait = null;
    }

    private static boolean isWebDriverAvailable(){
        return isObjectAvailable(driver);
    }
    private static boolean isWebDriverWaitAvailable(){
        return isObjectAvailable(driverWait);
    }
    private static boolean isObjectAvailable(Object o){
        return o != null;
    }
}
