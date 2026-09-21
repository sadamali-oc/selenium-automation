package utils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener
        implements ITestListener {

    @Override
    public void onTestFailure(
            ITestResult result
    ) {

        System.out.println(
                "========================================"
        );

        System.out.println(
                "Test failed: "
                        + result.getName()
        );

        Object instance =
                result.getInstance();

        if (instance instanceof DriverProvider) {

            System.out.println(
                    "DriverProvider detected."
            );

            WebDriver driver =
                    ((DriverProvider) instance)
                            .getDriver();

            if (driver != null) {

                System.out.println(
                        "WebDriver detected."
                );

                String path =
                        ScreenshotUtil.takeScreenshot(
                                driver,
                                result.getName()
                        );

                System.out.println(
                        "Screenshot path: "
                                + path
                );

            } else {

                System.out.println(
                        "ERROR: WebDriver is null."
                );
            }

        } else {

            System.out.println(
                    "ERROR: Test class does not implement DriverProvider."
            );
        }

        System.out.println(
                "========================================"
        );
    }
}
