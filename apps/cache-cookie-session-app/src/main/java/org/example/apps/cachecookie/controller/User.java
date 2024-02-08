package org.example.apps.cachecookie.controller;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author rival
 * @since 2024-01-20
 */

@Data
@NoArgsConstructor
public class User  implements Serializable {

    @Serial
    private static final long serialVersionUID=123456L;
    private Long id;
    private String username;
}
