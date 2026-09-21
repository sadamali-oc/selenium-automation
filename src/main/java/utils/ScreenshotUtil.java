package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class ScreenshotUtil {

    public static String takeScreenshot(
            WebDriver driver,
            String testName
    ) {

        try {

            if (driver == null) {
                System.out.println(
                        "Screenshot not taken: WebDriver is null."
                );
                return null;
            }

            TakesScreenshot screenshot =
                    (TakesScreenshot) driver;

            File source =
                    screenshot.getScreenshotAs(
                            OutputType.FILE
                    );

            // Project root / screenshots
            Path directory =
                    Path.of(
                            System.getProperty("user.dir"),
                            "screenshots"
                    );

            Files.createDirectories(directory);

            String fileName =
                    testName +
                            "_" +
                            System.currentTimeMillis() +
                            ".png";

            Path destination =
                    directory.resolve(fileName);

            Files.copy(
                    source.toPath(),
                    destination,
                    StandardCopyOption.REPLACE_EXISTING
            );

            System.out.println(
                    "Screenshot saved successfully: "
                            + destination.toAbsolutePath()
            );

            return destination.toString();

        } catch (Exception e) {

            System.out.println(
                    "Failed to capture screenshot."
            );

            e.printStackTrace();

            return null;
        }
    }
}
