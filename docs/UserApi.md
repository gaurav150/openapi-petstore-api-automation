# UserApi

All URIs are relative to *http://petstore.swagger.io/v2*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createUser**](UserApi.md#createUser) | **POST** /user | Create user |
| [**createUsersWithListInput**](UserApi.md#createUsersWithListInput) | **POST** /user/createWithList | Creates list of users with given input array |
| [**deleteUser**](UserApi.md#deleteUser) | **DELETE** /user/{username} | Delete user |
| [**getUserByName**](UserApi.md#getUserByName) | **GET** /user/{username} | Get user by user name |
| [**loginUser**](UserApi.md#loginUser) | **GET** /user/login | Logs user into the system |
| [**logoutUser**](UserApi.md#logoutUser) | **GET** /user/logout | Logs out current logged-in user |
| [**updateUser**](UserApi.md#updateUser) | **PUT** /user/{username} | Updated user |


<a id="createUser"></a>
# **createUser**
> createUser(user)

Create user

This can only be done by the logged-in user.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UserApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://petstore.swagger.io/v2");
    
    // Configure OAuth2 access token for authorization: petstore_auth
    OAuth petstore_auth = (OAuth) defaultClient.getAuthentication("petstore_auth");
    petstore_auth.setAccessToken("YOUR ACCESS TOKEN");

    UserApi apiInstance = new UserApi(defaultClient);
    User user = new User(); // User | Created user object
    try {
      apiInstance.createUser(user);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserApi#createUser");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **user** | [**User**](User.md)| Created user object | |

### Return type

null (empty response body)

### Authorization

[petstore_auth](../README.md#petstore_auth)

### HTTP request headers

 - **Content-Type**: application/json, application/xml
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **0** | Successful operation |  -  |

<a id="createUsersWithListInput"></a>
# **createUsersWithListInput**
> createUsersWithListInput(user)

Creates list of users with given input array

Creates multiple users from a supplied array.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UserApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://petstore.swagger.io/v2");
    
    // Configure OAuth2 access token for authorization: petstore_auth
    OAuth petstore_auth = (OAuth) defaultClient.getAuthentication("petstore_auth");
    petstore_auth.setAccessToken("YOUR ACCESS TOKEN");

    UserApi apiInstance = new UserApi(defaultClient);
    List<User> user = Arrays.asList(); // List<User> | List of user objects
    try {
      apiInstance.createUsersWithListInput(user);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserApi#createUsersWithListInput");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **user** | [**List&lt;User&gt;**](User.md)| List of user objects | |

### Return type

null (empty response body)

### Authorization

[petstore_auth](../README.md#petstore_auth)

### HTTP request headers

 - **Content-Type**: application/json, application/xml
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful operation |  -  |

<a id="deleteUser"></a>
# **deleteUser**
> deleteUser(username)

Delete user

This can only be done by the logged-in user.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UserApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://petstore.swagger.io/v2");
    
    // Configure OAuth2 access token for authorization: petstore_auth
    OAuth petstore_auth = (OAuth) defaultClient.getAuthentication("petstore_auth");
    petstore_auth.setAccessToken("YOUR ACCESS TOKEN");

    UserApi apiInstance = new UserApi(defaultClient);
    String username = "username_example"; // String | The user that needs to be deleted
    try {
      apiInstance.deleteUser(username);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserApi#deleteUser");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **username** | **String**| The user that needs to be deleted | |

### Return type

null (empty response body)

### Authorization

[petstore_auth](../README.md#petstore_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Invalid username supplied |  -  |
| **404** | User not found |  -  |

<a id="getUserByName"></a>
# **getUserByName**
> User getUserByName(username)

Get user by user name

Returns a single user.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UserApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://petstore.swagger.io/v2");
    
    // Configure OAuth2 access token for authorization: petstore_auth
    OAuth petstore_auth = (OAuth) defaultClient.getAuthentication("petstore_auth");
    petstore_auth.setAccessToken("YOUR ACCESS TOKEN");

    UserApi apiInstance = new UserApi(defaultClient);
    String username = "username_example"; // String | The name that needs to be fetched
    try {
      User result = apiInstance.getUserByName(username);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserApi#getUserByName");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **username** | **String**| The name that needs to be fetched | |

### Return type

[**User**](User.md)

### Authorization

[petstore_auth](../README.md#petstore_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json, application/xml

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful operation |  -  |
| **400** | Invalid username supplied |  -  |
| **404** | User not found |  -  |

<a id="loginUser"></a>
# **loginUser**
> String loginUser(username, password)

Logs user into the system

Login using username and password.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UserApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://petstore.swagger.io/v2");

    UserApi apiInstance = new UserApi(defaultClient);
    String username = "username_example"; // String | The username for login
    String password = "password_example"; // String | The password for login
    try {
      String result = apiInstance.loginUser(username, password);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserApi#loginUser");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **username** | **String**| The username for login | |
| **password** | **String**| The password for login | |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json, application/xml

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful operation |  * X-Rate-Limit - calls per hour allowed by the user <br>  * X-Expires-After - date in UTC when token expires <br>  |
| **400** | Invalid username/password |  -  |

<a id="logoutUser"></a>
# **logoutUser**
> logoutUser()

Logs out current logged-in user

Logs out the current logged-in user.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UserApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://petstore.swagger.io/v2");
    
    // Configure OAuth2 access token for authorization: petstore_auth
    OAuth petstore_auth = (OAuth) defaultClient.getAuthentication("petstore_auth");
    petstore_auth.setAccessToken("YOUR ACCESS TOKEN");

    UserApi apiInstance = new UserApi(defaultClient);
    try {
      apiInstance.logoutUser();
    } catch (ApiException e) {
      System.err.println("Exception when calling UserApi#logoutUser");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

null (empty response body)

### Authorization

[petstore_auth](../README.md#petstore_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **0** | Successful operation |  -  |

<a id="updateUser"></a>
# **updateUser**
> updateUser(username, user)

Updated user

This can only be done by the logged-in user.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UserApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://petstore.swagger.io/v2");
    
    // Configure OAuth2 access token for authorization: petstore_auth
    OAuth petstore_auth = (OAuth) defaultClient.getAuthentication("petstore_auth");
    petstore_auth.setAccessToken("YOUR ACCESS TOKEN");

    UserApi apiInstance = new UserApi(defaultClient);
    String username = "username_example"; // String | Name that needs to be updated
    User user = new User(); // User | Updated user object
    try {
      apiInstance.updateUser(username, user);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserApi#updateUser");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **username** | **String**| Name that needs to be updated | |
| **user** | [**User**](User.md)| Updated user object | |

### Return type

null (empty response body)

### Authorization

[petstore_auth](../README.md#petstore_auth)

### HTTP request headers

 - **Content-Type**: application/json, application/xml
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **0** | Successful operation |  -  |

