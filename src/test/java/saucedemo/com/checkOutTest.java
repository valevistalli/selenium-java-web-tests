package saucedemo.com;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class checkOutTest {
    private WebDriver driver;
    private checkOut CheckOut;
    private sauceDemo SauceDemo;
    private navigateInventoryPage NavigateInventoryPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        SauceDemo = new sauceDemo(driver, "https://www.saucedemo.com");
        SauceDemo.userNameSendKeys("standard_user");
        SauceDemo.passwordSendKeys("secret_sauce");
        SauceDemo.loginButtonClick();
        NavigateInventoryPage = new navigateInventoryPage(driver);
        NavigateInventoryPage.addProduct("add-to-cart-sauce-labs-backpack");
        NavigateInventoryPage.addProduct("add-to-cart-sauce-labs-fleece-jacket");
        NavigateInventoryPage.addProduct("add-to-cart-sauce-labs-onesie");
        CheckOut = new checkOut(driver);
    }

    @Test
    public void successfulCheckOutProcess(){
        CheckOut.successfulCheckOutProcess();
        Assertions.assertEquals("Thank you for your order!",driver.findElement(By.cssSelector("[class='complete-header']")).getText());
    }

    @Test
    public void missingNameCheckOut(){
        CheckOut.missingNameCheckOut();
        Assertions.assertEquals("Error: First Name is required", driver.findElement(By.cssSelector("[data-test='error']")).getText());
    }

    @Test
    public void missingLastnameCheckOut(){
        CheckOut.missingLastnameCheckOut();
        Assertions.assertEquals("Error: Last Name is required", driver.findElement(By.cssSelector("[data-test='error']")).getText());
    }

    @Test
    public void missingZipcodeCheckOut(){
        CheckOut.missingZipcodeCheckOut();
        Assertions.assertEquals("Error: Postal Code is required", driver.findElement(By.cssSelector("[data-test='error']")).getText());
    }

    @Test
    public void cancelCheckOut(){
        CheckOut.cancelCheckOut();
        String url = driver.getCurrentUrl();
        Assertions.assertEquals("https://www.saucedemo.com/cart.html", url);
    }

    @AfterEach
    public void closeDriver(){
        driver.quit();
    }
}
