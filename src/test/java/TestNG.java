import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

public class TestNG extends BaseTest {

    private boolean product_selection_test_pass,
            addToCard_test_Pass,
            cartManagemnet_test_pass;

    private enum WEB_ELEMENT_LOCATOR_KEYS {
        // web element locators keys
        NAVBAR_ID,
        NAVBAR_CART,

        CATEGORY,
        CATEGORY_TYPE,
        ITEM_CONTAINER,
        ITEM_CONTAINER_SINGLE_CARD,
        ITEM_CONTAINER_SINGLE_CARD_BLOCK,
        ITEM_CONTAINER_SINGLE_CARD_TITLE,

        SINGLE_PRODUCT_DETAIL,
        SINGLE_PRODUCT_TITLE,
        SINGLE_PRODUCT_TITLE_02,
        SINGLE_PRODUCT_PRICE,
        SINGLE_PRODUCT_BTN_ADD_TO_CARD,
        SINGLE_PRODUCT_ADD_TO_CART_SUCCESS,

        CART_TABLE_BODY_ID,
        CART_TABLE_ROW,
        CART_REMOVE_ITEM,
        CART_REMOVE_ITEM_BTN
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
        WEB_ELEMENT_LOCATORS.put(WEB_ELEMENT_LOCATOR_KEYS.CATEGORY,".//div[@class='list-group']");
        WEB_ELEMENT_LOCATORS.put(WEB_ELEMENT_LOCATOR_KEYS.CATEGORY_TYPE,".//a[@id='itemc']");
        WEB_ELEMENT_LOCATORS.put(WEB_ELEMENT_LOCATOR_KEYS.ITEM_CONTAINER,".//div[@id='tbodyid']");
        WEB_ELEMENT_LOCATORS.put(WEB_ELEMENT_LOCATOR_KEYS.ITEM_CONTAINER_SINGLE_CARD,
                ".//div[@class='card h-100']");
        WEB_ELEMENT_LOCATORS.put(WEB_ELEMENT_LOCATOR_KEYS.ITEM_CONTAINER_SINGLE_CARD_BLOCK,
                ".//div[@class='card-block']");
        WEB_ELEMENT_LOCATORS.put(WEB_ELEMENT_LOCATOR_KEYS.ITEM_CONTAINER_SINGLE_CARD_TITLE,
                "hrefch");
        WEB_ELEMENT_LOCATORS.put(WEB_ELEMENT_LOCATOR_KEYS.SINGLE_PRODUCT_DETAIL,
                ".//div[@class='product-content product-wrap clearfix product-deatil']");
        WEB_ELEMENT_LOCATORS.put(WEB_ELEMENT_LOCATOR_KEYS.SINGLE_PRODUCT_TITLE,
                ".//h2[@class='name']");
        WEB_ELEMENT_LOCATORS.put(WEB_ELEMENT_LOCATOR_KEYS.SINGLE_PRODUCT_PRICE,
                ".//h3[@class='price-container']");
        WEB_ELEMENT_LOCATORS.put(WEB_ELEMENT_LOCATOR_KEYS.SINGLE_PRODUCT_BTN_ADD_TO_CARD,
                 createXPathFindByText("a","Add to cart"));
        WEB_ELEMENT_LOCATORS.put(WEB_ELEMENT_LOCATOR_KEYS.NAVBAR_CART,
                createXPathFindByText("a","Cart"));

        WEB_ELEMENT_LOCATORS.put(WEB_ELEMENT_LOCATOR_KEYS.CART_TABLE_BODY_ID,
                "tbodyid");
        WEB_ELEMENT_LOCATORS.put(WEB_ELEMENT_LOCATOR_KEYS.CART_TABLE_ROW,
                ".//tr[@class='success']");
        WEB_ELEMENT_LOCATORS.put(WEB_ELEMENT_LOCATOR_KEYS.CART_REMOVE_ITEM,"Nokia");
        WEB_ELEMENT_LOCATORS.put(WEB_ELEMENT_LOCATOR_KEYS.CART_REMOVE_ITEM_BTN,
                createXPathForFindText("a","Delete"));

        //init expected values
        EXPECTED_VALUES.put(WEB_ELEMENT_LOCATOR_KEYS.NAVBAR_ID,"PRODUCT STORE");
        EXPECTED_VALUES.put(WEB_ELEMENT_LOCATOR_KEYS.CATEGORY_TYPE,"Phones");
        EXPECTED_VALUES.put(WEB_ELEMENT_LOCATOR_KEYS.SINGLE_PRODUCT_TITLE,
                "Samsung galaxy s6");
        EXPECTED_VALUES.put(WEB_ELEMENT_LOCATOR_KEYS.SINGLE_PRODUCT_TITLE_02,
                "Nokia lumia 1520");
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
                    EXPECTED_VALUES.get(WEB_ELEMENT_LOCATOR_KEYS.CATEGORY_TYPE),
                    EXPECTED_VALUES.get(WEB_ELEMENT_LOCATOR_KEYS.SINGLE_PRODUCT_TITLE)
            );

