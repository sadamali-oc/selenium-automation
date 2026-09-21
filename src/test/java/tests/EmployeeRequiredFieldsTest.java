package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pages.EmployeePage;
import pages.LoginPage;
import utils.ConfigReader;
import utils.DriverFactory;
import utils.DriverProvider;

import java.time.Duration;

@Listeners(utils.TestListener.class)
public class EmployeeRequiredFieldsTest
        implements DriverProvider {

    private WebDriver driver;
    private LoginPage loginPage;
    private EmployeePage employeePage;

    @BeforeMethod
    public void setUp() {

        driver = DriverFactory.createDriver();

        driver.get(
                ConfigReader.get("base.url")
        );

        loginPage = new LoginPage(driver);

        loginPage.login(
                ConfigReader.get("username"),
                ConfigReader.get("password")
        );

        WebDriverWait wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(30)
                );

        wait.until(
                ExpectedConditions.urlContains(
                        "/Dashboard"
                )
        );

        driver.get(
                ConfigReader.get("employee.url")
        );

        wait.until(
                ExpectedConditions.urlContains(
                        "/Employee"
                )
        );

        employeePage =
                new EmployeePage(driver);

        employeePage.clickNewEmployee();
    }

    @Test
    public void verifyRequiredFieldValidation() {

        employeePage.clickSave();

        // TEMPORARY FAILURE ONLY
        // This is used to verify screenshot capture.

        Assert.assertTrue(
                employeePage.isEmployeeFormOpen(),
                "Employee was saved even though required fields were empty."

        );
    }

    @Override
    public WebDriver getDriver() {

        return driver;
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}
