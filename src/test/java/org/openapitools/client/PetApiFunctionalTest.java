package org.openapitools.client;

import org.openapitools.client.api.PetApi;
import org.openapitools.client.model.Pet;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;
import static org.openapitools.client.utils.TestUtils.getPet;


public class PetApiFunctionalTest {

    private PetApi petAPi;

    @BeforeClass
    public void setUp() {
        petAPi = new PetApi();
        petAPi.getApiClient().setBasePath("https://petstore.swagger.io/v2");
    }

    // ==================== Add Pet ====================

    // Positive test cases
    @Test(description = "Verify adding a new pet with valid details")
    public void addNewPetValidDetailsTest() {
        try {
            String petCategoryName = "German Shepherd";
            Long petId = 1223L;
            String petName = "Lucy";
            List<String> photoUrls = new ArrayList<>();
            photoUrls.add("http://example.com");
            photoUrls.add("http://goodExample.com");
            // Creating Pet
            Pet pet = getPet(petCategoryName, petId, petName, Pet.StatusEnum.AVAILABLE, photoUrls);

            // Send POST Request
            Pet response = petAPi.addPet(pet);

            // Validate response
            Assert.assertNotNull(response, "Response should not be null");

            // Validate Name
            Assert.assertEquals(
                    response.getName(),
                    petName,
                    "Pet name should match"
            );

            // Validate status from response
            Assert.assertEquals(
                    response.getStatus(),
                    Pet.StatusEnum.AVAILABLE,
                    "Pet status should be AVAILABLE"
            );


        } catch (Exception e) {
            Assert.fail("API call failed: " + e.getMessage());
        }
    }

    @Test(description = "Verify adding a new pet with valid details and Status as Pending")
    public void addNewPetValidDetailsStatusAsPendingTest() {
        try {
            String petCategoryName = "German Shepherd";
            Long petId = 1223L;
            String petName = "Bauer";
            List<String> photoUrls = new ArrayList<>();
            photoUrls.add("http://example.com");
            photoUrls.add("http://goodExample.com");
            // Creating Pet
            Pet pet = getPet(petCategoryName, petId, petName, Pet.StatusEnum.PENDING, photoUrls);

            // Send POST Request
            Pet response = petAPi.addPet(pet);

            // Validate response
            Assert.assertNotNull(response, "Response should not be null");

            // Validate Name
            Assert.assertEquals(
                    response.getName(),
                    petName,
                    "Pet name should match"
            );

            // Validate status from response
            Assert.assertEquals(
                    response.getStatus(),
                    Pet.StatusEnum.PENDING,
                    "Pet status should be Pending"
            );


        } catch (Exception e) {
            Assert.fail("API call failed: " + e.getMessage());
        }
    }

    @Test(description = "Verify adding a new pet with valid details and Status as Sold")
    public void addNewPetValidDetailsStatusAsPSoldTest() {
        try {
            String petCategoryName = "German Shepherd";
            Long petId = 1223L;
            String petName = "Bauer";
            List<String> photoUrls = new ArrayList<>();
            photoUrls.add("http://example.com");
            photoUrls.add("http://goodExample.com");
            // Creating Pet
            Pet pet = getPet(petCategoryName, petId, petName, Pet.StatusEnum.SOLD, photoUrls);

            // Send POST Request
            Pet response = petAPi.addPet(pet);

            // Validate response
            Assert.assertNotNull(response, "Response should not be null");

            // Validate Name
            Assert.assertEquals(
                    response.getName(),
                    petName,
                    "Pet name should match"
            );

            // Validate status from response
            Assert.assertEquals(
                    response.getStatus(),
                    Pet.StatusEnum.SOLD,
                    "Pet status should be Sold"
            );


        } catch (Exception e) {
            Assert.fail("API call failed: " + e.getMessage());
        }
    }

    @Test(
            description = "Verify adding a new pet with valid details",
            dataProvider = "petTestData",
            dataProviderClass = TestDataProvider.class
    )
    public void addNewPetValidDetailsTest(PetTestData testData) {

        try {

            Pet pet = getPet(
                    testData.getCategoryName(),
                    testData.getId(),
                    testData.getName(),
                    Pet.StatusEnum.fromValue(testData.getStatus()),
                    testData.getPhotoUrls()
            );

            // Send POST Request
            Pet response = petAPi.addPet(pet);

            // Validate response
            Assert.assertNotNull(
                    response,
                    "Response should not be null"
            );

            // Validate Name
            Assert.assertEquals(
                    response.getName(),
                    testData.getName(),
                    "Pet name should match"
            );

            // Validate Status
            Assert.assertEquals(
                    response.getStatus(),
                    Pet.StatusEnum.fromValue(testData.getStatus()),
                    "Pet status should match"
            );

        } catch (Exception e) {
            Assert.fail("API call failed: " + e.getMessage());
        }
    }

