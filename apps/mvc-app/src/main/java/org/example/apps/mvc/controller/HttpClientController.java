package org.example.apps.mvc.controller;

import org.example.apps.mvc.dto.PostDto;
import org.example.apps.mvc.service.HttpClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author rival
 * @since 2023-10-24
 */

@RestController
@RequestMapping("client")
@RequiredArgsConstructor
public class HttpClientController {

    private final HttpClientService httpClientService;

    @GetMapping("/posts/{id}")
    public PostDto getPost(@PathVariable(name="id") Long id) throws IOException {
        return httpClientService.getPost(id);
    }


}
