package org.example.apps.beans;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rival
 * @since 2024-04-01
 */

@RestController
// @RequiredArgsConstructor
public class BeansController {

    private final BeansService beansService;
    private final MyBean myBean;

    public BeansController(
        @Qualifier("secondary") BeansService beansService,
        @Qualifier("first") MyBean myBean
    ) {
        this.beansService = beansService;
        this.myBean = myBean;
    }

    @GetMapping("")
    public String getTitle(){
        return beansService.getTitle();
    }



    @GetMapping("mail-host")
    public String getMailHost(){
        return beansService.getMailProperties().getHost();
    }


    @GetMapping("first-bean")
    public String getMyBean(){
        return myBean.getTitle();
    }
}
