package org.example.apps.auth.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author rival
 * @since 2023-12-21
 */
@Data
@Builder
public class LogInSuccessResponse {

    @Builder.Default
    private String message = "Successfully logged in.";
    private String token;
    private String username;
}
