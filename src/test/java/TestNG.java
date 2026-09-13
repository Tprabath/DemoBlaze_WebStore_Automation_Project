import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;

public class TestNG extends BaseTest {

    private enum WEB_ELEMENT_LOCATOR_KEYS {
        // web element locators keys
        NAVBAR_ID,

    }

    private enum CHECKOUT_DATA_KEYS {
        // checkout data keys
        CHECKOUT_DATA_NAME,
        CHECKOUT_DATA_COUNTRY,
        CHECKOUT_DATA_CITY,
        CHECKOUT_DATA_CREDIT_CARD_NO,
        CHECKOUT_DATA_MONTH,
        CHECKOUT_DATA_YEAR;
    }


    /*
     * a map for web element locators
     */
    private static final HashMap<WEB_ELEMENT_LOCATOR_KEYS,String>
            WEB_ELEMENT_LOCATORS = new HashMap<>();

    /*
    * a map for expected values,
    * for map WEB_ELEMENT_LOCATOR = expected value
     */
    private static final HashMap<WEB_ELEMENT_LOCATOR_KEYS,String>
            EXPECTED_VALUES = new HashMap<>();

    /*
    * a map for store example checkout data
     */
    private static final HashMap<CHECKOUT_DATA_KEYS,Object>
            EXAMPLE_CHECKOUT_DATA = new HashMap<>();

    static {
        //init web element locators
        WEB_ELEMENT_LOCATORS.put(WEB_ELEMENT_LOCATOR_KEYS.NAVBAR_ID,"nava");

        //init expected values
        EXPECTED_VALUES.put(WEB_ELEMENT_LOCATOR_KEYS.NAVBAR_ID,"PRODUCT STORE");

        // init example checkout data
        EXAMPLE_CHECKOUT_DATA.put(CHECKOUT_DATA_KEYS.CHECKOUT_DATA_NAME,"Test Student");
        EXAMPLE_CHECKOUT_DATA.put(CHECKOUT_DATA_KEYS.CHECKOUT_DATA_COUNTRY, "Sri Lanka");
        EXAMPLE_CHECKOUT_DATA.put(CHECKOUT_DATA_KEYS.CHECKOUT_DATA_CITY, "Colombo");
        EXAMPLE_CHECKOUT_DATA.put(CHECKOUT_DATA_KEYS.CHECKOUT_DATA_CREDIT_CARD_NO, "4111111111111111");
        EXAMPLE_CHECKOUT_DATA.put(CHECKOUT_DATA_KEYS.CHECKOUT_DATA_MONTH, 12);
        EXAMPLE_CHECKOUT_DATA.put(CHECKOUT_DATA_KEYS.CHECKOUT_DATA_YEAR, 2027);
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
        assert driver.findElement(
                    By.id(WEB_ELEMENT_LOCATORS.get(WEB_ELEMENT_LOCATOR_KEYS.NAVBAR_ID))
                ).getText()
                .equals(EXPECTED_VALUES.get(WEB_ELEMENT_LOCATOR_KEYS.NAVBAR_ID))
                : "Title is empty";
        
        log("TC01 : Home Page Smoke Test PASS");
    }

    @Test(enabled = false) //TC02
    public void productSelectionTest(){

    }

    @Test(enabled = false) //TC03
    public void addToCardTest(){

    }

    @Test(enabled = false) //TC04
    public void cardManagementTest(){

    }

    @Test(enabled = false) //TC05
    public void checkoutValidationTest(){

    }

    @AfterMethod(alwaysRun = true)
    public void afterTest(){
        cleanup();
    }
}