            if (clickWebElement(findCardTitle(item_card))) {
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
        try {
            WebElement product_item_title =  findCardTitle(findCardItem(
                    EXPECTED_VALUES.get(WEB_ELEMENT_LOCATOR_KEYS.CATEGORY_TYPE),
                    EXPECTED_VALUES.get(WEB_ELEMENT_LOCATOR_KEYS.SINGLE_PRODUCT_TITLE)
            ));

            if(clickWebElement(product_item_title)){
                addToCard_test_Pass = addToCart();
            }

        }catch (TimeoutException te){
            log("Operation Timeout");
        }catch (Exception e){
            log(e.getMessage());
        }
        assert addToCard_test_Pass : "TC03 Test failed";
        log("TC03 : Add to Card Test PASS");

    }

    @Test(enabled = false) //TC04
    public void cardManagementTest(){
        boolean is_click_product_01,
                is_click_product_02;

        String product_01 = EXPECTED_VALUES.get(
                WEB_ELEMENT_LOCATOR_KEYS.SINGLE_PRODUCT_TITLE);
        String product_02 = EXPECTED_VALUES.get(
                WEB_ELEMENT_LOCATOR_KEYS.SINGLE_PRODUCT_TITLE_02);

        try {

            // add product 01 to cart
            WebElement product_01_title =  findCardTitle(findCardItem(
                    EXPECTED_VALUES.get(WEB_ELEMENT_LOCATOR_KEYS.CATEGORY_TYPE),
                    product_01
            ));

            is_click_product_01 = clickWebElement(product_01_title);
            if(is_click_product_01){
                addToCart();
                navigateHome();
            }

            // add product 02 to cart
            WebElement product_02_title =  findCardTitle(findCardItem(
                    EXPECTED_VALUES.get(WEB_ELEMENT_LOCATOR_KEYS.CATEGORY_TYPE),
                    product_02
            ));

            is_click_product_02 = clickWebElement(product_02_title);
            if(is_click_product_02){
                addToCart();
                navigateHome();
            }

            //go cart page
            if(clickWebElement(findElement(By.xpath(
                    WEB_ELEMENT_LOCATORS.get(WEB_ELEMENT_LOCATOR_KEYS.NAVBAR_CART))))) {
                log("Navigate to cart page");

                // get cart items
                List<WebElement> cart_items = getCartItems();

                //remove nokia
                for(int i = 0; i < cart_items.size(); i++){
                    WebElement element = cart_items.get(i);
                    if(element.getText().contains(
                            WEB_ELEMENT_LOCATORS.get(WEB_ELEMENT_LOCATOR_KEYS.CART_REMOVE_ITEM))){
                        
                        WebElement removed_item = cart_items.remove(i);

                        removed_item.findElement(By.xpath(
                                WEB_ELEMENT_LOCATORS.get(WEB_ELEMENT_LOCATOR_KEYS.CART_REMOVE_ITEM_BTN)
                        )).click();

                        log(removed_item.findElement(By.xpath(".//td[2]")).getText()
                                + " Removed from cart");
                        break;
                    }
                }

                driverWait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.id(WEB_ELEMENT_LOCATORS.get(WEB_ELEMENT_LOCATOR_KEYS.CART_TABLE_BODY_ID)))
                );

                driver.navigate().refresh();

                //reload(reassign) cart items
                cart_items = getCartItems();
                String remains_need = EXPECTED_VALUES.get(WEB_ELEMENT_LOCATOR_KEYS.SINGLE_PRODUCT_TITLE);
                boolean isRemains = cart_items.getFirst().findElement(By.xpath(".//td[2]")).getText()
                        .contains(remains_need);
                if(isRemains){
                    log(remains_need + " is remain");
                }

                cartManagemnet_test_pass = isRemains && (cart_items.size() == 1);
            }

        }catch (Exception e){
            log(e.getMessage());
        }

        assert cartManagemnet_test_pass : "TC04 Test failed";
        log("TC04 : Add to Card Test PASS");
    }

    @Test(enabled = false) //TC05
    public void checkoutValidationTest(){

    }

    @AfterMethod(alwaysRun = true)
    public void afterTest(){
        cleanup();
    }


    private List<WebElement> getCartItems(){
        waitUntilVisibilityOfElementLocated(
                By.id(WEB_ELEMENT_LOCATORS.get(WEB_ELEMENT_LOCATOR_KEYS.CART_TABLE_BODY_ID)));

        //get count of cart items
        List<WebElement> cart_items = driver.findElements(By.xpath(
                WEB_ELEMENT_LOCATORS.get(WEB_ELEMENT_LOCATOR_KEYS.CART_TABLE_ROW)
        ));

        log("\n------ Cart Rows ------");

        for (int i = 0; i < cart_items.size(); i++){
            WebElement element = cart_items.get(i);
            String name = element.findElement(By.xpath(".//td[2]")).getText();
            String price = element.findElement(By.xpath(".//td[3]")).getText();
            String action = element.findElement(By.xpath(".//td[4]")).getText();
            log("""
                   Index=%d, Name=%s, Price=%s, Action=%s""".formatted(i,name,price,action));
        }
        log(cart_items.size() + " item(s) on cart");

        return cart_items;
    }

    private WebElement findCardItem(String expected_category,
                                    String itemName){
        WebElement foundCardElement = null;
        List<WebElement> categorie_types = findElementsFromRoot(
              findElement(By.xpath(WEB_ELEMENT_LOCATORS.get(WEB_ELEMENT_LOCATOR_KEYS.CATEGORY))),
                By.xpath(WEB_ELEMENT_LOCATORS.get(WEB_ELEMENT_LOCATOR_KEYS.CATEGORY_TYPE))
        );

        log("Expected Category : " + expected_category);
        for (WebElement element : categorie_types) {
            if (element.getText()
                    .equals(expected_category)) {
                element.click();
                log(expected_category + " found");
                break;
            }
        }


        List<WebElement> item_container_item_cards =  findElementsFromRoot(
                waitUntilVisibilityOfElementLocated(By.xpath(
                        WEB_ELEMENT_LOCATORS.get(
                                WEB_ELEMENT_LOCATOR_KEYS.ITEM_CONTAINER
                        )
                )),
                By.xpath(WEB_ELEMENT_LOCATORS.get(WEB_ELEMENT_LOCATOR_KEYS.ITEM_CONTAINER_SINGLE_CARD_BLOCK))
        );

        log("Expected item : " + itemName);
        for(WebElement item_card : item_container_item_cards){
            locateElement(item_card);

            WebElement card_title = findCardTitle(item_card);
            String title_text = card_title.getText();

            if(title_text.equals(itemName)){
                log(title_text + " found");
                foundCardElement = item_card;
                break;
            }
        }

        return foundCardElement;
}

    private boolean addToCart(){
        boolean addToCartSuccess = true;
        try {
            WebElement addToCartBtn = waitUntilElementToBeClickable(
                    By.xpath(
                            WEB_ELEMENT_LOCATORS.get(WEB_ELEMENT_LOCATOR_KEYS.SINGLE_PRODUCT_BTN_ADD_TO_CARD)
                    )
            );

            locateElement(addToCartBtn);
            boolean click = clickWebElement(addToCartBtn);
            if(click){
                addToCartSuccess = handleAlert(
                        EXPECTED_VALUES.get(WEB_ELEMENT_LOCATOR_KEYS.SINGLE_PRODUCT_ADD_TO_CART_SUCCESS),
                        true);
            }

        }catch (NoSuchElementException e){
            log(e);
        }

        return addToCartSuccess;
    }

    private void navigateHome(){
        findElement(By.id(
                WEB_ELEMENT_LOCATORS.get(WEB_ELEMENT_LOCATOR_KEYS.NAVBAR_ID)
        )).click();
    }
    private WebElement findCardTitle(WebElement item_card){
        if(!isObjectAvailable(item_card)){return null;}
        return findElementFromRoot(
                item_card,
                By.className(
                        WEB_ELEMENT_LOCATORS.get(
                                WEB_ELEMENT_LOCATOR_KEYS.ITEM_CONTAINER_SINGLE_CARD_TITLE
                        )
                )
        );
    }
    private boolean clickWebElement(WebElement element){
        if(!isObjectAvailable(element)) return false;
        element.click();
       return true;
    }
    private static WebElement findElementFromRoot(
            By element_locator){
        return findElementsFromRoot(element_locator).getFirst();
    }
    private static WebElement findElementFromRoot(
            SearchContext rootElement,
            By element_locator_key){
        return findElementsFromRoot(rootElement, element_locator_key).getFirst();
    }
    private static List<WebElement> findElementsFromRoot(
            By element_locator_key){
        return findElementsFromRoot(
                driver,
                element_locator_key
        );
    }
    private static List<WebElement> findElementsFromRoot(
            SearchContext rootElement,
            By element_locator_key){
        return findElements(rootElement,
                element_locator_key
        );
    }
}
