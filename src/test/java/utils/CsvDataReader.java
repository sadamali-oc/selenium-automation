package utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CsvDataReader {

    public static Object[][] readEmployeeData() {

        List<Object[]> data =
                new ArrayList<>();

        try {

            InputStream input =
                    CsvDataReader.class
                            .getClassLoader()
                            .getResourceAsStream(
                                    "testdata/employee_data.csv"
                            );

            if (input == null) {

                throw new RuntimeException(
                        "employee_data.csv not found."
                );
            }

            Reader reader =
                    new InputStreamReader(
                            input,
                            StandardCharsets.UTF_8
                    );

            Iterable<CSVRecord> records =
                    CSVFormat.DEFAULT
                            .builder()
                            .setHeader()
                            .setSkipHeaderRecord(true)
                            .build()
                            .parse(reader);

            for (CSVRecord record : records) {

                data.add(
                        new Object[]{

                                record.get("name"),

                                record.get("initials"),

                                record.get("firstName"),

                                record.get("lastName"),

                                record.get("address"),

                                record.get("mobile"),

                                record.get("phone"),

                                record.get("reference"),

                                record.get("vehicleNo"),

                                record.get("designation"),

                                record.get("epfNo"),

                                record.get("location"),

                                record.get("dateOfBirth")
                        }
                );
            }

            reader.close();

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to read employee test data.",
                    e
            );
        }

        return data.toArray(
                new Object[0][]
        );
    }
}