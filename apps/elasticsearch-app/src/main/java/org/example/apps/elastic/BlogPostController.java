package org.example.apps.elastic;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author rival
 * @since 2024-02-08
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("blogposts")
public class BlogPostController {

    private final BlogPostService blogPostService;

    @PostMapping("")
    public BlogPost createBlogPost(@RequestBody BlogPostCreateRequest reqBody){
        return blogPostService.create(reqBody.getTitle(), reqBody.getContent());
    }

    @GetMapping("")
    public Map<String, Object> searchBlogPost(BlogPostSearchParams params){
        List<BlogPost> result = blogPostService.searchBlogPostsByTitleOrContent(params.getTitle(), params.getContent(),params.getSearch());
        return Map.of("posts", result);
    }

}
