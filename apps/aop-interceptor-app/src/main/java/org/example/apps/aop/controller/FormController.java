package org.example.apps.aop.controller;

import org.example.apps.aop.dto.UserDto;
import org.springframework.web.bind.annotation.*;

/**
 * @author rival
 * @since 2023-10-23
 */
@RestController
@RequestMapping("form")
public class FormController {

    @GetMapping("/test")
    public String test(){
        return "Test";
    }

    @PostMapping("/handle-form")
    public String handleFormReq(@RequestParam String username, @RequestParam String password){
        return "Username : "+username+", Password : "+password;
    }

    @PostMapping("/handle-form2")
    public String handleFormReq2(@ModelAttribute UserDto user){
        return "Username : "+user.getUsername()+", Password : "+user.getPassword();
    }

}
