package org.example.apps.elastic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author rival
 * @since 2024-02-08
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogPostCreateRequest {

    private String title;
    private String content;
}
