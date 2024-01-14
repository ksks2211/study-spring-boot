package org.example.apps.auth.dto;

import lombok.Data;

/**
 * @author rival
 * @since 2023-12-21
 */
@Data
public class LogInRequest {
    private String username;
    private String password;
}
