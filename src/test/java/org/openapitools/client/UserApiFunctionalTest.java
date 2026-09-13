package org.openapitools.client;

import org.openapitools.client.api.UserApi;
import org.openapitools.client.model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.openapitools.client.utils.TestUtils.getUser;

public class UserApiFunctionalTest {
    private UserApi userApi;

    @BeforeClass
    public void setUp() {
        userApi = new UserApi();
        userApi.getApiClient().setBasePath("https://petstore.swagger.io/v2");
    }

    // Positive Test cases
    @Test(description = "Verify User Created with all valid fields")
    public void postUserCreatedWithValidData() {
        try {
            User user = getUser(23L,"Rohit","Kumar", "1234567890",
                    "abcn@example.com", "rohitK","qwerty",  1);

            ApiResponse<Void> response = userApi.createUserWithHttpInfo(user);

            // validate that response is not null
            Assert.assertNotNull(response,"response should not be null");

            // validate status code to be 200
            Assert.assertEquals(response.getStatusCode(),200,
                    "Expected  HTTP Status code to be 200");

            // Verify created user using GET
            Assert.assertNotNull(user.getUsername());
            User createdUser = userApi.getUserByName(user.getUsername());

            Assert.assertNotNull(createdUser, "Created user should be retrievable");

            Assert.assertEquals(
                    createdUser.getUsername(),
                    user.getUsername(),
                    "Username should match"
            );

            Assert.assertEquals(
                    createdUser.getFirstName(),
                    user.getFirstName(),
                    "First name should match"
            );

            Assert.assertEquals(
                    createdUser.getLastName(),
                    user.getLastName(),
                    "Last name should match"
            );

            Assert.assertEquals(
                    createdUser.getEmail(),
                    user.getEmail(),
                    "Email should match"
            );

        } catch (ApiException e) {
            Assert.fail(
                    "Create user API failed. Status code: "
                            + e.getCode()
                            + ", Response: "
                            + e.getResponseBody()
            );
        }
    }

    @Test(description = "Verify Users Created successfully",
    dataProvider = "userTestData",
    dataProviderClass = TestDataProvider.class)
    public void postCreateMultipleUsers(UserTestData testData) {
        try{
            System.out.println("========================================");
            System.out.println("Starting test: createUser");
            System.out.println("Test Data:");
            System.out.println("User ID      : " + testData.getId());
            System.out.println("First Name   : " + testData.getFirstName());
            System.out.println("Last Name    : " + testData.getLastName());
            System.out.println("Phone        : " + testData.getPhone());
            System.out.println("Email        : " + testData.getEmail());
            System.out.println("Username     : " + testData.getUsername());
            System.out.println("Password     : " + testData.getPassword());
            System.out.println("User Status  : " + testData.getUserStatus());
            System.out.println("========================================");

            User user = getUser(
                    testData.getId(),
                    testData.getFirstName(),
                    testData.getLastName(),
                    testData.getPhone(),
                    testData.getEmail(),
                    testData.getUsername(),
                    testData.getPassword(),
                    testData.getUserStatus());
            ApiResponse<Void> response = userApi.createUserWithHttpInfo(user);

            // validate that response is not null
            Assert.assertNotNull(response,"response should not be null");

            // validate status code to be 200
            Assert.assertEquals(response.getStatusCode(),200,
                    "Expected  HTTP Status code to be 200");

            // Verify created user using GET
            Assert.assertNotNull(user.getUsername());
            User createdUser = userApi.getUserByName(user.getUsername());

            Assert.assertNotNull(createdUser, "Created user should be retrievable");

            Assert.assertEquals(
                    createdUser.getUsername(),
                    user.getUsername(),
                    "Username should match"
            );

            Assert.assertEquals(
                    createdUser.getFirstName(),
                    user.getFirstName(),
                    "First name should match"
            );

            Assert.assertEquals(
                    createdUser.getLastName(),
                    user.getLastName(),
                    "Last name should match"
            );

            Assert.assertEquals(
                    createdUser.getEmail(),
                    user.getEmail(),
                    "Email should match"
            );

        } catch (ApiException e) {
            System.err.println("Exception when calling UserApi#createOrder");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
        }
    }

}