    // Negative test cases
    @Test(description = "Verify adding a pet with missing required fields")
    public void addPetMissingDetailsTest() {
        try {
            String petCategoryName = "German Shepherd";
            Long petId = 1223L;
            List<String> photoUrls = new ArrayList<>();
            photoUrls.add("http://example.com");
            photoUrls.add("http://goodExample.com");
            // Creating Pet
            Pet pet = getPet(petCategoryName, petId, null, Pet.StatusEnum.AVAILABLE, photoUrls);

            // Send POST Request
            petAPi.addPet(pet);

            // If API call succeeds, negative test should fail
            Assert.fail("Expected API to reject the request because name is missing");


        } catch (Exception e) {
            // Expected exception because required name is missing
            Assert.assertTrue(
                    e.getMessage().contains("required field `name`"),
                    "Expected error message for missing required name"
            );

            System.out.println(
                    "Expected exception received: " + e.getMessage()
            );
        }
    }

    // ==================== Delete Pet ====================

    // Positive test cases
    @Test(description = "Verify deleting an existing pet with a valid pet ID")
    public void deleteExistingPetValidPet() {

        try {
            String petCategoryName = "German Shepherd";
            Long petId = 1224L;
            String petName = "Max";

            List<String> photoUrls = new ArrayList<>();
            photoUrls.add("http://example.com");
            photoUrls.add("http://goodExample.com");

            // Create pet
            Pet pet = getPet(
                    petCategoryName,
                    petId,
                    petName,
                    Pet.StatusEnum.AVAILABLE,
                    photoUrls
            );

            Pet createdPet = petAPi.addPet(pet);

            Assert.assertNotNull(
                    createdPet,
                    "Pet should be created successfully"
            );

            Long createdPetId = createdPet.getId();

            // Delete pet
            String apiKey = "my-real-api-key";

            Assert.assertNotNull(createdPetId, "Pet should be created successfully");

            ApiResponse<Void> response =
                    petAPi.deletePetWithHttpInfo(
                            createdPetId,
                            apiKey
                    );

            // Verify DELETE response
            Assert.assertEquals(
                    response.getStatusCode(),
                    200,
                    "Expected HTTP status code 200"
            );

        } catch (Exception e) {
            Assert.fail("API call failed: " + e.getMessage());
        }
    }

    // Negative test cases
    @Test(description = "Verify deleting a pet with an invalid pet ID")
    public void deleteInvalidPet() {
        try {
            Long petId = 1224L;
            String apiKey = "my-real-api-key";
            petAPi.deletePet(
                    petId,
                    apiKey
            );
            // If API call succeeds, negative test should fail
            Assert.fail("Expected API to reject the request because name is missing");
        } catch (ApiException e) {

            System.out.println(
                    "Expected exception received for delete: " + e.getMessage()
            );

            Assert.assertEquals(
                    e.getCode(),
                    404,
                    "Expected HTTP 404 for a non-existing pet ID"
            );

            Assert.assertTrue(
                    e.getResponseBody() == null || e.getResponseBody().isEmpty(),
                    "Expected response body to be empty"
            );

        } catch (Exception e) {

            Assert.fail(
                    "Unexpected exception occurred: " + e.getMessage()
            );
        }
    }

    @Test(description = "Verify retrieving pets by valid status values")
    public void getPetsByValidStatusTest() {

        try {

            List<String> status = new ArrayList<>();
            status.add("available");
            status.add("pending");
            List<Pet> response = petAPi.findPetsByStatus(status);

            // Verify response is not null
            Assert.assertNotNull(
                    response,
                    "Response should not be null"
            );

            // Verify response contains pets
            Assert.assertFalse(
                    response.isEmpty(),
                    "Response should contain at least one pet"
            );

            // Verify each pet in the response
            for (Pet pet : response) {

                Assert.assertNotNull(
                        pet.getId(),
                        "Pet ID should not be null"
                );

                Assert.assertNotNull(
                        pet.getName(),
                        "Pet name should not be null"
                );

                Assert.assertNotNull(
                        pet.getStatus(),
                        "Pet status should not be null"
                );

                // Verify returned status is one of the requested statuses
                Assert.assertTrue(
                        status.contains(pet.getStatus().getValue()),
                        "Pet status should be either available or pending"
                );
            }
        } catch (Exception e) {
            Assert.fail("API call failed: " + e.getMessage());
        }
    }

