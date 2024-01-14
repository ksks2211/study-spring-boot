package org.example.apps.validation.dto.req;

import jakarta.validation.constraints.*;
import lombok.Data;

/**
 * @author rival
 * @since 2023-10-24
 */
@Data
public class UserRegistrationRequest {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Email(message = "Email should be valid")
    @NotNull(message = "Email cannot be null")
    @Size(max = 255, message = "Email cannot be longer than 255 characters")
    private String email;

    @NotNull(message = "Age cannot be null")
    @Min(value = 1, message = "Age should not be less than 1")
    @Max(value = 100, message = "Age should not be greater than 100")
    private Integer age;
}
