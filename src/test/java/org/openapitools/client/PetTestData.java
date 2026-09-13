package org.openapitools.client;

import lombok.Data;

import java.util.List;

@Data
public class PetTestData {

    private Long id;
    private String categoryName;
    private String name;
    private String status;
    private List<String> photoUrls;
}