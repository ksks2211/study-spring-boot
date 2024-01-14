package org.example.apps.mvc.dto;

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
