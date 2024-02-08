package org.example.apps.elastic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author rival
 * @since 2024-02-08
 */
@Document(indexName = "blogposts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogPost {

    @Id
    private String id;

    private String title;
    private String content;
}
