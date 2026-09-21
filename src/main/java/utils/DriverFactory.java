package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

    public static WebDriver createDriver() {
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        return driver;
    }
}