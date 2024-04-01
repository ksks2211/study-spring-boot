package org.example.apps.beans;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @author rival
 * @since 2024-04-01
 */
@Service("primary")
@Primary
public class PrimaryBeansService implements BeansService{
    @Override
    public String getTitle() {
        return "Primary";
    }

    @Override
    public MailProperties getMailProperties() {
        return null;
    }
}
