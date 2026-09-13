package org.openapitools.client.utils;

import org.openapitools.client.model.Category;
import org.openapitools.client.model.Order;
import org.openapitools.client.model.Pet;
import org.jetbrains.annotations.NotNull;
import org.openapitools.client.model.User;

import java.time.OffsetDateTime;
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

    public static Order getOrder(
            Long orderId,
            Long petId,
            Integer quantity,
            OffsetDateTime shipDate,
            Order.StatusEnum status,
            Boolean complete) {

        Order order = new Order();

        order.setId(orderId);
        order.setPetId(petId);
        order.setQuantity(quantity);
        order.setShipDate(shipDate);
        order.setStatus(status);
        order.setComplete(complete);

        return order;
    }

    public static User getUser(
            Long id,
            String firstName,
            String lastName,
            String phone,
            String email,
            String userName,
            String password,
            int userStatus
    ){
        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhone(phone);
        user.setEmail(email);
        user.setUserStatus(userStatus);
        user.setPassword(password);
        user.setUsername(userName);
        return user;
    }
}