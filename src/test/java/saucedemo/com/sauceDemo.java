package saucedemo.com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class sauceDemo extends driver{

    public sauceDemo(WebDriver driver, String url){
        super(driver, url);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "user-name")
    private WebElement userNameField;
    public void userNameSendKeys(String userName){
        userNameField.sendKeys(userName);
    }

    @FindBy(id = "password")
    private WebElement passwordField;
    public void passwordSendKeys(String password){
        passwordField.sendKeys(password);
    }

    @FindBy(id = "login-button")
    private WebElement loginButton;
    public void loginButtonClick(){
        loginButton.click();
    }

    public String getText(String css){
        return driver.findElement(By.cssSelector(css)).getText();
    }

    public void openBurgerMenu(){
        driver.findElement(By.id("react-burger-menu-btn")).click();
    }

    public int countImages(String src){
        List<WebElement> images = driver.findElements(By.cssSelector("img[src='"+src+"']"));
        return images.size();
    }

}
