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
public class BlogPostSearchParams {

    private String title;
    private String content;
    private String search;
}
