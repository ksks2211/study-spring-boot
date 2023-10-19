package org.example.apis.chat.controller;

import org.example.utils.common.ExampleUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rival
 * @since 2023-09-30
 */

@RestController
@RequestMapping("chat")
public class ChatController {

    @GetMapping("")
    public String chat(){

        int result = ExampleUtils.add(10, 20);
        return "Hello World : "+result;
    }
}
