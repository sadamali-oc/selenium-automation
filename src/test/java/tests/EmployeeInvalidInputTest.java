package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import pages.EmployeePage;
import pages.LoginPage;
import utils.ConfigReader;
import utils.DriverFactory;

import java.time.Duration;

public class EmployeeInvalidInputTest {

    private WebDriver driver;
    private EmployeePage employeePage;

    @BeforeMethod
    public void setUp() {

        driver = DriverFactory.createDriver();

        driver.get(ConfigReader.get("baseUrl"));

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                ConfigReader.get("username"),
                ConfigReader.get("password")
        );

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(30));

        wait.until(
                ExpectedConditions.urlContains("/Dashboard")
        );

        driver.get(ConfigReader.get("employeeUrl"));

        wait.until(
                ExpectedConditions.urlContains("/Employee")
        );

        employeePage = new EmployeePage(driver);

        employeePage.clickNewEmployee();
    }

    @Test
    public void verifyInvalidEmail() {

        employeePage.selectEmployeeGroup();
        employeePage.selectTitle("Mr.");

        employeePage.enterName("Invalid Email Test");
        employeePage.enterFirstName("Invalid");
        employeePage.enterLastName("Email");

        employeePage.enterNic("299512345678");

        employeePage.enterEmail("invalid-email");

        employeePage.enterMobile("0771234567");

        employeePage.enterDesignation("QA Tester");

        employeePage.clickSave();

        Assert.assertTrue(
                employeePage.isEmployeeFormOpen(),
                "Employee form closed with invalid email."
        );
    }

    @Test
    public void verifyInvalidMobile() {

        employeePage.selectEmployeeGroup();
        employeePage.selectTitle("Mr.");

        employeePage.enterName("Invalid Mobile Test");
        employeePage.enterFirstName("Invalid");
        employeePage.enterLastName("Mobile");

        employeePage.enterNic("299512345678");

        employeePage.enterEmail(
                "invalid.mobile@example.com"
        );

        employeePage.enterMobile("077ABC123");

        employeePage.enterDesignation("QA Tester");

        employeePage.clickSave();

        Assert.assertTrue(
                employeePage.isEmployeeFormOpen(),
                "Employee form closed with invalid mobile."
        );
    }

    @Test
    public void verifyInvalidNic() {

        employeePage.selectEmployeeGroup();
        employeePage.selectTitle("Mr.");

        employeePage.enterName("Invalid NIC Test");
        employeePage.enterFirstName("Invalid");
        employeePage.enterLastName("NIC");

        employeePage.enterNic("ABC123");

        employeePage.enterEmail(
                "invalid.nic@example.com"
        );

        employeePage.enterMobile("0771234567");

        employeePage.enterDesignation("QA Tester");

        employeePage.clickSave();

        Assert.assertTrue(
                employeePage.isEmployeeFormOpen(),
                "Employee form closed with invalid NIC."
        );
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}