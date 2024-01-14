package org.example.apps.auth.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author rival
 * @since 2023-12-21
 */
@NoArgsConstructor
@Data
public class UnauthorizedAccessResponse implements Serializable {
    private final String message = "Unauthorized Request!";
}
