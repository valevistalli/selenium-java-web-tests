package saucedemo.com;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class driver {
    public WebDriver driver;

    public driver(WebDriver driver, String url){
        this.driver = driver;
        this.driver.get(url);
    }

    public void wait(int seconds){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }
}
