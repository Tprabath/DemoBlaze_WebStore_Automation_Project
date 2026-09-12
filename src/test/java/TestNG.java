import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;

public class TestNG extends BaseTest {

    private static final HashMap<String,String> WEB_ELEMENT_LOCATORS = new HashMap<>();
    private static final HashMap<String,Object> EXAMPLE_CHECKOUT_DATA = new HashMap<>();

    static {

        //init web element locators


        // init example checkout data
        EXAMPLE_CHECKOUT_DATA.put("name", "Test Student");
        EXAMPLE_CHECKOUT_DATA.put("country", "Sri Lanka");
        EXAMPLE_CHECKOUT_DATA.put("city","Colombo");
        EXAMPLE_CHECKOUT_DATA.put("credit_card","4111111111111111");
        EXAMPLE_CHECKOUT_DATA.put("month", 12);
        EXAMPLE_CHECKOUT_DATA.put("year", 2027);
    }

    @BeforeMethod
    public void beforeTest(){
        /*
         *  setup() - start browser as normal window
         *  setup(true) - start browser with maximize window
         */
        setup(true);
    }

    @Test //TC01
    public void homePageSmokeTest(){

    }

    @Test //TC02
    public void productSelectionTest(){

    }

    @Test //TC03
    public void addToCardTest(){

    }

    @Test //TC04
    public void cardManagementTest(){

    }

    @Test //TC05
    public void checkoutValidationTest(){

    }

    @AfterMethod(alwaysRun = true)
    public void afterTest(){
        cleanup();
    }
}
