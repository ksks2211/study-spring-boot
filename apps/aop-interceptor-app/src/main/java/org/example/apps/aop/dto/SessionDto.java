package org.example.apps.aop.dto;

import lombok.Data;

import java.util.List;

/**
 * @author rival
 * @since 2023-10-24
 */

@Data
public class SessionDto {

    private  String currentSession;
    private List<String> sessions;




}
