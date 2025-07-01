package saucedemo.com;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class inventoryPageTest {
    private WebDriver driver;
    private navigateInventoryPage NavigateInventoryPage;
    private sauceDemo SauceDemo;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        SauceDemo = new sauceDemo(driver, "https://www.saucedemo.com");
        SauceDemo.userNameSendKeys("standard_user");
        SauceDemo.passwordSendKeys("secret_sauce");
        SauceDemo.loginButtonClick();
        NavigateInventoryPage = new navigateInventoryPage(driver);
    }

    @Test
    public void addProductToCart(){
        NavigateInventoryPage.addProduct("add-to-cart-sauce-labs-backpack");
        NavigateInventoryPage.addProduct("add-to-cart-sauce-labs-fleece-jacket");
        NavigateInventoryPage.addProduct("add-to-cart-sauce-labs-onesie");
        String badgeText = driver.findElement(By.cssSelector(".shopping_cart_badge")).getText();
        Assertions.assertEquals("3", badgeText);
    }

    @Test
    public void removeProductToCart(){
        NavigateInventoryPage.addProduct("add-to-cart-sauce-labs-backpack");
        NavigateInventoryPage.addProduct("add-to-cart-sauce-labs-fleece-jacket");
        NavigateInventoryPage.addProduct("add-to-cart-sauce-labs-onesie");
        NavigateInventoryPage.removeProduct("remove-sauce-labs-onesie");
        String badgeText = driver.findElement(By.cssSelector(".shopping_cart_badge")).getText();
        Assertions.assertEquals("2", badgeText);
    }

    @Test
    public void sortZA(){
        NavigateInventoryPage.sortZA();
        Assertions.assertEquals(NavigateInventoryPage.expectedProductNamesZA(), NavigateInventoryPage.actualProductNames());
    }

    @Test
    public void sortAZ(){
        NavigateInventoryPage.sortAZ();
        Assertions.assertEquals(NavigateInventoryPage.expectedProductNamesAZ(), NavigateInventoryPage.actualProductNames());
    }

    @AfterEach
    public void closeDriver(){
        driver.quit();
    }
}
