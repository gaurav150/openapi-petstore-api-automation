package org.openapitools.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.testng.annotations.DataProvider;

import java.io.InputStream;

public class TestDataProvider {
    @DataProvider(name = "petTestData")
    public Object[][] petTestData() {

        try {

            ObjectMapper objectMapper = new ObjectMapper();

            InputStream inputStream = getClass()
                    .getClassLoader()
                    .getResourceAsStream("testData/petData.json");

            PetTestData[] pets =
                    objectMapper.readValue(
                            inputStream,
                            PetTestData[].class
                    );

            Object[][] data = new Object[pets.length][1];

            for (int i = 0; i < pets.length; i++) {
                data[i][0] = pets[i];
            }

            return data;

        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to load pet test data from petData.json",
                    e
            );
        }
    }

    @DataProvider(name = "orderTestData")
    public Object[][] orderTestData() {
        try {

            ObjectMapper objectMapper = new ObjectMapper();

            // Register Java 8 date/time support
            objectMapper.registerModule(new JavaTimeModule());

            InputStream inputStream = getClass()
                    .getClassLoader()
                    .getResourceAsStream("testData/orderData.json");

            OrderTestData[] orders =
                    objectMapper.readValue(
                            inputStream,
                            OrderTestData[].class
                    );

            Object[][] data = new Object[orders.length][1];

            for (int i = 0; i < orders.length; i++) {
                data[i][0] = orders[i];
            }

            return data;

        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to load order test data from orderData.json",
                    e
            );
        }
    }
}
