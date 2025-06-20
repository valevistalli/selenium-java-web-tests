package saucedemo.com;

import org.openqa.selenium.WebDriver;

public class driver {
    public WebDriver driver;

    public driver(WebDriver driver, String url){
        this.driver = driver;
        this.driver.get(url);
    }

}
