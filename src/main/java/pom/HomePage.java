package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private final WebDriver driver;
    //Кнопка Заказать. Верх
    private final By buttonOrderTop = By.xpath(".//button[@class = 'Button_Button__ra12g' and " +
            "text() = 'Заказать']");
    //Кнопка Заказать. Низ
    private final By buttonOrderBottom = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button");

    public HomePage(WebDriver driver){
        this.driver = driver;
    }
    public void openHomePage(){
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    public void clickTopOrderButton(){
        new WebDriverWait(driver, 5).until(ExpectedConditions
                .elementToBeClickable(buttonOrderTop));
        driver.findElement(buttonOrderTop).click();
    }

    public void clickBottomOrderButton(){
        WebElement menuBottom = driver.findElement(By.className("Home_RoadMap__2tal_"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", menuBottom);

        WebElement element = driver.findElement(buttonOrderBottom);
        new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(element));
        driver.findElement(buttonOrderBottom).click();
    }

    public String getAnswer(int questionNumber){

        String pathToQuestion = ".//div[@class='accordion__item'][" + questionNumber + "]";

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(By.xpath(pathToQuestion)));
        WebElement question = driver.findElement(By.xpath(pathToQuestion));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", question);
        driver.findElement(By.xpath(pathToQuestion)).click();

        String pathToAnswer = pathToQuestion + "/div/p";
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(By.xpath(pathToAnswer)));

        return driver.findElement(By.xpath(pathToAnswer)).getText();

    }

}
