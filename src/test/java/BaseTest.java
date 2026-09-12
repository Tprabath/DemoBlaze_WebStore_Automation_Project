import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {
    private static final int WEB_DRIVER_WAIT = 5; // 5 sec
    private static final String BASE_URL = "";

    protected static WebDriver driver;
    protected static WebDriverWait driverWait;

    protected static void setup(){}
    protected static void setup(String pageUrl){}
    protected static void setup(WebDriver customDriver){}
    protected static void setup(WebDriver customDriver, String pageUrl){}
    protected static void cleanup(){}
    protected static WebDriverWait getWebDriverWait(){
        return null;
    }

}
