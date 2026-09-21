package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pages.EmployeePage;
import pages.LoginPage;
import utils.ConfigReader;
import utils.CsvDataReader;
import utils.DriverFactory;
import utils.DriverProvider;

import java.time.Duration;

@Listeners(utils.TestListener.class)
public class EmployeeDDTTest
        implements DriverProvider {

    private WebDriver driver;
    private EmployeePage employeePage;

    @BeforeMethod
    public void setUp() {

        driver =
                DriverFactory.createDriver();

        driver.get(
                ConfigReader.get("base.url")
        );

        LoginPage loginPage =
                new LoginPage(driver);

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

    @DataProvider(name = "employeeData")
    public Object[][] employeeData() {

        return CsvDataReader.readEmployeeData();
    }

    @Test(
            dataProvider = "employeeData"
    )
    public void verifyEmployeeSaveWithDifferentData(

            String name,
            String initials,
            String firstName,
            String lastName,
            String address,
            String mobile,
            String phone,
            String reference,
            String vehicleNo,
            String designation,
            String epfNo,
            String location,
            String dateOfBirth
    ) {

        String timestamp =
                String.valueOf(
                        System.currentTimeMillis()
                );

        String uniqueNic =
                "299"
                        + timestamp.substring(
                        timestamp.length() - 9
                );

        String uniqueEmail =
                "ddt.employee."
                        + timestamp
                        + "@example.com";

        employeePage.selectEmployeeGroup();

        employeePage.selectTitle(
                "Mr."
        );

        employeePage.enterName(
                name
        );

        employeePage.enterInitials(
                initials
        );

        employeePage.enterFirstName(
                firstName
        );

        employeePage.enterLastName(
                lastName
        );

        employeePage.enterAddress(
                address
        );

        employeePage.enterNic(
                uniqueNic
        );

        employeePage.enterEmail(
                uniqueEmail
        );

        employeePage.enterMobile(
                mobile
        );

        employeePage.enterPhone(
                phone
        );

        employeePage.enterReference(
                reference
        );

        employeePage.enterVehicleNo(
                vehicleNo
        );

        employeePage.selectEmployeeStatus(
                "Active"
        );

        employeePage.enterRemark(
                "DDT Automation Test"
        );

        employeePage.enterBankName(
                "Test Bank"
        );

        employeePage.enterBankAccount(
                "1234567890"
        );

        employeePage.enterDesignation(
                designation
        );

        employeePage.enterEpfNo(
                epfNo
        );

        employeePage.enterDateJoined(
                "2026-09-21"
        );

        employeePage.enterLocation(
                location
        );

        employeePage.enterDateOfBirth(
                dateOfBirth
        );

        Assert.assertEquals(
                employeePage.getNameValue(),
                name,
                "Name was not entered correctly."
        );

        Assert.assertEquals(
                employeePage.getFirstNameValue(),
                firstName,
                "First Name was not entered correctly."
        );

        Assert.assertEquals(
                employeePage.getLastNameValue(),
                lastName,
                "Last Name was not entered correctly."
        );

        Assert.assertEquals(
                employeePage.getMobileValue(),
                mobile,
                "Mobile was not entered correctly."
        );

        Assert.assertEquals(
                employeePage.getDesignationValue(),
                designation,
                "Designation was not entered correctly."
        );

        Assert.assertEquals(
                employeePage.getLocationValue(),
                location,
                "Location was not entered correctly."
        );

        employeePage.clickSave();

        Assert.assertTrue(
                employeePage.isEmployeeFormClosed(),
                "Employee form did not close after saving."
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
