import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

public class TestNG extends BaseTest {

    private boolean product_selection_test_pass,
            addToCard_test_Pass = false;

    private enum WEB_ELEMENT_LOCATOR_KEYS {
        // web element locators keys
        NAVBAR_ID,

        CATEGORY,
        CATEGORY_TYPE,
        ITEM_CONTAINER,
        ITEM_CONTAINER_SINGLE_CARD,
        ITEM_CONTAINER_SINGLE_CARD_BLOCK,
        ITEM_CONTAINER_SINGLE_CARD_TITLE,

        SINGLE_PRODUCT_DETAIL,
        SINGLE_PRODUCT_TITLE,
        SINGLE_PRODUCT_PRICE,
        SINGLE_PRODUCT_BTN_ADD_TO_CARD,
        SINGLE_PRODUCT_ADD_TO_CART_SUCCESS
    }

    private enum CHECKOUT_DATA_KEYS {
        // checkout data keys
        CHECKOUT_DATA_NAME,
        CHECKOUT_DATA_COUNTRY,
        CHECKOUT_DATA_CITY,
        CHECKOUT_DATA_CREDIT_CARD_NO,
        CHECKOUT_DATA_MONTH,
        CHECKOUT_DATA_YEAR
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
        WEB_ELEMENT_LOCATORS.put(WEB_ELEMENT_LOCATOR_KEYS.CATEGORY,"//div[@class='list-group']");
        WEB_ELEMENT_LOCATORS.put(WEB_ELEMENT_LOCATOR_KEYS.CATEGORY_TYPE,"//a[@id='itemc']");
        WEB_ELEMENT_LOCATORS.put(WEB_ELEMENT_LOCATOR_KEYS.ITEM_CONTAINER,"//div[@id='tbodyid']");
        WEB_ELEMENT_LOCATORS.put(WEB_ELEMENT_LOCATOR_KEYS.ITEM_CONTAINER_SINGLE_CARD,
                "//div[@class='card h-100']");
        WEB_ELEMENT_LOCATORS.put(WEB_ELEMENT_LOCATOR_KEYS.ITEM_CONTAINER_SINGLE_CARD_BLOCK,
                "//div[@class='card-block']");
        WEB_ELEMENT_LOCATORS.put(WEB_ELEMENT_LOCATOR_KEYS.ITEM_CONTAINER_SINGLE_CARD_TITLE,
                "//div[@class='card-block']/h4[@class='card-title']/a[@class='hrefch']");
        WEB_ELEMENT_LOCATORS.put(WEB_ELEMENT_LOCATOR_KEYS.SINGLE_PRODUCT_DETAIL,
                "//div[@class='product-content product-wrap clearfix product-deatil']");
        WEB_ELEMENT_LOCATORS.put(WEB_ELEMENT_LOCATOR_KEYS.SINGLE_PRODUCT_TITLE,
                "//h2[@class='name']");
        WEB_ELEMENT_LOCATORS.put(WEB_ELEMENT_LOCATOR_KEYS.SINGLE_PRODUCT_PRICE,
                "//h3[@class='price-container']");
        WEB_ELEMENT_LOCATORS.put(WEB_ELEMENT_LOCATOR_KEYS.SINGLE_PRODUCT_BTN_ADD_TO_CARD,
                "//a[normalize-space()='Add to cart']");

        //init expected values
        EXPECTED_VALUES.put(WEB_ELEMENT_LOCATOR_KEYS.NAVBAR_ID,"PRODUCT STORE");
        EXPECTED_VALUES.put(WEB_ELEMENT_LOCATOR_KEYS.CATEGORY,"Phones");
        EXPECTED_VALUES.put(WEB_ELEMENT_LOCATOR_KEYS.SINGLE_PRODUCT_TITLE,
                "Samsung galaxy s6");
        EXPECTED_VALUES.put(WEB_ELEMENT_LOCATOR_KEYS.SINGLE_PRODUCT_ADD_TO_CART_SUCCESS,
                "Product added");

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

    @Test(enabled = false) //TC01
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
        try {
            // Open Phones
            WebElement item_card = findCardItem(
                    EXPECTED_VALUES.get(WEB_ELEMENT_LOCATOR_KEYS.CATEGORY),
                    EXPECTED_VALUES.get(WEB_ELEMENT_LOCATOR_KEYS.SINGLE_PRODUCT_TITLE)
            );

            if (clickCard(findCardTitle(item_card),
                    EXPECTED_VALUES.get(WEB_ELEMENT_LOCATOR_KEYS.SINGLE_PRODUCT_TITLE))) {
                WebElement single_product_detail = waitUntilVisibilityOfElementLocated(
                        By.xpath(
                                WEB_ELEMENT_LOCATORS.get(
                                        WEB_ELEMENT_LOCATOR_KEYS.SINGLE_PRODUCT_DETAIL
                                )
                        )
                );

                String single_product_title = waitUntilVisibilityOfElementLocated(
                        By.xpath(
                                WEB_ELEMENT_LOCATORS.get(
                                        WEB_ELEMENT_LOCATOR_KEYS.SINGLE_PRODUCT_TITLE
                                )
                        )
                ).getText();

                // Verify Heading
                product_selection_test_pass = single_product_title.equals(
                        EXPECTED_VALUES.get(WEB_ELEMENT_LOCATOR_KEYS.SINGLE_PRODUCT_TITLE)
                );

                // print price
                log("Price : " + single_product_detail.findElement(By.xpath(
                        WEB_ELEMENT_LOCATORS.get(
                                WEB_ELEMENT_LOCATOR_KEYS.SINGLE_PRODUCT_PRICE
                        ))).getText()
                );
            }

        } catch (NoSuchElementException e) {
            log("No Element found : " + e.getMessage());

        } catch (Exception e) {
            log(e.getMessage());
        }

