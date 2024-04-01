package org.example.apps.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

/**
 * @author rival
 * @since 2024-04-01
 */
@Configuration
public class BeansConfig {

    @Bean(name="first")
    @Lazy
    public MyBean firstBean(){
        return  new MyBean("first");
    }

    @Bean(name="second")
    public MyBean secondBean(){
        return  new MyBean("second");
    }
}
