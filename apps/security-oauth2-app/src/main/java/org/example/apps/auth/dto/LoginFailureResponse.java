package org.example.apps.auth.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author rival
 * @since 2023-12-21
 */
@Data
@Builder
public class LoginFailureResponse {
    @Builder.Default
    private String message = "Check your username and password.";
}
