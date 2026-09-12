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

    private static boolean isWebDriverAvailable(){
        return isObjectAvailable(driver);
    }
    private static boolean isWebDriverWaitAvailable(){
        return isObjectAvailable(driverWait);
    }
    private static boolean isObjectAvailable(Object o){
        return o != null;
    }

    protected static void log(String message){
        System.out.println(message);
    }
}
