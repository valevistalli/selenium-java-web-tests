package saucedemo.com;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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

    @AfterEach
    public void closeDriver(){
        driver.quit();
    }
}
