import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pom.HomePage;
import pom.OrderPage;
import static org.hamcrest.CoreMatchers.startsWith;



public class PlaceOrderTest {
    private WebDriver driver;
    private String expectedMessageOrderPlaced = "Заказ оформлен";

    @Before
    public void start(){
        //driver = new FirefoxDriver();
        driver = new ChromeDriver();
    }

    @Test
    public void PlaceOrderTopButton(){
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage();
        homePage.clickTopOrderButton();

        OrderPage orderPage = new OrderPage(driver);
        String actual = orderPage.makeOrder("Петр", "Петров", "Советская д. 11, к. 12",
                "+79301812233", 5, 5, 2);

        MatcherAssert.assertThat(actual, startsWith(expectedMessageOrderPlaced));
    }

    @Test
    public void PlaceOrderBottomButton(){
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage();
        homePage.clickBottomOrderButton();

        OrderPage orderPage = new OrderPage(driver);
        String actual = orderPage.makeOrder("Александр", "Иванов",
                "ул. Терешкова, д. 1 к. 2", "+79290984233", 5, 1, 4);

        MatcherAssert.assertThat(actual, startsWith(expectedMessageOrderPlaced));

    }

    @After
    public void close(){
        driver.quit();
    }
}
