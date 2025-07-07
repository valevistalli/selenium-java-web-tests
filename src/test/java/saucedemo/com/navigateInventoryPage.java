package saucedemo.com;

import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class navigateInventoryPage {
    WebDriver driver;

    public navigateInventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addProduct(String idElement){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id(idElement)).click();
    }

    public void removeProduct(String idElement){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id(idElement)).click();
    }

    public void openProduct(String idElement){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id(idElement)).click();
    }

    public void goBack(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id("back-to-products")).click();
    }

    public void goToCart(){
        driver.findElement(By.id("shopping_cart_container")).click();
    }

    public void sortZA(){
        driver.findElement(By.cssSelector(".product_sort_container")).click();
        driver.findElement(By.cssSelector("[value='za']")).click();
    }

    public void sortAZ(){
        driver.findElement(By.cssSelector(".product_sort_container")).click();
        driver.findElement(By.cssSelector("[value='az']")).click();
    }

    public List actualProductNames(){
        List<WebElement> productElements = driver.findElements(By.className("inventory_item_name"));
        List<String> actualProductNames = new ArrayList<>();
        for (WebElement product : productElements) {
            actualProductNames.add(product.getText());
        }
        return actualProductNames;
    }

    public List expectedProductNamesZA(){
        List<WebElement> productElements = driver.findElements(By.className("inventory_item_name"));
        List<String> actualProductNames = new ArrayList<>();
        for (WebElement product : productElements) {
            actualProductNames.add(product.getText());
        }
        List<String> expectedProductNames = new ArrayList<>(actualProductNames);
        Collections.sort(expectedProductNames, Collections.reverseOrder());
        return expectedProductNames;
    }

    public List expectedProductNamesAZ(){
        List<WebElement> productElements = driver.findElements(By.className("inventory_item_name"));
        List<String> actualProductNames = new ArrayList<>();
        for (WebElement product : productElements) {
            actualProductNames.add(product.getText());
        }
        List<String> expectedProductNamesAZ = new ArrayList<>(actualProductNames);
        Collections.sort(expectedProductNamesAZ);
        return expectedProductNamesAZ;
    }

    public void sortPriceLH(){
        driver.findElement(By.cssSelector(".product_sort_container")).click();
        driver.findElement(By.cssSelector("[value='lohi']")).click();
    }

    public List expectedProductPriceLH(){
        List<WebElement> productElements = driver.findElements(By.className("inventory_item_price"));
        List<Double> actualProductPrice = new ArrayList<>();
        for (WebElement product : productElements) {
            String priceText = product.getText().replace("$", "");
            actualProductPrice.add(Double.parseDouble(priceText));
        }
        List<Double> expectedProductPrice = new ArrayList<>(actualProductPrice);
        Collections.sort(expectedProductPrice);
        return expectedProductPrice;
    }

    public List actualProductPrice(){
        List<WebElement> productElements = driver.findElements(By.className("inventory_item_price"));
        List<Double> actualProductPrice = new ArrayList<>();
        for (WebElement product : productElements) {
            String priceText = product.getText().replace("$", "");
            actualProductPrice.add(Double.parseDouble(priceText));
        }
        return actualProductPrice;
    }

    public void sortPriceHL(){
        driver.findElement(By.cssSelector(".product_sort_container")).click();
        driver.findElement(By.cssSelector("[value='hilo']")).click();
    }

    public List expectedProductPriceHL(){
        List<WebElement> productElements = driver.findElements(By.className("inventory_item_price"));
        List<Double> actualProductPrice = new ArrayList<>();
        for (WebElement product : productElements) {
            String priceText = product.getText().replace("$", "");
            actualProductPrice.add(Double.parseDouble(priceText));
        }
        List<Double> expectedProductPrice = new ArrayList<>(actualProductPrice);
        expectedProductPrice.sort(Collections.reverseOrder());
        return expectedProductPrice;
    }

    public void logout(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id("react-burger-menu-btn")).click();
        WebElement logoutLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link")));
        logoutLink.click();
    }

    public void productDetail(){
        driver.findElement((By.id("item_4_title_link"))).click();
    }

    public void goBackToProducts(){
        driver.findElement((By.id("item_4_title_link"))).click();
        driver.findElement(By.cssSelector(".btn.btn_secondary.back.btn_large.inventory_details_back_button")).click();
    }

}
