package org.openapitools.client;

import org.openapitools.client.api.StoreApi;
import org.openapitools.client.model.Order;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import static org.openapitools.client.utils.TestUtils.getOrder;

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

    // Create Store Order ID
    @Test(description = "Verify Store order added successfully")
    public void postCreateStoreOrder() {
        try {
            OffsetDateTime shipDate = OffsetDateTime.now();
            Long petId = 1223L;
            int quantity = 2;
            Order order = getOrder(
                    10L,
                    petId,
                    quantity,
                    shipDate,
                    Order.StatusEnum.PLACED,
                    false
            );
            Order response = storeApi.placeOrder(order);

            // Validate response
            Assert.assertNotNull(response, "Response should not be null");

            // Validate Ship date
            Assert.assertNotNull(
                    order.getShipDate(),
                    "Order request ship date should not be null"
            );

            Assert.assertNotNull(
                    response.getShipDate(),
                    "Order response ship date should not be null"
            );

            Assert.assertEquals(
                    response.getShipDate().toInstant().truncatedTo(ChronoUnit.MILLIS),
                    order.getShipDate().toInstant().truncatedTo(ChronoUnit.MILLIS),
                    "Ship Date should match"
            );

            // Validate pet ID
            Assert.assertEquals(response.getPetId(), petId,
                    "Pet ID Should match");

            Assert.assertEquals(
                    response.getStatus(),
                    Order.StatusEnum.PLACED,
                    "Order status should be PLACED"
            );

        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(description = "Verify Store orders added successfully",
            dataProvider = "orderTestData",
            dataProviderClass = TestDataProvider.class)
    public void postCreateStoreOrders(OrderTestData testData) {
        try {
            System.out.println("========================================");
            System.out.println("Starting test: postCreateStoreOrders");
            System.out.println("Test Data:");
            System.out.println("Order ID     : " + testData.getId());
            System.out.println("Pet ID       : " + testData.getPetId());
            System.out.println("Quantity     : " + testData.getQuantity());
            System.out.println("Ship Date    : " + testData.getShipDate());
            System.out.println("Status       : " + testData.getStatus());
            System.out.println("Complete     : " + testData.getComplete());
            System.out.println("========================================");

            Order order = getOrder(
                    testData.getId(),
                    testData.getPetId(),
                    testData.getQuantity(),
                    testData.getShipDate(),
                    Order.StatusEnum.fromValue(testData.getStatus()),
                    testData.getComplete()
            );
            Order response = storeApi.placeOrder(order);

            // Validate response
            Assert.assertNotNull(response, "Response should not be null");

            // Validate Order ID
            Assert.assertEquals(
                    response.getId(),
                    order.getId(),
                    "Order ID should match"
            );

            // Validate Pet ID
            Assert.assertEquals(
                    response.getPetId(),
                    order.getPetId(),
                    "Pet ID should match"
            );

            // Validate Quantity
            Assert.assertEquals(
                    response.getQuantity(),
                    order.getQuantity(),
                    "Order quantity should match"
            );

            // Validate Ship Date is not null in request
            Assert.assertNotNull(
                    order.getShipDate(),
                    "Order request ship date should not be null"
            );

            // Validate Ship Date is not null in response
            Assert.assertNotNull(
                    response.getShipDate(),
                    "Order response ship date should not be null"
            );

            // Validate Ship Date
            // Compare Instant because API may return a different timezone offset
            Assert.assertEquals(
                    response.getShipDate().toInstant(),
                    order.getShipDate().toInstant(),
                    "Order ship date should match"
            );

            // Validate Status
            Assert.assertEquals(
                    response.getStatus(),
                    order.getStatus(),
                    "Order status should match"
            );

            // Validate Complete flag
            Assert.assertEquals(
                    response.getComplete(),
                    order.getComplete(),
                    "Order complete status should match"
            );

        } catch (Exception e) {
            Assert.fail(
                    "API call failed: " + e.getMessage()
            );
        }
    }
}
