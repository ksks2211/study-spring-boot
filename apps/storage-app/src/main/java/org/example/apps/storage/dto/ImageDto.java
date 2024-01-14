package org.example.apps.storage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apps.storage.entity.Image;

/**
 * @author rival
 * @since 2023-12-30
 */
@Data
@Builder
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto{
    private byte[] bytes;
    private String contentType;
    private Long id;
    private Long size;
    private String originalName;


    public static ImageDto fromEntity(Image entity, byte[] bytes){
        return ImageDto.builder()
            .id(entity.getId())
            .bytes(bytes)
            .contentType(entity.getContentType())
            .size(entity.getSize())
            .build();
    }
}
