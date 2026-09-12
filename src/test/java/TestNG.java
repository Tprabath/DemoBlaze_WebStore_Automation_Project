import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNG extends BaseTest {

    @BeforeMethod
    private static void beforeTest(){

    }

    @Test
    private static void test(){

    }

    @AfterMethod(alwaysRun = true)
    private static void afterTest(){

    }
}
