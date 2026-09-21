package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.EmployeePage;
import pages.LoginPage;
import utils.ConfigReader;
import utils.DriverFactory;

import java.time.Duration;

public class EmployeeRequiredFieldsTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private EmployeePage employeePage;

    @BeforeMethod
    public void setUp() {

        driver = DriverFactory.createDriver();
        driver.get("https://uat.ezuite.com/");

        loginPage = new LoginPage(driver);

        String username = ConfigReader.get("username");
        String password = ConfigReader.get("password");

        loginPage.login(username, password);

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(30));

        wait.until(
                ExpectedConditions.urlContains("/Dashboard")
        );

        driver.get(
                "https://uat.ezuite.com/Distribute/DistributeMain/Employee"
        );

        wait.until(
                ExpectedConditions.urlContains("/Employee")
        );

        employeePage = new EmployeePage(driver);
    }

    @Test
    public void verifyRequiredFieldValidation() {

        employeePage.clickNewEmployee();

        // Leave required fields empty and attempt to save
        employeePage.clickSave();

        // Verify that the employee form remains open
        Assert.assertTrue(
                employeePage.isEmployeeFormOpen(),
                "Employee was saved even though required fields were empty."
        );
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}