    @Test(description = "Verify retrieving pet by valid petId")
    public void getPetByPetID() throws InterruptedException {
        try {
            String petCategoryName = "German Shepherd";
            Long petId = 1223L;
            String petName = "Lucy";
            List<String> photoUrls = new ArrayList<>();
            photoUrls.add("http://example.com");
            photoUrls.add("http://goodExample.com");
            // Creating Pet
            Pet pet = getPet(petCategoryName, petId, petName, Pet.StatusEnum.AVAILABLE, photoUrls);

            // Send POST Request
            Pet response = petAPi.addPet(pet);

            // Validate response
            Assert.assertNotNull(response, "Response should not be null");

            // Validate Name
            Assert.assertEquals(
                    response.getName(),
                    petName,
                    "Pet name should match"
            );

            // Validate status from response
            Assert.assertEquals(
                    response.getStatus(),
                    Pet.StatusEnum.AVAILABLE,
                    "Pet status should be AVAILABLE"
            );
            Long newPetId = response.getId();
            Assert.assertNotNull(newPetId);

            Pet result = petAPi.getPetById(newPetId);

            // Verify  response is not null
            Assert.assertNotNull(result, "Retrieved pet should not be null");

            // verify ID
            Assert.assertEquals(newPetId, response.getId(),
                    "pet ID should match the created pet ID");

            // Verify Name,
            Assert.assertEquals(result.getName(), petName, "Pet name should match");

            // verify status
            Assert.assertEquals(result.getStatus(), Pet.StatusEnum.AVAILABLE,
                    "Pet status should  be AVAILABLE");

            // Verify Category
            Assert.assertNotNull(
                    result.getCategory(),
                    "Pet category should not be null"
            );

            Assert.assertEquals(
                    result.getCategory().getName(),
                    petCategoryName,
                    "Pet category name should match"
            );

            // Verify Photo URLs
            Assert.assertNotNull(
                    result.getPhotoUrls(),
                    "Photo URLs should not be null"
            );

            Assert.assertEquals(
                    result.getPhotoUrls(),
                    photoUrls,
                    "Photo URLs should match"
            );
        } catch (ApiException e) {
            if (e.getCode() == 404) {
                sleep(1000);
            } else {
                Assert.fail("API call failed: " + e.getMessage());
            }

        }
    }

    @Test(description = "Verify getting pet with invalid pet ID")
    public void getPetByInvalidPetIdTest() {

        try {
            petAPi.getPetById(-999L);
            Assert.fail("Expected 404 exception");
        } catch (ApiException e) {

            Assert.assertEquals(
                    e.getCode(),
                    404,
                    "Status code should be 404"
            );

            Assert.assertTrue(
                    e.getResponseBody().contains("Pet not found"),
                    "Error message should contain Pet not found"
            );
        }
    }

    @Test(description = "Verify deleting a pet successfully using a valid pet ID")
    public void deletePetByValidPetIDTest() {
        try {
            String petCategoryName = "German Shepherd";
            Long petId = 1223L;
            String petName = "Lucy";
            List<String> photoUrls = new ArrayList<>();
            photoUrls.add("http://example.com");
            photoUrls.add("http://goodExample.com");
            // Creating Pet
            Pet pet = getPet(petCategoryName, petId, petName, Pet.StatusEnum.AVAILABLE, photoUrls);

            // Send POST Request
            Pet response = petAPi.addPet(pet);

            // Validate response
            Assert.assertNotNull(response, "Response should not be null");

            // Validate Name
            Assert.assertEquals(
                    response.getName(),
                    petName,
                    "Pet name should match"
            );

            // Validate status from response
            Assert.assertEquals(
                    response.getStatus(),
                    Pet.StatusEnum.AVAILABLE,
                    "Pet status should be AVAILABLE"
            );
            Long createdPetId = response.getId();
            Assert.assertNotNull(createdPetId);
            String apiKey = "my-real-api-key";

            Assert.assertNotNull(createdPetId, "Pet should be created successfully");

            ApiResponse<Void> result =
                    petAPi.deletePetWithHttpInfo(
                            createdPetId,
                            apiKey
                    );

            // Verify DELETE response
            Assert.assertEquals(
                    result.getStatusCode(),
                    200,
                    "Expected HTTP status code 200"
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Delete pet with non-existing Pet ID
    @Test(description = "Verify deleting a pet with a non-existing pet ID returns 404")
    public void deletePetByNonExistingPetIdTest() {

        try {
            Long petId = 999999999L;
            String apiKey = "my-real-api-key";

            petAPi.deletePet(petId, apiKey);

            Assert.fail(
                    "Expected 404 exception for non-existing pet ID"
            );

        } catch (ApiException e) {

            Assert.assertEquals(
                    e.getCode(),
                    404,
                    "Expected HTTP status code 404"
            );

            Assert.assertTrue(
                    e.getResponseBody() == null ||
                            e.getResponseBody().isEmpty(),
                    "Expected response body to be empty"
            );
        }
    }

    //  Delete pet with invalid/negative Pet ID
    @Test(description = "Verify deleting a pet with an invalid pet ID")
    public void deletePetByInvalidPetIdTest() {

        try {
            Long petId = -123L;
            String apiKey = "my-real-api-key";

            petAPi.deletePet(petId, apiKey);

            Assert.fail(
                    "Expected exception for invalid pet ID"
            );

        } catch (ApiException e) {

            Assert.assertEquals(
                    e.getCode(),
                    404,
                    "Expected HTTP status code 404"
            );
        }
    }

    // Delete pet without API key
    @Test(description = "Verify deleting a pet without an API key")
    public void deletePetWithoutApiKeyTest() {

        try {
            Long petId = 1223L;

            petAPi.deletePet(petId, null);

            Assert.fail(
                    "Expected exception when API key is not provided"
            );

        } catch (ApiException e) {
            Assert.assertEquals(e.getCode(), 404,
                    "Expected HTTP 404 when API key is missing");
        }
    }
}
