package org.example.apps.elastic;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.client.erhlc.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author rival
 * @since 2024-02-08
 */
@Service
@RequiredArgsConstructor
public class BlogPostService {

    private final BlogPostRepository blogPostRepository;

    private final ElasticsearchOperations elasticsearchOperations;

    private PageRequest getPageable(int page){
        return  PageRequest.of(page, 10);
    }

    private List<BlogPost> searchBlogPostsByTitle(String title){
        return blogPostRepository.findByTitleContaining(title,getPageable(0));
    }

    private List<BlogPost> searchBlogPostsByContent(String content){
        return blogPostRepository.findByContentContaining(content,getPageable(0));
    }

    public List<BlogPost> searchBlogPostsByTitleOrContent(String title, String content, String text){

        boolean searchTitle = StringUtils.hasText(title);
        boolean searchContent = StringUtils.hasText(content);
        boolean searchText = StringUtils.hasText(text);


        if(searchText) {
            return blogPostRepository.findByTitleContainingOrContentContaining(text, getPageable(0));
        }else if(searchTitle){
            return searchBlogPostsByTitle(title);
        }else if(searchContent){
            return searchBlogPostsByContent(content);
        }else{
            return List.of();
        }
    }

    public BlogPost create(String title, String content) {
        BlogPost blogPost = new BlogPost();
        blogPost.setTitle(title);
        blogPost.setContent(content);
        return blogPostRepository.save(blogPost);
    }



}
