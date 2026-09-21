package data;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class TestDataProvider {

    @DataProvider(name = "employeeData")
    public static Object[][] employeeData() {

        List<Object[]> data =
                new ArrayList<>();

        String file =
                "src/test/resources/employee_test_data.csv";

        try (BufferedReader reader =
                     new BufferedReader(
                             new FileReader(file)
                     )) {

            String line;

            // Skip header
            reader.readLine();

            while ((line = reader.readLine()) != null) {

                String[] values =
                        line.split(",");

                data.add(values);
            }

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to read employee CSV",
                    e
            );
        }

        return data.toArray(
                new Object[0][]
        );
    }
}