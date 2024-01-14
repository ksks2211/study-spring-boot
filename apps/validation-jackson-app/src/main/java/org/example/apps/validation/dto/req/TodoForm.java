package org.example.apps.validation.dto.req;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * @author rival
 * @since 2023-11-08
 */

@Data
public class TodoForm {

    @NotEmpty
    private String title;

    @NotEmpty
    @Email
    private String email;


    @Digits(fraction = 0, integer = 10)
    String tel;


}
