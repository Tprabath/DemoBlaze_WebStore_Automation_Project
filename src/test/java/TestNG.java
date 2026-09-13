import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;

public class TestNG extends BaseTest {

    private enum HASHMAP_KEYS {
        // web element locators keys


        // checkout data keys
        CHECKOUT_DATA_NAME,
        CHECKOUT_DATA_COUNTRY,
        CHECKOUT_DATA_CITY,
        CHECKOUT_DATA_CREDIT_CARD_NO,
        CHECKOUT_DATA_MONTH,
        CHECKOUT_DATA_YEAR;
    }


    private static final HashMap<HASHMAP_KEYS,String> WEB_ELEMENT_LOCATORS = new HashMap<>();
    private static final HashMap<HASHMAP_KEYS,Object> EXAMPLE_CHECKOUT_DATA = new HashMap<>();

    static {
        //init web element locators


        // init example checkout data
        EXAMPLE_CHECKOUT_DATA.put(HASHMAP_KEYS.CHECKOUT_DATA_NAME,"Test Student");
        EXAMPLE_CHECKOUT_DATA.put(HASHMAP_KEYS.CHECKOUT_DATA_COUNTRY, "Sri Lanka");
        EXAMPLE_CHECKOUT_DATA.put(HASHMAP_KEYS.CHECKOUT_DATA_CITY, "Colombo");
        EXAMPLE_CHECKOUT_DATA.put(HASHMAP_KEYS.CHECKOUT_DATA_CREDIT_CARD_NO, "4111111111111111");
        EXAMPLE_CHECKOUT_DATA.put(HASHMAP_KEYS.CHECKOUT_DATA_MONTH, 12);
        EXAMPLE_CHECKOUT_DATA.put(HASHMAP_KEYS.CHECKOUT_DATA_YEAR, 2027);
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
