package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class OrderPage {
    private final WebDriver driver;
    //Поле ввода имени
    private final By Name = By.xpath(".//input[@placeholder = '* Имя' ]");
    //Поле ввода фамилии
    private final By Surname = By.xpath(".//input[@placeholder = '* Фамилия' ]");
    //Поле ввода адреса
    private final By Address = By.xpath(".//input[@placeholder = '* Адрес: куда привезти заказ' ]");
    //Поле выбора станции метро
    private final By Subway = By.xpath(".//input[@placeholder = '* Станция метро' ]");
    //Поле ввода телефона
    private final By Phone = By.xpath(".//input[@placeholder = '* Телефон: на него позвонит курьер' ]");
    //Кнопка Далее на первой странице оформления заказа
    private final By ButtonOnwardFirstOrderPage = By.cssSelector("button.Button_Button__ra12g.Button_Middle__1CSJM");
    //Поле Когда привезут заказ
    private final By DateOrder = By.xpath(".//input[@placeholder = '* Когда привезти самокат' ]");
    //Поле Срок аренды
    private final By RentPeriod = By.cssSelector("div.Dropdown-placeholder");
    //Кнопка Заказать. Вторая страница оформления заказа
    private final By ButtonOrder = By.xpath(".//button[@class = 'Button_Button__ra12g " +
            "Button_Middle__1CSJM' and text() = 'Заказать']");
    //Подтверждение заказа
    private final By ButtonConfirmationOrder = By.xpath(".//button[@class ='Button_Button__ra12g " +
            "Button_Middle__1CSJM' and text() = 'Да' ]");
    //Описание заказа после оформления
    private final By OrderDescription = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ']");
    //Кнопка посмотреть статус заказа. Модальное окно после оформления заказа
    private final By ButtonCheckOrderStatus = By.xpath(".//button[@class ='Button_Button__ra12g " +
            "Button_Middle__1CSJM' and text() = 'Посмотреть статус']");

    public OrderPage(WebDriver driver){
        this.driver = driver;
    }

    public void setName(String userName){
        driver.findElement(Name).sendKeys(userName);
    }
    public void setSurname(String userSurName){
        driver.findElement(Surname).sendKeys(userSurName);
    }
    public void setAddress(String userAddress){
        driver.findElement(Address).sendKeys(userAddress);
    }
    public void setSubway(){
       WebElement fieldSubway = driver.findElement(Subway);
        new Actions(driver)
                .moveToElement(fieldSubway)
                .pause(Duration.ofSeconds(3))
                .click()
                .pause(Duration.ofSeconds(3))
                .moveByOffset(0,60)
                .click()
                .perform();
    }
    public void setPhone(String userPhone){
        driver.findElement(Phone).sendKeys(userPhone);
    }
    public void pressButtonOnwardFirstOrderPage(){
        driver.findElement(ButtonOnwardFirstOrderPage).click();
    }
    public void setDateOrder(int weekNumber, int dayNumber){
        driver.findElement(DateOrder).click();
        String week = ".//div[@class = 'react-datepicker__week'][" + weekNumber + "]";
        String day = week + "/div[@role ='button'][" + dayNumber + "]";
        driver.findElement(By.xpath(day)).click();
    }

    public void setRentPeriod(int countDays){
        driver.findElement(RentPeriod).click();
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(By.className("Dropdown-menu")));
        String selectedDay = ".//div[@class = 'Dropdown-menu']/div[@class = 'Dropdown-option' ][" + countDays + "]";
        driver.findElement(By.xpath(selectedDay)).click();
    }
    public void pressButtonOrder(){
        driver.findElement(ButtonOrder).click();
    }

    public void confirmOrder(){
        driver.findElement(ButtonConfirmationOrder).click();
    }

    public String makeOrder(String name, String surname, String address, String phone, int deliveryWeek,
                          int deliveryDay, int rentPeriod){
        setName(name);
        setSurname(surname);
        setAddress(address);
        setSubway();
        setPhone(phone);
        pressButtonOnwardFirstOrderPage();

        setDateOrder(deliveryWeek, deliveryDay);
        setRentPeriod(rentPeriod);
        pressButtonOrder();
        confirmOrder();
        return driver.findElement(OrderDescription).getText();
    }
}