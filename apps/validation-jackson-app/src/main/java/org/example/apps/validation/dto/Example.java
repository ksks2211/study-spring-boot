package org.example.apps.validation.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author rival
 * @since 2023-12-22
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties("internalField")
public class Example {
    @JsonProperty("special_field")
    private String someField;


    private String notEmptyField;
    private String emptyField;


    @JsonIgnore
    private String hiddenField;
    private String internalField;


    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    @Builder.Default
    private LocalDateTime current = LocalDateTime.now();
}
