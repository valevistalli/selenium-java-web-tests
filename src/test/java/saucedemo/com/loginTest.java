package saucedemo.com;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class loginTest {

    private WebDriver driver;
    private sauceDemo SauceDemo;

    @BeforeEach
    public void setUp(){
        driver = new ChromeDriver();
        SauceDemo = new sauceDemo(driver, "https://www.saucedemo.com");
    }

    @Test
    public void validLoginTest(){
        SauceDemo.userNameSendKeys("standard_user");
        SauceDemo.passwordSendKeys("secret_sauce");
        SauceDemo.loginButtonClick();
        Assertions.assertEquals("Swag Labs", SauceDemo.getText(".app_logo"));
    }

    @Test
    public void invalidLoginTest(){
        SauceDemo.userNameSendKeys("locked_out_user");
        SauceDemo.passwordSendKeys("secret_sauce");
        SauceDemo.loginButtonClick();
        Assertions.assertEquals("Epic sadface: Sorry, this user has been locked out.",SauceDemo.getText(".error-message-container.error"));
    }

    @Test
    public void problemUserLoginTest(){
        SauceDemo.userNameSendKeys("problem_user");
        SauceDemo.passwordSendKeys("secret_sauce");
        SauceDemo.loginButtonClick();
        Assertions.assertEquals(6,SauceDemo.countImages("/static/media/sl-404.168b1cce.jpg"),"The images are not the same");
    }

    @Test
    public void performanceGlitchUserLoginTest(){
        SauceDemo.userNameSendKeys("performance_glitch_user");
        SauceDemo.passwordSendKeys("secret_sauce");
        SauceDemo.loginButtonClick();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_list")));
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertTrue(currentUrl.contains("inventory"), "Inventory page should be loaded");
    }

    @AfterEach
    public void closeDriver(){
        driver.quit();
    }
}
