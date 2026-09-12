package org.openapitools.client.utils;

import org.openapitools.client.model.Category;
import org.openapitools.client.model.Pet;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TestUtils {

    @NotNull
    public static Category getCategory(
            String petCategoryName,
            Long petId) {

        Category category = new Category();
        category.setName(petCategoryName);
        category.setId(petId);

        return category;
    }

    @NotNull
    public static Pet getPet(
            String petCategoryName,
            Long petId,
            String petName,
            Pet.StatusEnum status,
            List<String> photoUrls) {

        Category category = getCategory(
                petCategoryName,
                petId
        );

        Pet pet = new Pet();
        pet.setName(petName);
        pet.setCategory(category);
        pet.setStatus(status);
        pet.setPhotoUrls(photoUrls);

        return pet;
    }
}