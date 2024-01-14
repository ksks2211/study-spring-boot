package org.example.apps.validation.dto.res;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author rival
 * @since 2023-10-24
 */

@Data
@AllArgsConstructor
public class ValidationErrorResponse {
    private String message;
    private Map<String,String> errors;
}
