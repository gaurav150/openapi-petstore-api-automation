package org.openapitools.client;

import org.jetbrains.annotations.NotNull;
import org.openapitools.client.api.PetApi;
import org.openapitools.client.model.Category;
import org.openapitools.client.model.Pet;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


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

            System.out.println(
                    "Pet deleted successfully: " + createdPetId
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

    @NotNull
    private static Category getCategory(String petCategoryName, Long petId) {
        // Create category
        Category category = new Category();
        category.setName(petCategoryName);
        category.setId(petId);
        return category;
    }

    @NotNull
    private static Pet getPet(String petCategoryName, Long petId, String petName, Pet.StatusEnum statusEnum, List<String> photoUrls) {
        // Create category
        Category category = getCategory(petCategoryName, petId);
        // Creating pet Data
        Pet pet = new Pet();
        pet.setName(petName);
        pet.setCategory(category);
        pet.setStatus(statusEnum);
        pet.setPhotoUrls(photoUrls);
        return pet;
    }
}
