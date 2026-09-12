package org.openapitools.client;

import com.fasterxml.jackson.databind.ObjectMapper;
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
}
