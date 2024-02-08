package org.example.apps.elastic;

import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author rival
 * @since 2024-02-08
 */
public interface BlogPostRepository extends ElasticsearchRepository<BlogPost, String> {

    List<BlogPost> findByTitleContaining(String title, Pageable pageable);
    List<BlogPost> findByContentContaining(String content,Pageable pageable);

    @Query("{\"multi_match\": {\"query\": \"?0\", \"fields\": [\"title\", \"content\"]}}")
    List<BlogPost> findByTitleContainingOrContentContaining(String searchText, Pageable pageable);

}
