package org.example.apps.book.dto;

import lombok.Data;

import java.util.List;

/**
 * @author rival
 * @since 2024-01-20
 */

@Data
public class BookCreateRequest {


    private String title;
    private List<String> keywordValues;


}
