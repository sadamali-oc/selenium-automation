package tests;

import org.openqa.selenium.Alert;
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

public class EmployeeXSSTest {

    private WebDriver driver;
    private EmployeePage employeePage;

    @BeforeMethod
    public void setUp() {

        driver = DriverFactory.createDriver();

        // Open login page
        driver.get(ConfigReader.get("base.url"));

        LoginPage loginPage = new LoginPage(driver);

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

        // Open New Employee form
        employeePage.clickNewEmployee();
    }

    @Test
    public void verifyXSSInRemarkField() {

        /*
         * Generate unique values so the test
         * does not fail because of duplicate data.
         */
        String timestamp =
                String.valueOf(System.currentTimeMillis());

        String uniqueNic =
                "299" + timestamp.substring(timestamp.length() - 9);

        String uniqueEmail =
                "xss.test." + timestamp + "@example.com";

        // Employee Group
        employeePage.selectEmployeeGroup();

        // Title
        employeePage.selectTitle("Mr.");

        // Basic employee information
        employeePage.enterName("XSS Test");
        employeePage.enterInitials("XT");
        employeePage.enterFirstName("XSS");
        employeePage.enterLastName("Test");
        employeePage.enterAddress("No. 100, Test Street");

        // Unique NIC
        employeePage.enterNic(uniqueNic);

        // Unique email
        employeePage.enterEmail(uniqueEmail);

        // Contact details
        employeePage.enterMobile("0771234567");
        employeePage.enterPhone("0112345678");

        // Other details
        employeePage.enterReference("XSS001");
        employeePage.enterVehicleNo("XSS-1234");

        // Employee status
        employeePage.selectEmployeeStatus("Active");

        /*
         * XSS payload is entered only into the
         * field being security-tested.
         */
        String xssPayload =
                "<script>alert('XSS')</script>";

        employeePage.enterRemark(xssPayload);

        // Bank details
        employeePage.enterBankName("Test Bank");
        employeePage.enterBankAccount("1234567890");

        // Employment details
        employeePage.enterDesignation("QA Tester");
        employeePage.enterEpfNo("EPF001");
        employeePage.enterDateJoined("2026-09-21");
        employeePage.enterLocation("Head Office");
        employeePage.enterDateOfBirth("1995-01-01");

        // Save employee
        employeePage.clickSave();

        /*
         * Check whether the XSS payload executed.
         */
        boolean alertDisplayed = false;

        try {

            WebDriverWait alertWait =
                    new WebDriverWait(driver, Duration.ofSeconds(3));

            Alert alert = alertWait.until(
                    ExpectedConditions.alertIsPresent()
            );

            alertDisplayed = true;

            alert.accept();

        } catch (Exception ignored) {

            // No JavaScript alert appeared.
        }

        Assert.assertFalse(
                alertDisplayed,
                "Potential XSS vulnerability: JavaScript alert was executed."
        );
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}
