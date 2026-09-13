package org.openapitools.client;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class OrderTestData {

    private Long id;
    private Long petId;
    private Integer quantity;
    private OffsetDateTime shipDate;
    private String status;
    private Boolean complete;
}