        assert product_selection_test_pass : "TC02 Test failed";
        log("TC02 : Product Selection PASS");
    }

    @Test(enabled = true) //TC03
    public void addToCardTest(){
        WebElement item_card = findCardItem(
                EXPECTED_VALUES.get(WEB_ELEMENT_LOCATOR_KEYS.CATEGORY),
                EXPECTED_VALUES.get(WEB_ELEMENT_LOCATOR_KEYS.SINGLE_PRODUCT_TITLE)
        );

        if(clickCard(findCardTitle(item_card),
                EXPECTED_VALUES.get(WEB_ELEMENT_LOCATOR_KEYS.SINGLE_PRODUCT_TITLE))){

            try {
                driverWait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath(
                                        WEB_ELEMENT_LOCATORS.get(
                                                WEB_ELEMENT_LOCATOR_KEYS.SINGLE_PRODUCT_BTN_ADD_TO_CARD
                                        )
                                ))).click();

                Alert alert = driverWait.until(
                        ExpectedConditions.alertIsPresent()
                );

                String alert_text = alert.getText();
                log("Alert Text : " + alert_text);
                alert.accept();

                addToCard_test_Pass = !alert_text.isEmpty() && alert_text.equals(
                        EXPECTED_VALUES.get(WEB_ELEMENT_LOCATOR_KEYS.SINGLE_PRODUCT_ADD_TO_CART_SUCCESS));

            }catch (TimeoutException te){
                log("Operation Timeout");
            }catch (Exception e){
                log(e.getMessage());
            }
        }

        assert addToCard_test_Pass : "TC03 Test failed";
        log("TC03 : Add to Card Test PASS");

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

    private WebElement findCardItem(String expected_category,
                                    String itemName){
        WebElement foundCardElement = null;
        List<WebElement> categorie_types = findElement(driver,By.xpath(
                // first, find category div
                WEB_ELEMENT_LOCATORS.get(WEB_ELEMENT_LOCATOR_KEYS.CATEGORY)
        )).findElements(By.xpath(
                // second, find all category types
                WEB_ELEMENT_LOCATORS.get(WEB_ELEMENT_LOCATOR_KEYS.CATEGORY_TYPE)
        ));

        for (WebElement element : categorie_types) {
            if (element.getText()
                    .equals(expected_category)) {
                element.click();
                break;
            }
        }

        List<WebElement> item_container_item_cards = waitUntilVisibilityOfElementLocated(
                // first, find item container and wait until container's elements
                By.xpath(WEB_ELEMENT_LOCATORS.get(
                        WEB_ELEMENT_LOCATOR_KEYS.ITEM_CONTAINER
                ))).findElements(
                // second, find every cards in container
                By.xpath(WEB_ELEMENT_LOCATORS.get(
                        WEB_ELEMENT_LOCATOR_KEYS.ITEM_CONTAINER_SINGLE_CARD
                ))
        );

        for(WebElement item_card : item_container_item_cards){

            WebElement card_title = findElement(item_card,  By.xpath(
                    WEB_ELEMENT_LOCATORS.get(
                            WEB_ELEMENT_LOCATOR_KEYS.ITEM_CONTAINER_SINGLE_CARD_BLOCK)
            )).findElement(By.xpath(
                    WEB_ELEMENT_LOCATORS.get(
                            WEB_ELEMENT_LOCATOR_KEYS.ITEM_CONTAINER_SINGLE_CARD_TITLE)
            ));

            if(card_title.getText().equals(itemName)){
                foundCardElement = item_card;
                break;
            }
        }

        return foundCardElement;
}

    private WebElement findCardTitle(WebElement item_card){
        return findElement(item_card,  By.xpath(
                WEB_ELEMENT_LOCATORS.get(
                        WEB_ELEMENT_LOCATOR_KEYS.ITEM_CONTAINER_SINGLE_CARD_BLOCK)
        )).findElement(By.xpath(
                WEB_ELEMENT_LOCATORS.get(
                        WEB_ELEMENT_LOCATOR_KEYS.ITEM_CONTAINER_SINGLE_CARD_TITLE)
        ));
    }
    private boolean clickCard(WebElement card,String card_title){
        boolean cardMatch = card.getText().equals(card_title);
        if(cardMatch) card.click();
        return cardMatch;
    }
}
