import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNG extends BaseTest {

    @BeforeMethod
    public void beforeTest(){

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

    }
}
