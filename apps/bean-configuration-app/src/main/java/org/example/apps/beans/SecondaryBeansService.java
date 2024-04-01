package org.example.apps.beans;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author rival
 * @since 2024-04-01
 */
@Service("secondary")
@RequiredArgsConstructor
public class SecondaryBeansService implements BeansService{

    private final MailProperties mailProperties;
    @Override
    public String getTitle() {
        return "Secondary";
    }

    @Override
    public MailProperties getMailProperties() {
        return mailProperties;
    }
}
