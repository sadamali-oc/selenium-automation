package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By usernameField = By.id("userName");

    private By nextButton = By.xpath(
            "//input[@type='button' and @value='Next']"
    );

    private By passwordField = By.id("password");

    private By loginButton = By.xpath(
            "//input[@type='button' and @value='Login']"
    );

    private By sessionWarning = By.xpath(
            "//*[contains(text(),'You are currently logged in')]"
    );

    private By yesButton = By.xpath(
            "//button[normalize-space()='Yes'] | //input[@value='Yes']"
    );

    public LoginPage(WebDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(20)
        );
    }

    public void enterUsername(String username) {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(usernameField)
        ).clear();

        driver.findElement(usernameField).sendKeys(username);
    }

    public void clickNext() {

        wait.until(
                ExpectedConditions.elementToBeClickable(nextButton)
        ).click();
    }

    public void enterPassword(String password) {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(passwordField)
        ).clear();

        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {

        wait.until(
                ExpectedConditions.elementToBeClickable(loginButton)
        ).click();
    }

    public void handleExistingSessionPopup() {

        try {

            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            sessionWarning
                    )
            );

            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            yesButton
                    )
            ).click();

        } catch (Exception e) {

            // Popup did not appear
        }
    }

    public void login(String username, String password) {

        enterUsername(username);

        clickNext();

        enterPassword(password);

        clickLogin();

        handleExistingSessionPopup();
    }
}