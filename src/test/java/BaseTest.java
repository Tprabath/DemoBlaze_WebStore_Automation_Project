import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {
    private static final int WEB_DRIVER_WAIT = 5; // 5 sec
    private static final String BASE_URL = "";

    private static ChromeDriver driver;
    private static WebDriverWait driverWait;

    protected static void setup(){}
    protected static void getPage(String url){}
    protected static void cleanup(){}
    protected static WebDriverWait getWebDriverWait(){
        return null;
    }

}
