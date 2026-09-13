package org.openapitools.client;

import lombok.Data;

@Data
public class UserTestData {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;
}
