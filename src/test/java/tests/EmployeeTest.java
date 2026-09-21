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

public class EmployeeTest {

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
    public void verifyEmployeeSave() {

        employeePage.clickNewEmployee();

        // Employee Group
        employeePage.selectEmployeeGroup();

        // Employee Information
        employeePage.selectTitle("Mr.");
        employeePage.enterName("Test Employee");
        employeePage.enterInitials("TE");
        employeePage.enterFirstName("Test");
        employeePage.enterLastName("Employee");
        employeePage.enterAddress("No. 100, Test Street");
        employeePage.enterNic("299512345678");
        employeePage.enterEmail("test.employee@example.com");
        employeePage.enterMobile("0771234567");
        employeePage.enterPhone("0112345678");
        employeePage.enterReference("REF001");
        employeePage.enterVehicleNo("TEST-1234");
        employeePage.selectEmployeeStatus("Active");
        employeePage.enterRemark("Selenium Automation Test");

        // Bank Information
        employeePage.enterBankName("Test Bank");
        employeePage.enterBankAccount("1234567890");

        // Employment Information
        employeePage.enterDesignation("QA Tester");
        employeePage.enterEpfNo("EPF001");
        employeePage.enterDateJoined("2026-09-17");
        employeePage.enterLocation("Head Office");
        employeePage.enterDateOfBirth("1995-01-01");

        // Verify Employee Information
        Assert.assertEquals(
                employeePage.getNameValue(),
                "Test Employee",
                "Name was not entered correctly."
        );

        Assert.assertEquals(
                employeePage.getInitialsValue(),
                "TE",
                "Initials were not entered correctly."
        );

        Assert.assertEquals(
                employeePage.getFirstNameValue(),
                "Test",
                "First Name was not entered correctly."
        );

        Assert.assertEquals(
                employeePage.getLastNameValue(),
                "Employee",
                "Last Name was not entered correctly."
        );

        Assert.assertEquals(
                employeePage.getAddressValue(),
                "No. 100, Test Street",
                "Address was not entered correctly."
        );

        Assert.assertEquals(
                employeePage.getNicValue(),
                "299512345678",
                "NIC was not entered correctly."
        );

        Assert.assertEquals(
                employeePage.getEmailValue(),
                "test.employee@example.com",
                "Email was not entered correctly."
        );

        Assert.assertEquals(
                employeePage.getMobileValue(),
                "0771234567",
                "Mobile was not entered correctly."
        );

        Assert.assertEquals(
                employeePage.getPhoneValue(),
                "0112345678",
                "Phone was not entered correctly."
        );

        Assert.assertEquals(
                employeePage.getReferenceValue(),
                "REF001",
                "Reference was not entered correctly."
        );

        Assert.assertEquals(
                employeePage.getVehicleNoValue(),
                "TEST-1234",
                "Vehicle No was not entered correctly."
        );

        Assert.assertEquals(
                employeePage.getRemarkValue(),
                "Selenium Automation Test",
                "Remark was not entered correctly."
        );

        // Verify Bank Information
        Assert.assertEquals(
                employeePage.getBankNameValue(),
                "Test Bank",
                "Bank Name was not entered correctly."
        );

        Assert.assertEquals(
                employeePage.getBankAccountValue(),
                "1234567890",
                "Bank Account was not entered correctly."
        );

        // Verify Employment Information
        Assert.assertEquals(
                employeePage.getDesignationValue(),
                "QA Tester",
                "Designation was not entered correctly."
        );

        Assert.assertEquals(
                employeePage.getEpfNoValue(),
                "EPF001",
                "EPF No was not entered correctly."
        );

        Assert.assertEquals(
                employeePage.getLocationValue(),
                "Head Office",
                "Location was not entered correctly."
        );

        // Save Employee
        employeePage.clickSave();

        // Verify Successful Save
        Assert.assertTrue(
                employeePage.isEmployeeFormClosed(),
                "Employee form did not close after saving."
        );
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}
