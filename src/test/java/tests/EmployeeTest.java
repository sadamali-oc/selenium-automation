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

        // Open login page
        driver.get(ConfigReader.get("base.url"));

        loginPage = new LoginPage(driver);

        // Login
        loginPage.login(
                ConfigReader.get("username"),
                ConfigReader.get("password")
        );

        // Wait for Dashboard
        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(30));

        wait.until(
                ExpectedConditions.urlContains("/Dashboard")
        );

        // Open Employee page
        driver.get(ConfigReader.get("employee.url"));

        // Wait for Employee page
        wait.until(
                ExpectedConditions.urlContains("/Employee")
        );

        employeePage = new EmployeePage(driver);
    }

    @Test
    public void verifyEmployeeSave() {

        /*
         * Generate unique test data.
         * This prevents duplicate NIC/email errors
         * when the test is executed multiple times.
         */
        String timestamp =
                String.valueOf(System.currentTimeMillis());

        String uniqueNic =
                "299" + timestamp.substring(timestamp.length() - 9);

        String uniqueEmail =
                "test.employee." + timestamp + "@example.com";

        // Open New Employee form
        employeePage.clickNewEmployee();

        // Employee Group
        employeePage.selectEmployeeGroup();

        // Title
        employeePage.selectTitle("Mr.");

        // Basic employee information
        employeePage.enterName("Test Employee");
        employeePage.enterInitials("TE");
        employeePage.enterFirstName("Test");
        employeePage.enterLastName("Employee");
        employeePage.enterAddress("No. 100, Test Street");

        // Unique NIC
        employeePage.enterNic(uniqueNic);

        // Unique email
        employeePage.enterEmail(uniqueEmail);

        // Contact details
        employeePage.enterMobile("0771234567");
        employeePage.enterPhone("0112345678");

        // Other details
        employeePage.enterReference("REF001");
        employeePage.enterVehicleNo("TEST-1234");

        // Employee status
        employeePage.selectEmployeeStatus("Active");

        // Remark
        employeePage.enterRemark(
                "Selenium Automation Test"
        );

        // Bank details
        employeePage.enterBankName("Test Bank");
        employeePage.enterBankAccount("1234567890");

        // Employment details
        employeePage.enterDesignation("QA Tester");
        employeePage.enterEpfNo("EPF001");
        employeePage.enterDateJoined("2026-09-17");
        employeePage.enterLocation("Head Office");
        employeePage.enterDateOfBirth("1995-01-01");

        // ==========================================
        // VERIFY ENTERED VALUES
        // ==========================================

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
                uniqueNic,
                "NIC was not entered correctly."
        );

        Assert.assertEquals(
                employeePage.getEmailValue(),
                uniqueEmail,
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

        // ==========================================
        // SAVE EMPLOYEE
        // ==========================================

        employeePage.clickSave();

        // Verify employee form closes after save
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
