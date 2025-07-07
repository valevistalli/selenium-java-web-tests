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

    @Test
    public void sortPriceLH(){
        NavigateInventoryPage.sortPriceLH();
        Assertions.assertEquals(NavigateInventoryPage.expectedProductPriceLH(), NavigateInventoryPage.actualProductPrice());
    }

    @Test
    public void sortPriceHL(){
        NavigateInventoryPage.sortPriceHL();
        Assertions.assertEquals(NavigateInventoryPage.expectedProductPriceHL(), NavigateInventoryPage.actualProductPrice());
    }

    @Test
    public void logoutTest(){
        NavigateInventoryPage.logout();
        Assertions.assertTrue(driver.findElement(By.id("login-button")).isEnabled());
    }

    @Test
    public void productDetail(){
        NavigateInventoryPage.productDetail();
        Assertions.assertEquals("carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.", driver.findElement(By.cssSelector(".inventory_details_desc.large_size")).getText());
    }

    @Test
    public void goBackToProducts(){
        NavigateInventoryPage.goBackToProducts();
        Assertions.assertTrue(driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).isEnabled());
    }

    @AfterEach
    public void closeDriver(){
        driver.quit();
    }
}
