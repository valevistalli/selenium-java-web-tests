package saucedemo.com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class checkOut {
    WebDriver driver;

    public checkOut(WebDriver driver) {
        this.driver = driver;
    }

    public void successfulCheckOutProcess(){
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement checkout = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));
        checkout.click();
        WebElement firstName = wait.until(ExpectedConditions.elementToBeClickable(By.id("first-name")));
        firstName.sendKeys("UserName");
        WebElement lastName = driver.findElement(By.id("last-name"));
        lastName.sendKeys("UserLastName");
        WebElement zipCode = driver.findElement(By.id("postal-code"));
        zipCode.sendKeys("5000");
        driver.findElement(By.id("continue")).click();
        WebElement finishBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("finish")));
        finishBtn.click();
    }

}
