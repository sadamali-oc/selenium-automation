package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EmployeePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Employee form
    private final By newEmployeeButton =
            By.id("addNewEmployee");

    private final By employeeModal =
            By.id("addStaffModel");

    private final By loader =
            By.id("loaderwrapper");

    // Basic employee details
    private final By title =
            By.id("employeeSalutations");

    private final By name =
            By.id("employeeName");

    private final By initials =
            By.id("empNameInitials");

    private final By firstName =
            By.id("empFirstName");

    private final By lastName =
            By.id("empLastName");

    private final By address =
            By.id("employeeAddress");

    private final By nic =
            By.id("employeeNic");

    private final By email =
            By.id("employeeEmail");

    private final By mobile =
            By.id("employeeMobile");

    private final By phone =
            By.id("employeePhone");

    private final By reference =
            By.id("employeeReference");

    private final By vehicleNo =
            By.id("employeevehicleNo");

    private final By employeeStatus =
            By.id("EmployeeStatus");

    private final By remark =
            By.id("employeeRemark");

    // Bank details
    private final By bankName =
            By.id("empBankName");

    private final By bankAccount =
            By.id("empBankAccount");

    // Employee group
    private final By employeeGroupAdmin =
            By.id("EZCMP1/EZLOC16/EZEMPG-1");

    // Employment details
    private final By designation =
            By.id("employeeDesignation");

    private final By epfNo =
            By.id("employeeEPFNo");

    private final By dateJoined =
            By.id("employeeDateJoined");

    private final By location =
            By.id("employeeLocation");

    private final By dateOfBirth =
            By.id("employeeDOB");

    // Other controls
    private final By employeeImage =
            By.id("file");

    private final By multipleLocationsButton =
            By.id("addItemRackNo");

    private final By saveButton =
            By.id("saveNewEmployeeBtn");

    public EmployeePage(WebDriver driver) {

        this.driver = driver;

        this.wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(30)
                );
    }

    // =========================================================
    // Employee form
    // =========================================================

    public void clickNewEmployee() {

        // Wait for any page loader to disappear.
        try {

            wait.until(
                    ExpectedConditions.invisibilityOfElementLocated(
                            loader
                    )
            );

        } catch (Exception ignored) {

            // Continue if loader element is not present.
        }

        // Wait for New Employee button.
        WebElement button =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                newEmployeeButton
                        )
                );

        scrollToElement(button);

        button.click();

        // Wait until the employee modal exists in the DOM.
        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        employeeModal
                )
        );

        // Wait until the main employee name field is available.
        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        name
                )
        );

        // Wait until the name field is visible.
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        name
                )
        );
    }

    // =========================================================
    // Employee group
    // =========================================================

    public void selectEmployeeGroup() {

        WebElement group =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                employeeGroupAdmin
                        )
                );

        scrollToElement(group);

        group.click();
    }

    // =========================================================
    // Basic employee details
    // =========================================================

    public void selectTitle(String value) {

        WebElement field =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                title
                        )
                );

        scrollToElement(field);

        field.click();

        field.sendKeys(value);
    }

    public void enterName(String value) {

        enterField(name, value);
    }

    public void enterInitials(String value) {

        enterField(initials, value);
    }

    public void enterFirstName(String value) {

        enterField(firstName, value);
    }

    public void enterLastName(String value) {

        enterField(lastName, value);
    }

    public void enterAddress(String value) {

        enterField(address, value);
    }

    public void enterNic(String value) {

        enterField(nic, value);
    }

    public void enterEmail(String value) {

        enterField(email, value);
    }

    public void enterMobile(String value) {

        enterField(mobile, value);
    }

    public void enterPhone(String value) {

        enterField(phone, value);
    }

    public void enterReference(String value) {

        enterField(reference, value);
    }

    public void enterVehicleNo(String value) {

        enterField(vehicleNo, value);
    }

    public void selectEmployeeStatus(String value) {

        WebElement field =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                employeeStatus
                        )
                );

        scrollToElement(field);

        field.click();

        field.sendKeys(value);
    }

    public void enterRemark(String value) {

        enterField(remark, value);
    }

    // =========================================================
    // Bank details
    // =========================================================

    public void enterBankName(String value) {

        enterField(bankName, value);
    }

    public void enterBankAccount(String value) {

        enterField(bankAccount, value);
    }

    // =========================================================
    // Employment details
    // =========================================================

    public void enterDesignation(String value) {

        enterField(designation, value);
    }

    public void enterEpfNo(String value) {

        enterField(epfNo, value);
    }

    public void enterDateJoined(String value) {

        enterDateField(dateJoined, value);
    }

    public void enterLocation(String value) {

        enterField(location, value);
    }

    public void enterDateOfBirth(String value) {

        enterDateField(dateOfBirth, value);
    }

    // =========================================================
    // Image upload
    // =========================================================

    public void uploadEmployeeImage(String filePath) {

        WebElement fileInput =
                wait.until(
                        ExpectedConditions.presenceOfElementLocated(
                                employeeImage
                        )
                );

        fileInput.sendKeys(filePath);
    }

    // =========================================================
    // Multiple locations
    // =========================================================

    public void openMultipleLocations() {

        WebElement button =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                multipleLocationsButton
                        )
                );

        scrollToElement(button);

        button.click();
    }

    // =========================================================
    // Common field methods
    // =========================================================

    private void enterField(
            By locator,
            String value
    ) {

        WebElement field =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                locator
                        )
                );

        scrollToElement(field);

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        locator
                )
        );

        field.click();

        field.clear();

        field.sendKeys(value);
    }

    private void enterDateField(
            By locator,
            String value
    ) {

        WebElement field =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                locator
                        )
                );

        scrollToElement(field);

        field.click();

        field.clear();

        field.sendKeys(value);
    }

    // =========================================================
    // Utility
    // =========================================================

    private void scrollToElement(
            WebElement element
    ) {

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        element
                );
    }

    // =========================================================
    // Field value getters
    // =========================================================

    public String getNameValue() {

        return getValue(name);
    }

    public String getInitialsValue() {

        return getValue(initials);
    }

    public String getFirstNameValue() {

        return getValue(firstName);
    }

    public String getLastNameValue() {

        return getValue(lastName);
    }

    public String getAddressValue() {

        return getValue(address);
    }

    public String getNicValue() {

        return getValue(nic);
    }

    public String getEmailValue() {

        return getValue(email);
    }

    public String getMobileValue() {

        return getValue(mobile);
    }

    public String getPhoneValue() {

        return getValue(phone);
    }

    public String getReferenceValue() {

        return getValue(reference);
    }

    public String getVehicleNoValue() {

        return getValue(vehicleNo);
    }

    public String getRemarkValue() {

        return getValue(remark);
    }

    public String getBankNameValue() {

        return getValue(bankName);
    }

    public String getBankAccountValue() {

        return getValue(bankAccount);
    }

    public String getDesignationValue() {

        return getValue(designation);
    }

    public String getEpfNoValue() {

        return getValue(epfNo);
    }

    public String getLocationValue() {

        return getValue(location);
    }

    private String getValue(By locator) {

        WebElement field =
                wait.until(
                        ExpectedConditions.presenceOfElementLocated(
                                locator
                        )
                );

        return field.getAttribute("value");
    }

    // =========================================================
    // Save
    // =========================================================

    public void clickSave() {

        try {

            wait.until(
                    ExpectedConditions.invisibilityOfElementLocated(
                            loader
                    )
            );

        } catch (Exception ignored) {

            // Continue if loader is not present.
        }

        WebElement button =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                saveButton
                        )
                );

        scrollToElement(button);

        button.click();
    }

    // =========================================================
    // Form state
    // =========================================================

    public boolean isEmployeeFormClosed() {

        try {

            wait.until(
                    ExpectedConditions.invisibilityOfElementLocated(
                            employeeModal
                    )
            );

            return true;

        } catch (Exception e) {

            return false;
        }
    }

    public boolean isEmployeeFormOpen() {

        try {

            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            employeeModal
                    )
            ).isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }
}
