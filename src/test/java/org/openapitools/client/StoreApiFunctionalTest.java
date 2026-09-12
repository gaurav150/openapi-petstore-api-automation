package org.openapitools.client;

import org.openapitools.client.api.StoreApi;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

public class StoreApiFunctionalTest {
    private StoreApi storeApi;

    @BeforeClass
    public void setUp() {
        storeApi = new StoreApi();
        storeApi.getApiClient().setBasePath("https://petstore.swagger.io/v2");
    }

    @Test(description = "Verify retrieving pet inventory successfully")
    public void getInventoryTest() {

        try {
            Map<String, Integer> response = storeApi.getInventory();

            // Verify response is not null
            Assert.assertNotNull(
                    response,
                    "Inventory response should not be null"
            );

            // Verify response contains inventory data
            Assert.assertFalse(
                    response.isEmpty(),
                    "Inventory response should contain data"
            );

            // Verify inventory values are not null
            for (Map.Entry<String, Integer> entry : response.entrySet()) {

                Assert.assertNotNull(
                        entry.getKey(),
                        "Inventory status should not be null"
                );

                Assert.assertNotNull(
                        entry.getValue(),
                        "Inventory count should not be null"
                );

                // Inventory count should not be negative
                Assert.assertTrue(
                        entry.getValue() >= 0,
                        "Inventory count should not be negative"
                );
            }

        } catch (ApiException e) {

            Assert.fail(
                    "Get inventory API failed. Status code: "
                            + e.getCode()
                            + ", Response: "
                            + e.getResponseBody()
            );
        }
    }

    // Verify inventory counts are not negative
    @Test(description = "Verify inventory does not contain negative quantities")
    public void verifyInventoryDoesNotContainNegativeQuantityTest() {

        try {

            Map<String, Integer> response = storeApi.getInventory();

            Assert.assertNotNull(
                    response,
                    "Inventory response should not be null"
            );

            // Negative test case for inventory quantity
            for (Map.Entry<String, Integer> entry : response.entrySet()) {

                Assert.assertTrue(
                        entry.getValue() >= 0,
                        "Inventory quantity should not be negative for status: "
                                + entry.getKey()
                );
            }

        } catch (ApiException e) {

            Assert.fail(
                    "Get inventory API failed. Status code: "
                            + e.getCode()
                            + ", Response: "
                            + e.getResponseBody()
            );
        }
    }

    // Verify inventory status is not null or empty
    @Test(description = "Verify inventory does not contain null or empty status")
    public void verifyInventoryStatusIsValidTest() {

        try {

            Map<String, Integer> response = storeApi.getInventory();

            Assert.assertNotNull(
                    response,
                    "Inventory response should not be null"
            );

            // Negative test case for inventory status
            for (String status : response.keySet()) {

                Assert.assertNotNull(
                        status,
                        "Inventory status should not be null"
                );

                Assert.assertFalse(
                        status.trim().isEmpty(),
                        "Inventory status should not be empty"
                );
            }

        } catch (ApiException e) {

            Assert.fail(
                    "Get inventory API failed. Status code: "
                            + e.getCode()
                            + ", Response: "
                            + e.getResponseBody()
            );
        }
    }

    // Verify inventory quantity is not null
    @Test(description = "Verify inventory does not contain null quantities")
    public void verifyInventoryQuantityIsNotNullTest() {

        try {

            Map<String, Integer> response = storeApi.getInventory();

            Assert.assertNotNull(
                    response,
                    "Inventory response should not be null"
            );

            // Negative test case for inventory quantity
            for (Map.Entry<String, Integer> entry : response.entrySet()) {

                Assert.assertNotNull(
                        entry.getValue(),
                        "Inventory quantity should not be null for status: "
                                + entry.getKey()
                );
            }

        } catch (ApiException e) {

            Assert.fail(
                    "Get inventory API failed. Status code: "
                            + e.getCode()
                            + ", Response: "
                            + e.getResponseBody()
            );
        }
    }
}
